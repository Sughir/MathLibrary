/**
 * Created Date : 2007/02/27 13:48:05
 */
package name.sugawara.hiroshi.math.complex;

import org.apache.commons.math.complex.Complex;

/**
 * Jakarta Commons MathのComplexクラスを継承した複素数クラス.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * 
 * Created Date : 2007/02/27 13:48:05
 * 
 */
public class RealComplex extends Complex {

  /**
   * シリアルバージョンID.
   * 
   * @since 2007/02/27 21:49:09
   */
  private static final long serialVersionUID = 439240730339351191L;

  /**
   * 実数が0である純虚数である複素数を生成する.
   * 
   * @param y
   *          虚数部
   * @since 2007/02/27 13:48:05
   */
  public RealComplex(final double y) {
    super(0.0, y);
  }

  /**
   * 実数部、虚数部を指定して複素数を生成する.
   * 
   * @param x
   *          実数部
   * @param y
   *          虚数部
   * @since 2007/02/27 13:48:05
   */
  public RealComplex(final double x, final double y) {
    super(x, y);
  }

  /**
   * 複素数をコピーするコピーコンストラクタ.
   * 
   * @param c
   *          複素数
   * @since 2007/02/27 21:46:39
   */
  public RealComplex(final Complex c) {
    super(c.getReal(), c.getImaginary());
  }

  /**
   * 複素数オブジェクトを生成するFactory Method.
   * 
   * @param x
   *          実数部
   * @param y
   *          虚数部
   * @return 複素数オブジェクト
   * @since 2007/02/23 17:06:58
   */
  public static Complex valueOf(final double x, final double y) {
    return new RealComplex(x, y);
  }
}
