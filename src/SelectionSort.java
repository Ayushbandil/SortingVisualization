import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort implements Runnable{
	
	private ArrayList<Integer> data;
	private VisualizerFrame frame;
	private boolean fast;
	
	public SelectionSort(ArrayList<Integer> data, VisualizerFrame frame) {
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
		System.out.println("Selection Sort:   Time = " + timeElapsed + " Space = " + spaceComplexity);
		frame.reDrawArray(data,-1,-1,-1);
		SortingVisualizer.isSorting=false;
	}

	public void sort() {
		int selected = 0;
		for(int i = 0; i < data.size(); i++){
			selected = i;
			for(int j = data.size()-1; j>i; j--){

				if (data.get(j) <= data.get(selected)){
					selected = j;
				}
				frame.reDrawArray(data, selected, j-1, -1);
				try {
					Thread.sleep(SortingVisualizer.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Collections.swap(data, i, selected);
		}
	}
}
