package com.blackoutburst.sim.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.blackoutburst.sim.commands.CommandEnd;
import com.blackoutburst.sim.commands.CommandScan;
import com.blackoutburst.sim.commands.CommandStart;
import com.blackoutburst.sim.core.Core;
import com.blackoutburst.sim.core.HPlayer;
import com.blackoutburst.sim.core.ScoreboardManager;
import com.blackoutburst.sim.core.SkullManager;
import com.blackoutburst.sim.event.JoinEvent;

public class Main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		new File("plugins/HypixelSim").mkdirs();
		Core.loadLocations();
		Core.gameTimer();
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.SKULL)) {
			SkullManager.rightClickEffect(event);
			HPlayer hp = HPlayer.getFromPlayer(event.getPlayer());
			hp.setScore(hp.getScore() + 1);
			hp.getPlayer().sendMessage("§aYou found a candy! §7("+hp.getScore()+" total)");
			Core.headLeft--;
			event.getClickedBlock().setType(Material.AIR);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		new JoinEvent().execute(event);
		if (!Core.gameRunning) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				ScoreboardManager.updatePreGamePlayer(p);
			}
		}
	}
	
	@EventHandler
 	public void onPlayerQuit(PlayerQuitEvent event) {
		Core.players.remove(HPlayer.getFromPlayer(event.getPlayer()));
		
		if (!Core.gameRunning) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				ScoreboardManager.updatePreGamePlayer(p);
			}
		}
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(event.toWeatherState());
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		event.setCancelled(true);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch(command.getName()) {
			case "start": new CommandStart().run(sender); break;
			case "end": new CommandEnd().run(sender); break;
			case "scan": new CommandScan().run(sender); break;
			default: return true;
		}
		return true;
	}
}
