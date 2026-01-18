package suike.suikecherry.config;

import suike.suikecherry.data.TreasureData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasureListRead {
    private static Map<String, List<TreasureData>> treasureMap = new HashMap<>();

    public static void config() {
        File configFile = new File(Config.config, "config/sui_ke/cherry/treasure.cfg");

        if (!configFile.exists()) return;

        try {
            String content = new String(Files.readAllBytes(configFile.toPath()));
            String currentSection = null;

            for (String line : content.split("\n")) {
                line = line.trim();

                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.substring(1, line.length() - 1);
                    continue;
                }

                if (currentSection == null || line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                try {
                    String[] parts = line.split(",");
                    if (parts.length != 3) continue;

                    String itemId = parts[0].trim();
                    int meta = Integer.parseInt(parts[1].trim());
                    float chance = Float.parseFloat(parts[2].trim());

                    TreasureData data = new TreasureData(itemId, meta, chance);
                    treasureMap.computeIfAbsent(currentSection, k -> new ArrayList<>()).add(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TreasureData> getTreasureList(String structureName) {
        List<TreasureData> list = treasureMap.get(structureName);
        if (list != null) {
            treasureMap.remove(structureName); // 移除已获取的列表
            return list; // 返回列表
        }
        return new ArrayList<>(); // 列表不存在
    }
}