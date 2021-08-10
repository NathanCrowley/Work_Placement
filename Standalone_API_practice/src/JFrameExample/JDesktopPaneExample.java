package JFrameExample;

import javax.swing.*;
import java.awt.*;

/*
The JDesktopPane class, can be used to create 'multi-document' applications.
A multi-document app can have many windows included in it.
We do it by making the 'contentPane' in the main window as an instance of the JDestopPane class or subclass.
Internal windows add instances of JInternalFrame to the JDesktopPane instance.
The internal windows are the instances of JInternalFrame or subclass.
 */

public class JDesktopPaneExample extends JFrame {

    public JDesktopPaneExample(){

        CustomDesktopPane desktopPane = new CustomDesktopPane();
        Container contentPane = getContentPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);
        desktopPane.display(desktopPane);

        setTitle("JDesktopPane Example");
        setSize(500,500);
        setLocation(300,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JDesktopPaneExample();
    }

    class CustomDesktopPane extends JDesktopPane{

        int numFrames = 3,x = 30, y = 30;

        public void display(CustomDesktopPane dp){
            for (int i = 0;i < numFrames; i++){
                JInternalFrame jframe = new JInternalFrame("Internal Frame "+i, true,true,true,true);

                jframe.setBounds(x,y,250,85);

                Container c1 = jframe.getContentPane();
                c1.add(new JLabel("I love my country"));
                dp.add(jframe);

                jframe.setVisible(true);

                y+= 85;
            }
        }

    }

}
