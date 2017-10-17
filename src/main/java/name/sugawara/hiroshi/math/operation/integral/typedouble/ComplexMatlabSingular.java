/*
 * 作成日: 2003/10/10
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.complex.DoubleComplex;
import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.operation.Operation;
import name.sugawara.hiroshi.math.operation.integral.ComplexIntegral;
import name.sugawara.hiroshi.math.operation.integral.IntegralException;

/**
 * 「MATLABによる数値解析」、手法による特異積分.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: ComplexMatlabSingular.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2003/10/10 19:44:03
 */

public class ComplexMatlabSingular extends Singular implements Cloneable {

  /**
   */
  private ComplexIntegral integral;

  /**
   */
  private Double          begin;

  /**
   */
  private long            n;

  /**
   */
  private double          gamma;

  /**
   */
  private double          eps;

  /**
   */
  private boolean         minus;                       // beginの値がマイナスかどうか。

  /**
   */
  private DoubleComplex   result = new DoubleComplex();

  /**
   * @param integral
   *          積分に使用するアルゴリズム
   * @param n
   *          積分範囲に影響する倍数
   * @param gamma
   *          積分範囲の終点の初期値
   * @param eps
   *          積分範囲を打ち切る値
   * @since 2003/10/17 17:33:20
   */
  public ComplexMatlabSingular(final ComplexIntegral integral, final long n, final double gamma,
      final double eps) {

    this.integral = integral;
    this.n = n;

    try {

      // EPSは0より大きい値でなければならない。
      if (eps <= 0) {
        throw new IntegralException("eps must be greater than zero.");
      }

      // EPSはgammaより小さい値でなければならない。
      if (eps > Math.abs(gamma)) {
        throw new IntegralException("eps must be less than gamma.");
      }

      this.eps = eps;

      // gammaは0より小さい値にはさせないでおく。
      if (gamma <= 0.0d) {
        throw new IntegralException("This gamma must be The Natural Number.");
      }

      this.gamma = gamma;
    } catch (IntegralException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 積分区間をセットする(開始位置のみ).
   * 
   * @param begin
   *          積分開始位置
   * @return 演算子
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#setInterval(java.lang.Number)
   * @since 2003/10/10 19:44:03
   */
  @Override
  public Operation setInterval(final Number begin) {

    ComplexMatlabSingular o = null;
    try {
      if (begin.getClass() != Class.forName("java.lang.Double")) {
        throw new ClassCastException("The type of begin must be java.lang.Double.");
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (begin.doubleValue() < 0) {
      this.minus = true;

      this.begin = new Double(Math.abs(begin.doubleValue()));
    } else {
      this.begin = (Double) begin;
    }

    o = new ComplexMatlabSingular(this.integral, this.n, this.gamma, this.eps);
    o.begin = (Double) begin;

    return o;
  }

  /**
   * 積分.
   * 
   * @param function
   *          被積分関数
   * @return 従属変数
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#operate(Function)
   * @since 2003/10/10 19:44:03
   */
  @Override
  public Number operate(final Function function) {
    double previousGamma, // 一変更前のgammaの値
    nextGamma, // 次のgammaの値
    maxGamma; // 条件を満たせるgammaの限界値

    // DoubleComplex a, b, c;
    Pack i1;
    nextGamma = this.gamma;
    boolean direction = true;
    // gamma値移動方向 true increasing( -> ), false decreasing( <- )

    try {

      i1 = integral(this.n, nextGamma, function);

      // 初期化
      do {
        if (i1.getA().compareTo(i1.getB()) == 1 && i1.getB().compareTo(i1.getC()) == 1) {
          direction = false;
          previousGamma = nextGamma;
          maxGamma = previousGamma;
          nextGamma -= Math.abs(this.begin.doubleValue() - nextGamma) / 2;
          i1 = integral(this.n, nextGamma, function);
          break;
        } else if (i1.getA().compareTo(i1.getB()) == 0 && i1.getB().compareTo(i1.getC()) == 0) {
          throw new ArithmeticException("Unexpected Exception.");
        } else {
          direction = true;
          previousGamma = nextGamma;
          nextGamma *= 2;
          maxGamma = nextGamma;
          i1 = integral(this.n, nextGamma, function);
        }

        // if (nextGamma > 1 / eps) {
        // //関数が減衰傾向にならず積分範囲が増大し、調整できないとき
        // throw new ArithmeticException(" eps. Unexpected Exception.");
        // } else

        if (Double.isInfinite(nextGamma)) {
          // 関数が減衰傾向にならず積分範囲が増大し、調整できないとき
          throw new ArithmeticException("Infinite. Unexpected Exception.");

        }

      } while (true);

      maxGamma = nextGamma;

      i1 = integral(this.n, nextGamma, function);
      double temp;

      do {
        if (i1.getA().compareTo(i1.getB()) == 1 && i1.getB().compareTo(i1.getC()) == 1) { // 減少方向に調整許可

          previousGamma = nextGamma;
          maxGamma = previousGamma;

          nextGamma -= Math.abs(previousGamma - nextGamma) / 2;
          i1 = integral(this.n, nextGamma, function);
          // } else if (
          // i1.getA().compareTo(i1.getB()) == 0
          // && i1.getB().compareTo(i1.getC()) == 0) {
          // throw new ArithmeticException("Unexpected Exception.");

        } else { // 増加方向へ調整要求

          // 以前もgammaが増加方向(右)へ進んでいたとき
          // if (direction == true) {
          temp = nextGamma;
          nextGamma += Math.abs(maxGamma - nextGamma) / 2;
          previousGamma = temp;
          i1 = integral(this.n, nextGamma, function);

          // 以前は減少方向だったとき
          // } else {
          //
          // temp = nextGamma;
          // nextGamma += Math.abs(previousGamma - nextGamma) / 2;
          // previousGamma = temp;
          // i1 = integral(this.n, nextGamma, function);
          // }
        }

      } while (this.eps < (previousGamma - nextGamma) / 2);

      i1 = integral(this.n, maxGamma, function);

      this.result = i1.getA().add(i1.getB()).add(i1.getC()).add(this.result);

    } catch (ClassCastException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return this.result;

  }

  /**
   * @author Hiroshi Sugawara
   * @version $Id: ComplexMatlabSingular.java 109 2010-06-13 04:26:48Z sugawara $ Created Date :
   *          2005/07/24　19:40:40
   */
  private class Pack {
    /**
     * a.
     * 
     * @since 2007/02/22　13:42:16
     */
    private DoubleComplex a;

    /**
     * b.
     * 
     * @since 2007/02/22　13:42:25
     */
    private DoubleComplex b;

    /**
     * c.
     * 
     * @since 2007/02/22　13:42:28
     */
    private DoubleComplex c;

    /**
     * abc.
     * 
     * @param a
     *          a
     * @param b
     *          b
     * @param c
     *          c
     * @since 2004/08/03
     */
    public Pack(final DoubleComplex a, final DoubleComplex b, final DoubleComplex c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    /**
     * @return 点aの値
     * @since 2004/08/03
     * @uml.property name="a"
     */
    public DoubleComplex getA() {
      return this.a;
    }

    /**
     * @return 点bの値
     * @since 2004/08/03
     * @uml.property name="b"
     */
    public DoubleComplex getB() {
      return this.b;
    }

    /**
     * @return 点cの値
     * @since 2004/08/03
     * @uml.property name="c"
     */
    public DoubleComplex getC() {
      return this.b;
    }
  }

  /**
   * @param n
   *          2番目、3番目の積分範囲の倍数
   * @param gamma
   *          最左端の積分範囲の終端
   * @param function
   *          関数
   * @return 倍数、積分範囲の終端、関数をまとめて取得
   * @since 2003/10/14 21:27:36
   */
  private Pack integral(final long n, final double gamma, final Function function) {

    DoubleComplex a, b, c;
    a = new DoubleComplex();
    b = new DoubleComplex();
    c = new DoubleComplex();

    if (this.minus) {
      this.result = ((DoubleComplex) this.integral.setInterval(new Double(0.0d), this.begin)
          .operate(function)).negate().add(this.result);
      this.result = ((DoubleComplex) this.integral.setInterval(new Double(0.0d), new Double(gamma))
          .operate(function)).add(this.result);
    }

    try {
      // gamma = begin.doubleValue();
      a = (DoubleComplex) this.integral.setInterval(this.begin, new Double(gamma))
          .operate(function);
      b = (DoubleComplex) (this.integral.setInterval(new Double(gamma), new Double(n * gamma))
          .operate(function));
      c = (DoubleComplex) (this.integral.setInterval(new Double(n * gamma), new Double(n * n
          * gamma)).operate(function));
    } catch (ClassCastException e) {
      e.printStackTrace();
      // } catch (CloneNotSupportedException e) {
      // e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new Pack(a, b, c);
  }

  // public boolean equals(Object obj) {
  // if (this == obj) {
  // return true;
  // }
  //
  // if (obj != null && getClass() == obj.getClass()) {
  // ComplexMatlabSingular c = (ComplexMatlabSingular) obj;
  // if (this.begin.equals(c.begin)
  // && this.integral.equals(c.integral)
  // && this.n == c.n
  // && this.gamma == c.gamma
  // && this.eps == c.eps
  // && this.minus == c.minus) {
  // return true;
  // }
  // }
  // return false;
  //
  // }

}