package App;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/*
Class to use JFrame to create a GUI that allows a user to enter a API endpoint.
    Then the response of the endpoint is displayed in a text area.
 */

public class curlAPI {

    public static String getAPIresult(String url) throws IOException {
        String command = "curl "+url;
        Process process = Runtime.getRuntime().exec(command);
        process.getInputStream();

        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);

        for (int numRead; (numRead = in.read(buffer,0,buffer.length))>0;){
            out.append(buffer,0,numRead);
        }

        String response = out.toString();
        return response;
    }

    public curlAPI(){
        JFrame frame = new JFrame("API Test Curl");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(300,300);
        frame.setSize(400,500);

        final JTextArea apiResultBox = new JTextArea();
        apiResultBox.setText("Output of curl+URL command....");

        final JScrollPane scrollTextArea = new JScrollPane(apiResultBox);
        scrollTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        frame.getContentPane().add(BorderLayout.CENTER, scrollTextArea);

        final JTextField inputField = new JTextField("Enter URL for API call :");
        inputField.addFocusListener(new MyFocusListener());
        frame.getContentPane().add(BorderLayout.NORTH,inputField);

        final JButton apiButton = new JButton("API call");
        frame.getContentPane().add(BorderLayout.SOUTH, apiButton);
        frame.getRootPane().setDefaultButton(apiButton);

        apiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*
                        - clear the response box
                        - get the response
                        - print response
                        - start from top of response
                     */
                    apiResultBox.setText(null);
                    String apiResponse = getAPIresult(inputField.getText());
                    apiResultBox.append(apiResponse);
                    apiResultBox.setCaretPosition(0);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new curlAPI();
    }

    //clears the inputField when a user types the url
    static class MyFocusListener extends FocusAdapter{
        @Override
        public void focusGained(FocusEvent fEvt){
            JTextComponent component = (JTextComponent) fEvt.getSource();
            component.selectAll();
        }
    }
}
