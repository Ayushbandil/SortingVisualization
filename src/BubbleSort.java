import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class BubbleSort implements Runnable{

	private ArrayList<Integer> data;
	private VisualizerFrame frame;

	public BubbleSort(ArrayList<Integer> data, VisualizerFrame frame) {
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
		System.out.println("Bubble Sort:   Time = " + timeElapsed + " Space = " + spaceComplexity);
		frame.reDrawArray(data,-1,-1,-1);
		SortingVisualizer.isSorting=false;
	}

	public void sort() {
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = 1; j < data.size(); j++) {
				if (data.get(j).compareTo(data.get(j - 1)) < 0) {
					Collections.swap(data, j, j-1);
					frame.reDrawArray(data, j, j+1, -1);
					try {
						Thread.sleep(SortingVisualizer.sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
