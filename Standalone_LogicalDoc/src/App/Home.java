package App;

import App.LogicalDocAPI.LogicalDOC_API;
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
    Home Class:
        - initialize JFrame components.
        - initialize variables.
        - Home method():
            - create JFrame components
            - create welcome message
            - Create input textField and submit button
            - create scrollPane for textArea
            - add functions to menuBar
            - add components to frame
*/

public class Home extends JFrame implements ActionListener {

    static JFrame frame;
    static JTextArea textArea;
    static JTextField listDocInput, createFolderInput, createListDocInput;
    static JButton submitFolderInput,submitListDocInput;
    static JScrollPane scrollPane;
    static JMenuBar menuBar;
    static JMenuItem uploadDoc, listFolder, listDoc, createFolder, getBookmarks, deleteFolder ,addBookmark;

    int size = 700;
    int location = 400;
    int folderInputLocationY = 5;
    int folderInputLocationHeight = 20;
    int folderSubmitWidth = 80;

    String folderPATHSearchBar = "Folder-PATH to create....";
    String folderIdSearchBar = "Folder-ID to list documents....";

    Home(){
        frame = new JFrame("Home");
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        Font welcomeFont = new Font("Calibri", Font.BOLD, 11);
        textArea.setFont(welcomeFont);
        textArea.setText("\n\n\n\tLogicalDoc Proof of Concept\n");
        textArea.append("\tby Nathan Crowley 26/08/2021\n");

        createFolderInputANDButton();
        hideCreateFolderInput();

        createListDocInputANDButton();
        hidecreateListDocInput();

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5,45,size-50,size-150);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getBookmarks();
        listFolders();
        createFolder();
        uploadDocument();
        listDocuments();

        menuBar.add(getBookmarks);
        menuBar.add(listFolder);
        menuBar.add(createFolder);
        menuBar.add(uploadDoc);
        menuBar.add(listDoc);

        frame.add(createFolderInput);
        frame.add(createListDocInput);
        frame.add(submitFolderInput);
        frame.add(submitListDocInput);
        frame.getRootPane().setDefaultButton(submitFolderInput);
        frame.getRootPane().setDefaultButton(submitListDocInput);
        frame.add(scrollPane);
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
        frame.setSize(size,size);
        frame.setLocation(location,location);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        frame.setVisible(true);
    }

    /*
        Methods for creating actionListeners for the menubar options.
        NOTE:
            - listDocuments not working??
     */
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
    public void uploadDocument(){
        /*
            In future this should upload a document to a given folder
         */
        uploadDoc = new JMenuItem("Open Document");
        uploadDoc.addActionListener(this);
    }
    public void createFolder(){
        createFolder = new JMenuItem("Create Folder");
        createFolder.addActionListener(this);
    }

    /*
        Actions the menuBar will completed when clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== getBookmarks){
            hideCreateFolderInput();
            hidecreateListDocInput();
            try {
                showBookmarks(LogicalDOC_API.getBookmarks());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        else if (e.getSource()== listFolder){
            hideCreateFolderInput();
            hidecreateListDocInput();
            try {
                showFolders(LogicalDOC_API.getFoldersArray());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        else if (e.getSource()== createFolder){
            textArea.setText("");
            showCreateFolderInput();
        }
        else if (e.getSource()== submitFolderInput){
            hidecreateListDocInput();
            String folderPATH = createFolderInput.getText();
            if (folderPATH.isEmpty() || folderPATH.equals(folderPATHSearchBar)){
                textArea.setText("*** ERROR folderPATH cannot be empty ***");
            }else{
                try {
                    LogicalDOC_API.createFolder(folderPATH);
                    showFolderCreated(folderPATH);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        else if (e.getSource()== uploadDoc){
            hideCreateFolderInput();
            hidecreateListDocInput();
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
            textArea.setText("");
            hideCreateFolderInput();
            showcreateListDocInput();
        }
        else if (e.getSource()== submitListDocInput){
            int folderID = 101;
            if (listDocInput.getText().isEmpty() || listDocInput.getText().equals(folderIdSearchBar)){
                textArea.setText("*** ERROR folderPATH cannot be empty ***");
            }else {
                folderID = Integer.parseInt(listDocInput.getText());
            }
            try {
                showDocuments(LogicalDOC_API.getDocumentsArray(folderID),folderID);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }

    /*
        Methods to show the output of the API json returned.
     */
    private void showDocuments(JSONArray documentsArray,int folderID) throws JSONException {
        textArea.setText(MessageFormat.format("Documents - folderId: {0} [",folderID));
        for (int i=0; i< documentsArray.length(); i++){
            JSONObject folder = documentsArray.getJSONObject(i);
            textArea.append("\n\tfileName: "+folder.get("fileName")+" - ID: "+folder.get("id")+",");
        }
        textArea.append("\n]");
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

    /*
        create textfield and submit button for 'createFolder' option and 'ListDocuments' option.
     */
    private void createFolderInputANDButton() {
        //input field
        createFolderInput = new JTextField("Folder-PATH to create....");
        createFolderInput.setBounds(5,folderInputLocationY,size-(folderSubmitWidth*2),folderInputLocationHeight);
        createFolderInput.addFocusListener(new curlAPI.MyFocusListener());
        //button
        submitFolderInput = new JButton("Submit");
        submitFolderInput.setBounds(size-150,folderInputLocationY,folderSubmitWidth,folderInputLocationHeight);
        submitFolderInput.addActionListener(this);
    }
    private void showCreateFolderInput(){
        createFolderInput.setVisible(true);
        submitFolderInput.setVisible(true);
        pack();
    }
    private void hideCreateFolderInput(){
        createFolderInput.setVisible(false);
        submitFolderInput.setVisible(false);
        pack();
    }
    private void createListDocInputANDButton(){
        //input field
        createListDocInput = new JTextField("Folder-ID to list documents....");
        createListDocInput.setBounds(5,folderInputLocationY,size-(folderSubmitWidth*2),folderInputLocationHeight);
        createListDocInput.addFocusListener(new curlAPI.MyFocusListener());
        //button
        submitListDocInput = new JButton("Submit");
        submitListDocInput.setBounds(size-150,folderInputLocationY,folderSubmitWidth,folderInputLocationHeight);
        submitListDocInput.addActionListener(this);
    }
    private void showcreateListDocInput(){
        createListDocInput.setVisible(true);
        submitListDocInput.setVisible(true);
        pack();
    }
    private void hidecreateListDocInput(){
        createListDocInput.setVisible(false);
        submitListDocInput.setVisible(false);
        pack();
    }

    /*
        Focus listener to replace text of TextField immediately when user begins to type.
     */
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
