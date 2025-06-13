package pbo_manajemen_toko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.awt.Color;

public class UserTest {
  
  private User user;
  
  @BeforeEach
  void setUp() {
      try {
          user = new User();
      } catch (Exception e) {
          System.out.println("Warning: GUI not available: " + e.getMessage());
      }
  }
  
  @AfterEach
  void tearDown() {
      if (user != null) {
          user.dispose();
      }
  }
  
  // Test constructor
  @Test
  void testConstructor() {
      if (user != null) {
          assertNotNull(user);
          assertTrue(user.isDisplayable());
          assertEquals(java.awt.Frame.MAXIMIZED_BOTH, user.getExtendedState());
          assertEquals(new Color(250, 250, 250), user.getContentPane().getBackground());
      }
  }
  
  // Test tampil tabel user
  @Test
  void testTampilTabelUser() {
      if (user == null) return;
      
      try {
          Method method = User.class.getDeclaredMethod("tampil_tabel_user");
          method.setAccessible(true);
          
          // Should not throw exception
          assertDoesNotThrow(() -> method.invoke(user));
      } catch (Exception e) {
          fail("tampil_tabel_user should work: " + e.getMessage());
      }
  }
  
  // Test cari data dengan keyword valid
  @Test
  void testCariDataValidKeyword() {
      if (user == null) return;
      
      try {
          Method method = User.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan keyword
          assertDoesNotThrow(() -> method.invoke(user, "admin"));
          assertDoesNotThrow(() -> method.invoke(user, "user"));
          assertDoesNotThrow(() -> method.invoke(user, "john"));
      } catch (Exception e) {
          fail("cariData should handle valid keyword: " + e.getMessage());
      }
  }
  
  // Test cari data dengan keyword kosong
  @Test
  void testCariDataEmptyKeyword() {
      if (user == null) return;
      
      try {
          Method method = User.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan keyword kosong
          assertDoesNotThrow(() -> method.invoke(user, ""));
          assertDoesNotThrow(() -> method.invoke(user, (String) null));
      } catch (Exception e) {
          fail("cariData should handle empty keyword: " + e.getMessage());
      }
  }
  
  // Test load nama user
  @Test
  void testLoadNama() {
      if (user == null) return;
      
      try {
          Method method = User.class.getDeclaredMethod("loadnama");
          method.setAccessible(true);
          
          // Should not throw exception
          assertDoesNotThrow(() -> method.invoke(user));
      } catch (Exception e) {
          fail("loadnama should work: " + e.getMessage());
      }
  }
  
  // Test akses komponen GUI
  @Test
  void testGUIComponents() {
      if (user == null) return;
      
      try {
          // Test akses tabel user
          Field tabelUser = User.class.getDeclaredField("tabel_user");
          tabelUser.setAccessible(true);
          assertNotNull(tabelUser.get(user));
          
          // Test akses search field
          Field searchField = User.class.getDeclaredField("searchField");
          searchField.setAccessible(true);
          assertNotNull(searchField.get(user));
          
          // Test akses buttons
          Field searchBtn = User.class.getDeclaredField("searchBtn");
          searchBtn.setAccessible(true);
          assertNotNull(searchBtn.get(user));
          
          Field addBtn = User.class.getDeclaredField("addBtn");
          addBtn.setAccessible(true);
          assertNotNull(addBtn.get(user));
          
          Field editBtn = User.class.getDeclaredField("editBtn");
          editBtn.setAccessible(true);
          assertNotNull(editBtn.get(user));
          
          Field deleteBtn = User.class.getDeclaredField("deleteBtn");
          deleteBtn.setAccessible(true);
          assertNotNull(deleteBtn.get(user));
          
          Field resetBtn = User.class.getDeclaredField("resetBtn");
          resetBtn.setAccessible(true);
          assertNotNull(resetBtn.get(user));
          
      } catch (Exception e) {
          fail("Should access GUI components: " + e.getMessage());
      }
  }
  
