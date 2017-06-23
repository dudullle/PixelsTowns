package pgenerator;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class SpecialBlockGenerator extends BlockPopulator {

	@Override
	public void populate(World world, Random r, Chunk chunk) {
		int x = chunk.getX()*16;
		int z = chunk.getZ()*16;
		int i = 0;
		int max = 4096;
		for(i = 0; i < max ; i++)
		{
			int cx = x + r.nextInt(15);
			int cz = z + r.nextInt(15);
			Biome b = world.getBiome(cx, cz);
			//GENERATION DE CLAY
			try
			{

				try
				{
					if(b.equals(Biome.MESA) || b.equals(Biome.MESA_CLEAR_ROCK)|| b.equals(Biome.MESA_ROCK)|| b.equals(Biome.MUTATED_MESA) || b.equals(Biome.MUTATED_MESA_CLEAR_ROCK)|| b.equals(Biome.MUTATED_MESA_ROCK))
					{
						cx = x + r.nextInt(15);
						cz = z + r.nextInt(15);
						int y = r.nextInt(world.getHighestBlockYAt(cx, cz));
						
						Block bl = world.getBlockAt(new Location(world, cx, y,cz));
						bl.setTypeIdAndData(159, (byte) (y % 14), true);
					}else
					{
						//GENERATION MINERAIS
						int j = r.nextInt(384);
						
						if(j == 1 || j == 2)
						{
							//DIAMANT
							int y = r.nextInt(16);
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.DIAMOND_ORE);
						}else if(j == 3 || j == 4 || j == 5  || j == 6)
						{
							//COAL
							int y = r.nextInt(world.getHighestBlockYAt(cx, cz)-15);
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.COAL_ORE);
						}else if(j == 7 || j == 8 || j == 9)
						{
							//IRON
							int y = Math.abs(r.nextInt(world.getHighestBlockYAt(cx, cz) - 15));
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.IRON_ORE);
						}else if(j == 10 || j == 11)
						{
							//OR
							int y = Math.abs(r.nextInt(24));
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.IRON_ORE);
						}else if(j == 13 || j == 14 || j == 15)
						{
							//REDSTONE
							int y = Math.abs(r.nextInt(16));
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.REDSTONE_ORE);
						}else if(j == 17)
						{
							//EMERAUDE
							int y = Math.abs(r.nextInt(world.getHighestBlockYAt(cx, cz) - 30));
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.EMERALD_ORE);
						}else if(j == 18)
						{
							//EMERAUDE
							int y = Math.abs(r.nextInt(24));
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setType(Material.LAPIS_ORE);
						}else if(j == 19 || j == 20 || j == 21 || j == 22 || j == 23)
						{
							//stone:1
							int y = Math.abs(r.nextInt(world.getHighestBlockYAt(cx, cz) - 15));
							Block bl = world.getBlockAt(new Location(world, cx, y,cz));
							bl.setTypeIdAndData(1,(byte) r.nextInt(6), true);
						}
					}
					


				}catch(Exception ex)
				{

				}
				
				
			}catch(Exception Ex)
			{

			}
		}
	}


}



