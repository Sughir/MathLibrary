/**
 * Created Date : 2006/11/20 17:35:50
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
 * @version $Id$ Created Date : 2006/11/20 17:35:50
 */
public class InverseHyperbolicTangentTest {

  /**
   * @since 2010/06/11　2:46:59
   */
  private InverseHyperbolicTangent arctanh;

  /**
   * @since 2010/06/11　2:47:02
   */
  private InverseHyperbolicTangent arctanh2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/20 17:35:51
   */
  @Before
  public void setUp() throws Exception {
    this.arctanh = new InverseHyperbolicTangent(DecimalFunctionTest.PRECISION);
    this.arctanh2 = new InverseHyperbolicTangent(DecimalFunctionTest.PRECISION);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.InverseHyperbolicTangent#InverseHyperbolicTangent(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testInverseHyperbolicTangent() {
    assertNotNull(this.arctanh);
    assertNotNull(this.arctanh2);
    assertNotSame(this.arctanh, this.arctanh2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.InverseHyperbolicTangent#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal result = this.arctanh.getDependentVariable(BigDecimal.ZERO);
    System.out.printf("%10.10e\n", result);

    double dResult = 0;
    for (double i = -0.5; i < 0.5; i += 0.01) {
      result = this.arctanh.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("arctanh(%f) = %2.15e   ", Double.valueOf(i), result);
      dResult = DoubleMath.atanh(i);
      System.out
          .printf("double arctanh(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-16);

    }

    Precision p = new NandEpsilon(BigInteger.valueOf(400), DecimalFunctionTest.EPS,
        DecimalFunctionTest.MC);
    this.arctanh = new InverseHyperbolicTangent(p);
    for (double i = -0.999; i < -0.5; i += 0.001) {
      result = this.arctanh.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("arctanh(%f) = %2.15e   ", Double.valueOf(i), result);
      dResult = DoubleMath.atanh(i);
      System.out
          .printf("double arctanh(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-15);
    }

    for (double i = 0.5; i < 0.998; i += 0.001) {
      result = this.arctanh.getDependentVariable(BigDecimal.valueOf(i));
      System.out.printf("arctanh(%f) = %2.15e   ", Double.valueOf(i), result);
      dResult = DoubleMath.atanh(i);
      System.out
          .printf("double arctanh(%f) = %2.15e\n", Double.valueOf(i), Double.valueOf(dResult));
      assertEquals(result.doubleValue(), dResult, 10e-15);

    }

    result = this.arctanh.getDependentVariable(new BigDecimal("0.998"));
    System.out.printf("arctanh(%f) = %2.15e   ", Double.valueOf(0.998), result);
    dResult = DoubleMath.atanh(0.998);
    System.out.printf("double arctanh(%f) = %2.15e\n", Double.valueOf(0.998), Double
        .valueOf(dResult));
    assertEquals(result.doubleValue(), dResult, 10e-14);

  }
}
