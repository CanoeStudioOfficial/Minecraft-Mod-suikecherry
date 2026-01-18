package suike.suikecherry.config.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import suike.suikecherry.SuiKe;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), SuiKe.MODID, false, false, GuiConfig.getAbridgedConfigPath("Cherry Config"));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<>();
        // 添加普通配置页面
        elements.add(new DummyConfigElement.DummyCategoryElement(I18n.format("suikecherry.config.general"), "general", 
            ConfigGuiRead.getConfigElements()));
        // 添加需要重启的配置页面
        elements.add(new DummyConfigElement.DummyCategoryElement(I18n.format("suikecherry.config.restart"), "restart", 
            ConfigGuiRead.getRestartRequiredElements()));
        return elements;
    }

    @Override public void onGuiClosed() {}
}