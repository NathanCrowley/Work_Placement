package JFrameExample;

import javax.swing.*;
import java.awt.*;

/*
JLayeredPane class used to add depth to swing container.
It is used to provide a third dimension for positioning component and divide the depth-range into several different layers.
 */

public class JLayeredPaneExample extends JFrame {

    public JLayeredPaneExample(){
        super("LayeredPane Example");
        setSize(200,200);
        JLayeredPane pane = getLayeredPane();

        //creating buttons
        JButton top = new JButton();
        top.setBackground(Color.white);
        top.setBounds(20,20,50,50);

        JButton middle = new JButton();
        middle.setBackground(Color.red);
        middle.setBounds(40,40,50,50);

        JButton bottom = new JButton();
        bottom.setBackground(Color.cyan);
        bottom.setBounds(60,60,50,50);

        //adding buttons to pane
        pane.add(bottom, Integer.valueOf(1));
        pane.add(middle, Integer.valueOf(2));
        pane.add(top, Integer.valueOf(3));

    }

    public static void main(String[] args) {
        JLayeredPaneExample panel = new JLayeredPaneExample();
        panel.setLocation(300,300);
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);

    }

}
