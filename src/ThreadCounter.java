public class ThreadCounter extends Thread {
    int[] array;
    String name;
     public boolean finished = false;
    public boolean haspair = false;
    ThreadCounter(int[] array, String name) {
        this.name = name;
        this.array = array;
    }

    @Override
    public void run() {
        System.out.println(name + " Thread is running");
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
