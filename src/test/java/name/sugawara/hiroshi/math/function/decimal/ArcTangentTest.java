/**
 * Created Date : 2006/06/11 17:58:58
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * ArcTangentのテスト.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/06/11　17:58:58
 */
public final class ArcTangentTest extends DecimalFunctionTest {

  /**
   * テスト用変数.
   * 
   * @since 2006/06/11　18:01:06
   */
  private ArcTangent atan;
  /**
   * 
   * @since 2010/06/10　22:00:11
   */
  private ArcTangent atan2;

  /**
   * セットアップ.
   * 
   * @see TestCase#setUp()
   * @throws Exception
   *           例外.
   */
  @Before
  protected void setUp() throws Exception {
    this.atan = new ArcTangent(DecimalFunctionTest.PRECISION);
    this.atan2 = new ArcTangent(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'ArcTangent.ArcTangent(Precision)' のためのテスト・メソッド.
   */
  @Test
  public void testArcTangent() {
    Assert.assertFalse(this.atan.equals(this.atan2));
    Assert.assertNotNull(this.atan);
    Assert.assertNotNull(this.atan2);

  }

  /**
   * 'ArcTangent.getDependentVariable(BigDecimal)' のためのテスト・メソッド.
   */
  @Test
  public void testGetDependentVariable() {
    BigDecimal value1 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.EIGHT,
        DecimalFunctionTest.SCALE, DecimalFunctionTest.MODE).multiply(DecimalFunctionTest.FOUR);
    BigDecimal value2 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.TWO,
        DecimalFunctionTest.SCALE, DecimalFunctionTest.MODE);
    BigDecimal value3 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.TWO,
        DecimalFunctionTest.MC);

    System.out.println("value1=" + value1);
    System.out.println("value2=" + value2);
    System.out.println("value3=" + value2);
    Assert.assertEquals(value1.doubleValue(), value2.doubleValue(), 10e-324);
    Assert.assertEquals(value2.doubleValue(), value3.doubleValue(), 10e-324);
    Assert.assertEquals(value3.doubleValue(), value1.doubleValue(), 10e-324);

    String str = "";
    BigDecimal result;
    double doubleResult;
    for (double i = -1; i < 1; i += 0.05) {
      str = Double.toString(i);
      result = this.atan.getDependentVariable(new BigDecimal(str));
      doubleResult = Math.acos(i);

      System.out.println(" acos(" + str + ")= " + result + "\n");
      System.out.println("For Comparing To Math.acos(" + str + ") = " + doubleResult);
      Assert.assertEquals("引数の値=" + str, result.doubleValue(), doubleResult, 10e-324);
    }

    BigDecimal result1 = this.atan.getDependentVariable(BigDecimal.ONE);
    BigDecimal result2 = this.atan.getDependentVariable(BigDecimal.ONE.negate());
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
