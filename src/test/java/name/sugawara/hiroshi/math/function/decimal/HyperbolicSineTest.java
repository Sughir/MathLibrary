/**
 * Created Date : 2006/07/10 17:13:44
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import junit.framework.Assert;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/10 17:13:44
 */
public class HyperbolicSineTest extends DecimalFunctionTest {
  /**
   * @since 2010/06/11　2:43:33
   */
  private HyperbolicSine sinh;

  /**
   * @since 2010/06/11　2:43:35
   */
  private HyperbolicSine sinh2;

  /**
   * @see DecimalFunctionTest#setUp()
   */
  @Before
  protected void setUp() throws Exception {
    MathContext mc = new MathContext(100, RoundingMode.HALF_EVEN);
    Precision p = new NandEpsilon(new BigInteger("1200"), DecimalFunctionTest.EPS, mc);

    // this.sinh = new HyperbolicSine(DecimalFunctionTest.PRECISION);
    // this.sinh2 = new HyperbolicSine(DecimalFunctionTest.PRECISION);
    this.sinh = new HyperbolicSine(p);
    this.sinh2 = new HyperbolicSine(p);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.HyperbolicSine.HyperbolicSine(Precision)'
   * のためのテスト・メソッド
   */
  public void testHyperbolicSine() {
    Assert.assertFalse(this.sinh.equals(this.sinh2));
    Assert.assertNotNull(this.sinh);
    Assert.assertNotNull(this.sinh2);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.HyperbolicSine.getDependentVariable(BigDecimal)'
   * のためのテスト・メソッド
   */
  @Test
  public void testGetDependentVariable() {
    BigDecimal b;

    double real;
    for (int i = -5; i < 5; i++) {
      b = this.sinh.getDependentVariable(new BigDecimal(i));
      System.out.println("BigDecimal sinh(" + i + ")=" + b);
      real = Math.sinh(i);
      System.out.println("double sinh(" + i + ")=" + real);

      Assert.assertEquals(b.doubleValue(), real, 10e-13);
    }

    b = this.sinh.getDependentVariable(new BigDecimal("500"));
    real = Math.sinh(500);
    System.out.println("BigDecimal sinh(" + 500 + ")=" + b);
    System.out.println("double sinh(" + 500 + ")=" + real);

    Assert.assertEquals(b.doubleValue(), real, 10e-13);

  }

  /**
   * 'HyperbolicSine.sinhWithResidual(BigDecimal,Precision)' のためのテスト・メソッド
   */
  @Test
  public void testSinhWithResidual() {
    BigDecimal b;

    double real;

    MathContext mc = new MathContext(100, RoundingMode.HALF_EVEN);
    Precision p = new NandEpsilon(new BigInteger("1200"), DecimalFunctionTest.EPS, mc);

    b = HyperbolicSine.sinhWithResidual(new BigDecimal("500"), p);
    real = Math.sinh(500);
    System.out.println("BigDecimal sinh(" + 500 + ")=" + b);
    System.out.println("double sinh(" + 500 + ")=" + real);

    Assert.assertEquals(b.doubleValue(), real, 10e201);

  }
}
