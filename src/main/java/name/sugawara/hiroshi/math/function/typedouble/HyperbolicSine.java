/*
 * 作成日: 2005/01/03
 * 
 * 
 */
package name.sugawara.hiroshi.math.function.typedouble;

/**
 * hyperbolic sine (双曲線正弦) sinh(x) を求める. Javaによるアルゴリズム辞典を参考に作成。
 * 
 * @author Hiroshi Sugawara
 * @version $Id: HyperbolicSine.java 109 2010-06-13 04:26:48Z sugawara $
 */
@Deprecated
public final class HyperbolicSine {

  /**
   * 機械エプシロンの1/5乗程度. {@code EPS5} のコメント
   * 
   * @since 2005/01/03
   */
  private static final double EPS5 = 0.001;

  /**
   * コンストラクタ使用禁止.
   */
  private HyperbolicSine() {
    // コンストラクタ使用禁止.
  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める.
   * 
   * @param argument
   *          値
   * @return sinh(argument)
   */
  public static double sinh(final double argument) {

    if (Math.abs(argument) > HyperbolicSine.EPS5) {
      final double t = Math.exp(argument);
      return (t - 1 / t) / 2;
    }

    return argument * (1 + argument * argument / 6);

  }
}
