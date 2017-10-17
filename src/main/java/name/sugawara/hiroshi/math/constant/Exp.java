package name.sugawara.hiroshi.math.constant;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.integer.BigIntegerMath;
import name.sugawara.hiroshi.math.precision.NumberOfTerm;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 指数関数 exponential function 自然対数の底 base of natural logarithm.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Exp.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 */
final strictfp class Exp {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/11 3:09:32
   */
  private Exp() {
    // Empty block
  }

  /**
   * 自然対数の底 e を長桁で求める.
   * 
   * <pre>
   *  信頼できる桁数にするには、 scale &lt; log10(gamma(n + 1)) 
   *  の範囲にする必要がある. (gammaはガンマ関数(nが整数より
   *  n! (nの階乗))) scaleを大 きくするために、 この式に従っ
   *  て n も大きくしなければならない.
   * </pre>
   * 
   * @param p
   *          誤差
   * @return 自然対数の底 e を返す
   */
  public static BigDecimal e(final Precision p) {
    final BigInteger n = ((NumberOfTerm) p).getN();

    if (n.compareTo(BigInteger.ZERO) < 0) {
      throw new Error("The term index must not be a negative value.");
    }
    final int scale = ((NumberOfTerm) p).getScale();
    final int mode = ((NumberOfTerm) p).getRoundingMode();

    BigDecimal e = BigDecimal.ZERO;

    for (BigInteger l = BigInteger.ZERO; l.compareTo(n) < 0; l = l.add(BigInteger.ONE)) {
      e = e.add(BigDecimal.ONE.divide(new BigDecimal(BigIntegerMath.factorial(l)), scale, mode));
    }
    return e;
  }
}
