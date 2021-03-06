/**
 * Created Date : 2006/11/21 0:05:46
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
 * @version $Id$ Created Date : 2006/11/21 0:05:46
 */
public class LogTest {

  /**
   * @since 2010/06/11　2:48:14
   */
  private Log log;

  /**
   * @since 2010/06/11　2:48:12
   */
  private Log log2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/21 0:05:46
   */
  @Before
  public void setUp() throws Exception {
    this.log = new Log(DecimalFunctionTest.PRECISION);
    this.log2 = new Log(DecimalFunctionTest.PRECISION);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Log#Log(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testLog() {
    assertNotNull(this.log);
    assertNotNull(this.log2);
    assertNotSame(this.log, this.log2);
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
   * {@link name.sugawara.hiroshi.math.function.decimal.Log#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal result = null;
    double dResult = 0;
    for (double i = 1; i < 100; i += 0.001) {
      result = this.log.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("log(%f) = %2.15e ", Double.valueOf(i), result);
      dResult = Math.log(i);
      System.out.printf("double log(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-15);
    }

    Precision p = new NandEpsilon(BigInteger.valueOf(400), DecimalFunctionTest.EPS,
        DecimalFunctionTest.MC);
    this.log = new Log(p);
    for (double i = 0.001; i < 1; i += 0.001) {
      result = this.log.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("log(%f) = %2.15e   ", Double.valueOf(i), result);
      dResult = Math.log(i);
      System.out.printf("double log(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-15);
    }

  }

}
