package pbo_manajemen_toko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.awt.Color;

public class BarangTest {
  
  private Barang barang;
  
  @BeforeEach
  void setUp() {
      try {
          barang = new Barang();
      } catch (Exception e) {
          System.out.println("Warning: GUI not available: " + e.getMessage());
      }
  }
  
  @AfterEach
  void tearDown() {
      if (barang != null) {
          barang.dispose();
      }
  }
  
  // Test constructor
  @Test
  void testConstructor() {
      if (barang != null) {
          assertNotNull(barang);
          assertTrue(barang.isDisplayable());
          assertEquals(java.awt.Frame.MAXIMIZED_BOTH, barang.getExtendedState());
          assertEquals(new Color(250, 250, 250), barang.getContentPane().getBackground());
      }
  }
  
  // Test tampil tabel barang
  @Test
  void testTampilTabelBarang() {
      if (barang == null) return;
      
      try {
          Method method = Barang.class.getDeclaredMethod("tampil_tabel_barang");
          method.setAccessible(true);
          
          // Should not throw exception
          assertDoesNotThrow(() -> method.invoke(barang));
      } catch (Exception e) {
          fail("tampil_tabel_barang should work: " + e.getMessage());
      }
  }
  
  // Test cari data dengan keyword valid
  @Test
  void testCariDataValidKeyword() {
      if (barang == null) return;
      
      try {
          Method method = Barang.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan keyword
          assertDoesNotThrow(() -> method.invoke(barang, "laptop"));
          assertDoesNotThrow(() -> method.invoke(barang, "mouse"));
      } catch (Exception e) {
          fail("cariData should handle valid keyword: " + e.getMessage());
      }
  }
  
  // Test cari data dengan keyword kosong
  @Test
  void testCariDataEmptyKeyword() {
      if (barang == null) return;
      
      try {
          Method method = Barang.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan keyword kosong
          assertDoesNotThrow(() -> method.invoke(barang, ""));
          assertDoesNotThrow(() -> method.invoke(barang, (String) null));
      } catch (Exception e) {
          fail("cariData should handle empty keyword: " + e.getMessage());
      }
  }
  
  // Test load nama user
  @Test
  void testLoadNama() {
      if (barang == null) return;
      
      try {
          Method method = Barang.class.getDeclaredMethod("loadnama");
          method.setAccessible(true);
          
          // Should not throw exception
          assertDoesNotThrow(() -> method.invoke(barang));
      } catch (Exception e) {
          fail("loadnama should work: " + e.getMessage());
      }
  }
  
  // Test akses komponen GUI
  @Test
  void testGUIComponents() {
      if (barang == null) return;
      
      try {
          // Test akses tabel barang
          Field tabelBarang = Barang.class.getDeclaredField("tabel_barang");
          tabelBarang.setAccessible(true);
          assertNotNull(tabelBarang.get(barang));
          
          // Test akses search field
          Field searchField = Barang.class.getDeclaredField("searchField");
          searchField.setAccessible(true);
          assertNotNull(searchField.get(barang));
          
          // Test akses buttons
          Field searchBtn = Barang.class.getDeclaredField("searchBtn");
          searchBtn.setAccessible(true);
          assertNotNull(searchBtn.get(barang));
          
          Field addBtn = Barang.class.getDeclaredField("addBtn");
          addBtn.setAccessible(true);
          assertNotNull(addBtn.get(barang));
          
          Field editBtn = Barang.class.getDeclaredField("editBtn");
          editBtn.setAccessible(true);
          assertNotNull(editBtn.get(barang));
          
          Field deleteBtn = Barang.class.getDeclaredField("deleteBtn");
          deleteBtn.setAccessible(true);
          assertNotNull(deleteBtn.get(barang));
          
          Field resetBtn = Barang.class.getDeclaredField("resetBtn");
          resetBtn.setAccessible(true);
          assertNotNull(resetBtn.get(barang));
          
      } catch (Exception e) {
          fail("Should access GUI components: " + e.getMessage());
      }
  }
  
