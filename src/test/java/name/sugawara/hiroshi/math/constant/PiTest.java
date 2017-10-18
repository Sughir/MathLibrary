/**
 * Created Date : 2006/07/17 22:00:32
 */
package name.sugawara.hiroshi.math.constant;

import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import junit.framework.Assert;
import name.sugawara.hiroshi.math.precision.NumberOfTerm;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.Test;

/**
 * Piのテスト.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/17　22:00:32
 */
public class PiTest {

  /**
   * 'name.sugawara.hiroshi.math.constant.Pi.pi(Precision)' のためのテスト・メソッド
   */
  @Test
  public final void testPi() {

    BigInteger N = new BigInteger("100");
    int scale = 1000;
    RoundingMode rm = RoundingMode.HALF_EVEN;
    MathContext mc = new MathContext(scale, rm);
    Precision p = new NumberOfTerm(N, mc);

    StringBuilder sb = new StringBuilder();
    sb.append("Pi = " + Pi.pi(p) + "\n");
    sb.append("For Comparing To Math.PI = " + Math.PI);
    System.out.println(new String(sb));

    Assert.assertEquals(Pi.pi(p).doubleValue(), Math.PI, 10e-14);
  }
}
