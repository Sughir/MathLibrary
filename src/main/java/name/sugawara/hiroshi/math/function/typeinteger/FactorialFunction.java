package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * 階乗関数の値を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: FactorialFunction.java 83 2005-09-12 16:18:56Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final strictfp class FactorialFunction {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/01/03
   */
  private FactorialFunction() {
    // コンストラクタ使用禁止
  }

  /**
   * 階乗関数(自然数のみ) a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す.
   * 
   * @param a
   *          自然数
   * @param x
   *          自然数
   * @return a^(x) = a( a - 1 )( a - 2 ) ... ( a - x + 1 )を返す
   */
  public static long factorial(final long a, final long x) {
    long lastResult;
    
    // a < 0, x < 0 のそき
    if (a < 0 || x < 0) {
      throw new ArithmeticException("The argument must be a nutural number.");

      // a < x のとき
    } else if (a < x) {
      lastResult = 0;

      // x = 0 のとき
    } else if (x == 0) {
      lastResult = 1;

      // x = 1 のとき
    } else if (x == 1) {
      lastResult = a;
    } else {
      long result = 1;
      for (long i = 0; i < x; i++) {

        result *= a - i;

      }
      lastResult = result;
    }
    return lastResult;
  }
}
