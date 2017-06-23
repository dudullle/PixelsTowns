package PixelsSW.main;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.core.helpers.Transform;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.boydti.fawe.util.EditSessionBuilder;
import com.boydti.fawe.util.TaskManager;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.world.DataException;

public class SchManager {
	public static boolean pasteSchematics(World world, File file,Location origin) throws DataException, IOException, MaxChangedBlocksException
	{
		TaskManager.IMP.async(new Runnable() {
			@Override
			public void run() {
				EditSession es = new EditSessionBuilder(new BukkitWorld(world)).fastmode(true).build();
				Vector v = new Vector(origin.getBlockX(), origin.getBlockY(), origin.getBlockZ());


				boolean noAir = false;
				boolean entities = true;
				Vector position = new Vector(0, 0, 0);
				try {
					SchematicFormat.getFormat(file).load(file).paste(es, v, noAir, entities);
				} catch (MaxChangedBlocksException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (com.sk89q.worldedit.data.DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				es.flushQueue();

			}
		});
		return true;
	}
	public static void setvoid(World world)
	{
		TaskManager.IMP.async(new Runnable() {
			@Override
			public void run() {
				EditSession es = new EditSessionBuilder(new BukkitWorld(world)).fastmode(true).build();

				Vector pos1 = new Vector(-600, 256, -600);
				Vector pos2 = new Vector(600, 0, 600);
				CuboidRegion region = new CuboidRegion(pos1, pos2);
				try {
					es.setBlocks(region, new BaseBlock(0));
				} catch (MaxChangedBlocksException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}

