package App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import App.curlAPI.*;

/*
Package App to hold all JFrame frames of the entire app.
    Home:
        - JFrame page that has links to all other pages.
        1) Open and store documents
        2) Retrieve documents
 */

public class Home extends JFrame implements ActionListener {

    static JFrame frame;
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JMenuBar menuBar;
    static JMenu file;
    static JMenuItem open,store,retrieve;
    //***
    static JMenuItem testcurl;

    int size = 800;
    int location = 400;

    Home(){
        frame = new JFrame("Home");
        menuBar = new JMenuBar();
        textArea = new JTextArea();
        file = new JMenu("File");

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5,5,size-50,size-70);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        addOpenTab();
        addStoreTab();
        addRetrieveTab();
        //***
        addTestCurl();

        file.add(open);
        file.add(store);
        file.add(retrieve);
        //***
        file.add(testcurl);

        menuBar.add(file);

        frame.add(scrollPane);
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
        frame.setSize(size,size);
        frame.setLocation(location,location);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addOpenTab(){
        open = new JMenuItem("Open");
        open.addActionListener(this);
    }
    public void addStoreTab(){
        store = new JMenuItem("Store");
        store.addActionListener(this);
    }
    public void addRetrieveTab(){
        retrieve = new JMenuItem("Retrieve");
        retrieve.addActionListener(this);
    }
    //***
    public void addTestCurl(){
        testcurl = new JMenuItem("curl API");
        testcurl.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==testcurl){
            new curlAPI();
        }
        else if (e.getSource()==open){
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(this);
            if (i==JFileChooser.APPROVE_OPTION){
                File f = fc.getSelectedFile();
                String filepath = f.getPath();

                try{
                    BufferedReader br = new BufferedReader(new FileReader(filepath));
                    String s1="",s2="";
                    while((s1=br.readLine()) != null){
                        s2 += s1+"\n";
                    }
                    textArea.setText(s2);
                    textArea.setCaretPosition(0);
                    br.close();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }else if (e.getSource()==store){
            textArea.setText("Store button pressed");
        }else if (e.getSource()==retrieve){
            textArea.setText("Retrieve button pressed");
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
