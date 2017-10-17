package name.sugawara.hiroshi.math.precision;

import java.math.MathContext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 丸め誤差の精度情報.
 * Since October.12.2002
 * 
 * @author Hiroshi Sugawara
 * @version $Id: RoundingError.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */

public class RoundingError extends Precision {

  /**
   * @since 2010/06/11　3:06:42
   */
  private static final long serialVersionUID = 7236581104902992455L;

  /**
   * BigDecimal でdivide()を使用するときに必要とする値、スケール.
   */
  private int               scale;

  /**
   * BigDecimal でdivide()を使用するときに必要とする値、丸めモード.
   */
  private int               roundingMode;

  /**
   * java5から追加されたBigDecimalで使用される MathContextを指定し、情報としてオブジェクトに格納する.
   * 
   * @param mathContext
   *          MathContextオブジェクト
   * @since 2005/07/25 13:22:26
   */
  public RoundingError(final MathContext mathContext) {
    super(mathContext);
    this.scale = mathContext.getPrecision();
    this.roundingMode = mathContext.getRoundingMode().ordinal();
  }

  /**
   * 現在のオブジェクトに指定された BigDecimal のスケールを返す.
   * 
   * @return スケール
   * @since 1.1
   */
  public final int getScale() {
    return this.scale;
  }

  /**
   * 現在のオブジェクトに指定された BigDecimal の丸めモードを返す.
   * 
   * @return 丸めモード
   * @since 1.1
   */
  public final int getRoundingMode() {
    return this.roundingMode;
  }

  /**
   * オブジェクトが同値であるかどうかを比較する.
   * 
   * @param other
   *          比較対象
   * @return 同値であればtrue
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof RoundingError)) {
      return false;
    }
    final RoundingError castOther = (RoundingError) other;
    return new EqualsBuilder().append(this.getMathContext(), castOther.getMathContext()).append(
        this.scale, castOther.scale).append(this.roundingMode, castOther.roundingMode).isEquals();
  }

  /**
   * オブジェクトのハッシュコードを求める.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.getMathContext()).append(this.scale).append(
        this.roundingMode).toHashCode();
  }

}
