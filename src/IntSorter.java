import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntSorter {
    //Threads
    int[] array;
    int threadscount;
    int[] numbpersthread;
    List<ThreadCounter> threadCounters;
    PairManager pairmanager;


    public IntSorter(int[] arr, int threadscount) {
        array = arr;
        this.threadscount = threadscount;
        threadCounters = new ArrayList<ThreadCounter>();
        numbpersthread = SetEqualArray(arr.length, threadscount);
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
    public void StartSorting() {
        for (int i = 0; i < threadscount; i++) {

            int[] arrtothread = Arrays.copyOfRange(array, firstindex, firstindex + numbpersthread[i]);
            firstindex += numbpersthread[i];

            threadCounters.add(new ThreadCounter(arrtothread, "Thread " + i));
            threadCounters.get(i).sortarray();

            for (int k = 0; k < arrtothread.length; k++) {
                System.out.println(threadCounters.get(i).name + " number = " + arrtothread[k]);
            }
        }


        pairmanager = new PairManager();

        Sort();
    }


    //How many times it was counted
    int iteracjaliczenia = 0;


    //make pairs
    //every recurrence
    //decrease number threads
    public void Sort(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Iteration = " + iteracjaliczenia);

        //first of all create pairs
        List<ThreadCountPair> threadsCountPair = pairmanager.makepairs(threadCounters);

        List<ThreadCounter> newthreadcounters = new ArrayList<ThreadCounter>();
        

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

        for (Thread thread : newthreadcounters) {
            try {
                thread.join();

            } catch (InterruptedException e){
                System.out.println("blad");
            }
        }


        //new threads
        threadCounters = new ArrayList<>(newthreadcounters);

        for (int i = 0; i < threadsCountPair.size(); i++) {
            for (int k = 0; k < threadCounters.get(i).array.length; k++) {
                System.out.println(threadCounters.get(i).name + " number = " + threadCounters.get(i).array[k]);
            }
        }


        iteracjaliczenia++;
        if(threadCounters.size() == 1) {
            System.out.println("The end");
            return;
        }

        Sort();
    }
}
