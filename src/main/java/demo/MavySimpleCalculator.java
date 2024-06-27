package demo;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * ----------------------------------
 * Author: Maverick G. Fabroa
 * ----------------------------------
 * Date: May 10, 2021
 * ----------------------------------
 */

/**
 * Global properties
 */


public class MavySimpleCalculator {
    private static final Dimension WIN_SIZE = new Dimension(450, 650);
    private static final Border PADDING = BorderFactory.createEmptyBorder(16, 16, 16, 16);
    private static final int POINT_LENGTH = 10;

    /**
     * REACTIVE PROPERTIES
     */

    private static int mode = 0;
    private static boolean isOn = false;

    /**
     * Convert decimal number to binary
     *
     * > Using my own original algorithm
     *
     * @param dec
     * @return String
     */
    public static String convertDecimalToBinary(String dec) {
        // Check if the decimal is not empty
        if (dec.length() > 0) {
            String leftNum = "";
            String rightNum = "";

            // ==== COMPUTATION ==== //

            try {
                // Split the text by dots
                final String[] splits = dec.split("\\.");

                // Convert the string to long
                long num = Long.parseLong(splits.length > 1 ? splits[0] : dec);

                // While num is greater than 0
                while (num > 0) {
                    // Get the remainder and append it to the output
                    leftNum += num % 2;
                    // Divide the num by 2 without floating point
                    num = Math.floorDiv(num, 2);
                }

                // Splits is equal to 2 if there is a dot or point
                if (splits.length == 2) {
                    /**
                     * Convert decimal point to binary
                     */

                    // Has floating point
                    // Convert the decimal point to double
                    // For intance, the value of splits[1] is 625
                    //
                    // Then pt would be 0.625
                    double pt = Double.parseDouble("0." + splits[1]);

                    // Length tracker
                    int ptLength = 0;

                    /**
                     * Converting floating point number to binary
                     *
                     * 0.625 x 2 = 1.25  – 1
                     * 0.25 x 2 = 0.5    – 0
                     * 0.5 x 2 = 1.0     – 1
                     *
                     * Reset the whole number to 0 if it's 1
                     */

                    // While pt is not 0 and pt is less than 0
                    while (pt != 0 && pt < 1) {
                        // Multiply the point by 2
                        pt *= 2;

                        //  If the pt is greater than or equal to 1, add 1 else 0
                        rightNum += (pt >= 1) ? "1" : "0";

                        // If pt is greater than 1,
                        // then subtract it by it's whole number
                        if (pt > 1) {
                            pt -= Math.floor(pt);
                        }

                        // Increase the length counter
                        ptLength++;

                        // If pt is equal to 1.0, then break the loop
                        if (pt == 1.0 || ptLength >= POINT_LENGTH) {
                            break;
                        }
                    }
                }
                else if (splits.length > 2) {
                    // Multiple points
                    return "-3"; // Please input only one dot
                }
            }
            catch (NumberFormatException e) {
                return "-2"; // Input numbers only
            }

            // ===================== //

            // Check if it's floating point
            if (leftNum.length() > 0 && rightNum.length() > 0) {
                return MavyProps.reverseString(leftNum) + "." + rightNum;
            }

            return MavyProps.reverseString(leftNum);
        }

        return "-1"; // Please fill the decimal field
    }

