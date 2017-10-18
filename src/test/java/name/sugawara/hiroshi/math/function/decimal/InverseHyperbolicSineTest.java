/**
 * Created Date : 2006/11/18 0:41:35
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.typedouble.DoubleMath;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/11/18 0:41:35
 */
public class InverseHyperbolicSineTest {

  /**
   * @since 2010/06/11　2:46:59
   */
  private InverseHyperbolicSine arcsinh;

  /**
   * @since 2010/06/11　2:47:02
   */
  private InverseHyperbolicSine arcsinh2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/18 0:41:35
   */
  @Before
  public void setUp() throws Exception {
    BigInteger n = BigInteger.valueOf(200);
    Precision p = new NandEpsilon(n, DecimalFunctionTest.EPS, DecimalFunctionTest.MC);
    this.arcsinh = new InverseHyperbolicSine(p);
    this.arcsinh2 = new InverseHyperbolicSine(p);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.InverseHyperbolicSine#InverseHyperbolicSine(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testInverseHyperbolicSine() {
    assertNotNull(this.arcsinh);
    assertNotNull(this.arcsinh2);
    assertNotSame(this.arcsinh, this.arcsinh2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.InverseHyperbolicSine#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal result = this.arcsinh.getDependentVariable(BigDecimal.ONE);
    System.out.printf("%10.10e\n", result);

    double dResult = 0;
    for (double i = -100; i < 100; i += 0.1) {
      result = this.arcsinh.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("arcsinh(%f) = %2.15e   ", Double.valueOf(i), result);
      dResult = DoubleMath.asinh(i);
      System.out
          .printf("double arcsinh(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-16);

    }
  }

}
