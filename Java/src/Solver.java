import java.util.ArrayList;

class CubePosition {
	Cube b;
	int x, y, z;

	public CubePosition(Cube b, int x, int y, int z) {
		this.b = b;
		this.x = x;
		this.y = y;
		this.z = z;
	}

}

class SimpleSolver {
	public static ArrayList<CubePosition> solve(TestCase tc) {
		ArrayList<CubePosition> solution = new ArrayList<CubePosition>();
		int dimx=tc.dimensions[0];
		int dimy=tc.dimensions[1];
		int dimz=tc.dimensions[2];
		int[][][]grid=makeGrid(tc);
		for(int x=0;x<dimx;x++) {
			for(int y=0;y<dimy;y++) {
				for(int z=0;z<dimz;z++) {
					if(grid[x][y][z]==1) {
						solution.add(new CubePosition(new Cube(1,1,1),x,y,z));
					}
				}
			}
		}
		return solution;
	}
	public static int[][][] makeGrid(TestCase tc){
		int dimx=tc.dimensions[0];
		int dimy=tc.dimensions[1];
		int dimz=tc.dimensions[2];
		int[][][] grid = new int[dimx][dimy][dimz];
		for(int x=0;x<dimx;x++) {
			for(int y=0;y<dimy;y++) {
				for(int z=0;z<dimz;z++) {
					if(tc.hmap[x][y]>z) {
						grid[x][y][z]=1;
					}
				}
			}
		}
		return grid;
	}
}