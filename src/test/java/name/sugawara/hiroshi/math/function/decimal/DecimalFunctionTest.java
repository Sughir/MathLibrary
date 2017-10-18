/**
 * 
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import name.sugawara.hiroshi.math.constant.Constant;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * BigDecmalを扱って計算する関数に共通して使われる変数をまとめるテストケース.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2005/07/13 9:25:32
 * 
 */
public abstract class DecimalFunctionTest {

  /**
   * 丸めモード.
   * 
   * @uml.property name="MODE" changeability="frozen"
   */
  protected static final int         MODE               = BigDecimal.ROUND_HALF_EVEN;

  /**
   * 項の演算回数.
   * 
   * @uml.property name="N" changeability="frozen"
   */
  protected static final BigInteger  N                  = new BigInteger("100");

  /**
   * スケール.
   * 
   * @uml.property name="SCALE" changeability="frozen"
   */
  protected static final int         SCALE              = 100;

  /**
   * 機械イプシロン.
   * 
   * @uml.property name="EPS" changeability="frozen"
   */
  protected static final BigDecimal  EPS                = new BigDecimal("0.00000000000000000001");

  /**
   * MathContextオブジェクト.
   * 
   * @since 2006/06/11 17:26:38
   */
  protected static final MathContext MC                 = new MathContext(
                                                            DecimalFunctionTest.SCALE,
                                                            RoundingMode.HALF_EVEN);

  /**
   * 精度.
   * 
   * @since 2005/07/11 21:28:53
   */
  protected static final Precision   PRECISION          = new NandEpsilon(DecimalFunctionTest.N,
                                                            DecimalFunctionTest.EPS,
                                                            DecimalFunctionTest.MC);

  /**
   * 0.
   * 
   * @since 2005/07/13 9:43:34
   */
  protected static final BigDecimal  ZERO               = BigDecimal.ZERO;

  /**
   * 1.
   * 
   * @since 2005/07/13 9:43:39
   */
  protected static final BigDecimal  ONE                = BigDecimal.ONE;

  /**
   * 2.
   * 
   * @since 2005/07/13 9:43:42
   */
  protected static final BigDecimal  TWO                = new BigDecimal("2.0");

  /**
   * 3.
   * 
   * @since 2005/07/13 9:43:46
   */
  protected static final BigDecimal  THREE              = new BigDecimal("3.0");

  /**
   * 4.
   * 
   * @since 2005/07/13 9:43:50
   */
  protected static final BigDecimal  FOUR               = new BigDecimal("4.0");

  /**
   * 5.
   * 
   * @since 2005/07/13 9:43:56
   */
  protected static final BigDecimal  FIVE               = new BigDecimal("5.0");

  /**
   * 6.
   * 
   * @since 2005/07/13 9:44:04
   */
  protected static final BigDecimal  SIX                = new BigDecimal("6.0");

  /**
   * 7.
   * 
   * @since 2005/07/13 9:44:11
   */
  protected static final BigDecimal  SEVEN              = new BigDecimal("7.0");

  /**
   * 8.
   * 
   * @since 2005/07/13 9:44:16
   */
  protected static final BigDecimal  EIGHT              = new BigDecimal("8.0");

  /**
   * 9.
   * 
   * @since 2005/07/13 9:44:21
   */
  protected static final BigDecimal  NINE               = new BigDecimal("9.0");

  /**
   * 10.
   * 
   * @since 2005/07/13 9:44:25
   */
  protected static final BigDecimal  TEN                = new BigDecimal("10.0");

  /**
   * double型最小許容誤差.
   * 
   * @since 2005/09/14 19:04:11
   */
  protected static final double      DOUBLE_MINIMUM_EPS = 10e-324;

  /**
   * 円周率を返す.
   * 
   * @return 円周率
   * @since 2005/07/11 21:29:02
   */
  protected static BigDecimal pi() {
    return new Constant(DecimalFunctionTest.PRECISION).pi();
  }

}
