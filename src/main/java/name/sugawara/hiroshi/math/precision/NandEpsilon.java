package name.sugawara.hiroshi.math.precision;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 誤差、精度情報.<br />
 * Since October.12.2002
 * 
 * @author Hiroshi Sugawara
 * @version $Id: NandEpsilon.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */

public class NandEpsilon extends RoundingError {

  /**
   * シリアル.
   * 
   * @since 2007/03/09 12:53:02
   */
  private static final long serialVersionUID = 3593689834125723008L;

  /**
   * 級数や連分数を計算するときに項を計算する回数. この値が大きいほど精度が高くなるが、計算速度は落ちる.
   */
  private BigInteger        n;

  /**
   * 機械イプシロン(許容誤差).
   */
  private BigDecimal        epsilon;

  /**
   * 指定した引数に従って精度情報を作成する.
   * @param n
   *          項を計算する回数
   * @param epsilon
   *          機械イプシロン(許容誤差)
   * @param mathContext
   *          MathContextオブジェクト
   */
  public NandEpsilon(final BigInteger n, final BigDecimal epsilon, final MathContext mathContext) {
    super(mathContext);
    this.n = n;
    this.epsilon = epsilon;
  }

  /**
   * 指定した引数に従って精度情報を作成する.
   * 
   * @param n
   *          項を計算する回数
   * @param epsilon
   *          機械イプシロン(許容誤差)
   * @param scale
   *          BigDecimal でdivide()を使用するときに必要とする値、スケール
   * @param roundingMode
   *          BigDecimal でdivide()を使用するときに必要とする値、丸めモード
   */
  // public NandEpsilon(final BigInteger n, final BigDecimal epsilon,
  // final int scale, final int roundingMode) {
  // super(scale, roundingMode);
  // this.n = n;
  // this.epsilon = epsilon;
  // }
  /**
   * 級数や連分数を計算するときに項を計算する回数を示す値を返す.
   * 
   * @return 項を演算する回数
   */
  public final BigInteger getN() {
    return this.n;
  }

  /**
   * 機械イプシロン(許容誤差)を返す.
   * 
   * @return 機械イプシロン(許容誤差)
   */
  public final BigDecimal getEpsilon() {
    return this.epsilon;
  }

  /**
   * オブジェクトが同値であるかどうかを比較する.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return 同値であればtrueを返す
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public final boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof NandEpsilon)) {
      return false;
    }
    final NandEpsilon castOther = (NandEpsilon) other;
    return new EqualsBuilder().appendSuper(super.equals(other)).append(this.getMathContext(),
        castOther.getMathContext()).append(this.n, castOther.n).append(this.epsilon,
        castOther.epsilon).isEquals();
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
        .append(this.n).append(this.epsilon).toHashCode();
  }

}
