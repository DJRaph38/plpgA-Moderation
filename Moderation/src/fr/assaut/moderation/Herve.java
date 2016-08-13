package fr.assaut.moderation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Herve implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		p.sendMessage(ChatColor.BOLD+"Non mais vraiment ! Prends cette calotte petit étudiant de BASE !");
		return false;
	}

}
