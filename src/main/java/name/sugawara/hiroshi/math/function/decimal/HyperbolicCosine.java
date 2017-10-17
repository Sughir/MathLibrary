package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * hyperbolic cosine (双曲線余弦) cosh(x) を求める.
 * 
 * @author Hiroshi Sugwara
 * @version $Id: HyperbolicCosine.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class HyperbolicCosine implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 誤差.
   * 
   * {@code precision}
   * 
   * @since 2004/08/14
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
  public HyperbolicCosine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * hyperbolic cosine (双曲線余弦) cosh(x) を求める.
   * 
   * @param argument
   *          値
   * @return cosh(argument)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return HyperbolicCosine.cosh(argument, this.precision);
  }

  /**
   * hyperbolic cosine (双曲線余弦) cosh(x) を求める.
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return cosh(argument)
   * @since 1.1
   */
  private static BigDecimal cosh(final BigDecimal argument, final Precision p) {
    if (argument.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ONE;
    }

    final BigMath math = new BigMath(p);
    final BigDecimal t = math.exp(argument);

    final MathContext mc = p.getMathContext();
    return t.add(BigDecimal.ONE.divide(t, mc)).multiply(new BigDecimal("0.5"));
  }
}
