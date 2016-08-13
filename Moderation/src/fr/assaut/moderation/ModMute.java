package fr.assaut.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModMute implements CommandExecutor {

	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /mod <OPT> !";
	public String HELP_MESSAGE_MUTE = ChatColor.RED+"Erreur, tentez /mod mute <Pseudo> <Raison> <Durée> !";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté, tentez via le backend !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		// /mod mute <PSEUDO> <RAISON> <DUREE>
		//              0        1        2
		if(p.hasPermission("moderation.access")){			
			if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
				if(args.length == 3){
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null){
						p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"Vous avez donné le silence chez "+args[0]+" pour "+args[1]+" pendant "+args[2]+"s");
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("moderation.access")){
								pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a réduit au silence "+target.getName()+" pour "+args[1]+" pendant "+args[2]+"s");
							}
						}
						target.sendMessage(ChatColor.RED+"Vous ne pouvez plus parler pendant "+args[2]+"s ! motif : "+args[1]);
						//target.mutePlayer(args[2]);
						 
						 // FINIR LE MUTE
						 
					}
				}else{
					p.sendMessage(ChatColor.DARK_PURPLE+HELP_MESSAGE_MUTE);
				}//MUTE
			}else{
				p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
			}
		}else{
			p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
		}
	return false;
	}

}
