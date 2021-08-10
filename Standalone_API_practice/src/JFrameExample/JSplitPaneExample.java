package JFrameExample;

/*
Used to divide two components.
The two components are divided based on the look and feel implementation, and they can be resized by user.
If the minimum size of the two components is greater than the size of split pane, the divider will not allow you to resize it.

The two components in a split pane can be aligned left to right using 'JSplitPane.HORIZONTAL_SPLIT'
or top to bottom using JSplitPane.VERTICAL_SPLIT.
When the user is resizing the components the min size of componets is used to determine the max/min position the components can be set to.
 */

import javax.swing.*;
import java.awt.*;

public class JSplitPaneExample {

    private static void createAndShow(){
        //create and set up window
        final JFrame frame = new JFrame("JSplitPane Example");

        //display the window
        frame.setSize(300,300);
        frame.setLocation(300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set flow layout for the frame
        frame.getContentPane().setLayout(new FlowLayout());
        String[] option1 = {"A","B","C","D","E"};
        JComboBox box1 = new JComboBox(option1);
        String[] option2 = {"1","2","3","4","5"};
        JComboBox box2 = new JComboBox(option2);

        Panel panel1 = new Panel();
        panel1.add(box1);
        Panel panel2 = new Panel();
        panel1.add(box2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1,panel2);

        frame.getContentPane().add(splitPane);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShow();
            }
        });
    }
}
