/*
 * 作成日: 2003/10/23
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * 特異積分(返り値が複素数になる関数に対応。).
 * 
 * @author Hiroshi Sugawara
 * @version $Id: ComplexSingular.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2003/10/23 22:01:15
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public abstract class ComplexSingular {

  /**
   * 積分範囲(開始位置のみ)を指定.
   * 
   * @param begin
   *          積分開始位置
   * @return 積分区間をセットした積分オブジェクトを返す。
   * @see name.sugawara.hiroshi.math.operation.integral.ComplexIntegral#setInterval(java.lang.Number,
   *      java.lang.Number)
   * @since 2003/10/23 22:03:06
   */
  public abstract Operation setInterval(final Number begin);

  /**
   * 数値積分結果を返す.
   * 
   * @param function
   *          関数
   * @return 数値積分結果
   * @see name.sugawara.hiroshi.math.operation.integral.ComplexIntegral#setInterval(java.lang.Number,
   *      java.lang.Number)
   * @since 2003/10/23 22:03:39
   */
  public abstract Number operate(final Function function);
}