package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 自然対数(natural logarithm)を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Log.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Log implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * コンストラクタ.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/03
   */
  public Log(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 自然対数(連分数展開版)を求める.
   * 
   * 
   * 級数展開版より高速。 桁落ちが無い。
   * 
   * @param argument
   *          BigDecimal型の値
   * @return 自然対数log_e(x)をBigDecimal型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return Log.logOfContinuedFraction(argument, this.precision);
  }

  /**
   * 自然対数(log(1+x)の級数展開版)を求める 複数回の減算が含まれるため桁落ちが生じる.
   * 
   * @param a
   *          BigDecimal型の値
   * @param p
   *          誤差
   * @return 自然対数log_e(a)をBigDecimal型で返す
   * @since 1.1
   */
  // private static BigDecimal logOfSeriesExpansion(BigDecimal a, Precision p) {
  //
  // LogJudgement.judgeOfNegativeNumber(a);
  //
  // BigDecimal x = a.subtract(BigDecimal.ONE);
  // BigDecimal term = new BigDecimal(0.0d);
  // BigDecimal temporaryTerm = BigDecimal.ZERO;
  //
  // BigInteger N = ((NumberOfTerm) p).getN();
  // int scale = ((NumberOfTerm) p).getScale();
  // int mode = ((NumberOfTerm) p).getRoundingMode();
  // Power pow = new Power(p);
  //
  // for (BigInteger k = BigInteger.ONE;
  // k.compareTo(N) == -1;
  // k = k.add(BigInteger.ONE)) {
  // temporaryTerm =
  // ((BigDecimal) pow.getDependentVariable(x, k)).divide(
  // new BigDecimal(k),
  // scale,
  // mode);
  //
  // if (k.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0) {
  // temporaryTerm = temporaryTerm.negate();
  // }
  // term = term.add(temporaryTerm);
  // }
  // return term;
  // }
  /**
   * 自然対数(log( (1+x) / (1-x) )の級数展開版)を求める. <br />
   * BigDecimalのスケールが少なければlog(1+x)より高速。 しかも浮動小数点による減算がないため桁落ちも無い。
   * 乗算によりスケールが増大するためスケールが大きいと演算速度は log(1+x)の級数展開版より減速する。
   * 
   * @param a
   *          BigDecimal型の値
   * @param p
   *          誤差
   * @return 自然対数log_e(x)をBigDecimal型で返す
   * @since 1.1
   */
  // private static BigDecimal logOfSeriesExpansion2(BigDecimal a, Precision p)
  // {
  //
  // LogJudgement.judgeOfNegativeNumber(a);
  //
  // BigDecimal one = BigDecimal.ONE;
  // BigDecimal term = BigDecimal.ZERO;
  // BigDecimal temporaryTermWithPower = null;
  // BigDecimal numerator = a.subtract(one);
  // BigDecimal denominator = a.add(one);
  //
  // BigInteger N = ((NumberOfTerm) p).getN();
  // int scale = ((NumberOfTerm) p).getScale();
  // int mode = ((NumberOfTerm) p).getRoundingMode();
  //
  // BigDecimal temporaryTerm = numerator.divide(denominator, scale, mode);
  //
  // BigInteger exponent = null;
  // Power pow = new Power(p);
  // for (BigInteger k = BigInteger.ONE;
  // k.compareTo(N) == -1;
  // k = k.add(BigInteger.ONE)) {
  // exponent = k.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE);
  // temporaryTermWithPower =
  // (
  // (BigDecimal) pow.getDependentVariable(
  // temporaryTerm,
  // exponent)).divide(
  // new BigDecimal(exponent),
  // scale,
  // mode);
  // term = term.add(temporaryTermWithPower);
  // }
  // return term.multiply(new BigDecimal("2.0"));
  // }
  /**
   * 自然対数(連分数展開版)を求める. <br />
   * 級数展開版より高速。 桁落ちが無い。
   * 
   * @param a
   *          BigDecimal型の値
   * @param p
   *          誤差
   * @return 自然対数log_e(x)をBigDecimal型で返す
   * @since 1.1
   */
  public static BigDecimal logOfContinuedFraction(final BigDecimal a, final Precision p) {

    LogJudgement.judgeOfNegativeNumber(a);

    BigDecimal s = BigDecimal.ZERO;
    final BigDecimal x = a.subtract(BigDecimal.ONE);

    final BigInteger two = BigInteger.valueOf(2);
    BigInteger leftOfLastDenominator = null;
    BigDecimal lastDenominator = null;
    BigDecimal ix = null;
    BigDecimal rDenominator = null;
    BigDecimal denominator = null;

    final BigInteger n = ((NandEpsilon) p).getN();
    final MathContext mc = p.getMathContext();

    for (BigInteger i = n; i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
      leftOfLastDenominator = i.multiply(two).add(BigInteger.ONE);
      lastDenominator = new BigDecimal(leftOfLastDenominator).add(s);

      ix = x.multiply(new BigDecimal(i));

      rDenominator = ix.divide(lastDenominator, mc);
      denominator = new BigDecimal(two).add(rDenominator);
      s = ix.divide(denominator, mc);
    }
    return x.divide(s.add(new BigDecimal(BigInteger.ONE)), mc);
  }
}
