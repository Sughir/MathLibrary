/**
 * Created Date : 2006/11/29 18:57:53
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/11/29 18:57:53
 */
public class SineTest {
  /**
   * @since 2010/06/11　2:50:33
   */
  private Sine sin;

  /**
   * @since 2010/06/11　2:50:35
   */
  private Sine sin2;

  /**
   * @throws java.lang.Exception
   * @since 2006/11/29 18:57:53
   */
  @Before
  public void setUp() throws Exception {
    this.sin = new Sine(DecimalFunctionTest.PRECISION);
    this.sin2 = new Sine(DecimalFunctionTest.PRECISION);
  }

  /**
   * Test method for {@link Sine#Sine(Precision)}.
   */
  @Test
  public final void testSine() {
    assertNotNull(this.sin);
    assertNotNull(this.sin2);
    assertNotSame(this.sin, this.sin2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Sine#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal result = null;
    double dResult = 0;
    final BigDecimal incriment = new BigDecimal("0.001");
    for (BigDecimal i = new BigDecimal("-100"); i.compareTo(new BigDecimal("100")) < 0; i = i
        .add(incriment)) {
      result = this.sin.getDependentVariable(i);
      System.out.printf("sin(%f) = %2.15e ", i, result);
      dResult = Math.sin(i.doubleValue());
      System.out.printf("double sin(%f) = %2.15e\n", i, Double.valueOf(dResult));

      assertEquals(result.doubleValue(), dResult, Math.abs(Math.sin(i.doubleValue()))
          * Math.pow(10, 16));
    }
  }

}
