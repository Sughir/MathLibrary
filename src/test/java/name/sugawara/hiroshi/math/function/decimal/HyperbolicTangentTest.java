/**
 * Created Date : 2006/07/10 17:23:07
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/10 17:23:07
 */
public class HyperbolicTangentTest extends DecimalFunctionTest {

  /**
   * @since 2010/06/11　2:44:37
   */
  private HyperbolicTangent tanh;

  /**
   * @since 2010/06/11　2:44:38
   */
  private HyperbolicTangent tanh2;

  /**
   * @see DecimalFunctionTest#setUp()
   */
  @Before
  protected void setUp() throws Exception {
    this.tanh = new HyperbolicTangent(DecimalFunctionTest.PRECISION);
    this.tanh2 = new HyperbolicTangent(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.HyperbolicTangent.HyperbolicTangent(Precision)'
   * のためのテスト・メソッド
   */
  @Test
  public void testHyperbolicTangent() {
    Assert.assertFalse(this.tanh.equals(this.tanh2));
    Assert.assertNotNull(this.tanh);
    Assert.assertNotNull(this.tanh2);
  }

  /**
   * 'HyperbolicTangent.getDependentVariable(BigDecimal)' のためのテスト・メソッド
   */
  @Test
  public void testGetDependentVariable() {
    BigDecimal b;

    double real;
    for (int i = -5; i < 5; i++) {
      b = this.tanh.getDependentVariable(new BigDecimal(i));
      System.out.println("BigDecimal tanh(" + i + ")=" + b);
      real = Math.tanh(i);
      System.out.println("double tanh(" + i + ")=" + real);

      Assert.assertEquals(b.doubleValue(), real, 10e-13);
    }

    b = this.tanh.getDependentVariable(new BigDecimal("500"));
    real = Math.tanh(500);
    System.out.println("BigDecimal tanh(" + 500 + ")=" + b);
    System.out.println("double tanh(" + 500 + ")=" + real);

    Assert.assertEquals(b.doubleValue(), real, 10e-13);
  }
}
