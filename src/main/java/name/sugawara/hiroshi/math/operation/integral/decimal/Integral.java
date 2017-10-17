package name.sugawara.hiroshi.math.operation.integral.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * BIgDecimal型対応数値積分.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Integral.java 83 2006-02-22 15:28:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="interface"
 */

public interface Integral extends
    Operation<FunctionOfSingleVariable<BigDecimal, BigDecimal>, BigDecimal>, Cloneable {

  /**
   * 積分区間[begin, end]をセットする.
   * 
   * @since 1.1
   * @param begin
   *          積分区間の開始位置
   * @param end
   *          積分区間の終了位置
   * @return 積分区間を Operation オブジェクトに渡す
   * @since 2004/08/03
   */
  Operation setInterval(final BigDecimal begin, final BigDecimal end);

  /**
   * 分割数を取得する.
   * 
   * @return 分割数
   * @since 2004/01/26 17:22:56
   * 
   * @uml.property name="numberOfPartitions"
   */
  BigInteger getNumberOfPartitions();

  /**
   * 積分開始位置を取得する.
   * 
   * @return 積分開始位置
   * @since 2004/01/26 17:25:21
   * 
   * @uml.property name="begin"
   */
  BigDecimal getBegin();

  /**
   * 積分終了位置を取得する.
   * 
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   * 
   * @uml.property name="end"
   */
  BigDecimal getEnd();

}
