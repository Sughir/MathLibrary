package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal でアークタンジェント(逆正接)関数 atan(x) を求める.
 *
 * @author Hiroshi Sugawara
 * @version $Id: ArcTangent.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class ArcTangent implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
   *          精度
   */
  public ArcTangent(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 指定された角度の逆正接 (アークタンジェント) を返す.
   *
   * @param argument
   *          引数
   * @return 引数の逆正接 (アークタンジェント)をBigDecimal 型で返す
   */
  @Override
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return ArcTangent.atan(argument, this.precision);
  }

  /**
   * 指定された角度の逆正接 (アークタンジェント) を返す.
   *
   * @param argument
   *          引数
   * @param p
   *          誤差
   * @return 引数の逆正接 (アークタンジェント)をBigDecimal 型で返す
   */
  private static BigDecimal atan(final BigDecimal argument, final Precision p) {
    BigDecimal arg = argument;

    final BigDecimal zero = BigDecimal.ZERO;
    final BigDecimal one = BigDecimal.ONE;
    final BigDecimal two = new BigDecimal("2.0");
    int sign;
    BigDecimal result;

    if (argument.compareTo(zero) == 0) {
      return zero;
    } else if (arg.compareTo(one) > 0) {
      sign = 1;
      arg = one.divide(arg, p.getMathContext());
    } else if (arg.compareTo(one.negate()) < 0) {
      sign = -1;
      arg = one.divide(arg, p.getMathContext());
    } else {
      sign = 0;
    }

    final BigInteger number = ((NandEpsilon) p).getN();

    BigDecimal a = zero;
    BigDecimal numerator;
    BigDecimal denominator;
    BigDecimal squareI;
    for (BigInteger i = number; i.compareTo(BigInteger.ONE) >= 0; i = i.subtract(BigInteger.ONE)) {
      squareI = new BigDecimal(i.multiply(i));
      numerator = squareI.multiply(arg).multiply(arg);
      denominator = two.multiply(new BigDecimal(i)).add(one).add(a);
      a = numerator.divide(denominator, p.getMathContext());
    }
    final Constant c = new Constant(p);

    final BigDecimal ninty = c.pi().multiply(new BigDecimal("0.5"));

    if (sign > 0) {
      result = ninty.subtract(arg.divide(one.add(a), p.getMathContext()));
    } else if (sign < 0) {
      result = ninty.negate().subtract(arg.divide(one.add(a), p.getMathContext()));
    } else {
      result = arg.divide(one.add(a), p.getMathContext());
    }
    return result;
  }
}
