/**
 * Created Date : 2007/02/02 17:03:33
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
 * @version $Id$ Created Date : 2007/02/02 17:03:33
 */
public class PowerTest {
  /**
   * @since 2010/06/11　3:04:06
   */
  private Power p;

  /**
   * @since 2010/06/11　3:04:04
   */
  private Power p2;

  /**
   * @throws java.lang.Exception
   * @since 2007/02/02 17:03:33
   */
  @Before
  public void setUp() throws Exception {
    this.p = new Power();
    this.p2 = new Power();
  }

  /**
   * Test method for {@link Power#pow(java.math.BigInteger, java.math.BigInteger)}.
   */
  @Test
  public final void testPow() {
    assertNotNull(this.p);
    assertNotNull(this.p2);
    assertNotSame(this.p, this.p2);
  }

  /**
   * Test method for
   * {@link name.sugawara.hiroshi.math.function.integer.Power#getDependentVariable(java.math.BigInteger, java.math.BigInteger)}
   * .
   */
  @Test
  public final void testGetDependentVariable() {

    BigInteger result = null;
    for (int i = -80; i < 80; i++) {
      for (int j = 0; j < 9; j++) {
        result = this.p.getDependentVariable(BigInteger.valueOf(i), BigInteger.valueOf(j));
        System.out.printf("%3d^(%3d) = %20d ", Integer.valueOf(i), Integer.valueOf(j), result);
        long mathR = (long) Math.pow(i, j);
        System.out.printf("math = %d ", Long.valueOf(mathR));
        assertTrue(result.compareTo(BigInteger.valueOf(mathR)) == 0);
      }
      System.out.println();
    }
  }

}
