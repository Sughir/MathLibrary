/*
 * 作成日: 2003/10/06
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.operation.Operation;

// 作成途中

/**
 * 積分範囲が a - ∞ の数値積分.
 * 
 * @author sugawara
 * @since 1.1
 * @version $Id: OouraSingular.java 109 2010-06-13 04:26:48Z sugawara $
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public class OouraSingular extends Singular {

  /**
   * 積分開始位置.
   * 
   * @uml.property name="begin"
   */
  private double   begin;

  /**
   * 積分.
   * 
   * @uml.property name="integral"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Integral integral;

  /**
   * 積分に使用するアルゴリズムを指定する.
   * 
   * @param integral
   *          積分アルゴリズム
   * @since 1.1
   */
  public OouraSingular(final Integral integral) {
    this.integral = integral;
  }

  /**
   * 積分する.
   * 
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#operate(Function)
   * @param function
   *          被積分対象の数学関数
   * @return 積分結果
   * @since 1.1
   */
  @Override
  public Number operate(final Function function) {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

  /**
   * 積分範囲を指定する.<br />
   * 積分範囲のうち終点は∞。<br />
   * 
   * @param begin
   *          積分開始位置
   * @return このオブジェクトに積分範囲を指定された新たなオブジェクト
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#setInterval(java.lang.Number)
   */
  @Override
  public Operation setInterval(final Number begin) {

    OouraSingular o = new OouraSingular(this.integral);

    try {
      if (begin.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of independent1 must be java.lang.Double.");
      }
      o.begin = begin.doubleValue();

    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return o;

  }
}
