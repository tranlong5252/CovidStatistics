package tranlong5252.covid19statistics;

import org.bukkit.Sound;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tranlong5252.covid19statistics.API.Data;
import tranlong5252.covid19statistics.API.CvAPI;
import tranlong5252.covid19statistics.commands.CVCmd;

import java.util.List;

public final class CovidStatistic extends JavaPlugin implements Listener {
    public static final String prefix = "§f[§eCovid Statistic§f]§r ";
    List<String> WStats;
    List<String> VNStats;
    boolean enable_sound;
    String broadcast_sound;
    static CovidStatistic main;
    Data data;

    CvAPI stats;
    public long delay;

   @Override
    public void onEnable() {
       loadConfig();
       saveDefaultConfig();
       //if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {throw new RuntimeException("Không tìm thấy PlaceholderAPI!");}
       main =this;
       stats = new CvAPI();
       this.data = new Data(this);
       new CVTasks(stats).runTaskTimerAsynchronously(this, 20, 20 * delay);
       getCommand("covid").setExecutor(new CVCmd(this));
   }

   @Override
   public void onDisable(){
       data.getConfig().getBoolean(" ");
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
