package biomesoplenty.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemBOPFlower extends ItemBlock
{
    private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "daisy", "tulip", "wildflower", "violet", "anemone", "toadstool", "cactus", "yucca", "portobello", "bluemilk"};
    
    public ItemBOPFlower(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta & 15;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return (new StringBuilder()).append(plants[itemStack.getItemDamage()]).toString();
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return Block.blocksList[this.itemID].getIcon(0, meta);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        int id = world.getBlockId(x, y, z);

        if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
            side = 1;
        else if (!Block.blocksList[id].isBlockReplaceable(world, x, y, z))
        {
            if (side == 0)
                --y;

            if (side == 1)
                ++y;

            if (side == 2)
                --z;

            if (side == 3)
                ++z;

            if (side == 4)
                --x;

            if (side == 5)
                ++x;
        }

        if (!player.canPlayerEdit(x, y, z, side, itemStack))
        {
            return false;
        }
        else if (itemStack.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (world.canPlaceEntityOnSide(this.getBlockID(), x, y, z, false, side, (Entity)null, itemStack))
            {
                Block block = Block.blocksList[this.getBlockID()];
                int j1 = block.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, 0);

                if (world.setBlock(x, y, z, this.getBlockID(), itemStack.getItemDamage(), 3))
                {
                    if (world.getBlockId(x, y, z) == this.getBlockID())
                    {
                        Block.blocksList[this.getBlockID()].onBlockPlacedBy(world, x, y, z, player, itemStack);
                        Block.blocksList[this.getBlockID()].onPostBlockPlaced(world, x, y, z, j1);
                    }

                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --itemStack.stackSize;
                }
            }

            return true;
        }
    }
}
