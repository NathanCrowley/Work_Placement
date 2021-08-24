package App;

import LogicalDocAPI_Testing.CommandGETResponse;
import LogicalDocAPI_Testing.test_LogicalDOC_API.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.print.PrinterGraphics;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;

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
    static JTextField listDocInput;
    static JButton submitFolderId;
    static JScrollPane scrollPane;
    static JMenuBar menuBar;
    static JMenuItem uploadDoc, listFolder, listDoc;
    static JMenuItem testcurl;
    static JMenuItem bookmarks;

    int size = 700;
    int location = 400;

    Home(){
        frame = new JFrame("Home");
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        listDocInput = new JTextField("Enter FolderID....");
        listDocInput.addFocusListener(new curlAPI.MyFocusListener());
        submitFolderId = new JButton("Submit");
        submitFolderId.setBounds(size-100,0,80,20);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5,25,size-50,size-100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        uploadDocument();
        listFolders();
        listDocuments();
        addTestCurl();
        getBookmarks();

        menuBar.add(uploadDoc);
        menuBar.add(bookmarks);
        menuBar.add(listFolder);
        menuBar.add(listDoc);
        menuBar.add(testcurl);

        frame.add(submitFolderId);
        frame.getContentPane().add(BorderLayout.NORTH,listDocInput);
        frame.add(scrollPane);
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
        frame.setSize(size,size);
        frame.setLocation(location,location);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void getBookmarks(){
        bookmarks = new JMenuItem("Get Bookmarks");
        bookmarks.addActionListener(this);
    }
    public void uploadDocument(){
        uploadDoc = new JMenuItem("Upload Document");
        uploadDoc.addActionListener(this);
    }
    public void listFolders(){
        listFolder = new JMenuItem("List Folders");
        listFolder.addActionListener(this);
    }
    public void listDocuments(){
        listDoc = new JMenuItem("List Documents");
        listDoc.addActionListener(this);
    }
    public void addTestCurl(){
        testcurl = new JMenuItem("curl API");
        testcurl.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==testcurl){
            new curlAPI();
        }
        else if (e.getSource()== bookmarks){
            //textArea.setText("GET Bookmarks");
            String url = "http://localhost:8081/services/rest/bookmark/getBookmarks\n";
            String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

            CommandGETResponse commandGETResponse = new CommandGETResponse();
            String response = null;
            try {
                response = commandGETResponse.getCommandResponse(command);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            JSONArray arr = null;
            try {
                arr = new JSONArray(response);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            textArea.setText("Bookmarks [");
            for (int i=0; i<arr.length();i++) {
                JSONObject item = null;
                try {
                    item = arr.getJSONObject(i);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                try {
                    textArea.append("\n\tName: "+item.get("title")+" - ID: "+item.get("id")+",");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            textArea.append("\n]");
        }
        else if (e.getSource()== uploadDoc){
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
        }
        else if (e.getSource()== listFolder){
            //textArea.setText("List Folders");
            String url = "http://localhost:8081/services/rest/folder/listChildren?folderId=4";
            String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

            CommandGETResponse commandGETResponse = new CommandGETResponse();
            String response = null;
            try {
                response = commandGETResponse.getCommandResponse(command);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            JSONArray arr = null;
            try {
                arr = new JSONArray(response);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            textArea.setText("Folders [");
            for (int i=0; i<arr.length();i++) {
                JSONObject item = null;
                try {
                    item = arr.getJSONObject(i);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                try {
                    textArea.append("\n\tName: "+item.get("name")+" - ID: "+item.get("id")+",");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            textArea.append("\n]");
        }
        else if (e.getSource()== listDoc){
            textArea.setText("List Documents");
            //listDocInput.setVisible(true);
        }
    }

    //clears the inputField when a user types the url
    static class MyFocusListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent fEvt){
            JTextComponent component = (JTextComponent) fEvt.getSource();
            component.selectAll();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
