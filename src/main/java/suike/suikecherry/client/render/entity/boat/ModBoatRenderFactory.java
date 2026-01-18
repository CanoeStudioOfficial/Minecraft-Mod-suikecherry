package suike.suikecherry.client.render.entity.boat;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import suike.suikecherry.entity.boat.ModEntityBoat;

public class ModBoatRenderFactory implements IRenderFactory<ModEntityBoat> {
    @Override
    public Render<? super ModEntityBoat> createRenderFor(RenderManager manager) {
        return new ModBoatRender(manager);
    }
}