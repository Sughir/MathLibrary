package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * Fibonacci sequence (フィボナッチ数列) を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Fibonacci.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Fibonacci implements FunctionOfSingleVariable<BigInteger, BigInteger> {

  /**
   * Fibonacci数列をlong型で求める. 結果はBigInteger型.
   * 
   * @param n
   *          第n項
   * @return 第n項のFibonacci数列を返す
   * @since 1.1
   */
  public static BigInteger fibonacci(final long n) {
    return Fibonacci.fibonacci(BigInteger.valueOf(n));
  }

  /**
   * Finonacci数列をBigInteger型で求める.
   * 
   * <pre>
   *        演算に使用する記憶領域が十分であれば、
   *        BigInteger型を使用することにより
   *        long型演算で生じたオーバーフローによる影響が全く無くなる。
   * </pre>
   * 
   * @param n
   *          第n項 (0より大きな自然数)
   * @return 第n項のFibonacci数列を返す
   * @since 1.1
   */
  public static BigInteger fibonacci(final BigInteger n) {
    if (n.compareTo(ONE) <= 0) {
      return ONE;
    }
    BigInteger a = ONE, a1, b = ONE, b1, c = ZERO, c1, x = ONE, x1, y = ZERO, y1, number;

    number = n.subtract(ONE);
    while (number.compareTo(ZERO) > 0) {
      if (number.mod(BigInteger.valueOf(2)).compareTo(ONE) == 0) {
        x1 = x;
        y1 = y;
        x = (a.multiply(x1)).add(b.multiply(y1));
        y = (b.multiply(x1)).add(c.multiply(y1));
      }
      number = number.divide(BigInteger.valueOf(2));
      a1 = a;
      b1 = b;
      c1 = c;
      a = (a1.multiply(a1)).add(b1.multiply(b1));
      b = b1.multiply(a1.add(c1));
      c = (b1.multiply(b1)).add(c1.multiply(c1));
    }
    return x;
  }

  /**
   * フィボナッチ数列のn番目の値を求める.
   * 
   * @param independent
   *          自然数
   * @return フィボナッチ数列のindependent番目の値
   * @see FunctionOfSingleVariable#getDependentVariable(Number)
   * @since 2007/02/01 19:12:15
   */
  public BigInteger getDependentVariable(final BigInteger independent) {
    return Fibonacci.fibonacci(independent);
  }
}
