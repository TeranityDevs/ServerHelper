package net.teranity.plugin.serverhelper.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class SimpleCommand extends Command {

    public SimpleCommand(String name, String... aliases) {
        super(name);

        if (aliases != null || aliases.length > 0) {
            setAliases(Arrays.stream(aliases).collect(Collectors.toList()));
        }

        try {
            Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);

            CommandMap commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
            commandMap.register("ServerHelper", this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        execute(commandSender, strings);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);
}
