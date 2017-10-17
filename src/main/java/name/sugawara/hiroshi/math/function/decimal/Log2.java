package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 2進対数を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Log2.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Log2 implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 精度情報を指定する.
   * 
   * @param precision
   *          精度
   */
  public Log2(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 底を2とした対数を、自然対数を連分数展開で求める.
   * 
   * <pre>
   *    Log.logOfContinuedFraction()を用いて求める.
   * </pre>
   * 
   * @param argument
   *          BigDecimal型の値
   * @return 常用対数log_10(x)をBigDecimal型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return Log2.log2(argument, this.precision);
  }

  /**
   * 底を2とした対数を、自然対数を連分数展開で求めるLog.
   * 
   * logOfContinuedFraction()を用いて求める.
   * 
   * @param x
   *          BigDecimal型の値
   * @param p
   *          誤差
   * @return 常用対数log_10(x)をBigDecimal型で返す
   * @since 1.1
   */
  public static BigDecimal log2(final BigDecimal x, final Precision p) {
    final BigDecimal numerator = Log.logOfContinuedFraction(x, p);
    final BigDecimal denominator = Log.logOfContinuedFraction(new BigDecimal("2"), p);
    final MathContext mc = p.getMathContext();
    return numerator.divide(denominator, mc);
  }
}
