public class ThreadCountPair {

    ThreadCountPair(ThreadCounter thread1, ThreadCounter thread2, ThreadCounter newthread){
        this.thread1 = thread1;
        this.thread2 = thread2;
        this.newthread = newthread;
    }

    public ThreadCounter thread1;
    public ThreadCounter thread2;
    public ThreadCounter newthread;

    //It counts between two threads
    //and returns new ThreadCounter
    int[] SortBetweenThreads() {


        int[] array = new int[thread1.array.length + thread2.array.length];

        int thread1Pos = 0;
        int thread2Pos = 0;
        int arrpos = 0;

        //ThreadCounter NewThreadCounter = new ThreadCounter(array, ("("+ thread1.name + " " + thread2.name + ")"), );

        while (true){
            //compare two numbers
            //when smaller
            //when is out array length
            //when is out numbers
            if(thread1.array[thread1Pos] <= thread2.array[thread2Pos]) {
                array[arrpos] = thread1.array[thread1Pos];
                thread1Pos++;
            }

            else if(thread1.array[thread1Pos] > thread2.array[thread2Pos]) {
                array[arrpos] = thread2.array[thread2Pos];
                thread2Pos++;
            }


            ++arrpos;


            //when one array is at the end
            //copy second
            if(thread1Pos == thread1.array.length) {
                for (int i = arrpos; i < array.length; i++) {
                    array[arrpos] = thread2.array[thread2Pos];

                    arrpos++;
                    thread2Pos++;
                }

                return array;
            }

            else if (thread2Pos == thread2.array.length) {

                for (int i = arrpos; i < array.length; i++) {
                    array[arrpos] = thread1.array[thread1Pos];

                    arrpos++;
                    thread1Pos++;;
                }

                return array;
            }
        }
    }
}
