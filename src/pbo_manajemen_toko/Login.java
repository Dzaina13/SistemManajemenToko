/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pbo_manajemen_toko;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.Component;
import java.awt.Insets;

/**
 *
 * @author dzaina
 */
public class Login extends javax.swing.JFrame {

  // Variable untuk menyimpan data user yang login
  private static int loggedInUserId = -1;
  private static String loggedInUsername = "";
  private static String loggedInRole = ""; 

  /**
   * Creates new form Login
   */
  public Login() {
      initComponents();
      setupDesign();
      setupPlaceholders();
  }
  
  // Getter methods untuk data user yang login
  public static int getLoggedInUserId() {
      return loggedInUserId;
  }
  
  public static String getLoggedInUsername() {
      return loggedInUsername;
  }
  
  public static String getLoggedInRole() {
      return loggedInRole;
  }
  
  /**
   * Setup desain dengan panel hijau seperti di gambar SVG
   */
  private void setupDesign() {
      // Background utama putih
      getContentPane().setBackground(Color.WHITE);
      setBackground(Color.WHITE);
      
      // Setup panel hijau (sebelah kanan)
      rightPanel.setBackground(new Color(50, 142, 110)); // #328E6E
      
      // Setup left panel (form login)
      leftPanel.setBackground(Color.WHITE);
      
      // Setup brand label
      brandLabel.setFont(new Font("Poppins", Font.BOLD, 24));
      brandLabel.setForeground(new Color(50, 142, 110));
      
      // Setup title
      titleLabel.setFont(new Font("Poppins", Font.BOLD, 18));
      titleLabel.setForeground(new Color(51, 51, 50)); // #333332
      
      // Setup subtitle
      subtitleLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
      subtitleLabel.setForeground(new Color(120, 120, 120));
      
      // Setup input fields
      femail.setFont(new Font("Poppins", Font.PLAIN, 14));
      femail.setBorder(new RoundedBorder(8, new Color(200, 200, 200)));
      femail.setBackground(new Color(248, 248, 248));
      
      fpass.setFont(new Font("Poppins", Font.PLAIN, 14));
      fpass.setBorder(new RoundedBorder(8, new Color(200, 200, 200)));
      fpass.setBackground(new Color(248, 248, 248));
      
      // Setup login button
      loginButton.setFont(new Font("Poppins", Font.BOLD, 14));
      loginButton.setBackground(new Color(50, 142, 110));
      loginButton.setForeground(Color.WHITE);
      loginButton.setBorder(new RoundedBorder(8, new Color(50, 142, 110)));
      loginButton.setFocusPainted(false);
      loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
  }
  
  /**
   * Setup placeholder text untuk input fields
   */
  private void setupPlaceholders() {
      // Placeholder untuk email
      if (femail.getText().isEmpty()) {
          femail.setText("Masukkan email Anda");
          femail.setForeground(Color.GRAY);
      }
      
      // Placeholder untuk password
      if (fpass.getPassword().length == 0) {
          fpass.setText("Masukkan password Anda");
          fpass.setForeground(Color.GRAY);
          fpass.setEchoChar((char) 0);
      }
      
      // Focus listeners untuk email
      femail.addFocusListener(new java.awt.event.FocusAdapter() {
          public void focusGained(java.awt.event.FocusEvent evt) {
              if (femail.getText().equals("Masukkan email Anda")) {
                  femail.setText("");
                  femail.setForeground(Color.BLACK);
              }
          }
          public void focusLost(java.awt.event.FocusEvent evt) {
              if (femail.getText().isEmpty()) {
                  femail.setText("Masukkan email Anda");
                  femail.setForeground(Color.GRAY);
              }
          }
      });
      
      // Focus listeners untuk password
      fpass.addFocusListener(new java.awt.event.FocusAdapter() {
          public void focusGained(java.awt.event.FocusEvent evt) {
              if (String.valueOf(fpass.getPassword()).equals("Masukkan password Anda")) {
                  fpass.setText("");
                  fpass.setForeground(Color.BLACK);
                  fpass.setEchoChar('*');
              }
          }
          public void focusLost(java.awt.event.FocusEvent evt) {
              if (fpass.getPassword().length == 0) {
                  fpass.setText("Masukkan password Anda");
                  fpass.setForeground(Color.GRAY);
                  fpass.setEchoChar((char) 0);
              }
          }
      });
  }
  
  /**
   * Custom rounded border class
   */
  class RoundedBorder extends AbstractBorder {
      private int radius;
      private Color color;
      
      public RoundedBorder(int radius, Color color) {
          this.radius = radius;
          this.color = color;
      }
      
      @Override
      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
          Graphics2D g2d = (Graphics2D) g.create();
          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2d.setColor(color);
          g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
          g2d.dispose();
      }
      
