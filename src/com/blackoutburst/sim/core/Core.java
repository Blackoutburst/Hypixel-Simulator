package com.blackoutburst.sim.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.blackoutburst.sim.main.Main;


public class Core {
	
	public static List<HPlayer> players = new ArrayList<HPlayer>();
	public static Location sleepyHollowSpawn = null;
	public static int[] sleepyHollow = new int[] {-99, 58, 441, 86, 111, 626};
	public static List<Location> sleepyHollowHeads;
	public static int gameTime = 300;
	public static boolean gameRunning = false;
	public static int headLeft = 0;
	
	private static void loadMapHeads(String fileName, List <Location> list) {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(new File("plugins/HypixelSim/"+fileName+".yml"));
		
		if (file.getConfigurationSection("loc") == null) return;
		
		Set<String> loc = file.getConfigurationSection("loc").getKeys(false);
		
		for (String i : loc) {
			double x = file.getDouble("loc."+i+".x");
			double y = file.getDouble("loc."+i+".y");
			double z = file.getDouble("loc."+i+".z");
			
			list.add(new Location(Bukkit.getWorld("world"), x, y, z));
		}
	}
	
	public static void loadLocations() {
		sleepyHollowSpawn = new Location(Bukkit.getWorld("world"), -4.5f, 75, 534.5f, 0, 0);
		sleepyHollowHeads = new ArrayList<Location>();
		loadMapHeads("sleepyHollow", sleepyHollowHeads);
	}
	
	public static void startGame() {
		gameTime = 300;
		gameRunning = true;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			ScoreboardManager.setGameBoard(p);
			p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, false, false));
		}
	}
	
	public static void endGame() {
		gameTime = 300;
		gameRunning = false;
	}
	
	public static void gameTimer() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					if (gameRunning) {
						gameTime--;
						for (Player p : Bukkit.getOnlinePlayers()) {
							ScoreboardManager.updateGameTime(p);
						}
						if (gameTime <= 0) {
							endGame();
						}
					}
				} catch(Exception e) {}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0L, 20L);
	}
}
