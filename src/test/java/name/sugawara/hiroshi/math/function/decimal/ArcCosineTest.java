/**
 * 
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ArcCosineのテスト.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2005/07/13 9:19:31
 */
public class ArcCosineTest extends DecimalFunctionTest {

  /**
   * テスト用変数.
   * 
   * @since 2005/09/14 18:31:36
   */
  private ArcCosine acos;

  /**
   * テスト用変数.
   * 
   * @since 2005/09/14 18:31:53
   */
  private ArcCosine acos2;

  /**
   * メイン.
   * 
   * @param args
   *          引数
   * @since 2005/07/13 9:20:09
   */
  // public static void main(final String[] args) {
  // junit.swingui.TestRunner.run(ArcCosineTest.class);
  // }

  /**
   * セットアップ.
   * 
   * @since 2005/07/13 9:20:40
   * @throws Exception
   *           例外.
   */
  @Before
  protected final void setUp() throws Exception {

    this.acos = new ArcCosine(DecimalFunctionTest.PRECISION);
    this.acos2 = new ArcCosine(DecimalFunctionTest.PRECISION);

  }

  /**
   * Test method for 'ArcCosine.ArcCosine(Precision)'.
   * 
   * @since 2005/07/13 9:21:19
   */
  @Test
  public final void testArcCosine() {
    Assert.assertFalse(this.acos.equals(this.acos2));
    Assert.assertNotNull(this.acos);
    Assert.assertNotNull(this.acos2);
  }

  /**
   * Test method for 'ArcCosine.getDependentVariable(BigDecimal)'.
   * 
   * @since 2005/07/13 9:21:31
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
      result = this.acos.getDependentVariable(new BigDecimal(str));
      doubleResult = Math.acos(i);

      System.out.println(" acos(" + str + ")= " + result + "\n");
      System.out.println("For Comparing To Math.acos(" + str + ") = " + doubleResult);
      Assert.assertEquals("引数の値=" + str, result.doubleValue(), doubleResult, 10e-324);
    }

    BigDecimal result1 = this.acos.getDependentVariable(BigDecimal.ONE);
    BigDecimal result2 = this.acos.getDependentVariable(BigDecimal.ONE.negate());
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
