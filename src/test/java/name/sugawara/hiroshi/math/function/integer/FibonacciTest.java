/**
 * Created Date : 2007/02/01 19:33:18
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
 * @version $Id$ Created Date : 2007/02/01 19:33:18
 */
public class FibonacciTest {
  /**
   * @since 2010/06/11　3:03:15
   */
  private Fibonacci f;

  /**
   * @since 2010/06/11　3:03:13
   */
  private Fibonacci f2;

  /**
   * @throws java.lang.Exception
   * @since 2007/02/01 19:33:18
   */
  @Before
  public void setUp() throws Exception {
    this.f = new Fibonacci();
    this.f2 = new Fibonacci();
  }

  /**
   * Test method for {@link Fibonacci#fibonacci(BigInteger)}.
   */
  @Test
  public final void testFibonacciBigInteger() {
    assertNotNull(this.f);
    assertNotNull(this.f2);
    assertNotSame(this.f, this.f2);
  }

  /**
   * Test method for {@link Fibonacci#getDependentVariable(BigInteger)}.
   */
  @Test
  public final void testGetDependentVariable() {
    BigInteger result = BigInteger.ZERO;
    BigInteger a = BigInteger.ZERO, b = BigInteger.ONE, c = BigInteger.ONE;
    for (int i = 1; i < 10000; i++) {
      result = this.f.getDependentVariable(BigInteger.valueOf(i));
      System.out.printf("f(%3d) = %100d\n", Integer.valueOf(i), result);
      // System.out.printf("a=%d,b=%d,c=%d result=%d\n", a, b, c,result);

      assertTrue(b.compareTo(result) == 0);
      a = b;
      b = c;
      c = a.add(b);
    }
  }
}
