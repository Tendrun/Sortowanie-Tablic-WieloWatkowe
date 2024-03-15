public class PairManager {
    ThreadCountPair[] threadsCountPair;

    public void makepairs(ThreadCounter[] threadCounters) {

        threadsCountPair = new ThreadCountPair[(int)Math.ceil(threadCounters.length / 2.0)];

        for(int i = 0; i < threadCounters.length; i++){
            if(i + 1 < threadCounters.length){
                threadsCountPair[i] = new ThreadCountPair();
                threadsCountPair[i].thread1 = threadCounters[i];
                threadsCountPair[i].thread2 = threadCounters[i];
            }
        }
    }

    public void compare(int T1, int T2, int x){

    }
}