    /**
     * Convert binary to decimal
     *
     * > Using my own original algorithm
     *
     * @param bin
     * @return
     */
    public static String convertBinaryToDecimal(String bin) {
        // Check if the text is not empty
        if (bin.length() > 0) {
            // Output bin
            int leftBin = 0;
            double rightBin = 0;

            // Check if the binary contains only 0 and 1
            for (char b : bin.toCharArray()) {
                if (b != '0' && b != '1' && b != '.') {
                    return "-4"; // Please input only 0 and 1"
                }
            }

            // Split the text by dots
            final String[] SPLITS = bin.split("\\.");
            final boolean hasFloating = SPLITS.length == 2;

            if (SPLITS.length <= 2) {
                try {
                    // Get the length of the whole number
                    final int LENGTH = hasFloating ? SPLITS[0].length() : bin.length();
                    final String chToCompute = hasFloating ? SPLITS[0] : bin;

                    // For every character
                    for (int i = 1; i <= LENGTH; i++) {
                        // If the character is 1, then add it's corresponding perfect square
                        if (chToCompute.charAt(i - 1) == '1') {
                            /**
                             * Using the binary conversion table
                             * If last element, add only 1
                             *
                             * For instance, 101
                             *
                             * 1st element = 1 = (2 ^ length - 1) = (2 ^ 3 - 1) = (2 ^ 2)
                             * 2nd element = 0, so skip
                             * 3rd element = 1 = (last element) = 1
                             *
                             * Subtotal:
                             *
                             * 1st = 4
                             * 2nd = 0
                             * 3rd = 1
                             *
                             * Total: 5
                             */
                            leftBin += (i == LENGTH) ? 1 : (int)Math.pow(2, LENGTH - i);
                        }
                    }

                    // If the binary has a decimal point
                    if (hasFloating) {
                        final String pt = SPLITS[1];

                        for (int i = 1; i <= pt.length(); i++) {
                            final String ch = String.valueOf(pt.charAt(i - 1));
                            final double ans = Integer.parseInt(ch) * Math.pow(2, -i);

                            rightBin += ans;
                        }

                        String finalPoint = String.valueOf(rightBin).replace("0.", "");

                        return String.valueOf(leftBin) + "." + finalPoint;
                    }

                    return String.valueOf(leftBin);
                }
                catch (Exception e) {
                    return "-2"; // An error has occured
                }
            }
            else {
                // Multiple points
                return "-3"; // Please input only one dot
            }
        }

        return "-1"; // Please fill the binary field
    }

    /**
     * Invoking my own algorithm for evaluating expressions with MDAS
     *
     * @param label
     * @param inputPrevious
     * @param input
     */
    private static void calculate(JLabel label, JLabel inputPrevious, String input) {
        final String INPUT = input.replaceAll(" ", "");

        MavyInputCalculator calculator = new MavyInputCalculator(INPUT);
        final String ANSWER = calculator.calculate();

        if (ANSWER.startsWith("E: ")) {
            final String ERROR = MavyProps.forceCapitalize(ANSWER.replace("E: ", "").replaceAll("_", " "));

            if (ERROR.contains("For Input String")) {
                inputPrevious.setText("Malformed Expression");
            } else {
                inputPrevious.setText(ERROR);
            }

            inputPrevious.setForeground(MavyProps.RED_COLOR);
        }
        else {
            label.setText(ANSWER);
            inputPrevious.setForeground(MavyProps.FORE_COLOR);
        }
    }

    /**
     * Switch between modes
     *
     *  Normal Mode        = 0
     *  Decimal to Binary  = 1
     *  Binary to Decimal  = 2
     * ```
     */
    public static int switchMode(JLabel normal, JLabel db, JLabel bd) {
        normal.setForeground(MavyProps.FORE_COLOR);
        db.setForeground(MavyProps.FORE_COLOR);
        bd.setForeground(MavyProps.FORE_COLOR);

        // Increment mode
        mode++;

        switch (mode % 3) {
            // Normal mode
            case 0:
                normal.setForeground(MavyProps.YELLOW_COLOR);
                break;
            // Decimal to Binary mode
            case 1:
                db.setForeground(MavyProps.YELLOW_COLOR);
                break;
            // Binary to Decimal mode
            case 2:
                bd.setForeground(MavyProps.YELLOW_COLOR);
                break;
        }

        return mode % 3;
    }

