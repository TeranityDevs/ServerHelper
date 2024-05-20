package net.teranity.plugin.serverhelper.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerLoadEvent extends Event {
    private static HandlerList handlers = new HandlerList();
    private String server;

    public ServerLoadEvent(String server) {
        this.server = server;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getServer() {
        return server;
    }
}
