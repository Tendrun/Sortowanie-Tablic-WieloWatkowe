import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Above size 1000 it is not sorting correctly
        int[] arr = new int[2000];

        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            // Generate a random integer
            // between INT_MIN and INT_MAX
            arr[i] = random.nextInt(100);
        }

        long startTime = System.nanoTime();
        IntSorter firstask = new IntSorter(arr, 4, false);

        firstask.StartSorting();

        long endTime = System.nanoTime();
        long durationNano = (endTime - startTime);

        double durationSec = (double)durationNano / 1_000_000_000;

        System.out.println("Complete time: " + durationSec + " seconds");
    }
}