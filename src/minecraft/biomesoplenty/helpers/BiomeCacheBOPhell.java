package biomesoplenty.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.LongHashMap;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.world.WorldChunkManagerBOPhell;

public class BiomeCacheBOPhell
{
	private final WorldChunkManagerBOPhell chunkManager;
	private long lastCleanupTime = 0L;
	private LongHashMap cacheMap = new LongHashMap();

	@SuppressWarnings("rawtypes")
	private List cache = new ArrayList();

	public BiomeCacheBOPhell(WorldChunkManagerBOPhell par1WorldChunkManager)
	{
		chunkManager = par1WorldChunkManager;
	}

	@SuppressWarnings("unchecked")
	public BiomeCacheBlockBOPhell getBiomeCacheBlock(int par1, int par2)
	{
		par1 >>= 4;
		par2 >>= 4;
		long var3 = par1 & 4294967295L | (par2 & 4294967295L) << 32;
		BiomeCacheBlockBOPhell var5 = (BiomeCacheBlockBOPhell)cacheMap.getValueByKey(var3);

		if (var5 == null)
		{
			var5 = new BiomeCacheBlockBOPhell(this, par1, par2);
			cacheMap.add(var3, var5);
			cache.add(var5);
		}

		var5.lastAccessTime = System.currentTimeMillis();
		return var5;
	}

	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return this.getBiomeCacheBlock(par1, par2).getBiomeGenAt(par1, par2);
	}

	public void cleanupCache()
	{
		long var1 = System.currentTimeMillis();
		long var3 = var1 - lastCleanupTime;

		if (var3 > 7500L || var3 < 0L)
		{
			lastCleanupTime = var1;

			for (int var5 = 0; var5 < cache.size(); ++var5)
			{
				BiomeCacheBlockBOPhell var6 = (BiomeCacheBlockBOPhell)cache.get(var5);
				long var7 = var1 - var6.lastAccessTime;

				if (var7 > 30000L || var7 < 0L)
				{
					cache.remove(var5--);
					long var9 = var6.xPosition & 4294967295L | (var6.zPosition & 4294967295L) << 32;
					cacheMap.remove(var9);
				}
			}
		}
	}

	public BiomeGenBase[] getCachedBiomes(int par1, int par2)
	{
		return this.getBiomeCacheBlock(par1, par2).biomes;
	}

	static WorldChunkManagerBOPhell getChunkManager(BiomeCacheBOPhell par0BiomeCache)
	{
		return par0BiomeCache.chunkManager;
	}
}
