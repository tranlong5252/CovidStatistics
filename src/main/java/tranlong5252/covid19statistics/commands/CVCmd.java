package tranlong5252.covid19statistics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.JSONArray;
import org.json.JSONObject;
import tranlong5252.covid19statistics.API.CvAPI;
import tranlong5252.covid19statistics.CovidStatistic;

import java.io.IOException;

public class CVCmd implements CommandExecutor {

    final CovidStatistic plugin;

    public CVCmd(CovidStatistic plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            StringBuilder locBuilder = new StringBuilder();
            String str;
            for (String s : args) {
                locBuilder.append(s).append(" ");
            }
            try {
                str = new CvAPI().getAPI();
                JSONObject obj = new JSONObject(str);
                JSONArray locations = obj.getJSONArray("locations");
                String cases ="", caseToday="", death="", recovered="", treating="", name="";
                String locStr = locBuilder.toString().trim();
                for (var loc : locations) {
                    JSONObject locObj = new JSONObject(String.valueOf(loc));
                    name = String.valueOf(locObj.get("name"));
                    if (!name.equalsIgnoreCase(locStr)) continue;
                    cases = String.valueOf(locObj.get("cases"));
                    caseToday = String.valueOf(locObj.get("casesToday"));
                    death = String.valueOf(locObj.get("death"));
                    recovered = String.valueOf(locObj.get("recovered"));
                    treating = String.valueOf(locObj.get("treating"));

                    caseToday = (caseToday.charAt(0) != '-') ? "+" + caseToday : caseToday;

                    sender.sendMessage(CovidStatistic.prefix + "Thông tin dịch Covid tại §a" + name);
                    sender.sendMessage("§cLây nhiễm: " + cases + " §f(§7" + caseToday + "§f)");
                    sender.sendMessage("§7Tử vong: " + death);
                    sender.sendMessage("§aKhỏi: " + recovered);
                    sender.sendMessage("§eĐang điều trị: " + treating);
                    return true;
                }
                sender.sendMessage("§cKhông tìm thấy tỉnh/thành nào tên§e " + locStr +
                        "§c. Hãy đảm bảo nhập đúng tên tỉnh/thành");
                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (args.length == 0) {
            sender.sendMessage(CovidStatistic.prefix + "§aSử dụng: /covid <tên tỉnh/thành>");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("covid19.admin")) {
            plugin.reload();
            sender.sendMessage(CovidStatistic.prefix + "§aĐã reload thành công");
        }
        return true;
    }
}
