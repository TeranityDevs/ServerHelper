package net.teranity.plugin.serverhelper.commands;

import net.teranity.plugin.serverhelper.ServerHelper;
import net.teranity.plugin.serverhelper.util.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class ServerListCommand extends SimpleCommand {
    private ServerHelper serverHelper = ServerHelper.getInstance();

    public ServerListCommand() {
        super("serverlist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("serverhelper.permissions") || (sender instanceof ConsoleCommandSender)) {
            if (serverHelper.getServers().isEmpty()) {
                sender.sendMessage(ChatUtil.sendColor("&cNo servers registered."));
                return;
            }

            sender.sendMessage(ChatUtil.sendColor("&aServer Registered List: &7(" + serverHelper.getServers().size() + ")"));
            for (String str : serverHelper.getServers()) {
                sender.sendMessage(ChatUtil.sendColor("&7- &f" + str));
            }
        } else {
            sender.sendMessage(ChatUtil.sendColor("&cYou don't have required permission to do this action."));
            return;
        }
    }
}
