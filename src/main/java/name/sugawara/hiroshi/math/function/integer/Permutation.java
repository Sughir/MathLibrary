/*
 * Created on 2003/07/08
 * 
 */
package name.sugawara.hiroshi.math.function.integer;

import java.math.BigInteger;

import name.sugawara.hiroshi.math.function.object.FunctionOfTwoVariable;

/**
 * 順列(Permutation)の数.
 * 
 * nPr
 * 
 * @author sugawara
 * @version $Id: Permutation.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1 To change the template for this generated type comment go to
 *        Window>Preferences>Java>Code Generation>Code and Comments
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class Permutation implements FunctionOfTwoVariable<BigInteger, BigInteger, BigInteger> {

  /**
   * n個のものからr個を取り出す順列の個数を求める.
   * 
   * @param n
   *          n
   * @param r
   *          r
   * @return nPr
   * @since 1.1
   */
  public static BigInteger permutation(final BigInteger n, final BigInteger r) {
    if (n.compareTo(BigInteger.ZERO) < 0 || r.compareTo(BigInteger.ZERO) < 0) {
      throw new ArithmeticException("The value n and the value r must be the natural numbers.");
    } else if (n.compareTo(r) == 0) {
      return BigIntegerMath.factorial(n);
    } else {
      return BigIntegerMath.factorial(n).divide(BigIntegerMath.factorial(n.subtract(r)));
    }
  }

  /**
   * nPk.
   * 
   * @param independent1
   *          n
   * @param independent2
   *          k
   * @return nPk
   * @see FunctionOfTwoVariable#getDependentVariable(Number,Number)
   * @since 2007/02/02 11:41:44
   */
  public BigInteger getDependentVariable(final BigInteger independent1,
      final BigInteger independent2) {
    return Permutation.permutation(independent1, independent2);

  }
}