      @Override
      public Insets getBorderInsets(Component c) {
          return new Insets(10, 15, 10, 15);
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

        leftPanel = new javax.swing.JPanel();
        brandLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        subtitleLabel = new javax.swing.JLabel();
        femail = new javax.swing.JTextField();
        fpass = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        subtitleLabel1 = new javax.swing.JLabel();
        titleLabel1 = new javax.swing.JLabel();
        titleLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Toko Aja");
        setResizable(false);

        leftPanel.setBackground(new java.awt.Color(255, 255, 255));

        brandLabel.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        brandLabel.setForeground(new java.awt.Color(50, 142, 110));
        brandLabel.setText("Toko Aja.");

        titleLabel.setFont(new java.awt.Font("Poppins", 1, 20)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(51, 51, 50));
        titleLabel.setText("Selamat Datang di Toko Aja.");

        subtitleLabel.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N
        subtitleLabel.setForeground(new java.awt.Color(120, 120, 120));
        subtitleLabel.setText("Masukkan akun Anda untuk mulai menggunakan ");

        femail.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        femail.setForeground(new java.awt.Color(102, 102, 102));
        femail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true), javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        femail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femailActionPerformed(evt);
            }
        });

        fpass.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        fpass.setForeground(new java.awt.Color(102, 102, 102));
        fpass.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true), javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        fpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fpassActionPerformed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(50, 142, 110));
        loginButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Masuk");
        loginButton.setBorder(null);
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        rightPanel.setBackground(new java.awt.Color(50, 142, 110));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        subtitleLabel1.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N
        subtitleLabel1.setForeground(new java.awt.Color(120, 120, 120));
        subtitleLabel1.setText("semua fitur di TokoAja.");

        titleLabel1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        titleLabel1.setForeground(new java.awt.Color(51, 51, 50));
        titleLabel1.setText("Username");

        titleLabel2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        titleLabel2.setForeground(new java.awt.Color(51, 51, 50));
        titleLabel2.setText("Password");

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(brandLabel))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subtitleLabel)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(subtitleLabel1))))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(femail)
                            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fpass)
                            .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titleLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(brandLabel)
                        .addGap(135, 135, 135)
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subtitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subtitleLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(titleLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(titleLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fpass, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
      String email = femail.getText().trim();
      String password = String.valueOf(fpass.getPassword());
      
      // Validasi input kosong
      if (email.isEmpty() || email.equals("Masukkan email Anda")) {
          JOptionPane.showMessageDialog(this, "Email tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }
      
      if (password.isEmpty() || password.equals("Masukkan password Anda")) {
          JOptionPane.showMessageDialog(this, "Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }
      
      // Hash password sebelum validasi
//      String hashedPassword = hashPassword(password);
      
      try {
          // Gunakan Koneksi.konfigDB() sesuai dengan class database kamu
          Connection conn = Koneksi.konfigDB();
          String sql = "SELECT id_user, username, role FROM users WHERE username = ? AND password = ?";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, email);
          pstmt.setString(2, password);
          
          ResultSet rs = pstmt.executeQuery();
          
         if (rs.next()) {
            // Login berhasil
            loggedInUserId = rs.getInt("id_user");
            loggedInUsername = rs.getString("username");
            loggedInRole = rs.getString("role");
            
            // Set session
            Session.setSession(loggedInUserId, loggedInUsername, loggedInRole);

            JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang " + loggedInUsername, "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Redirect berdasarkan role
            switch (loggedInRole.toLowerCase()) {
                case "kasir":
                    new DashboardKasir().setVisible(true);
                    
                    break;

                case "super admin":
                    new Dashboard().setVisible(true);
                   
                    break;
                 
            }

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Email atau password salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }

          
          rs.close();
          pstmt.close();
          conn.close();
          
      } catch (SQLException e) {
          JOptionPane.showMessageDialog(this, "Error koneksi database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
      }
  }//GEN-LAST:event_loginButtonActionPerformed

    private void femailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femailActionPerformed

    private void fpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fpassActionPerformed

    public class Session {
        private static int userId;
        private static String username;
        private static String role;

        public static void setSession(int id, String user, String r) {
            userId = id;
            username = user;
            role = r;
        }

        public static int getUserId() {
            return userId;
        }

        public static String getUsername() {
            return username;
        }

        public static String getRole() {
            return role;
        }

        public static void clearSession() {
            userId = 0;
            username = null;
            role = null;
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
          java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
              new Login().setVisible(true);
          }
      });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JTextField femail;
    private javax.swing.JPasswordField fpass;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel subtitleLabel;
    private javax.swing.JLabel subtitleLabel1;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    // End of variables declaration//GEN-END:variables
}
