package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 指数関数 exponential function.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Power.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Power implements
    FunctionOfTwoVariable<BigDecimal, BigInteger, BigDecimal> {

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
  public Power(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 基数の整数乗 base^exponent を返す.<br />
   * 指数が負数でない場合はscaleとroundingModeは使用されない。
   * 
   * @param base
   *          指数関数の基数部
   * @param exponent
   *          指数関数の指数部
   * @return 1 番目の BigDecimal 型引数を、2 番目の BigInteger 型引数で累乗した値
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal base, final BigInteger exponent) {
    return Power.pow(base, exponent, this.precision);
  }

  /**
   * 基数の整数乗 base^exponent を返す. <br />
   * 指数が負数でない場合はscaleとroundingModeは使用されない。
   * 
   * @param base
   *          指数関数の基数部
   * @param exponent
   *          指数関数の指数部
   * @param p
   *          誤差
   * @return 1 番目の BigDecimal 型引数を、2 番目の BigInteger 型引数で累乗した値
   * @since 1.1
   */
  private static BigDecimal pow(final BigDecimal base, final BigInteger exponent, final Precision p) {
    BigDecimal lastResult;
    if (exponent.compareTo(BigInteger.ZERO) == 0) {
      lastResult = BigDecimal.ONE;
    } else if (exponent.compareTo(BigInteger.ZERO) > 0) {
      BigDecimal result = base;

      for (BigInteger bi = BigInteger.ONE; bi.compareTo(exponent) < 0; bi = bi.add(BigInteger.ONE)) {
        result = result.multiply(base);
      }
      lastResult = result;
    } else {

      BigDecimal mResult = base;
      for (BigInteger bi = BigInteger.ONE; bi.compareTo(exponent.abs()) < 0; bi = bi
          .add(BigInteger.ONE)) {
        mResult = mResult.multiply(base);
      }
      final MathContext mc = p.getMathContext();
      lastResult = BigDecimal.ONE.divide(mResult, mc);
    }
    return lastResult;
  }

  // /** 整数を基数とする基数の正の整数乗 base^exponent を返す。
  // * 指数部が負の場合は ArithmeticException をスローする。
  // * @param base BigInteger 型の指数関数の基数部
  // * @param exponent 正の整数からなる指数関数の指数部
  // * @return 1 番目の BigInteger 型引数を、2 番目の BigInteger 型引数で累乗した値
  // * @since 1.1
  // */
  // public static BigInteger pow(BigInteger base, BigInteger exponent){
  // if( exponent.compareTo(BigInteger.ZERO) == -1){
  // throw new ArithmeticException("The negative exponent is not supported by
  // this method.");
  // } else if(exponent.compareTo(BigInteger.ZERO) == 0){
  // return BigInteger.ONE;
  // } else if(exponent.compareTo(BigInteger.ONE) == 0){
  // return base;
  // } else {
  // BigInteger result = base;
  // for(BigInteger bi = BigInteger.ONE;
  // bi.compareTo(exponent) == -1;
  // bi = bi.add(BigInteger.ONE)
  // ){
  // result = result.multiply(base);
  // }
  // return result;
  // }
  // }
}
