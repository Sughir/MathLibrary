/**
 * Created Date : 2006/07/06 15:25:57
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Cosineのテストケース.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/06 15:25:57
 */
public final class CosineTest extends DecimalFunctionTest {

  /**
   * テスト変数.
   * 
   * @since 2006/06/11 18:27:41
   */
  private Cosine cos;

  /**
   * @since 2010/06/10　22:01:12
   */
  private Cosine cos2;

  /**
   * セットアップ.
   * 
   * @see TestCase#setUp()
   */
  @Before
  protected void setUp() throws Exception {
    this.cos = new Cosine(DecimalFunctionTest.PRECISION);
    this.cos2 = new Cosine(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'Cosine.Cosine(Precision)' のためのテスト・メソッド.
   */
  @Test
  public void testCosine() {
    Assert.assertFalse(this.cos.equals(this.cos2));
    Assert.assertNotNull(this.cos);
    Assert.assertNotNull(this.cos2);

  }

  /**
   * 'Cosine.getDependentVariable(BigDecimal)' のためのテスト・メソッド.
   */
  @Test
  public void testGetDependentVariable() {

    BigDecimal value;

    BigDecimal value2 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.EIGHT,
        DecimalFunctionTest.SCALE, DecimalFunctionTest.MODE).multiply(DecimalFunctionTest.FOUR);
    BigDecimal value3 = DecimalFunctionTest.pi().divide(DecimalFunctionTest.TWO,
        DecimalFunctionTest.SCALE, DecimalFunctionTest.MODE);

    System.out.println("value2=" + value2);
    System.out.println("value3=" + value3);

    String str = "";

    Cosine cos = new Cosine(DecimalFunctionTest.PRECISION);

    for (int i = -20; i < 20; i++) {
      str = Integer.toString(i);
      value = DecimalFunctionTest.pi().divide(DecimalFunctionTest.EIGHT, DecimalFunctionTest.SCALE,
          DecimalFunctionTest.MODE).multiply(new BigDecimal(str));
      System.out.println(" cos(" + value + ")= " + cos.getDependentVariable(value) + "\n");
      System.out.println("For Comparing To Math.cos(" + value.doubleValue() + ") = "
          + Math.cos(value.doubleValue()));

    }

    for (int i = -20; i < 20; i++) {
      str = Integer.toString(i);
      value = DecimalFunctionTest.pi().divide(DecimalFunctionTest.EIGHT, DecimalFunctionTest.SCALE,
          DecimalFunctionTest.MODE).multiply(new BigDecimal(str));
      System.out.println(" (" + value + ")= " + cos.getDependentVariable(value) + "\n");

      double d = 1 - Math.cos(value.doubleValue());
      System.out.println("For Comparing To 1 - Math.cos(" + value.doubleValue() + ") = " + d);
    }
  }
}
