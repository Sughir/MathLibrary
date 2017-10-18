package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal でアークコサイン(逆余弦)関数 {@code acos(x)}を求める.
 *
 * @author Hiroshi Sugawara
 * @version $Id: ArcCosine.java 109 2010-06-13 04:26:48Z sugawara $ *
 * @since 1.1
 */

public strictfp class ArcCosine implements
    FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   */
  private Precision precision;

  /**
   * 精度を指定する.
   *
   * @param precision
   *          精度
   */
  public ArcCosine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * {@Link BigDecimal}でアークコサイン(逆余弦)関数 {@code acos(x)} を求める.
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 引数の逆余弦 (アークコサイン)をBigDecimal 型で返す
   */
  private static BigDecimal acos(final BigDecimal argument, final Precision p) {
    final Constant c = new Constant(p);
    BigDecimal result;

    if (argument.compareTo(BigDecimal.ZERO) == 0) {
      result = c.pi().multiply(new BigDecimal("0.5"));
    } else if (argument.compareTo(BigDecimal.ONE) == 0) {
      result = BigDecimal.ZERO;
    } else if (argument.compareTo(BigDecimal.ONE.negate()) == 0) {
      result = c.pi();
    } else if (argument.abs().compareTo(BigDecimal.ONE) == 1) {
      throw new ArithmeticException(
          "The expression is Not a Number. This method"
              + " ArcSine.asin() cannot operate the complex"
              + " numbers. The value of an argument must be"
              + " the range of |a| <= 1.");
    } else {
      final ArcSine asin = new ArcSine(p);
      result = asin.getDependentVariable(argument).negate().add(
          c.pi().multiply(new BigDecimal("0.5")));
    }
    return result;
  }

  /**
   * 指定された角度の逆余弦 (アークコサイン) を返す. <br />
   * 引数の値が-1から1までの範囲外のとき、ArithmeticExceptionを返す。
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 逆余弦 (アークコサイン)
   * @see FunctionOfSingleVariable#getDependentVariable(Number)
   */
  @Override
  public final BigDecimal getDependentVariable(final BigDecimal argument) {

    return ArcCosine.acos(argument, this.precision);

  }

}
