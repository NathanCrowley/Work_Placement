package JFrameExample;

import javax.swing.*;

/*
JEditorPane class is used to create simple text editor window.
This class has 'setContentType()' and 'setText()' methods.
    - setContentType("text/plain"): method used to set the content type to be plain text.
    - setText(text): method is used to set initial text content.
 */

public class JEditorPaneExample {
    JFrame myFrame = null;

    public static void main(String[] args) {
        //(new JEditorPaneExample()).testPlainText();
        (new JEditorPaneExample()).testHTML();
    }

    private void testPlainText(){
        myFrame = new JFrame("JEditorPane Test");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400,200);

        JEditorPane myPane = new JEditorPane();
        myPane.setContentType("text/plain");
        myPane.setText("Sleeping is necessary for healthy body."
            +"But sleeping in unnecessary times may spoil our health,wealth and studies."
            +"Doctors advise that the sleeping at improper timings may lead for obestity during student days.");
        myFrame.setContentPane(myPane);
        myFrame.setVisible(true);
    }

    private void testHTML(){
        myFrame = new JFrame("JEditorPane Test");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400,200);

        JEditorPane myPane = new JEditorPane();
        myPane.setContentType("text/html");
        myPane.setText("<h1>Sleeping</h1>"
            +"<p>Sleeping is necessary for a healthy body."
                +" But sleeping in unnecessary times may spoil our health, wealth and studies."
                +" Doctors advise that the sleeping at improper timings may lead for obesity during the students days." +
                "</p>");
        myFrame.setContentPane(myPane);
        myFrame.setVisible(true);
    }
}
