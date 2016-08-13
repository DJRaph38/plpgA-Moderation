package fr.assaut.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModBan implements CommandExecutor {

	
	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /mod <OPT> !";
	public String HELP_MESSAGE_BAN = ChatColor.RED+"Erreur, tentez /mod ban <Pseudo> <Raison> <Durée> !";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté, tentez via le backend !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// /mod ban <PSEUDO> <MOTIF> <DUREE>
		//             0        1       2
		Player p = (Player) sender;
		if(p.hasPermission("moderation.access")){
			if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
				if(args.length == 3){
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null){
						p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
						//target.setBanned(true);
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"Joueur "+target.getName()+" banni pour "+args[1]+" pendant "+args[2]+"j");
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("moderation.access")){
								pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a banni "+target.getName()+" pour "+args[1]+" pendant "+args[2]+"j");
							}
						}
						//target.setBanned(true);
					 
						// CONTINUER
					 
					}
				}else{
					p.sendMessage(ChatColor.RED+HELP_MESSAGE_BAN);
				}
			}else{
				p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
			}//BAN
		}else{
			p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
		}
		
		return false;
	}

}
