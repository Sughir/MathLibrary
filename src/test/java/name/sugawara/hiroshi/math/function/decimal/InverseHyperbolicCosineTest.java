/**
 * Created Date : 2006/11/16 21:41:21
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;
import name.sugawara.hiroshi.math.function.typedouble.DoubleMath;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Before;
import org.junit.Test;

/**
 * arccosh()のテスト.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/11/16 21:41:21
 */
public class InverseHyperbolicCosineTest {
  /**
   * @since 2010/06/11　2:46:36
   */
  private InverseHyperbolicCosine arccosh;

  /**
   * @since 2010/06/11　2:46:38
   */
  private InverseHyperbolicCosine arccosh2;

  /**
   * セットアップ.
   * 
   * @throws java.lang.Exception
   * @since 2006/11/16 21:41:21
   */
  @Before
  public void setUp() throws Exception {
    Precision p = new NandEpsilon(BigInteger.valueOf(400), DecimalFunctionTest.EPS,
        DecimalFunctionTest.MC);
    this.arccosh = new InverseHyperbolicCosine(p);
    this.arccosh2 = new InverseHyperbolicCosine(p);
  }

  /**
   * 例外発生テスト.
   * 
   * @since 2006/11/17 16:36:35
   */
  @Test(expected = ArithmeticException.class)
  public void arithmetic() {
    this.arccosh.getDependentVariable(BigDecimal.ONE.negate());
    this.arccosh.getDependentVariable(BigDecimal.ZERO);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.InverseHyperbolicCosine#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    System.out.println("start testGetDependentVariable()");
    BigDecimal result = this.arccosh.getDependentVariable(BigDecimal.ONE);
    double dResult = DoubleMath.acosh(1);
    System.out.println("BigDecimal : " + result);
    System.out.println("double : " + dResult);

    Assert.assertEquals(result.doubleValue(), dResult, 10e-100);

    for (double i = 1; i < 10; i += 0.1) {
      result = this.arccosh.getDependentVariable(BigDecimal.valueOf(i));
      dResult = DoubleMath.acosh(i);
      System.out.printf("BigDecimal(%f) : %1.15e\n", Double.valueOf(i), result);
      System.out.printf("double(%f) : %1.15e\n", Double.valueOf(i), Double.valueOf(dResult));

      Assert.assertEquals(result.doubleValue(), dResult, 10e-15);
    }

    for (double i = 1; i < 1000; i += 100.1) {
      result = this.arccosh.getDependentVariable(BigDecimal.valueOf(i));
      dResult = DoubleMath.acosh(i);
      System.out.printf("BigDecimal(%f) : %1.15e\n", Double.valueOf(i), result);
      System.out.printf("double(%f) : %1.15e\n", Double.valueOf(i), Double.valueOf(dResult));

      Assert.assertEquals(result.doubleValue(), dResult, (10e-14));
    }
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.InverseHyperbolicCosine#InverseHyperbolicCosine(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testInverseHyperbolicCosine() {
    assertNotNull(this.arccosh);
    assertNotNull(this.arccosh2);
    Assert.assertNotSame(this.arccosh, this.arccosh2);
  }

}