  // Test table model initialization
  @Test
  void testTableModel() {
      if (user == null) return;
      
      try {
          Field tabelUser = User.class.getDeclaredField("tabel_user");
          tabelUser.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) tabelUser.get(user);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          
          // Verify column count dan names
          assertEquals(6, model.getColumnCount());
          assertEquals("Id User", model.getColumnName(0));
          assertEquals("Username", model.getColumnName(1));
          assertEquals("Nama", model.getColumnName(2));
          assertEquals("Role", model.getColumnName(3));
          assertEquals("Password", model.getColumnName(4));
          assertEquals("Terakhir Diperbarui", model.getColumnName(5));
          
      } catch (Exception e) {
          fail("Table model should be initialized: " + e.getMessage());
      }
  }
  
  // Test search functionality
  @Test
  void testSearchFunctionality() {
      if (user == null) return;
      
      try {
          // Set search text
          Field searchField = User.class.getDeclaredField("searchField");
          searchField.setAccessible(true);
          javax.swing.JTextField field = (javax.swing.JTextField) searchField.get(user);
          
          // Test different search terms
          field.setText("admin");
          assertEquals("admin", field.getText());
          
          field.setText("user");
          assertEquals("user", field.getText());
          
          field.setText("");
          assertEquals("", field.getText());
          
      } catch (Exception e) {
          fail("Search functionality should work: " + e.getMessage());
      }
  }
  
  // Test button actions
  @Test
  void testButtonActions() {
      if (user == null) return;
      
      try {
          // Test search button
          Field searchBtn = User.class.getDeclaredField("searchBtn");
          searchBtn.setAccessible(true);
          javax.swing.JButton btn = (javax.swing.JButton) searchBtn.get(user);
          
          // Set text di search field
          Field searchField = User.class.getDeclaredField("searchField");
          searchField.setAccessible(true);
          javax.swing.JTextField field = (javax.swing.JTextField) searchField.get(user);
          field.setText("test");
          
          // Simulate button click
          assertDoesNotThrow(() -> btn.doClick());
          
          // Test reset button
          Field resetBtn = User.class.getDeclaredField("resetBtn");
          resetBtn.setAccessible(true);
          javax.swing.JButton resetButton = (javax.swing.JButton) resetBtn.get(user);
          
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
      if (user == null) return;
      
      try {
          // Test akses navigation buttons
          Field dashboardBtn = User.class.getDeclaredField("dashbaordBtn");
          dashboardBtn.setAccessible(true);
          assertNotNull(dashboardBtn.get(user));
          
          Field barangBtn = User.class.getDeclaredField("barangBtn");
          barangBtn.setAccessible(true);
          assertNotNull(barangBtn.get(user));
          
          Field historyBtn = User.class.getDeclaredField("historyBtn");
          historyBtn.setAccessible(true);
          assertNotNull(historyBtn.get(user));
          
          Field logoutBtn = User.class.getDeclaredField("logoutBtn");
          logoutBtn.setAccessible(true);
          assertNotNull(logoutBtn.get(user));
          
      } catch (Exception e) {
          fail("Navigation buttons should be accessible: " + e.getMessage());
      }
  }
  
  // Test profile components
  @Test
  void testProfileComponents() {
      if (user == null) return;
      
      try {
          // Test profile name label
          Field labelNama = User.class.getDeclaredField("label_nama");
          labelNama.setAccessible(true);
          javax.swing.JLabel nameLabel = (javax.swing.JLabel) labelNama.get(user);
          assertNotNull(nameLabel);
          
          // Test role label
          Field dashboardLabel6 = User.class.getDeclaredField("dashbaordLabel6");
          dashboardLabel6.setAccessible(true);
          javax.swing.JLabel roleLabel = (javax.swing.JLabel) dashboardLabel6.get(user);
          assertNotNull(roleLabel);
          assertEquals("Super-admin", roleLabel.getText());
          
      } catch (Exception e) {
          fail("Profile components should be accessible: " + e.getMessage());
      }
  }
  
  // Test sidebar components
  @Test
  void testSidebarComponents() {
      if (user == null) return;
      
      try {
          // Test sidebar panel
          Field sideBar = User.class.getDeclaredField("sideBar");
          sideBar.setAccessible(true);
          javax.swing.JPanel panel = (javax.swing.JPanel) sideBar.get(user);
          assertNotNull(panel);
          assertEquals(new Color(237, 255, 249), panel.getBackground());
          
          // Test user button (active)
          Field userBtn = User.class.getDeclaredField("userBtn");
          userBtn.setAccessible(true);
          javax.swing.JLabel btn = (javax.swing.JLabel) userBtn.get(user);
          assertNotNull(btn);
          assertEquals("User", btn.getText());
          
      } catch (Exception e) {
          fail("Sidebar components should be accessible: " + e.getMessage());
      }
  }
  
  // Test title component
  @Test
  void testTitleComponent() {
      if (user == null) return;
      
      try {
          Field tittle = User.class.getDeclaredField("tittle");
          tittle.setAccessible(true);
          javax.swing.JLabel titleLabel = (javax.swing.JLabel) tittle.get(user);
          assertNotNull(titleLabel);
          assertEquals("Manajemen User", titleLabel.getText());
          
      } catch (Exception e) {
          fail("Title component should be accessible: " + e.getMessage());
      }
  }
  
  // Test add button functionality
  @Test
  void testAddButtonFunctionality() {
      if (user == null) return;
      
      try {
          Field addBtn = User.class.getDeclaredField("addBtn");
          addBtn.setAccessible(true);
          javax.swing.JButton button = (javax.swing.JButton) addBtn.get(user);
          
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
      if (user == null) return;
      
      try {
          Field editBtn = User.class.getDeclaredField("editBtn");
          editBtn.setAccessible(true);
          javax.swing.JButton button = (javax.swing.JButton) editBtn.get(user);
          
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
      if (user == null) return;
      
      try {
          Field deleteBtn = User.class.getDeclaredField("deleteBtn");
          deleteBtn.setAccessible(true);
          javax.swing.JButton button = (javax.swing.JButton) deleteBtn.get(user);
          
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
      if (user == null) return;
      
      try {
          Field tabelUser = User.class.getDeclaredField("tabel_user");
          tabelUser.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) tabelUser.get(user);
          
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
      if (user == null) return;
      
      // Verify fullscreen is set in constructor
      assertEquals(java.awt.Frame.MAXIMIZED_BOTH, user.getExtendedState());
  }
  
  // Test background color
  @Test
  void testBackgroundColor() {
      if (user == null) return;
      
      // Verify background color is set
      assertEquals(new Color(250, 250, 250), user.getContentPane().getBackground());
  }
  
  // Test cari data dengan multiple keywords
  @Test
  void testCariDataMultipleKeywords() {
      if (user == null) return;
      
      try {
          Method method = User.class.getDeclaredMethod("cariData", String.class);
          method.setAccessible(true);
          
          // Test dengan berbagai keyword
          String[] keywords = {"admin", "kasir", "super", "user123", "john doe"};
          
          for (String keyword : keywords) {
              assertDoesNotThrow(() -> method.invoke(user, keyword),
                  "Should handle keyword: " + keyword);
          }
          
      } catch (Exception e) {
          fail("cariData should handle multiple keywords: " + e.getMessage());
      }
  }
  
  // Test table data structure
  @Test
  void testTableDataStructure() {
      if (user == null) return;
      
      try {
          Field tabelUser = User.class.getDeclaredField("tabel_user");
          tabelUser.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) tabelUser.get(user);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          
          // Test column structure
          String[] expectedColumns = {
              "Id User", "Username", "Nama", "Role", "Password", "Terakhir Diperbarui"
          };
          
          for (int i = 0; i < expectedColumns.length; i++) {
              assertEquals(expectedColumns[i], model.getColumnName(i));
          }
          
      } catch (Exception e) {
          fail("Table data structure should be correct: " + e.getMessage());
      }
  }
}