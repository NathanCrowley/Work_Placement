package API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class JFrameCurl {

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

    public static void main(String[] args) {

        JFrame frame = new JFrame("API Test Curl");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(300,300);
        frame.setSize(300,300);

        final JTextArea apiResultBox = new JTextArea(10,40);
        frame.getContentPane().add(BorderLayout.CENTER, apiResultBox);

        apiResultBox.append("Enter URL for API call :");
        final JTextField inputField = new JTextField(10);
        frame.getContentPane().add(BorderLayout.NORTH,inputField);


        final JButton apiButton = new JButton("API call");
        frame.getContentPane().add(BorderLayout.SOUTH, apiButton);
        apiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String test = "Hello\n";
                try {
                    apiResultBox.setText(null);
                    //String apiResponse = getAPIresult("https://jsonplaceholder.typicode.com/posts/1");
                    String apiResponse = getAPIresult(inputField.getText());

                    apiResultBox.append(apiResponse);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        frame.setVisible(true);

    }
}
