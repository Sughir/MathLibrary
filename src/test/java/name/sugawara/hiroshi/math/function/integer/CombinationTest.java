/**
 * Created Date : 2006/12/03 19:24:23
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
 * @version $Id$ Created Date : 2006/12/03 19:24:23
 */
public class CombinationTest {
  /**
   * @since 2010/06/11　3:02:19
   */
  private Combination c;

  /**
   * @since 2010/06/11　3:02:17
   */
  private Combination c2;

  /**
   * @throws java.lang.Exception
   * @since 2006/12/03 19:24:25
   */
  @Before
  public void setUp() throws Exception {
    this.c = new Combination();
    this.c2 = new Combination();
  }

  /**
   * Test method for {@link Combination#Combination()}.
   */
  @Test
  public final void testCombination() {
    assertNotNull(this.c);
    assertNotNull(this.c2);
    assertNotSame(this.c, this.c2);
  }

  /**
   * Test method for {@link Combination#getDependentVariable(BigInteger, BigInteger)}.
   */
  @Test
  public final void testGetDependentVariable() {
    BigInteger result = null;
    Factorial f = new Factorial();
    for (int i = 1; i < 1000; i++) {
      for (int j = 0; j <= i; j++) {
        result = this.c.getDependentVariable(BigInteger.valueOf(i), BigInteger.valueOf(j));
        System.out.printf("%3dC%4d = %20d ", Integer.valueOf(i), Integer.valueOf(j), result);
        assertTrue(result.compareTo(f.getDependentVariable(BigInteger.valueOf(i)).divide(
            f.getDependentVariable(BigInteger.valueOf(j)).multiply(
                f.getDependentVariable(BigInteger.valueOf(i - j))))) == 0);
      }
      System.out.println();
    }

  }

}
