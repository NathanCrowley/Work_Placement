package JFrameExample;

/*
Used to make scrollable view of a component.
When screen size is limited, we use a scroll pane to display a large component or
    a component whose size can change dynamically.
 */

import javax.swing.*;
import java.awt.*;

public class JScrollPaneExample {
    private static final long serialVersionUID = 1L;

    private static void createAndShowGUI(){
        //create and set up window
        final JFrame frame = new JFrame("Scroll Pane Example");

        //Display the window
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set flow layout for frame
        frame.getContentPane().setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(20,20);
        JScrollPane scrollTextArea = new JScrollPane(textArea);

        scrollTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.getContentPane().add(scrollTextArea);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
