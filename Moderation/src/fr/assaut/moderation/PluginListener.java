package fr.assaut.moderation;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PluginListener implements Listener {

	File configFile = new File("plugin/Moderation/PlayerConfig.yml");
	FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	
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
			String uuid = p.getUniqueId().toString();
			if(config.contains(uuid)){
				p.sendMessage(ChatColor.GOLD+"Bienvenue sur Assault v1.0 ! Bon jeu � toi !");
			}else{
				try{
					config.set(uuid,1);
					config.save(configFile);
					//CONFIG NE MARCHE PAS
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}//ONPLAYERJOIN

	
	
}