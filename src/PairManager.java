import java.util.ArrayList;
import java.util.List;

public class PairManager {

    public List<ThreadCountPair> makepairs(List<ThreadCounter> threadCounters) {

        List<ThreadCountPair> threadsCountPair = new ArrayList<ThreadCountPair>();

        for(int i = 0; i < threadCounters.size(); i++) {
            if (threadCounters.get(i).haspair)
                continue;


            ThreadCounter currentthread = threadCounters.get(i);
            //Find thread with no pair
            while(true) {

                //find if thread is in range
                if(!(i + 1 < threadCounters.size())) break;

                ++i;
                ThreadCounter nextthread = threadCounters.get(i);

                //if has pair go next
                if(nextthread.haspair) continue;

                //if doesnt create pair and break while
                else {
                    ThreadCounter childthread =
                            new ThreadCounter(null, "("+ currentthread.name + " " + nextthread.name + ")");

                    ThreadCountPair threadpair = new ThreadCountPair(currentthread, nextthread, childthread);
                    childthread.currentthreadcountpair = threadpair;

                    threadsCountPair.add(threadpair);
                    threadpair.thread1 = currentthread;
                    threadpair.thread2 = nextthread;

                    currentthread.haspair = true;
                    nextthread.haspair = true;

                    break;
                }

            }

            for(int j = 0; j < threadCounters.size(); j++) {
                if(threadCounters.get(i).haspair) continue;
                ThreadCounter thr = threadCounters.get(i);
                ThreadCountPair threadpair = new ThreadCountPair(thr, null,null);
                thr.haspair = true;
                threadsCountPair.add(threadpair);
            }

        }

        return threadsCountPair;
    }
}