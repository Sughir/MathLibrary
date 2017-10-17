/*
 * Created on 2003/06/11
 * 
 */
package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * int, long型関連ユーティリティ.
 * 
 * @author Hiroshi Sugawara
 * @since 1.1
 * @version $Id: IntegerUtility.java 102 2007-04-10 06:27:03Z sugawara $
 * 
 */
public final class IntegerUtility {

  /**
   * 使用禁止.
   * 
   * @since 2007/02/22 13:52:34
   */
  private IntegerUtility() {
    // 使用禁止.
  }

  /**
   * 奇数であれば真を返す.
   * 
   * @param x
   *          整数
   * @return xが奇数ならば真
   * @since 1.1
   */
  public static boolean odd(final long x) {
    return (x & 1) != 0;
  }

  /**
   * * 組み合わせ数 (number of combinations).
   * 
   * n個の ものからk個のものを選ぶ組み合わせ数 nCk を求める.
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return nCk
   * @since 2005/11/24 18:31:55
   */
  public static long combination(final long n, final long k) {
    return Combination.getNumberOfCombinationsWithFast(n, k);
  }

  /**
   * 順列(Permutation)の数 nPr. n個のものからr個を取り出す順列の個数を求める.
   * 
   * @param n
   *          整数
   * @param r
   *          整数
   * @return 整数
   * @since 2005/11/24 18:34:49
   */
  public static long permutation(final long n, final long r) {
    return Permutation.permutation(n, r);
  }

  /**
   * 階乗(x!)を求める.
   * 
   * @param x
   *          整数
   * @return 整数
   * @since 2005/11/24 18:19:54
   */
  public static long factorial(final long x) {
    return Factorial.factorial(x);
  }

  /**
   * 階乗関数(自然数のみ) a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す.
   * 
   * @param a
   *          整数
   * @param x
   *          整数
   * @return 整数
   * @since 2005/11/24 18:19:54
   */
  public static long factorial(final long a, final long x) {
    return FactorialFunction.factorial(a, x);
  }

  /**
   * Fibonacci数列をlong型で求める.
   * 
   * <pre>
   *        第93項からオーバーフローの影響により値が負となり不正確になる.
   * </pre>
   * 
   * @param n
   *          整数
   * @return 整数
   * @since 2005/11/24 18:36:17
   */
  public static long fibonacci(final long n) {
    return Fibonacci.fibonacci(n);

  }

  /**
   * 第一種スターリング数. 異なるn個のものをr個の空でない部分順列集合に分割する仕方の数.
   * 
   * @param n
   *          整数
   * @param k
   *          整数
   * @return 整数
   * @since 2005/11/24 18:54:16
   */
  public static long stirlingFirstKind(final long n, final long k) {
    return Stirling.firstKind(n, k);

  }

  /**
   * 第二種スターリング数.<br />
   * 異なるn個のものをr個の空でない部分集合に分割する仕方の数.
   * 
   * @param n
   *          整数
   * @param k
   *          整数
   * @return 整数
   * @since 2005/11/24 18:54:16
   */
  public static long stirlingSecondKind(final long n, final long k) {
    return Stirling.secondKind(n, k);

  }
}
