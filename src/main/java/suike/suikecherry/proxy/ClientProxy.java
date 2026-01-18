package suike.suikecherry.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import suike.suikecherry.client.render.armor.ArmorTrimRender;
import suike.suikecherry.client.render.tileentity.*;
import suike.suikecherry.packet.PacketHandler;
import suike.suikecherry.particle.CherryParticle;
import suike.suikecherry.particle.ModParticle;
import suike.suikecherry.tileentity.*;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemRenderer(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void register() {
        new ArmorTrimRender();
        PacketHandler.registerClientPackets();

        registerGui();
        registerParticle();

        registerTileEntityRender(HasBackSideSignTileEntity.class, new HasBackSideSignTileEntityRender(), "has_back_side_sign");
        registerTileEntityRender(HangingSignTileEntity.class, new HangingSignTileEntityRender(), "hanging_sign");
        registerTileEntityRender(BrushableTileEntity.class, new BrushableTileEntityRender(), "brushable_blcok");
        registerTileEntityRender(DecoratedPotTileEntity.class, new DecoratedPotTileEntityRender(), "decorated_pot");
        registerTileEntityRender(ChiseledBookShelfTileEntity.class, new ChiseledBookShelfTileEntityRender(), "chiseled_bookshelf");
    }

    @SideOnly(Side.CLIENT)
    public void registerTileEntityRender(Class<? extends TileEntity> tileEntityClass, TileEntityRender tileEntityRender, String name) {
        super.registerTileEntity(tileEntityClass, name);
        if (tileEntityRender != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, tileEntityRender);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerParticle() {
        Minecraft MC = Minecraft.getMinecraft();
        MC.effectRenderer.registerParticle(ModParticle.CHERRY_PARTICLE_ID, new CherryParticle.CherryParticleFactory());
    }

// 材质
    @Override
    @SideOnly(Side.CLIENT)
    public void addRegisterTextures() {
        ModParticle.registerParticleTexture();
    }

    @SideOnly(Side.CLIENT)
    private static List<ResourceLocation> TEXTURES = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    public static ResourceLocation addRegisterTextures(ResourceLocation textures) {
        TEXTURES.add(textures);
        return textures;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        for (ResourceLocation textures : TEXTURES) {
            event.getMap().registerSprite(textures);
        }
    }
}