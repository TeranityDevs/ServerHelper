package net.teranity.plugin.serverhelper;

import cz.foresttech.forestredis.shared.RedisManager;

import java.util.List;

public class HelperAPI {
    private static HelperAPI API;

    private RedisManager redisManager;
    private List<String> servers;
    private String currentServer;

    public HelperAPI(RedisManager redisManager, List<String> servers, String currentServer) {
        this.redisManager = redisManager;
        this.servers = servers;
        this.currentServer = currentServer;
    }

    public static void init(RedisManager redisManager, List<String> servers, String currentServer) {
        API = new HelperAPI(redisManager, servers, currentServer);
    }

    public static HelperAPI getAPI() {
        return API;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public List<String> getServers() {
        return servers;
    }

    public String getCurrentServer() {
        return currentServer;
    }
}
