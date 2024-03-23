//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        int[] arr = new int[]
                {
                7,5,3,4, //T1
                4,2,2,3, //T2
                1,6,3, //T3
                5,4,2  //T4
                };
        IntSorter firstask = new IntSorter(arr, 14);
        firstask.StartSorting();


        long endTime = System.nanoTime();
        long durationNano = (endTime - startTime);

        double durationSec = (double)durationNano / 1_000_000_000;

        System.out.println("Complete time: " + durationSec + " seconds");
    }
}