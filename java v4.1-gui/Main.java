/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;


import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.Timer;

 
/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class Main extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton, listButton, clearButton;
    JTextArea log, helplog, textlog;
    JFileChooser fc;
 
    public Main() {
        super(new BorderLayout());
 
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(10,10,10,10));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        helplog = new JTextArea(20,20);
        helplog.setMargin(new Insets(10,10,10,10));
        helplog.setEditable(false);
        JScrollPane logScrollPane1 = new JScrollPane(helplog);
        helplog.setLineWrap(true);
        
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        openButton = new JButton("Add mark");
        openButton.addActionListener(this);
 
        saveButton = new JButton("Remove mark");
        saveButton.addActionListener(this);
        
        listButton = new JButton("List a directory");
        listButton.addActionListener(this);
        
        clearButton = new JButton("Clear Log");
        clearButton.addActionListener(this);
 

        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(listButton);
        buttonPanel.add(clearButton);
 

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(logScrollPane1, BorderLayout.LINE_END);
        buttonPanel.setBackground(new Color(70, 130, 180));
        
        helplog.append("Myriware: Software Solutions\nFile Sequence Randomizer\n\nHow To Use:\n\nAdd Mark--Add a tag, which will have a \nrandom number, so that the files \nappear in a random order\nRemove Mark--Remove that tag so that \nthe original name is restored\nList a directory--Display the contents of a directory.");
        helplog.append("\n\nWhen you press a button, one of the \nthree options, it will bring up a file \nchooser. Once opened, you navigate \nto the directory you would like to \nrandomize. When found, press the \n'Open' button.");

    }
 

	public void actionPerformed(ActionEvent e) {
 
        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(Main.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("\nAdding marks to: " + file.getName() + "." + newline);
                programlogic b = new programlogic();
                b.programLogic(file.getAbsolutePath());
                listBash(file.getAbsolutePath());
            } else {
                log.append("Cancelled" + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
 
        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showOpenDialog(Main.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                removemark a = new removemark();
                a.removeMark(file.getAbsolutePath());
                log.append("\nRemoving marks from: " + file.getName() + "." + newline);
                listBash(file.getAbsolutePath());
            } else {
                log.append("Cancelled." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        } else if (e.getSource() == listButton) {
            int returnVal = fc.showOpenDialog(Main.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                listBash(file.getAbsolutePath());
            } else {
                log.append("Cancelled." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
    } else if (e.getSource() == clearButton) {
        log.setText("");
    }
        
        
    } 
    
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("File Sequence Randomizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(650, 450));
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setBackground(new Color(0,0,0));
        frame.setLocation(500,300);
        
 
        //Add content to the window.
        frame.add(new Main());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    

	public void listBash(String folder) {
        String[] lspathnames;
        File f = new File(folder);
        lspathnames = f.list();
        for (String lspathname : lspathnames) {
        	log.append(lspathname + newline);
        }
      }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
                
            }
        });
    }
}