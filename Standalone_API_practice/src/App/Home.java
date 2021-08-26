package App;

import LogicalDocAPI.LogicalDOC_API;
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

import org.json.JSONException;

import java.text.MessageFormat;

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
    static JMenuItem uploadDoc, listFolder, listDoc, createFolder, getBookmarks, addBookmark;
    static JMenuItem testcurl;

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
        //******
        listDocInput.setVisible(false);
        submitFolderId.setVisible(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5,25,size-50,size-100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getBookmarks();
        addBookmarks();
        listFolders();
        createFolder();
        uploadDocument();
        listDocuments();
        addTestCurl();

        //**** Working ****
        menuBar.add(getBookmarks);
        menuBar.add(addBookmark);
        menuBar.add(listFolder);
        menuBar.add(createFolder);
        //***** end ****
        menuBar.add(uploadDoc);
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

    private void addBookmarks() {
        addBookmark = new JMenuItem("Add Bookmark");
        addBookmark.addActionListener(this);
    }

    // GET
    public void addTestCurl(){
        testcurl = new JMenuItem("curl API");
        testcurl.addActionListener(this);
    }
    public void getBookmarks(){
        getBookmarks = new JMenuItem("Get Bookmarks");
        getBookmarks.addActionListener(this);
    }
    public void listFolders(){
        listFolder = new JMenuItem("List Folders");
        listFolder.addActionListener(this);
    }
    public void listDocuments(){
        listDoc = new JMenuItem("List Documents");
        listDoc.addActionListener(this);
    }
    //POST
    public void uploadDocument(){
        uploadDoc = new JMenuItem("Open Document");
        uploadDoc.addActionListener(this);
    }
    public void createFolder(){
        createFolder = new JMenuItem("Create Folder");
        createFolder.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //**** working ****
        if (e.getSource()==testcurl){
            new curlAPI();
        }
        else if (e.getSource()== getBookmarks){
            try {
                showBookmarks(LogicalDOC_API.getBookmarks());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        /*else if (e.getSource()== addBookmark){
            // 105 = NathanFolder
            int folderId = 105;
            try {
                LogicalDOC_API.addBookmark();
                showBookmarkAdded(folderId);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }*/
        else if (e.getSource()== listFolder){
            try {
                showFolders(LogicalDOC_API.getFoldersArray());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        else if (e.getSource()== createFolder){
            String folderPATH = "NathanFolder2";
            /* in future create textfield and get folderPATH from user */
            try {
                LogicalDOC_API.createFolder(folderPATH);
                showFolderCreated(folderPATH);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        //**** end ****
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
        else if (e.getSource()== listDoc){
            textArea.setText("List Documents");
            //listDocInput.setVisible(true);
        }
    }

    private void showBookmarkAdded(int folderId) {
        textArea.setText(MessageFormat.format("{0} added to Bookmarks.",folderId));
    }
    private void showBookmarks(JSONArray bookmarksArray) throws JSONException {
        textArea.setText("Bookmarks [");
        for (int i=0; i<bookmarksArray.length(); i++){
            JSONObject bookmark = bookmarksArray.getJSONObject(i);
            textArea.append("\n\tName: "+bookmark.get("title")+" - ID: "+bookmark.get("id")+",");
        }
        textArea.append("\n]");
    }
    private void showFolders(JSONArray foldersArray) throws JSONException {
        textArea.setText("Folders [");
        for (int i=0; i< foldersArray.length(); i++){
            JSONObject folder = foldersArray.getJSONObject(i);
            textArea.append("\n\tName: "+folder.get("name")+" - ID: "+folder.get("id")+",");
        }
        textArea.append("\n]");
    }
    private void showFolderCreated(String folderPATH) {
        textArea.setText(MessageFormat.format("\t... Creating folder: /Default/{0} ...",folderPATH));
        textArea.append("\nFolder created successfully.");
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
