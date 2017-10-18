/**
 * Created Date : 2007/02/01 15:45:19
 */
package name.sugawara.hiroshi.math.function.integer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2007/02/01 15:45:19
 */
public class FactorialFunctionTest {

  /**
   * @since 2010/06/11　3:02:39
   */
  private FactorialFunction f;

  /**
   * @since 2010/06/11　3:02:37
   */
  private FactorialFunction f2;

  /**
   * @throws java.lang.Exception
   * @since 2007/02/01 15:45:19
   */
  @Before
  public void setUp() throws Exception {
    this.f = new FactorialFunction();
    this.f2 = new FactorialFunction();
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.integer.FactorialFunction#factorial(java.math.BigInteger, java.math.BigInteger)}
   * .
   */
  @Test
  public final void testFactorial() {
    assertNotNull(this.f);
    assertNotNull(this.f2);
    assertNotSame(this.f, this.f2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.integer.FactorialFunction#getDependentVariable(java.math.BigInteger, java.math.BigInteger)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {

    BigInteger result = null;
    BigInteger test = BigInteger.ONE;
    assertTrue(this.f.getDependentVariable(BigInteger.valueOf(0), BigInteger.valueOf(0)).compareTo(
        BigInteger.ONE) == 0);
    assertTrue(this.f.getDependentVariable(BigInteger.valueOf(1), BigInteger.valueOf(0)).compareTo(
        BigInteger.ONE) == 0);
    for (int i = 0; i < 10; i++) {
      test = BigInteger.valueOf(i);
      for (int j = 0; j < 10; j++) {
        result = this.f.getDependentVariable(BigInteger.valueOf(i), BigInteger.valueOf(j));
        System.out.printf("%2d^(%2d) = %8d ", Integer.valueOf(i), Integer.valueOf(j), result);

        if (test.intValue() < j) {
          assertTrue(result.compareTo(BigInteger.ZERO) == 0);
        }
      }
      System.out.println();
    }
  }

}
