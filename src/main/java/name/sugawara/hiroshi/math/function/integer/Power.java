/**
 * Created Date : 2006/07/19 16:50:07
 */
package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;

/**
 * 整数のみに限定した指数関数.
 * 
 * 指数には負の値を入れることはできない.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * 
 * Created Date : 2006/07/19 16:50:07
 * 
 */
public final class Power implements FunctionOfTwoVariable<BigInteger, BigInteger, BigInteger> {

  /**
   * 引数・戻り値が整数のみの指数関数の結果を返す.
   * 
   * 引数の指数部には自然数のみしか入れることができない.
   * 
   * @param base
   *          基数部
   * @param exponent
   *          自然数の指数部
   * @return BigInteger型の値
   * @since 2006/07/19 17:04:55
   */
  public static BigInteger pow(final BigInteger base, final BigInteger exponent) {
    BigInteger result = BigInteger.ONE;

    if (exponent.compareTo(BigInteger.ZERO) < 0) {
      throw new ArithmeticException("The exponent must be natural number");
    } else if (base.compareTo(BigInteger.ONE) == 0 || exponent.compareTo(BigInteger.ZERO) == 0) {
      result = BigInteger.ONE;
      // } else if (exponent.compareTo(BigInteger.ONE) == 0) {
      // result = base;
    } else {
      BigInteger product = base;
      for (BigInteger i = BigInteger.ONE; i.compareTo(exponent) < 0; i = i.add(BigInteger.ONE)) {
        product = product.multiply(base);
      }
      result = product;
    }

    return result;

  }

  /**
   * 引数・戻り値が整数のみの指数関数の結果を返す.
   * 
   * 引数の指数部には自然数のみしか入れることができない.
   * 
   * @param base
   *          基数部
   * @param exponent
   *          自然数の指数部
   * @return BigInteger型の値
   * @see FunctionOfTwoVariable#getDependentVariable(Number,Number)
   * @since 2007/02/02 16:41:13
   */
  public BigInteger getDependentVariable(final BigInteger base, final BigInteger exponent) {
    return Power.pow(base, exponent);
  }

}
