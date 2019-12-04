import java.util.ArrayList;

public class SortingVisualizer {
	
	private static Thread sortingThread;

	static VisualizerFrame frame;
	static ArrayList<Integer> data;
	static boolean isSorting = false;
	static int sortDataCount = 300;
	static int sleep = 2;
	static int blockWidth;

	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
		frame.setLocationRelativeTo(null);
	}
	
	public static void resetArray(){
		if (isSorting)
			return;

		data = new ArrayList<>();
		blockWidth = (int) Math.max(Math.floor(900/sortDataCount), 1);

		for(int i = 0; i < sortDataCount; i++){
			data.add((int) (sortDataCount*Math.random() + 1));
		}
		frame.preDrawArray(data);
	}
	
	public static void startSort(String type){
		if (sortingThread == null || !isSorting){
			resetArray();
			isSorting = true;

			switch(type){
			case "Bubble":
				sortingThread = new Thread(new BubbleSort(data, frame));
				break;

			case "Selection":
				sortingThread = new Thread(new SelectionSort(data, frame));
				break;

			case "Insertion":
				sortingThread = new Thread(new InsertionSort(data, frame));
				break;

			case "Merge":
				sortingThread = new Thread(new MergeSort(data, frame));
				break;

			case "Quick":
				sortingThread = new Thread(new QuickSort(data, frame));
				break;
				
			default:
				isSorting = false;
				return;
			}
			sortingThread.start();
		}
		
	}
}
