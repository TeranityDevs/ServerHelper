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
    private List<String> servers;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        load();

        new ServerListCommand();
    }

    @Override
    public void onDisable() {
        redisManager.close();
    }

    private void load() {
        redisManager = RedisManager.getAPI();
        if (redisManager == null) {
            getServer().shutdown();
        }

        servers = new ArrayList<>();
        currentServer = redisManager.getServerIdentifier();

        // Init API
        if (HelperAPI.getAPI() == null) {
            HelperAPI.init(redisManager, servers, currentServer);
        }

        // slr = Server Load Request, srns = Server Received Name Server
        String[] channels = {"slr", "srns"};
        redisManager.subscribe(channels);

        getServer().getPluginManager().registerEvents(new ServerRedisListener(), this);

        servers.add(currentServer);
        redisManager.publishObject(channels[0], new ServerRequest(redisManager.getServerIdentifier()));

        for (String server : servers) {
            redisManager.publishObject("srns", new ServerReceived(server, redisManager.getServerIdentifier()));
        }
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
 