package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 直角三角形の斜辺の長さを求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Hypotenuse.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class Hypotenuse implements FunctionOfTwoVariable<BigDecimal, BigDecimal, BigDecimal> {

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
   */
  public Hypotenuse(final Precision precision) {
    this.precision = precision;
  }

  /**
   * C. B. Moler and D. Morrison による方法でhypotenuseの長さをBigDecimal型で返す.<br />
   * 演算繰り返し数は単精度で2,倍精度で3でよい。
   * 
   * @param x
   *          値
   * @param y
   *          値
   * @return sqrt(x^2 + y^2)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal x, final BigDecimal y) {
    return Hypotenuse.hypot2(x, y, this.precision);
  }

  /**
   * C. B. Moler and D. Morrison による方法でhypotenuseの長さをBigDecimal型で返す.<br />
   * 演算繰り返し数は単精度で2,倍精度で3でよい。
   * 
   * @param x
   *          x
   * @param y
   *          y
   * @param p
   *          誤差
   * @return sqrt(x^2 + y^2)
   * @since 1.1
   * 
   */
  private static strictfp BigDecimal hypot2(final BigDecimal x, final BigDecimal y,
      final Precision p) {
    BigDecimal t;
    BigDecimal absx = x.abs();
    BigDecimal absy = y.abs();
    if (absx.compareTo(absy) < 0) {
      t = absx;
      absx = absy;
      absy = t;
    }

    final BigInteger n = ((NandEpsilon) p).getN();
    // BigInteger n = BigInteger.valueOf(50);

    final MathContext mc = ((NandEpsilon) p).getMathContext();

    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal four = new BigDecimal("4.0");

    if (absy.compareTo(BigDecimal.ZERO) == 0) {
      return absx;
    }
    for (BigInteger i = BigInteger.ZERO; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
      t = absy.divide(absx, mc);
      t = t.multiply(t);
      t = t.divide(t.add(four), mc);
      absx = absx.add(t.multiply(absx).multiply(two));
      absy = absy.multiply(t);
    }
    return absx;
  }
}
