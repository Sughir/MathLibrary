/*
 * 作成日: 2005/01/03 $Id: HyperbolicTangent.java 109 2010-06-13 04:26:48Z sugawara $
 * 
 * 
 */
package name.sugawara.hiroshi.math.function.typedouble;

/**
 * Hyperbolic tangent (双曲線正接) tanh(x) を求める. <br/> Javaによるアルゴリズム辞典を参考に作成。
 * 
 * @author Hiroshi Sugawara
 * @version $Id: HyperbolicTangent.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2005/01/03
 */
@Deprecated
public final class HyperbolicTangent {

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
  private HyperbolicTangent() {
    // コンストラクタ使用禁止
  }

  /**
   * Hyperbolic tangent (双曲線正接) tanh(x) を求める.
   * 
   * @param argument
   *          値
   * @return tanh(argument)
   * @since 2005/01/03
   */
  public static double tanh(final double argument) {
    double result = 0;
    if (argument > HyperbolicTangent.EPS5) {
      result = 2 / (1 + Math.exp(-2 * argument)) - 1;
    }
    if (argument < -HyperbolicTangent.EPS5) {
      result = 1 - 2 / (Math.exp(2 * argument) + 1);
    } else {
      result = argument * (1 - argument * argument / 3);
    }
    return result;
  }
}
