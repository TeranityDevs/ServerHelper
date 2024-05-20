package net.teranity.plugin.serverhelper.server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SimpleServerUser {
    private UUID uuid;

    public SimpleServerUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player toPlayer() {
        return Bukkit.getPlayer(getUuid());
    }
}
