/*
 * 作成日: 2005/01/03 $Id: InverseHyperbolicCosine.java 62 2005-01-17 07:59:22Z
 * sugawara $
 * 
 * 
 */
package name.sugawara.hiroshi.math.function.typedouble;

/**
 * Inverse hyperbolic cosine (逆双曲線余弦) arccosh(x) を求める.
 * <br/>
 * Javaによるアルゴリズム辞典を参考に作成.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: InverseHyperbolicCosine.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 2005/01/03
 */
public final class InverseHyperbolicCosine {
  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/01/03
   */
  private InverseHyperbolicCosine() {
    // コンストラクタ使用禁止.
  }

  /**
   * inverse hyperbolic cosine (逆双曲線余弦) arccosh(x) を求める.
   * 
   * @param argument
   *          値
   * @return acosh(argument)
   * @since 2005/01/03
   */
  public static double acosh(final double argument) {
    return Math.log(argument + Math.sqrt(argument * argument - 1));
  }

}
