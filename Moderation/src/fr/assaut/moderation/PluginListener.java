package fr.assaut.moderation;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PluginListener implements Listener {

	File configFile = new File("plugins/Moderation/PlayerConfig.yml");
	FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	
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
			String uuid = p.getUniqueId().toString();
			if(config.contains(uuid)){
				p.sendMessage(ChatColor.GOLD+"Bienvenue sur Assault v1.0 ! Bon jeu à toi !");
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

	@EventHandler
	public void onPlayerBreak(BlockBreakEvent f){
		Player p = f.getPlayer();
		if(!(p.isOp())){
			f.setCancelled(true);
			p.sendMessage(ChatColor.DARK_RED+"Vous ne pouvez pas effectuer cette action.");
		}
	}
	
	@EventHandler
	public void onPlayerTouch(BlockDamageEvent g){
		Player p = g.getPlayer();
		if(!(p.isOp())){
			g.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent h){
		Player p = h.getPlayer();
		if(!(p.isOp())){
			h.setCancelled(true);
			p.sendMessage(ChatColor.DARK_RED+"Action impossible !");
		}
	}
	
	
}
