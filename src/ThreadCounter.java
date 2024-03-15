public class ThreadCounter extends Thread {

    public Boolean haspair = false;
    int[] array;
    ThreadCounter(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
        for (int i = 0; i < array.length ; i++){
            System.out.println(currentThread().getName() + " number = " + array[i]);
        }
    }

    void setpair(Boolean haspair){
        this.haspair = haspair;
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
            if (swapped == false)
                break;
        }


        for (int k = 0; k < array.length; k++) {
            System.out.println(currentThread().getName() + " number = " + array[k]);
        }
    }

}
