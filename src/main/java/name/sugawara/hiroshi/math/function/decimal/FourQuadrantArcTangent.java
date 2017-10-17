package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal で4象限アークタンジェント(4象限逆正接)関数 atan(x) を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: FourQuadrantArcTangent.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class FourQuadrantArcTangent implements
    FunctionOfTwoVariable<BigDecimal, BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 精度を指定する.
   * 
   * @param precision
   *          精度
   * @since 2004/08/03
   */
  public FourQuadrantArcTangent(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 指定された角度の4象限逆正接逆正接 (4象限アークタンジェント) を返す.
   * 
   * @param y
   *          ラジアンで表した BigDecimal型の角度
   * @param x
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の逆正接 (アークタンジェント)をBigDecimal 型で返す
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal y, final BigDecimal x) {
    return FourQuadrantArcTangent.atan2(y, x, this.precision);
  }

  /**
   * 指定された角度の4象限逆正接逆正接 (4象限アークタンジェント) を返す.
   * 
   * @param y
   *          ラジアンで表した BigDecimal型の角度
   * @param x
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 引数の逆正接 (アークタンジェント)をBigDecimal 型で返す
   * @since 1.1
   */
  private static BigDecimal atan2(final BigDecimal y, final BigDecimal x, final Precision p) {
    final Constant c = new Constant(p);
    final BigDecimal zero = BigDecimal.ZERO;
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal pi = c.pi();
    final MathContext mc = p.getMathContext();
    final BigMath math = new BigMath(p);

    BigDecimal result;

    if (x.compareTo(zero) == 0 && y.compareTo(zero) == 0) {
      result = zero;
    } else if (x.compareTo(zero) > 0 && y.compareTo(zero) == 0) {
      result = zero;
    } else if (x.compareTo(zero) == 0 && y.compareTo(zero) > 0) {
      result = pi.divide(two, mc);
    } else if (x.compareTo(zero) < 0 && y.compareTo(zero) == 0) {
      result = pi;
    } else if (x.compareTo(zero) == 0 && y.compareTo(zero) < 0) {
      result = pi.divide(two, mc).negate();
    } else if (x.compareTo(zero) > 0 && y.compareTo(zero) > 0) {
      result = math.atan(y.divide(x, mc));
    } else if (x.compareTo(zero) < 0 && y.compareTo(zero) > 0) {
      result = math.atan(y.divide(x, mc)).add(pi);
    } else if (x.compareTo(zero) < 0 && y.compareTo(zero) < 0) {
      result = math.atan(y.divide(x, mc)).subtract(pi);
    } else if (x.compareTo(zero) > 0 && y.compareTo(zero) < 0) {
      result = math.atan(y.divide(x, mc));
    } else {
      throw new Error("An unexpected error.");
    }
    return result;
  }
}
