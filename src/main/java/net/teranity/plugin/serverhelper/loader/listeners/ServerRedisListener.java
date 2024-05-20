package net.teranity.plugin.serverhelper.loader.listeners;

import cz.foresttech.forestredis.spigot.events.RedisMessageReceivedEvent;
import net.teranity.plugin.serverhelper.ServerHelper;
import net.teranity.plugin.serverhelper.loader.ServerReceived;
import net.teranity.plugin.serverhelper.loader.ServerRequest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerRedisListener implements Listener {
    private ServerHelper serverHelper = ServerHelper.getInstance();

    @EventHandler
    public void onReceived(RedisMessageReceivedEvent e) {
        if (e.getChannel().equals("slr")) {
            ServerRequest serverRequest = e.getMessageObject(ServerRequest.class);

            String from = serverRequest.getFromServer();
            if (from.equals(serverHelper.getRedisManager().getServerIdentifier())) return;

            serverHelper.getRedisManager().publishObject("srns", new ServerReceived(from, serverHelper.getRedisManager().getServerIdentifier()));
        } else if (e.getChannel().equals("srns")) {
            ServerReceived serverReceived = e.getMessageObject(ServerReceived.class);

            if (serverReceived.getToServer().equals(serverHelper.getRedisManager().getServerIdentifier())) {
                String serverName = serverReceived.getServerName();

                if (serverHelper.getServers().contains(serverName)) return;

                serverHelper.getServers().add(serverName);
                serverHelper.getLogger().info("Server: " + serverName + " is loaded.");

                serverHelper.getRedisManager().publishObject("srns", new ServerReceived(serverName, serverHelper.getRedisManager().getServerIdentifier()));
            }
        }
    }
}
