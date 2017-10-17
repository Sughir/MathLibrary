package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;

/**
 * 組み合わせ数 (number of combinations) n個のものからk個のものを選ぶ組み合わせ数 nCk を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Combination.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class Combination implements FunctionOfTwoVariable<BigInteger, BigInteger, BigInteger> {

  /**
   * n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で求める.
   * 
   * このアルゴリズムでは、100個のものから4個のものを選ぶ組み合わせ数は3921225通りであり、
   * 再帰的に呼び出すメソッドの呼び出し回数も3921225回となり遅い。
   * 
   * @param n
   *          値
   * @param k
   *          値
   * @return n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で返す
   * @deprecated かわりにgetNumberOfCombinationsWithFast()を使用する。
   */
  @Deprecated
  public static BigInteger getNumberOfCombinations(final BigInteger n, final BigInteger k) {
    if (n.compareTo(BigInteger.ZERO) < 0 || k.compareTo(BigInteger.ZERO) < 0) {
      throw new ArithmeticException("The value of an argument must be a positive integer.\n"
          + "The value of the first argument must not exceed the value"
          + " of the second argument.");

    } else if (k.compareTo(BigInteger.ZERO) == 0 || k.compareTo(n) == 0) {
      return BigInteger.ONE;
    }

    return Combination.getNumberOfCombinations(n.subtract(BigInteger.ONE),
        k.subtract(BigInteger.ONE)).add(
        Combination.getNumberOfCombinations(n.subtract(BigInteger.ONE), k));
  }

  /**
   * n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で求める.
   * 
   * 組み合わせ数の公式を使用し高速化. getNumberOfCombinations() よりも高速
   * 
   * @param n
   *          値
   * @param k
   *          値
   * @return n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で返す
   */
  public static BigInteger getNumberOfCombinationsWithFast(final BigInteger n, final BigInteger k) {
    BigInteger mutableK = k;
    BigInteger result = null;
    if (n.compareTo(BigInteger.ZERO) < 0 || k.compareTo(BigInteger.ZERO) < 0) {
      throw new ArithmeticException("The value of an argument must be a positive integer.\n"
          + "The value of the first argument must not exceed the "
          + "value of the second argument.");
    } else if (n.subtract(mutableK).compareTo(mutableK) < 0) {
      mutableK = n.subtract(mutableK);
    }

    if (k.compareTo(BigInteger.ZERO) == 0) {
      result = BigInteger.ONE;
    } else if (k.compareTo(BigInteger.ONE) == 0) {
      result = n;
    } else {
      final BigInteger numerator = BigIntegerMath.factorial(n);
      final BigInteger denominator = BigIntegerMath.factorial(mutableK).multiply(
          BigIntegerMath.factorial(n.subtract(mutableK)));

      if (numerator.remainder(denominator).compareTo(BigInteger.ZERO) != 0) {
        throw new ArithmeticException("The result has not been a integer.");
      }
      result = numerator.divide(denominator);
    }
    return result;

  }

  /**
   * nCk.
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return nCk
   * @see FunctionOfTwoVariable#getDependentVariable(Number,Number)
   * @since 2006/12/05 11:19:21
   */
  public BigInteger getDependentVariable(final BigInteger n, final BigInteger k) {
    return Combination.getNumberOfCombinationsWithFast(n, k);
  }
}
