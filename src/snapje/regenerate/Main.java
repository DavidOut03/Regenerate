package snapje.regenerate;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import snapje.regenerate.Events.Explosion;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Explosion(), this);
	}
	
	public void onDisable() {
		
	}

}
