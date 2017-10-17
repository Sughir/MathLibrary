package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 常用対数(common logarithm)を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Log10.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Log10 implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
  public Log10(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 常用対数を自然対数を連分数展開で求めるLog.logOfContinuedFraction()を用いて求める.<br />
   * 
   * @param argument
   *          BigDecimal型の値
   * @return 常用対数log_10(x)をBigDecimal型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return Log10.log10(argument, this.precision);
  }



  /**
   * 常用対数を自然対数を連分数展開で求めるLog.logOfContinuedFraction()を用いて求める.<br />
   * 
   * @param x
   *          BigDecimal型の値
   * @param p
   *          誤差
   * @return 常用対数log_10(x)をBigDecimal型で返す
   * @since 1.1
   */
  public static BigDecimal log10(final BigDecimal x, final Precision p) {
    final  BigDecimal numerator = Log.logOfContinuedFraction(x, p);
    final  BigDecimal denominator = Log.logOfContinuedFraction(new BigDecimal("10"), p);
    final   MathContext mc = p.getMathContext();
    return numerator.divide(denominator, mc);
  }
}
