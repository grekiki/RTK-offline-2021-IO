import java.io.*;
import java.util.ArrayList;

public class SolutionWriter {
	public static final int submissionCode = 123456;

	public static void writeSolution(ArrayList<CubePosition> solution, TestCase tc, int tcase) throws Exception {
		if (!checkSolution(solution, tc, true)) {
			return;
		}
		int dimx = tc.dimensions[0];
		int dimy = tc.dimensions[1];
		int dimz = tc.dimensions[2];
		int[][][] output = new int[dimx][dimy][dimz];
		int p = 0;
		for (CubePosition cp : solution) {
			p++;
			for (int dx = 0; dx < cp.b.dim[0]; dx++) {
				for (int dy = 0; dy < cp.b.dim[1]; dy++) {
					for (int dz = 0; dz < cp.b.dim[2]; dz++) {
						output[cp.x + dx][cp.y + dy][cp.z + dz] = p;
					}
				}
			}
		}
		int[] scores = getScores();
		if (scores[tcase - 1] <= solution.size()) {
			System.out.println("Attempt at length " + solution.size() + " is not an improvement on " + scores[tcase - 1]);
			return;
		} else {
			System.out.println("IMPROVEMENT ON CASE " + tcase + " FROM " + scores[tcase - 1] + " TO " + solution.size());
			scores[tcase - 1] = solution.size();
			setScores(scores);
		}
		// Write the solution
		PrintWriter out = new PrintWriter(new FileWriter(new File("outputs\\output" + tcase + ".txt")));
		out.println(submissionCode);
		out.println("Pokrajina");
		out.println();
		out.println(tcase);
		for (int z = 0; z < dimz; z++) {
			for (int y = 0; y < dimy; y++) {
				for (int x = 0; x < dimx; x++) {
					out.print(output[x][y][z] + " ");
				}
				out.println();
			}
		}
		out.close();

	}

	public static boolean checkSolution(ArrayList<CubePosition> solution, TestCase tc, boolean debug) {
		int dimx = tc.dimensions[0];
		int dimy = tc.dimensions[1];
		int dimz = tc.dimensions[2];
		int[][][] grid = makeGrid(tc);
		for (CubePosition cp : solution) {
			for (int dx = 0; dx < cp.b.dim[0]; dx++) {
				for (int dy = 0; dy < cp.b.dim[1]; dy++) {
					for (int dz = 0; dz < cp.b.dim[2]; dz++) {
						if (grid[cp.x + dx][cp.y + dy][cp.z + dz] == 0) {
							if (debug) {
								System.out.println("Wrong solution");
							}
							return false;
						} else {
							grid[cp.x + dx][cp.y + dy][cp.z + dz] = 0;
						}
					}
				}
			}
		}
		// Check if grid contains any unfilled positions
		for (int x = 0; x < dimx; x++) {
			for (int y = 0; y < dimy; y++) {
				for (int z = 0; z < dimz; z++) {
					if (grid[x][y][z] == 1) {
						if (debug) {
							System.out.println("Wrong solution. Field " + x + " " + y + " " + z + " is empty.");
						}
						return false;
					}
				}
			}
		}
		return true;
	}

	public static int[][][] makeGrid(TestCase tc) {
		int dimx = tc.dimensions[0];
		int dimy = tc.dimensions[1];
		int dimz = tc.dimensions[2];
		int[][][] grid = new int[dimx][dimy][dimz];
		for (int x = 0; x < dimx; x++) {
			for (int y = 0; y < dimy; y++) {
				for (int z = 0; z < dimz; z++) {
					if (tc.hmap[x][y] > z) {
						grid[x][y][z] = 1;
					}
				}
			}
		}
		return grid;
	}

	public static int[] getScores() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File("scores.txt")));
		int[] scores = new int[26];
		for (int i = 0; i < scores.length; i++) {
			scores[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		return scores;
	}

	public static void setScores(int[] scores) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(new File("scores.txt")));
		for (int i = 0; i < scores.length; i++) {
			out.println(scores[i]);
		}
		out.close();
	}
}
