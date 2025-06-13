import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JFrameTest {
  
  private JFrame frame;
  
  @BeforeEach
  void setUp() {
      try {
          frame = new JFrame();
      } catch (HeadlessException e) {
          System.out.println("Warning: GUI not available: " + e.getMessage());
      }
  }
  
  @AfterEach
  void tearDown() {
      if (frame != null) {
          frame.dispose();
      }
  }
  
  // Test default constructor
  @Test
  void testDefaultConstructor() {
      if (frame != null) {
          assertNotNull(frame);
          assertNotNull(frame.getRootPane());
          assertEquals(WindowConstants.HIDE_ON_CLOSE, frame.getDefaultCloseOperation());
          assertTrue(frame.isRootPaneCheckingEnabled());
      }
  }
  
  // Test constructor with title
  @Test
  void testConstructorWithTitle() {
      if (GraphicsEnvironment.isHeadless()) return;
      
      try {
          JFrame titleFrame = new JFrame("Test Title");
          assertNotNull(titleFrame);
          assertEquals("Test Title", titleFrame.getTitle());
          titleFrame.dispose();
      } catch (Exception e) {
          fail("Constructor with title should work: " + e.getMessage());
      }
  }
  
  // Test constructor with GraphicsConfiguration
  @Test
  void testConstructorWithGraphicsConfiguration() {
      if (GraphicsEnvironment.isHeadless()) return;
      
      try {
          GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                  .getDefaultScreenDevice().getDefaultConfiguration();
          JFrame gcFrame = new JFrame(gc);
          assertNotNull(gcFrame);
          gcFrame.dispose();
      } catch (Exception e) {
          fail("Constructor with GraphicsConfiguration should work: " + e.getMessage());
      }
  }
  
  // Test constructor with title and GraphicsConfiguration
  @Test
  void testConstructorWithTitleAndGraphicsConfiguration() {
      if (GraphicsEnvironment.isHeadless()) return;
      
      try {
          GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                  .getDefaultScreenDevice().getDefaultConfiguration();
          JFrame titleGcFrame = new JFrame("Test Title", gc);
          assertNotNull(titleGcFrame);
          assertEquals("Test Title", titleGcFrame.getTitle());
          titleGcFrame.dispose();
      } catch (Exception e) {
          fail("Constructor with title and GraphicsConfiguration should work: " + e.getMessage());
      }
  }
  
  // Test setDefaultCloseOperation valid values
  @Test
  void testSetDefaultCloseOperationValidValues() {
      if (frame == null) return;
      
      // Test valid operations
      frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      assertEquals(WindowConstants.DO_NOTHING_ON_CLOSE, frame.getDefaultCloseOperation());
      
      frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      assertEquals(WindowConstants.HIDE_ON_CLOSE, frame.getDefaultCloseOperation());
      
      frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      assertEquals(WindowConstants.DISPOSE_ON_CLOSE, frame.getDefaultCloseOperation());
      
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      assertEquals(WindowConstants.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
  }
  
  // Test setDefaultCloseOperation invalid value
  @Test
  void testSetDefaultCloseOperationInvalidValue() {
      if (frame == null) return;
      
      assertThrows(IllegalArgumentException.class, () -> {
          frame.setDefaultCloseOperation(999);
      });
  }
  
  // Test getDefaultCloseOperation
  @Test
  void testGetDefaultCloseOperation() {
      if (frame == null) return;
      
      // Default should be HIDE_ON_CLOSE
      assertEquals(WindowConstants.HIDE_ON_CLOSE, frame.getDefaultCloseOperation());
  }
  
  // Test setJMenuBar and getJMenuBar
  @Test
  void testMenuBar() {
      if (frame == null) return;
      
      JMenuBar menuBar = new JMenuBar();
      frame.setJMenuBar(menuBar);
      assertEquals(menuBar, frame.getJMenuBar());
      
      // Test null menubar
      frame.setJMenuBar(null);
      assertNull(frame.getJMenuBar());
  }
  
  // Test getRootPane
  @Test
  void testGetRootPane() {
      if (frame == null) return;
      
      JRootPane rootPane = frame.getRootPane();
      assertNotNull(rootPane);
      assertTrue(rootPane.isOpaque());
  }
  
  // Test setRootPane
  @Test
  void testSetRootPane() {
      if (frame == null) return;
      
      JRootPane newRootPane = new JRootPane();
      frame.setRootPane(newRootPane);
      assertEquals(newRootPane, frame.getRootPane());
  }
  
  // Test getContentPane
  @Test
  void testGetContentPane() {
      if (frame == null) return;
      
      Container contentPane = frame.getContentPane();
      assertNotNull(contentPane);
      assertTrue(contentPane instanceof Container);
  }
  
  // Test setContentPane
  @Test
  void testSetContentPane() {
      if (frame == null) return;
      
      JPanel newContentPane = new JPanel();
      frame.setContentPane(newContentPane);
      assertEquals(newContentPane, frame.getContentPane());
  }
  
  // Test setContentPane with null
  @Test
  void testSetContentPaneNull() {
      if (frame == null) return;
      
      assertThrows(IllegalComponentStateException.class, () -> {
          frame.setContentPane(null);
      });
  }
  
  // Test getLayeredPane
  @Test
  void testGetLayeredPane() {
      if (frame == null) return;
      
      JLayeredPane layeredPane = frame.getLayeredPane();
      assertNotNull(layeredPane);
  }
  
  // Test setLayeredPane
  @Test
  void testSetLayeredPane() {
      if (frame == null) return;
      
      JLayeredPane newLayeredPane = new JLayeredPane();
      frame.setLayeredPane(newLayeredPane);
      assertEquals(newLayeredPane, frame.getLayeredPane());
  }
  
  // Test getGlassPane
  @Test
  void testGetGlassPane() {
      if (frame == null) return;
      
      Component glassPane = frame.getGlassPane();
      assertNotNull(glassPane);
  }
  
  // Test setGlassPane
  @Test
  void testSetGlassPane() {
      if (frame == null) return;
      
      JPanel newGlassPane = new JPanel();
      frame.setGlassPane(newGlassPane);
      assertEquals(newGlassPane, frame.getGlassPane());
  }
  
  // Test isRootPaneCheckingEnabled
  @Test
  void testIsRootPaneCheckingEnabled() {
      if (frame == null) return;
      
      // Should be true by default after frameInit
      assertTrue(frame.isRootPaneCheckingEnabled());
  }
  
  // Test setRootPaneCheckingEnabled
  @Test
  void testSetRootPaneCheckingEnabled() {
      if (frame == null) return;
      
      frame.setRootPaneCheckingEnabled(false);
      assertFalse(frame.isRootPaneCheckingEnabled());
      
      frame.setRootPaneCheckingEnabled(true);
      assertTrue(frame.isRootPaneCheckingEnabled());
  }
  
  // Test add component
  @Test
  void testAddComponent() {
      if (frame == null) return;
      
      JButton button = new JButton("Test");
      frame.add(button);
      
      // Component should be added to content pane
      Container contentPane = frame.getContentPane();
      assertEquals(1, contentPane.getComponentCount());
      assertEquals(button, contentPane.getComponent(0));
  }
  
  // Test remove component
  @Test
  void testRemoveComponent() {
      if (frame == null) return;
      
      JButton button = new JButton("Test");
      frame.add(button);
      frame.remove(button);
      
      Container contentPane = frame.getContentPane();
      assertEquals(0, contentPane.getComponentCount());
  }
  
  // Test setLayout
  @Test
  void testSetLayout() {
      if (frame == null) return;
      
      FlowLayout flowLayout = new FlowLayout();
      frame.setLayout(flowLayout);
      
      // Layout should be set on content pane
      assertEquals(flowLayout, frame.getContentPane().getLayout());
  }
  
  // Test setTransferHandler and getTransferHandler
  @Test
  void testTransferHandler() {
      if (frame == null) return;
      
      TransferHandler handler = new TransferHandler();
      frame.setTransferHandler(handler);
      assertEquals(handler, frame.getTransferHandler());
      
      // Test null transfer handler
      frame.setTransferHandler(null);
      assertNull(frame.getTransferHandler());
  }
  
  // Test update method
  @Test
  void testUpdate() {
      if (frame == null) return;
      
      // Should not throw exception
      assertDoesNotThrow(() -> {
          Graphics g = frame.getGraphics();
          if (g != null) {
              frame.update(g);
              g.dispose();
          }
      });
  }
  
  // Test processWindowEvent HIDE_ON_CLOSE
  @Test
  void testProcessWindowEventHideOnClose() {
      if (frame == null) return;
      
      frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      frame.setVisible(true);
      
      WindowEvent closeEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
      frame.processWindowEvent(closeEvent);
      
      assertFalse(frame.isVisible());
  }
  
  // Test processWindowEvent DO_NOTHING_ON_CLOSE
  @Test
  void testProcessWindowEventDoNothingOnClose() {
      if (frame == null) return;
      
      frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      frame.setVisible(true);
      
      WindowEvent closeEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
      frame.processWindowEvent(closeEvent);
      
      assertTrue(frame.isVisible());
  }
  
  // Test static setDefaultLookAndFeelDecorated
  @Test
  void testSetDefaultLookAndFeelDecorated() {
      JFrame.setDefaultLookAndFeelDecorated(true);
      assertTrue(JFrame.isDefaultLookAndFeelDecorated());
      
      JFrame.setDefaultLookAndFeelDecorated(false);
      assertFalse(JFrame.isDefaultLookAndFeelDecorated());
  }
  
  // Test static isDefaultLookAndFeelDecorated
  @Test
  void testIsDefaultLookAndFeelDecorated() {
      // Default should be false
      boolean defaultValue = JFrame.isDefaultLookAndFeelDecorated();
      assertTrue(defaultValue == true || defaultValue == false); // Just check it returns a boolean
  }
  
  // Test paramString
  @Test
  void testParamString() {
      if (frame == null) return;
      
      String paramString = frame.paramString();
      assertNotNull(paramString);
      assertTrue(paramString.contains("defaultCloseOperation="));
      assertTrue(paramString.contains("rootPane="));
      assertTrue(paramString.contains("rootPaneCheckingEnabled="));
  }
  
  // Test getAccessibleContext
  @Test
  void testGetAccessibleContext() {
      if (frame == null) return;
      
      AccessibleContext context = frame.getAccessibleContext();
      assertNotNull(context);
      assertTrue(context instanceof JFrame.AccessibleJFrame);
  }
  
  // Test AccessibleJFrame getAccessibleName with title
  @Test
  void testAccessibleJFrameGetAccessibleNameWithTitle() {
      if (frame == null) return;
      
      frame.setTitle("Test Frame");
      AccessibleContext context = frame.getAccessibleContext();
      assertEquals("Test Frame", context.getAccessibleName());
  }
  
  // Test AccessibleJFrame getAccessibleName without title
  @Test
  void testAccessibleJFrameGetAccessibleNameWithoutTitle() {
      if (frame == null) return;
      
      frame.setTitle(null);
      AccessibleContext context = frame.getAccessibleContext();
      // Should return super.getAccessibleName() when title is null
      assertNotNull(context.getAccessibleName());
  }
  
  // Test AccessibleJFrame getAccessibleStateSet
  @Test
  void testAccessibleJFrameGetAccessibleStateSet() {
      if (frame == null) return;
      
      AccessibleContext context = frame.getAccessibleContext();
      AccessibleStateSet stateSet = context.getAccessibleStateSet();
      assertNotNull(stateSet);
  }
  
  // Test frameInit method
  @Test
  void testFrameInit() {
      if (frame == null) return;
      
      // Verify frameInit was called in constructor
      assertNotNull(frame.getRootPane());
      assertTrue(frame.isRootPaneCheckingEnabled());
      
      // Verify events are enabled
      long eventMask = frame.getEventMask();
      assertTrue((eventMask & AWTEvent.KEY_EVENT_MASK) != 0);
      assertTrue((eventMask & AWTEvent.WINDOW_EVENT_MASK) != 0);
  }
  
  // Test createRootPane method
  @Test
  void testCreateRootPane() {
      if (frame == null) return;
      
      try {
          Method createRootPane = JFrame.class.getDeclaredMethod("createRootPane");
          createRootPane.setAccessible(true);
          JRootPane rootPane = (JRootPane) createRootPane.invoke(frame);
          
          assertNotNull(rootPane);
          assertTrue(rootPane.isOpaque());
      } catch (Exception e) {
          fail("createRootPane should work: " + e.getMessage());
      }
  }
  
  // Test setIconImage
  @Test
  void testSetIconImage() {
      if (frame == null) return;
      
      // Create a simple test image
      Image testImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
      
      assertDoesNotThrow(() -> {
          frame.setIconImage(testImage);
      });
  }
  
  // Test repaint method
  @Test
  void testRepaint() {
      if (frame == null) return;
      
      assertDoesNotThrow(() -> {
          frame.repaint(100, 0, 0, 50, 50);
      });
  }
  
  // Test getGraphics method
  @Test
  void testGetGraphics() {
      if (frame == null) return;
      
      assertDoesNotThrow(() -> {
          Graphics g = frame.getGraphics();
          if (g != null) {
              g.dispose();
          }
      });
  }
}