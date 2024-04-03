import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Above size 1000 it is not sorting correctly
        int[] arr = new int[10000];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            // Generate a random integer
            // between INT_MIN and INT_MAX
            arr[i] = random.nextInt(100);
        }

        long startTime = System.nanoTime();


        IntSorter firstask = new IntSorter(arr, 4, false, 1);
        List<ThreadCounter> threadCounters = firstask.StartSorting();
        firstask.Sort(threadCounters);


        long endTime = System.nanoTime();
        long durationNano = (endTime - startTime);

        double durationSec = (double)durationNano / 1_000_000_000;

        System.out.println("Complete time: " + durationSec + " seconds");
    }
}