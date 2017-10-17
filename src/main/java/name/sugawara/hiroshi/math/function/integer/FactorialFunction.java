package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;

/**
 * 階乗関数の値を求める (BigIntegerを使用).
 * 
 * @author Hiroshi Sugawara
 * @version $Id: FactorialFunction.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class FactorialFunction implements
    FunctionOfTwoVariable<BigInteger, BigInteger, BigInteger> {

  /**
   * 階乗関数(自然数のみ) a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す.
   * 
   * @param a
   *          自然数
   * @param x
   *          自然数
   * @return a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す
   */
  public static BigInteger factorial(final BigInteger a, final BigInteger x) {

    BigInteger result;
    // a < 0, x < 0 のそき
    if (a.compareTo(BigInteger.ZERO) < 0 || x.compareTo(BigInteger.ZERO) < 0) {
      throw new ArithmeticException("The argument must be a nutural number.");

      // a < x のとき
    } else if (a.compareTo(x) < 0) {
      result = BigInteger.ZERO;

      // x = 0 のとき
    } else if (x.compareTo(BigInteger.ZERO) == 0) {
      result = BigInteger.ONE;

      // x = 1 のとき
    } else if (x.compareTo(BigInteger.ONE) == 0) {
      result = a;
    } else {
      BigInteger product = BigInteger.ONE;
      for (BigInteger i = BigInteger.ZERO; i.compareTo(x) < 0 || i.compareTo(x) != 0; i = i
          .add(BigInteger.ONE)) {

        product = product.multiply(a.subtract(i));

      }
      result = product;
    }
    return result;
  }

  /**
   * 階乗関数(自然数のみ) a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す.
   * 
   * @param independent1
   *          自然数a
   * @param independent2
   *          自然数x
   * @return a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す
   * @see FunctionOfTwoVariable#getDependentVariable(Number,Number)
   * @since 2007/02/01 15:41:48
   */
  public BigInteger getDependentVariable(final BigInteger independent1,
      final BigInteger independent2) {
    return FactorialFunction.factorial(independent1, independent2);

  }
}
