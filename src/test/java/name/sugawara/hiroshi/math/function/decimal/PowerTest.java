/**
 * Created Date : 2006/11/21 21:08:34
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/11/21 21:08:34
 */
public class PowerTest {
  /**
   * @since 2010/06/11　2:50:15
   */
  private Power power;

  /**
   * @since 2010/06/11　2:50:16
   */
  private Power power2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/21 21:08:34
   */
  @Before
  public void setUp() throws Exception {
    this.power = new Power(DecimalFunctionTest.PRECISION);
    this.power2 = new Power(DecimalFunctionTest.PRECISION);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Power#Power(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testPower() {
    assertNotNull(this.power);
    assertNotNull(this.power2);
    assertNotSame(this.power, this.power2);

  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Power#getDependentVariable(java.math.BigDecimal, java.math.BigInteger)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {

    // Precision p = new NandEpsilon(BigInteger.valueOf(1000),
    // DecimalFunctionTest.EPS,
    // DecimalFunctionTest.MC);
    // this.power = new Power(p);
    BigDecimal result = null;
    double dResult = 0;
    final BigDecimal incriment = new BigDecimal("0.001");
    for (BigDecimal i = new BigDecimal("-10"); i.compareTo(new BigDecimal("-0.07")) < 0; i = i
        .add(incriment)) {
      for (int j = -100; j < 100; j++) {
        result = this.power.getDependentVariable(i, BigInteger.valueOf(j));
        System.out.printf("pow(%f,%d) = %2.15e ", i, Integer.valueOf(j), result);
        dResult = Math.pow(i.doubleValue(), j);
        System.out.printf("double pow(%f,%d) = %2.15e\n", i, Integer.valueOf(j), Double
            .valueOf(dResult));

        assertEquals(result.doubleValue(), dResult, Math.abs(Math.pow(i.doubleValue(), j))
            * Math.pow(10, 16));
      }
    }

    // BigDecimal eps = new BigDecimal("0.000000000000000000000000000001");//
    // +0000000000
    // MathContext mc = new MathContext(10000, RoundingMode.HALF_EVEN);
    // p = new NandEpsilon(BigInteger.valueOf(4000), DecimalFunctionTest.EPS,
    // DecimalFunctionTest.MC);
    // this.power = new Power(p);
    // p = new NandEpsilon(BigInteger.valueOf(4000), eps, mc);
    // this.power = new Power(p);

    for (BigDecimal i = new BigDecimal("-0.07"); i.compareTo(BigDecimal.ZERO) < 0; i = i
        .add(incriment)) {
      for (int j = -100; j < 100; j++) {
        result = this.power.getDependentVariable(i, BigInteger.valueOf(j));
        System.out.printf("pow(%f,%d) = %2.15e ", i, Integer.valueOf(j), result);
        dResult = Math.pow(i.doubleValue(), j);
        System.out.printf("double pow(%f,%d) = %2.15e\n", i, Integer.valueOf(j), Double
            .valueOf(dResult));

        assertEquals(result.doubleValue(), dResult, Math.abs(Math.pow(i.doubleValue(), j))
            * Math.pow(10, 16));
      }
    }

    for (BigDecimal i = incriment; i.compareTo(BigDecimal.TEN) < 0; i = i.add(incriment)) {
      for (int j = -100; j < 100; j++) {
        result = this.power.getDependentVariable(i, BigInteger.valueOf(j));
        System.out.printf("pow(%f,%d) = %2.15e ", i, Integer.valueOf(j), result);
        dResult = Math.pow(i.doubleValue(), j);
        System.out.printf("double pow(%f,%d) = %2.15e\n", i, Integer.valueOf(j), Double
            .valueOf(dResult));

        assertEquals(result.doubleValue(), dResult, Math.abs(Math.pow(i.doubleValue(), j)));
      }
    }
  }
}
