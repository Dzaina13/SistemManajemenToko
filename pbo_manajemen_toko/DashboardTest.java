package pbo_manajemen_toko;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class DashboardTest {
  
  @Before
  public void setUp() {
      System.setProperty("java.awt.headless", "true");
  }
  
  // Test 1: Dashboard class exists
  @Test
  public void testDashboardClassExists() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          assertNotNull("Dashboard class should exist", dashboardClass);
      } catch (ClassNotFoundException e) {
          fail("Dashboard class not found");
      }
  }
  
  // Test 2: Dashboard has constructor
  @Test
  public void testDashboardConstructorExists() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          Constructor<?> constructor = dashboardClass.getConstructor();
          assertNotNull("Dashboard constructor should exist", constructor);
      } catch (Exception e) {
          fail("Dashboard constructor not found: " + e.getMessage());
      }
  }
  
  // Test 3: Main method exists
  @Test
  public void testMainMethodExists() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          Method mainMethod = dashboardClass.getMethod("main", String[].class);
          assertNotNull("Main method should exist", mainMethod);
          
          // Cek modifier method
          int modifiers = mainMethod.getModifiers();
          assertTrue("Main method should be public", java.lang.reflect.Modifier.isPublic(modifiers));
          assertTrue("Main method should be static", java.lang.reflect.Modifier.isStatic(modifiers));
      } catch (Exception e) {
          fail("Main method should exist and be public static: " + e.getMessage());
      }
  }
  
  // Test 4: LoadData method exists
  @Test
  public void testLoadDataMethodExists() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          Method loadDataMethod = dashboardClass.getDeclaredMethod("LoadData");
          assertNotNull("LoadData method should exist", loadDataMethod);
      } catch (Exception e) {
          fail("LoadData method should exist: " + e.getMessage());
      }
  }
  
  // Test 5: Chart methods exist
  @Test
  public void testChartMethodsExist() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          
          Method loadLineChart = dashboardClass.getDeclaredMethod("loadLineChart");
          Method loadPieChart = dashboardClass.getDeclaredMethod("loadPieChart");
          
          assertNotNull("loadLineChart method should exist", loadLineChart);
          assertNotNull("loadPieChart method should exist", loadPieChart);
      } catch (Exception e) {
          fail("Chart methods should exist: " + e.getMessage());
      }
  }
  
  // Test 6: Navigation event methods exist
  @Test
  public void testNavigationMethodsExist() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          
          Method barangBtnClick = dashboardClass.getDeclaredMethod("barangBtnMouseClicked", java.awt.event.MouseEvent.class);
          Method userBtnClick = dashboardClass.getDeclaredMethod("userBtnMouseClicked", java.awt.event.MouseEvent.class);
          Method historyBtnClick = dashboardClass.getDeclaredMethod("historyBtnMouseClicked", java.awt.event.MouseEvent.class);
          Method logoutBtnClick = dashboardClass.getDeclaredMethod("logoutBtnMouseClicked", java.awt.event.MouseEvent.class);
          
          assertNotNull("barangBtnMouseClicked should exist", barangBtnClick);
          assertNotNull("userBtnMouseClicked should exist", userBtnClick);
          assertNotNull("historyBtnMouseClicked should exist", historyBtnClick);
          assertNotNull("logoutBtnMouseClicked should exist", logoutBtnClick);
      } catch (Exception e) {
          fail("Navigation methods should exist: " + e.getMessage());
      }
  }
  
  // Test 7: Dashboard extends JFrame
  @Test
  public void testDashboardExtendsJFrame() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          Class<?> superClass = dashboardClass.getSuperclass();
          assertEquals("Dashboard should extend JFrame", "javax.swing.JFrame", superClass.getName());
      } catch (Exception e) {
          fail("Dashboard should extend JFrame: " + e.getMessage());
      }
  }
  
  // Test 8: Required fields exist
  @Test
  public void testRequiredFieldsExist() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          
          // Cek beberapa field penting
          dashboardClass.getDeclaredField("sideBar");
          dashboardClass.getDeclaredField("totalPendapatanPanel");
          dashboardClass.getDeclaredField("totalBarangPanel");
          dashboardClass.getDeclaredField("barangMasukPanel");
          dashboardClass.getDeclaredField("barangKeluarPanel");
          dashboardClass.getDeclaredField("grafikPendapatan");
          dashboardClass.getDeclaredField("grafikBarang");
          
          assertTrue("All required fields should exist", true);
      } catch (Exception e) {
          fail("Required fields should exist: " + e.getMessage());
      }
  }
  
  // Test 9: Button fields exist
  @Test
  public void testButtonFieldsExist() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          
          dashboardClass.getDeclaredField("dashbaordBtn");
          dashboardClass.getDeclaredField("barangBtn");
          dashboardClass.getDeclaredField("userBtn");
          dashboardClass.getDeclaredField("historyBtn");
          dashboardClass.getDeclaredField("logoutBtn");
          
          assertTrue("All button fields should exist", true);
      } catch (Exception e) {
          fail("Button fields should exist: " + e.getMessage());
      }
  }
  
  // Test 10: Value label fields exist
  @Test
  public void testValueLabelFieldsExist() {
      try {
          Class<?> dashboardClass = Class.forName("pbo_manajemen_toko.Dashboard");
          
          dashboardClass.getDeclaredField("totalPendapatanValue");
          dashboardClass.getDeclaredField("totalBarangValue");
          dashboardClass.getDeclaredField("barangMasukValue");
          dashboardClass.getDeclaredField("barangKeluarValue");
          
          assertTrue("All value label fields should exist", true);
      } catch (Exception e) {
          fail("Value label fields should exist: " + e.getMessage());
      }
  }
}
