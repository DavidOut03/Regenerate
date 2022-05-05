package snapje.regenerate.Events;


import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import snapje.regenerate.Explode;
import snapje.regenerate.Main;


public class Explosion implements Listener {
	

	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onExplode(EntityExplodeEvent e) {
	
		e.setCancelled(true);
		
		Explode explode = new Explode(e.getEntity(), e.blockList());
		explode.regenerateBlocks(); 
		
		
	}
	
	
	

}
