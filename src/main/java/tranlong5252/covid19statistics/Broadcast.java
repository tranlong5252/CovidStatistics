package tranlong5252.covid19statistics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import tranlong5252.covid19statistics.API.CvAPI;

import java.util.ArrayList;

public class Broadcast {
    CovidStatistic plugin = CovidStatistic.getMain();
    ArrayList<String> WMsg = new ArrayList<>();
    ArrayList<String> VNMsg = new ArrayList<>();
    CvAPI api;
    public Broadcast(CvAPI api){
        this.api = api;
    }

    public void setVNMessage() {
        if (!VNMsg.isEmpty()) VNMsg.clear();
        for (String s : plugin.getVNMessages()) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            s = s.replace("{date_time}", api.getTimeNow())
                    .replace("{VNCases}", api.getVNCases())
                    .replace("{VNDeaths}", api.getVNDeaths())
                    .replace("{VNRecovered}", api.getVNRecovered())
                    .replace("{VNRecovering}", api.getVNRecovering())
                    .replace("{todayVNCases}", api.getTodayVNCases())
                    .replace("{todayVNDeaths}", api.getTodayVNDeaths())
                    .replace("{todayVNRecovered}", api.getTodayVNRecovered())
                    .replace("{todayVNRecovering}", api.getTodayVNRecovering());
            VNMsg.add(s);
        }
    }

    public void setWorldMessage() {
        if (!WMsg.isEmpty()) WMsg.clear();
        for (String s : plugin.getWorldMessages()) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            s = s.replace("{date_time}", api.getTimeNow())
                    .replace("{WCases}", api.getWCases())
                    .replace("{WDeaths}", api.getWDeaths())
                    .replace("{WRecovered}", api.getWRecovered())
                    .replace("{WRecovering}", api.getWRecovering())
                    .replace("{todayWCases}", api.getTodayWCases())
                    .replace("{todayWDeaths}", api.getTodayWDeaths())
                    .replace("{todayWRecovered}", api.getTodayWRecovered())
                    .replace("{todayWRecovering}", api.getTodayWRecovering());

            WMsg.add(s);
        }
    }

    public void broadcast() {
        setVNMessage();
        setWorldMessage();
        Bukkit.getScheduler().runTask(plugin, () -> {
            VNMsg.forEach(Bukkit::broadcastMessage);
            WMsg.forEach(Bukkit::broadcastMessage);
            if (plugin.isSoundEnable()) {
                Bukkit.getOnlinePlayers().forEach(
                        (p) -> p.playSound(p.getLocation(), plugin.getSound(), 1, 1));
        }});
    }
}
