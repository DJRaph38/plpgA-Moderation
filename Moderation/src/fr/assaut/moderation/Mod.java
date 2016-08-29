package fr.assaut.moderation;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Mod implements CommandExecutor,Listener{

	
	
	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------
	public String HELP_MESSAGE = ChatColor.RED+"Erreur, tentez /mod <avert/kick/ban/mute> <Pseudo> {<Durée>} <Raison> !";
	public String HELP_MESSAGE_AVERT = ChatColor.RED+"Erreur, tentez /mod avert <Pseudo> <Raison> !";
	public String HELP_MESSAGE_BAN = ChatColor.RED+"Erreur, tentez /mod ban <Pseudo> <Durée> <Raison> !";
	public String HELP_MESSAGE_KICK = ChatColor.RED+"Erreur, tentez /mod kick <Pseudo> <Raison> !";
	public String HELP_MESSAGE_MUTE = ChatColor.RED+"Erreur, tentez /mod mute <Pseudo> <Durée> <Raison>!";
	public String OFFLINE_TARGET_PLAYER_MESSAGE = ChatColor.RED+"Erreur, joueur non connecté, tentez via le backend !";
	public String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED+"Vous n'avez pas les droits pour effectuer ceci !";
	public String STAFF_MESSAGE = ChatColor.RED+"Vous ne pouvez donner cette punition à un membre de l'équipe !";
	public static int task;
	public static int seconds;
	public static Player muted;
	public String mute;
	public static LinkedList<String> mutedPlayers = new LinkedList<String>();	
	// ----------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unused")
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
							//GESTION RAISON
							StringBuilder sb = new StringBuilder();
							for(int i=2; i < args.length; i++){
								sb.append(args[i]).append(" ");
							}
							String message = sb.toString();
							//SYSTEME
							Player target = Bukkit.getPlayer(args[1]);
							if(!target.hasPermission("moderation.moderateur") || !target.hasPermission("moderation.supermoderateur") || !target.hasPermission("moderation.administrateur") || !target.hasPermission("moderation.surveillant")){
								if(target == null){
									p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"Vous avez donné un avertissement à "+target.getName()+" pour "+ message);
									for(Player pls : Bukkit.getOnlinePlayers()){
										if(pls.hasPermission("moderation.access")){
											pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a donné un avertissement à "+target.getName()+" pour "+message);
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
										target.sendMessage(ChatColor.RED+"Vous avez reçu un avertissement pour "+ message);
									}
									/**
									 * 
									 *       SYSTEME AVERTISSEMENT A TERMINER
									 * 
									 */
								}
							}else{
								p.sendMessage(STAFF_MESSAGE);
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
					// /mod ban <PSEUDO> <DUREE> <RAISON>
					//       0      1       2       3
					if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
						if(args.length >= 4){
							//GESTION RAISON
							StringBuilder sb = new StringBuilder();
							for(int i=3; i < args.length; i++){
								sb.append(args[i]).append(" ");
							}
							String message = sb.toString();
							//SYSTEME
							Player target = Bukkit.getPlayer(args[1]);
							if(!target.hasPermission("moderation.moderateur") || !target.hasPermission("moderation.supermoderateur") || !target.hasPermission("moderation.administrateur")){
								if(target == null){
									p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
									//target.setBanned(true);
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"Joueur "+target.getName()+" banni pour "+message+" pendant "+args[2]+"j");
									for(Player pls : Bukkit.getOnlinePlayers()){
										if(pls.hasPermission("moderation.access")){
											pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a banni "+target.getName()+" pour "+message+" pendant "+args[2]+" jours.");
										}
									}
									//target.setBanned(true);
								    /**
								     * 
								     *     SYSTEME BAN A TERMINER
								     * 
								     */
								}
							}else{
								p.sendMessage(STAFF_MESSAGE);
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
							//RAISON
							StringBuilder sb = new StringBuilder();
							for(int i=2; i < args.length; i++){
								sb.append(args[i]).append(" ");
							}
							String message = sb.toString();
							//SYSTEME
							Player target = Bukkit.getPlayer(args[1]);
							if(!target.hasPermission("moderation.moderateur") || !target.hasPermission("moderation.supermoderateur") || !target.hasPermission("moderation.administrateur") ){				
								if(target == null){
									p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"Joueur "+target.getName()+" kick pour "+message);
									for(Player pls : Bukkit.getOnlinePlayers()){
										if(pls.hasPermission("moderation.access")){
											pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a kick "+target.getName()+" pour "+message);
										}
									}
									//alias
									if(args[2].equalsIgnoreCase("LI")){
										target.kickPlayer(ChatColor.RED+"Langage inapproprié");
									}else if(args[2].equalsIgnoreCase("ECT")){
										target.kickPlayer(ChatColor.RED+"Evocation de Cheat dans le Chat");
									}else if(args[2].equalsIgnoreCase("ACT")){
										target.kickPlayer(ChatColor.RED+"Accusation de Cheat dans le Chat");
									}else{
										target.kickPlayer(ChatColor.DARK_RED+message);
									}
								}
							}else{
								p.sendMessage(STAFF_MESSAGE);
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
					// /mod mute <PSEUDO> <DUREE> <RAISON>
					//       0      1        2       3
					if((p.hasPermission("moderation.moderateur")) || (p.hasPermission("moderation.supermoderateur")) || (p.hasPermission("moderation.administrateur"))){
						if(args.length >= 4){
							//RAISON
							StringBuilder sb = new StringBuilder();
							for(int i=3; i < args.length; i++){
								sb.append(args[i]).append(" ");
							}
							String message = sb.toString();
							//SYSTEME
							Player target = Bukkit.getPlayer(args[1]);
							if(!target.hasPermission("moderation.moderateur") || !target.hasPermission("moderation.supermoderateur") || !target.hasPermission("moderation.administrateur")){
								if(target == null){
									p.sendMessage(ChatColor.RED+OFFLINE_TARGET_PLAYER_MESSAGE);
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"Vous avez donné le silence chez "+args[1]+" pour "+message+" pendant "+args[2]+"mn/s");
									for(Player pls : Bukkit.getOnlinePlayers()){
										if(pls.hasPermission("moderation.access")){
											pls.sendMessage(ChatColor.DARK_PURPLE+p.getName()+" a réduit au silence "+target.getName()+" pour "+message+" pendant "+args[2]+"mn/s");
										}
									}										
										//alias
									if(args[3].equalsIgnoreCase("LI")){
										target.sendMessage(ChatColor.RED+"Vous ne pouvez plus parler pendant "+args[2]+"mn ! Motif : Langage inapproprié");
									}else if(args[3].equalsIgnoreCase("ECT")){
										target.sendMessage(ChatColor.RED+"Vous ne pouvez plus parler pendant "+args[2]+"mn ! Motif : Evocation de Cheat dans le Chat");
									}else if(args[3].equalsIgnoreCase("ACT")){
										target.sendMessage(ChatColor.RED+"Vous ne pouvez plus parler pendant "+args[2]+"mn ! Motif : Accusation de Cheat dans le Chat");
									}else{
										target.sendMessage(ChatColor.RED+"Vous ne pouvez plus parler pendant "+args[2]+"mn ! Motif : "+message);
									}																		
										//gestion du temps & conversion !
									int tempsMute = Integer.parseInt(args[2]);
									seconds = tempsMute;
									if(seconds == 1){
										seconds = 60;
									}else if(seconds == 2){
										seconds = 120;
									}else if(seconds == 3){
										seconds = 180;
									}else if(seconds == 4){
										seconds = 240;
									}else if(seconds == 5){
										seconds = 300;
									}									
										//lancement du mute
									muted = target;
									mute = target.getName();
									mutedPlayers.add(mute);
									startCountdown();
																		
									
								}
							}else{
								p.sendMessage(STAFF_MESSAGE);
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
	/**
	 * 
	 * 		Procédures complémentaires (mute)
	 * 	
	 */
	
	public static void startCountdown() {		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Moderation"),new Runnable(){					
			@Override
			public void run() {
				seconds--;
				if(seconds == 0){
					muted.sendMessage(ChatColor.RED+"Tu peux de nouveau parler, attention au comportement !");
					Bukkit.getScheduler().cancelTask(task);
					mutedPlayers.remove(muted);
				}							
			}				
		},20,20);		
	}//STARTCOUNTDOWN
	
	public static LinkedList<String> getMutedplayers(){
		return mutedPlayers;
	}

	public static int getSeconds(){
		return seconds;
	}
	
	
}