package demo;

import java.awt.*;

public class MavyProps {
    /**
     * Colors
     */
    public static final Color DARK_COLOR = new Color(0xFF1C212E);
    public static final Color SURFACE_COLOR = new Color(0xFF272B39);
    public static final Color FORE_COLOR = new Color(0xFF7A88A6);
    public static final Color DISABLED_COLOR = new Color(0xFF2C313E);
    public static final Color RED_COLOR = new Color(0xFFF08070);
    public static final Color YELLOW_COLOR = new Color(0xFFFBC02D);
    public static final Color GREEN_COLOR = new Color(0xFF5EC479);
    public static final Color BLUE_COLOR = new Color(0xFF3B8EEA);

    /**
     * Set whether to see the steps on how the computer executes my algorithm
     */
    public static final boolean SHOW_STEPS = false;

    /**
     * Create custom font with size
     *
     * @param size
     * @return Font
     */
    public static Font createFont(int size) {
        return new Font("Arial", Font.BOLD, size);
    }

    /**
     * Force capitalized strings
     *
     * @param text
     * @return
     */
    public static String forceCapitalize(String text) {
        String output = "";

        final String[] WORDS = text.split(" ");

        for (int i = 0; i < WORDS.length; i++) {
            output += String.valueOf(WORDS[i].charAt(0)).toUpperCase() + WORDS[i].substring(1, WORDS[i].length()).toLowerCase();

            if (i != WORDS.length - 1) {
                output += " ";
            }
        }

        return output;
    }

    /**
     * Reverse a string
     *
     * @param text
     * @return String
     */
    public static String reverseString(String text) {
        return new StringBuilder(text).reverse().toString();
    }
}