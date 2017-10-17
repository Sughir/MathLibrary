package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;

/**
 * 階乗を求める (BigIntegerを使用).
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Factorial.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class Factorial implements FunctionOfSingleVariable<BigInteger, BigInteger> {

  /**
   * 引数の階乗を格納.
   * 
   * @param number
   *          乗算する回数
   * @return n! を返す
   */
  public static BigInteger factorial(final BigInteger number) {
    // numberが0と等しい場合
    if (number.compareTo(BigInteger.ZERO) == 0) {
      return BigInteger.ONE;
      // numberが0より小さい場合
    } else if (number.compareTo(BigInteger.ZERO) < 0) {
      throw new Error("The value which you can input is only 0 or a positive number.");
    } else {
      return number.multiply(Factorial.factorial(number.subtract(BigInteger.ONE)));
    }
  }

  /**
   * 引数の階乗を格納.
   * 
   * 
   * 
   * @param number
   *          乗算する回数をlong型で代入
   * @return n!を返す。
   */
  public static BigInteger factorial(final long number) {
    return Factorial.factorial(new BigInteger(Long.toString(number)));
  }

  /**
   * 演算.
   * @param independent BigInteger型の自然数
   * @return 演算結果
   * @see FunctionOfSingleVariable#getDependentVariable(Number)
   * @since 2006/12/11 14:49:30
   */
  public BigInteger getDependentVariable(final BigInteger independent) {
    return Factorial.factorial(independent);
  }

}
