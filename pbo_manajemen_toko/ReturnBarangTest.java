package pbo_manajemen_toko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class ReturnBarangTest {
  
  private ReturnBarang returnBarang;
  
  @BeforeEach
  void setUp() {
      // Setup minimal untuk testing
      try {
          // Set dummy session untuk menghindari error
          if (Login.Session != null) {
              // Assuming Login.Session has a method to set user ID
              // Login.Session.setUserId(1);
          }
          
          returnBarang = new ReturnBarang();
      } catch (Exception e) {
          // Jika ada error saat inisialisasi, skip test yang memerlukan GUI
          System.out.println("Warning: Could not initialize ReturnBarang GUI: " + e.getMessage());
      }
  }
  
  @AfterEach
  void tearDown() {
      if (returnBarang != null) {
          returnBarang.dispose();
      }
  }
  
  /**
   * Test constructor ReturnBarang
   */
  @Test
  void testReturnBarangConstructor() {
      if (returnBarang != null) {
          assertNotNull(returnBarang);
          assertEquals("Return Barang", returnBarang.getTitle());
          assertTrue(returnBarang.isDisplayable());
      } else {
          // Skip test jika GUI tidak bisa diinisialisasi
          System.out.println("Skipping constructor test - GUI not available");
      }
  }
  
  /**
   * Test method setFullScreenMode dengan parameter true
   */
  @Test
  void testSetFullScreenModeTrue() {
      if (returnBarang == null) {
          System.out.println("Skipping setFullScreenMode test - GUI not available");
          return;
      }
      
      try {
          Method method = ReturnBarang.class.getDeclaredMethod("setFullScreenMode", boolean.class);
          method.setAccessible(true);
          method.invoke(returnBarang, true);
          
          // Verify fullscreen mode is set
          assertEquals(java.awt.Frame.MAXIMIZED_BOTH, returnBarang.getExtendedState());
      } catch (Exception e) {
          fail("Method setFullScreenMode should be accessible: " + e.getMessage());
      }
  }
  
  /**
   * Test method setFullScreenMode dengan parameter false
   */
  @Test
  void testSetFullScreenModeFalse() {
      if (returnBarang == null) {
          System.out.println("Skipping setFullScreenMode test - GUI not available");
          return;
      }
      
      try {
          Method method = ReturnBarang.class.getDeclaredMethod("setFullScreenMode", boolean.class);
          method.setAccessible(true);
          method.invoke(returnBarang, false);
          
          // Verify normal mode is set
          assertEquals(java.awt.Frame.NORMAL, returnBarang.getExtendedState());
      } catch (Exception e) {
          fail("Method setFullScreenMode should be accessible: " + e.getMessage());
      }
  }
  
  /**
   * Test method resetFormReturn
   */
  @Test
  void testResetFormReturn() {
      if (returnBarang == null) {
          System.out.println("Skipping resetFormReturn test - GUI not available");
          return;
      }
      
      try {
          // Set some data first
          Field finputidtsc = ReturnBarang.class.getDeclaredField("finputidtsc");
          finputidtsc.setAccessible(true);
          javax.swing.JTextField idField = (javax.swing.JTextField) finputidtsc.get(returnBarang);
          idField.setText("TRX001");
          
          Field finputtgl = ReturnBarang.class.getDeclaredField("finputtgl");
          finputtgl.setAccessible(true);
          javax.swing.JTextField tglField = (javax.swing.JTextField) finputtgl.get(returnBarang);
          tglField.setText("2024-01-15");
          
          Field fjumlahretur = ReturnBarang.class.getDeclaredField("fjumlahretur");
          fjumlahretur.setAccessible(true);
          javax.swing.JTextField jumlahField = (javax.swing.JTextField) fjumlahretur.get(returnBarang);
          jumlahField.setText("5");
          
          // Add some data to table
          Field ftbldetailtsc = ReturnBarang.class.getDeclaredField("ftbldetailtsc");
          ftbldetailtsc.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) ftbldetailtsc.get(returnBarang);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          model.addRow(new Object[]{"ITEM001", 5, 50000.0});
          
          // Call resetFormReturn
          Method method = ReturnBarang.class.getDeclaredMethod("resetFormReturn");
          method.setAccessible(true);
          method.invoke(returnBarang);
          
          // Verify fields are cleared
          assertEquals("", idField.getText());
          assertEquals("", tglField.getText());
          assertEquals("", jumlahField.getText());
          assertEquals(0, model.getRowCount());
          
      } catch (Exception e) {
          fail("resetFormReturn method should work: " + e.getMessage());
      }
  }
  
  /**
   * Test validasi input kosong pada SaveReturn
   */
  @Test
  void testSaveReturnWithEmptyInput() {
      if (returnBarang == null) {
          System.out.println("Skipping SaveReturn test - GUI not available");
          return;
      }
      
      try {
          // Setup table tanpa selection (selectedRow = -1)
          Field ftbldetailtsc = ReturnBarang.class.getDeclaredField("ftbldetailtsc");
          ftbldetailtsc.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) ftbldetailtsc.get(returnBarang);
          table.clearSelection();
          
          // Call SaveReturn - should return early due to no selection
          Method method = ReturnBarang.class.getDeclaredMethod("SaveReturn");
          method.setAccessible(true);
          
          // This should not throw exception, just return early
          assertDoesNotThrow(() -> method.invoke(returnBarang));
          
      } catch (Exception e) {
          fail("SaveReturn should handle empty input gracefully: " + e.getMessage());
      }
  }
  
  /**
   * Test validasi jumlah return tidak valid
   */
  @Test
  void testSaveReturnWithInvalidAmount() {
      if (returnBarang == null) {
          System.out.println("Skipping SaveReturn validation test - GUI not available");
          return;
      }
      
      try {
          // Setup form dengan data invalid
          Field fjumlahretur = ReturnBarang.class.getDeclaredField("fjumlahretur");
          fjumlahretur.setAccessible(true);
          javax.swing.JTextField jumlahField = (javax.swing.JTextField) fjumlahretur.get(returnBarang);
          jumlahField.setText("abc"); // Invalid number
          
          Field finputidtsc = ReturnBarang.class.getDeclaredField("finputidtsc");
          finputidtsc.setAccessible(true);
          javax.swing.JTextField idField = (javax.swing.JTextField) finputidtsc.get(returnBarang);
          idField.setText("TRX001");
          
          Field finputtgl = ReturnBarang.class.getDeclaredField("finputtgl");
          finputtgl.setAccessible(true);
          javax.swing.JTextField tglField = (javax.swing.JTextField) finputtgl.get(returnBarang);
          tglField.setText("2024-01-15");
          
          // Setup table dengan data dan selection
          Field ftbldetailtsc = ReturnBarang.class.getDeclaredField("ftbldetailtsc");
          ftbldetailtsc.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) ftbldetailtsc.get(returnBarang);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          model.addRow(new Object[]{"ITEM001", 5, 50000.0});
          table.setRowSelectionInterval(0, 0);
          
          // Call SaveReturn
          Method method = ReturnBarang.class.getDeclaredMethod("SaveReturn");
          method.setAccessible(true);
          
          // Should not throw exception, should handle gracefully
          assertDoesNotThrow(() -> method.invoke(returnBarang));
          
      } catch (Exception e) {
          fail("SaveReturn should handle invalid input gracefully: " + e.getMessage());
      }
  }
  
  /**
   * Test method loadBarangTransaksi dengan ID kosong
   */
  @Test
  void testLoadBarangTransaksiEmptyId() {
      if (returnBarang == null) {
          System.out.println("Skipping loadBarangTransaksi test - GUI not available");
          return;
      }
      
      try {
          Method method = ReturnBarang.class.getDeclaredMethod("loadBarangTransaksi", String.class);
          method.setAccessible(true);
          
          // Test dengan ID kosong - should handle gracefully
          assertDoesNotThrow(() -> method.invoke(returnBarang, ""));
          
          // Test dengan ID null - should handle gracefully  
          assertDoesNotThrow(() -> method.invoke(returnBarang, (String) null));
          
      } catch (Exception e) {
          fail("loadBarangTransaksi should handle empty/null ID: " + e.getMessage());
      }
  }
  
  /**
   * Test akses ke komponen GUI
   */
  @Test
  void testGUIComponentsAccess() {
      if (returnBarang == null) {
          System.out.println("Skipping GUI components test - GUI not available");
          return;
      }
      
      try {
          // Test akses ke field-field penting
          Field finputidtsc = ReturnBarang.class.getDeclaredField("finputidtsc");
          finputidtsc.setAccessible(true);
          assertNotNull(finputidtsc.get(returnBarang));
          
          Field finputtgl = ReturnBarang.class.getDeclaredField("finputtgl");
          finputtgl.setAccessible(true);
          assertNotNull(finputtgl.get(returnBarang));
          
          Field fjumlahretur = ReturnBarang.class.getDeclaredField("fjumlahretur");
          fjumlahretur.setAccessible(true);
          assertNotNull(fjumlahretur.get(returnBarang));
          
          Field ftbldetailtsc = ReturnBarang.class.getDeclaredField("ftbldetailtsc");
          ftbldetailtsc.setAccessible(true);
          assertNotNull(ftbldetailtsc.get(returnBarang));
          
          Field ftblreturn = ReturnBarang.class.getDeclaredField("ftblreturn");
          ftblreturn.setAccessible(true);
          assertNotNull(ftblreturn.get(returnBarang));
          
      } catch (Exception e) {
          fail("Should be able to access GUI components: " + e.getMessage());
      }
  }
  
  /**
   * Test button actions - simulasi click
   */
  @Test
  void testButtonActions() {
      if (returnBarang == null) {
          System.out.println("Skipping button actions test - GUI not available");
          return;
      }
      
      try {
          // Test fbtnbatal action - should clear form
          Field fbtnbatal = ReturnBarang.class.getDeclaredField("fbtnbatal");
          fbtnbatal.setAccessible(true);
          javax.swing.JButton batalBtn = (javax.swing.JButton) fbtnbatal.get(returnBarang);
          
          // Set some data first
          Field finputidtsc = ReturnBarang.class.getDeclaredField("finputidtsc");
          finputidtsc.setAccessible(true);
          javax.swing.JTextField idField = (javax.swing.JTextField) finputidtsc.get(returnBarang);
          idField.setText("TRX001");
          
          // Simulate button click
          batalBtn.doClick();
          
          // Verify field is cleared
          assertEquals("", idField.getText());
          
      } catch (Exception e) {
          fail("Button actions should work: " + e.getMessage());
      }
  }
  
  /**
   * Test table model initialization
   */
  @Test
  void testTableModelInitialization() {
      if (returnBarang == null) {
          System.out.println("Skipping table model test - GUI not available");
          return;
      }
      
      try {
          // Test detail transaction table
          Field ftbldetailtsc = ReturnBarang.class.getDeclaredField("ftbldetailtsc");
          ftbldetailtsc.setAccessible(true);
          javax.swing.JTable table = (javax.swing.JTable) ftbldetailtsc.get(returnBarang);
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          
          // Verify column count and names
          assertEquals(3, model.getColumnCount());
          assertEquals("ID Item", model.getColumnName(0));
          assertEquals("Jumlah", model.getColumnName(1));
          assertEquals("Total", model.getColumnName(2));
          
          // Test return table
          Field ftblreturn = ReturnBarang.class.getDeclaredField("ftblreturn");
          ftblreturn.setAccessible(true);
          javax.swing.JTable returnTable = (javax.swing.JTable) ftblreturn.get(returnBarang);
          DefaultTableModel returnModel = (DefaultTableModel) returnTable.getModel();
          
          // Verify column count and names
          assertEquals(5, returnModel.getColumnCount());
          assertEquals("ID Return", returnModel.getColumnName(0));
          assertEquals("ID Transaksi", returnModel.getColumnName(1));
          assertEquals("ID Item", returnModel.getColumnName(2));
          assertEquals("Jumlah Return", returnModel.getColumnName(3));
          assertEquals("Tanggal Return", returnModel.getColumnName(4));
          
      } catch (Exception e) {
          fail("Table models should be properly initialized: " + e.getMessage());
      }
  }
}
