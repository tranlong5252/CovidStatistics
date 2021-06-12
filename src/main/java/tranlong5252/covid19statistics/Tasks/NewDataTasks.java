package tranlong5252.covid19statistics.Tasks;

import org.bukkit.scheduler.BukkitRunnable;
import tranlong5252.covid19statistics.API.CvAPI;
import tranlong5252.covid19statistics.Config.Data;
import tranlong5252.covid19statistics.CovidStatistic;

public class NewDataTasks extends BukkitRunnable {
    CovidStatistic plugin;
    CvAPI api = new CvAPI();
    public NewDataTasks(CovidStatistic plugin){
        this.plugin = plugin;
    }

    long    VNCases, VNRecovered, VNDeaths,
            WCases, WRecovered, WDeaths,
            NVNCases, NVNRecovered, NVNDeaths,
            NWCases, NWRecovered, NWDeaths,
            CVNCases = 0, CVNRecovered = 0, CVNDeaths = 0,
            CWCases = 0, CWRecovered = 0, CWDeaths = 0;
    String VCStr,VDStr,VRStr,WCStr,WDStr,WRStr;

    @Override
    public void run() {
        try {
            NVNCases = api.getMap().get("VNCases");
            NVNDeaths = api.getMap().get("VNDeaths");
            VNRecovered = api.getMap().get("VNRecovered");
            NWCases = api.getMap().get("WCases");
            NWDeaths = api.getMap().get("WDeaths");
            NWRecovered = api.getMap().get("WRecovered");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Data data = new Data(plugin);
        VNCases = data.getDataFile().getLong("VNCases");
        VNDeaths = data.getDataFile().getLong("VNDeaths");
        //data.getDataFile().getLong("VNRecovering");
        VNRecovered = data.getDataFile().getLong("VNRecovered");
        WCases = data.getDataFile().getLong("WCases");
        WDeaths = data.getDataFile().getLong("WDeaths");
        //data.getDataFile().getLong("WRecovering");
        WRecovered =data.getDataFile().getLong("WRecovered");

        if(VNCases!=NVNCases||VNDeaths!=NVNDeaths||VNRecovered!=NVNRecovered||WCases!=NWCases||
                WRecovered!=NWRecovered||WDeaths!=NWDeaths) {
            CVNCases = NVNCases - VNCases;
            CVNRecovered = NVNRecovered - VNRecovered;
            CVNDeaths = NVNDeaths - VNDeaths;
            CWCases = NWCases - WCases;
            CWRecovered = NVNRecovered- WRecovered;
            CWDeaths = NWDeaths - WDeaths;

            if (CVNCases<0){
                VCStr = String.format("&a%s",CVNCases);
            }
            VCStr = String.format("&c%s",CVNCases);
            if (CVNRecovered<0){
                VRStr = String.format("&c%s",CVNRecovered);
            }
            VRStr = String.format("&a%s",CVNRecovered);
            if (CVNDeaths<0){
                VDStr = String.format("&a%s",CVNDeaths);
            }
            VDStr = String.format("&c%s",CVNDeaths);
            if (CWCases<0){
                WCStr = String.format("&a%s",CWCases);
            }
            WCStr = String.format("&c%s",CWCases);
            if (CWRecovered<0){
                WRStr = String.format("&c%s",CWRecovered);
            }
            WRStr = String.format("&a%s",CWRecovered);
            if (CWDeaths<0){
                WDStr = String.format("&a%s",CWDeaths);
            }
            WDStr = String.format("&c%s",CWDeaths);
        }
    }


    public String getVCStr(){
        return VCStr;
    }
    public String getVDStr(){
        return VDStr;
    }
    public String getVRStr(){
        return VRStr;
    }
    public String getWCStr(){
        return WCStr;
    }
    public String getWDStr(){
        return WDStr;
    }
    public String getWRStr(){
        return WRStr;
    }
}
