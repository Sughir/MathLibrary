package name.sugawara.hiroshi.math.constant;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 定数.
 * @author   Hiroshi Sugawara
 * @version   $Id: Constant.java 109 2010-06-13 04:26:48Z sugawara $
 * 
 * Created Date : 2005/07/24　19:40:06
 */
public final strictfp class Constant {

  /**
   * 誤差. {@code precision}
   * 
   * @since 2004/08/14
   */
  private Precision precision;

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2004/08/03
   */
  @SuppressWarnings("unused")
  private Constant() {
    // Empty block
  }

  /**
   * 誤差を指定する.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/14
   */
  public Constant(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 円周率を返す.
   * 
   * @param p
   *          誤差
   * @return BigDecimal型の値
   * @since 1.1
   * @deprecated pi()を推奨.
   */
  @Deprecated
  public static BigDecimal pi(final Precision p) {
    return Pi.pi(p);
  }

  /**
   * 円周率を返す.
   * 
   * @return BigDecimal型の値
   * @since 2004/08/14
   */
  public BigDecimal pi() {
    return Pi.pi(this.precision);
  }

  /**
   * 自然対数の底を返す.
   * 
   * @param p
   *          誤差
   * @return BigDecimal型の値
   * @since 1.1
   * @deprecated exp()を推奨
   */
  @Deprecated
  public static BigDecimal exp(final Precision p) {
    return Exp.e(p);
  }

  /**
   * 自然対数の底を返す.
   * 
   * @return BigDecimal型の値
   * @since 1.1
   */
  public BigDecimal exp() {
    return Exp.e(this.precision);
  }
}
