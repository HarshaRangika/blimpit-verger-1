/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import controller.UsermangementClient;
import gui.VergerMain;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import controller.ApiConnector;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tharusha
 */
public class UserManager extends javax.swing.JFrame {

    
          
//        String text = txtUMSer.getText();
//        sorter.setRowFilter(RowFilter.regexFilter(text,0));
    public UserManager() {

        lookandfeels();
        initComponents();
        ImageIcon icon = new ImageIcon("Image/icon.png");
        setIconImage(icon.getImage());

        loadpendinguser();
        loaduserinfo();
        
    }

    public UserManager(int row, String table) {
        lookandfeels();
        initComponents();
        if (table.equals("pending")) {

            tabUserRequest.remove(row);

        } else if (table.equals("info")) {
            tabuserInfo.remove(row);
        }
        ImageIcon icon = new ImageIcon("Image/icon.png");
        setIconImage(icon.getImage());

        loadpendinguser();
        loaduserinfo();
    }

    public void loadpendinguser() {
        ApiConnector apiConnector = new ApiConnector();
        String get =apiConnector.get("http://localhost:8080/api/usrmgtservice/users/?status=pending");
        
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        
        int arrayLength = 0;
        Vector row;

        try {
            jsonArray = (JSONArray) parser.parse(get);
            arrayLength = jsonArray.size();
            JSONObject jsonobj = new JSONObject();
            DefaultTableModel model = (DefaultTableModel) tabUserRequest.getModel();

            for (int i = 0; i < arrayLength; i++) {
                row = new Vector();
                jsonobj = (JSONObject) jsonArray.get(i);
                                                
                row.add(0, jsonobj.get("name").toString());
                row.add(1, jsonobj.get("username").toString());
                row.add(2, jsonobj.get("designation").toString());

                model.addRow(row);

            }

        } catch (ParseException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public void loaduserinfo() {

         ApiConnector apiConnector = new ApiConnector();
        String get =apiConnector.get("http://localhost:8080/api/usrmgtservice/users/?status=Accepted");
        
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        
        int arrayLength = 0;
        Vector row;

        try {
            jsonArray = (JSONArray) parser.parse(get);
            arrayLength = jsonArray.size();
            JSONObject jsonobj = new JSONObject();
            DefaultTableModel model = (DefaultTableModel) tabuserInfo.getModel();

            for (int i = 0; i < arrayLength; i++) {
                row = new Vector();
                jsonobj = (JSONObject) jsonArray.get(i);
                                                
                row.add(0, jsonobj.get("name").toString());
                row.add(1, jsonobj.get("username").toString());
                row.add(2, jsonobj.get("designation").toString());
                row.add(3,"Accepted");

                model.addRow(row);

            }

        } catch (ParseException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void search(){
        
        String key=txtUMSer.getText().toLowerCase();
        
        ApiConnector apiConnector = new ApiConnector();
        String get =apiConnector.get("http://localhost:8080/api/usrmgtservice/searchusers/"+key);
        
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        
        int arrayLength = 0;
        Vector row;

        try {
            jsonArray = (JSONArray) parser.parse(get);
            arrayLength = jsonArray.size();
            JSONObject jsonobj = new JSONObject();
            DefaultTableModel model = (DefaultTableModel) tabuserInfo.getModel();

            for (int i = 0; i < arrayLength; i++) {
                row = new Vector();
                jsonobj = (JSONObject) jsonArray.get(i);
                                                
                row.add(0, jsonobj.get("name").toString());
                row.add(1, jsonobj.get("username").toString());
                row.add(2, jsonobj.get("designation").toString());
                row.add(3,"Accepted");

                model.addRow(row);

            }

        } catch (ParseException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
     private static void lookandfeels() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabuserInfo = new javax.swing.JTable();
        txtUMSer = new javax.swing.JTextField();
        btnUMsea = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabUserRequest = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnUMok = new javax.swing.JButton();
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Verger International");

        tabuserInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name of User", "User Name", "Designation", "Authorization"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabuserInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabuserInfoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabuserInfo);

        txtUMSer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtUMSerMouseEntered(evt);
            }
        });
        txtUMSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUMSerActionPerformed(evt);
            }
        });
        txtUMSer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUMSerKeyReleased(evt);
            }
        });

        btnUMsea.setText("Search");
        btnUMsea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUMseaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(txtUMSer, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUMsea)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUMSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUMsea))
                .addContainerGap())
        );

        jTabbedPane1.addTab("User Info", jPanel3);

        tabUserRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name of the User", "User Name", "Desgnation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabUserRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabUserRequestMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabUserRequest);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User Request", jPanel4);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Blimp_50x.png"))); // NOI18N
        jLabel1.setOpaque(true);

        btnUMok.setText("Ok");
        btnUMok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUMokActionPerformed(evt);
            }
        });

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/Left_20px.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUMok, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnUMok, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(705, 685));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int row;
    int colum;

    private void btnUMokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUMokActionPerformed
        VergerMain vm = new VergerMain("admin");
        vm.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnUMokActionPerformed

    private void tabuserInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabuserInfoMouseClicked

        JTable source = (JTable) evt.getSource();
        row = source.rowAtPoint(evt.getPoint());
        colum = tabuserInfo.getColumnCount();

        UserVeiwe uv = new UserVeiwe();

        uv.setVisible(true);
