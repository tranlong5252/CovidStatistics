package tranlong5252.covid19statistics.API;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tranlong5252.covid19statistics.CovidStatistic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Data {
    CvAPI API;
    CovidStatistic plugin;
    File file;
    FileConfiguration config;

    public Data(CovidStatistic plugin) {
        this.plugin = plugin;
    }

    public void reloadConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), "data.yml");
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        InputStream defStream = this.plugin.getResource("data.yml");
        if (defStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defStream));
            this.config.setDefaults(defConfig);
        }
    }
    public FileConfiguration getConfig(){
        if(this.config!=null) reloadConfig();
            return this.config;
    }
    public void saveConfig() {
        if(this.config==null||this.file==null)
            return;
        try {
            this.getConfig().save(this.file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Không thể lưu file " + this.file, e);
        }
    }
    public void saveDefaultConfig(){
        if(this.file==null) this.file = new File(this.plugin.getDataFolder(),"dat.yml");
        if(!this.file.exists())this.plugin.saveResource("data.yml",false);
    }
}