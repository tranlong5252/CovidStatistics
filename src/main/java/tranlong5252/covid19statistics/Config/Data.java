package tranlong5252.covid19statistics.Config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import tranlong5252.covid19statistics.API.CvAPI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class Data {
    private final File file;
    private final Plugin plugin;

    long VNCases, VNRecovering, VNRecovered, VNDeaths,
            WCases, WRecovering, WRecovered, WDeaths;
    private FileConfiguration DataConfig;

    public Data(Plugin plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "data.yml");
        DataConfig = YamlConfiguration.loadConfiguration(file);
    }

    public void setData() {
        CvAPI api = new CvAPI();
        try {
            TreeMap<String, Long> data= api.getMap();
            VNCases = data.get("VNCases");
            VNDeaths = data.get("VNDeaths");
            VNRecovering = data.get("VNRecovering");
            VNRecovered = data.get("VNRecovered");
            WCases = data.get("WCases");
            WDeaths = data.get("WDeaths");
            WRecovering = data.get("WRecovering");
            WRecovered = data.get("WRecovered");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            writer.write("#Vui lòng không chỉnh sửa ở file này!" + "\n");
            writer.write("\n");
            writer.write("#DataVN:" + "\n");
            writer.write("VNCases: " + VNCases + "\n");
            writer.write("VNDeaths: " + VNDeaths + "\n");
            writer.write("VNRecovering: " + VNRecovering + "\n");
            writer.write("VNRecovered: " + VNRecovered + "\n");
            writer.write("\n");
            writer.write("#DataWorld:" + "\n");
            writer.write("WCases: " + WCases + "\n");
            writer.write("WDeaths: " + WDeaths + "\n");
            writer.write("WRecovering: " + WRecovering + "\n");
            writer.write("WRecovered: " + WRecovered + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getDataFile() {
        return this.DataConfig;
    }

    public void createDataFile() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }

        DataConfig = new YamlConfiguration();
        try {
            DataConfig.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}