//                     ......................Name........................../......................Username......................./........................Designation................../......................Authorization....................                      
        uv.loadDetails(tabuserInfo.getModel().getValueAt(row, 0).toString(), tabuserInfo.getModel().getValueAt(row, 1).toString(), tabuserInfo.getModel().getValueAt(row, 2).toString(), tabuserInfo.getModel().getValueAt(row, 3).toString());
        this.setVisible(false);

    }//GEN-LAST:event_tabuserInfoMouseClicked

    private void tabUserRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabUserRequestMouseClicked
        JTable source = (JTable) evt.getSource();
        row = source.rowAtPoint(evt.getPoint());
        colum = source.columnAtPoint(evt.getPoint());

        UserAuth ua = new UserAuth(row, colum);
        ua.loadDetails(tabUserRequest.getModel().getValueAt(row, 0).toString(), tabUserRequest.getModel().getValueAt(row, 1).toString(),
                tabUserRequest.getModel().getValueAt(row, 2).toString());
        ua.setVisible(true);

        //this.disable();
    }//GEN-LAST:event_tabUserRequestMouseClicked

    private void txtUMSerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUMSerMouseEntered
        if (txtUMSer.getText().equals("User Name")) {

            txtUMSer.setText("");

        }

    }//GEN-LAST:event_txtUMSerMouseEntered

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        VergerMain vm = new VergerMain("admin");
        vm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnUMseaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUMseaActionPerformed
//        
//        String name=txtUMSer.getText();
//        ApiConnector apiConnector = new ApiConnector();
//        String get =apiConnector.get("http://localhost:8080/api/usrmgtservice/users/?username="+name);
//        
//        JSONParser parser = new JSONParser();
//        JSONArray jsonArray = null;
//        
//        int arrayLength = 0;
//        Vector row;
//
//        try {
//            jsonArray = (JSONArray) parser.parse(get);
//            arrayLength = jsonArray.size();
//            JSONObject jsonobj = new JSONObject();
//            DefaultTableModel model = (DefaultTableModel) tabuserInfo.getModel();
//
//            for (int i = 0; i < arrayLength; i++) {
//                row = new Vector();
//                jsonobj = (JSONObject) jsonArray.get(i);
//                                                
//                row.add(0, jsonobj.get("username").toString());
//                row.add(1, jsonobj.get("name").toString());
//                row.add(2, jsonobj.get("designation").toString());
//                row.add(3,"Accepted");
//
//                model.addRow(row);
//
//            }
//
//        } catch (ParseException ex) {
//            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
//        }










    }//GEN-LAST:event_btnUMseaActionPerformed

    private void txtUMSerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUMSerKeyReleased
       
        if(txtUMSer.getText().equals("")){
             DefaultTableModel model = (DefaultTableModel) tabuserInfo.getModel(); 
        model.setRowCount(0);
            loaduserinfo();
        }
        else{
            DefaultTableModel model = (DefaultTableModel) tabuserInfo.getModel(); 
        model.setRowCount(0);
        search();
        }
    }//GEN-LAST:event_txtUMSerKeyReleased

    private void txtUMSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUMSerActionPerformed
     
    }//GEN-LAST:event_txtUMSerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        lookandfeels();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUMok;
    private javax.swing.JButton btnUMsea;
    private javax.swing.JButton btnback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabUserRequest;
    private javax.swing.JTable tabuserInfo;
    private javax.swing.JTextField txtUMSer;
    // End of variables declaration//GEN-END:variables
}
