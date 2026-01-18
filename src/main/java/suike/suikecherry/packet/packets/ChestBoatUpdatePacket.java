package suike.suikecherry.packet.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import suike.suikecherry.entity.boat.ModEntityChestBoat;

public class ChestBoatUpdatePacket implements IMessage {
    private int entityID;
    private ItemStack stack = ItemStack.EMPTY;

    public ChestBoatUpdatePacket() {}

    public ChestBoatUpdatePacket(int entityID, ItemStack stack) {
        this.entityID = entityID;
        this.stack = stack;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
        ByteBufUtils.writeItemStack(buf, stack);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = buf.readInt();
        stack = ByteBufUtils.readItemStack(buf);
    }

    // 客户端处理：更新本地数据
    @SideOnly(Side.CLIENT)
    public static class ClientHandler implements IMessageHandler<ChestBoatUpdatePacket, IMessage> {
        @Override
        public IMessage onMessage(ChestBoatUpdatePacket message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                World world = Minecraft.getMinecraft().world;
                Entity entity = world.getEntityByID(message.entityID);
                if (entity instanceof ModEntityChestBoat) {
                    ((ModEntityChestBoat) entity).chestInventory.updateChestVisuals(message.stack);
                }
            });
            return null;
        }
    }

    // 服务端处理：验证并保存数据
    public static class ServerHandler implements IMessageHandler<ChestBoatUpdatePacket, IMessage> {
        @Override
        public IMessage onMessage(ChestBoatUpdatePacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            World world = player.world;

            ((WorldServer) world).addScheduledTask(() -> {
                Entity entity = world.getEntityByID(message.entityID);
                if (entity instanceof ModEntityChestBoat) {
                    ((ModEntityChestBoat) entity).chestInventory.validateAndUpdateContents(message.stack);
                }
            });
            return null;
        }
    }
}