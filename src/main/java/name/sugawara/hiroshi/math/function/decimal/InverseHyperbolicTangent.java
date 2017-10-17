package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * Inverse hyperbolic tangent (逆双曲線正弦) arctanh(x) を求める. <br />
 * 
 * @author Hiroshi Sugwara
 * @version $Id: InverseHyperbolicTangent.java,v 1.1 2004/05/06 10:21:40
 *          sugawara Exp $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class InverseHyperbolicTangent implements
    FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * コンストラクタ.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/03
   */
  public InverseHyperbolicTangent(final Precision precision) {
    this.precision = precision;
  }

  /**
   * inverse hyperbolic tangent (双曲線正弦) arctanh(x) を求める.
   * 
   * <pre>
   *     -1 &lt; x &lt; 1 の範囲外のとき ArithmeticException を返す。 argument = 0
   *     付近では精度を維持するためにatanhNearZeroPoint()を使用。
   * </pre>
   * 
   * @param argument
   *          値
   * @return arctanh(argument)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {

    if (argument.abs().compareTo(new BigDecimal("0.1")) < 0) {
      return InverseHyperbolicTangent.atanhNearZeroPoint(argument, this.precision);
    } else {
      return InverseHyperbolicTangent.atanh(argument, this.precision);
    }

  }

  /**
   * inverse hyperbolic tangent (双曲線正弦) arctanh(x) を求める.
   * 
   * <pre>
   *    -1 &lt; x &lt; 1 の範囲外のとき ArithmeticException を返す.
   * </pre>
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return arctanh(argument)
   * @since 1.1
   */
  private static BigDecimal atanh(final BigDecimal argument, final Precision p) {
    final BigDecimal one = BigDecimal.ONE;
    if (argument.abs().compareTo(one) >= 0) {
      throw new ArithmeticException("This method InverseHyperbolictangent.atanh() cannot operate"
          + " the complex numbers or the infinite numbers. The value of "
          + "an argument must be the range of  -1 < a < 1 .");
    } else if (argument.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }
    final MathContext mc = p.getMathContext();
    final BigMath math = new BigMath(p);

    return new BigDecimal("0.5").multiply(math.log(one.add(argument).divide(one.subtract(argument),
        mc)));
  }

  /**
   * inverse hyperbolic tangent (双曲線正弦) arctanh(x) を求める.
   * 
   * <pre>
   *    -1 &lt; x &lt; 1 の範囲外のとき ArithmeticException を返す.
   *    argument = 0 付近で精度を維持するために使用.
   * </pre>
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return arctanh(argument)
   * @since 1.1
   */
  private static BigDecimal atanhNearZeroPoint(final BigDecimal argument, final Precision p) {
    final BigDecimal one = BigDecimal.ONE;
    if (argument.abs().compareTo(one) == 1 || argument.abs().compareTo(one) == 0) {
      throw new ArithmeticException("This method InverseHyperbolicTangent.atanh()"
          + " cannot operate the complex numbers or the "
          + "infinite numbers. The value of an argument " + "must be the range of  -1 < a < 1 .");
    } else if (argument.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }

    BigDecimal term = BigDecimal.ZERO;
    final BigInteger two = BigInteger.valueOf(2);
    BigDecimal numerator;
    BigInteger denominator;
    final BigInteger n = ((NandEpsilon) p).getN();
    final MathContext mc = p.getMathContext();
    final BigMath math = new BigMath(p);

    for (BigInteger i = BigInteger.ZERO; i.compareTo(n) == -1; i = i.add(BigInteger.ONE)) {
      numerator = math.pow(argument, two.multiply(i).add(BigInteger.ONE));
      denominator = two.multiply(i).add(BigInteger.ONE);
      term = term.add(numerator.divide(new BigDecimal(denominator), mc));
    }
    return term;
  }
}
