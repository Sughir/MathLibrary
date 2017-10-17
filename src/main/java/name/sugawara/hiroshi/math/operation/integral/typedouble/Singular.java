/*
 * 作成日: 2003/10/06
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * @author sugawara
 * @since 1.1
 * @version $Id: Singular.java 109 2010-06-13 04:26:48Z sugawara $
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public abstract class Singular implements Operation {

  /**
   * 積分範囲(開始位置のみ)を指定.
   * 
   * @param begin
   *          積分開始位置
   * @return 積分範囲を指定した演算オブジェクト
   * @see Integral#setInterval(Number,Number)
   * @since 1.1
   */
  public abstract Operation setInterval(final Number begin);

  /**
   * 積分する.
   * 
   * @param function
   *          関数
   * @return 従属変数
   * @see name.sugawara.hiroshi.math.operation.Operation#operate(Function)
   * @since 1.1
   */
  public abstract Number operate(final Function function);

}