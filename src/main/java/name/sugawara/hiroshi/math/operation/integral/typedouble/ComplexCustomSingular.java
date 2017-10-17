/*
 * 作成日: 2004/01/30
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * @author Hiroshi Sugawara
 * @version $Id: ComplexCustomSingular.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2004/01/30 23:59:50
 */
public class ComplexCustomSingular extends Singular {
  // TODO 不要になり開発中断予定
  /**
   * 積分開始区間をセットする.
   * 
   * @param begin
   *          積分開始区間
   * @return 演算子
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#setInterval(java.lang.Number)
   * @since 2004/01/30 23:59:50
   */
  @Override
  public Operation setInterval(final Number begin) {
    return null;
  }

  /**
   * 演算.
   * 
   * @param function
   *          関数
   * @return 従属変数
   * @see name.sugawara.hiroshi.math.operation.Operation#operate(name.sugawara.hiroshi.math.function.object.Function)
   * @since 2004/01/30 23:59:50
   */
  @Override
  public Number operate(final Function function) {

    return null;
  }

}
