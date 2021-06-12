package tranlong5252.covid19statistics.Config;

import tranlong5252.covid19statistics.API.CvAPI;

import java.util.HashMap;
import java.util.Map;

public class Data {
    CvAPI api = new CvAPI();
    boolean isNew = false;

    private final Map<String, String> data = new HashMap<String, String>(){{
        put("VNCases",api.getVNCases());
        put("VNDeaths",api.getVNDeaths());
        put("VNRecovering",api.getVNRecovering());
        put("VNRecovered",api.getVNRecovered());
        put("WCases",api.getWCases());
        put("WDeaths",api.getWDeaths());
        put("WRecovering",api.getWRecovering());
        put("WRecovered",api.getWRecovered());
    }};

    public Map<String, String> getData() {
        return data;
    }
}