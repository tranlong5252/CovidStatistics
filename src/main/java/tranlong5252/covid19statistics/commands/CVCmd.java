package tranlong5252.covid19statistics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tranlong5252.covid19statistics.CovidStatistic;

public class CVCmd implements CommandExecutor {

    final CovidStatistic plugin;
    public CVCmd(CovidStatistic plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("covid")) {
            if (!sender.hasPermission("covid19.admin")) {
                sender.sendMessage(CovidStatistic.prefix + "§cBạn không có quyền sử dụng lệnh này");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(CovidStatistic.prefix + "§aSử dụng: /covid19 reload");
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.reload();
                sender.sendMessage(CovidStatistic.prefix + "§aĐã reload thành công");
            }
        }
        return true;
    }
}
