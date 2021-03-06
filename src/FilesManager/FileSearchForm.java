/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FilesManager;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Prudhvi
 */
public class FileSearchForm extends javax.swing.JFrame {
    FileNoComboSuggest fileComboHelp;
    SubjComboSuggest subSearchHelp;
    TableModel tableModel;
    boolean fileNotFound;
    /**
     * Creates new form FileSearchForm
     */
    public FileSearchForm() {
        initComponents();
        fileNotFound=false;
        
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
        
        List<HardCopy> listTemp=null;
        StoredPath sp=new StoredPath();
        if(sp.isPathPres()){
            System.out.println(sp.getPath());
            File f=new File(sp.getPath());
            if(f.exists()){
                listTemp=ExcelParser.readExcelData(sp.getPath());
                Search.fileList=listTemp;    
            }
            else{
                JOptionPane.showMessageDialog(this, "sorry previous file not found!");
                fileNotFound=true;
                openFileButtonActionPerformed(null);
            }
            
        }
        else{
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Excel files", "xls", "xlsx");
            chooser.setFileFilter(filter);
            int option = chooser.showOpenDialog(FileSearchForm.this);

            if(option==JFileChooser.APPROVE_OPTION){
                File f=chooser.getSelectedFile();
                System.out.println(f.getName());
                listTemp=ExcelParser.readExcelData(f.getAbsolutePath());
                Search.fileList=listTemp;
                sp.storePath(f.getAbsolutePath());
            }
            else if(option==JFileChooser.CANCEL_OPTION){
                setVisible(false);
                dispose();
                System.exit(0);
            }
        }
       
        
        
        
        
        
        
        tableModel=resultsTable.getModel();
        resultsTable.setRowHeight(120);
        resultsTable.setDefaultRenderer(String.class, new MultiLineCellRenderer());
//        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultsTable.getColumnModel().getColumn(0).setMaxWidth(40);
        resultsTable.getColumnModel().getColumn(1).setMaxWidth(300);
        resultsTable.getColumnModel().getColumn(2).setMaxWidth(500);
        resultsTable.getColumnModel().getColumn(3).setMaxWidth(500);
        resultsTable.getColumnModel().getColumn(4).setMaxWidth(500);
        resultsTable.getColumnModel().getColumn(5).setMaxWidth(500);
        updatetable(listTemp);
//        resizeColumnWidth(resultsTable);
//        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        fileSearchBox.setEditable(true);
        fileComboHelp=new FileNoComboSuggest(fileSearchBox);
        fileSearchBox.setModel(fileComboHelp);
        fileSearchBox.addItemListener(fileComboHelp);
        
       
        
        
        
        JTextField editor = (JTextField) fileSearchBox.getEditor().getEditorComponent();
        
        subjBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    List<HardCopy> l=Search.getBySubject(subjBox.getText());
                    updatetable(l);
                }
                    
            }
        });
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt)
            {
                fileSearchBoxActionPerformed(null);
                
            }
            @Override
            public void keyPressed(KeyEvent evt)
            {
                fileSearchBoxActionPerformed(null);
                
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileSearchBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        outDateChoose = new com.toedter.calendar.JDateChooser();
        inDateChoose = new com.toedter.calendar.JDateChooser();
        searchButton = new javax.swing.JButton();
        subjBox = new javax.swing.JTextField();
        openFileButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        resultsNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileSearchBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fileSearchBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fileSearchBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fileSearchBoxItemStateChanged(evt);
            }
        });
        fileSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSearchBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("File No");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Subject");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Inward Date");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Outward Date");

        resultsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        resultsTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "S.No", "Inward Date", "File No", "Subject", "Outward Date", "DispacthedTo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        resultsTable.setColumnSelectionAllowed(true);
        resultsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(resultsTable);
        if (resultsTable.getColumnModel().getColumnCount() > 0) {
            resultsTable.getColumnModel().getColumn(0).setResizable(false);
            resultsTable.getColumnModel().getColumn(1).setResizable(false);
            resultsTable.getColumnModel().getColumn(2).setResizable(false);
            resultsTable.getColumnModel().getColumn(3).setResizable(false);
            resultsTable.getColumnModel().getColumn(4).setResizable(false);
            resultsTable.getColumnModel().getColumn(5).setResizable(false);
        }

        inDateChoose.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                inDateChoosePropertyChange(evt);
            }
        });

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchButton.setText("Search By Date");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        subjBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        subjBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjBoxActionPerformed(evt);
            }
        });

        openFileButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        openFileButton.setText("Open a new File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Total Results found:");

        resultsNumber.setEditable(false);
        resultsNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(openFileButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resultsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(790, 790, 790)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(outDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fileSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(671, 671, 671)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(inDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(subjBox, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(openFileButton)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subjBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resultsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(outDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(searchButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSearchBoxActionPerformed
        
        List<HardCopy> l=null;
        try {
            l=fileComboHelp.database;
            
        } catch (Exception e) {
            System.out.println("EXCEPTION");
            e.printStackTrace();;
        }
        
         updatetable(l);
        
    }//GEN-LAST:event_fileSearchBoxActionPerformed

    private void inDateChoosePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_inDateChoosePropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_inDateChoosePropertyChange

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        try {
            Date d1=inDateChoose.getDate();
            Date d2=outDateChoose.getDate();
            List<HardCopy> l=Search.getByDates(d1, d2);
            updatetable(l);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Excel files", "xls", "xlsx");
        chooser.setFileFilter(filter);
        int option = chooser.showOpenDialog(FileSearchForm.this);
        
        if(option==JFileChooser.APPROVE_OPTION){
            File f=chooser.getSelectedFile();
            System.out.println(f.getName());
            List<HardCopy> list=ExcelParser.readExcelData(f.getAbsolutePath());
            Search.fileList=list;
            StoredPath sp=new StoredPath();
            sp.storePath(f.getAbsolutePath());
        }
        else if(option==JFileChooser.CANCEL_OPTION&&fileNotFound==true){
            this.dispose();
            System.exit(0);
        }
        
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void subjBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjBoxActionPerformed
        
    }//GEN-LAST:event_subjBoxActionPerformed

    private void fileSearchBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fileSearchBoxItemStateChanged
      
    }//GEN-LAST:event_fileSearchBoxItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FileSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileSearchForm().setVisible(true);
                
            }
        });
        
    }
    private void updateRowHeights()
    {
        try
        {
            for (int row = 0; row < resultsTable.getRowCount(); row++)
            {
                int rowHeight = resultsTable.getRowHeight();
                int column=3;
                Component comp = resultsTable.prepareRenderer(resultsTable.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                resultsTable.setRowHeight(row, rowHeight);
            }
        }
        catch(ClassCastException e) {}
    }
    private void updatetable(List<HardCopy> l)
    {
        int index=0;
        
        if(l!=null){
            System.out.println(l.size());
            for (HardCopy hardCopy : l) {
                if(index>=100)
                    break;
                tableModel.setValueAt(index+1, index, 0);
                tableModel.setValueAt(hardCopy.inDate.toLocaleString(), index, 1);
                tableModel.setValueAt(hardCopy.fileNo, index, 2);
                if(hardCopy.subject!=null)
                {
                    String s=hardCopy.subject;
                    StringBuilder sb = new StringBuilder(s);

                    int i = 0;
                    while ((i = sb.indexOf(" ", i + 30)) != -1) {
                        sb.replace(i, i + 1, "\n");
                    }
                    tableModel.setValueAt(sb.toString(), index, 3);

                }
                if(hardCopy.outDate!=null)
                    tableModel.setValueAt(hardCopy.outDate.toLocaleString(), index, 4);
                if(hardCopy.dispatchedTo!=null)
                    tableModel.setValueAt(hardCopy.dispatchedTo, index, 5);
                index++;
            }
        }
        for (int j = index; j < 100; j++) {
            for (int k = 0; k < 6; k++) {
                    tableModel.setValueAt("", j, k);
            }
        }
            
            resultsNumber.setText(String.valueOf(l.size()));
    }
    
  public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 50; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width, width);
        }
        columnModel.getColumn(column).setPreferredWidth(width);
    }
}
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox fileSearchBox;
    private com.toedter.calendar.JDateChooser inDateChoose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton openFileButton;
    private com.toedter.calendar.JDateChooser outDateChoose;
    private javax.swing.JTextField resultsNumber;
    private javax.swing.JTable resultsTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField subjBox;
    // End of variables declaration//GEN-END:variables
}
class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

  public MultiLineCellRenderer() {
    setLineWrap(true);
    setWrapStyleWord(true);
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(table.getBackground());
    }
    setFont(table.getFont());
    if (hasFocus) {
      setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
      if (table.isCellEditable(row, column)) {
        setForeground(UIManager.getColor("Table.focusCellForeground"));
        setBackground(UIManager.getColor("Table.focusCellBackground"));
      }
    } else {
      setBorder(new EmptyBorder(1, 2, 1, 2));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
  
}


