import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import demo.*;

import java.awt.*;

public class MavyPropsTest {

    @Test
    public void testShowStepsConstant() {
        // Assert that SHOW_STEPS is indeed false
        Assertions.assertTrue(!MavyProps.SHOW_STEPS); // Using assertTrue with negation for expected value of false
    }

    @Test
    public void testDarkColor() {
        Assertions.assertEquals(new Color(0xFF1C212E), MavyProps.DARK_COLOR);
    }

    @Test
    public void testSurfaceColor() {
        Assertions.assertEquals(new Color(0xFF272B39), MavyProps.SURFACE_COLOR);
    }

    @Test
    public void testForeColor() {
        Assertions.assertEquals(new Color(0xFF7A88A6), MavyProps.FORE_COLOR);
    }

    @Test
    public void testDisabledColor() {
        Assertions.assertEquals(new Color(0xFF2C313E), MavyProps.DISABLED_COLOR);
    }

    @Test
    public void testRedColor() {
        Assertions.assertEquals(new Color(0xFFF08070), MavyProps.RED_COLOR);
    }

    @Test
    public void testYellowColor() {
        Assertions.assertEquals(new Color(0xFFFBC02D), MavyProps.YELLOW_COLOR);
    }

    @Test
    public void testGreenColor() {
        Assertions.assertEquals(new Color(0xFF5EC479), MavyProps.GREEN_COLOR);
    }

    @Test
    public void testBlueColor() {
        Assertions.assertEquals(new Color(0xFF3B8EEA), MavyProps.BLUE_COLOR);
    }

    @Test
    public void testCreateFont() {
        Font expectedFont = new Font("Arial", Font.BOLD, 20);
        Font actualFont = MavyProps.createFont(20);

        Assertions.assertEquals(expectedFont.getFontName(), actualFont.getFontName());
        Assertions.assertEquals(expectedFont.getStyle(), actualFont.getStyle());
        Assertions.assertEquals(expectedFont.getSize(), actualFont.getSize());
    }

    @Test
    public void testForceCapitalize_case1() {
        Assertions.assertEquals("Test String", MavyProps.forceCapitalize("test string"));
    }

    @Test
    public void testForceCapitalize_case2() {
        Assertions.assertEquals("This Is A Test", MavyProps.forceCapitalize("this is a test"));
    }

    @Test
    public void testForceCapitalize_case3() {
        Assertions.assertEquals("Empty", MavyProps.forceCapitalize("empty"));
    }

    @Test
    public void testReverseString_case1() {
        Assertions.assertEquals("retset", MavyProps.reverseString("tester"));
    }

    @Test
    public void testReverseString_case2() {
        Assertions.assertEquals("", MavyProps.reverseString(""));
    }
}
