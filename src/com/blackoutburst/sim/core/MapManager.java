package com.blackoutburst.sim.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class MapManager {

	public static void addBarrier() {
		for (int x = -15; x <=6; x++) {
			for (int y = 72; y <=80; y++) {
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y, 524)).setType(Material.BARRIER);
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y, 545)).setType(Material.BARRIER);
			}
		}
		
		for (int z = 524; z <=545; z++) {
			for (int y = 72; y <=80; y++) {
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), -15, y, z)).setType(Material.BARRIER);
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), 6, y, z)).setType(Material.BARRIER);
			}
		}
	}
	
	public static void removeBarrier() {
		for (int x = -15; x <=6; x++) {
			for (int y = 72; y <=80; y++) {
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y, 524)).setType(Material.AIR);
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y, 545)).setType(Material.AIR);
			}
		}
		
		for (int z = 524; z <=545; z++) {
			for (int y = 72; y <=80; y++) {
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), -15, y, z)).setType(Material.AIR);
				Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), 6, y, z)).setType(Material.AIR);
			}
		}
	}
}
