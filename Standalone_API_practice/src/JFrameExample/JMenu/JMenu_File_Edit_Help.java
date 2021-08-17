package JFrameExample.JMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class JMenu_File_Edit_Help extends JFrame implements ActionListener{

    static JFrame frame;
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JMenuBar menuBar;
    static JMenu file,edit,help;
    static JMenuItem newWindow,open,save;
    static JMenuItem cut,copy,paste,selectAll;
    static JMenuItem about;

    int size = 400;
    int location = 500;

    JMenu_File_Edit_Help(){
        frame = new JFrame("Editor");
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5,5,size-50,size-70);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        frame.add(scrollPane);

        addFileTab();
        addEditTab();
        addHelpTab();

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
        frame.setSize(size,size);
        frame.setLocation(location,location);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // create menuBar tabs
    public void addFileTab(){
        file = new JMenu("File");
        newWindow = new JMenuItem("New");open = new JMenuItem("Open");save = new JMenuItem("Save");
        file.add(newWindow);file.add(open);file.add(save);

        newWindow.addActionListener(this);
        open.addActionListener(this);
    }
    public void addEditTab(){
        edit = new JMenu("Edit");
        cut = new JMenuItem("Cut");copy = new JMenuItem("Copy");paste = new JMenuItem("Paste");selectAll = new JMenuItem("selectAll");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
    }
    public void addHelpTab(){
        help = new JMenu("Help");
        about = new JMenuItem("About");
        help.add(about);

        about.addActionListener(this);
    }

    // give menuItem functions
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==cut){
            textArea.cut();
        }
        else if(e.getSource()==open){
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
        else if(e.getSource()==newWindow){
            createNewFrame();
        }
        else if(e.getSource()==copy){
            textArea.copy();
        }
        else if(e.getSource()==paste){
            textArea.paste();
        }
        else if(e.getSource()==selectAll){
            textArea.selectAll();
        }
        else if(e.getSource()==about){
            frame.setTitle("About");
            textArea.selectAll();
            textArea.cut();
            textArea.append("About Page....");
        }
    }

    public void createNewFrame(){
        JFrame newFrame = new JFrame("New Window");
        newFrame.setLocation(location+50,location+50);
        newFrame.setSize(size,size);
        newFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new JMenu_File_Edit_Help();
    }
}
