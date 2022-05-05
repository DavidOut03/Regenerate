package snapje.regenerate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Explode {

	
	private static HashMap<Entity, Explode> explosions = new HashMap<Entity, Explode>();
	private HashMap<Location, BlockState> state = new HashMap<Location, BlockState>();
	private HashMap<Location, ItemStack[]> content = new HashMap<Location, ItemStack[]>();
	private Entity entity;
	private List<Block> blocks;
	
	public Explode(Entity explodingEntity, List<Block> list) {
		this.entity = explodingEntity;
		this.blocks = list;
		
		explosions.put(entity, this);
	}
	
	public Entity getExplodingEntity() {
		return entity;
	}
	public  List<Block> getExplodingBlocks() {
		return blocks;
	}
	
	public void regenerateBlocks() {
		
		for(Block b : getExplodingBlocks()) {
			
			if(b.getType() != Material.TNT || b.getType() == Material.BEDROCK || b.getType() == Material.OBSIDIAN || b.getType() != Material.SAND) {
			
			state.put(b.getLocation(), b.getState());
			if(b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST) {
				Chest c = (Chest) b.getState();
				ItemStack[] inv = c.getBlockInventory().getContents();
				content.put(b.getLocation(), inv);
				c.getBlockInventory().clear();
			}
			b.setType(Material.AIR);
			
			/*
			FallingBlock fb = b.getLocation().getWorld().spawnFallingBlock(b.getLocation(), Material.BEDROCK, (byte) 0);
			
			Random random = new Random();
			double x = random.nextDouble();
			double y = random.nextDouble();
			double z = random.nextDouble();
			
			Vector vector = new Vector(x, y, z);
			fb.setVelocity(vector); */
			
			
	
		int delay = 10;
		for(Location loc : state.keySet()) {
			delay += 1;
			
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			
			@Override
			public void run() {
					final BlockState bs = state.get(loc);
					bs.update(true, false);
					if(bs.getType() == Material.CHEST || bs.getType() == Material.TRAPPED_CHEST) {
						Chest c = (Chest) loc.getBlock().getState();
						c.getBlockInventory().clear();
						c.getBlockInventory().setContents(content.get(loc));
					}
			}
		}, delay );
		}
			} 
				
		
		}
		
	}
}
