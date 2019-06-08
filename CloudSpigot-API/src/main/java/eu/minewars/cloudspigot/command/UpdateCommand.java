package eu.minewars.cloudspigot.command;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import eu.minewars.cloudspigot.updater.Updater;

public class UpdateCommand extends BukkitCommand {

	public UpdateCommand(String name) {
		super(name);

		this.description = "Update CloudSpigot";
		this.usageMessage = "/update";
		this.setPermission("cloudspigot.command.update");
		this.setAliases(Arrays.asList("updater"));
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!testPermission(sender)) {
			return true;
		}

		sender.sendMessage("§9Checking for updates, please wait..");
		sender.sendMessage(Updater.update());

		return true;
	}

}