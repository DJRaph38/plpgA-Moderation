package fr.assaut.moderation;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;




public class Main extends JavaPlugin{

	public void onEnable(){
		getLogger().info("Plugin Moderation ON !");
		
		CommandExecutor avert = new ModAvert(); //AVERT
		getCommand("avert").setExecutor(avert);
		
		CommandExecutor c2 = new ModMute();//MUTE
		getCommand("mmute").setExecutor(c2);
		
		CommandExecutor c3 = new ModBan();//BAN
		getCommand("mban").setExecutor(c3);
		
		CommandExecutor c4 = new ModKick();//KICK
		getCommand("mkick").setExecutor(c4);
		
		CommandExecutor report = new ReportCommand();//REPORT
		getCommand("report").setExecutor(report);
		
		CommandExecutor herve = new Herve();//HERVEBLANCHON
		getCommand("herve").setExecutor(herve);
		
		//CONNEXION D'UN MODERATEUR, ADMIN, SM, YOUTUBEUR A AJOUTER
	}
	
	
	public void onDisable(){
		getLogger().info("Plugin [Moderation] OFF !");
	}
}
