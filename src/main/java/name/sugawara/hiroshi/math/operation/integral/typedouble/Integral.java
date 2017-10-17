package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * 数値積分.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Integral.java 109 2010-06-13 04:26:48Z sugawara $
 * @param <T1>
 *          第一引数の型
 * @param <T2>
 *          第二引数の型
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="interface"
 */

public interface Integral<T1 extends Number, T2 extends Number>
    extends Operation<FunctionOfSingleVariable, Number>, Cloneable {

  /**
   * 積分区間[begin, end]をセットする.
   * 
   * @since 1.1
   * @param begin
   *          積分区間の開始位置
   * @param end
   *          積分区間の終了位置
   * @return 積分区間を Operation オブジェクトに渡す
   * @throws CloneNotSupportedException
   *           クローンがサポートされていないとき
   * @since 2004/08/03
   */
  Operation setInterval(final T1 begin, final T2 end) throws CloneNotSupportedException;

  /**
   * 分割数を取得する.
   * 
   * @return 分割数
   * @since 2004/01/26 17:22:56
   * 
   * @uml.property name="numberOfPartitions"
   */
  Number getNumberOfPartitions();

  /**
   * 積分開始位置を取得する.
   * 
   * @return 積分開始位置
   * @since 2004/01/26 17:25:21
   * 
   * @uml.property name="begin"
   */
  T1 getBegin();

  /**
   * 積分終了位置を取得する.
   * 
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   * 
   * @uml.property name="end"
   */
  T2 getEnd();

}
