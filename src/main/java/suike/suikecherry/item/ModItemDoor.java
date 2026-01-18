package suike.suikecherry.item;

import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import suike.suikecherry.SuiKe;
import suike.suikecherry.block.ModBlockDoor;

//门物品
public class ModItemDoor extends ItemDoor {
    public ModItemDoor(String name, ModBlockDoor block) {
        super(block);
        /*设置物品名*/this.setRegistryName(name);
        /*设置物品名key*/this.setTranslationKey(SuiKe.MODID + "." + name);
        block.setItem(this);

        /*添加到ITEMS列表*/ItemBase.ITEMS.add(this);
    }

//燃料功能
    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 200; //返回燃烧时间（单位：tick）
    }
}