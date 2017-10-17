package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import static java.math.BigDecimal.ZERO;

/**
 * BigDecimal でタンジェント(正接)関数 tan(x) を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Tangent.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Tangent implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
  public Tangent(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 指定された角度の正接 (タンジェント) を返す.
   * 
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の正接 (タンジェント)をBigDecimal 型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return Tangent.tan(argument, this.precision);
  }

  /**
   * 指定された角度の正接 (タンジェント) を返す.
   * 
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 引数の正接 (タンジェント)をBigDecimal 型で返す
   * @since 1.1
   */
  private static BigDecimal tan(final BigDecimal argument, final Precision p) {
    final BigInteger n = ((NandEpsilon) p).getN();
    final MathContext mc = p.getMathContext();
    final Constant c = new Constant(p);
    final BigDecimal pi = c.pi(), fourtyFive = pi.divide(new BigDecimal("4.0"), mc);
    final BigDecimal ninty = pi.divide(new BigDecimal("2.0"), mc), twoHundredAndSeventy = pi
        .multiply(new BigDecimal("3.0")).divide(new BigDecimal("2.0"), mc), cycle = pi
        .multiply(new BigDecimal("2.0"));

    BigDecimal result;

    if (argument.compareTo(ZERO) == 0 || argument.compareTo(cycle) == 0) {
      result = ZERO;
    } else if (argument.compareTo(cycle) > 0) {
      result = Tangent.tan(argument.subtract(cycle), p);
    } else if (argument.compareTo(cycle.negate()) < 0) {
      result = Tangent.tan(argument.add(cycle), p);
    } else if (argument.compareTo(pi) == 0) {
      result = ZERO;
    } else if (argument.compareTo(ninty) == 0 || argument.compareTo(twoHundredAndSeventy) == 0) {
      // 例外の日本語訳 : タンジェントの値が無限になっている可能性があります.
      // もし、引数の値がπまたは2分のπでないとわかっているならば、
      // 計算の精度と桁数を高めてください.
      throw new ArithmeticException("The value of the tangent may be infinite. "
          + "If you know that the value of the argument is not a pi or a half"
          + " of pi, raise the precision of calculation and increase the number of digits.");

    } else if (argument.compareTo(ninty) > 0 && argument.compareTo(pi) < 0) {
      result = Tangent.tan(argument.subtract(pi), p);
    } else if (argument.compareTo(pi) > 0 && argument.compareTo(twoHundredAndSeventy) < 0) {
      result = Tangent.tan(argument.subtract(pi), p);
    } else if (argument.compareTo(twoHundredAndSeventy) > 0 && argument.compareTo(cycle) < 0) {
      result = Tangent.tan(argument.subtract(cycle), p);
    } else if (argument.compareTo(fourtyFive) > 0 && argument.compareTo(ninty) < 0) {
      result = BigDecimal.ONE.divide(Tangent.tan(ninty.subtract(argument), p), mc);
    } else if (argument.compareTo(fourtyFive.negate()) < 0
        && argument.compareTo(ninty.negate()) > 0) {
      result = BigDecimal.ONE.divide(Tangent.tan(argument.add(ninty), p), mc).negate();
    } else {

      final BigInteger twoInt = BigInteger.valueOf(2);
      final BigDecimal numerator = argument.multiply(argument);
      BigDecimal denominator, s = ZERO;
      BigInteger partsOfDenominator;

      for (BigInteger k = n; k.compareTo(BigInteger.ONE) > 0; k = k.subtract(BigInteger.ONE)) {
        partsOfDenominator = twoInt.multiply(k).subtract(BigInteger.ONE);
        denominator = new BigDecimal(partsOfDenominator).subtract(s);

        s = numerator.divide(denominator, mc);
      }
      result = argument.divide(BigDecimal.ONE.subtract(s), mc);
    }
    return result;
  }
}
