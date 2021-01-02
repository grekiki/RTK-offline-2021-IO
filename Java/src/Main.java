import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void printHMap(int[][] hmap) {
		for (int y = 0; y < hmap[0].length; y++) {
			for (int x = 0; x < hmap.length; x++) {
				System.out.format("% 4d", hmap[x][y]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		int testCase = 15;

		TestCase tc = TestReader.readTestCase(testCase);

		ArrayList<CubePosition> solution = SimpleSolver.solve(tc);

		SolutionWriter.writeSolution(solution, tc, testCase);
	}

}
