package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.demo.*;

import javax.swing.*;

class MavySimpleCalculatorTest {
    @Test
    public void testConvertDecimalToBinaryValidInteger() {
        Assertions.assertEquals("1010", MavySimpleCalculator.convertDecimalToBinary("10"));
    }

    @Test
    public void testConvertDecimalToBinaryValidDecimal() {
        Assertions.assertEquals("1010.001", MavySimpleCalculator.convertDecimalToBinary("10.125"));
    }

    @Test
    public void testConvertDecimalToBinaryEmptyInput() {
        Assertions.assertEquals("-1", MavySimpleCalculator.convertDecimalToBinary(""));
    }

    @Test
    public void testConvertDecimalToBinaryInvalidCharacter() {
        Assertions.assertEquals("-2", MavySimpleCalculator.convertDecimalToBinary("10a"));
    }

    @Test
    public void testConvertDecimalToBinaryMultipleDots() {
        Assertions.assertEquals("-3", MavySimpleCalculator.convertDecimalToBinary("10.1.0"));
    }

    // Test convertBinaryToDecimal
    @Test
    public void testConvertBinaryToDecimalValidBinaryInteger() {
        Assertions.assertEquals("10", MavySimpleCalculator.convertBinaryToDecimal("1010"));
    }

    @Test
    public void testConvertBinaryToDecimalValidBinaryDecimal() {
        Assertions.assertEquals("10.125", MavySimpleCalculator.convertBinaryToDecimal("1010.001"));
    }

    @Test
    public void testConvertBinaryToDecimalEmptyInput() {
        Assertions.assertEquals("-1", MavySimpleCalculator.convertBinaryToDecimal(""));
    }

//    @Test
//    public void testConvertBinaryToDecimalInvalidCharacter() {
//        Assertions.assertEquals("-2", MavySimpleCalculator.convertBinaryToDecimal("10.2"));
//    }

    @Test
    public void testConvertBinaryToDecimalMultipleDots() {
        Assertions.assertEquals("-3", MavySimpleCalculator.convertBinaryToDecimal("10.1.0"));
    }

    @Test
    public void testConvertBinaryToDecimalNonBinaryCharacter() {
        Assertions.assertEquals("-4", MavySimpleCalculator.convertBinaryToDecimal("10.1a"));
    }

    // Test switchMode
    @Test
    public void testSwitchMode() {
        JLabel normal = new JLabel("Normal");
        JLabel db = new JLabel("Dec to Bin");
        JLabel bd = new JLabel("Bin to Dec");

        Assertions.assertEquals(1, MavySimpleCalculator.switchMode(normal, db, bd)); // Switch to Dec to Bin
        Assertions.assertEquals(2, MavySimpleCalculator.switchMode(normal, db, bd)); // Switch to Bin to Dec
        Assertions.assertEquals(0, MavySimpleCalculator.switchMode(normal, db, bd)); // Switch to Normal
    }
}