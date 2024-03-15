import java.util.Arrays;

public class IntSorter {
    //numbers to sort
    int[] array;



    //Threads
    int threadscount;
    int numbperthread;
    ThreadCounter[] threadCounters;
    PairManager pairmanager;


    public IntSorter(int[] arr, int threadscount) {
        array = arr;
        this.threadscount = threadscount;
        threadCounters = new ThreadCounter[threadscount];
        numbperthread = array.length/threadscount;
    }

    //Create threads
    //Give them numbers to count
    int firstindex = 0;
    public void StartSorting() {
        for (int i = 0; i < threadscount; i++) {

            int[] arrtothread = Arrays.copyOfRange(array, firstindex, firstindex + numbperthread);
            firstindex += numbperthread;

            threadCounters[i] = new ThreadCounter(arrtothread);
            threadCounters[i].start();
            //Thread inner sort
            threadCounters[i].sortarray();
        }


        pairmanager = new PairManager();

        Sort();
    }

    //make pairs
    //every recurrence
    //decrease
    public void Sort(){
        //first of all create pairs
        pairmanager.makepairs(threadCounters);


    }

}
