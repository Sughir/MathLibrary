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
 * @version $Id: ComplexTrapezoid.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2003/10/10 19:25:35
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public class ComplexTrapezoid implements ComplexIntegral {

  /**
   * @uml.property name="begin"
   */
  private Double     begin;

  /**
   * @uml.property name="end"
   */
  private Double     end;

  /**
   * @uml.property name="numberOfPartitions" readOnly="true"
   */
  private final long numberOfPartitions;

  /**
   * 分割数をセットする.
   * 
   * @param numberOfPartitions
   *          分割数
   * @since 2003/10/10 19:25:35
   */
  public ComplexTrapezoid(final long numberOfPartitions) {
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
   * 積分区間をセットする.
   * 
   * @param begin
   *          始点
   * @param end
   *          終点
   * @return 積分範囲を指定した演算オブジェクト
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Integral#setInterval(Number,
   *      Number)
   * @since 2003/10/10 19:25:35
   */
  public Operation setInterval(final Number begin, final Number end) {

    try {
      if (begin.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException(
            "The type of independent1 must be java.lang.Double or DoubleComplex.");
      } else if (end.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of independent1 must be java.lang.Double.");
      }
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    ComplexTrapezoid c = new ComplexTrapezoid(this.numberOfPartitions);
    c.begin = (Double) begin;
    c.end = (Double) end;
    return c;
  }

  /**
   * 被積分関数を指定し積分する.
   * 
   * @param function
   *          関数
   * @return 演算結果
   * @see name.sugawara.hiroshi.math.operation.Operation#operate(name.sugawara.hiroshi.math.function.object.Function)
   * @since 2003/10/10 19:25:35
   */
  public Number operate(final Function function) {

    // double integratedValue = 0.0d;
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
    DoubleComplex s = new DoubleComplex();
    double x0 = a;

    DoubleComplex y1;
    // , sTemp;
    DoubleComplex y0 = ((DoubleComplex) f.getDependentVariable(new Double(x0)));
    double d;

    for (long i = 1; i <= this.numberOfPartitions; i++) {
      d = a + i * smallInterval;

      y1 = ((DoubleComplex) f.getDependentVariable(new Double(d)));

      s = new DoubleComplex(a, 0.0d).add((y0.add(y1)).multiply(
          new DoubleComplex(smallInterval, 0.0d)).multiply(new DoubleComplex(0.5d, 0.0d)));
      y0 = y1;
    }

    return s.multiply(new DoubleComplex(sign, 0.0d));
  }

  /**
   * 分割数を取得する.
   * 
   * @return 分割数
   * @since 2004/01/26 17:22:56
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
   * 積分終了位置を取得する.
   * 
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   */
  public double getEnd() {
    return this.end.doubleValue();
  }

}
