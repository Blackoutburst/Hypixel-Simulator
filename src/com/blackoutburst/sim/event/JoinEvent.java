package com.blackoutburst.sim.event;

import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerJoinEvent;

import com.blackoutburst.sim.core.Core;
import com.blackoutburst.sim.core.HPlayer;
import com.blackoutburst.sim.core.ScoreboardManager;

public class JoinEvent {
	
	public void execute(PlayerJoinEvent event) {
		event.getPlayer().teleport(Core.sleepyHollowSpawn);
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		event.getPlayer().setAllowFlight(false);
		event.getPlayer().setHealth(20);
		event.getPlayer().setFoodLevel(20);
		event.getPlayer().setSaturation(100000);
		Core.players.add(new HPlayer(event.getPlayer()));
		ScoreboardManager.init(event.getPlayer());
	}
}
