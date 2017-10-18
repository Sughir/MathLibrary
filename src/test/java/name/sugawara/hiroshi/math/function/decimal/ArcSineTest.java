/**
 * Created Date : 2006/06/11 18:24:57
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ArcSineのテスト.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * 
 * Created Date : 2006/06/11 18:24:57
 * 
 */
public class ArcSineTest extends DecimalFunctionTest {


  /**
   * テスト変数.
   * 
   * @since 2006/06/11 18:27:41
   */
  private ArcSine asin;
  /**
   * 
   * @since 2010/06/10　21:59:09
   */
  private ArcSine asin2;


   /**
   * セットアップ.
   * 
   * @see TestCase#setUp()
   * @throws Exception
   *           例外.
   */
  @Before
  protected void setUp() throws Exception {
    this.asin = new ArcSine(DecimalFunctionTest.PRECISION);
    this.asin2 = new ArcSine(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'ArcSine.ArcSine(Precision)' のためのテスト・メソッド.
   */
  @Test
  public final void testArcSine() {
    Assert.assertFalse(this.asin.equals(this.asin2));
    Assert.assertNotNull(this.asin);
    Assert.assertNotNull(this.asin2);

  }

  /**
   * 'ArcSine.getDependentVariable(BigDecimal)' のためのテスト・メソッド.
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal value1 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.EIGHT,
        DecimalFunctionTest.SCALE, DecimalFunctionTest.MODE).multiply(DecimalFunctionTest.FOUR);
    BigDecimal value2 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.TWO,
        DecimalFunctionTest.SCALE, DecimalFunctionTest.MODE);
    BigDecimal value3 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.TWO,
        DecimalFunctionTest.MC);

    System.out.println("value1=" + value1);
    System.out.println("value2=" + value2);
    System.out.println("value3=" + value3);
    Assert.assertEquals(value1.doubleValue(), value2.doubleValue(), 10e-324);
    Assert.assertEquals(value2.doubleValue(), value3.doubleValue(), 10e-324);
    Assert.assertEquals(value3.doubleValue(), value1.doubleValue(), 10e-324);

    String str = "";
    BigDecimal result;
    double doubleResult;
    for (double i = -1; i < 1; i += 0.05) {
      str = Double.toString(i);
      result = this.asin.getDependentVariable(new BigDecimal(str));
      doubleResult = Math.acos(i);

      System.out.println(" acos(" + str + ")= " + result + "\n");
      System.out.println("For Comparing To Math.acos(" + str + ") = " + doubleResult);
      Assert.assertEquals("引数の値=" + str, result.doubleValue(), doubleResult, 10e-324);
    }

    BigDecimal result1 = this.asin.getDependentVariable(BigDecimal.ONE);
    BigDecimal result2 = this.asin.getDependentVariable(BigDecimal.ONE.negate());
    System.out.println(" acos(1)= " + result1);
    System.out.println(" acos(-1)= " + result2);

    double doubleResult1 = Math.acos(1);
    double doubleResult2 = Math.acos(-1);

    System.out.println("For Comparing To Math.acos(-1) = " + Math.acos(-1));
    System.out.println("For Comparing To Math.acos(1) = " + Math.acos(1));

    Assert.assertEquals(result1.doubleValue(), doubleResult1, 10e-324);
    Assert.assertEquals(result2.doubleValue(), doubleResult2, 10e-324);

  }

}
