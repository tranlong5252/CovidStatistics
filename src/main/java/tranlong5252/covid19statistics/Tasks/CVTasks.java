package tranlong5252.covid19statistics.Tasks;

import org.bukkit.scheduler.BukkitRunnable;
import tranlong5252.covid19statistics.API.CvAPI;
import tranlong5252.covid19statistics.Broadcast;

public class CVTasks extends BukkitRunnable {
    CvAPI api = new CvAPI();
    Broadcast bc = new Broadcast(api);

    @Override
    public void run() {

        try {
            api.GetStatistics();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bc.broadcast();
    }
}
