public class ThreadCounter extends Thread {
    int[] array;
    String name;
     public boolean finished = false;
    public boolean haspair = false;

    ThreadCounter T1;
    ThreadCounter T2;

    ThreadCountPair currentthreadcountpair;

    ThreadCounter(int[] array, String name, ThreadCountPair currentthreadcountpair) {
        this.name = name;
        this.array = array;
        this.currentthreadcountpair = currentthreadcountpair;
    }

    ThreadCounter(int[] array, String name, ThreadCounter T1, ThreadCounter T2) {
        this.name = name;
        this.array = array;

        this.T1 = T1;
        this.T2 = T2;
    }

    @Override
    public void run() {
        currentthreadcountpair.SortBetweenThreads();
    }

    //bubble sorting
    public void sortarray(){
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }

}
