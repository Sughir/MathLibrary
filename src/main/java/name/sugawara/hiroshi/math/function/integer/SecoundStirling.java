/**
 * Created Date : 2007/02/02 20:29:05
 */
package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;

import static java.math.BigInteger.ONE;

/**
 * 第二種スターリング数.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * 
 * Created Date : 2007/02/02 20:29:05
 * 
 */
public final class SecoundStirling implements
    FunctionOfTwoVariable<BigInteger, BigInteger, BigInteger> {

  /**
   * 第二種スターリング数.
   * 
   * 異なるn個のものをr個の空でない部分集合に分割する仕方の数。
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return BigInteger型整数
   * @since 1.1
   */
  public static BigInteger secondKind(final BigInteger n, final BigInteger k) {

    BigInteger result;
    if (k.compareTo(ONE) < 0 || k.compareTo(n) > 0) {
      result = BigInteger.ZERO;
    } else if (k.compareTo(ONE) == 0 || k.compareTo(n) == 0) {
      result = ONE;
    } else {
      result = k.multiply(SecoundStirling.secondKind(n.subtract(ONE), k)).add(
          SecoundStirling.secondKind(n.subtract(ONE), k.subtract(ONE)));
    }
    return result;

  }

  /**
   * 第二種スターリング数.
   * 
   * 異なるn個のものをr個の空でない部分集合に分割する仕方の数。
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return BigInteger型整数
   * @see FunctionOfTwoVariable#getDependentVariable(Number,Number)
   * @since 2007/02/02 20:29:05
   */
  public BigInteger getDependentVariable(final BigInteger n, final BigInteger k) {
    return SecoundStirling.secondKind(n, k);
  }

}
