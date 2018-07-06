/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tharusha
 */
public class FileHandler {

    private String folderName;
    private String baseLocation;

    public FileHandler(String folderName, String baseLocation) {
        this.folderName = folderName;
        this.baseLocation = baseLocation;
    }

    //final JFileChooser fc = new JFileChooser(new File(folderName));
    final JFileChooser fc = new JFileChooser(baseLocation);
    String fileName;

    public String fileName(JFrame _dd) {
        int response = fc.showDialog(_dd, folderName);
        if (response == JFileChooser.APPROVE_OPTION) {

            fileName = fc.getSelectedFile().toString();

        } else {

            //JOptionPane.showMessageDialog(null, "The File open Operation was Cancelled");
            fileName = "The File open Operation was Cancelled";

        }

        return fileName;
    }

    public String openFile(String filepath) {
        
        //File file = new File("/home/neeshad/Desktop/des/design.pdf");

        File file = new File(filepath);
        
        if (file.exists()) {

            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(file);
                    return "File Open Sucess";
                } catch (IOException ex) {
                    Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                    return "error" + ex.getMessage();
                }
            } else {
                System.out.println("Awt Desktop is not supported!");
                return "Awt Desktop is not supported!";
            }

        } else {
            return "File Not found";
        }


    }

}
