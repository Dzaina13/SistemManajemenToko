/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pbo_manajemen_toko;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font; // Import Font for styling
import java.awt.Cursor; // Import Cursor
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author nabil
 */
public class ListBarang extends javax.swing.JFrame {

    /**
     * Creates new form ListBarang
     */
    public ListBarang() {
        initComponents();
        // Set extended state to maximized for fullscreen behavior like Dashboard
        setExtendedState(ListBarang.MAXIMIZED_BOTH);
        // Set background color of content pane for main frame background
        getContentPane().setBackground(new Color(250, 250, 250)); // Corresponds to #fafafa
        LoadDataBarang("");
        loadnama();

        // Apply consistent styles programmatically
        applyConsistentStyles();
    }

    private void applyConsistentStyles() {
        // --- Panel Background Colors ---
        jPanel1.setBackground(new Color(237, 255, 249)); // #edf9ff for sidebar
        jPanel2.setBackground(new Color(250, 250, 250)); // #fafafa for main content

        // Logo - using existing jLabel1 ("Toko") and jLabel2 ("Aja.")
        jLabel1.setFont(new Font("Poppins", Font.BOLD, 24));
        jLabel1.setForeground(Color.BLACK); // "Toko" is typically black

        jLabel2.setFont(new Font("Poppins", Font.BOLD, 24));
        jLabel2.setForeground(new Color(50, 142, 110)); // #328e6e for "Aja."

        // Navigation labels (using original variable names)
        // Dashboard Button (jLabel3)
        jLabel3.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel3.setForeground(new Color(123, 122, 120)); // #7b7a78 (Inactive)
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/Frame-1.png"))); // Consistent icon path
        jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for clickable

        // List Barang Button (jLabel4) - current active page
        jLabel4.setFont(new Font("Poppins Medium", Font.BOLD, 14)); // Poppins Medium, Bold for active
        jLabel4.setForeground(new Color(50, 142, 110)); // #328e6e (Active color)
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/box-1.png"))); // Consistent icon path
        jLabel4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Default cursor as it's current page

        // Transaksi Button (jLabel5)
        jLabel5.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel5.setForeground(new Color(123, 122, 120)); // #7b7a78 (Inactive)
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/Frame-2.png"))); // Consistent icon path
        jLabel5.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for clickable

        // Return Barang Button (jLabel6)
        jLabel6.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel6.setForeground(new Color(123, 122, 120)); // #7b7a78 (Inactive)
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/boxes.png"))); // Consistent icon path
        jLabel6.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for clickable

        // Histori Transaksi Button (jLabel7)
        jLabel7.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel7.setForeground(new Color(123, 122, 120)); // #7b7a78 (Inactive)
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/transaction-history-stroke-rounded 1.png"))); // Consistent icon path
        jLabel7.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for clickable

        // --- User Info & Logout (Matching Dashboard structure) ---
        // Profile Icon (NEW JLabel: profileIcon)
        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/profile-img.png"))); // Consistent icon path

        // User Name (jLabel8 - now text only)
        jLabel8.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel8.setForeground(Color.BLACK); // Name is typically black
        // NO setIcon here, as profileIcon handles the image

        // User Role (jLabel9 - Kasir)
        jLabel9.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel9.setForeground(new Color(123, 122, 120)); // #7b7a78 (Role color)

        // Logout Button (jLabel10 - now text only)
        jLabel10.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
        jLabel10.setForeground(new Color(123, 122, 120)); // #7b7a78 (Inactive)
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/logout.png")));
        jLabel10.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for clickable

        // Main Panel Title (jLabel11 - "List Barang")
        jLabel11.setFont(new Font("Poppins SemiBold", Font.BOLD, 24)); // Poppins SemiBold, Bold 24pt
        jLabel11.setForeground(new Color(50, 142, 110)); // #328e6e

        // Search text field and buttons
        fcaribarang.setFont(new Font("Poppins", Font.PLAIN, 13)); // Consistent font for input
        fbtncari.setFont(new Font("Poppins", Font.BOLD, 15)); // Poppins Bold 15pt
        fbtnreset.setFont(new Font("Poppins", Font.BOLD, 15)); // Poppins Bold 15pt

        // Table Header Font (Optional, often set globally or via UIManager)
        ftblbarang.getTableHeader().setFont(new Font("Poppins Medium", Font.BOLD, 12));
        ftblbarang.setFont(new Font("Poppins", Font.PLAIN, 12));
        ftblbarang.setRowHeight(25); // Add some row height for better visual

        // Adjust preferred size of scroll pane for table to limit its height
        jScrollPane1.setPreferredSize(new java.awt.Dimension(708, 400)); // Set a reasonable fixed height
        jScrollPane1.setMinimumSize(new java.awt.Dimension(708, 200)); // Set a minimum height
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        profileIcon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        fcaribarang = new javax.swing.JTextField();
        fbtncari = new javax.swing.JButton();
        fbtnreset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ftblbarang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("List Barang");
        setBackground(new java.awt.Color(250, 250, 250));

        jPanel1.setBackground(new java.awt.Color(237, 255, 249));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("Toko");

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 142, 110));
        jLabel2.setText("Aja.");

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(123, 122, 120));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/Frame-1.png"))); // NOI18N
        jLabel3.setText(" Dashboard");
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(50, 142, 110));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/box-1.png"))); // NOI18N
        jLabel4.setText(" List Barang");
        jLabel4.setToolTipText("");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(123, 122, 120));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_manajemen_toko/icons/transaction.png"))); // NOI18N
        jLabel5.setText(" Transaksi");
        jLabel5.setToolTipText("");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(123, 122, 120));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_manajemen_toko/icons/product-management.png"))); // NOI18N
        jLabel6.setText(" Return Barang");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(123, 122, 120));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/transaction-history-stroke-rounded 1.png"))); // NOI18N
        jLabel7.setText(" Histori Transaksi");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel8.setText("Nabila");
        jLabel8.setToolTipText("");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(123, 122, 120));
        jLabel9.setText("Kasir");
        jLabel9.setToolTipText("");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(123, 122, 120));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/logout.png"))); // NOI18N
        jLabel10.setText("Logout");
        jLabel10.setToolTipText("");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons/profile-img.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(profileIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(profileIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(24, 24, 24)
                .addComponent(jLabel10)
                .addGap(41, 41, 41))
        );

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(50, 142, 110));
        jLabel11.setText("List Barang");

        fcaribarang.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        fcaribarang.setToolTipText("Cari barang berdasarkan nama...");

        fbtncari.setBackground(new java.awt.Color(50, 142, 110));
        fbtncari.setFont(new java.awt.Font("Poppins", 1, 15)); // NOI18N
        fbtncari.setForeground(new java.awt.Color(255, 255, 255));
        fbtncari.setText("Cari");
        fbtncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbtncariActionPerformed(evt);
            }
        });

        fbtnreset.setBackground(new java.awt.Color(50, 142, 110));
        fbtnreset.setFont(new java.awt.Font("Poppins", 1, 15)); // NOI18N
        fbtnreset.setForeground(new java.awt.Color(255, 255, 255));
        fbtnreset.setText("Reset");
        fbtnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbtnresetActionPerformed(evt);
            }
        });

        ftblbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Stok", "Harga Satuan", "Terakhir Diperbarui"
            }
        ));
        jScrollPane1.setViewportView(ftblbarang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fcaribarang, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(fbtncari)
                        .addGap(18, 18, 18)
                        .addComponent(fbtnreset))
                    .addComponent(jLabel11))
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fcaribarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fbtncari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fbtnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        DashboardKasir dsb = new DashboardKasir();
        dsb.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        int idUser = Login.Session.getUserId();;
        TransaksiKasir transaksi = new TransaksiKasir(idUser);
        transaksi.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        ReturnBarang rtrB = new ReturnBarang();
        rtrB.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        HistoriKasir hst = new HistoriKasir();
        hst.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void fbtncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbtncariActionPerformed
        // TODO add your handling code here:
        String keyword = fcaribarang.getText().trim();
        LoadDataBarang(keyword);
    }//GEN-LAST:event_fbtncariActionPerformed

    private void fbtnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbtnresetActionPerformed
        // TODO add your handling code here:
        fcaribarang.setText(""); // Kosongkan inputan cari
        LoadDataBarang(""); //tampilkan semua data
    }//GEN-LAST:event_fbtnresetActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Login.Session.clearSession();
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void LoadDataBarang(String keyword) {
        DefaultTableModel tbl_model = (DefaultTableModel) ftblbarang.getModel();
        tbl_model.setRowCount(0);

        try {
            java.sql.Connection Vconn = Koneksi.konfigDB();
            String sql = "SELECT * FROM items WHERE nama_item LIKE ?";
            java.sql.PreparedStatement ps = Vconn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            java.sql.ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("id_item"),
                    rs.getString("nama_item"),
                    rs.getInt("stok"),
                    rs.getDouble("harga"),
                    rs.getTimestamp("created_at")
                };
                tbl_model.addRow(row);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load data barang: " + e.getMessage());
        }
    }

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
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListBarang().setVisible(true);
            }
        });
    }
    
                private void loadnama() {
                int idUser = Login.Session.getUserId();
                String nama = "";
                try {
                    java.sql.Connection Vconn = (Connection)Koneksi.konfigDB();
                    String sql = "SELECT nama FROM users WHERE id_user = ?";
                    java.sql.PreparedStatement s = Vconn.prepareStatement(sql);
                    s.setInt(1, idUser);
                    ResultSet rs = s.executeQuery();

                    if (rs.next()) {
                        nama = rs.getString("nama");
                    }

                    rs.close();
                    s.close();
                    Vconn.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                jLabel8.setText(""+nama+"");
            }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fbtncari;
    private javax.swing.JButton fbtnreset;
    private javax.swing.JTextField fcaribarang;
    private javax.swing.JTable ftblbarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel profileIcon;
    // End of variables declaration//GEN-END:variables
}
