package tranlong5252.covid19statistics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import tranlong5252.covid19statistics.API.CvAPI;
import tranlong5252.covid19statistics.Tasks.NewDataTasks;

import java.util.ArrayList;

public class Broadcast {
    CovidStatistic plugin = CovidStatistic.getMain();
    ArrayList<String> WMsg = new ArrayList<>();
    ArrayList<String> VNMsg = new ArrayList<>();
    CvAPI api;
    NewDataTasks nData = new NewDataTasks(plugin);
    public Broadcast(CvAPI api){
        this.api = api;
    }

    public void setVNMessage() {
        if (!VNMsg.isEmpty()) {
            VNMsg.clear();
        }
        for (String s : plugin.getVNMessages()) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            s = s.replace("{date_time}", api.getTimeNow())
                    .replace("{VNCases}", api.getVNCases())
                    .replace("{VNDeaths}", api.getVNDeaths())
                    .replace("{VNRecovered}", api.getVNRecovered())
                    .replace("{VNRecovering}", api.getVNRecovering())
                    .replace("{CVNCases}", nData.getVCStr())
                    .replace("{CVNDeaths}", nData.getVDStr())
                    .replace("{CVNRecovered}", nData.getVRStr());
            VNMsg.add(s);
        }
    }

    public void setWorldMessage() {
        if (!WMsg.isEmpty()) {
            WMsg.clear();
        }
        for (String s : plugin.getWorldMessages()) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            s = s.replace("{date_time}", api.getTimeNow())
                    .replace("{WCases}", api.getWCases())
                    .replace("{WDeaths}", api.getWDeaths())
                    .replace("{WRecovered}", api.getWRecovered())
                    .replace("{WRecovering}", api.getWRecovering())
                    .replace("{CWCases}", nData.getWCStr())
                    .replace("{CWDeaths}", nData.getWDStr())
                    .replace("{CWRecovered}", nData.getWRStr());
            WMsg.add(s);
        }
    }

    public void broadcast() {
        setVNMessage();
        setWorldMessage();

        new BukkitRunnable() {
            @Override
            public void run() {
                VNMsg.forEach(Bukkit::broadcastMessage);
                WMsg.forEach(Bukkit::broadcastMessage);
                if (plugin.isSoundEnable()) {
                    Bukkit.getOnlinePlayers().forEach((p) -> p.playSound(p.getLocation(), plugin.getSound(), 1, 1));
                }
            }
        }.runTask(plugin);
    }
}
