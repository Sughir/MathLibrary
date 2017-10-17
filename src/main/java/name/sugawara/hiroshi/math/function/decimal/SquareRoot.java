package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 平方根をBigDecimal型で求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: SquareRoot.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class SquareRoot implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 精度を設定する.
   * 
   * @param precision
   *          精度
   * @since 2004/08/03
   */
  public SquareRoot(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 底を2とした対数を、自然対数を連分数展開で求める.
   * 
   * Log.logOfContinuedFraction()を用いて求める.
   * 
   * @param argument
   *          BigDecimal型の値
   * @return 常用対数log_10(x)をBigDecimal型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return SquareRoot.sqrt(argument, this.precision);
  }

  /**
   * 平方根をBigDecimal型で求める.
   * 
   * <pre>
   *   許容誤差を10の-6乗以下にするとdouble型以上の精度を得られる.
   * </pre>
   * 
   * @param a
   *          値
   * @param p
   *          誤差
   * @return sqrt(a) (aの平方)を返す
   * @since 1.1
   */
  private static BigDecimal sqrt(final BigDecimal a, final Precision p) {
    if (a.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    } else if (a.compareTo(BigDecimal.ZERO) < 0) {
      throw new ArithmeticException("The expression is Not a Number."
          + " This method and class cannot operate the complex numbers.");
    }

    final BigDecimal eps = ((NandEpsilon) p).getEpsilon();
    final MathContext mc = p.getMathContext();
    BigDecimal x = a;
    BigDecimal y;
    while (true) {
      y = x.add(a.divide(x, mc)).multiply(new BigDecimal("0.5"));
      if (x.subtract(y).abs().compareTo(eps) < 0) {
        return y;
      }
      x = y;
    }
  }
}
