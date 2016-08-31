package fr.assaut.moderation;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess implements Listener{

	@EventHandler
	public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		
		if(!(p.hasPermission("moderation.access"))){
			if(e.getMessage().equalsIgnoreCase("/pl") || e.getMessage().equalsIgnoreCase("/plugins") ){
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED+"Vous n'avez pas le droit de voir cela !");
			}
		
			if(e.getMessage().equalsIgnoreCase("/help") || e.getMessage().equalsIgnoreCase("/?") ){
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED+"---------------------------------------");
				p.sendMessage(ChatColor.GOLD+"Menu d'aide :");
				p.sendMessage(ChatColor.RED+"---------------------------------------");
				p.sendMessage("/hub : fait revenir au hub !"+ChatColor.DARK_PURPLE+" Impossible de retourner en jeu à ce moment la.");
				p.sendMessage("/hat : Te fait devenir quelqu'un de beau !");
				if(p.hasPermission("moderation.access")){
					p.sendMessage(ChatColor.DARK_PURPLE+"/mod avert <PSEUDO> <RAISON> : Pas d'espaces pour les raisons, donne un avertissement !");
					if(p.hasPermission("moderation.administrateur") || p.hasPermission("moderation.supermoderateur") || p.hasPermission("moderation.moderateur")){
						p.sendMessage(ChatColor.DARK_PURPLE+"/mod kick <PSEUDO> <RAISON> : Pas d'espaces pour les raisons, kick un joueur.");
						p.sendMessage(ChatColor.DARK_PURPLE+"/mod ban <PSEUDO> <RAISON> <DUREE> : Pas d'espaces pour les raisons, ban un joueur.");
						p.sendMessage(ChatColor.DARK_PURPLE+"/mod mute <PSEUDO> <RAISON> <DUREE> : Pas d'espaces pour les raisons, mute un joueur.");
					}
					p.sendMessage(ChatColor.DARK_PURPLE+"ALIASES : LI : Langage inapproprié // ECT : Evocation de Cheat dans le Chat // ACT : Accusation de Cheat");
				}
				p.sendMessage(ChatColor.RED+"---------------------------------------");
			}
		}//!if
	}
}
