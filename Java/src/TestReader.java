import java.io.*;
import java.util.StringTokenizer;

class Cube {
	int[] dim;

	public Cube(int x, int y, int z) {
		dim = new int[] { x, y, z };
	}
}

class TestCase {
	int[] dimensions;
	int[][] hmap;
	Cube[] cubes;

	public TestCase(int[] dimensions, int[][] hmap, Cube[] cubes) {
		this.dimensions = dimensions;
		this.hmap = hmap;
		this.cubes = cubes;
	}
}

public class TestReader {
	static BufferedReader in;

	public static TestCase readTestCase(int testCase) throws Exception {
		if (testCase <= 0 || testCase > 26) {
			System.out.println("Testcase " + testCase + " is not within 1-26");
			return null;
		}
		in = new BufferedReader(new FileReader(new File("pokrajina.in")));
		in.readLine();
		in.readLine();
		in.readLine();
		for (int i = 1; i < testCase; i++) {
			readSingleTestCase();
		}
		return readSingleTestCase();
	}

	private static TestCase readSingleTestCase() throws IOException {
		in.readLine();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[][] hmap = new int[w][h];
		int layers = 0;
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < w; j++) {
				hmap[j][i] = Integer.parseInt(st.nextToken());
				layers = Math.max(layers, hmap[j][i]);
			}
		}
		int cubeCount = Integer.parseInt(in.readLine());
		Cube[] cubes = new Cube[cubeCount];
		for (int i = 0; i < cubeCount; i++) {
			st = new StringTokenizer(in.readLine());
			int dimx = Integer.parseInt(st.nextToken());
			int dimy = Integer.parseInt(st.nextToken());
			int dimz = Integer.parseInt(st.nextToken());
			cubes[i] = new Cube(dimx, dimy, dimz);
		}
		in.readLine();
		return new TestCase(new int[] { w, h, layers }, hmap, cubes);
	}
}
