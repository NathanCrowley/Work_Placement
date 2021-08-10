package JFrameExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class JFrameTest {

    public static void main(String[] args) {

        final int[] x = {1};

        JFrame f = new JFrame("A JFrame");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setSize(250,250);
        f.setLocation(300,200);

        final JTextArea textArea = new JTextArea(10,40);
        f.getContentPane().add(BorderLayout.CENTER,textArea);

        final JButton button = new JButton("Click me");
        f.getContentPane().add(BorderLayout.SOUTH, button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Button was clicked - "+ x[0] +"\n");
                x[0] +=1;
            }
        });

        f.setVisible(true);

    }
}
