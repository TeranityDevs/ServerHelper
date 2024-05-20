package net.teranity.plugin.serverhelper.loader;

public class ServerReceived {
    private String toServer;
    private String serverName;

    public ServerReceived(String toServer, String serverName) {
        this.toServer = toServer;
        this.serverName = serverName;
    }

    public String getToServer() {
        return toServer;
    }

    public String getServerName() {
        return serverName;
    }
}
