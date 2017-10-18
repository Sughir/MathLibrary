package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.MathContext;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal でコサイン(余弦)関数 cos(x) を求める.
 *
 * @author Hiroshi Sugawara
 * @version $Id: Cosine.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class Cosine implements FunctionOfSingleVariable<BigDecimal, BigDecimal> {

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
  public Cosine(final Precision precision) {
    this.precision = precision;
  }

  /**
   * 指定された角度の余弦 (コサイン) を返す
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 引数の余弦 (コサイン)をBigDecimal 型で返す
   */
  // private static BigDecimal cos(BigDecimal argument, Precision p) {
  // BigInteger N = ((NumberOfTerm) p).getN();
  //
  // BigDecimal one = BigDecimal.ONE;
  // BigDecimal two = new BigDecimal("2.0");
  // BigDecimal four = new BigDecimal("4.0");
  //
  // BigDecimal pi = Constant.pi(p);
  // BigDecimal fourtyFive = pi.divide(four, p.getMathContext());
  // BigDecimal ninty = pi.divide(two, p.getMathContext());
  // BigDecimal twoHundredAndSeventy =
  // pi.multiply(new BigDecimal("3.0")).divide(two, p.getMathContext());
  //
  // BigDecimal cycle = pi.multiply(two);
  //
  // if (argument.compareTo(BigDecimal.ZERO) == 0
  // || argument.compareTo(cycle) == 0) {
  // return BigDecimal.ONE;
  // } else if (
  // argument.compareTo(ninty) == 0
  // || argument.compareTo(twoHundredAndSeventy) == 0) {
  // return BigDecimal.ZERO;
  // } else if (argument.compareTo(pi) == 0) {
  // return one.negate();
  // }
  // Sine sin = new Sine(p);
  //
  // return (BigDecimal)sin.getDependentVariable(argument.add(ninty));
  // }
  /**
   * 1から指定された角度の余弦 (コサイン) を引いた値を返す。 1 - cos(argument) を返す.
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @param p
   *          誤差
   * @return 1 から引数の余弦 (コサイン)を引いた値を BigDecimal 型で返す
   */
  private static BigDecimal oneDivideCos(final BigDecimal argument, final Precision p) {
    BigDecimal result;
    final MathContext mc = p.getMathContext();
    final Constant c = new Constant(p);

    final BigDecimal one = BigDecimal.ONE;
    final BigDecimal two = new BigDecimal("2.0");
    // BigDecimal four = new BigDecimal("4.0");

    // BigDecimal fourtyFive = Constant.pi(p).divide(four, mc);
    final BigDecimal ninty = c.pi().divide(two, mc);
    final BigDecimal pi = c.pi();
    final BigDecimal twoHundredAndSeventy = c.pi().multiply(new BigDecimal("3.0")).divide(two, mc);

    final BigDecimal cycle = pi.multiply(two);

    if (argument.compareTo(BigDecimal.ZERO) == 0 || argument.compareTo(cycle) == 0) {
      result = BigDecimal.ZERO;
    } else if (argument.compareTo(ninty) == 0 || argument.compareTo(twoHundredAndSeventy) == 0) {
      result = BigDecimal.ONE;
    } else if (argument.compareTo(pi) == 0) {
      result = two;
    } else {

      final Tangent tangent = new Tangent(p);
      BigDecimal tan = tangent.getDependentVariable(argument.divide(two, mc));
      tan = tan.multiply(tan);
      final BigDecimal numerator = two.multiply(tan);
      final BigDecimal denominator = tan.add(one);
      result = numerator.divide(denominator, mc);

    }
    return result;
  }

  /**
   * 指定された角度の余弦 (コサイン) を返す.
   *
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の余弦 (コサイン)をBigDecimal 型で返す
   */
  @Override
  public BigDecimal getDependentVariable(final BigDecimal argument) {
    return Cosine.oneDivideCos(argument, this.precision);
  }
}
