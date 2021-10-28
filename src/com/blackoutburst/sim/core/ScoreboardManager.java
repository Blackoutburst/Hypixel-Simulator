package com.blackoutburst.sim.core;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreboardManager {
	
	public static void clear(HPlayer p) {
		for (int i = 15; i >= 0; i--)
			p.getBoard().remove(i);
	}
	
	public static void updatePreGamePlayer(Player p) {
		HPlayer hp = HPlayer.getFromPlayer(p);
		hp.getBoard().set(5, "Players: §a"+Bukkit.getOnlinePlayers().size()+"/8");
	}
	
	public static void updatePreGameTime(Player p, int time) {
		HPlayer hp = HPlayer.getFromPlayer(p);
		hp.getBoard().set(3, "Starting in §a"+time+"s");
	}
	
	public static void setPreGameBoard(Player p) {
		HPlayer hp = HPlayer.getFromPlayer(p);
		String pattern = "dd/MM/yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		
		clear(hp);
		
		hp.getBoard().setTitle("§eHALLOWEEN SIMULATOR");
		hp.getBoard().set(8, "§7"+dateInString); 
		hp.getBoard().set(7, " ");
		hp.getBoard().set(6, "Map: §aSleepy Hollow"); 
		hp.getBoard().set(5, "Players: §a"+Bukkit.getOnlinePlayers().size()+"/8");
		hp.getBoard().set(4, "  ");
		hp.getBoard().set(3, "Starting soon");
		hp.getBoard().set(2, "   ");
		hp.getBoard().set(1, "§enot.hypixel.net");
	}
	
	public static void init(Player p) {
		Board board = new Board(p);
		HPlayer hp = HPlayer.getFromPlayer(p);
		String pattern = "dd/MM/yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		
		board.setTitle("§e§lHALLOWEEN SIMULATOR");
		board.set(8, "§7"+dateInString); 
		board.set(7, " ");
		board.set(6, "Map: §aSleepy Hollow"); 
		board.set(5, "Players: §a"+Bukkit.getOnlinePlayers().size()+"/8");
		board.set(4, "  ");
		board.set(3, "Starting soon");
		board.set(2, "   ");
		board.set(1, "§enot.hypixel.net");
		hp.setBoard(board);
	}
	
	public static void setGameBoard(Player p) {
		HPlayer hp = HPlayer.getFromPlayer(p);
		String pattern = "dd/MM/yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		
		hp.getBoard().setTitle("§e§lHALLOWEEN SIMULATOR");
		hp.getBoard().set(13, "§7"+dateInString); 
		hp.getBoard().set(12, " ");
		hp.getBoard().set(11, "Time Left: §a5:00"); 
		hp.getBoard().set(10, "Candy Left: §a"+Core.headLeft);
		hp.getBoard().set(9, "  ");
		hp.getBoard().set(8, "Candy Found: §a0");
		hp.getBoard().set(7, "Ranking: §a1");
		hp.getBoard().set(6, "   ");
		
		int i = 0;
		for (HPlayer hpl : Core.players) {
			hp.getBoard().set(5-i++, i+". "+hpl.getPlayer().getDisplayName()+" §7(§e"+hpl.getScore()+"§7)");
		}
		hp.getBoard().set(2, "    ");
		hp.getBoard().set(1, "§enot.hypixel.net");
	}
	
	public static void updateGameTime(Player p) {
		int minutes = Core.gameTime / 60;
		int seconds = Core.gameTime % 60;
		String time = String.format("%d:%02d", minutes, seconds);
		
		HPlayer hp = HPlayer.getFromPlayer(p);
		
		hp.getBoard().set(11, "Time Left: §a"+time); 
		hp.getBoard().set(10, "Candy Left: §a"+Core.headLeft);
		hp.getBoard().set(8, "Candy Found: §a"+hp.getScore());
		
		Collections.sort(Core.players, new PlayerComparator());
		int pos = 1;
		for (HPlayer hpl : Core.players) {
			if (hp.getPlayer().getUniqueId().equals(hpl.getPlayer().getUniqueId())) {
				break;
			}
			pos++;
		}
		
		hp.getBoard().set(7, "Ranking: §a"+pos);
		updatePlayers();
	}
	
	public static void updatePlayers() {
		
  		for (int i = 0; i < 3; i++) {
  			if (i < Core.players.size()) {
	  			for (HPlayer hp : Core.players) {
	  				HPlayer p = Core.players.get(i);
	  				hp.getBoard().set(5-i, (i+1)+". "+p.getPlayer().getDisplayName()+" §7(§e"+p.getScore()+"§7)");
	  			}
  			} else {
  				for (HPlayer hp : Core.players) {
  					hp.getBoard().set(5 - i, "     ");
  				}
  			}
  		}
  	}
}
