package suike.suikecherry.recipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import suike.suikecherry.oredict.OreDict;

@Mod.EventBusSubscriber
public class RecipeEvent {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        OreDict.oreDict();
        SignRecipe.register();
        FurnaceRecipe.register();
        BoatRecipe.register(event);
        SmithingTemplateRecipe.register(event);
        CraftRecipe.register();
    }
}