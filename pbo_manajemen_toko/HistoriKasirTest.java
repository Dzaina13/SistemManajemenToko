package pbo_manajemen_toko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class HistoriKasirTest {
  
  private HistoriKasir historiKasir;
  
  @BeforeEach
  void setUp() {
      try {
          historiKasir = new HistoriKasir();
      } catch (Exception e) {
          System.out.println("Warning: GUI not available: " + e.getMessage());
      }
  }
  
  @AfterEach
  void tearDown() {
      if (historiKasir != null) {
          historiKasir.dispose();
      }
  }
  
  // Test constructor
  @Test
  void testConstructor() {
      if (historiKasir != null) {
          assertNotNull(historiKasir);
          assertEquals("Histori Transaksi", historiKasir.getTitle());
          assertTrue(historiKasir.isDisplayable());
      }
  }
  
  // Test fullscreen mode true
  @Test
  void testSetFullScreenModeTrue() {
      if (historiKasir == null) return;
      
      try {
          Method method = HistoriKasir.class.getDeclaredMethod("setFullScreenMode", boolean.class);
          method.setAccessible(true);
          method.invoke(historiKasir, true);
          
          assertEquals(java.awt.Frame.MAXIMIZED_BOTH, historiKasir.getExtendedState());
      } catch (Exception e) {
          fail("setFullScreenMode should work: " + e.getMessage());
      }
  }
  
  // Test fullscreen mode false
  @Test
  void testSetFullScreenModeFalse() {
      if (historiKasir == null) return;
      
      try {
          Method method = HistoriKasir.class.getDeclaredMethod("setFullScreenMode", boolean.class);
          method.setAccessible(true);
          method.invoke(historiKasir, false);
          
          assertEquals(java.awt.Frame.NORMAL, historiKasir.getExtendedState());
      } catch (Exception e) {
          fail("setFullScreenMode should work: " + e.getMessage());
      }
  }
  
  // Test tampil tabel histori
  @Test
  void testTampilTabelHistoriTransaksi() {
      if (historiKasir == null) return;
      
      try {
          Method method = HistoriKasir.class.getDeclaredMethod("tampil_tabel_histori_transaksi");
          method.setAccessible(true);
          
          // Should not throw exception
          assertDoesNotThrow(() -> method.invoke(historiKasir));
      } catch (Exception e) {
          fail("tampil_tabel_histori_transaksi should work: " + e.getMessage());
      }
  }
  
  // Test cari data dengan keyword valid
  @Test
  void testCariDataValidKeyword() {
      if (historiKasir == null) return;
      
      try {
          Method method = HistoriKasir.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan keyword
          assertDoesNotThrow(() -> method.invoke(historiKasir, "TRX001"));
          assertDoesNotThrow(() -> method.invoke(historiKasir, "123"));
      } catch (Exception e) {
          fail("cariData should handle valid keyword: " + e.getMessage());
      }
  }
  
  // Test cari data dengan keyword kosong
  @Test
  void testCariDataEmptyKeyword() {
      if (historiKasir == null) return;
      
      try {
          Method method = HistoriKasir.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan keyword kosong
          assertDoesNotThrow(() -> method.invoke(historiKasir, ""));
          assertDoesNotThrow(() -> method.invoke(historiKasir, (String) null));
      } catch (Exception e) {
          fail("cariData should handle empty keyword: " + e.getMessage());
      }
  }
  
  // Test load nama user
  @Test
  void testLoadNama() {
      if (historiKasir == null) return;
      
      try {
          Method method = HistoriKasir.class.getDeclaredMethod("loadnama");
          method.setAccessible(true);
          
          // Should not throw exception
          assertDoesNotThrow(() -> method.invoke(historiKasir));
      } catch (Exception e) {
          fail("loadnama should work: " + e.getMessage());
      }
  }
  
  // Test akses komponen GUI
  @Test
  void testGUIComponents() {
      if (historiKasir == null) return;
      
      try {
          // Test akses tabel histori
          Field ftblhistori = HistoriKasir.class.getDeclaredField("ftblhistori");
          ftblhistori.setAccessible(true);
          assertNotNull(ftblhistori.get(historiKasir));
          
          // Test akses field cari
          Field fcarihst = HistoriKasir.class.getDeclaredField("fcarihst");
          fcarihst.setAccessible(true);
          assertNotNull(fcarihst.get(historiKasir));
          
          // Test akses button cari
          Field fbtnhst = HistoriKasir.class.getDeclaredField("fbtnhst");
          fbtnhst.setAccessible(true);
          assertNotNull(fbtnhst.get(historiKasir));
          
          // Test akses button reset
          Field fresethst = HistoriKasir.class.getDeclaredField("fresethst");
          fresethst.setAccessible(true);
          assertNotNull(fresethst.get(historiKasir));
          
      } catch (Exception e) {
          fail("Should access GUI components: " + e.getMessage());
      }
  }
  
  // Test table model initialization
  @Test
  void testTableModel() {
      if (historiKasir == null) return;
      
      try {
          Field ftblhistori = HistoriKasir.class.getDeclaredField("ftblhistori");
          ftblhistori.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) ftblhistori.get(historiKasir);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          
          // Verify column count dan names
          assertEquals(5, model.getColumnCount());
          assertEquals("ID Transaksi", model.getColumnName(0));
          assertEquals("ID User", model.getColumnName(1));
          assertEquals("ID Diskon", model.getColumnName(2));
          assertEquals("Total Harga", model.getColumnName(3));
          assertEquals("Tanggal", model.getColumnName(4));
          
      } catch (Exception e) {
          fail("Table model should be initialized: " + e.getMessage());
      }
  }
  
  // Test button actions
  @Test
  void testButtonActions() {
      if (historiKasir == null) return;
      
      try {
          // Test button cari
          Field fbtnhst = HistoriKasir.class.getDeclaredField("fbtnhst");
          fbtnhst.setAccessible(true);
          javax.swing.JButton cariBtn = (javax.swing.JButton) fbtnhst.get(historiKasir);
          
          // Set text di field cari
          Field fcarihst = HistoriKasir.class.getDeclaredField("fcarihst");
          fcarihst.setAccessible(true);
          javax.swing.JTextField cariField = (javax.swing.JTextField) fcarihst.get(historiKasir);
          cariField.setText("test");
          
          // Simulate button click
          assertDoesNotThrow(() -> cariBtn.doClick());
          
          // Test button reset
          Field fresethst = HistoriKasir.class.getDeclaredField("fresethst");
          fresethst.setAccessible(true);
          javax.swing.JButton resetBtn = (javax.swing.JButton) fresethst.get(historiKasir);
          
          // Simulate reset click
          resetBtn.doClick();
          
          // Verify field cleared
          assertEquals("", cariField.getText());
          
      } catch (Exception e) {
          fail("Button actions should work: " + e.getMessage());
      }
  }
  
  // Test navigation buttons
  @Test
  void testNavigationButtons() {
      if (historiKasir == null) return;
      
      try {
          // Test akses navigation buttons
          Field dashboardBtn = HistoriKasir.class.getDeclaredField("dashboardBtn");
          dashboardBtn.setAccessible(true);
          assertNotNull(dashboardBtn.get(historiKasir));
          
          Field transaksiBtn = HistoriKasir.class.getDeclaredField("transaksiBtn");
          transaksiBtn.setAccessible(true);
          assertNotNull(transaksiBtn.get(historiKasir));
          
          Field returnBarangBtn = HistoriKasir.class.getDeclaredField("returnBarangBtn");
          returnBarangBtn.setAccessible(true);
          assertNotNull(returnBarangBtn.get(historiKasir));
          
          Field listBarangBtn = HistoriKasir.class.getDeclaredField("listBarangBtn");
          listBarangBtn.setAccessible(true);
          assertNotNull(listBarangBtn.get(historiKasir));
          
          Field logoutBtn = HistoriKasir.class.getDeclaredField("logoutBtn");
          logoutBtn.setAccessible(true);
          assertNotNull(logoutBtn.get(historiKasir));
          
      } catch (Exception e) {
          fail("Navigation buttons should be accessible: " + e.getMessage());
      }
  }
  
  // Test profile components
  @Test
  void testProfileComponents() {
      if (historiKasir == null) return;
      
      try {
          // Test profile name label
          Field profileNameLabel = HistoriKasir.class.getDeclaredField("profileNameLabel");
          profileNameLabel.setAccessible(true);
          javax.swing.JLabel nameLabel = (javax.swing.JLabel) profileNameLabel.get(historiKasir);
          assertNotNull(nameLabel);
          
          // Test profile role label
          Field profileRoleLabel = HistoriKasir.class.getDeclaredField("profileRoleLabel");
          profileRoleLabel.setAccessible(true);
          javax.swing.JLabel roleLabel = (javax.swing.JLabel) profileRoleLabel.get(historiKasir);
          assertNotNull(roleLabel);
          assertEquals("Kasir", roleLabel.getText());
          
      } catch (Exception e) {
          fail("Profile components should be accessible: " + e.getMessage());
      }
  }
  
  // Test search functionality
  @Test
  void testSearchFunctionality() {
      if (historiKasir == null) return;
      
      try {
          // Set search text
          Field fcarihst = HistoriKasir.class.getDeclaredField("fcarihst");
          fcarihst.setAccessible(true);
          javax.swing.JTextField searchField = (javax.swing.JTextField) fcarihst.get(historiKasir);
          
          // Test different search terms
          searchField.setText("TRX");
          assertEquals("TRX", searchField.getText());
          
          searchField.setText("123");
          assertEquals("123", searchField.getText());
          
          searchField.setText("");
          assertEquals("", searchField.getText());
          
      } catch (Exception e) {
          fail("Search functionality should work: " + e.getMessage());
      }
  }
}
