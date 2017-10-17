/*
 * Created on 2003/07/08
 * 
 */
package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * 順列(Permutation)の数 nPr.
 * 
 * @author sugawara
 * @version $Id: Permutation.java 83 2005-09-12 16:18:56Z sugawara $
 * @since 1.1 To change the template for this generated type comment go to
 *        Window>Preferences>Java>Code Generation>Code and Comments
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class Permutation {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:22:47
   */
  private Permutation() {
    // Empty block
  }

  /**
   * n個のものからr個を取り出す順列の個数を求める.
   * 
   * @param n
   *          n
   * @param r
   *          r
   * @return nPr
   * @since 1.1
   */
  public static long permutation(final long n, final long r) {
    if (n < 0 || r < 0) {
      throw new ArithmeticException("The value n and the value r must be the natural numbers.");
    } else if (n == r) {
      return IntegerUtility.factorial(n);
    } else {
      return IntegerUtility.factorial(n) / IntegerUtility.factorial(n - r);
    }
  }
}
