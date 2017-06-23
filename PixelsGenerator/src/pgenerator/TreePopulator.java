package pgenerator;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

import org.bukkit.BlockChangeDelegate;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;

import net.minecraft.server.v1_11_R1.Village;

public class TreePopulator extends BlockPopulator {

	@Override
	public void populate(World world, Random r, Chunk chunk) {
		int x = chunk.getX()*16;
		int z = chunk.getZ()*16;
		int i = 0;
		int max = 16;
		for(i = 0; i < max ; i++)
		{
			int cx = x + r.nextInt(15);
			int cz = z + r.nextInt(15);
			Biome b = world.getBiome(cx, cz);
			try
			{

				int j = r.nextInt(5);
				try
				{

					cx = x + r.nextInt(15);
					cz = z + r.nextInt(15);
					//Placer de l'herbe sur de l'herbe (LOL)
					if(world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz) - 1,cz)).getType().equals(Material.GRASS))
					{
						Block bl = world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz),cz));
						bl.setTypeIdAndData(31, (byte) 1, true);
					}else if(world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz) - 1,cz)).getType().equals(Material.SAND) && j == 2)
					{
						Block bl = world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz),cz));
						bl.setTypeIdAndData(81,(byte) 0, true);
						Block bl2 = world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz)+1,cz));
						bl2.setTypeIdAndData(81,(byte) 0, true);
						Block bl3 = world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz)+2,cz));
						bl3.setTypeIdAndData(81,(byte) 0, true);
					}


				}catch(Exception ex)
				{

				}
				if(b.equals(Biome.FOREST) || b.equals(Biome.ROOFED_FOREST)  ||  b.equals(Biome.MUTATED_FOREST)  || b.equals(Biome.MUTATED_BIRCH_FOREST))
				{
					ArrayList<TreeType> tree = new ArrayList<TreeType>();
					tree.add(TreeType.BIRCH);
					tree.add(TreeType.REDWOOD);
					tree.add(TreeType.BIG_TREE);
					tree.add(TreeType.TREE);
					tree.add(TreeType.BROWN_MUSHROOM);
					RandomBuild(tree, world,cx,cz,r);
					cx = x + r.nextInt(15);
					cz = z + r.nextInt(15);



					//world.getBlockAt(new Location(world, cx, world.getHighestBlockYAt(cx, cz),cz)).setType(Material.getMaterial("tallgrass"));


				}else if(b.equals(Biome.BIRCH_FOREST) || b.equals(Biome.BIRCH_FOREST_HILLS))
				{
					max = 32;
					ArrayList<TreeType> tree = new ArrayList<TreeType>();
					tree.add(TreeType.BIRCH);
					tree.add(TreeType.TALL_BIRCH);
					RandomBuild(tree, world,cx,cz,r);
					cx = x + r.nextInt(15);
					cz = z + r.nextInt(15);


				}
				else if(b.equals(Biome.OCEAN) || b.equals(Biome.DEEP_OCEAN))
				{

				}else if(b.equals(Biome.SAVANNA) || b.equals(Biome.SAVANNA_ROCK) || b.equals(Biome.MUTATED_SAVANNA) || b.equals(Biome.MUTATED_SAVANNA_ROCK))
				{
					if(r.nextInt(16) == 15)
					{
						ArrayList<TreeType> tree = new ArrayList<TreeType>();
						tree.add(TreeType.ACACIA);
						tree.add(TreeType.TREE);
						RandomBuild(tree, world,cx,cz,r);
					}
				}else if(b.equals(Biome.RIVER))
				{

				}else if(b.equals(Biome.PLAINS))
				{
					if(r.nextInt(64) == 15)
					{
						ArrayList<TreeType> tree = new ArrayList<TreeType>();
						tree.add(TreeType.BIG_TREE);
						RandomBuild(tree, world,cx,cz,r);
					}

				}else
				{
					world.generateTree(new Location(world, cx, world.getHighestBlockYAt(cx, cz),cz), TreeType.ACACIA);
				}

			}catch(Exception Ex)
			{

			}
		}
	}
	public void RandomBuild(ArrayList<TreeType> tree, World w , int x,int z, Random r)
	{
		Block bl = w.getBlockAt(new Location(w, x, w.getHighestBlockYAt(x, z),z));
		if(bl.getType().equals(Material.getMaterial(31)))
		{
			bl.setType(Material.AIR);
		}
		w.generateTree(new Location(w, x, w.getHighestBlockYAt(x, z),z), tree.get(r.nextInt(tree.size())));
	}

}


