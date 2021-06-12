package tranlong5252.covid19statistics.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import tranlong5252.covid19statistics.CovidStatistic;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CvAPI {
    CovidStatistic plugin = CovidStatistic.getMain();
    ArrayList<String> WMsg = new ArrayList<>();
    ArrayList<String> VNMsg = new ArrayList<>();
    private String VNCases, VNRecovering, VNRecovered, VNDeaths,
            WCases, WRecovering, WRecovered, WDeaths;

    public void GetStatistics() throws Exception {
        Verify();
        URL url = new URL("https://ncov.moh.gov.vn");
        URLConnection con = url.openConnection();
        InputStream inputstream = con.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        Pattern p = Pattern.compile("font24\\S+((?!0*[.,]?0+$)\\d*[.,])?\\d+");
        int i = 1;
        String string;
        while ((string = bufferedreader.readLine()) != null && i <= 8) {
            Matcher m = p.matcher(string);
            if (m.find()) {
                String out = m.group().replace("font24\">", "");
                switch (i) {
                    case 1:
                        VNCases = out;
                        setVNCases(VNCases);
                    case 2:
                        VNRecovering = out;
                        setVNRecovering(VNRecovering);
                    case 3:
                        VNRecovered = out;
                        setVNRecovered(VNRecovered);
                    case 4:
                        VNDeaths = out;
                        setVNDeaths(VNDeaths);
                    case 5:
                        WCases = out;
                        setWCases(WCases);
                    case 6:
                        WRecovering = out;
                        setWRecovering(WRecovering);
                    case 7:
                        WRecovered = out;
                        setWRecovered(WRecovered);
                    case 8:
                        WDeaths = out;
                        setWDeaths(WDeaths);
                }
                i++;
            }
        }
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

    /**
     * Setter
     */
    public void setVNCases(String VNCases) {
        this.VNCases = VNCases;
    }

    public void setVNMessage() throws Exception {
        GetStatistics();
        if (!VNMsg.isEmpty()) {
            VNMsg.clear();
        }
        for (String s : plugin.getVNMessages()) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            s = s.replace("{date_time}", getTimeNow())
                    .replace("{VNCases}", getVNCases())
                    .replace("{VNDeaths}", getVNDeaths())
                    .replace("{VNRecovered}", getVNRecovered())
                    .replace("{VNRecovering}", getVNRecovering());
            VNMsg.add(s);
        }
    }

    public void setWorldMessage() {
        if (!WMsg.isEmpty()) {
            WMsg.clear();
        }
        for (String s : plugin.getWorldMessages()) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            s = s.replace("{date_time}", getTimeNow())
                    .replace("{WCases}", getWCases())
                    .replace("{WDeaths}", getWDeaths())
                    .replace("{WRecovered}", getWRecovered())
                    .replace("{WRecovering}", getWRecovering());
            WMsg.add(s);
        }
    }

    public void broadcast() throws Exception {
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

    private final Map<String, String> data = new HashMap<String, String>(){{
        put("VNCases",getVNCases());
        put("VNDeaths",getVNDeaths());
        put("VNRecovering",getVNRecovering());
        put("VNRecovered",getVNRecovered());
        put("WCases",getWCases());
        put("WDeaths",getWDeaths());
        put("WRecovering",getWRecovering());
        put("WRecovered",getWRecovered());
    }};

    public Map<String, String> getData() {
        return data;
    }
}