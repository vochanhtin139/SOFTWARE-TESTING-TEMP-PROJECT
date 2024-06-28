import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import demo.*;


class MavyInputCalculatorTest {
    @Test
    public void testEvaluate_NoOperators() {
        MavyInputCalculator calc = new MavyInputCalculator("10");
        Assertions.assertEquals("10", calc.calculate());
    }

    @Test
    public void testEvaluate_SimpleAddition() {
        MavyInputCalculator calc = new MavyInputCalculator("5+3");
        Assertions.assertEquals("8", calc.calculate());
    }

    @Test
    public void testEvaluate_SimpleMultiplication() {
        MavyInputCalculator calc = new MavyInputCalculator("2x5");
        Assertions.assertEquals("10", calc.calculate());
    }

    @Test
    public void testEvaluate_SimpleSubtraction() {
        MavyInputCalculator calc = new MavyInputCalculator("10-2");
        Assertions.assertEquals("8", calc.calculate());
    }

    @Test
    public void testEvaluate_SimpleDivision() {
        MavyInputCalculator calc = new MavyInputCalculator("10/2");
        Assertions.assertEquals("5", calc.calculate());
    }

    @Test
    public void testEvaluate_PEMDAS() {
        MavyInputCalculator calc = new MavyInputCalculator("2+3x4");
        Assertions.assertEquals("14", calc.calculate());
    }

    @Test
    public void testEvaluate_NegativeNumbers() {
        MavyInputCalculator calc = new MavyInputCalculator("-5+3");
        Assertions.assertEquals("-2", calc.calculate());
    }

    @Test
    public void testEvaluate_DecimalNumbers() {
        MavyInputCalculator calc = new MavyInputCalculator("2.5+3.2");
        Assertions.assertEquals("5.7", calc.calculate());
    }

    @Test
    public void testEvaluate_MalformedExpression_MissingOperand() {
        MavyInputCalculator calc = new MavyInputCalculator("5+");
        Assertions.assertEquals("E: MALFORMED_EXPRESSION", calc.calculate());
    }

    @Test
    public void testEvaluate_MalformedExpression_Empty() {
        MavyInputCalculator calc = new MavyInputCalculator("");
        Assertions.assertEquals("", calc.calculate());
    }

    @Test
    public void testEvaluate_NumberFormatException() {
        MavyInputCalculator calc = new MavyInputCalculator("abc+3");
        Assertions.assertEquals("E: For input string: \"abc\"", calc.calculate());
    }
}