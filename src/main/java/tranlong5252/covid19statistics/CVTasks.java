package tranlong5252.covid19statistics;

import org.bukkit.scheduler.BukkitRunnable;
import tranlong5252.covid19statistics.API.CvAPI;

public class CVTasks extends BukkitRunnable {
    final CvAPI api;

    public CVTasks(CvAPI api) {
        this.api = api;
    }

    @Override
    public void run() {
        try {
            api.broadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
