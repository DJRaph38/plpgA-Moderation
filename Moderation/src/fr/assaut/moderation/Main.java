package fr.assaut.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;




public class Main extends JavaPlugin{

	
	
	public void onEnable(){
		getLogger().info("Plugin Moderation ON !");
		
		CommandExecutor modo = new Mod(); //COMMANDES MODERATION
		getCommand("mod").setExecutor(modo);
		
		CommandExecutor report = new ReportCommand();//REPORT
		getCommand("report").setExecutor(report);
		
		CommandExecutor herve = new Herve();//HERVEBLANCHON
		getCommand("herve").setExecutor(herve);
		
		//CONNEXION D'UN MODERATEUR, ADMIN, SM, YOUTUBEUR A AJOUTER
		Bukkit.getPluginManager().registerEvents(new PluginListener(),this); //************************************************* HERE WE GO

	}
	
	
	public void onDisable(){
		getLogger().info("Plugin [Moderation] OFF !");
	}
}
