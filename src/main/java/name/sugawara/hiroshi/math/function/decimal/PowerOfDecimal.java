package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal で実数の実数乗の演算が可能な指数関数.
 * 
 * exponential function
 * 
 * @author Hiroshi Sugawara
 * @version $Id: PowerOfDecimal.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class PowerOfDecimal implements
    FunctionOfTwoVariable<BigDecimal, BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 誤差を指定する.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/03
   */
  public PowerOfDecimal(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 実数の実数上を計算. <br />
   * pow( base, exponent) を返す (baseのexponent乗を返す) <br />
   * exponent が 0 のとき 1 を返す <br />
   * exponent が 1 のとき base をそのまま返す <br />
   * exponent が 0, base が 1 のとき 0 を返す <br />
   * 返す型が無限大、非数値や複素数になるような引数の組み合わせの場合、ArithmeticExceptionをスローする <br />
   * 
   * @param base
   *          指数関数の基数部
   * @param exponent
   *          指数関数の指数部
   * @return pow( base, exponent ) を返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal base, final BigDecimal exponent) {
    return PowerOfDecimal.pow(base, exponent, this.precision);
  }

  /**
   * 実数の実数上を計算.
   * 
   * <pre>
   *             pow( base, exponent) を返す (baseのexponent乗を返す).
   *             exponent が 0 のとき 1 を返す.
   *             exponent が 1 のとき base をそのまま返す.
   *             exponent が 0, base が 1 のとき 0 を返す.
   *             基数が負でかつ指数が整数でない場合、複素数になってしまうため、
   *             ArithmeticExceptionをスローする.
   *             返す型が無限大、非数値や複素数になるような
   *             引数の組み合わせの場合、ArithmeticExceptionをスローする.
   * </pre>
   * 
   * @param base
   *          指数関数の基数部
   * @param exponent
   *          指数関数の指数部
   * @param p
   *          誤差
   * @return pow( base, exponent ) を返す
   * @since 1.1
   */
  private static BigDecimal pow(final BigDecimal base, final BigDecimal exponent, final Precision p) {
    BigDecimal result;
    if (exponent.compareTo(BigDecimal.ZERO) == 0) {
      result = BigDecimal.ONE;
    } else if (exponent.compareTo(BigDecimal.ONE) == 0) {
      result = base;
    } else if (base.compareTo(BigDecimal.ZERO) == 0 && exponent.compareTo(BigDecimal.ZERO) > 0) {
      result = BigDecimal.ZERO;
    } else if (base.compareTo(BigDecimal.ZERO) == 0 && exponent.compareTo(BigDecimal.ZERO) < 0) {
      throw new ArithmeticException("The expression is a divide by zero.");
    } else if (base.compareTo(BigDecimal.ZERO) < 0
        && new BigDecimal(exponent.toBigInteger()).compareTo(exponent) == 0) {

      // このメソッドBigDecimal#toBigIntegerExact()により、exponentが
      // 小数である場合、BigIntegerへの型変換によって小数部が除去されてしまう
      // ことを通知するために例外ArithmeticExcceptionがスローされる。
      final BigInteger bigInteger = exponent.toBigIntegerExact();
      // bigInteger(exponent)が偶数のとき
      if (bigInteger.remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) {
        result = PowerOfDecimal.pow(base.abs(), exponent, p);
      } else {
        result = PowerOfDecimal.pow(base.abs(), exponent, p).negate();
      }

    } else if (base.compareTo(BigDecimal.ZERO) < 0) {
      throw new ArithmeticException("The expression is Not a Number."
          + " This method PowerOfDecimal.pow() cannot operate the complex numbers.");
    } else {
      final Exponent exp = new Exponent(p);
      final Log log = new Log(p);
      result = exp.getDependentVariable(exponent.multiply(log.getDependentVariable(base)));
    }
    return result;
  }
}
