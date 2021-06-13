/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.parking;

import db.KoneksiMysql;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Home extends javax.swing.JFrame {
    Connection Con;
    ResultSet RsKndrMsk;
    ResultSet RsKndrKlr;
    ResultSet RsNoKendaraan;

    ResultSet RsConfig;
    Statement stm;
    int kapasitas;
    
    String sJenisKendaraan;
    private Object[][] dataTable = null;
    private Object[][] dataTableKlr = null;
    private String[] header = {"No Kendaraan","Jenis Kendaraan","Waktu Masuk"};
    private String[] headerKlr = {"No Kendaraan","Jenis Kendaraan","Waktu Masuk","Waktu Keluar"};
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        open_db();
        baca_data();
        config();
        baca_data_kendaraan_keluar();
    }
    
    private void open_db(){
        try {
            KoneksiMysql konek = new KoneksiMysql();
            Con = konek.getConnection();
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            System.out.println("Berhasil ");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
    void config(){
        try {
            RsConfig = stm.executeQuery("select * from config where name = 'kapasitas'");
            RsConfig.next();
            
            kapasitas = RsConfig.getInt("value");
            lKapasitas.setText(String.valueOf(kapasitas));
            
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void baca_data() {
        try {
            RsKndrMsk = stm.executeQuery("select * from trx_kendaraan where waktu_keluar IS NULL");
            ResultSetMetaData meta = RsKndrMsk.getMetaData();
            int col = meta.getColumnCount();
            Integer baris = 0;
            
            // Proses untuk mencari total kendaraan yang masuk
            while (RsKndrMsk.next()) {
                baris = RsKndrMsk.getRow();
            }
            if (baris > 0) {
                lTotKendaraan.setText(baris.toString());
            }
            
            // Proses untuk get tabel kendaraan masuk
            dataTable = new Object[baris][col];
            int x = 0;
            RsKndrMsk.beforeFirst();
            
            while (RsKndrMsk.next()) {
                dataTable[x][0] = RsKndrMsk.getString("no_kendaraan");
                dataTable[x][1] = RsKndrMsk.getString("jenis_kendaraan");
                dataTable[x][2] = RsKndrMsk.getTimestamp("waktu_masuk");
                x++;
            }
            tblKendaraanMasuk.setModel(new DefaultTableModel(dataTable, header));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void baca_data_kendaraan_keluar() {
        try {
            RsKndrKlr = stm.executeQuery("select * from trx_kendaraan where waktu_keluar IS NOT NULL and DATE_FORMAT(waktu_keluar, '%Y-%m-%d') LIKE DATE_FORMAT(NOW(), '%Y-%m-%d')");
            ResultSetMetaData meta = RsKndrKlr.getMetaData();
            int col = meta.getColumnCount();
            int baris = 0;
            
            while (RsKndrKlr.next()) {
                baris = RsKndrKlr.getRow();
            }
            dataTableKlr = new Object[baris][col];
            int x = 0;
            RsKndrKlr.beforeFirst();
            
            while (RsKndrKlr.next()) {
                dataTableKlr[x][0] = RsKndrKlr.getString("no_kendaraan");
                dataTableKlr[x][1] = RsKndrKlr.getString("jenis_kendaraan");
                dataTableKlr[x][2] = RsKndrKlr.getTimestamp("waktu_masuk");
                dataTableKlr[x][3] = RsKndrKlr.getTimestamp("waktu_keluar");
                x++;
            }
            tblKendaraanKeluar.setModel(new DefaultTableModel(dataTableKlr, headerKlr));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKendaraanMasuk = new javax.swing.JTable();
        TextNoKendaraan = new javax.swing.JTextField();
        CmbJenisKendaraan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BtnMasuk = new javax.swing.JButton();
        lKapasitas = new javax.swing.JLabel();
        lTotKendaraan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtNoKendaraanKeluar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lJenisKendaraan = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lJamMasuk = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lJamKeluar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lLamaParkir = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKendaraanKeluar = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuConfig = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aplikasi Parkir Kampus Udinus");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(430, 300));

        jLabel3.setText("Nomor Kendaraan");

        jLabel4.setText("Jenis Kendaraan");

        tblKendaraanMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nomor Polisi", "Jenis Kendaraan", "Jam Masuk"
            }
        ));
        jScrollPane1.setViewportView(tblKendaraanMasuk);

        TextNoKendaraan.setSize(new java.awt.Dimension(100, 26));

        CmbJenisKendaraan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roda 2", "Roda 4" }));
        CmbJenisKendaraan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbJenisKendaraanActionPerformed(evt);
            }
        });

        jLabel5.setText("Kapasitas Tersedia : ");

        jLabel6.setText("Total Kendaraan :");

        BtnMasuk.setText("Masuk");
        BtnMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMasukMouseClicked(evt);
            }
        });

        lKapasitas.setText("0");

        lTotKendaraan.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Kendaraan Masuk");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextNoKendaraan)
                            .addComponent(CmbJenisKendaraan, 0, 160, Short.MAX_VALUE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lKapasitas))
                                .addComponent(BtnMasuk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(88, 88, 88)
                                .addComponent(lTotKendaraan)))))
                .addGap(26, 26, 26))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TextNoKendaraan)
                    .addComponent(jLabel5)
                    .addComponent(lKapasitas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CmbJenisKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(lTotKendaraan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jLabel7)
                    .addContainerGap(445, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(430, 300));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Kendaraan Keluar");

        TxtNoKendaraanKeluar.setSize(new java.awt.Dimension(100, 26));
        TxtNoKendaraanKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNoKendaraanKeluarKeyPressed(evt);
            }
        });

        jLabel8.setText("Nomor Kendaraan");

        jLabel9.setText("Jenis Kendaraan");

        lJenisKendaraan.setText("-");

        jLabel10.setText("Jam Masuk");

        lJamMasuk.setText("-");

        jLabel11.setText("Jam Keluar");

        lJamKeluar.setText("-");

        jLabel12.setText("Lama Parkir");

        lLamaParkir.setText("-");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Keluar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton3.setText("No STNK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tblKendaraanKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblKendaraanKeluar);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lLamaParkir, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lJamKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lJamMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtNoKendaraanKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(lJenisKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(55, 55, 55)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TxtNoKendaraanKeluar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lJenisKendaraan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lJamMasuk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lJamKeluar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lLamaParkir))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(865, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        MenuConfig.setText("Config");
        MenuConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuConfigActionPerformed(evt);
            }
        });
        jMenu1.add(MenuConfig);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Laporan");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CmbJenisKendaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbJenisKendaraanActionPerformed
        JComboBox vJenisKendaraan = (javax.swing.JComboBox)evt.getSource();
        sJenisKendaraan = (String) vJenisKendaraan.getSelectedItem();
    }//GEN-LAST:event_CmbJenisKendaraanActionPerformed
    
    private void clear_input_kendaraan_masuk(){
        TextNoKendaraan.setText("");
    }
    
    private void BtnMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMasukMouseClicked
        String noKendaraan = (String) TextNoKendaraan.getText();
        sJenisKendaraan =  CmbJenisKendaraan.getSelectedItem().toString();
        try {
            stm.executeUpdate("INSERT into trx_kendaraan (no_kendaraan,jenis_kendaraan,waktu_masuk) values('"+noKendaraan.toUpperCase()+"','"+sJenisKendaraan+"', NOW())");
            JOptionPane.showMessageDialog(null, "Sukses Input Data");
            baca_data(); // Refresh Tabel
            clear_input_kendaraan_masuk(); // Clear inputan
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e); 
        }
    }//GEN-LAST:event_BtnMasukMouseClicked

    private void TxtNoKendaraanKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNoKendaraanKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNoKendaraanKeluarKeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String noKendaraan = TxtNoKendaraanKeluar.getText().toString();
        
        try {
            // Proses Cek No Kendaraan
            RsNoKendaraan = stm.executeQuery("select * from trx_kendaraan where no_kendaraan = '" + noKendaraan.toUpperCase() + "' and waktu_keluar is null");
            int id_trx = 0;
            while (RsNoKendaraan.next()) {
                id_trx = RsNoKendaraan.getInt("id");
            }
            
            // Proses kendaraan keluar
            if(id_trx != 0){ // -> Jika id nya bukan 0 yang berarti ID ketemu
                stm.executeUpdate("UPDATE trx_kendaraan set waktu_keluar = NOW() WHERE id = " + id_trx);
                
                // Proses untuk update Data Kendaraan keluar
                RsNoKendaraan = stm.executeQuery("select *,TIMESTAMPDIFF(SECOND,waktu_masuk,waktu_keluar) as time  from trx_kendaraan where id = " + id_trx);
                while (RsNoKendaraan.next()) {
                    int time = RsNoKendaraan.getInt("time");
                    int jam = (time / (60 * 60));
                    int sisa_jam = (time % (60 * 60));
                    
                    int menit = (sisa_jam / (60));
                    
                    String time_final = jam + " Jam " + menit + " Menit ";
                    lJenisKendaraan.setText(RsNoKendaraan.getString("no_kendaraan"));
                    lJamMasuk.setText(RsNoKendaraan.getString("waktu_masuk"));
                    lJamKeluar.setText(RsNoKendaraan.getString("waktu_keluar"));
                    lLamaParkir.setText(time_final);
                }
                baca_data();
                baca_data_kendaraan_keluar();
                int total = Integer.valueOf(lTotKendaraan.getText().toString());
                lTotKendaraan.setText(String.valueOf(total - 1));
            
                JOptionPane.showMessageDialog(null, "No Kendaraan Ditemukan, Dan Data Sudah di Simpan");
            }else{
                JOptionPane.showMessageDialog(null, "No Kendaraan Tidak Ditemukan");
            }
            
            
            baca_data();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e); 
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void MenuConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuConfigActionPerformed
        // TODO add your handling code here:
        new Konfigurasi().setVisible(true);
    }//GEN-LAST:event_MenuConfigActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new FormLaporanStnk().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnMasuk;
    private javax.swing.JComboBox<String> CmbJenisKendaraan;
    private javax.swing.JMenuItem MenuConfig;
    private javax.swing.JTextField TextNoKendaraan;
    private javax.swing.JTextField TxtNoKendaraanKeluar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lJamKeluar;
    private javax.swing.JLabel lJamMasuk;
    private javax.swing.JLabel lJenisKendaraan;
    private javax.swing.JLabel lKapasitas;
    private javax.swing.JLabel lLamaParkir;
    private javax.swing.JLabel lTotKendaraan;
    private javax.swing.JTable tblKendaraanKeluar;
    private javax.swing.JTable tblKendaraanMasuk;
    // End of variables declaration//GEN-END:variables
}