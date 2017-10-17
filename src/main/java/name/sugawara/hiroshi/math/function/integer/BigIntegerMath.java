package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

/**
 * 整数関数 各種整数関数を任意精度で求める.
 * 
 * Facadeパターンを適用。
 * 
 * @author Hiroshi Sugawara
 * @version $Id: BigIntegerMath.java 94 2006-11-05 18:59:55Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class BigIntegerMath {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:17:28
   */
  private BigIntegerMath() {
    // Empty block
  }

  /**
   * 第n項の Finonacci 数列を BigInteger 型で求める.
   * 
   * @param n
   *          第n項 (0より大きな自然数)
   * @return 第n項のFibonacci数列を返す
   * @since 1.1
   */
  public static BigInteger fibonacci(final BigInteger n) {
    return Fibonacci.fibonacci(n);
  }

  /**
   * BigInteger 型の値の階乗を BigInteger 型で返す.
   * 
   * @param number
   *          乗算する回数 ( BigInteger 型の正の整数)
   * @return number! を返す
   */
  public static BigInteger factorial(final BigInteger number) {
    return Factorial.factorial(number);
  }

  /**
   * BigInteger 型の値の階乗関数 a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 ) を
   * BigInteger 型で返す(自然数のみ).
   * 
   * @param a
   *          乗算する回数 ( BigInteger 型の正の整数)
   * @param x
   *          乗算する回数 ( BigInteger 型の正の整数)
   * @return 階乗関数 a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す
   */
  public static BigInteger factorial(final BigInteger a, final BigInteger x) {
    return FactorialFunction.factorial(a, x);
  }

  /**
   * n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で求める.
   * 
   * 組み合わせ数の公式を使用し高速化。
   * 
   * @param n
   *          値
   * @param k
   *          値
   * @return n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で返す
   */
  public static BigInteger getCombination(final BigInteger n, final BigInteger k) {
    return Combination.getNumberOfCombinationsWithFast(n, k);
  }

  /**
   * 戻り値が整数のみとなる指数関数.
   * 
   * 引数の指数部には自然数しか入れることができない.
   * 
   * @param base
   *          基数部
   * @param exponent
   *          自然数の指数部
   * @return BigInteger型の値
   * @since 2006/07/19 17:07:33
   */
  public static BigInteger power(final BigInteger base,
      final BigInteger exponent) {
    return Power.pow(base, exponent);

  }
}
