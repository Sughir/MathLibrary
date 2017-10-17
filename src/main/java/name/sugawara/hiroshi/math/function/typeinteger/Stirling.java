/*
 * Created on 2003/07/08
 * 
 */
package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * 第一種、第二種スターリング数.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: FirstStirling.java 83 2005-09-12 16:18:56Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final strictfp class Stirling {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:23:28
   */
  private Stirling() {
    // Empty block
  }

  /**
   * 第一種スターリング数. 異なるn個のものをr個の空でない部分順列集合に分割する仕方の数.
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return 整数
   * @since 1.1
   */
  public static long firstKind(final long n, final long k) {
    long result;
    if (k < 1 || k > n) {
      result = 0;
    } else

    if (k == n) {
      result = 1;
    } else {

      result = (n - 1) * (Stirling.firstKind(n - 1, k)) + Stirling.firstKind(n - 1, k - 1);
    }
    return result;
  }

  /**
   * 第二種スターリング数.<br />
   * 異なるn個のものをr個の空でない部分集合に分割する仕方の数.
   * 
   * @param n
   *          n
   * @param k
   *          k
   * @return 整数
   * @since 1.1
   */
  public static long secondKind(final long n, final long k) {
    long result;
    if (k < 1 || k > n) {
      result = 0;
    } else

    if (k == 1 || k == n) {
      result = 1;
    } else {

      result = k * Stirling.secondKind(n - 1, k) + Stirling.secondKind(n - 1, k - 1);
    }
    return result;
  }
}
