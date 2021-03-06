package biomesoplenty.configuration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Biomes;
import biomesoplenty.entities.EntityGlob;
import biomesoplenty.entities.EntityJungleSpider;
import biomesoplenty.entities.EntityRosester;
import biomesoplenty.entities.projectiles.EntityDart;
import biomesoplenty.entities.projectiles.EntityMudball;
import biomesoplenty.entities.projectiles.EntityPoisonDart;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPEntities {

	//Eggs
	public static int eggIdCounter = 300;

	//Find the first available egg ID after our egg ID counter
	public static int getUniqueEntityEggId() {
		do {
			eggIdCounter++;
		} while (EntityList.getStringFromID(eggIdCounter) != null);

		return eggIdCounter;
	}

	@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
		int id = getUniqueEntityEggId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}

	public static void init()
	{
		EntityRegistry.registerModEntity(EntityMudball.class, "MudBall", BOPConfiguration.entityMudballID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDart.class, "Dart", BOPConfiguration.entityDartID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityPoisonDart.class, "PoisonDart", BOPConfiguration.entityPoisonDartID, BiomesOPlenty.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", BOPConfiguration.jungleSpiderID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", BOPConfiguration.rosesterID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityGlob.class, "Glob", BOPConfiguration.globID, BiomesOPlenty.instance, 80, 3, true);

		registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);
		registerEntityEgg(EntityRosester.class, 14831439, 16756224);
		registerEntityEgg(EntityGlob.class, 6836276, 8414787);

		if (Biomes.bambooForest.isPresent() && Biomes.jungleNew.isPresent() && Biomes.tropicalRainforest.isPresent() && Biomes.oasis.isPresent() && Biomes.tropics.isPresent())
		{
			EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, Biomes.bambooForest.get(), Biomes.jungleNew.get(), Biomes.tropicalRainforest.get(), Biomes.oasis.get(), Biomes.tropics.get());
		}

		if (Biomes.bog.isPresent() && Biomes.deadSwamp.isPresent() && Biomes.fen.isPresent() && Biomes.moor.isPresent() && Biomes.quagmire.isPresent() && Biomes.sludgepit.isPresent() && Biomes.swamplandNew.isPresent())
		{
			EntityRegistry.addSpawn(EntityGlob.class, 1, 1, 1, EnumCreatureType.creature, Biomes.bog.get(), Biomes.deadSwamp.get(), Biomes.fen.get(), Biomes.moor.get(), Biomes.quagmire.get(), Biomes.sludgepit.get(), Biomes.swamplandNew.get());
		}

		if (Biomes.garden.isPresent())
		{
			EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, Biomes.garden.get());
		}

		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.JungleSpider.name", "en_US", "Jungle Spider");
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.Rosester.name", "en_US", "Rosester");
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.Glob.name", "en_US", "Glob");
	}
}
