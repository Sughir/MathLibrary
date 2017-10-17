package name.sugawara.hiroshi.math.precision;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 機械イプシロン(許容誤差). $Author: sugawara $ $Date: 2010-06-13 13:26:48 +0900 (日, 13 6 2010) $ 許容誤差の精度情報<br />
 * Since October.12.2002
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Epsilon.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public class Epsilon extends RoundingError {

  /**
   * シリアル.
   * 
   * @since 2007/03/09 12:47:59
   */
  private static final long serialVersionUID = 2121804116716965867L;

  /**
   * 機械イプシロン(許容誤差).
   */
  private BigDecimal        epsilon;

  /**
   * 指定した引数に従って精度情報を作成する. epsilon の値が小さいほど精度が高くなるが、計算速度は落ちる.
   * 
   * @param epsilon
   *          機械イプシロン(許容誤差)
   * @param mathContext
   *          MathContextオブジェクト
   * @since 1.1
   */
  public Epsilon(final BigDecimal epsilon, final MathContext mathContext) {
    super(mathContext);
    this.epsilon = epsilon;
  }

  /**
   * 機械イプシロン(許容誤差)を返す.
   * 
   * @return 機械イプシロン(許容誤差)
   * @since 1.1
   */
  public final BigDecimal getEpsilon() {
    return this.epsilon;
  }

  /**
   * オブジェクトが同値かどうかを比較する.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return 同値であればtrue
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public final boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Epsilon)) {
      return false;
    }
    final Epsilon castOther = (Epsilon) other;
    return new EqualsBuilder().appendSuper(super.equals(other)).append(this.getMathContext(),
        castOther.getMathContext()).append(this.epsilon, castOther.epsilon).isEquals();
  }

  /**
   * ハッシュコードを返す.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public final int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(this.getMathContext())
        .append(this.epsilon).toHashCode();
  }

}
