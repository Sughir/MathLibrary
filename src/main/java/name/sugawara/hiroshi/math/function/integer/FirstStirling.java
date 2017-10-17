/*
 * Created on 2003/07/08
 * 
 */
package name.sugawara.hiroshi.math.function.integer;

import static java.math.BigInteger.ONE;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;

/**
 * 第一種スターリング数.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: FirstStirling.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 */

public final strictfp class FirstStirling implements
    FunctionOfTwoVariable<BigInteger, BigInteger, BigInteger> {

  /**
   * 第一種スターリング数 異なるn個のものをr個の空でない部分順列集合に分割する仕方の数.
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return BigInteger型整数
   * @since 1.1
   */
  public static BigInteger firstKind(final BigInteger n, final BigInteger k) {

    BigInteger result;
    if (k.compareTo(ONE) < 0 || k.compareTo(n) > 0) {
      result = BigInteger.ZERO;
    } else if (k.compareTo(n) == 0) {
      result = ONE;
    } else {
      result = (n.subtract(ONE)).multiply(FirstStirling.firstKind(n.subtract(ONE), k)).add(
          FirstStirling.firstKind(n.subtract(ONE), k.subtract(ONE)));
    }
    return result;
  }

  /**
   * 第一種スターリング数.
   * 
   * 異なるn個のものをr個の空でない部分集合に分割する仕方の数。
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return BigInteger型整数
   * @see FunctionOfTwoVariable#getDependentVariable(java.lang.Number,
   *      java.lang.Number)
   * @since 2007/02/02 19:55:15
   */
  public BigInteger getDependentVariable(final BigInteger n, final BigInteger k) {
    return FirstStirling.firstKind(n, k);
  }
}
