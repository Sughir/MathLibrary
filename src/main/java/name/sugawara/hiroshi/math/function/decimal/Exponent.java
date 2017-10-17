package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.integer.BigIntegerMath;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 自然対数の底 (base of natural logarithm) の累乗.
 * 
 * @author Hiroshi Sugawara
 * @version 0.1
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Exponent implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 計算用実数2.
   * 
   * @since 2006/07/19 21:52:57
   */
  private static final BigDecimal TWO = new BigDecimal("2.0");

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision               precision;

  /**
   * 精度を指定する.
   * 
   * @param precision
   *          精度
   */
  public Exponent(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 自然対数の底のexponent乗 e^exponentをテイラー(Taylor)展開を用いて求める.
   * 
   * <pre>
   *          (Exponential Function).
   *          exponentが負数の場合は正数として求めてから逆数を求めることにより
   *          桁落ちを最小限にしている. 連分数展開版よりも精度が悪く、速度も遅い.
   *          許容誤差EPSが0以下のとき例外をスローする.
   * </pre>
   * 
   * @param exponent
   *          指数
   * @return 自然対数の底のbase乗をBigDecimal型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal exponent) {
    return Exponent.expOfContinuedFraction(exponent, this.precision);
  }

  /**
   * 自然対数の底のexponent乗 e^exponentをテイラー(Taylor)展開を用いて求める.
   * 
   * <pre>
   *            (Exponential Function).
   *            exponentが負数の場合は正数として求めてから逆数を求めることにより
   *            桁落ちを最小限にしている. 連分数展開版よりも精度が悪く、速度も遅い.
   *            許容誤差EPSが0以下のとき例外をスローする.
   * </pre>
   * 
   * @param exponent
   *          指数
   * @param p
   *          誤差
   * @return 自然対数の底のbase乗をBigDecimal型で返す
   * @since 1.1
   */
  // private static BigDecimal exp(BigDecimal exponent, Precision p) {
  // int scale = ((Epsilon) p).getScale();
  // int mode = ((Epsilon) p).getRoundingMode();
  // BigDecimal eps = ((Epsilon) p).getEpsilon();
  //
  // //exponentが負数の場合、正数として求めてから逆数を求めることにより桁落ちを最小限にする
  // //指数が 0 のとき 1 を返す
  // if (exponent.compareTo(BigDecimal.ZERO) == -1) {
  // return BigDecimal.ONE.divide(exp(exponent.abs(), p), scale, mode);
  // } else if (exponent.compareTo(BigDecimal.ZERO) == 0) {
  // return BigDecimal.ONE;
  // }
  //
  // if (eps.compareTo(BigDecimal.ZERO) == -1
  // || eps.compareTo(BigDecimal.ZERO) == 0) {
  // throw new ArithmeticException("A permissible error must be greater than
  // zero.");
  // }
  //
  // BigDecimal s = BigDecimal.ONE;
  // BigDecimal e = BigDecimal.ONE;
  // BigDecimal d = BigDecimal.ONE;
  //
  // for (int k = 1;; k++) {
  // d = s;
  // e = (e.multiply(exponent)).divide(new BigDecimal(k), scale, mode);
  // s = s.add(e);
  // BigDecimal sd = s.subtract(d);
  // BigDecimal absSD = sd.abs();
  //
  // BigDecimal absD = d.abs();
  // BigDecimal epsMultiplyAbsD = eps.multiply(absD);
  //
  // if (absSD.compareTo(epsMultiplyAbsD) == -1) {
  // return s;
  // }
  // }
  // }
  /**
   * ldexp(x,k)関数.
   * 
   * @param x
   *          BigDecimal型の実数
   * @param k
   *          BigInteger型の整数
   * @param p
   *          精度
   * @return BigDecimal型の実数
   * @since 2006/07/19 21:23:30
   */
  private static BigDecimal ldexp(final BigDecimal x, final BigInteger k, final Precision p) {

    boolean sign = false;
    if (k.compareTo(BigInteger.ZERO) < 0) {
      sign = true;
    }
    final BigDecimal twoK = new BigDecimal(BigIntegerMath.power(BigInteger.valueOf(2), k.abs()));

    if (sign) {
      return x.divide(twoK, p.getMathContext());
    } else {
      return x.multiply(twoK);
    }
  }

  /**
   * 自然対数の底のexponent乗 e^exponentを連分数展開を用いて求める.
   * 
   * <pre>
   *         (Exponential Function).
   *         桁落ちは級数展開版よりも小さくなっている。
   * </pre>
   * 
   * @param exponent
   *          指数
   * @param p
   *          誤差
   * @return 自然対数の底のbase乗をBigDecimal型で返す
   * @since 1.1
   */
  private static BigDecimal expOfContinuedFraction(final BigDecimal exponent, final Precision p) {

    if (exponent.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ONE;
    }

    final Log log = new Log(p);

    final MathContext mc = p.getMathContext();
    final BigDecimal log2 = log.getDependentVariable(Exponent.TWO);

    BigDecimal addedValue;
    if (exponent.compareTo(BigDecimal.ZERO) >= 0) {
      addedValue = new BigDecimal("0.5");
    } else {
      addedValue = new BigDecimal("-0.5");
    }

    final int four = 4;
    final int six = 6;
    final BigInteger k = exponent.divide(log2, mc).add(addedValue).toBigInteger();
    final BigDecimal x = exponent.subtract(new BigDecimal(k).multiply(log2));
    final BigDecimal x2 = x.multiply(x);
    final BigInteger n = ((NandEpsilon) p).getN().multiply(BigInteger.valueOf(four)).add(
        BigInteger.valueOf(2));
    BigDecimal w = x2.divide(new BigDecimal(n), mc);

    for (BigInteger i = n.subtract(BigInteger.valueOf(four)); i.compareTo(BigInteger.valueOf(six)) >= 0; i = i
        .subtract(BigInteger.valueOf(four))) {
      w = x2.divide(w.add(new BigDecimal(i)), mc);
    }
    final BigDecimal twoW = Exponent.TWO.add(w);

    return Exponent.ldexp(twoW.add(x).divide(twoW.subtract(x), mc), k, p);

    // BigDecimal s = BigDecimal.ZERO;
    // // BigDecimal one = BigDecimal.ONE;
    // BigInteger two = BigInteger.valueOf(2);
    // BigDecimal decimalTwo = new BigDecimal("2.0");
    // int intFour = 4;
    // BigInteger four = BigInteger.valueOf(intFour);
    // BigInteger partsOfDenominator;
    // BigDecimal denominator;
    //
    // for (BigInteger i = n; i.compareTo(BigInteger.ONE) == 1
    // || i.compareTo(BigInteger.ONE) == 0; i = i.subtract(BigInteger.ONE)) {
    //
    // partsOfDenominator = four.multiply(i).add(two);
    // denominator = new BigDecimal(partsOfDenominator).add(s);
    // s = (exponent.multiply(exponent)).divide(denominator, mc);
    // }
    // s = s.add(decimalTwo);
    //
    // return (s.add(exponent)).divide(s.subtract(exponent), mc);
  }
}
