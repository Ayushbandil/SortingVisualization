import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class QuickSort implements Runnable{

    private ArrayList<Integer> data;
    private VisualizerFrame frame;

    public QuickSort(ArrayList<Integer> data, VisualizerFrame frame) {
        this.data = data;
        this.frame = frame;
    }

    public void run() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        Instant start = Instant.now();
        sort(0, data.size() - 1);
        Instant finish = Instant.now();
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long timeElapsed = Duration.between(start, finish).toMillis();
        long spaceComplexity = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Quick Sort:   Time = " + timeElapsed + " Space = " + spaceComplexity);
        frame.reDrawArray(data,-1,-1,-1);
        SortingVisualizer.isSorting=false;
    }

    private void sort(int low, int high) {
        int i = low;
        int j = high;

        int pivot = data.get(low + (high - low) / 2);

        while (i <= j) {
            while (data.get(i) < pivot) {
                i++;
            }
            while (data.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                Collections.swap(data, i, j);
                i++;
                j--;
            }

            frame.reDrawArray(data, pivot, j, i);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (low < j) {
            sort(low, j);
        }

        if (i < high) {
            sort(i, high);
        }
    }
}
