package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * hyperbolic tangent (双曲線正接) tanh(x) を求める.
 * 
 * @author Hiroshi Sugwara
 * @version $Id: HyperbolicTangent.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class HyperbolicTangent implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
  public HyperbolicTangent(final Precision precision) {
    this.precision = precision;
  }

  /**
   * hyperbolic tangent (双曲線正接) tanh(x) を求める 桁落ちが無い.
   * 
   * @param argument
   *          値
   * @return tanh(argument)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return HyperbolicTangent.tanh(argument, this.precision);
  }

  /**
   * hyperbolic tangent (双曲線正接) tanh(x) を求める 桁落ちが生じる. 高速だが、argument = 0
   * 付近では落ちが生じやすい。
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return tanh(argument)
   * @since 1.1
   */
  // private static BigDecimal tanhWithResidual(
  // BigDecimal argument,
  // Precision p) {
  // if (argument.compareTo(BigDecimal.ZERO) == 0) {
  // return BigDecimal.ZERO;
  // }
  //
  // BigMath math = new BigMath(p);
  // BigDecimal t = math.exp(argument.multiply(new BigDecimal("2.0")));
  // int scale = ((RoundingError) p).getScale();
  // int mode = ((RoundingError) p).getRoundingMode();
  //
  // return t.subtract(BigDecimal.ONE).divide(
  // t.add(BigDecimal.ONE),
  // scale,
  // mode);
  // }
  /**
   * hyperbolic tangent (双曲線正接) tanh(x) を求める 桁落ちが無い.
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return tanh(argument)
   * @since 1.1
   */
  private static BigDecimal tanh(final BigDecimal argument, final Precision p) {
    if (argument.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }

    final MathContext mc = p.getMathContext();
    final HyperbolicSine sinh = new HyperbolicSine(p);
    final HyperbolicCosine cosh = new HyperbolicCosine(p);

    return (sinh.getDependentVariable(argument)).divide(cosh.getDependentVariable(argument), mc);
  }
}
