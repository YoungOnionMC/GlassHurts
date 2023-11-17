package com.youngonion.glasshurts.core.mixin;

import com.youngonion.glasshurts.event.GlassDamageSource;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockGlass.class)
public class BlockGlassMixin extends BlockBreakable {

    protected final AxisAlignedBB EPSILON_AABB = new AxisAlignedBB(0.01D, 0.01D, 0.01D, 0.99D, 0.99D, 0.99D);

    protected BlockGlassMixin(Material materialIn, boolean ignoreSimilarity) {
        super(materialIn, ignoreSimilarity);

    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if(worldIn.isRemote) {
            return;
        }

        double motionDownwards = entityIn.motionY;
        Vec3d velocity = new Vec3d(entityIn.motionX, entityIn.motionY, entityIn.motionZ);

        if(motionDownwards < 0.07840000092983247f && motionDownwards >= 0.0f) {// seems to be gravity constant???
            return;
        }

        double mag = velocity.length();

        if(mag <= 0.10f) { // slow enough speed it won't break the glass
            return;
        }

        System.out.println("entity speed: " + mag);

        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());

        entityIn.attackEntityFrom(GlassDamageSource.GLASS_DAMAGE, 3);
        if(entityIn instanceof EntityLivingBase ELB) {
            ELB.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 90, 3));
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return EPSILON_AABB;
    }
}
