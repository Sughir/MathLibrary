/**
 * Created Date : 2006/12/11 14:51:40
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
 * @version $Id$ Created Date : 2006/12/11 14:51:40
 */
public class FactorialTest {
  /**
   * @since 2010/06/11　3:02:59
   */
  private Factorial f;

  /**
   * @since 2010/06/11　3:02:57
   */
  private Factorial f2;

  /**
   * @throws java.lang.Exception
   * @since 2006/12/11 14:51:40
   */
  @Before
  public void setUp() throws Exception {
    this.f = new Factorial();
    this.f2 = new Factorial();
  }

  /**
   * Test method for {@link Factorial#factorial(java.math.BigInteger)}.
   */
  @Test
  public final void testFactorialBigInteger() {
    assertNotNull(this.f);
    assertNotNull(this.f2);
    assertNotSame(this.f, this.f2);
  }

  /**
   * Test method for {@link Factorial#getDependentVariable(java.math.BigInteger)}.
   */
  @Test
  public final void testGetDependentVariable() {
    BigInteger result = null;
    BigInteger test = BigInteger.ONE;
    assertTrue(this.f.getDependentVariable(BigInteger.valueOf(0)).compareTo(BigInteger.ONE) == 0);
    assertTrue(this.f.getDependentVariable(BigInteger.valueOf(1)).compareTo(BigInteger.ONE) == 0);
    for (int i = 1; i < 2000; i++) {
      result = this.f.getDependentVariable(BigInteger.valueOf(i));
      System.out.printf("%d! = %d\n", Integer.valueOf(i), result);
      test = test.multiply(BigInteger.valueOf(i));
      assertTrue(result.compareTo(test) == 0);
    }

  }
}
