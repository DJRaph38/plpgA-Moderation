package fr.assaut.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModKick implements CommandExecutor {

	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /mod <OPT> !";
	public String HELP_MESSAGE_KICK = ChatColor.RED+"Erreur, tentez /mod kick <Pseudo> <Raison> !";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté, tentez via le backend !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		Player p = (Player) sender;
		// /mod kick <PLAYER> <MOTIF> <AUTRES>
		//              0        1       2,3,4,5
		if(p.hasPermission("moderation.access")){
			if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
				if(args.length >= 2){
					Player target = Bukkit.getPlayer(args[0]);
										
					if(target == null){
						p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"Joueur "+target.getName()+" kick pour "+args[1]);
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("moderation.access")){
								pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a kick "+target.getName()+" pour "+args[1]);
							}
						}
						target.kickPlayer(ChatColor.RED+args[1]);
					}
				}else{
					p.sendMessage(ChatColor.RED+HELP_MESSAGE_KICK);
				}
			}else{
				p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
			}//KICK
		}else{
			p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
		}
		
		return false;
	}

}
