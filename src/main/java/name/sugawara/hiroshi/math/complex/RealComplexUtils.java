/**
 * Created Date : 2007/02/27 22:59:14
 */
package name.sugawara.hiroshi.math.complex;

import org.apache.commons.math.complex.Complex;

/**
 * Jakarta Commons MathのComplexUtilsに不足しているメソッドを集めたユーティリティクラス.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * 
 *          Created Date : 2007/02/27 22:59:14
 * 
 */
public final class RealComplexUtils {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2007/02/28 1:35:07
   */
  private RealComplexUtils() {
    // 使用禁止
  }

  /**
   * 複素数の偏角を求める.
   * 
   * @param c
   *          複素数
   * @return 引数で指定された複素数の偏角をRadian(ラディアン)で返す。
   * @since 1.1
   */
  public static double arg(final Complex c) {
    return Math.atan2(c.getImaginary(), c.getReal());
  }

  /**
   * 複素数の常用対数(底を10とする対数)を求める. <br />
   * log_10(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 引数の常用対数をComplex型(複素数)で返す。
   * @since 1.1
   */
  public static Complex log10(final Complex z) {
    final double logTen = Math.log(10.0);

    if (z.getReal() > 0.0 && z.getImaginary() == 0.0) {
      return new Complex(Math.log(z.getReal()) / logTen, 0.0);
    }
    org.apache.commons.math.complex.Complex c = new org.apache.commons.math.complex.Complex(z
        .getReal(), z.getImaginary());

    final double r = c.log().getReal() / logTen;
    final double i = c.log().getImaginary() / logTen;
    return new Complex(r, i);
  }

  /**
   * 複素数の底を2とする対数)を求める. <br />
   * log_2(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 引数の底を2とする対数をComplex型(複素数)で返す。
   * @since 1.1
   */
  public static Complex log2(final Complex z) {
    final double logTwo = Math.log(2.0);

    if (z.getReal() > 0.0 && z.getImaginary() == 0.0) {
      return new Complex(Math.log(z.getReal()) / logTwo, 0.0);
    }
    org.apache.commons.math.complex.Complex c = new org.apache.commons.math.complex.Complex(z
        .getReal(), z.getImaginary());

    final double r = c.log().getReal() / logTwo;
    final double i = c.log().getImaginary() / logTwo;
    return new Complex(r, i);
  }
}