    public static void main(String[] args) {
        // Main frame
        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(WIN_SIZE);
        frame.setMinimumSize(WIN_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * Set icon
         */
        try {
            URL image = new File(System.getProperty("user.dir") + "/mavy-icon.png").toURI().toURL();

            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                frame.setIconImage(icon.getImage());
            }
        }
        catch (MalformedURLException e) {
            // Do nothing
        }

        /** ================== LAYOUTS ===================== */

        BorderLayout panelLayout = new BorderLayout();
        panelLayout.setVgap(16);

        GridLayout inputPanelLayout = new GridLayout(3, 1);
        inputPanelLayout.setVgap(8);

        GridLayout input1PanelLayout = new GridLayout(1, 3);
        GridBagLayout buttonsPanelLayout = new GridBagLayout();

        /** ================= COMPONENTS ==================== */

        JPanel panel = new JPanel(panelLayout);
        JPanel inputPanel = new JPanel(inputPanelLayout);
        JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
        JPanel input1Panel = new JPanel(input1PanelLayout);
        JPanel input2Panel = new JPanel(new GridLayout(1, 1));
        JPanel input3Panel = new JPanel(new GridLayout(1, 1));

        // input1Panel's components
        JLabel mode1 = new JLabel("Dec to Bin", SwingConstants.CENTER);
        JLabel mode2 = new JLabel("Bin to Dec", SwingConstants.CENTER);
        JLabel mode3 = new JLabel("Normal", SwingConstants.CENTER);

        // input2Panel's componenets
        JLabel inputLabel = new JLabel(" ", SwingConstants.RIGHT);

        // input3Panel's components
        JLabel inputPrevious = new JLabel(" ", SwingConstants.RIGHT);

        // 1st row
        MavyButton addButton = new MavyButton("+", MavyProps.BLUE_COLOR);
        MavyButton subButton = new MavyButton("-", MavyProps.BLUE_COLOR);
        MavyButton mulButton = new MavyButton("x", MavyProps.BLUE_COLOR);
        MavyButton divButton = new MavyButton("/", MavyProps.BLUE_COLOR);

        // 2nd row
        MavyButton num7Button = new MavyButton("7", MavyProps.GREEN_COLOR);
        MavyButton num8Button = new MavyButton("8", MavyProps.GREEN_COLOR);
        MavyButton num9Button = new MavyButton("9", MavyProps.GREEN_COLOR);
        MavyButton clearButton = new MavyButton("C", MavyProps.RED_COLOR);

        // 3rd row
        MavyButton num4Button = new MavyButton("4", MavyProps.GREEN_COLOR);
        MavyButton num5Button = new MavyButton("5", MavyProps.GREEN_COLOR);
        MavyButton num6Button = new MavyButton("6", MavyProps.GREEN_COLOR);
        MavyButton switchModeButton = new MavyButton("S/M", MavyProps.YELLOW_COLOR);

        // 4th row
        MavyButton num1Button = new MavyButton("1", MavyProps.GREEN_COLOR);
        MavyButton num2Button = new MavyButton("2", MavyProps.GREEN_COLOR);
        MavyButton num3Button = new MavyButton("3", MavyProps.GREEN_COLOR);
        MavyButton powerButton = new MavyButton("On");

        // 5th row
        MavyButton num0Button = new MavyButton("0", MavyProps.GREEN_COLOR);
        MavyButton dotButton = new MavyButton(".", MavyProps.BLUE_COLOR);
        MavyButton equalsButton = new MavyButton("=", MavyProps.BLUE_COLOR);

        /** ==================== STYLES ===================== */

        panel.setBorder(PADDING);
        panel.setBackground(MavyProps.DARK_COLOR);

        mode1.setForeground(MavyProps.FORE_COLOR);
        mode2.setForeground(MavyProps.FORE_COLOR);
        mode3.setForeground(MavyProps.FORE_COLOR);

        mode1.setFont(MavyProps.createFont(14));
        mode2.setFont(MavyProps.createFont(14));
        mode3.setFont(MavyProps.createFont(14));

        inputPanel.setBorder(PADDING);
        inputPanel.setBackground(MavyProps.SURFACE_COLOR);

        inputLabel.setFont(MavyProps.createFont(28));
        inputLabel.setForeground(MavyProps.FORE_COLOR);

        inputPrevious.setFont(MavyProps.createFont(18));
        inputPrevious.setForeground(MavyProps.FORE_COLOR);

        input1Panel.setBackground(MavyProps.SURFACE_COLOR);
        input2Panel.setBackground(MavyProps.SURFACE_COLOR);
        input3Panel.setBackground(MavyProps.SURFACE_COLOR);

        buttonsPanel.setBorder(PADDING);
        buttonsPanel.setBackground(MavyProps.SURFACE_COLOR);

        powerButton.setCustomEnabled(true);
        powerButton.setForeground(MavyProps.GREEN_COLOR);

        /** =============== EVENT LISTENERS ================== */

        final ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Object src = e.getSource();

                String toAdd = "";

                if (src.equals(num0Button)) {
                    toAdd = "0";
                } else if (src.equals(num1Button)) {
                    toAdd = "1";
                } else if (src.equals(num2Button)) {
                    toAdd = "2";
                }  else if (src.equals(num3Button)) {
                    toAdd = "3";
                } else if (src.equals(num4Button)) {
                    toAdd = "4";
                } else if (src.equals(num5Button)) {
                    toAdd = "5";
                } else if (src.equals(num6Button)) {
                    toAdd = "6";
                } else if (src.equals(num7Button)) {
                    toAdd = "7";
                } else if (src.equals(num8Button)) {
                    toAdd = "8";
                } else if (src.equals(num9Button)) {
                    toAdd = "9";
                } else if (src.equals(dotButton)) {
                    toAdd = ".";
                } else if (src.equals(addButton)) {
                    toAdd = " + ";
                } else if (src.equals(subButton)) {
                    toAdd = " - ";
                } else if (src.equals(mulButton)) {
                    toAdd = " x ";
                } else if (src.equals(divButton)) {
                    toAdd = " / ";
                }

                inputLabel.setText(inputLabel.getText() + toAdd);
            }
        };

        /**
         * Attach listeners to the main buttons
         */
        num0Button.addActionListener(numListener);
        num1Button.addActionListener(numListener);
        num2Button.addActionListener(numListener);
        num3Button.addActionListener(numListener);
        num4Button.addActionListener(numListener);
        num5Button.addActionListener(numListener);
        num6Button.addActionListener(numListener);
        num7Button.addActionListener(numListener);
        num8Button.addActionListener(numListener);
        num9Button.addActionListener(numListener);
        dotButton.addActionListener(numListener);
        addButton.addActionListener(numListener);
        subButton.addActionListener(numListener);
        mulButton.addActionListener(numListener);
        divButton.addActionListener(numListener);

        /**
         * Equal button listener
         */
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String EXPRESSION = inputLabel.getText().trim();

                switch (mode % 3) {
                    // Normal mode
                    case 0:
                        inputPrevious.setText(EXPRESSION);
                        calculate(inputLabel, inputPrevious, EXPRESSION);
                        break;
                    // Decimal to Binary
                    case 1:
                        final String BIN = convertDecimalToBinary(EXPRESSION);

                        switch (BIN) {
                            // No decimal input
                            case "-1":
                                inputPrevious.setText("Please fill-in the field");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            // If has latters
                            case "-2":
                                inputPrevious.setText("Please input only numbers");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            // If has multiple dots
                            case "-3":
                                inputPrevious.setText("Please input only one dot");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            default:
                                inputPrevious.setText(BIN);
                                inputPrevious.setForeground(MavyProps.FORE_COLOR);
                        }

                        break;
                    // Binary to Decimal
                    case 2:
                        final String DECIMAL = convertBinaryToDecimal(EXPRESSION);

                        switch (DECIMAL) {
                            // No decimal input
                            case "-1":
                                inputPrevious.setText("Please fill-in the field");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            // If has latters
                            case "-2":
                                inputPrevious.setText("An error has occured");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            // If has multiple dots
                            case "-3":
                                inputPrevious.setText("Please input only one dot");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            // If has any number other than 0 and 1
                            case "-4":
                                inputPrevious.setText("Please input only 0 and 1");
                                inputPrevious.setForeground(MavyProps.RED_COLOR);
                                break;
                            default:
                                inputPrevious.setForeground(MavyProps.FORE_COLOR);
                                inputPrevious.setText(DECIMAL);
                        }

                        break;
                }
            }
        });

        /**
         * Clear button listener
         */
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputLabel.setText(" ");
                inputPrevious.setText(" ");
            }
        });

        /**
         * Switch mode button listener
         */
        switchModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = switchMode(mode3, mode1, mode2);

                inputLabel.setText(" ");
                inputPrevious.setText(" ");

                num2Button.setCustomEnabled(mode != 2);
                num3Button.setCustomEnabled(mode != 2);
                num4Button.setCustomEnabled(mode != 2);
                num5Button.setCustomEnabled(mode != 2);
                num6Button.setCustomEnabled(mode != 2);
                num7Button.setCustomEnabled(mode != 2);
                num8Button.setCustomEnabled(mode != 2);
                num9Button.setCustomEnabled(mode != 2);

                addButton.setCustomEnabled(mode == 0);
                subButton.setCustomEnabled(mode == 0);
                mulButton.setCustomEnabled(mode == 0);
                divButton.setCustomEnabled(mode == 0);
            }
        });

        /**
         * Power button listener
         */
        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOn = !isOn;

                if (!isOn) {
                    inputLabel.setText(" ");
                    inputPrevious.setText(" ");
                    mode = -1;
                    switchMode(mode3, mode1, mode2);
                    mode3.setForeground(MavyProps.FORE_COLOR);
                } else {
                    mode3.setForeground(MavyProps.YELLOW_COLOR);
                    num0Button.setForeground(MavyProps.GREEN_COLOR);
                    num1Button.setForeground(MavyProps.GREEN_COLOR);
                    num2Button.setForeground(MavyProps.GREEN_COLOR);
                    num3Button.setForeground(MavyProps.GREEN_COLOR);
                    num4Button.setForeground(MavyProps.GREEN_COLOR);
                    num5Button.setForeground(MavyProps.GREEN_COLOR);
                    num6Button.setForeground(MavyProps.GREEN_COLOR);
                    num7Button.setForeground(MavyProps.GREEN_COLOR);
                    num8Button.setForeground(MavyProps.GREEN_COLOR);
                    num9Button.setForeground(MavyProps.GREEN_COLOR);
                }

                /**
                 * Disable or enable buttons
                 * Using the custom enabling and disabling algorithm I made
                 */
                num0Button.setCustomEnabled(isOn);
                num1Button.setCustomEnabled(isOn);
                num2Button.setCustomEnabled(isOn);
                num3Button.setCustomEnabled(isOn);
                num4Button.setCustomEnabled(isOn);
                num5Button.setCustomEnabled(isOn);
                num6Button.setCustomEnabled(isOn);
                num7Button.setCustomEnabled(isOn);
                num8Button.setCustomEnabled(isOn);
                num9Button.setCustomEnabled(isOn);

                addButton.setCustomEnabled(isOn);
                subButton.setCustomEnabled(isOn);
                mulButton.setCustomEnabled(isOn);
                divButton.setCustomEnabled(isOn);

                switchModeButton.setCustomEnabled(isOn);
                clearButton.setCustomEnabled(isOn);
                dotButton.setCustomEnabled(isOn);
                equalsButton.setCustomEnabled(isOn);

                powerButton.setText(isOn ? "Off" : "On");
                powerButton.setForeground(isOn ? MavyProps.RED_COLOR : MavyProps.GREEN_COLOR);
            }
        });

        /** ============ ADDITION OF COMPONENTS ============== */

        input1Panel.add(mode1);
        input1Panel.add(mode2);
        input1Panel.add(mode3);

        input2Panel.add(inputLabel);
        input3Panel.add(inputPrevious);

        inputPanel.add(input1Panel);
        inputPanel.add(input2Panel);
        inputPanel.add(input3Panel);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(16, 8, 16, 8);

        // 1st row
        buttonsPanel.add(addButton, gbc);

        gbc.gridx++;
        buttonsPanel.add(subButton, gbc);

        gbc.gridx++;
        buttonsPanel.add(mulButton, gbc);

        gbc.gridx++;
        buttonsPanel.add(divButton, gbc);

        // 2nd row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num7Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num8Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num9Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(clearButton, gbc);

        // 3rd row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num4Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num5Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num6Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(switchModeButton, gbc);

        // 4th row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num1Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num2Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num3Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(powerButton, gbc);

        // 5th row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num0Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(dotButton, gbc);

        gbc.gridx++;
        gbc.gridwidth = 2;

        buttonsPanel.add(equalsButton, gbc);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}