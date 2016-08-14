package fr.assaut.moderation;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PluginListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission("moderation.administrateur")){
			e.setJoinMessage(ChatColor.RED+"L'Administrateur "+e.getPlayer().getName()+" est connecté !");
		}else if(p.hasPermission("moderation.supermoderateur")){
			e.setJoinMessage(ChatColor.DARK_BLUE+"Le Super-Modérateur "+e.getPlayer().getName()+" est connecté !");
	    }else if(p.hasPermission("moderation.moderateur")){
	    	e.setJoinMessage(ChatColor.AQUA+"Le modérateur "+e.getPlayer().getName()+" est connecté !");
	    }else if(p.hasPermission("moderation.developper")){
	    	e.setJoinMessage(ChatColor.GREEN+"Le développeur "+e.getPlayer().getName()+" est connecté !");
	    }else{
			e.setJoinMessage(ChatColor.GOLD+"Bienvenue à "+e.getPlayer().getName()+" pour sa première connexion sur le serveur !");
		}
	}//ONPLAYERJOIN
	
	
}
