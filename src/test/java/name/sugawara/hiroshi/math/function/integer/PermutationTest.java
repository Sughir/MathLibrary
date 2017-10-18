/**
 * Created Date : 2007/02/02 11:46:05
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
 * @version $Id$ Created Date : 2007/02/02 11:46:05
 */
public class PermutationTest {

  /**
   * @since 2010/06/11　3:03:50
   */
  private Permutation p;

  /**
   * @since 2010/06/11　3:03:48
   */
  private Permutation p2;

  /**
   * @throws java.lang.Exception
   * @since 2007/02/02 11:46:05
   */
  @Before
  public void setUp() throws Exception {
    this.p = new Permutation();
    this.p2 = new Permutation();
  }

  /**
   * Test method for {@link Permutation#permutation(java.math.BigInteger, java.math.BigInteger)}.
   */
  @Test
  public final void testPermutation() {
    assertNotNull(this.p);
    assertNotNull(this.p2);
    assertNotSame(this.p, this.p2);
  }

  /**
   * Test method for {@link Permutation#getDependentVariable(BigInteger, BigInteger)}.
   */
  @Test
  public final void testGetDependentVariable() {
    BigInteger result = null;

    assertTrue(this.p.getDependentVariable(BigInteger.valueOf(0), BigInteger.valueOf(0)).compareTo(
        BigInteger.ONE) == 0);
    assertTrue(this.p.getDependentVariable(BigInteger.valueOf(1), BigInteger.valueOf(0)).compareTo(
        BigInteger.ONE) == 0);

    Factorial f = new Factorial();

    for (int i = 1; i < 100; i++) {
      for (int j = 0; j <= i; j++) {
        result = this.p.getDependentVariable(BigInteger.valueOf(i), BigInteger.valueOf(j));
        System.out.printf("%2dP%2d = %8d ", Integer.valueOf(i), Integer.valueOf(j), result);

        assertTrue(result.compareTo(f.getDependentVariable(BigInteger.valueOf(i)).divide(
            f.getDependentVariable(BigInteger.valueOf(i - j)))) == 0);
      }
      System.out.println();
    }

  }

}
