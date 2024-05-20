package net.teranity.plugin.serverhelper;

import cz.foresttech.forestredis.shared.RedisManager;
import net.teranity.plugin.serverhelper.commands.ServerListCommand;
import net.teranity.plugin.serverhelper.loader.ServerReceived;
import net.teranity.plugin.serverhelper.loader.ServerRequest;
import net.teranity.plugin.serverhelper.loader.listeners.ServerRedisListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ServerHelper extends JavaPlugin {

    private static ServerHelper instance;

    public static ServerHelper getInstance() {
        return instance;
    }

    private RedisManager redisManager;
    private String currentServer;
    private List<String> servers = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        redisManager = RedisManager.getAPI();
        if (redisManager == null) {
            getServer().shutdown();
        }

        // slr = Server Load Request, srns = Server Received Name Server
        String[] channels = {"slr", "srns"};
        redisManager.subscribe(channels);

        currentServer = redisManager.getServerIdentifier();

        getServer().getPluginManager().registerEvents(new ServerRedisListener(), this);

        getLogger().info("Loading for servers name...");
        servers.add(currentServer);
        redisManager.publishObject(channels[0], new ServerRequest(redisManager.getServerIdentifier()));

        for (String server : servers) {
            redisManager.publishObject("srns", new ServerReceived(server, redisManager.getServerIdentifier()));
        }

        new ServerListCommand();
    }

    @Override
    public void onDisable() {
        redisManager.close();
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public String getCurrentServer() {
        return currentServer;
    }

    public List<String> getServers() {
        return servers;
    }
}
 