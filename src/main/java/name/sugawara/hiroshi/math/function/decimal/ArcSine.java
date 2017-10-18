package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal でアークサイン(逆正弦)関数 asin(x) を求める.
 *
 * @author Hiroshi Sugawara
 * @version $Id: ArcSine.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public strictfp class ArcSine implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
  public ArcSine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 計算する.
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の逆余弦 (アークコサイン)をBigDecimal 型で返す
   */
  @Override
  public final BigDecimal getDependentVariable(final BigDecimal argument) {

    final Constant c = new Constant(this.precision);
    return ArcSine.asin(argument, this.precision).negate().add(
        c.pi().multiply(new BigDecimal("0.5")));
  }

  /**
   * 指定された角度の逆正弦 (アークサイン) を返す. 引数の値が-1から1までの範囲外のとき、ArithmeticExceptionを返す.
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 引数の逆正弦 (アークサイン)をBigDecimal 型で返す
   * @since 1.1
   */
  private static BigDecimal asin(final BigDecimal argument, final Precision p) {
    final Constant c = new Constant(p);

    BigDecimal result;
    if (argument.compareTo(BigDecimal.ZERO) == 0) {
      result = BigDecimal.ZERO;
    } else if (argument.compareTo(BigDecimal.ONE) == 0) {
      result = c.pi().multiply(new BigDecimal("0.5"));
    } else if (argument.compareTo(BigDecimal.ONE.negate()) == 0) {
      result = c.pi().multiply(new BigDecimal("0.5")).negate();
    } else if (argument.abs().compareTo(BigDecimal.ONE) == 1) {
      throw new ArithmeticException("The expression is Not a Number. This method ArcSine.asin()"
          + " cannot operate the complex numbers. The value of an argument"
          + " must be the range of |a| <= 1.");
    } else {

      final BigDecimal a2 = argument.multiply(argument);
      final BigDecimal oneSubA2 = BigDecimal.ONE.subtract(a2);

      final ArcTangent atan = new ArcTangent(p);
      final SquareRoot sqrt = new SquareRoot(p);
      result = atan.getDependentVariable(
      argument.divide(sqrt.getDependentVariable(oneSubA2), p.getMathContext()));
    }
    return result;
  }
}
