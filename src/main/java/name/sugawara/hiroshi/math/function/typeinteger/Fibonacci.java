package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * Fibonacci sequence (フィボナッチ数列) を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Fibonacci.java 83 2005-09-12 16:18:56Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Fibonacci {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:21:35
   */
  private Fibonacci() {
    // Empty block
  }

  /**
   * Fibonacci数列をlong型で求める.
   * 
   * <pre>
   *      第93項からオーバーフローの影響により値が負となり不正確になる.
   * </pre>
   * 
   * @param n
   *          第n項
   * @return 第n項のFibonacci数列を返す
   * @since 1.1
   */
  public static long fibonacci(final long n) {
    long mutableN = n;
    long a, a1, b, b1, c, c1, x, x1, y, y1;

    a = 1;
    b = 1;
    c = 0;
    x = 1;
    y = 0;
    mutableN--;
    while (mutableN > 0) {
      if (mutableN % 2 == 1) {
        x1 = x;
        y1 = y;
        x = a * x1 + b * y1;
        y = b * x1 + c * y1;
      }
      mutableN /= 2;

      a1 = a;
      b1 = b;
      c1 = c;
      a = a1 * a1 + b1 * b1;
      b = b1 * (a1 + c1);
      c = b1 * b1 + c1 * c1;
    }
    return x;
  }
}
