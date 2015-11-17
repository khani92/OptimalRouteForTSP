import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimalTSPDriver {

	public static void main(String[] args) {

		int startPos = 0, endPos = 0;
		OptimalTSP optimalTSPObj = new OptimalTSP();
		List<Integer> minimalPath = new ArrayList<>();

		try (Scanner scanObj = new Scanner(System.in);) {

			System.out.print("Enter start index: ");
			startPos = scanObj.nextInt();

			System.out.print("Enter end index: ");
			endPos = scanObj.nextInt();

			optimalTSPObj.loadGraph(startPos, endPos);

			System.out.println("Crime records Processed: \n");

			for (int i = 0; i < optimalTSPObj.getFileData().length; i++) {
				System.out.println(optimalTSPObj.getFileData()[i]);
			}

			optimalTSPObj.populateAllPaths(new ArrayList<Integer>());

			System.out.print("\nHamiltonian Cycle (minimal): ");
			minimalPath = optimalTSPObj.getAllPaths().get(
					optimalTSPObj.getMinPathLocation());

			System.out.println(minimalPath);

			System.out.println("Length of the Cycle: "
					+ optimalTSPObj.getLengthFromPath(minimalPath) + " miles");

			scanObj.close();
		}

	}
}
