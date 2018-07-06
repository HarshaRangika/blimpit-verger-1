/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

/**
 *
 * @author neeshad
 */
public class COAFileCreater {
    
    
    
    
    private void fileMaker(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd");
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        String y = String.format("%1$ty/%1$tm/%1$td", now);

        String productName = "Cinnamon Bark Essential Oil Ceylon";
        String code = "0118JA-04";
        String analysisReference = "18JAN-CNB-V-1";
        String lotNumber = "1801-CNB-V002";

        int maxRowSize = 8;

        XWPFDocument doc = new XWPFDocument();
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();


        XWPFParagraph para = doc.createParagraph();
        XWPFRun runer = para.createRun();

        runer.setText("CERTIFICATE OF");
        runer.addBreak();
        runer.setText("   ANALYSIS");
        runer.setFontSize(28);
        runer.setBold(true);
        runer.setFontFamily("Myriad Pro");

        XWPFParagraph paratwo = doc.createParagraph();
        XWPFRun runertwo = paratwo.createRun();
        runertwo.setFontFamily("Myriad Pro");
        runertwo.addBreak();
        runertwo.addBreak();
        runertwo.setFontSize(11);
        runertwo.setText("Date :");
        runertwo.setText(y);
        runertwo.addBreak();

        XWPFParagraph parathree = doc.createParagraph();
        XWPFRun runthree = parathree.createRun();
        runthree.setText("Product Information");
        runthree.setUnderline(UnderlinePatterns.SINGLE);
        runthree.setBold(true);

        XWPFParagraph parafour = doc.createParagraph();
        XWPFRun runfour = parafour.createRun();
        runfour.addBreak();
        runfour.setText("Product Name :        " +productName+"                       " + "Sample");
        runfour.addBreak();
        runfour.setText("Code:  "+code);
        runfour.addBreak();
        runfour.addBreak();
        runfour.setText("Analysis Reference: "+analysisReference+"                     Lot Number :"+lotNumber);
        runfour.addBreak();
        runfour.setText("Customer Reference:                                             Manufacturing Date :");
        runfour.addBreak();
        runfour.addBreak();

        XWPFParagraph parafive = doc.createParagraph();
        XWPFRun runfive = parafive.createRun();
        runfive.setBold(true);
        runfive.setUnderline(UnderlinePatterns.SINGLE);
        runfive.setText("Physical Indexes :");
        runfive.setFontSize(11);

        XWPFParagraph parasix = doc.createParagraph();
        XWPFRun runsix = parasix.createRun();
        runsix.setText("Refractive Index (20 C)"+"1,5"+"                      Optical Rotation(20 C:):"+"-1,40");
        runsix.addBreak();
        runsix.setText("Specific Gravity (20 C): "+"1,02"+"                    Flash Point :"+"75 C");
        runsix.addBreak();
        runsix.setText("Solubility in Ethanol(70% v/v) :"+ "1 in 2             "+"Color :"+"Deep yellow color");
        runsix.addBreak();
        runsix.setText("Order");
        runsix.addBreak();
        runsix.addBreak();

        XWPFParagraph paraseven = doc.createParagraph();
        XWPFRun runseven = paraseven.createRun();
        runseven.setText("GC Composition Report:");
        runseven.setBold(true);
        runseven.setUnderline(UnderlinePatterns.SINGLE);


        ///Table start

        XWPFTable table = doc.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Compound");
        tableRowOne.addNewTableCell().setText("Occurrence");
        tableRowOne.addNewTableCell().setText("AFNOR/ISO Standards");

        //create second row
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("Trans-Cinnamic Aldehyde");
        tableRowTwo.getCell(1).setText("col two, row two");
        tableRowTwo.getCell(2).setText("col three, row two");
        //create third row
        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("col one, row three");
        tableRowThree.getCell(1).setText("col two, row three");
        tableRowThree.getCell(2).setText("col three, row three");

        //create third row
        XWPFTableRow tableRowFour = table.createRow();
        tableRowFour.getCell(0).setText("col one, row four");
        tableRowFour.getCell(1).setText("col two, row four");
        tableRowFour.getCell(2).setText("col three, row four");

        //create third row
        XWPFTableRow tableRowFive = table.createRow();
        tableRowFive.getCell(0).setText("Eugenol");
        tableRowFive.getCell(1).setText("col two, row five");
        tableRowFive.getCell(2).setText("col three, row five");

        XWPFParagraph paraeight = doc.createParagraph();
        XWPFRun runeight = paraeight.createRun();
        runeight.addBreak(BreakClear.RIGHT);
        runeight.setText("                                                                                 Issued by QC Management,");

        //runfour.;


        try (FileOutputStream out = new FileOutputStream("testDoc8.docx")) {
            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
