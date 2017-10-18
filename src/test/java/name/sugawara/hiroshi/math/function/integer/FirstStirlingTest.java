/**
 * Created Date : 2007/02/02 18:05:20
 */
package name.sugawara.hiroshi.math.function.integer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2007/02/02　18:05:20
 */
public class FirstStirlingTest {
  /**
   * @since 2010/06/11　3:03:32
   */
  private FirstStirling s1;

  /**
   * @since 2010/06/11　3:03:33
   */
  private FirstStirling s2;

  /**
   * @throws java.lang.Exception
   * @since 2007/02/02　18:05:20
   */
  @Before
  public void setUp() throws Exception {
    this.s1 = new FirstStirling();
    this.s2 = new FirstStirling();
  }

  /**
   * Test method for {@link FirstStirling#firstKind(java.math.BigInteger, java.math.BigInteger)}.
   */
  @Test
  public final void testFirstKind() {
    assertNotNull(this.s1);
    assertNotNull(this.s2);
    assertNotSame(this.s1, this.s2);
  }

  /**
   * @since 2007/02/02　20:36:07
   */
  @Test
  public final void testGetDependentVariable() {

    BigInteger result = null;
    for (int i = -10; i < 10; i++) {
      for (int j = -10; j < 10; j++) {
        result = this.s1.getDependentVariable(BigInteger.valueOf(i), BigInteger.valueOf(j));
        System.out.printf("{%3d,　%3d} = %20d ", Integer.valueOf(i), Integer.valueOf(j), result);
        // long mathR = (long) Math.pow(i, j);
        // System.out.printf("math = %d ", Long.valueOf(mathR));
        // assertTrue(result.compareTo(BigInteger.valueOf(mathR)) == 0);
      }
      System.out.println();
    }
  }

}
