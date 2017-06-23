package pgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class YourGenerator extends ChunkGenerator {

	List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

	public YourGenerator() {
		populators.add(new TreePopulator());
		populators.add(new SpecialBlockGenerator());
		populators.addAll(Bukkit.getWorld("lobby").getPopulators());

	}

	public List<BlockPopulator> getDefaultPopulators(World world) {
		return populators;
	}

	public byte[][] generateBlockSections(World world, Random random, int Chunkx, int Chunkz, BiomeGrid biomeGrid)
	{
		byte[][] result = new byte[world.getMaxHeight() / 16][]; //world height / chunk part height (=16, look above)
		int x= 0;
		int z= 0;

		for(x = 0; x < 16; x++)
		{
			for(z = 0; z < 16; z++)
			{
				//setBlock(result, x, 0, z, (byte)Material.BEDROCK.getId());
				Biome b = biomeGrid.getBiome(x, z);
				int mult = 30;
				int trans = 0;
				if(b.equals(Biome.FOREST) || b.equals(Biome.BIRCH_FOREST) || b.equals(Biome.ROOFED_FOREST)  ||  b.equals(Biome.MUTATED_FOREST)  || b.equals(Biome.MUTATED_BIRCH_FOREST))
				{
					//FORETS
					mult = 27;
				}
				else if(b.equals(Biome.OCEAN) || b.equals(Biome.DEEP_OCEAN))
				{
					//OCEANS
					mult = 18;
					trans = -10;
				}else if(b.equals(Biome.DESERT) || b.equals(Biome.SAVANNA) || b.equals(Biome.PLAINS))
				{
					//DESERTS
					mult = 25;
				}else if(b.equals(Biome.BEACHES) || b.equals(Biome.SWAMPLAND))
				{

					mult = 23;
				}else if(b.equals(Biome.BIRCH_FOREST_HILLS) || b.equals(Biome.DESERT_HILLS) || b.equals(Biome.EXTREME_HILLS)|| b.equals(Biome.FOREST_HILLS) || b.equals(Biome.JUNGLE_HILLS) )
				{
					mult = 34;
					trans = 6;
				}else if(b.equals(Biome.RIVER))
				{
					mult = 18;
					trans = -4;
				}
				int maxY = mathExpression(Chunkx,Chunkz,x,z, mult, trans);


				if(b.equals(Biome.RIVER))
				{
					//Si dans la rivière -> Eau
					//Si bord de rivière -> cobble

					//AJOUTER -> prise en charge de la mer
					try
					{
						if(world.getBiome(Chunkx*16+x +1, Chunkz*16 + z).equals(Biome.RIVER) == false || world.getBiome(Chunkx*16+x -1, Chunkz*16 + z).equals(Biome.RIVER) == false || world.getBiome(Chunkx*16+x, Chunkz*16 + z +1).equals(Biome.RIVER) == false|| world.getBiome(Chunkx*16+x, Chunkz*16 + z - 1).equals(Biome.RIVER)== false)
						{
							maxY = Math.max(Math.max(mathExpression(Chunkx,Chunkz,x,z+1, mult, trans),mathExpression(Chunkx,Chunkz,x,z-1, mult, trans)),Math.max(mathExpression(Chunkx,Chunkz,x +1 ,z, mult, trans),mathExpression(Chunkx,Chunkz,x-1,z, mult, trans)));
							setBlock(result, x, maxY +1 , z, (byte)Material.COBBLESTONE.getId());
							setBlock(result, x, maxY +2 , z, (byte)Material.COBBLESTONE.getId());
							setBlock(result, x, maxY +3 , z, (byte)Material.COBBLESTONE.getId());
							setBlock(result, x, maxY +4 , z, (byte)Material.COBBLESTONE.getId());

						}else
						{
							setBlock(result, x, maxY +1 , z, (byte)Material.SAND.getId());
							setBlock(result, x, maxY +2 , z, (byte)Material.STATIONARY_WATER.getId());
							setBlock(result, x, maxY +3 , z, (byte)Material.STATIONARY_WATER.getId());

						}
					}catch(Exception ex)
					{

					}
					generateLine(maxY,x,z,result);
				}else if(b.equals(Biome.DESERT) || b.equals(Biome.DESERT_HILLS) || b.equals(Biome.MUTATED_DESERT))
				{
					//biomeGrid.setBiome(x, z, Biome.BEACHES);
					
					//id / 14
					setBlock(result, x, maxY , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -1 , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -2 , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -3 , z, (byte)Material.SANDSTONE.getId());
					generateLine(maxY-4,x,z,result);
					

				}else if(b.equals(Biome.MESA) || b.equals(Biome.MESA_CLEAR_ROCK)|| b.equals(Biome.MESA_ROCK)|| b.equals(Biome.MUTATED_MESA) || b.equals(Biome.MUTATED_MESA_CLEAR_ROCK)|| b.equals(Biome.MUTATED_MESA_ROCK))
				{
					//biomeGrid.setBiome(x, z, Biome.BEACHES);
					int y = 0;
					for(y = 0 ; y <= maxY; y++)
					{
						ItemStack i = new ItemStack(Material.HARD_CLAY, 1, (byte) (y % 14));
						setBlock(result, x, y , z, (byte)i.getType().getId());	
					}

				}
				else if(maxY < 85 && maxY >= 80 && b.equals(Biome.RIVER) == false)
				{
					//biomeGrid.setBiome(x, z, Biome.BEACHES);
					setBlock(result, x, maxY , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -1 , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -2 , z, (byte)Material.SAND.getId());
					generateLine(maxY-3,x,z,result);

				}else if(maxY < 80 && b.equals(Biome.RIVER) == false)
				{
					//biomeGrid.setBiome(x, z, Biome.OCEAN);
					int y = 0;
					for(y = maxY + 1 ; y < 79; y++)
					{
						setBlock(result, x, y , z, (byte)Material.WATER.getId());	
					}
					setBlock(result, x, maxY -1 , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -2 , z, (byte)Material.SAND.getId());
					setBlock(result, x, maxY -3 , z, (byte)Material.SAND.getId());
					generateLine(maxY-4,x,z,result);

				}else
				{
					setBlock(result, x, maxY , z, (byte)Material.GRASS.getId());
					setBlock(result, x, maxY -1 , z, (byte)Material.DIRT.getId());
					setBlock(result, x, maxY -2 , z, (byte)Material.DIRT.getId());
					generateLine(maxY-3,x,z,result);
				}





			}
		}
		return result;
	}


	public void generateLine(int maxY, int x, int z, byte[][] result)
	{
		int y = 0;
		for(y = 0 ; y <= maxY; y++)
		{
			setBlock(result, x, y , z, (byte)Material.STONE.getId());	
		}
	}
	void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
		// is this chunk part already initialized?
		if (result[y >> 4] == null) {
			// Initialize the chunk part
			result[y >> 4] = new byte[4096];
		}
		// set the block (look above, how this is done)
		result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;

	}
	int mathExpression(int x, int z ,int x1,int z1, int mult, int trans)
	{
		return (int) Math.max(Math.min(stretchedNoise2(x*16+x1,z*16+z1,5*10)*mult + 100 + trans,200),10);
	}
	//TEST


	static float noise2(int x, int y) {
		int n = x + y * 57;
		n = (n << 13) ^ n;
		return (float) (1.0-((n*(n*n*15731+789221)+1376312589)&0x7fffffff)/1073741824.0);
	}
	static float stretchedNoise2(float x_float, float y_float, float stretch) {

		x_float /= stretch;
		y_float /= stretch;

		int x = (int) Math.floor(x_float);
		int y = (int) Math.floor(y_float);

		float fractional_X = x_float - x;
		float fractional_Y = y_float - y;

		double[] p = new double[4];
		for (int j = 0; j < 4; j++) {
			double[] p2 = new double[4];
			for (int i = 0; i < 4; i++) {
				p2[i] = noise2(x + i - 1, y + j - 1);
			}

			p[j] = cubicInterp(p2, fractional_X);
		}

		return (float) cubicInterp(p, fractional_Y);
	}
	public static double cubicInterp(double[] p, double x) {
		return cubicInterp(p[0],p[1],p[2],p[3], x);
	}
	public static double cubicInterp(double v0, double v1, double v2, double v3, double x) {
		double P = (v3 - v2) - (v0 - v1);
		double Q = (v0 - v1) - P;
		double R = v2 - v0;
		double S = v1;
		return P * x * x * x + Q * x * x + R * x + S;
	}

}