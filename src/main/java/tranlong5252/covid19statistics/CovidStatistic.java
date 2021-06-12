package tranlong5252.covid19statistics;

import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import tranlong5252.covid19statistics.API.CvAPI;
import tranlong5252.covid19statistics.commands.CVCmd;

import java.util.List;

public final class CovidStatistic extends JavaPlugin {
    static CovidStatistic main;
    public static final String prefix = "§f[§eCovid Statistic§f]§r ";
    List<String> WStats, VNStats;
    boolean enable_sound;
    String broadcast_sound;
    CvAPI stats;
    public long delay;
    @Override
    public void onEnable() {
       loadConfig();
       saveDefaultConfig();
       main = this;
       stats = new CvAPI();
       new CVTasks(stats).runTaskTimerAsynchronously(this, 20, 20 * delay);
       getCommand("covid").setExecutor(new CVCmd(this));
   }

    public void loadConfig() {
       WStats = getConfig().getStringList("WStats");
       VNStats = getConfig().getStringList("VNStats");
       delay = getConfig().getLong("delay");
       enable_sound = getConfig().getBoolean("sound.enable");
       broadcast_sound = getConfig().getString("sound.sound");
    }
    public void reload(){
       reloadConfig();
       loadConfig();
    }

    public List<String> getWorldMessages() {
       return WStats;
    }
    public List<String> getVNMessages() {
       return VNStats;
    }
    public boolean isSoundEnable() {
       return enable_sound;
    }
    public Sound getSound() {
       return Sound.valueOf(broadcast_sound);
    }
    public static CovidStatistic getMain() {
       return main;
    }
}
