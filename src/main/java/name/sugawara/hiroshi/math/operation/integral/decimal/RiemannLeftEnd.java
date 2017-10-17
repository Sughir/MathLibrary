package name.sugawara.hiroshi.math.operation.integral.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;
import name.sugawara.hiroshi.math.operation.integral.IntegralException;
import name.sugawara.hiroshi.math.precision.Precision;
import name.sugawara.hiroshi.math.precision.RoundingError;

/**
 * リーマン積分.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: RiemannLeftEnd.java 109 2010-06-13 04:26:48Z sugawara $ Created Date : 2005/07/24
 *          19:40:38
 */
public final class RiemannLeftEnd implements Integral {

  /**
   * 積分開始位置.
   * 
   * @since 2006/08/24 23:56:03
   */
  private BigDecimal begin;

  /**
   * 積分終了位置.
   * 
   * @since 2006/08/24 23:56:16
   */
  private BigDecimal end;

  /**
   * 分割数.
   * 
   * @since 2006/08/24 23:56:22
   */
  private BigInteger numberOfPartitions;

  /**
   * 誤差.
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
   */
  public RiemannLeftEnd(final BigInteger numberOfPartitions, final Precision precision) {
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
   * @see java.lang.Object#clone()
   * @since 2004/02/05 19:34:20
   * @return クローン
   */
  @Override
  protected Object clone() {
    try {
      super.clone();
    } catch (CloneNotSupportedException e) {

      System.out.println(e);
      e.printStackTrace();
    }
    BigInteger numberOfPartitions2 = this.numberOfPartitions;
    RiemannLeftEnd r = new RiemannLeftEnd(numberOfPartitions2, this.precision);
    r.begin = this.begin;
    r.end = this.begin;
    return r;
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
   * @see Integral#setInterval(BigDecimal,BigDecimal)
   */

  public Operation setInterval(final BigDecimal begin, final BigDecimal end) {
    RiemannLeftEnd r = new RiemannLeftEnd(this.numberOfPartitions, this.precision);
    r.begin = begin;
    r.end = end;
    return r;
  }

  /**
   * 積分区間、分割数に応じて積分する.
   * 
   * @since 1.1
   * @param function
   *          関数
   * @return 積分区間、分割数に応じた積分値を返す。
   */
  public BigDecimal operate(final FunctionOfSingleVariable<BigDecimal, BigDecimal> function) {
    int sign = 1;

    BigDecimal a, b;
    if (this.begin.compareTo(this.end) == 0) {
      return BigDecimal.ZERO;
    } else if (this.begin.compareTo(this.end) > 0) {
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
    BigDecimal d = BigDecimal.ZERO;
    BigDecimal result = null;

    BigInteger limit = this.numberOfPartitions.subtract(BigInteger.ONE);
    BigDecimal integratedValue = BigDecimal.ZERO;

    try {
      for (BigInteger i = BigInteger.ONE; i.compareTo(limit) == -1; i.add(BigInteger.ONE)) {
        d = a.add(smallInterval.multiply(new BigDecimal(i)));
        integratedValue = (f.getDependentVariable(d)).multiply(smallInterval).add(integratedValue);
      }
      result = integratedValue.multiply(new BigDecimal(sign));
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

}
