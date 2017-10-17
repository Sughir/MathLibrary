package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal でサイン(正弦)関数 sin(x) を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Sine.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 */

public final strictfp class Sine implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 精度を指定する.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/03
   */
  public Sine(final Precision precision) {
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
    return Sine.sin(argument, this.precision);
  }

  /**
   * 指定された角度の正弦 (サイン) を返す.
   * 
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 引数の正弦 (サイン)をBigDecimal 型で返す
   * @since 1.1
   */
  private static BigDecimal sin(final BigDecimal argument, final Precision p) {
    final MathContext mc = p.getMathContext();
    final Constant c = new Constant(p);

    final BigDecimal two = new BigDecimal("2.0");
    // BigDecimal four = new BigDecimal("4.0");

    // BigDecimal fourtyFive = Constant.pi(p).divide(four, mc);
    final BigDecimal ninty = c.pi().divide(two, mc);
    final BigDecimal pi = c.pi();
    final BigDecimal twoHundredAndSeventy = pi.multiply(new BigDecimal("3.0")).divide(two, mc);

    final BigDecimal cycle = pi.multiply(two);
    BigDecimal result = null;

    if (argument.compareTo(BigDecimal.ZERO) == 0 || argument.compareTo(cycle) == 0
        || argument.compareTo(pi) == 0) {
      result = BigDecimal.ZERO;
    } else if (argument.compareTo(ninty) == 0) {
      result = BigDecimal.ONE;
    } else if (argument.compareTo(twoHundredAndSeventy) == 0) {
      result = BigDecimal.ONE.negate();
    } else {

      final Tangent tan = new Tangent(p);

      final BigDecimal valueOfTangent = tan.getDependentVariable(argument.divide(two, mc));
      final BigDecimal numerator = valueOfTangent.multiply(two);
      final BigDecimal denominator = valueOfTangent.multiply(valueOfTangent).add(BigDecimal.ONE);
      result = numerator.divide(denominator, mc);
    }
    return result;
  }
}
