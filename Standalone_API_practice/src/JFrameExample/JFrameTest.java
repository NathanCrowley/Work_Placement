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
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        f.getContentPane().add(scrollPane);

        final JButton button = new JButton("Click me");
        f.getContentPane().add(BorderLayout.SOUTH, button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Button was clicked - "+ x[0] +"\n");
                x[0] +=1;
            }
        });

        // Hides and shows text area
        final JButton hideButton = new JButton("hide/show");
        f.getContentPane().add(BorderLayout.NORTH,hideButton);
        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setVisible(!textArea.isVisible());
            }
        });

        f.setVisible(true);

    }
}
