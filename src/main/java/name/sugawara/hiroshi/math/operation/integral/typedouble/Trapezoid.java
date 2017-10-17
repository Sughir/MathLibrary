/*
 * Created on 2003/06/24
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;
import name.sugawara.hiroshi.math.operation.integral.IntegralException;

/**
 * 台形公式.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Trapezoid.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2004/01/31 11:34:17
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public strictfp class Trapezoid implements Integral, Cloneable {

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
   * @since 1.1
   */
  public Trapezoid(final long numberOfPartitions) {
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
   * @return 演算子
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Integral#setInterval(Number,
   *      Number)
   * @since 1.1
   */
  public Operation setInterval(final Number begin, final Number end) {

    try {
      if (begin.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of independent1 must be java.lang.Double.");
      } else if (end.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of independent2 must be java.lang.Double.");
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Trapezoid t = new Trapezoid(this.numberOfPartitions);
    t.begin = new Double(begin.doubleValue());
    t.end = new Double(end.doubleValue());
    return t;
  }

  /**
   * 被積分関数を指定し積分する.
   * 
   * @param function
   *          関数
   * @return 従属変数
   * @see name.sugawara.hiroshi.math.operation.Operation#operate(name.sugawara.hiroshi.math.function.object.Function)
   * @since 1.1
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
    double s = 0.0d, x0 = a;
    // sMinus = 0.0d,
    // sPlus = 0.0d,
    // trianglePlus = 0.0d,
    // triangleMinus = 0.0d;
    double y1; // sTemp;
    double y0 = f.getDependentVariable(new Double(x0)).doubleValue();
    double d;

    for (long i = 1; i <= this.numberOfPartitions; i++) {
      d = a + i * smallInterval;

      y1 = f.getDependentVariable(new Double(d)).doubleValue();
      s += (y0 + y1) * smallInterval * 0.5d;
      y0 = y1;
    }

    /*
     * 桁落ち対策を試してみたが除算回数増加により反って誤差が増大した。 \int^30_{-20} sin(x)dx で分割数8192で 値が0.25382から50ほどにまで変化した。
     * June.26.2003
     */
    // for(long i = 1; i <= numberOfPartitions; i++){
    // y1 = f.getDependentVariable(a + i * smallInterval);
    // s += (y0 + y1) * smallInterval * 0.5d;
    //
    // // //桁落ち軽減
    // // if(y0 > 0 && y1 < 0){
    // // double ay1 = StrictMath.abs(y1);
    // // trianglePlus += smallInterval * y0 * y0 * 0.5d / (y0 + ay1);
    // // triangleMinus += smallInterval * ay1 * ay1 * 0.5d / (y0 + ay1);
    // // } else if(y0 < 0 && y1 > 0){
    // // double ay0 = StrictMath.abs(y0);
    // // triangleMinus += smallInterval * ay0 * ay0 * 0.5d / (ay0 + y1);
    // // trianglePlus += smallInterval * y1 * y1 * 0.5d / (ay0 + y1);
    // // } else if( ( sTemp = (y0 + y1) * smallInterval * 0.5d ) < 0.0d){
    //
    // if( ( sTemp = (y0 + y1) * smallInterval * 0.5d ) < 0.0d){
    // sMinus += sTemp;
    // } else {
    // sPlus += sTemp;
    // }
    //
    // s += (y0 + y1) * smallInterval * 0.5d;
    // y0 = y1;
    // }
    // return sign * ( ( sPlus + trianglePlus ) - ( sMinus + triangleMinus ) );
    // return sign * ( sPlus - sMinus );
    return new Double(sign * s);
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
   * 
   * @return コピー
   * @see java.lang.Object#clone()
   * @since 2004/01/31 11:34:31
   */
  @Override
  public Object clone() {
    long numberOfPartitions2 = this.numberOfPartitions;
    Trapezoid t = new Trapezoid(numberOfPartitions2);
    if (this.begin != null) {
      t.begin = new Double(this.begin.doubleValue());
    } else {
      t.begin = null;
    }

    if (this.end != null) {
      t.end = new Double(this.end.doubleValue());
    } else {
      t.end = null;
    }

    return t;
  }

}
