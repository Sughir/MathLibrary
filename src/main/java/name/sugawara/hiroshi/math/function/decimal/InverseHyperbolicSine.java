package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import name.sugawara.hiroshi.math.function.integer.BigIntegerMath;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import static java.math.BigDecimal.ZERO;
import static java.math.BigInteger.ONE;

/**
 * inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める.
 * 
 * @author Hiroshi Sugwara
 * @version $Id: InverseHyperbolicSine.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class InverseHyperbolicSine implements
    FunctionOfSingleVariable<BigDecimal, BigDecimal> {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * 誤差を指定する.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/03
   */
  public InverseHyperbolicSine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める.
   * 
   * <pre>
   *              argument = 0 付近では桁落ち対策のため、asinhWithDivergence() を使用する.
   *              argument = 0 より離れたところでは発散するため、asinh() を使用する.
   * </pre>
   * 
   * @param argument
   *          値
   * @return asinh(argument)
   * @since 1.1
   */
  public BigDecimal getDependentVariable(final BigDecimal argument) {

    if (argument.abs().compareTo(new BigDecimal("0.1")) < 0) {
      return InverseHyperbolicSine.asinhWithDivergence(argument, this.precision);
    } else {
      return InverseHyperbolicSine.asinh(argument, this.precision);
    }

  }

  /**
   * inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める.
   * 
   * <pre>
   *              argument = 0 付近では桁落ち対策のため、asinhWithDivergence() を使用する.
   * </pre>
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return asinh(argument)
   * @since 1.1
   */
  private static BigDecimal asinh(final BigDecimal argument, final Precision p) {
    final BigMath math = new BigMath(p);

    BigDecimal result;
    if (argument.compareTo(ZERO) == 0) {
      result = ZERO;
    } else if (argument.compareTo(ZERO) > 0) {
      result = math.log(argument.add(math.sqrt(argument.multiply(argument).add(BigDecimal.ONE))));
    } else if (argument.compareTo(ZERO) < 0) {
      result = math.log(
          math.sqrt(argument.multiply(argument).add(BigDecimal.ONE)).subtract(argument)).negate();
    } else {
      throw new Error("Unexpected Error");
    }
    return result;
  }

  /**
   * inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める.
   * 
   * <pre>
   *             argument = 0 付近での桁落ち対策に使われる.
   *             argument = 0 より離れたところでは発散するため、asinh() を使用する.
   * </pre>
   * 
   * @param argument
   *          値
   * @param p
   *          誤差
   * @return asinh(argument)
   * @since 1.1
   */
  private static BigDecimal asinhWithDivergence(final BigDecimal argument, final Precision p) {
    // この判定はすでにほかのメソッドで行われているためコメントアウト。
    // if (argument.compareTo(ZERO) == 0) {
    // return ZERO;
    // }

    final BigInteger two = BigInteger.valueOf(2);
    final BigInteger four = BigInteger.valueOf(4);
    BigDecimal negativeTerm = ZERO, positiveTerm = ZERO, singleTerm = ZERO;
    BigDecimal numerator, denominator;
    BigInteger sign = ONE;
    BigInteger squareValue, twoI, twoIPlusOne, factorialValue, numeratorOfInteger;
    final BigInteger n = ((NandEpsilon) p).getN();
    final MathContext mc = p.getMathContext();
    final BigMath math = new BigMath(p);
    for (BigInteger i = BigInteger.ZERO; i.compareTo(n) < 0; i = i.add(ONE)) {

      if (i.mod(two).compareTo(ONE) == 0) {
        sign = ONE.negate();
      } else {
        sign = ONE.abs();
      }

      twoI = two.multiply(i);
      twoIPlusOne = twoI.add(ONE);

      factorialValue = BigIntegerMath.factorial(twoI, i);
      squareValue = factorialValue.multiply(factorialValue);
      numeratorOfInteger = squareValue.multiply(sign);

      // 指数が正の整数のみであるため、ここでBigMath#pow()メソッドを使用しても
      // scale, roundingMode は使用されない。
      numerator = new BigDecimal(numeratorOfInteger).multiply(math.pow(argument, twoIPlusOne));
      denominator = (math.pow(new BigDecimal(four), i)).multiply(new BigDecimal(BigIntegerMath
          .factorial(twoIPlusOne)));
      singleTerm = numerator.divide(denominator, mc);

      if (i.mod(two).compareTo(ONE) == 0) {
        negativeTerm = negativeTerm.add(singleTerm);
      } else {
        positiveTerm = positiveTerm.add(singleTerm);
      }
    }
    return negativeTerm.add(positiveTerm);
  }
}
