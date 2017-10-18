/**
 * Created Date : 2006/07/20 17:46:33
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/20 17:46:33
 */
public final class HypotenuseTest extends DecimalFunctionTest {

  /**
   * @since 2010/06/11　2:45:42
   */
  private Hypotenuse h;

  /**
   * @since 2010/06/11　2:45:40
   */
  private Hypotenuse h2;

  /**
   * @see DecimalFunctionTest#setUp()
   */
  @Before
  protected void setUp() throws Exception {
    Precision p = new NandEpsilon(BigInteger.valueOf(5), DecimalFunctionTest.EPS,
        DecimalFunctionTest.MC);
    this.h = new Hypotenuse(p);
    this.h2 = new Hypotenuse(p);
  }

  /**
   * 'name.sugawara.hiroshi.math.function.decimal.Hypotenuse.Hypotenuse(Precision)' のためのテスト・メソッド
   */
  @Test
  public void testHypotenuse() {

    Assert.assertNotNull(this.h);
    Assert.assertNotNull(this.h2);
    Assert.assertNotSame(this.h, this.h2);

  }

  /**
   * 'Hypotenuse.getDependentVariable(BigDecimal, BigDecimal)' のためのテスト・メソッド
   */
  @Test
  public void testGetDependentVariable() {
    System.out.println("start testGetDependentVariable()");
    BigDecimal result = this.h.getDependentVariable(BigDecimal.ONE, BigDecimal.ONE);
    double dResult = Math.hypot(1, 1);
    System.out.println("BigDecimal : " + result);
    System.out.println("double : " + dResult);

    Assert.assertEquals(result.doubleValue(), dResult, 10e-100);

    for (double i = -1; i < 1; i += 0.4) {
      for (double j = -1; j < 1; j += 0.4) {
        result = this.h.getDependentVariable(BigDecimal.valueOf(i), BigDecimal.valueOf(j));
        dResult = Math.hypot(i, j);
        System.out.printf("BigDecimal(%f,%f) : %1.5e\n", Double.valueOf(i), Double.valueOf(j),
            result);
        System.out.printf("double(%f,%f) : %1.5e\n", Double.valueOf(i), Double.valueOf(j), Double
            .valueOf(dResult));

        Assert.assertEquals(result.doubleValue(), dResult, 10e-15);
      }
    }

    for (double i = -100; i < 100; i += 100.1) {
      for (double j = -100; j < 100; j += 100.1) {
        result = this.h.getDependentVariable(BigDecimal.valueOf(i), BigDecimal.valueOf(j));
        dResult = Math.hypot(i, j);
        System.out.printf("BigDecimal(%f,%f) : %1.5e\n", Double.valueOf(i), Double.valueOf(j),
            result);
        System.out.printf("double(%f,%f) : %1.5e\n", Double.valueOf(i), Double.valueOf(j), Double
            .valueOf(dResult));

        Assert.assertEquals(result.doubleValue(), dResult, (10e-14) * i * i + (10e-14) * j * j);
      }
    }

  }
}
