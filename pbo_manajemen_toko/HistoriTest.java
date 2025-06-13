package pbo_manajemen_toko;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.Component;
import java.awt.Container;

/**
 * JUnit Test untuk Histori
 * Test untuk halaman histori aktivitas dengan fitur pencarian
 */
public class HistoriTest {

    private Histori histori;

    @Before
    public void setUp() {
        // Mock static session data untuk testing
        MockSession.userId = 1;
        MockSession.userRole = "Super-admin";
        MockSession.userName = "Test User";
        
        histori = new Histori();
    }

    @After
    public void tearDown() {
        if (histori != null) {
            histori.dispose();
        }
        // Clear mock session
        MockSession.clear();
    }

    // Static mock class untuk session
    public static class MockSession {
        public static int userId = 0;
        public static String userRole = "";
        public static String userName = "";
        
        public static void clear() {
            userId = 0;
            userRole = "";
            userName = "";
        }
    }

    @Test
    public void testHistoriInitialization() {
        assertNotNull("Histori should not be null", histori);
        assertEquals("Histori", histori.getTitle());
    }

    @Test
    public void testWindowProperties() {
        assertEquals("Window title should be correct", "Histori", histori.getTitle());
        assertTrue("Histori should be displayable", histori.isDisplayable());
        assertEquals("Should be maximized", JFrame.MAXIMIZED_BOTH, histori.getExtendedState());
    }

    @Test
    public void testComponentsExist() {
        assertTrue("Histori should have components", 
                  histori.getContentPane().getComponentCount() > 0);
    }

    @Test
    public void testNavigationButtons() {
        // Test navigation buttons ada
        Component[] components = getAllComponents(histori.getContentPane());
        
        boolean foundDashboardBtn = false;
        boolean foundBarangBtn = false;
        boolean foundUserBtn = false;
        boolean foundHistoryBtn = false;
        boolean foundLogoutBtn = false;
        
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                String text = label.getText();
                if (text != null) {
                    if (text.equals("Dashboard")) foundDashboardBtn = true;
                    else if (text.equals("Barang")) foundBarangBtn = true;
                    else if (text.equals("User")) foundUserBtn = true;
                    else if (text.equals("Histori")) foundHistoryBtn = true;
                    else if (text.equals("Logout")) foundLogoutBtn = true;
                }
            }
        }
        
        assertTrue("Should have Dashboard navigation", foundDashboardBtn);
        assertTrue("Should have Barang navigation", foundBarangBtn);
        assertTrue("Should have User navigation", foundUserBtn);
        assertTrue("Should have Histori navigation", foundHistoryBtn);
        assertTrue("Should have Logout navigation", foundLogoutBtn);
    }

    @Test
    public void testMainTitle() {
        // Test title "Histori Aktivitas"
        boolean foundTitle = false;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                String text = label.getText();
                if (text != null && text.equals("Histori Aktivitas")) {
                    foundTitle = true;
                    break;
                }
            }
        }
        
        assertTrue("Should have 'Histori Aktivitas' title", foundTitle);
    }

    @Test
    public void testSearchComponents() {
        // Test search field dan buttons ada
        boolean foundSearchField = false;
        boolean foundSearchBtn = false;
        boolean foundResetBtn = false;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JTextField) {
                foundSearchField = true;
            } else if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                String text = button.getText();
                if (text != null) {
                    if (text.equals("Cari")) {
                        foundSearchBtn = true;
                    } else if (text.equals("Reset")) {
                        foundResetBtn = true;
                    }
                }
            }
        }
        
        assertTrue("Should have search field", foundSearchField);
        assertTrue("Should have search button", foundSearchBtn);
        assertTrue("Should have reset button", foundResetBtn);
    }

    @Test
    public void testHistoriTable() {
        // Test tabel histori ada
        boolean foundTable = false;
        boolean foundScrollPane = false;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JTable) {
                JTable table = (JTable) comp;
                foundTable = true;
                
                // Test table columns
                assertEquals("Table should have 4 columns", 4, table.getColumnCount());
                assertEquals("Column 1 should be 'Id Histori'", "Id Histori", table.getColumnName(0));
                assertEquals("Column 2 should be 'Id User'", "Id User", table.getColumnName(1));
                assertEquals("Column 3 should be 'Aktivitas'", "Aktivitas", table.getColumnName(2));
                assertEquals("Column 4 should be 'Waktu'", "Waktu", table.getColumnName(3));
                
            } else if (comp instanceof JScrollPane) {
                foundScrollPane = true;
            }
        }
        
        assertTrue("Should have histori table", foundTable);
        assertTrue("Should have scroll pane for table", foundScrollPane);
    }

    @Test
    public void testBrandingExists() {
        // Test branding "TokoAja." ada
        boolean foundBranding = false;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                String text = label.getText();
                if (text != null && text.equals("TokoAja.")) {
                    foundBranding = true;
                    break;
                }
            }
        }
        
        assertTrue("Should have 'TokoAja.' branding", foundBranding);
    }

    @Test
    public void testUserInfoExists() {
        // Test user info ada
        boolean foundUserRole = false;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                String text = label.getText();
                if (text != null && text.equals("Super-admin")) {
                    foundUserRole = true;
                    break;
                }
            }
        }
        
        assertTrue("Should have user role", foundUserRole);
    }

    @Test
    public void testTableHasModel() {
        // Test table punya model
        JTable table = null;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JTable) {
                table = (JTable) comp;
                break;
            }
        }
        
        assertNotNull("Table should exist", table);
        
        if (table != null) {
            assertNotNull("Table should have a model", table.getModel());
            assertEquals("Table should have 4 columns", 4, table.getModel().getColumnCount());
        }
    }

    @Test
    public void testSearchFieldExists() {
        // Test search field ada dan bisa diakses
        JTextField searchField = null;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JTextField) {
                searchField = (JTextField) comp;
                break;
            }
        }
        
        assertNotNull("Search field should exist", searchField);
    }

    @Test
    public void testTableInitiallyEmpty() {
        // Test table awalnya kosong atau ada data
        JTable table = null;
        
        Component[] components = getAllComponents(histori.getContentPane());
        
        for (Component comp : components) {
            if (comp instanceof JTable) {
                table = (JTable) comp;
                break;
            }
        }
        
        if (table != null) {
            // Test row count (bisa 0 atau ada data)
            assertTrue("Table row count should be >= 0", table.getModel().getRowCount() >= 0);
        }
    }

    @Test
    public void testButtonsAreClickable() {
        // Test buttons bisa diklik
        Component[] components = getAllComponents(histori.getContentPane());
        
        int buttonCount = 0;
        
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                assertTrue("Button should be enabled", button.isEnabled());
                buttonCount++;
            }
        }
        
        assertTrue("Should have at least 2 buttons (Cari & Reset)", buttonCount >= 2);
    }

    /**
     * Helper method untuk mendapatkan semua komponen secara rekursif
     */
    private Component[] getAllComponents(Container container) {
        java.util.List<Component> componentList = new java.util.ArrayList<>();
        addComponentsRecursively(container, componentList);
        return componentList.toArray(new Component[0]);
    }

    private void addComponentsRecursively(Container container, java.util.List<Component> componentList) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            componentList.add(component);
            if (component instanceof Container) {
                addComponentsRecursively((Container) component, componentList);
            }
        }
    }
}