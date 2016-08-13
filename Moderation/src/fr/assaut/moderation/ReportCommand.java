package fr.assaut.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /report <Pseudo> <Raison> !";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		/*
		 *  /report <Joueur> <Raison> <Autre argument>
		 */
		if(p.hasPermission("moderation.access")){
			if(args.length == 2){
				//@SuppressWarnings("deprecation")
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null){
					p.sendMessage(OFFLINE_TARGET_PLAYER_MESSAGE);
				}else{
					p.sendMessage(ChatColor.DARK_RED+"Vous avez signalé le joueur "+target.getName()+" pour "+args[1]);
					for(Player pls : Bukkit.getOnlinePlayers()){
						if(pls.hasPermission("moderation.access")){
							pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a signalé "+target.getName()+" pour "+args[1]);
						}
					}
				}
			}else{
				p.sendMessage(HELP_MESSAGE);
			}
		}else{
			p.sendMessage(NO_PERMISSION_MESSAGE);
		}
		return false;
	}

}
