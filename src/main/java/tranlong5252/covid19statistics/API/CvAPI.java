package tranlong5252.covid19statistics.API;

import org.json.JSONObject;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CvAPI {
    private String VNCases, VNRecovering, VNRecovered, VNDeaths,
            WCases, WRecovering, WRecovered, WDeaths,
            todayVNCases, todayVNRecovering, todayVNRecovered, todayVNDeaths,
            todayWCases, todayWRecovering, todayWRecovered, todayWDeaths;

    public void GetStatistics() throws Exception {
        //Verify();
        String str = getAPI();
        JSONObject obj = new JSONObject(str);
        JSONObject total = obj.getJSONObject("total");
        JSONObject allInternal = total.getJSONObject("internal");
        setVNRecovered(String.valueOf(allInternal.get("recovered")));
        setVNCases(String.valueOf(allInternal.get("cases")));
        setVNDeaths(String.valueOf(allInternal.get("death")));
        setVNRecovering(String.valueOf(allInternal.get("treating")));

        JSONObject allWorld = total.getJSONObject("world");
        setWRecovered(String.valueOf(allWorld.get("recovered")));
        setWCases(String.valueOf(allWorld.get("cases")));
        setWDeaths(String.valueOf(allWorld.get("death")));
        setWRecovering(String.valueOf(allWorld.get("treating")));

        JSONObject today = obj.getJSONObject("today");
        JSONObject todayInternal = today.getJSONObject("internal");
        setTodayVNRecovered(String.valueOf(todayInternal.get("recovered")));
        setTodayVNCases(String.valueOf(todayInternal.get("cases")));
        setTodayVNDeaths(String.valueOf(todayInternal.get("death")));
        setTodayVNRecovering(String.valueOf(todayInternal.get("treating")));

        JSONObject todayWorld = today.getJSONObject("world");
        setTodayWRecovered(String.valueOf(todayWorld.get("recovered")));
        setTodayWCases(String.valueOf(todayWorld.get("cases")));
        setTodayWDeaths(String.valueOf(todayWorld.get("death")));
        setTodayWRecovering(String.valueOf(todayWorld.get("treating")));
    }

    public String getAPI() throws IOException {
        URL url = new URL("https://static.pipezero.com/covid/data.json");
        URLConnection con = url.openConnection();
        InputStream inputstream = con.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        return new BufferedReader(inputstreamreader).readLine();
    }

    public void Verify() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    public String getTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));
    }

    public String getWRecovering() {
        return WRecovering;
    }

    public void setWRecovering(String WRecovering) {
        this.WRecovering = WRecovering;
    }

    public String getWRecovered() {
        return WRecovered;
    }

    public void setWRecovered(String WRecovered) {
        this.WRecovered = WRecovered;
    }

    public String getWDeaths() {
        return WDeaths;
    }

    public void setWDeaths(String WDeaths) {
        this.WDeaths = WDeaths;
    }

    public String getWCases() {
        return WCases;
    }

    public void setWCases(String WCases) {
        this.WCases = WCases;
    }

    public String getVNRecovering() {
        return VNRecovering;
    }

    public void setVNRecovering(String VNRecovering) {
        this.VNRecovering = VNRecovering;
    }

    public String getVNRecovered() {
        return VNRecovered;
    }

    public void setVNRecovered(String VNRecovered) {
        this.VNRecovered = VNRecovered;
    }

    public String getVNDeaths() {
        return VNDeaths;
    }

    public void setVNDeaths(String VNDeaths) {
        this.VNDeaths = VNDeaths;
    }

    public String getVNCases() {
        return VNCases;
    }

    public void setVNCases(String VNCases) {
        this.VNCases = VNCases;
    }

    public String getTodayVNCases() {
        return todayVNCases;
    }

    public void setTodayVNCases(String todayVNCases) {
        this.todayVNCases = (todayVNCases.charAt(0) != '-') ? "+" + todayVNCases : todayVNCases;
    }

    public String getTodayVNRecovering() {
        return todayVNRecovering;
    }

    public void setTodayVNRecovering(String todayVNRecovering) {
        this.todayVNRecovering = (todayVNRecovering.charAt(0) != '-') ? "+" + todayVNRecovering : todayVNRecovering;
    }

    public String getTodayVNRecovered() {
        return todayVNRecovered;
    }

    public void setTodayVNRecovered(String todayVNRecovered) {
        this.todayVNRecovered = (todayVNRecovered.charAt(0) != '-') ? "+" + todayVNRecovered : todayVNRecovered;
    }

    public String getTodayVNDeaths() {
        return todayVNDeaths;
    }

    public void setTodayVNDeaths(String todayVNDeaths) {
        this.todayVNDeaths = (todayVNDeaths.charAt(0) != '-') ? "+" + todayVNDeaths : todayVNDeaths;
    }

    public String getTodayWCases() {
        return todayWCases;
    }

    public void setTodayWCases(String todayWCases) {
        this.todayWCases = (todayWCases.charAt(0) != '-') ? "+" + todayWCases : todayWCases;
    }

    public String getTodayWRecovering() {
        return todayWRecovering;
    }

    public void setTodayWRecovering(String todayWRecovering) {
        this.todayWRecovering = (todayWRecovering.charAt(0) != '-') ? "+" + todayWRecovering : todayWRecovering;
    }

    public String getTodayWRecovered() {
        return todayWRecovered;
    }

    public void setTodayWRecovered(String todayWRecovered) {
        this.todayWRecovered = (todayWRecovered.charAt(0) != '-') ? "+" + todayWRecovered : todayWRecovered;
    }

    public String getTodayWDeaths() {
        return todayWDeaths;
    }

    public void setTodayWDeaths(String todayWDeaths) {
        this.todayWDeaths = (todayWDeaths.charAt(0) == '+') ? "+" + todayWDeaths : todayWDeaths;
    }
}