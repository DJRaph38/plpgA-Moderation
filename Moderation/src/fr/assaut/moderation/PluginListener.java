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
			e.setJoinMessage(ChatColor.RED+"L'Administrateur "+e.getPlayer().getName()+" est connect� !");
		}else if(p.hasPermission("moderation.supermoderateur")){
			e.setJoinMessage(ChatColor.DARK_BLUE+"Le Super-Mod�rateur "+e.getPlayer().getName()+" est connect� !");
	    }else if(p.hasPermission("moderation.moderateur")){
	    	e.setJoinMessage(ChatColor.AQUA+"Le mod�rateur "+e.getPlayer().getName()+" est connect� !");
	    }else if(p.hasPermission("moderation.developper")){
	    	e.setJoinMessage(ChatColor.GREEN+"Le d�veloppeur "+e.getPlayer().getName()+" est connect� !");
	    }else{
			e.setJoinMessage(ChatColor.GOLD+"Bienvenue � "+e.getPlayer().getName()+" pour sa premi�re connexion sur le serveur !");
		}
	}//ONPLAYERJOIN
	
	
}
