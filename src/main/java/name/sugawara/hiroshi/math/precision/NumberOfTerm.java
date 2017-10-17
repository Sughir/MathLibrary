package name.sugawara.hiroshi.math.precision;

import java.math.BigInteger;
import java.math.MathContext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 誤差、精度情報.
 * Since October.12.2002
 * 
 * @author Hiroshi Sugawara
 * @version $Id: NumberOfTerm.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */

public final class NumberOfTerm extends RoundingError {

  /**
   * シリアル.
   * 
   * @since 2007/03/09 13:14:04
   */
  private static final long serialVersionUID = -7069187729726919493L;

  /**
   * 級数や連分数を計算するときに項を計算する回数. <br/>
   * この値が大きいほど精度が高くなるが、計算速度は落ちる.
   */
  private final BigInteger  n;

  /**
   * hastCode()用に用いる.
   * @since 2007/03/09 13:07:22
   */
  private transient int     hashCode;

  /**
   * 指定した引数に従って精度情報を作成する. <br/>
   * n の値が大きいほど精度が高くなるが、計算速度は落ちる.
   * 
   * @param n
   *          連分数や級数を計算するときに項を計算する回数
   * @param mathContext
   *          MathContextオブジェクト
   * @since 2005/07/25 15:07:01
   */
  public NumberOfTerm(final BigInteger n, final MathContext mathContext) {
    super(mathContext);
    this.n = n;

  }

  /**
   * 級数や連分数を計算するときに項を計算する回数を示す値を返す.<br/>
   * 
   * @return 項を演算する回数
   */
  public BigInteger getN() {
    return this.n;
  }

  /**
   * 指定されたオブジェクトと現在のオブジェクトの要素が等しいかどうかを比較する.
   * 
   * @param other
   *          指定されたオブジェクト
   * @return 2つの値が等しいとき真を返す
   * @since 1.1
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof NumberOfTerm)) {
      return false;
    }
    final NumberOfTerm castOther = (NumberOfTerm) other;
    return new EqualsBuilder().appendSuper(super.equals(other)).append(this.n, castOther.n)
        .isEquals();
  }

  /**
   * ハッシュコードを求める.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    if (this.hashCode == 0) {
      this.hashCode = new HashCodeBuilder().appendSuper(super.hashCode()).append(this.n)
          .toHashCode();
    }
    return this.hashCode;
  }

}
