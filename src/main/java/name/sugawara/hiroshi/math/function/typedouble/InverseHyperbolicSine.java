/*
 * 作成日: 2005/01/03 $Id: InverseHyperbolicSine.java 62 2005-01-17 07:59:22Z
 * sugawara $
 * 
 * 
 */
package name.sugawara.hiroshi.math.function.typedouble;

/**
 * Inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める. Javaによるアルゴリズム辞典を参考に作成。
 * 
 * @author Hiroshi Sugawara
 * @version $Id: InverseHyperbolicSine.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2005/01/03
 */
public final class InverseHyperbolicSine {

  /**
   * 
   * 機械エプシロンの1/5乗程度.
   * 
   * {@code EPS5} のコメント
   * 
   * @since 2005/01/03
   */
  private static final double EPS5 = 0.001;

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/01/03
   */
  private InverseHyperbolicSine() {
    // コンストラクタ使用禁止。
  }

  /**
   * Inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める.
   * 
   * @param argument
   *          値
   * @return asinh(argument)
   * @since 2005/01/03
   */
  public static double asinh(final double argument) {
    double result = 0;
    if (argument > InverseHyperbolicSine.EPS5) {
      result = Math.log(Math.sqrt(argument * argument + 1) + argument);
    }
    if (argument < -InverseHyperbolicSine.EPS5) {
      result = -Math.log(Math.sqrt(argument * argument + 1) - argument);
    } else {
      return argument * (1 - argument * argument / 6);
    }
    return result;
  }

}
