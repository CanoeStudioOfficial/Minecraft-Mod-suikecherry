package suike.suikecherry.proxy;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import suike.suikecherry.SuiKe;
import suike.suikecherry.client.gui.GuiHandler;
import suike.suikecherry.packet.PacketHandler;
import suike.suikecherry.tileentity.*;

public class CommonProxy {
    public void registerItemRenderer(Item item) {}

    public void register() {
        PacketHandler.registerServerPackets();

        registerGui();

        registerTileEntity(HasBackSideSignTileEntity.class, "has_back_side_sign");
        registerTileEntity(HangingSignTileEntity.class, "hanging_sign");
        registerTileEntity(BrushableTileEntity.class, "brushable_blcok");
        registerTileEntity(DecoratedPotTileEntity.class, "decorated_pot");
        registerTileEntity(ChiseledBookShelfTileEntity.class, "chiseled_bookshelf");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name) {
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(SuiKe.MODID, name));
    }

    public void registerGui() {
        NetworkRegistry.INSTANCE.registerGuiHandler(SuiKe.instance, new GuiHandler());
    }

    public void addRegisterTextures() {}
}