/**
 * Created Date : 2006/07/10 17:00:35
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/10 17:00:35
 */
public class HyperbolicCosineTest extends DecimalFunctionTest {

  /**
   * @since 2010/06/11　2:42:34
   */
  private HyperbolicCosine cosh;

  /**
   * @since 2010/06/11　2:42:32
   */
  private HyperbolicCosine cosh2;

  /**
   * @see DecimalFunctionTest#setUp()
   */
  @Before
  protected void setUp() throws Exception {
    this.cosh = new HyperbolicCosine(DecimalFunctionTest.PRECISION);
    this.cosh2 = new HyperbolicCosine(DecimalFunctionTest.PRECISION);

  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.HyperbolicCosine.HyperbolicCosine(Precision)'
   * のためのテスト・メソッド
   */
  @Test
  public void testHyperbolicCosine() {
    Assert.assertFalse(this.cosh.equals(this.cosh2));
    Assert.assertNotNull(this.cosh);
    Assert.assertNotNull(this.cosh2);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.HyperbolicCosine.getDependentVariable(BigDecimal)'
   * のためのテスト・メソッド
   */
  @Test
  public void testGetDependentVariable() {
    BigDecimal b;

    double real;
    for (int i = -5; i < 5; i++) {
      b = this.cosh.getDependentVariable(new BigDecimal(i));
      System.out.println("BigDecimal cosh(" + i + ")=" + b);
      real = Math.cosh(i);
      System.out.println("double cosh(" + i + ")=" + real);

      Assert.assertEquals(b.doubleValue(), real, 10e-13);

    }

    b = this.cosh.getDependentVariable(new BigDecimal("500"));
    real = Math.cosh(500);
    System.out.println("BigDecimal cosh(" + 500 + ")=" + b);
    System.out.println("double cosh(" + 500 + ")=" + real);

    Assert.assertEquals(b.doubleValue(), real, 10e201);
  }

}
