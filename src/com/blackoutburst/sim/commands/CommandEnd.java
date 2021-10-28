package com.blackoutburst.sim.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import com.blackoutburst.sim.core.Core;
import com.blackoutburst.sim.core.MapManager;

public class CommandEnd {

	public void run(CommandSender sender) {
		List<Location> spawn = new ArrayList<Location>(Core.sleepyHollowHeads);
		
		for (Location loc : spawn) {
			Bukkit.getWorld("world").getBlockAt(loc).setType(Material.AIR);
		}
		MapManager.addBarrier();
		Core.endGame();
	}
}
