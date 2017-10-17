package name.sugawara.hiroshi.math.constant;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecimal で円周率を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Pi.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 */
final strictfp class Pi {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/11 3:11:21
   */
  private Pi() {
    // Empty block
  }

  /**
   * 円周率をJ.Machin(マチン)の公式を用いて求める.
   * 
   * @param p
   *          誤差
   * @return 円周率を BigDecimal 型で返す
   */
  public static BigDecimal pi(final Precision p) {

    final BigInteger n = ((NandEpsilon) p).getN();
    final int scale = ((NandEpsilon) p).getScale();
    final int mode = ((NandEpsilon) p).getRoundingMode();

    final BigInteger two = new BigInteger("2");

    BigDecimal numerator;
    BigDecimal denominator;
    BigInteger partsOfDenominator;

    BigDecimal firstTerm = BigDecimal.ZERO;
    BigDecimal secondTerm = BigDecimal.ZERO;

    final BigDecimal x1 = new BigDecimal("0.2");
    final BigDecimal squareX1 = x1.multiply(x1);

    final BigDecimal x2 = new BigDecimal("239");
    final BigDecimal squareX2 = x2.multiply(x2);
    final BigDecimal x2one = new BigDecimal("1");
    final BigDecimal x2reciprocal = x2one.divide(squareX2, scale, mode);

    for (BigInteger k = n; k.compareTo(BigInteger.ZERO) == 1; k = k.subtract(BigInteger.ONE)) {

      numerator = new BigDecimal(k).multiply(new BigDecimal(k)).multiply(squareX1);
      partsOfDenominator = two.multiply(k).add(BigInteger.ONE);
      denominator = new BigDecimal(partsOfDenominator).add(firstTerm);
      firstTerm = numerator.divide(denominator, scale, mode);
    }
    firstTerm = x1.divide(firstTerm.add(BigDecimal.ONE), scale, mode);

    for (BigInteger k = n; k.compareTo(BigInteger.ZERO) == 1; k = k.subtract(BigInteger.ONE)) {

      numerator = new BigDecimal(k).multiply(new BigDecimal(k)).multiply(x2reciprocal);
      partsOfDenominator = two.multiply(k).add(BigInteger.ONE);
      denominator = new BigDecimal(partsOfDenominator).add(secondTerm);
      secondTerm = numerator.divide(denominator, scale, mode);
    }
    secondTerm = x2one.divide(x2, scale, mode).divide(secondTerm.add(BigDecimal.ONE), scale, mode);

    return firstTerm.multiply(new BigDecimal("16.0")).subtract(
        secondTerm.multiply(new BigDecimal("4.0")));
  }
}
