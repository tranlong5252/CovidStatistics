package tranlong5252.covid19statistics;

import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import tranlong5252.covid19statistics.Config.Data;
import tranlong5252.covid19statistics.Tasks.CVTasks;
import tranlong5252.covid19statistics.Tasks.NewDataTasks;
import tranlong5252.covid19statistics.commands.CVCmd;

import java.util.List;

public final class CovidStatistic extends JavaPlugin {
    public static final String prefix = "§f[§eCovid Statistic§f]§r ";
    static CovidStatistic main;
    public long delay;
    List<String> WStats, VNStats;
    boolean enable_sound;
    String broadcast_sound;
    Data data = new Data(this);

    public static CovidStatistic getMain() {
    return main;
    }

    @Override
    public void onEnable() {
        loadConfig();
        saveDefaultConfig();
        main = this;
        new CVTasks().runTaskTimerAsynchronously(this, 20, 20 * delay);
        new NewDataTasks(this).runTaskTimerAsynchronously(this, 20, 20 * delay);
        getCommand("covid").setExecutor(new CVCmd(this));
        data.createDataFile();
        data.setData();
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        WStats = getConfig().getStringList("WStats");
        VNStats = getConfig().getStringList("VNStats");
        delay = getConfig().getLong("delay");
        enable_sound = getConfig().getBoolean("sound.enable");
        broadcast_sound = getConfig().getString("sound.sound");
        saveConfig();
    }

    public void reload() {
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
}
