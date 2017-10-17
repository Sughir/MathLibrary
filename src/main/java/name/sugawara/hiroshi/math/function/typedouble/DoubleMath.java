package name.sugawara.hiroshi.math.function.typedouble;

/**
 * double 型初等関数.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: DoubleMath.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class DoubleMath {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/11/24 14:51:27
   */
  private DoubleMath() {
    // コンストラクタ使用禁止.
  }

  /**
   * C. B. Moler and D. Morrison による方法でhypotenuseの長さを求める.
   * 
   * @param x
   *          x
   * @param y
   *          y
   * @return sqrt(x^2 + y^2)
   */
  @Deprecated
  public static strictfp double hypot2(final double x, final double y) {
    return Math.hypot(x, y);
  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める.
   * 
   * @param x
   *          値
   * @return sinh(x)
   * @since 2005/01/03
   */
  @Deprecated
  public static strictfp double sinh(final double x) {
    return Math.sinh(x);
  }

  /**
   * hyperbolic cosine (双曲線余弦) cosh(x) を求める.
   * 
   * @param x
   *          値
   * @return cosh(x)
   * @since 2005/01/03
   */
  @Deprecated
  public static strictfp double cosh(final double x) {
    return Math.cosh(x);
  }

  /**
   * hyperbolic sine (双曲線正接) tanh(x) を求める.
   * 
   * @param x
   *          値
   * @return tanh(x)
   * @since 2005/01/03
   */
  @Deprecated
  public static strictfp double tanh(final double x) {
    return Math.tanh(x);
  }

  /**
   * Inverse hyperbolic sine (逆双曲線正弦) asinh(x) を求める.
   * 
   * @param x
   *          値
   * @return asinh(x)
   * @since 2005/01/03
   */
  public static strictfp double asinh(final double x) {
    return InverseHyperbolicSine.asinh(x);
  }

  /**
   * Inverse hyperbolic cosine (逆双曲線余弦) acosh(x) を求める.
   * 
   * @param x
   *          値
   * @return acosh(x)
   * @since 2005/01/03
   */
  public static strictfp double acosh(final double x) {
    return InverseHyperbolicCosine.acosh(x);
  }

  /**
   * Inverse hyperbolic sine (逆双曲線正接) atanh(x) を求める.
   * 
   * @param x
   *          値
   * @return atanh(x)
   * @since 2005/01/03
   */
  public static strictfp double atanh(final double x) {
    return InverseHyperbolicTangent.atanh(x);
  }

  /**
   * log2(x) を求める.
   * 
   * @param x
   *          値
   * @return log2(x)
   * @since 2005/01/03
   */
  public static strictfp double log2(final double x) {
    return StrictMath.log(x) / StrictMath.log(2);
  }

}
