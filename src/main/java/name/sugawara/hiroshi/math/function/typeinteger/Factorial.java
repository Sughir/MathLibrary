package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * 階乗を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Factorial.java 83 2005-09-12 16:18:56Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class Factorial {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:20:09
   */
  private Factorial() {
    // Empty block
  }

  /**
   * 引数の階乗を格納.
   * 
   * @param number
   *          乗算する回数
   * @return n! を返す
   */
  public static long factorial(final long number) {
    // numberが0と等しい場合
    if (number == 0) {
      return 1;
      // numberが0より小さい場合
    } else if (number < 0) {
      throw new Error(
          "The value which you can input is only 0 or a positive number.");
    } else {
      return number * Factorial.factorial(number - 1);
    }
  }

}
