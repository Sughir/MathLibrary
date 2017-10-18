package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 立方根をBigDecimal型で求める.
 *
 * @author Hiroshi Sugawara
 * @version $Id: CubeRoot.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public class CubeRoot implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 誤差. {@code precision}
   *
   * @since 2004/08/14
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
  public CubeRoot(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 立方根を実数の範囲内でBigDecimal型で求める.
   *
   * 値が負のとき、正のときの結果に符号をつけたものが返される.
   *
   * このメソッドでは虚数解を返さない.
   *
   * @param argument
   *          値
   * @return aの立方根のうち実数解を返す
   */
  @Override
  public final BigDecimal getDependentVariable(final BigDecimal argument) {
    return CubeRoot.cubeRoot(argument, this.precision);
  }

  /**
   * 立方根を実数の範囲内でBigDecimal型で求める. <br />
   * 値が負のとき、正のときの結果に符号をつけたものが返される。 このメソッドでは虚数解を返さない。
   *
   * @param a
   *          値
   * @param p
   *          誤差
   * @return aの立方根のうち実数解を返す
   */
  private static BigDecimal cubeRoot(final BigDecimal a, final Precision p) {
    BigDecimal result;

    if (a.compareTo(BigDecimal.ZERO) == 0) {
      result = BigDecimal.ZERO;
    } else if (a.compareTo(BigDecimal.ONE) == 0) {
      result = BigDecimal.ONE;
    } else {

      final BigDecimal two = new BigDecimal("2.0");
      final BigDecimal three = new BigDecimal("3.0");
      BigDecimal x1;
      BigDecimal x2;
      x1 = a;
      final NandEpsilon castedP = (NandEpsilon) p;

      final BigDecimal eps = castedP.getEpsilon();
      final MathContext mc = p.getMathContext();
      x2 = (two.multiply(x1).add(a.divide(x1.multiply(x1), mc))).divide(three, mc);

      while (x2.subtract(x1).abs().compareTo(eps) == 1) {
        x1 = x2;
        x2 = (two.multiply(x1).add(a.divide(x1.multiply(x1), mc))).divide(three, mc);
      }

      result = x2;
    }
    return result;
  }
}
