/**
 * Created Date : 2006/11/21 19:04:11
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/11/21 19:04:11
 */
public class Log2Test {
  /**
   * @since 2010/06/11　2:48:14
   */
  private Log2 log;

  /**
   * @since 2010/06/11　2:48:12
   */
  private Log2 log2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/21 19:04:11
   */
  @Before
  public void setUp() throws Exception {
    this.log = new Log2(DecimalFunctionTest.PRECISION);
    this.log2 = new Log2(DecimalFunctionTest.PRECISION);
  }

  /**
   * 対数に0や負の値を入れたときの例外発生をテスト.
   * 
   * @since 2006/11/21 17:37:46
   */
  @Test(expected = ArithmeticException.class)
  public final void negativeNumber() {
    this.log.getDependentVariable(BigDecimal.ZERO);
    this.log.getDependentVariable(BigDecimal.ONE.negate());
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Log2#Log2(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testLog2() {
    assertNotNull(this.log);
    assertNotNull(this.log2);
    assertNotSame(this.log, this.log2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Log2#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal result = null;
    double dResult = 0;
    for (double i = 1; i < 100; i += 0.001) {
      result = this.log.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("log2(%f) = %2.15e ", Double.valueOf(i), result);
      dResult = Math.log(i) / Math.log(2);
      System.out.printf("double log2(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-15);
    }

    Precision p = new NandEpsilon(BigInteger.valueOf(400), DecimalFunctionTest.EPS,
        DecimalFunctionTest.MC);
    this.log = new Log2(p);
    for (double i = 0.001; i < 1; i += 0.001) {
      result = this.log.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("log2(%f) = %2.15e   ", Double.valueOf(i), result);
      dResult = Math.log(i) / Math.log(2);
      System.out.printf("double log2(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-15);
    }
  }

}
