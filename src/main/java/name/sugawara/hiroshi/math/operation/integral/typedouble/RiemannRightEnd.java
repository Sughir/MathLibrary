package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;
import name.sugawara.hiroshi.math.operation.integral.IntegralException;

/**
 * 最大和 (Right End Point) の Riemann integral(リーマン積分).<br />
 * 最大和の sectional mensuration(区分求積法)
 * 
 * @author Hiroshi Sugawara
 * @version 0.0
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public class RiemannRightEnd implements Integral, Cloneable {

  /**
   * 積分開始位置.
   * 
   * @uml.property name="begin"
   */
  private Double begin;

  /**
   * 積分終了位置.
   * 
   * @uml.property name="end"
   */
  private Double end;

  /**
   * 分割数.
   * 
   * @uml.property name="numberOfPartitions"
   */
  private long   numberOfPartitions;

  /**
   * 分割数をセットする.
   * 
   * @param numberOfPartitions
   *          分割数
   */
  public RiemannRightEnd(final long numberOfPartitions) {
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
   * @return 積分区間を Operation オブジェクトに渡す
   */
  public Operation setInterval(final Number begin, final Number end) {
    try {
      if (begin.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException(
            "The type of independent1 must be java.lang.Double or DoubleComplex.");
      } else if (end.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of independent1 must be java.lang.Double.");
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    RiemannRightEnd r = new RiemannRightEnd(this.numberOfPartitions);
    r.begin = (Double) begin;
    r.end = (Double) end;
    return r;
  }

  /**
   * 積分区間、分割数に応じて積分する.
   * 
   * @param function
   *          関数
   * @since 1.1
   * @return 積分区間、分割数に応じた積分値を返す。
   */
  public Number operate(final Function function) {
    double integratedValue = 0.0d;
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
    double d;

    for (long i = 1; i < this.numberOfPartitions; i++) {
      d = a + i * smallInterval;
      integratedValue += f.getDependentVariable(new Double(d)).doubleValue() * smallInterval;
    }

    return new Double(sign * integratedValue);
  }

  /**
   * 分割数を取得する.
   * 
   * @return 分割数
   * @since 2004/01/26 17:22:56
   * @uml.property name="numberOfPartitions"
   */
  public Number getNumberOfPartitions() {
    return new Long(this.numberOfPartitions);
  }

  /**
   * 積分開始位置を取得する.
   * 
   * @return 積分開始位置
   * @since 2004/01/26 17:25:21
   * @uml.property name="begin"
   */
  public Number getBegin() {
    return this.begin;
  }

  /**
   * 積分終了位置を取得する.
   * 
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   * @uml.property name="end"
   */
  public Number getEnd() {
    return this.end;
  }

  /**
   * オブジェクトのコピー.
   * @return コピーされたオブジェクト
   * @see java.lang.Object#clone()
   * @since 2004/02/05 19:34:20
   */
  @Override
  protected Object clone() {
    long numberOfPartitions2 = this.numberOfPartitions;
    RiemannRightEnd r = new RiemannRightEnd(numberOfPartitions2);
    r.begin = this.begin;
    r.end = this.begin;
    return r;
  }

}
