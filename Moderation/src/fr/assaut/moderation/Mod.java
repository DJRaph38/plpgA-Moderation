package fr.assaut.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mod implements CommandExecutor {

	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------
	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /mod <OPT> <ARGS> !";
	public String HELP_MESSAGE_AVERT = ChatColor.RED+"Erreur, tentez /mod avert <Pseudo> <Raison> !";
	public String HELP_MESSAGE_BAN = ChatColor.RED+"Erreur, tentez /mod ban <Pseudo> <Raison> <Durée> !";
	public String HELP_MESSAGE_KICK = ChatColor.RED+"Erreur, tentez /mod kick <Pseudo> <Raison> !";
	public String HELP_MESSAGE_MUTE = ChatColor.RED+"Erreur, tentez /mod mute <Pseudo> <Raison> <Durée> !";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté, tentez via le backend !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";
	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------
	/**
	 * /mod avert <Pseudo> <Raison>
	 *      ban   <Pseudo> <Raison> <Durée>
	 *      kick  <Pseudo> <Raison>
	 *      mute  <Pseudo> <Raison> <Durée>
	 */
	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender; 
		
		if(p.hasPermission("moderation.access")){
			
			if(args.length >= 3){
				
				if(args[0].equalsIgnoreCase("avert")){
					
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					// /mod avert <PSEUDO> <RAISON>
					//        0      1        2
					if((p.hasPermission("moderation.surveillant")) || (p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){	
						if(args.length >= 3){	
							Player target = Bukkit.getPlayer(args[1]);
							if(target == null){
								p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"Vous avez donné un avertissement à "+target.getName()+" pour "+args[2]);
								for(Player pls : Bukkit.getOnlinePlayers()){
									if(pls.hasPermission("moderation.access")){
										pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a donné un avertissement à "+target.getName()+" pour "+args[2]);
									}
								}
								//alias
								if(args[2].equalsIgnoreCase("LI")){
									target.sendMessage(ChatColor.RED+"Vous avez reçu un avertissement pour langage inapproprié");
								}else if(args[2].equalsIgnoreCase("ECT")){
									target.sendMessage(ChatColor.RED+"Vous avez reçu un avertissement pour Evocation de Cheat dans le Chat");
								}else if(args[2].equalsIgnoreCase("ACT")){
									target.sendMessage(ChatColor.RED+"Vous avez reçu un avertissement pour Accusation de Cheat dans le Chat");
								}else{
									target.sendMessage(ChatColor.RED+"Vous avez reçu un avertissement pour "+args[2]);
								}
								//-------------------------------
								//SYSTEME AVERTISSEMENT A TERMINER
								//-------------------------------
							}
						}else{
							p.sendMessage(HELP_MESSAGE_AVERT);
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
					}//AVERT
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					
				}else if(args[0].equalsIgnoreCase("ban")){
					
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					// /mod ban <PSEUDO> <RAISON> <DUREE>
					//       0      1       2        3
					if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
						if(args.length == 4){
							Player target = Bukkit.getPlayer(args[1]);
							if(target == null){
								p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
								//target.setBanned(true);
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"Joueur "+target.getName()+" banni pour "+args[2]+" pendant "+args[3]+"j");
								for(Player pls : Bukkit.getOnlinePlayers()){
									if(pls.hasPermission("moderation.access")){
										pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a banni "+target.getName()+" pour "+args[2]+" pendant "+args[3]+" jours.");
									}
								}
								//target.setBanned(true);
							    //-----------
								// CONTINUER
							    //-----------
							}
						}else{
							p.sendMessage(ChatColor.RED+HELP_MESSAGE_BAN);
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
					}//BAN
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					
				}else if(args[0].equalsIgnoreCase("kick")){
					
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					// /mod kick <PSEUDO> <RAISON>
					//       0      1        2    
					if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
						if(args.length >= 3){
							Player target = Bukkit.getPlayer(args[1]);
												
							if(target == null){
								p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"Joueur "+target.getName()+" kick pour "+args[2]);
								for(Player pls : Bukkit.getOnlinePlayers()){
									if(pls.hasPermission("moderation.access")){
										pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a kick "+target.getName()+" pour "+args[2]);
									}
								}
								target.kickPlayer(ChatColor.RED+args[2]);
							}
						}else{
							p.sendMessage(ChatColor.RED+HELP_MESSAGE_KICK);
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
					}//KICK
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					
				}else if(args[0].equalsIgnoreCase("mute")){
					
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					// /mod mute <PSEUDO> <RAISON> <DUREE>
					//       0      1       2        3
					if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
						if(args.length >= 4){
							Player target = Bukkit.getPlayer(args[1]);
							if(target == null){
								p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"Vous avez donné le silence chez "+args[1]+" pour "+args[2]+" pendant "+args[3]+"s");
								for(Player pls : Bukkit.getOnlinePlayers()){
									if(pls.hasPermission("moderation.access")){
										pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a réduit au silence "+target.getName()+" pour "+args[2]+" pendant "+args[3]+"s");
									}
								}
								target.sendMessage(ChatColor.RED+"Vous ne pouvez plus parler pendant "+args[3]+"s ! motif : "+args[2]);
								 //--------------
								 // FINIR LE MUTE
								 //--------------
								//int tempsMute = Integer.parseInt(args[3]);
							}
						}else{
							p.sendMessage(ChatColor.DARK_PURPLE+HELP_MESSAGE_MUTE);
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+NO_PERMISSION_MESSAGE);
					}//MUTE
					// ----------------------------------------------------------------------------------------------------------------------------------------------------
					
				}else{
					p.sendMessage(HELP_MESSAGE);
				}//Mod avert / ban / kick / mute
				
			}else{
				p.sendMessage(HELP_MESSAGE);
			}//args.length
			
		}else{
				p.sendMessage(NO_PERMISSION_MESSAGE);	
		}//moderation.access	
		
		
		return false;
	}
	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------
}
