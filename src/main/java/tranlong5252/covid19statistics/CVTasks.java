package tranlong5252.covid19statistics;

import org.bukkit.scheduler.BukkitRunnable;
import tranlong5252.covid19statistics.API.CvAPI;

public class CVTasks extends BukkitRunnable {
    CvAPI api;
    CovidStatistic plugin = CovidStatistic.getMain();

    public CVTasks(CvAPI api) {
        this.api = api;
    }

    @Override
    public void run() {
        try {
            api.broadcast();
            plugin.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
