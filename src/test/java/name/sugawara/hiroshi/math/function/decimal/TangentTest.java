/**
 * Created Date : 2006/12/01 13:57:31
 */
package name.sugawara.hiroshi.math.function.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/12/01 13:57:31
 */
public class TangentTest {

  /**
   * @since 2010/06/11　3:01:51
   */
  private Tangent tan;

  /**
   * @since 2010/06/11　3:01:48
   */
  private Tangent tan2;

  /**
   * @throws java.lang.Exception
   * @since 2006/12/01 13:57:31
   */
  @Before
  public void setUp() throws Exception {
    this.tan = new Tangent(DecimalFunctionTest.PRECISION);
    this.tan2 = new Tangent(DecimalFunctionTest.PRECISION);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Tangent#Tangent(name.sugawara.hiroshi.math.precision.Precision)}
   * .
   */
  @Test
  public final void testTangent() {
    assertNotNull(this.tan);
    assertNotNull(this.tan2);
    assertNotSame(this.tan, this.tan2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.decimal.Tangent#getDependentVariable(java.math.BigDecimal)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {
    BigDecimal result = null;
    double dResult = 0;
    final BigDecimal incriment = new BigDecimal("0.001");
    for (BigDecimal i = new BigDecimal("-100"); i.compareTo(new BigDecimal("100")) < 0; i = i
        .add(incriment)) {
      result = this.tan.getDependentVariable(i);
      System.out.printf("tan(%f) = %2.15e ", i, result);
      dResult = Math.sin(i.doubleValue());
      System.out.printf("double tan(%f) = %2.15e\n", i, Double.valueOf(dResult));

      assertEquals(result.doubleValue(), dResult, Math.abs(Math.tan(i.doubleValue()))
          * Math.pow(10, 16));
    }

  }

}
