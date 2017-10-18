/**
 * Created Date : 2006/07/10 14:52:21
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/10 14:52:21
 */
public class FourQuadrantArcTangentTest extends DecimalFunctionTest {

  /**
   * 
   * @since 2010/06/11　2:41:49
   */
  private FourQuadrantArcTangent atan2a;

  /**
   * 
   * @since 2010/06/11　2:41:46
   */
  private FourQuadrantArcTangent atan2b;

  /**
   * セットアップ.
   */
  @Before
  protected void setUp() throws Exception {
    this.atan2a = new FourQuadrantArcTangent(DecimalFunctionTest.PRECISION);
    this.atan2b = new FourQuadrantArcTangent(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.FourQuadrantArcTangent.FourQuadrantArcTangent(Precision)
   * ' のためのテスト・メソッド
   */
  @Test
  public void testFourQuadrantArcTangent() {
    Assert.assertFalse(this.atan2a.equals(this.atan2b));
    Assert.assertNotNull(this.atan2a);
    Assert.assertNotNull(this.atan2b);
  }

  /**
   * 
   * 'name.sugawara.hiroshi.math.function.decimal.FourQuadrantArcTangent.getDependentVariable(BigDecimal
   * , BigDecimal)' のためのテスト・メソッド
   */
  @Test
  public void testGetDependentVariable() {
    int x, y;
    BigDecimal by, bx;

    x = 0;
    y = 0;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result1 = this.atan2a.getDependentVariable(by, bx);
    double dResult1 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result1);
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult1);

    x = 1;
    y = 0;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result2 = this.atan2a.getDependentVariable(by, bx);
    double dResult2 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result2);
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult2);

    x = 0;
    y = 1;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result3 = this.atan2a.getDependentVariable(by, bx);
    double dResult3 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result3);
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult3);

    x = 1;
    y = 1;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result4 = this.atan2a.getDependentVariable(by, bx);
    double dResult4 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result4);
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult4);

    x = -1;
    y = 0;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result5 = this.atan2a.getDependentVariable(by, bx);
    double dResult5 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result5 + "\n");
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult5);

    x = 0;
    y = -1;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result6 = this.atan2a.getDependentVariable(by, bx);
    double dResult6 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result6 + "\n");
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult6);

    x = -1;
    y = -1;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result7 = this.atan2a.getDependentVariable(by, bx);
    double dResult7 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result7 + "\n");
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult7);

    x = -1;
    y = 1;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result8 = this.atan2a.getDependentVariable(by, bx);
    double dResult8 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result8 + "\n");
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult8);

    x = 1;
    y = -1;
    by = new BigDecimal(Integer.toString(y));
    bx = new BigDecimal(Integer.toString(x));

    BigDecimal result9 = this.atan2a.getDependentVariable(by, bx);
    double dResult9 = Math.atan2(y, x);

    System.out.println(" atan2(" + y + ", " + x + ")= " + result9 + "\n");
    System.out.println("For Comparing To Math.atan2(" + y + ", " + x + ") = " + dResult9);

    Assert.assertEquals(result1.doubleValue(), dResult1, 10e-324);
    Assert.assertEquals(result2.doubleValue(), dResult2, 10e-324);
    Assert.assertEquals(result3.doubleValue(), dResult3, 10e-324);
    Assert.assertEquals(result4.doubleValue(), dResult4, 10e-324);
    Assert.assertEquals(result5.doubleValue(), dResult5, 10e-324);
    Assert.assertEquals(result6.doubleValue(), dResult6, 10e-324);
    Assert.assertEquals(result7.doubleValue(), dResult7, 10e-324);
    Assert.assertEquals(result8.doubleValue(), dResult8, 10e-324);

  }

}
