package biomesoplenty.blocks;

import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

@Deprecated
public class BlockAmethystBlock extends Block
{
    public BlockAmethystBlock(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
	
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return BOPBlocks.amethystBlock.blockID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:amethystblock");
	}
}
