package com.blackoutburst.sim.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import com.blackoutburst.sim.core.Core;

public class CommandScan {

	private void saveLocation(int pos, int x, int y, int z) {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(new File("plugins/HypixelSim/seats.yml"));
		
		file.set("loc."+pos+".x", x);
		file.set("loc."+pos+".y", y);
		file.set("loc."+pos+".z", z);
		try {
			file.save(new File("plugins/HypixelSim/seats.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(CommandSender sender) {
		sender.sendMessage("§bStarting scan");
		int pos = 0;
		
		for (int x = Core.sleepyHollow[0]; x <= Core.sleepyHollow[3]; x++) {
			for (int y = Core.sleepyHollow[1]; y <= Core.sleepyHollow[4]; y++) {
				for (int z = Core.sleepyHollow[2]; z <= Core.sleepyHollow[5]; z++) {
					Block b = Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y, z));
					if (b.getType().equals(Material.SPONGE)) {
						saveLocation(pos, x, y, z);
						pos++;
					}
				}
			}
		}
		sender.sendMessage("§bScan complete found §6"+pos+" §b location");
	}
}
