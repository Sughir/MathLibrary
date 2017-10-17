/*
 * 作成日: 2005/01/03 $Id: InverseHyperbolicTangent.java 62 2005-01-17 07:59:22Z
 * sugawara $
 * 
 * 
 */
package name.sugawara.hiroshi.math.function.typedouble;

/**
 * Inverse hyperbolic tangent (逆双曲線正弦) arctanh(x) を求める. Javaによるアルゴリズム辞典を参考に作成.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: InverseHyperbolicTangent.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2005/01/03
 */
public final class InverseHyperbolicTangent {

  /**
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
  private InverseHyperbolicTangent() {
    // コンストラクタ使用禁止
  }

  /**
   * Inverse hyperbolic tangent (逆双曲線正弦) arctanh(x) を求める. <br />
   * 
   * @param argument
   *          値
   * @return atanh(argument)
   * @since 2005/01/03
   */
  public static double atanh(final double argument) {
    final double half = 0.5;
    if (Math.abs(argument) > InverseHyperbolicTangent.EPS5) {
      return half * Math.log((1 + argument) / (1 - argument));
    }
    return argument * (1 + argument * argument / 3);
  }

}
