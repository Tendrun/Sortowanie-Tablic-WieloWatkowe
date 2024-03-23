import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntSorter {
    //numbers to sort
    int[] array;
    //Threads
    int threadscount;
    int[] numbperthread;
    List<ThreadCounter> threadCounters;
    PairManager pairmanager;


    public IntSorter(int[] arr, int threadscount) {
        array = arr;
        this.threadscount = threadscount;
        threadCounters = new ArrayList<ThreadCounter>();
        numbperthread = new int[threadscount];
                //array.length/threadscount;
    }

    //Create threads
    //Give them numbers to count
    int firstindex = 0;
    public void StartSorting() {
        for (int i = 0; i < threadscount; i++) {

            int[] arrtothread = Arrays.copyOfRange(array, firstindex, firstindex + numbperthread[i]);
            firstindex += numbperthread[i];

            threadCounters.add(new ThreadCounter(arrtothread, "Thread " + i));
            threadCounters.get(i).start();


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

        List<ThreadCounter> newthreadCounters = new ArrayList<ThreadCounter>();


        //Thread inner sort
        for (int i = 0; i < threadCounters.size(); i++){
            threadCounters.get(i).sortarray();
        }

        //sort between threads
        for (int i = 0; i < threadsCountPair.size(); i++) {


            //if no pair skip
            if (threadsCountPair.get(i).thread2 == null) {
                threadsCountPair.get(i).thread1.haspair = false;
                newthreadCounters.add(threadsCountPair.get(i).thread1);
                continue;
            }

            //if has pair
            ThreadCounter newthread = SortBetweenThreads(threadsCountPair.get(i).thread1, threadsCountPair.get(i).thread2);
            threadsCountPair.get(i).thread1.interrupt();
            threadsCountPair.get(i).thread2.interrupt();


            newthread.haspair = false;
            newthreadCounters.add(newthread);



        }

        //new threads
        threadCounters = new ArrayList<>(newthreadCounters);

        for (int i = 0; i < threadsCountPair.size(); i++) {
            for (int k = 0; k < threadCounters.get(i).array.length; k++) {
                System.out.println(threadCounters.get(i).name + " number = " + threadCounters.get(i).array[k]);
            }
        }


        iteracjaliczenia++;
        if(threadCounters.size() == 1){
            System.out.println("The end");
            return;
        }

        Sort();
    }

    //It counts between two threads
    //and returns new ThreadCounter
    ThreadCounter SortBetweenThreads(ThreadCounter T1, ThreadCounter T2) {


        int[] array = new int[T1.array.length + T2.array.length];

        int T1Pos = 0;
        int T2Pos = 0;
        int arrpos = 0;

        ThreadCounter NewThreadCounter = new ThreadCounter(array, ("("+ T1.name + " " + T2.name + ")"));

        while (true){



            //compare two numbers
            //when smaller
            //when is out array length
            //when is out numbers
            if(T1.array[T1Pos] <= T2.array[T2Pos]) {
                array[arrpos] = T1.array[T1Pos];
                T1Pos++;
            }

            else if(T1.array[T1Pos] > T2.array[T2Pos]) {
                array[arrpos] = T2.array[T2Pos];
                T2Pos++;
            }


            ++arrpos;


            //when one array is at the end
            //copy second
            if(T1Pos == T1.array.length) {
                for (int i = arrpos; i < array.length; i++) {
                    array[arrpos] = T2.array[T2Pos];

                    arrpos++;
                    T2Pos++;
                }

                return new ThreadCounter(array, ("("+ T1.name + " " + T2.name + ")"));
            }

            else if (T2Pos == T2.array.length) {

                for (int i = arrpos; i < array.length; i++) {
                    array[arrpos] = T1.array[T1Pos];

                    arrpos++;
                    T1Pos++;;
                }

                return new ThreadCounter(array, ("("+ T1.name + " " + T2.name + ")"));
            }

        }
    }


}
