/**
 * 
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2005/09/14 18:38:31
 */
public final class SquareRootTest extends DecimalFunctionTest {

  /**
   * テスト用変数.
   * 
   * @since 2005/09/14 18:54:58
   */
  private SquareRoot sqrt;

  /**
   * テスト用変数.
   * 
   * @since 2005/09/14 18:55:20
   */
  private SquareRoot sqrt2;

  /**
   * セットアップ.
   */
  @Before
  protected void setUp() throws Exception {
    this.sqrt = new SquareRoot(PRECISION);
    this.sqrt2 = new SquareRoot(PRECISION);

  }

  /**
   * Test method for 'SquareRoot.SquareRoot(Precision)'.
   */
  @Test
  public void testSquareRoot() {
    assertNotNull(this.sqrt);
    assertNotNull(this.sqrt2);
    assertFalse(this.sqrt.equals(this.sqrt2));
  }

  /**
   * Test method for 'SquareRoot.getDependentVariable(BigDecimal)'.
   */
  @Test
  public void testGetDependentVariable() {
    BigDecimal result = this.sqrt.getDependentVariable(TWO);
    double doubleResult = Math.sqrt(2);

    System.out.println("sqrt2= " + result);
    System.out.println("Math.sqrt(2)= " + doubleResult);

    assertEquals(result.doubleValue(), doubleResult, DOUBLE_MINIMUM_EPS);

    BigDecimal resultX;
    double doubleResultX;

    for (double i = 0.0d; i < 100.0d; i += 0.001d) {
      resultX = this.sqrt.getDependentVariable(new BigDecimal(i));
      doubleResultX = Math.sqrt(i);
      assertEquals(resultX.doubleValue(), doubleResultX, DOUBLE_MINIMUM_EPS);
    }
  }

}