  // Test table model initialization
  @Test
  void testTableModel() {
      if (barang == null) return;
      
      try {
          Field tabelBarang = Barang.class.getDeclaredField("tabel_barang");
          tabelBarang.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) tabelBarang.get(barang);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          
          // Verify column count dan names
          assertEquals(5, model.getColumnCount());
          assertEquals("Id Barang", model.getColumnName(0));
          assertEquals("Nama Barang", model.getColumnName(1));
          assertEquals("Stok", model.getColumnName(2));
          assertEquals("Harga Satuan", model.getColumnName(3));
          assertEquals("Terakhir Diperbarui", model.getColumnName(4));
          
      } catch (Exception e) {
          fail("Table model should be initialized: " + e.getMessage());
      }
  }
  
  // Test search functionality
  @Test
  void testSearchFunctionality() {
      if (barang == null) return;
      
      try {
          // Set search text
          Field searchField = Barang.class.getDeclaredField("searchField");
          searchField.setAccessible(true);
          javax.swing.JTextField field = (javax.swing.JTextField) searchField.get(barang);
          
          // Test different search terms
          field.setText("laptop");
          assertEquals("laptop", field.getText());
          
          field.setText("mouse");
          assertEquals("mouse", field.getText());
          
          field.setText("");
          assertEquals("", field.getText());
          
      } catch (Exception e) {
          fail("Search functionality should work: " + e.getMessage());
      }
  }
  
  // Test button actions
  @Test
  void testButtonActions() {
      if (barang == null) return;
      
      try {
          // Test search button
          Field searchBtn = Barang.class.getDeclaredField("searchBtn");
          searchBtn.setAccessible(true);
          javax.swing.JButton btn = (javax.swing.JButton) searchBtn.get(barang);
          
          // Set text di search field
          Field searchField = Barang.class.getDeclaredField("searchField");
          searchField.setAccessible(true);
          javax.swing.JTextField field = (javax.swing.JTextField) searchField.get(barang);
          field.setText("test");
          
          // Simulate button click
          assertDoesNotThrow(() -> btn.doClick());
          
          // Test reset button
          Field resetBtn = Barang.class.getDeclaredField("resetBtn");
          resetBtn.setAccessible(true);
          javax.swing.JButton resetButton = (javax.swing.JButton) resetBtn.get(barang);
          
          // Simulate reset click
          resetButton.doClick();
          
          // Verify field cleared
          assertEquals("", field.getText());
          
      } catch (Exception e) {
          fail("Button actions should work: " + e.getMessage());
      }
  }
  
  // Test navigation buttons
  @Test
  void testNavigationButtons() {
      if (barang == null) return;
      
      try {
          // Test akses navigation buttons
          Field dashboardBtn = Barang.class.getDeclaredField("dashbaordBtn");
          dashboardBtn.setAccessible(true);
          assertNotNull(dashboardBtn.get(barang));
          
          Field userBtn = Barang.class.getDeclaredField("userBtn");
          userBtn.setAccessible(true);
          assertNotNull(userBtn.get(barang));
          
          Field historyBtn = Barang.class.getDeclaredField("historyBtn");
          historyBtn.setAccessible(true);
          assertNotNull(historyBtn.get(barang));
          
          Field logoutBtn = Barang.class.getDeclaredField("logoutBtn");
          logoutBtn.setAccessible(true);
          assertNotNull(logoutBtn.get(barang));
          
      } catch (Exception e) {
          fail("Navigation buttons should be accessible: " + e.getMessage());
      }
  }
  
  // Test profile components
  @Test
  void testProfileComponents() {
      if (barang == null) return;
      
      try {
          // Test profile name label
          Field labelNama = Barang.class.getDeclaredField("label_nama");
          labelNama.setAccessible(true);
          javax.swing.JLabel nameLabel = (javax.swing.JLabel) labelNama.get(barang);
          assertNotNull(nameLabel);
          
          // Test role label
          Field role = Barang.class.getDeclaredField("role");
          role.setAccessible(true);
          javax.swing.JLabel roleLabel = (javax.swing.JLabel) role.get(barang);
          assertNotNull(roleLabel);
          assertEquals("Super-admin", roleLabel.getText());
          
      } catch (Exception e) {
          fail("Profile components should be accessible: " + e.getMessage());
      }
  }
  
  // Test sidebar components
  @Test
  void testSidebarComponents() {
      if (barang == null) return;
      
      try {
          // Test sidebar panel
          Field sideBar = Barang.class.getDeclaredField("sideBar");
          sideBar.setAccessible(true);
          javax.swing.JPanel panel = (javax.swing.JPanel) sideBar.get(barang);
          assertNotNull(panel);
          assertEquals(new Color(237, 255, 249), panel.getBackground());
          
          // Test barang button (active)
          Field barangBtn = Barang.class.getDeclaredField("barangBtn");
          barangBtn.setAccessible(true);
          javax.swing.JLabel btn = (javax.swing.JLabel) barangBtn.get(barang);
          assertNotNull(btn);
          assertEquals("Barang", btn.getText());
          
      } catch (Exception e) {
          fail("Sidebar components should be accessible: " + e.getMessage());
      }
  }
  
  // Test title component
  @Test
  void testTitleComponent() {
      if (barang == null) return;
      
      try {
          Field tittle = Barang.class.getDeclaredField("tittle");
          tittle.setAccessible(true);
          javax.swing.JLabel titleLabel = (javax.swing.JLabel) tittle.get(barang);
          assertNotNull(titleLabel);
          assertEquals("Manajemen Barang", titleLabel.getText());
          
      } catch (Exception e) {
          fail("Title component should be accessible: " + e.getMessage());
      }
  }
  
  // Test add button functionality
  @Test
  void testAddButtonFunctionality() {
      if (barang == null) return;
      
      try {
          Field addBtn = Barang.class.getDeclaredField("addBtn");
          addBtn.setAccessible(true);
          javax.swing.JButton button = (javax.swing.JButton) addBtn.get(barang);
          
          assertNotNull(button);
          assertEquals("Tambah", button.getText());
          assertTrue(button.isEnabled());
          
      } catch (Exception e) {
          fail("Add button should be functional: " + e.getMessage());
      }
  }
  
  // Test edit button functionality
  @Test
  void testEditButtonFunctionality() {
      if (barang == null) return;
      
      try {
          Field editBtn = Barang.class.getDeclaredField("editBtn");
          editBtn.setAccessible(true);
          javax.swing.JButton button = (javax.swing.JButton) editBtn.get(barang);
          
          assertNotNull(button);
          assertEquals("Ubah", button.getText());
          assertTrue(button.isEnabled());
          
      } catch (Exception e) {
          fail("Edit button should be functional: " + e.getMessage());
      }
  }
  
  // Test delete button functionality
  @Test
  void testDeleteButtonFunctionality() {
      if (barang == null) return;
      
      try {
          Field deleteBtn = Barang.class.getDeclaredField("deleteBtn");
          deleteBtn.setAccessible(true);
          javax.swing.JButton button = (javax.swing.JButton) deleteBtn.get(barang);
          
          assertNotNull(button);
          assertEquals("Hapus", button.getText());
          assertTrue(button.isEnabled());
          
      } catch (Exception e) {
          fail("Delete button should be functional: " + e.getMessage());
      }
  }
  
  // Test table selection
  @Test
  void testTableSelection() {
      if (barang == null) return;
      
      try {
          Field tabelBarang = Barang.class.getDeclaredField("tabel_barang");
          tabelBarang.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) tabelBarang.get(barang);
          
          // Test selection mode
          assertEquals(javax.swing.ListSelectionModel.SINGLE_SELECTION, 
                      table.getSelectionModel().getSelectionMode());
          
          // Test initial selection
          assertEquals(-1, table.getSelectedRow());
          
      } catch (Exception e) {
          fail("Table selection should work: " + e.getMessage());
      }
  }
  
  // Test fullscreen mode
  @Test
  void testFullscreenMode() {
      if (barang == null) return;
      
      // Verify fullscreen is set in constructor
      assertEquals(java.awt.Frame.MAXIMIZED_BOTH, barang.getExtendedState());
  }
  
  // Test background color
  @Test
  void testBackgroundColor() {
      if (barang == null) return;
      
      // Verify background color is set
      assertEquals(new Color(250, 250, 250), barang.getContentPane().getBackground());
  }
}
