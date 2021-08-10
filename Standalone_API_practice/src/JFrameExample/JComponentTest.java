package JFrameExample;

import javax.swing.*;
import java.awt.*;

/*
JComponent class is the base class of ALL Swing components except top-level containers.
Swing components whos name begin with "J" are descendants of the JComponent class.
    eg. JButton / JScrollPane / JPanel/ JTable.
But JFrame and JDialog dont inherit JComponent class because they are child of top-level containers.

The JComponent class extends extends the Container class which itself extends Component.
The Container class has support for adding components to the container.
 */

class MyJComponent extends JComponent{
    public void paint(Graphics g){
        g.setColor(Color.green);
        g.fillRect(30,30,100,100);
    }
}

public class JComponentTest {
    public static void main(String[] args) {

        MyJComponent com = new MyJComponent();

        //create basic JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("JComponent Example");

        frame.setSize(300,200);
        frame.setLocation(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add component to main frame
        frame.add(com);
        frame.setVisible(true);

    }
}
