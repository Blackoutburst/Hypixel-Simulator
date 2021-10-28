package com.blackoutburst.sim.core;

import org.bukkit.entity.Player;

public class HPlayer {
	
	protected Player player;
	protected Board board;
	protected int score;
	
	public HPlayer(Player player) {
		this.player = player;
		this.board = null;
		this.score = 0;
	}
	
	public static HPlayer getFromPlayer(Player p) {
		for (HPlayer hp : Core.players) {
			if (hp.player.getUniqueId().equals(p.getUniqueId())) {
				return (hp);
			}
		}
		return (null);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
