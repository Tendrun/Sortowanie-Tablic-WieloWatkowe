public class ThreadCounter extends Thread {
    public int[] array;
    String name;
    public boolean haspair = false;
    ThreadCountPair currentthreadcountpair;

    ThreadCounter(int[] array, String name) {
        this.name = name;
        this.array = array;
    }


    @Override
    public void run() {
        array = currentthreadcountpair.SortBetweenThreads();

        if(IntSorter.printcalculations) {
            for (int i = 0; i < array.length; i++) {
                System.out.println("name = " + name + " number = " + array[i]);
            }
        }

    }

}
