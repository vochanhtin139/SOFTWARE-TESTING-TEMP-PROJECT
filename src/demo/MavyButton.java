package src.demo;

import javax.swing.*;
import java.awt.*;

/**
 * Custom Mavy Button
 */
public class MavyButton extends JButton {
    public Color fgColor = MavyProps.FORE_COLOR;

    public MavyButton(String text) {
        setText(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(MavyProps.createFont(20));
        setCustomEnabled(false);
    }

    public MavyButton(String text, Color fgColor) {
        this(text);
        this.fgColor = fgColor;
    }

    /**
     * Custom enabling and disabling button
     *
     * @param b
     */
    public void setCustomEnabled(boolean b) {
        setEnabled(b);

        if (b) {
            setBackground(MavyProps.DARK_COLOR);
            setForeground(this.fgColor);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setBackground(MavyProps.DISABLED_COLOR);
        }
    }
}
