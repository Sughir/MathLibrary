/**
 * Created Date : 2006/07/17 16:07:59
 */
package name.sugawara.hiroshi.math.constant;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import name.sugawara.hiroshi.math.precision.NumberOfTerm;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * Expのテスト.
 *
 * @author Hiroshi Sugawara
 * @version $Id$
 *
 * Created Date : 2006/07/17 16:07:59
 *
 */
public class ExpTest {


  /**
   * 'name.sugawara.hiroshi.math.constant.Exp.e(Precision)' のためのテスト・メソッド
   */
  @Test
  public final void testE() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n      Math.E=" + Math.E);
    sb.append("\nMath.E=" + Math.E);

    int n = 500;
    int scale = 1000;
    RoundingMode rm = RoundingMode.HALF_EVEN;
    MathContext mc = new MathContext(scale,rm);
    Precision p;
    // = new NumberOfTerm(new BigInteger(Integer.toString(n)), scale,mode);

    for (int i = 0; i <= n; i += 20) {
      p = new NumberOfTerm(new BigInteger(Integer.toString(i)), mc);
      sb.append("\nExp.e(" + i + ")=" + Exp.exp(p));
    }

    p = new NumberOfTerm(new BigInteger(Integer.toString(100)), mc);

    assertEquals(Exp.exp(p).doubleValue(), Math.E, 10e-100);

    System.out.println(sb.toString());

  }

}
