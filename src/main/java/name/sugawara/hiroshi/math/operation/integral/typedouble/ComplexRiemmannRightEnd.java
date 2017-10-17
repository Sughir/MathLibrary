/*
 * 作成日: 2003/10/10
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.complex.DoubleComplex;
import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;
import name.sugawara.hiroshi.math.operation.integral.ComplexIntegral;
import name.sugawara.hiroshi.math.operation.integral.IntegralException;

/**
 * @author Hiroshi Sugawara
 * @version $Id: ComplexRiemmannRightEnd.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2003/10/10 14:37:46
 * @uml.stereotype name="tagged" isDefined="true"
 */

public class ComplexRiemmannRightEnd implements ComplexIntegral {

  /**
   * @uml.property name="begin"
   */
  private Double begin;

  /**
   * @uml.property name="end"
   */
  private Double end;

  /**
   * @uml.property name="numberOfPartitions"
   */
  private long   numberOfPartitions;

  /**
   * 分割数をセットする.
   * 
   * @param numberOfPartitions
   *          分割数
   * @since 2003/10/10 14:37:46
   */
  public ComplexRiemmannRightEnd(final long numberOfPartitions) {
    try {
      if (numberOfPartitions < 1) {
        throw new IntegralException("The number of partitions must be grater than one.");
      }
    } catch (IntegralException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.numberOfPartitions = numberOfPartitions;

  }

  /**
   * 積分区間[begin, end]をセットする.
   * 
   * @since 1.1
   * @param begin
   *          積分区間の開始位置
   * @param end
   *          積分区間の終了位置
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Integral#setInterval(java.lang.Number,
   *      java.lang.Number)
   * @since 2003/10/10 14:37:46
   * @return 積分区間を Operation オブジェクトに渡す
   */
  public Operation setInterval(final Number begin, final Number end) {

    try {
      if (begin.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of begin must be java.lang.Double or DoubleComplex.");
      } else if (end.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of end must be java.lang.Double.");
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    ComplexRiemmannRightEnd c = new ComplexRiemmannRightEnd(this.numberOfPartitions);
    c.begin = (Double) begin;
    c.end = (Double) end;
    return c;

  }

  /**
   * 積分区間、分割数に応じて積分する.
   * 
   * @param function
   *          関数
   * @see name.sugawara.hiroshi.math.operation.Operation#operate(name.sugawara.hiroshi.math.function.object.Function)
   * @since 2003/10/10 14:37:46
   * @return 積分区間、分割数に応じた積分値を返す。
   */
  public Number operate(final Function function) {

    int sign = 1;

    double a, b;
    if (this.begin.doubleValue() == this.end.doubleValue()) {
      return new Double(0.0d);
    } else if (this.begin.doubleValue() > this.end.doubleValue()) {
      a = this.end.doubleValue();
      b = this.begin.doubleValue();
      sign = -1;
    } else {
      a = this.begin.doubleValue();
      b = this.end.doubleValue();
      sign = 1;
    }

    double smallInterval = (b - a) / this.numberOfPartitions;
    FunctionOfSingleVariable f = (FunctionOfSingleVariable) function;
    double d = 0.0d;

    Number result = null;
    try {

      DoubleComplex integratedValue = new DoubleComplex();
      DoubleComplex si = new DoubleComplex(smallInterval, 0.0d);

      for (long i = 0; i < this.numberOfPartitions - 1; i++) {
        d = a + i * smallInterval;
        integratedValue = integratedValue.add(((DoubleComplex) f
            .getDependentVariable(new Double(d))).multiply(si));
      }
      result = integratedValue.multiply(new DoubleComplex(sign, 0.0d));
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  /**
   * 分割数を取得する.
   * 
   * @return 分割数
   * @since 2004/01/26 17:22:56
   * @uml.property name="numberOfPartitions"
   */
  public long getNumberOfPartitions() {
    return this.numberOfPartitions;
  }

  /**
   * 積分開始位置を取得する.
   * 
   * @return 積分開始位置
   * @since 2004/01/26 17:25:21
   */
  public double getBegin() {
    return this.begin.doubleValue();
  }

  /**
   * 　積分終了位置を取得する.
   * 
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   */
  public double getEnd() {
    return this.end.doubleValue();
  }

}
