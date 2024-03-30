import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultiThreadProgressBar extends JFrame {
    private final JProgressBar[] progressBars;
    private final int numThreads;

    MultiThreadProgressBar(int numThreads) {

        this.numThreads = numThreads;

        setTitle("Multi-Thread Progress Bar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numThreads, 1));
        progressBars = new JProgressBar[numThreads];

        for (int i = 0; i < numThreads; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setValue(0);
            progressBars[i].setStringPainted(true);
            panel.add(progressBars[i]);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void ThreadIncreaseProgress(int threadIndex, int progress){
        progressBars[threadIndex].setValue(progress);
    }
}
