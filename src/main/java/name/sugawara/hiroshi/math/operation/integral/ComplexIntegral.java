/*
 * 作成日: 2003/10/23
 */
package name.sugawara.hiroshi.math.operation.integral;

import name.sugawara.hiroshi.math.operation.Operation;

/**
 * 積分(複素数版).
 * @author   Hiroshi Sugawara
 * @version   $Id: ComplexIntegral.java 83 2006-02-22 15:28:03Z sugawara $
 * @since   2003/10/23 21:57:28
 * @uml.stereotype   uml_id="null" isDefined="true" name="interface" 
 */

public interface ComplexIntegral extends Operation {

  /**
   * 積分区間[begin, end]をセットする.
   *
   * @since 1.1
   * @param begin
   *          積分区間の開始位置
   * @param end
   *          積分区間の終了位置
   * @return 積分区間を Operation オブジェクトに渡す
   */
  Operation setInterval(final Number begin, final Number end);

  /**
   * 分割数を取得する.
   *
   * @return 分割数
   * @since 2004/01/26 17:22:56
   */
  long getNumberOfPartitions();

  /**
   * 積分開始位置を取得する.
   *
   * @return 積分開始位置
   * @since 2004/01/26 17:25:21
   */
  double getBegin();

  /**
   * 積分終了位置を取得する.
   *
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   */
  double getEnd();

}
