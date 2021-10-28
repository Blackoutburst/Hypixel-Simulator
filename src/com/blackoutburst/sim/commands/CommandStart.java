package com.blackoutburst.sim.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.blackoutburst.sim.core.Core;
import com.blackoutburst.sim.core.MapManager;
import com.blackoutburst.sim.core.ScoreboardManager;
import com.blackoutburst.sim.core.SkullManager;
import com.blackoutburst.sim.main.Main;
import com.blackoutburst.sim.nms.NMSTitle;

public class CommandStart {

	public void run(CommandSender sender) {
		List<Location> spawn = new ArrayList<Location>(Core.sleepyHollowHeads);
		Collections.shuffle(spawn);
		
		Core.headLeft = 250;
		for (int i = 0; i < Core.headLeft; i++) {
			SkullManager.setSkull(spawn.get(i));
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("§eThe game starts in §610 §eseconds!");
		}
		
		for (int i = 0; i < 10; i++) {
			final int s = i;
			new BukkitRunnable(){
				@Override
				public void run(){
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (s >= 5) {
							p.sendMessage("§eThe game starts in §c"+(10-s)+" §eseconds!");
							NMSTitle.sendTitle(p, "§c"+(10-s), "", 0, 20, 0);
						}
						ScoreboardManager.updatePreGameTime(p, (10-s));
					}
				}
			}.runTaskLaterAsynchronously(Main.getPlugin(Main.class), 20L * (s));
		}
		
		new BukkitRunnable(){
			@Override
			public void run(){
				for (Player p : Bukkit.getOnlinePlayers()) {
					NMSTitle.sendTitle(p, "§cGo", "", 0, 20, 10);
					MapManager.removeBarrier();
					Core.startGame();
				}
			}
		}.runTaskLater(Main.getPlugin(Main.class), 20L * 10);
	}
}
