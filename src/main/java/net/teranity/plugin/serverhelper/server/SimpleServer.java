package net.teranity.plugin.serverhelper.server;

import java.util.List;

public class SimpleServer {
    private String serverName;
    private List<SimpleServerUser> users;

    public SimpleServer(String serverName, List<SimpleServerUser> users) {
        this.serverName = serverName;
        this.users = users;
    }

    public String getServerName() {
        return serverName;
    }

    public List<SimpleServerUser> getUsers() {
        return users;
    }
}
