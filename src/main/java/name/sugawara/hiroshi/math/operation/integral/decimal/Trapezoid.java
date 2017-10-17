/*
 * Created on 2004/03/02
 */
package name.sugawara.hiroshi.math.operation.integral.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;
import name.sugawara.hiroshi.math.operation.integral.IntegralException;
import name.sugawara.hiroshi.math.precision.Precision;
import name.sugawara.hiroshi.math.precision.RoundingError;

/**
 * 台形公式.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Trapezoid.java 109 2010-06-13 04:26:48Z sugawara $ Created Date : 2005/07/24
 *          19:40:39
 */
public final strictfp class Trapezoid implements Integral, Cloneable {

  /**
   * 積分開始位置.
   * 
   * @since 2005/07/11 22:46:24
   */
  private BigDecimal begin;

  /**
   * 積分終了位置.
   * 
   * @since 2005/07/11 22:46:36
   */
  private BigDecimal end;

  /**
   * 分割数.
   * 
   * @since 2005/07/11 22:46:46
   */
  private BigInteger numberOfPartitions;

  /**
   * 制度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision  precision;

  /**
   * 分割数をセットする.
   * 
   * @param numberOfPartitions
   *          分割数
   * @param precision
   *          誤差
   * @since 1.1
   */
  public Trapezoid(final BigInteger numberOfPartitions, final Precision precision) {
    try {
      if (numberOfPartitions.compareTo(BigInteger.valueOf(1)) == -1) {
        throw new IntegralException("The number of partitions must be grater than one.");
      }
    } catch (IntegralException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.numberOfPartitions = numberOfPartitions;
    this.precision = precision;
  }

  /**
   * 積分区間をセットする.
   * 
   * @param begin
   *          始点
   * @param end
   *          終点
   * @return 演算子.
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Integral#setInterval(Number,
   *      Number)
   * @since 1.1
   */
  public Operation setInterval(final BigDecimal begin, final BigDecimal end) {

    Trapezoid r = new Trapezoid(this.numberOfPartitions, this.precision);
    r.begin = begin;
    r.end = end;
    return r;
  }

  /**
   * 被積分関数を指定し積分する.
   * 
   * @param function
   *          関数
   * @return 演算結果
   * @see Operation#operate(name.sugawara.hiroshi.math.function.object.Function)
   * @since 1.1
   */
  public BigDecimal operate(final FunctionOfSingleVariable<BigDecimal, BigDecimal> function) {

    BigDecimal integratedValue = BigDecimal.ZERO;
    int sign = 1;

    BigDecimal a, b;
    if (this.begin.compareTo(this.end) == 0) {
      return BigDecimal.ZERO;
    } else if (this.begin.compareTo(this.end) == 1) {
      a = this.end;
      b = this.begin;
      sign = -1;
    } else {
      a = this.begin;
      b = this.end;
      sign = 1;
    }

    BigDecimal smallInterval = (b.subtract(a)).divide(new BigDecimal(this.numberOfPartitions),
        ((RoundingError) this.precision).getScale(), ((RoundingError) this.precision)
            .getRoundingMode());
    FunctionOfSingleVariable<BigDecimal, BigDecimal> f = function;
    BigDecimal d;

    BigDecimal x0 = a;

    BigDecimal y1;
    BigDecimal y0 = f.getDependentVariable(x0);

    BigDecimal s = BigDecimal.ZERO;

    for (BigInteger i = BigInteger.ONE; i.compareTo(this.numberOfPartitions) == -1
        || i.compareTo(this.numberOfPartitions) == 0; i = i.add(BigInteger.ONE)) {
      d = smallInterval.multiply(new BigDecimal(i)).add(a);
      y0 = (f.getDependentVariable(d)).multiply(smallInterval).add(integratedValue);
      y1 = f.getDependentVariable(d);

      s = (y0.add(y1)).multiply(smallInterval).multiply(new BigDecimal("0.5d")).add(s);
    }

    return integratedValue.multiply(new BigDecimal(sign));

  }

  /**
   * 分割数を取得する.
   * 
   * @return 分割数
   * @since 2004/01/26 17:22:56
   * @uml.property name="numberOfPartitions"
   */
  public BigInteger getNumberOfPartitions() {
    return this.numberOfPartitions;
  }

  /**
   * 積分開始位置を取得する.
   * 
   * @return 積分開始位置
   * @since 2004/01/26 17:25:21
   * @uml.property name="begin"
   */
  public BigDecimal getBegin() {
    return this.begin;
  }

  /**
   * 積分終了位置を取得する.
   * 
   * @return 積分終了位置
   * @since 2004/01/26 17:25:25
   * @uml.property name="end"
   */
  public BigDecimal getEnd() {
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
    try {
      super.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    }
    BigInteger numberOfPartitions2 = this.numberOfPartitions;
    Trapezoid t = new Trapezoid(numberOfPartitions2, this.precision);
    if (this.begin != null) {
      t.begin = this.begin;
    } else {
      t.begin = null;
    }

    if (this.end != null) {
      t.end = this.end;
    } else {
      t.end = null;
    }

    return t;
  }

}
