/*
 * 作成日: 2005/01/03 $Id: HyperbolicCosine.java 102 2007-04-10 06:27:03Z sugawara $
 * 
 * 
 */
package name.sugawara.hiroshi.math.function.typedouble;

/**
 * Hyperbolic cosine (双曲線余弦) cosh(x) を求める。<br/> Javaによるアルゴリズム辞典を参考に作成.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: HyperbolicCosine.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 2005/01/03
 */
@Deprecated
public final class HyperbolicCosine {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/01/03
   */
  private HyperbolicCosine() {
    // コンストラクタ使用禁止
  }

  /**
   * hyperbolic cosine (双曲線余弦) cosh(x) を求める.
   * 
   * @param argument
   *          値
   * @return cosh(argument)
   * @since 2005/01/03
   */
  public static double cosh(final double argument) {
    final double t = Math.exp(argument);
    return (t + 1 / t) / 2;

  }
}
