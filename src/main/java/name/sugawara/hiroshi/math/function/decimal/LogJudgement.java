package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

/**
 * 対数関数値判定.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: LogJudgement.java 98 2006-12-03 10:41:56Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class LogJudgement {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:09:41
   */
  private LogJudgement() {
    // Empty block
  }

  /**
   * 対数の値が無限大,非数値になるかどうかを判定.
   * 
   * 無限大、非数値になるような値が設定されている場合、ArithmeticExceptionをスローする
   * 
   * @param a
   *          BigDecimal型の値
   * @since 1.1
   */
  public static void judgeOfNegativeNumber(final BigDecimal a) {
    if (a.compareTo(BigDecimal.ZERO) == 0) {
      throw new ArithmeticException("The value of logarithm is the negative infinite.");
    } else if (a.compareTo(BigDecimal.ZERO) < 0) {
      throw new ArithmeticException("The value of logarithm is the nonnumeral(NaN).");
    }
  }
}
