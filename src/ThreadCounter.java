public class ThreadCounter extends Thread {
    int[] array;
    String name;
    public boolean finished = false;
    public boolean haspair = false;

    public boolean innersorting = false;


    ThreadCounter T1;
    ThreadCounter T2;

    ThreadCountPair currentthreadcountpair;


    MultiThreadProgressBar multithreadprogressbar;
    int delaytaskms = 0;
    int threadindex;

    ThreadCounter(int[] array, String name) {
        this.name = name;
        this.array = array;
    }


    ThreadCounter(int[] array, String name, MultiThreadProgressBar multithreadprogressbar,
                  int threadindex ,int delaytaskms) {
        this.name = name;
        this.array = array;
        this.multithreadprogressbar = multithreadprogressbar;
        this.delaytaskms = delaytaskms;
        this.threadindex = threadindex;
    }


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

        if(innersorting){
            sortarray();
            innersorting = false;
            return;
        }

        array = currentthreadcountpair.SortBetweenThreads();

        if(IntSorter.printcalculations) {
            for (int i = 0; i < array.length; i++) {
                System.out.println("name = " + name + " number = " + array[i]);
            }
        }

        finished = true;
    }

    //bubble sorting
    public void sortarray(){
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {

                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            float progress = (float)i/array.length;

            int progresss_int = (int) (progress * 100);

            multithreadprogressbar.ThreadIncreaseProgress(threadindex, progresss_int);

            try {
                sleep(delaytaskms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // If no two elements were
            // swapped by inner loop, then break
            if (!swapped){
                multithreadprogressbar.ThreadIncreaseProgress(threadindex, 100);
                break;
            }
        }

    }

}
