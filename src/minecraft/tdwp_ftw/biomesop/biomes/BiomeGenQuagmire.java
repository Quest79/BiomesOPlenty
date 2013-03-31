package tdwp_ftw.biomesop.biomes;

import java.util.Random;
import java.awt.Color;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadTree;

public class BiomeGenQuagmire extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenQuagmire(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = (byte)mod_BiomesOPlenty.mud.blockID;
        this.fillerBlock = (byte)mod_BiomesOPlenty.mud.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 0;
        this.customBiomeDecorator.grassPerChunk = 10;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
        this.waterColorMultiplier = 13390080;
		this.customBiomeDecorator.generateQuagmire = true;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenDeadTree(false);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 10390377;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 10390377;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (mod_BiomesOPlenty.skyColors = true)
		{
        return 12436670;
		}	
		else
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
    }
}