package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * hyperbolic sine (双曲線正弦) sinh(x) を求める.
 * 
 * @author Hiroshi Sugwara
 * @version $Id: HyperbolicSine.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class HyperbolicSine implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
  public HyperbolicSine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める 桁落ちが生じない.
   * 
   * @param argument
   *          値
   * @return sinh(argument)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {

    // return HyperbolicSine.sinh(argument, this.precision);
    return HyperbolicSine.sinhWithResidual(argument, this.precision);
  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める.
   * 
   * argument = 0 付近では落ちが生じやすい. 改良.
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return sinh(argument)
   * @since 1.1
   */
  static BigDecimal sinhWithResidual(final BigDecimal argument, final Precision p) {

    final BigDecimal eps5 = new BigDecimal("0.001");

    final BigMath math = new BigMath(p);

    if (argument.abs().compareTo(eps5) > 0) {
      final BigDecimal t = math.exp(argument);
      return t.subtract(BigDecimal.ONE.divide(t, p.getMathContext())).multiply(
          new BigDecimal("0.5"));

    }

    return argument.multiply(BigDecimal.ONE.add(argument.multiply(argument).divide(
        new BigDecimal("6"), p.getMathContext())));

  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める.
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return sinh(argument)
   * @since 1.1
   */
  // @Deprecated
  // private static BigDecimal sinh(final BigDecimal argument, final Precision
  // p) {
  //
  // if (argument.compareTo(BigDecimal.ZERO) == 0) {
  // return BigDecimal.ZERO;
  // }
  //
  // BigDecimal one = BigDecimal.ONE;
  // BigDecimal term = BigDecimal.ZERO;
  // BigDecimal sq = argument.multiply(argument);
  // BigDecimal numerator = argument;
  // BigDecimal denominator = one;
  // BigDecimal dCount = one;
  //
  // BigInteger n = ((NandEpsilon) p).getN();
  // MathContext mc = p.getMathContext();
  // for (BigInteger i = BigInteger.ONE; i.compareTo(n) == -1; i = i
  // .add(BigInteger.ONE)) {
  // term = term.add(numerator.divide(denominator, mc));
  //
  // numerator = numerator.multiply(sq);
  // dCount = dCount.add(one);
  // denominator = denominator.multiply(dCount);
  // dCount = dCount.add(one);
  // denominator = denominator.multiply(dCount);
  // }
  // return term;
  // }
}
