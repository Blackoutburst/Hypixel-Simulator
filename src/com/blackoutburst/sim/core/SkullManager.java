package com.blackoutburst.sim.core;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.blackoutburst.sim.main.Main;
import com.blackoutburst.sim.nms.NMSParticle;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class SkullManager {
	
	private static Material pickRandomItem() {
		int rng = new Random().nextInt(4);
		
		switch(rng) {
			case 0: return Material.GOLDEN_APPLE;
			case 1: return Material.COOKIE;
			case 2: return Material.PUMPKIN_PIE;
			default : return Material.CAKE;
		}
	}
	
	public static void rightClickEffect(PlayerInteractEvent event) {
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_PICKUP, 1.0f, 0.5f);
		
		Location loc = event.getClickedBlock().getLocation();
		loc.setX(loc.getX() + 0.5f);
		loc.setZ(loc.getZ() + 0.5f);
		
		for (int i = 0; i < 3; i++) {
			NMSParticle.spawnParticle(event.getPlayer(), EnumParticle.EXPLOSION_NORMAL, (float)(loc.getX()), (float)(loc.getY()), (float)(loc.getZ()));
			ItemStack itemstack = new ItemStack(pickRandomItem(), 1);
			Item item = event.getPlayer().getWorld().dropItem(loc, itemstack);
			item.setPickupDelay(100);
			
			new BukkitRunnable(){
				@Override
				public void run(){
					item.remove();
				}
			}.runTaskLaterAsynchronously(Main.getPlugin(Main.class), 10L);
		}
	}
	
	private static BlockFace getRandomRotation() {
		int rng = new Random().nextInt(16);
		
		switch(rng) {
			case 0: return BlockFace.EAST;
			case 1: return BlockFace.EAST_NORTH_EAST;
			case 2: return BlockFace.EAST_SOUTH_EAST;
			case 3: return BlockFace.NORTH;
			case 4: return BlockFace.NORTH_EAST;
			case 5: return BlockFace.NORTH_NORTH_EAST;
			case 6: return BlockFace.NORTH_NORTH_WEST;
			case 7: return BlockFace.NORTH_WEST;
			case 8: return BlockFace.SOUTH;
			case 9: return BlockFace.SOUTH_EAST;
			case 10: return BlockFace.SOUTH_SOUTH_EAST;
			case 11: return BlockFace.SOUTH_SOUTH_WEST;
			case 12: return BlockFace.SOUTH_WEST;
			case 13: return BlockFace.WEST;
			case 14: return BlockFace.WEST_NORTH_WEST;
			case 15: return BlockFace.WEST_SOUTH_WEST;
			default: return BlockFace.SELF;
		}
	}
	
	private static String getRandomSkin() {
		int rng = new Random().nextInt(6);
		
		if (new Random().nextInt(1000) == 0) {
			return "http://textures.minecraft.net/texture/15aa8c3a91d325d45fc53867667ef9d4e7455d2cfede4b05607e6daf6860e7d5"; // ??????
		}
		
		switch(rng) {
			case 0: return "http://textures.minecraft.net/texture/bd6347465458cf6b1c2dc722bd267f7e720ba98566aa41e8dc0ed36ce4be4a88"; //Red
			case 1: return "http://textures.minecraft.net/texture/2670c37a2f177e8afe29627adfb692bab30ba679736ccccc826148987f9bcf27"; //Blue
			case 2: return "http://textures.minecraft.net/texture/39668767f1141835e2c49ad2b415598f1b166be9173902a0257e77704f913e1f"; //Gray
			case 3: return "http://textures.minecraft.net/texture/2c42b95ee1a78b08b8f9e7acfedf33ad6e4ecd081912518ba99f041a7ef21f1b"; //Green
			case 4: return "http://textures.minecraft.net/texture/4eb46bbca3812363b59676ff629dc3698257fd178d394fadffda7143422e2f10"; //Purple
			default: return "http://textures.minecraft.net/texture/dd31173e7b7c78e5e48dcff66d57980aeca3824cd8584aac46d77c260c38bb4c"; //Orange
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void setSkull(Location loc) {
		Block skull = loc.getWorld().getBlockAt(loc);
		skull.setType(Material.SKULL, false);
		
		Skull s = (Skull) skull.getState();
		s.setRawData((byte) 1);
		s.setRotation(getRandomRotation());
		s.update();
		
		NBTEditor.setSkullTexture(skull, getRandomSkin());
	}
}
