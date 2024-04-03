public class BubbleSortingThread extends Thread{

    int[] array;
    String name;
    MultiThreadProgressBar multithreadprogressbar;
    int delaytaskms = 0;
    int threadindex;


    BubbleSortingThread(int[] array, String name, MultiThreadProgressBar multithreadprogressbar,
                  int threadindex ,int delaytaskms) {
        this.name = name;
        this.array = array;
        this.multithreadprogressbar = multithreadprogressbar;
        this.delaytaskms = delaytaskms;
        this.threadindex = threadindex;
    }

    @Override
    public void run() {
        sortarray();
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
                break;
            }
        }
        multithreadprogressbar.ThreadIncreaseProgress(threadindex, 100);
    }
}
