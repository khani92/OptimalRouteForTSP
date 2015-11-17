import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimalTSP {

	private String[] fileData = null;
	private Graph tspGraph = null;
	private Point[] points = null;
	private List<List<Integer>> allPaths = new ArrayList<>();

	public void loadGraph(int start, int end) {

		fileData = getDataFromFile(start, end);
		points = getPointsFromFile();

		int size = end - start + 1;
		tspGraph = new Graph(size);

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				tspGraph.addEdge(i, j, Point.distance(points[i], points[j]));
				tspGraph.addEdge(j, i, Point.distance(points[i], points[j]));
			}
		}
	}

	public String[] getDataFromFile(int start, int end) {
		Scanner scanObj;
		int pos = 0, counter = 0;
		String[] data = new String[end - start + 1];

		try {
			scanObj = new Scanner(new File("CrimeLatLonXY1990.csv"));
			scanObj.nextLine();

			while (pos < start && scanObj.hasNext()) {
				scanObj.nextLine();
				pos++;
			}
			while (pos <= end && scanObj.hasNext()) {
				data[counter] = scanObj.nextLine();
				counter++;
				pos++;

			}

			scanObj.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	public Point[] getPointsFromFile() {
		String[] row = null;
		Point[] pointArray = new Point[fileData.length];
		for (int i = 0; i < fileData.length; i++) {
			row = fileData[i].split("[,]");
			pointArray[i] = new Point(Double.parseDouble(row[0].trim()),
					Double.parseDouble(row[1].trim()));
		}

		return pointArray;
	}

	/**
	 * This method runs all permutations and stores all the path in the allPaths array
	 * @param list
	 */
	public void populateAllPaths(List<Integer> list) {

		for (int i = 0; i < tspGraph.getSize(); i++) {

			if (!list.contains(i)) {

				list.add(i);
				populateAllPaths(list);
				if (!allPaths.contains(list)) {
					printToList(list);
				}

				list.remove(list.size() - 1);
			}

		}

	}

	/**
	 * This methods acts as a selector and removes the undesired cases 
	 * @param list
	 */
	public void printToList(List<Integer> list) {

		if (list.size() == tspGraph.getSize()) {
			List<Integer> list2 = new ArrayList<>(list);
			list2.add(list2.get(0));
			allPaths.add(list2);
		}

	}

	public double getLengthFromPath(List<Integer> list) {
		double sum = 0.0;

		for (int i = 0; i < list.size() - 1; i++) {
			sum = sum
					+ Point.distance(points[list.get(i)],
							points[list.get(i + 1)]);
		}

		return sum * (0.00018939);
	}

	/**
	 * Prints the minimum path from the all Paths method
	 * @return
	 */
	public int getMinPathLocation() {

		double min = getLengthFromPath(allPaths.get(0));
		int loc = 0;
		double tempDist = 0.0;

		for (int i = 0; i < allPaths.size(); i++) {
			tempDist = getLengthFromPath(allPaths.get(i));
			if (min > tempDist) {
				min = tempDist;
				loc = i;

			}
		}
		
		return loc;

	}

	public String[] getFileData() {
		return fileData;
	}

	public List<List<Integer>> getAllPaths() {
		return allPaths;
	}

}
