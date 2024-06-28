import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import demo.*;

import java.awt.*;

public class MavyButtonTest {

    @Test
    public void testDefaultConstructor_text() {
        MavyButton button = new MavyButton("Test Button");
        Assertions.assertEquals("Test Button", button.getText());
    }

    @Test
    public void testDefaultConstructor_focusPainted() {
        MavyButton button = new MavyButton("Test Button");
        Assertions.assertFalse(button.isFocusPainted());
    }

    @Test
    public void testDefaultConstructor_borderPainted() {
        MavyButton button = new MavyButton("Test Button");
        Assertions.assertFalse(button.isBorderPainted());
    }

    @Test
    public void testDefaultConstructor_font() {
        MavyButton button = new MavyButton("Test Button");
        Assertions.assertEquals(MavyProps.createFont(20), button.getFont());
    }

    @Test
    public void testDefaultConstructor_enabled() {
        MavyButton button = new MavyButton("Test Button");
        Assertions.assertFalse(button.isEnabled());
    }

//    @Test
//    public void testDefaultConstructor_foreground() {
//        MavyButton button = new MavyButton("Test Button");
//        Assertions.assertEquals(MavyProps.FORE_COLOR, button.getForeground());
//    }

    @Test
    public void testConstructorWithColor_text() {
        Color customColor = Color.RED;
        MavyButton button = new MavyButton("Test Button", customColor);
        Assertions.assertEquals("Test Button", button.getText());
    }

    @Test
    public void testConstructorWithColor_focusPainted() {
        Color customColor = Color.RED;
        MavyButton button = new MavyButton("Test Button", customColor);
        Assertions.assertFalse(button.isFocusPainted());
    }

    @Test
    public void testConstructorWithColor_borderPainted() {
        Color customColor = Color.RED;
        MavyButton button = new MavyButton("Test Button", customColor);
        Assertions.assertFalse(button.isBorderPainted());
    }

    @Test
    public void testConstructorWithColor_font() {
        Color customColor = Color.RED;
        MavyButton button = new MavyButton("Test Button", customColor);
        Assertions.assertEquals(MavyProps.createFont(20), button.getFont());
    }

    @Test
    public void testConstructorWithColor_enabled() {
        Color customColor = Color.RED;
        MavyButton button = new MavyButton("Test Button", customColor);
        Assertions.assertFalse(button.isEnabled());
    }

//    @Test
//    public void testConstructorWithColor_foreground() {
//        Color customColor = Color.RED;
//        MavyButton button = new MavyButton("Test Button", customColor);
//        Assertions.assertEquals(customColor, button.getForeground());
//    }

    @Test
    public void testSetCustomEnabled_Enabled_isEnabled() {
        MavyButton button = new MavyButton("Test Button");
        button.setCustomEnabled(true);
        Assertions.assertTrue(button.isEnabled());
    }

    @Test
    public void testSetCustomEnabled_Enabled_background() {
        MavyButton button = new MavyButton("Test Button");
        button.setCustomEnabled(true);
        Assertions.assertEquals(MavyProps.DARK_COLOR, button.getBackground());
    }

    @Test
    public void testSetCustomEnabled_Enabled_foreground() {
        MavyButton button = new MavyButton("Test Button");
        button.setCustomEnabled(true);
        Assertions.assertEquals(button.fgColor, button.getForeground());
    }

    @Test
    public void testSetCustomEnabled_Enabled_cursor() {
        MavyButton button = new MavyButton("Test Button");
        button.setCustomEnabled(true);
        Assertions.assertEquals(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), button.getCursor());
    }

    @Test
    public void testSetCustomEnabled_Disabled_isEnabled() {
        MavyButton button = new MavyButton("Test Button");
        button.setCustomEnabled(true); // Set to enabled first
        button.setCustomEnabled(false);
        Assertions.assertFalse(button.isEnabled());
    }

    @Test
    public void testSetCustomEnabled_Disabled_background() {
        MavyButton button = new MavyButton("Test Button");
        button.setCustomEnabled(true); // Set to enabled first
        button.setCustomEnabled(false);
        Assertions.assertEquals(MavyProps.DISABLED_COLOR, button.getBackground());
    }
}
