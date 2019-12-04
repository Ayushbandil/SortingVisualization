import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class InsertionSort implements Runnable{
	
	private ArrayList<Integer> data;
	private VisualizerFrame frame;

	public InsertionSort(ArrayList<Integer> data, VisualizerFrame frame) {
		this.data = data;
		this.frame = frame;
	}
	
	public void run() {
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
		Instant start = Instant.now();
		sort();
		Instant finish = Instant.now();
		long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
		long timeElapsed = Duration.between(start, finish).toMillis();
		long spaceComplexity = usedMemoryAfter - usedMemoryBefore;
		System.out.println("Insertion Sort:   Time = " + timeElapsed + " Space = " + spaceComplexity);
		frame.reDrawArray(data,-1,-1,-1);
		SortingVisualizer.isSorting=false;
	}
	
	public void sort() {
		for (int i = 0; i < data.size(); i++) {
			Integer temp = data.get(i);
			int j = i;
			while (j > 0 && data.get(j - 1).compareTo(temp) > 0) {
				data.set(j, data.get(j-1));
				j--;
				frame.reDrawArray(data, i, j, -1);
				try {
					Thread.sleep(SortingVisualizer.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			data.set(j, temp);
		}
	}
}
