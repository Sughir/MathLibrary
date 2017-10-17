package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * inverse hyperbolic cosine (逆双曲線余弦) arccosh(x) を求める.
 * 
 * @author Hiroshi Sugwara
 * @version $Id: InverseHyperbolicCosine.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class InverseHyperbolicCosine implements
    FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 精度を指定して関数オブジェクトを生成.
   * 
   * @param precision
   *          精度
   * @since 2004/08/03
   */
  public InverseHyperbolicCosine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める. 桁落ちが生じない.
   * 
   * @param argument
   *          値
   * @return sinh(argument)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return InverseHyperbolicCosine.acosh(argument, this.precision);
  }

  /**
   * inverse hyperbolic cosine (双曲線余弦) arccosh(x) を求める.
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return arccosh(argument)
   * @since 1.1
   */
  private static BigDecimal acosh(final BigDecimal argument, final Precision p) {
    if (argument.compareTo(BigDecimal.ONE) == -1) {
      throw new ArithmeticException("This method InverseHyperbolicCosine.acosh()"
          + " cannot operate the complex numbers. The value"
          + " of an argument must be the range of  a >= 1 .");
    } else if (argument.compareTo(BigDecimal.ONE) == 0) {
      return BigDecimal.ZERO;
    }

    final BigDecimal x2subOne = argument.multiply(argument).subtract(BigDecimal.ONE);
    final BigMath math = new BigMath(p);
    return math.log(argument.add(math.sqrt(x2subOne)));
  }
}
