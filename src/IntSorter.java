import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class IntSorter {

    //UI
    MultiThreadProgressBar multithreadprogressbar;
    int delaytaskms;

    //Threads
    int[] array;
    int threadscount;
    int[] numbpersthread;
    PairManager pairmanager;

    static boolean printcalculations;

    public IntSorter(int[] arr, int threadscount, boolean printcalculations_, int delaytaskms) {
        array = arr;
        this.threadscount = threadscount;
        numbpersthread = SetEqualArray(arr.length, threadscount);
        printcalculations = printcalculations_;
        this.delaytaskms = delaytaskms;
    }

    //sort thread equally per thread
    int[] SetEqualArray(int NumbersCount, int ThreadCount) {

        int Equal = NumbersCount / ThreadCount;
        int rest = NumbersCount % ThreadCount;
        int[] array = new int[ThreadCount];

        for (int i = 0; i < array.length; i++) {
            array[i] = Equal;

            if(rest > 0) {
                array[i]++;
                rest--;
            }
        }

        return array;
    }

    //Create threads
    //Give them numbers to count
    int firstindex = 0;
    public List<ThreadCounter> StartSorting() {

        //create UI
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    MultiThreadProgressBar frame = new MultiThreadProgressBar(threadscount);
                    multithreadprogressbar = frame;
                    frame.setVisible(true);
                }
            });
        }
        catch (InterruptedException e) {
            e.printStackTrace(System.out);
        } catch (InvocationTargetException e) {
            e.printStackTrace(System.out);
        }


        List<BubbleSortingThread> bubbleSortingThreads = new ArrayList<BubbleSortingThread>();


        //Inner sorting
        for (int i = 0; i < threadscount; i++) {

            int[] arrtothread = Arrays.copyOfRange(array, firstindex, firstindex + numbpersthread[i]);
            firstindex += numbpersthread[i];

            bubbleSortingThreads.add(new BubbleSortingThread(arrtothread, "Thread " + i, multithreadprogressbar, i, delaytaskms));
            bubbleSortingThreads.get(i).start();
        }

        for (BubbleSortingThread thread : bubbleSortingThreads) {
            try {
                thread.join();

            } catch (InterruptedException e){
                System.out.println("error");
            }
        }

        List<ThreadCounter> threadCounters = new ArrayList<ThreadCounter>();

        for (int i = 0; i < threadscount; i++) {
            threadCounters.add(new ThreadCounter(bubbleSortingThreads.get(i).array, "Thread " + i));
        }

        pairmanager = new PairManager();

        return threadCounters;
    }


    //How many times it was counted
    int iteracjaliczenia = 0;


    //make pairs
    //every recurrence
    //decrease number threads
    public void Sort(List<ThreadCounter> threadCounters){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Iteration = " + iteracjaliczenia);

        //first of all create pairs
        List<ThreadCountPair> threadsCountPair = pairmanager.makepairs(threadCounters);

        List<ThreadCounter> newthreadcounters = new ArrayList<>();

        

        //sort between threads
        for (int i = 0; i < threadsCountPair.size(); i++) {


            //if no pair skip
            if (threadsCountPair.get(i).thread2 == null) {
                threadsCountPair.get(i).thread1.haspair = false;
                newthreadcounters.add(threadsCountPair.get(i).thread1);
                continue;
            }

            //if has pair
            threadsCountPair.get(i).newthread.start();
            threadsCountPair.get(i).thread1.interrupt();
            threadsCountPair.get(i).thread2.interrupt();


            threadsCountPair.get(i).newthread.haspair = false;
            newthreadcounters.add(threadsCountPair.get(i).newthread);



        }

        //Wait for others threads to finnish job

        for (Thread thread : newthreadcounters) {
            try {
                thread.join();

            } catch (InterruptedException e){
                System.out.println("error");
            }
        }


        //new threads
        threadCounters = new ArrayList<>(newthreadcounters);

        if(printcalculations) {
            for (int i = 0; i < threadsCountPair.size(); i++) {
                for (int k = 0; k < threadCounters.get(i).array.length; k++) {
                    System.out.println(threadCounters.get(i).name + " number = " + threadCounters.get(i).array[k]);
                }
            }
        }

        iteracjaliczenia++;
        if(threadCounters.size() == 1) {
            System.out.println("The end");

            array = newthreadcounters.get(0).array;

            for (int i = 0; i < array.length; i++) {
                System.out.println("number = " + array[i]);
            }

            return;
        }

        Sort(newthreadcounters);
    }
}
