/**
 * Created Date : 2006/07/07 0:24:22
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ExponentのTest.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/07　0:24:22
 */
public class ExponentTest {

  /**
   * テスト変数.
   * 
   * @since 2006/07/07　9:11:01
   */
  private Exponent exp;
  /**
   * 
   * @since 2010/06/11　2:40:36
   */
  private Exponent exp2;

  /**
   */
  @Before
  protected void setUp() throws Exception {
    this.exp = new Exponent(DecimalFunctionTest.PRECISION);
    this.exp2 = new Exponent(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.Exponent.Exponent(Precision)' のためのテスト・メソッド
   */
  @Test
  public final void testExponent() {
    Assert.assertFalse(this.exp.equals(this.exp2));
    Assert.assertNotNull(this.exp);
    Assert.assertNotNull(this.exp2);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.Exponent.getDependentVariable(BigDecimal)'
   * のためのテスト・メソッド
   */
  @Test
  public final void testGetDependentVariable() {

    StringBuilder sb = new StringBuilder();

    BigDecimal result = this.exp.getDependentVariable(new BigDecimal(0.0d));
    double doubleResult = Math.exp(0);
    sb.append("       Exponent.exp(0)=" + result);
    sb.append("\n      Math.exp(0)=" + doubleResult);

    BigDecimal result2 = this.exp.getDependentVariable(new BigDecimal(1.0d));
    double doubleResult2 = Math.exp(1);
    sb.append("\n       Exponent.exp(1)=" + result2);
    sb.append("\n      Math.exp(1)=" + doubleResult2);

    BigDecimal result3 = this.exp.getDependentVariable(new BigDecimal("1"));
    sb.append("\n      Exponent.expOfContinuedFraction(\"1\")=" + result3);

    BigDecimal result4 = this.exp.getDependentVariable(new BigDecimal("-20.5"));
    sb.append("\n      Exponent.exp(\"-20.5\")=" + result4);

    double doubleResult3 = Math.exp(-20.5d);
    sb.append("\n      Math.exp(-20.5d)=" + doubleResult3);

    System.out.println(new String(sb));

    Assert.assertEquals(result.doubleValue(), doubleResult, 10e-324);
    Assert.assertEquals(result2.doubleValue(), doubleResult2, 10e-16);
    Assert.assertEquals(result3.doubleValue(), doubleResult2, 10e-16);
    Assert.assertEquals(result4.doubleValue(), doubleResult3, 10e-324);

  }

}
