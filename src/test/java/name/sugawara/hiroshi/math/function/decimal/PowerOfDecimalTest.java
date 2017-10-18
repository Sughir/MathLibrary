/**
 * Created Date : 2006/11/23 2:59:20
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/11/23 2:59:20
 */
public class PowerOfDecimalTest {

  /**
   * @since 2010/06/11　2:49:54
   */
  private PowerOfDecimal power;

  /**
   * @since 2010/06/11　2:49:52
   */
  private PowerOfDecimal power2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/23 2:59:20
   */
  @Before
  public void setUp() throws Exception {
    this.power = new PowerOfDecimal(DecimalFunctionTest.PRECISION);
    this.power2 = new PowerOfDecimal(DecimalFunctionTest.PRECISION);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.PowerOfDecimal#PowerOfDecimal(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testPowerOfDecimal() {
    assertNotNull(this.power);
    assertNotNull(this.power2);
    assertNotSame(this.power, this.power2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.PowerOfDecimal#getDependentVariable(java.math.BigDecimal, java.math.BigDecimal)}
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
    final BigDecimal maxJ = new BigDecimal("100");
    for (BigDecimal i = new BigDecimal("-10"); i.compareTo(new BigDecimal("-0.07")) < 0; i = i
        .add(incriment)) {
      for (BigDecimal j = new BigDecimal("-100"); j.compareTo(maxJ) < 0; j = j.add(BigDecimal.ONE)) {
        result = this.power.getDependentVariable(i, j);
        System.out.printf("pow(%f,%f) = %2.15e ", i, j, result);
        dResult = Math.pow(i.doubleValue(), j.doubleValue());
        System.out.printf("double pow(%f,%f) = %2.15e\n", i, j, Double.valueOf(dResult));

        assertEquals(result.doubleValue(), dResult, Math.abs(Math.pow(i.doubleValue(), j
            .doubleValue()))
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
      for (BigDecimal j = new BigDecimal("-100"); j.compareTo(maxJ) < 0; j = j.add(BigDecimal.ONE)) {
        result = this.power.getDependentVariable(i, j);
        System.out.printf("pow(%f,%f) = %2.15e ", i, j, result);
        dResult = Math.pow(i.doubleValue(), j.doubleValue());
        System.out.printf("double pow(%f,%f) = %2.15e\n", i, j, Double.valueOf(dResult));

        assertEquals(result.doubleValue(), dResult, Math.abs(Math.pow(i.doubleValue(), j
            .doubleValue()))
            * Math.pow(10, 16));
      }
    }

    for (BigDecimal i = incriment; i.compareTo(BigDecimal.TEN) < 0; i = i.add(incriment)) {
      for (BigDecimal j = new BigDecimal("-100"); j.compareTo(maxJ) < 0; j = j.add(incriment)) {
        result = this.power.getDependentVariable(i, j);
        System.out.printf("pow(%f,%f) = %2.15e ", i, j, result);
        dResult = Math.pow(i.doubleValue(), j.doubleValue());
        System.out.printf("double pow(%f,%f) = %2.15e\n", i, j, Double.valueOf(dResult));

        assertEquals(result.doubleValue(), dResult, Math.abs(Math.pow(i.doubleValue(), j
            .doubleValue()))
            * Math.pow(10, 16));
      }
    }
  }

}
