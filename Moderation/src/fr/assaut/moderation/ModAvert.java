package fr.assaut.moderation;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModAvert implements CommandExecutor{
	
	
	// MESSAGES D'ERREUR
	
	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /mod <OPT> !";
	public String HELP_MESSAGE_AVERT = ChatColor.RED+"Erreur, tentez /mod avert <Pseudo> <Raison> !";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté, tentez via le backend !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";

	
	
	 // ALGO COMMANDE
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		
		if(p.hasPermission("moderation.access")){
				 //avert <PSEUDO> <RAISON>
				 //         0        1
				if((p.hasPermission("moderation.surveillant")) || (p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
					//if(args.length == 2){	
						Player target = Bukkit.getPlayer(args[0]);
						if(target == null){
							p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
						}else{
							p.sendMessage(ChatColor.DARK_PURPLE+"Vous avez donné un avertissement à "+target.getName()+" pour "+args[1]);
							for(Player pls : Bukkit.getOnlinePlayers()){
								if(pls.hasPermission("moderation.access")){
									pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a donné un avertissement à "+target.getName()+" pour "+args[1]);
								}
							}
							target.sendMessage(ChatColor.RED+"Vous avez reçu un avertissement pour "+args[1]);
							
							//SYSTEME AVERTISSEMENT A TERMINER
							 
						}
					//}else{
						//p.sendMessage(ChatColor.DARK_PURPLE+HELP_MESSAGE_AVERT);
					//}
				}else{
					p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
				}//AVERT
		}else{
			p.sendMessage(ChatColor.RED+NO_PERMISSION_MESSAGE);
		}
		return false;
	}//COMMANDE

}
