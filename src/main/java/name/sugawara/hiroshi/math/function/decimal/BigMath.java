package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

import name.sugawara.hiroshi.math.precision.Precision;

/**
 * 各種数学関数を任意精度で求める. <br/>Facadeパターンを適用.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: BigMath.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */
public final strictfp class BigMath {

  /**
   * 精度.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision precision;

  /**
   * デフォルトコンストラクタ使用禁止.
   * 
   * @since 2005/07/11 21:35:33
   */
  @SuppressWarnings("unused")
  private BigMath() {
    // Empty block.
  }

  /**
   * 誤差を指定して用いる.
   * 
   * @param precision
   *          誤差
   * @since 2004/08/03
   */
  public BigMath(final Precision precision) {
    this.precision = precision;
  }

  /**
   * オイラー数 e を BigDecimal 値で累乗した値を返す.
   * 
   * @param exponent
   *          指数部
   * @return オイラー数 e を BigDecimal 値で累乗した値
   * @since 1.1
   */
  public BigDecimal exp(final BigDecimal exponent) {
    final Exponent exp = new Exponent(this.precision);
    return exp.getDependentVariable(exponent);
  }

  /**
   * 1 番目の BigDecimal 型引数を、2 番目の BigDecimal 型引数で累乗した値を返す.
   * 
   * @param base
   *          基数部
   * @param exponent
   *          指数部
   * @return 1 番目の BigDecimal 型引数を、2 番目の BigDecimal 型引数で累乗した値
   * @since 1.1
   */
  public BigDecimal pow(final BigDecimal base, final BigDecimal exponent) {

    final PowerOfDecimal pow = new PowerOfDecimal(this.precision);
    return pow.getDependentVariable(base, exponent);
  }

  /**
   * 1 番目の BigDecimal 型引数を、2 番目の BigInteger 型引数で累乗した値を返す.
   * 
   * @param base
   *          BigDecimal 型の基数部
   * @param exponent
   *          BigInteger 型の指数部
   * @return 1 番目の BigDecimal 型引数を、2 番目の BigInteger 型引数で累乗した値
   * @since 1.1
   */
  public BigDecimal pow(final BigDecimal base, final BigInteger exponent) {
    final Power pow = new Power(this.precision);
    return pow.getDependentVariable(base, exponent);
  }

  /**
   * 指定された角度の正弦 (サイン) を返す.
   * 
   * @param argument
   *          ラジアンで表した角度
   * @return 引数の正弦 (サイン)
   * @since 1.1
   */
  public BigDecimal sin(final BigDecimal argument) {
    final Sine sin = new Sine(this.precision);
    return sin.getDependentVariable(argument);
  }

  /**
   * 指定された角度の余弦 (コサイン) を返す.
   * 
   * @param argument
   *          ラジアンで表した角度
   * @return 引数の余弦 (コサイン)
   * @since 1.1
   */
  public BigDecimal cos(final BigDecimal argument) {
    final Cosine cos = new Cosine(this.precision);
    return cos.getDependentVariable(argument);
  }

  /**
   * 指定された角度の正接 (タンジェント) を返す.
   * 
   * @param argument
   *          ラジアンで表した角度
   * @return 引数の正接 (タンジェント)
   * @since 1.1
   */
  public BigDecimal tan(final BigDecimal argument) {
    final Tangent tan = new Tangent(this.precision);
    return tan.getDependentVariable(argument);
  }

  /**
   * 指定された角度の逆正弦 (アークサイン) を返す. 引数の値が-1から1までの範囲外のとき、ArithmeticExceptionを返す.
   * 
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の逆正弦 (アークサイン)をBigDecimal 型で返す
   * @since 1.1
   */
  public BigDecimal asin(final BigDecimal argument) {
    final ArcSine asin = new ArcSine(this.precision);
    return asin.getDependentVariable(argument);
  }

  /**
   * 指定された角度の逆余弦 (アークコサイン) を返す. 引数の値が-1から1までの範囲外のとき、ArithmeticExceptionを返す.
   * 
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の逆余弦 (アークコサイン)をBigDecimal 型で返す
   * @since 1.1
   */
  public BigDecimal acos(final BigDecimal argument) {
    final ArcCosine acos = new ArcCosine(this.precision);
    return acos.getDependentVariable(argument);
  }

  /**
   * 指定された角度の逆正接 (アークタンジェント) を返す.
   * 
   * @param argument
   *          ラジアンで表した BigDecimal型の角度
   * @return 引数の逆正接 (アークタンジェント)をBigDecimal 型で返す
   * @since 1.1
   */
  public BigDecimal atan(final BigDecimal argument) {
    final ArcTangent atan = new ArcTangent(this.precision);
    return atan.getDependentVariable(argument);
  }

  /**
   * 指定された角度の4象限逆正接逆正接(4象限アークタンジェント) を返す. 直交座標 (x, y) を極座標 (r, theta) に変換する.
   * 
   * @param y
   *          ラジアンで表した BigDecimal型の値
   * @param x
   *          ラジアンで表した BigDecimal型の値
   * @return 引数の逆正接 (アークタンジェント)をBigDecimal 型で返す
   * @since 1.1
   */
  public BigDecimal atan2(final BigDecimal y, final BigDecimal x) {
    final FourQuadrantArcTangent atan2 = new FourQuadrantArcTangent(this.precision);
    return atan2.getDependentVariable(y, x);
  }

  /**
   * hyperbolic sine (双曲線正弦) sinh(x) を求める. 桁落ちが生じない.
   * 
   * @param argument
   *          値
   * @return sinh(argument)
   * @since 1.1
   */
  public BigDecimal sinh(final BigDecimal argument) {
    final HyperbolicCosine sinh = new HyperbolicCosine(this.precision);
    return sinh.getDependentVariable(argument);
  }

  /**
   * hyperbolic cosine (双曲線余弦) cosh(x) を求める.
   * 
   * @param argument
   *          値
   * @return cosh(argument)
   * @since 1.1
   */
  public BigDecimal cosh(final BigDecimal argument) {
    final HyperbolicCosine cosh = new HyperbolicCosine(this.precision);
    return cosh.getDependentVariable(argument);
  }

  /**
   * hyperbolic tangent (双曲線正接) tanh(x) を求める. 桁落ちが無い.
   * 
   * @param argument
   *          値
   * @return tanh(argument)
   * @since 1.1
   */
  public BigDecimal tanh(final BigDecimal argument) {
    final HyperbolicTangent tanh = new HyperbolicTangent(this.precision);
    return tanh.getDependentVariable(argument);
  }

  /**
   * inverse hyperbolic sine (逆双曲線正弦) arcsinh(x) を求める.
   * 
   * @param argument
   *          値
   * @return asinh(argument)
   * @since 1.1
   */
  public BigDecimal asinh(final BigDecimal argument) {
    final InverseHyperbolicSine asinh = new InverseHyperbolicSine(this.precision);
    return asinh.getDependentVariable(argument);
  }

  /**
   * inverse hyperbolic cosine (双曲線余弦) arccosh(x) を求める.
   * 
   * @param argument
   *          値
   * @return arccosh(argument)
   * @since 1.1
   */
  public BigDecimal acosh(final BigDecimal argument) {
    final InverseHyperbolicCosine acosh = new InverseHyperbolicCosine(this.precision);
    return acosh.getDependentVariable(argument);
  }

  /**
   * inverse hyperbolic tangent (双曲線正弦) arctanh(x) を求める.
   * 
   * @param argument
   *          値
   * @return arccosh(argument)
   * @since 1.1
   */
  public BigDecimal atanh(final BigDecimal argument) {
    final InverseHyperbolicTangent atanh = new InverseHyperbolicTangent(this.precision);
    return atanh.getDependentVariable(argument);
  }

  /**
   * 指定された BigDecimal 値の自然対数値 (底は e) を返す.
   * 
   * @param a
   *          0.0 よりも大きい数値
   * @return ln a の値.a の自然対数
   * @since 1.1
   */
  public BigDecimal log(final BigDecimal a) {
    final Log log = new Log(this.precision);
    return log.getDependentVariable(a);
  }

  /**
   * 指定された BigDecimal 値の2進対数値 (底は e) を返す.
   * 
   * @param x
   *          0.0 よりも大きい数値
   * @return log_2(a) の値.a の2進対数
   * @since 1.1
   */
  public BigDecimal log2(final BigDecimal x) {
    final Log2 log2 = new Log2(this.precision);
    return log2.getDependentVariable(x);
  }

  /**
   * 指定された BigDecimal 値の常用対数(10進対数)値 (底は e) を返す.
   * 
   * @param x
   *          0.0 よりも大きい数値
   * @return log_10(a) の値.a の常用対数
   * @since 1.1
   */
  public BigDecimal log10(final BigDecimal x) {
    final Log10 log10 = new Log10(this.precision);
    return log10.getDependentVariable(x);
  }

  /**
   * 直角三角形の斜辺の(hypotenuse)の長さをBigDecimal型で返す C. B. Moler and D. Morrison
   * による方法を適用している. 演算繰り返し数は単精度で2,倍精度で3でよい.
   * 
   * @param x
   *          x 斜辺でない一方の辺
   * @param y
   *          y 斜辺でないもう一方の辺
   * @return sqrt(x^2 + y^2)
   * @since 1.1
   */
  public BigDecimal hypot2(final BigDecimal x, final BigDecimal y) {
    final Hypotenuse hypot2 = new Hypotenuse(this.precision);
    return hypot2.getDependentVariable(x, y);
  }

  /**
   * 平方根をBigDecimal型で求める. <br />
   * 許容誤差を10の-6乗以下にするとdouble型以上の精度を得られる.
   * 
   * @param x
   *          値
   * @return sqrt(a) (aの平方)を返す
   * @since 1.1
   */
  public BigDecimal sqrt(final BigDecimal x) {
    final SquareRoot sqrt = new SquareRoot(this.precision);
    return sqrt.getDependentVariable(x);
  }

  /**
   * 立方根を実数の範囲内でBigDecimal型で求める. <br />
   * 値が負のとき、正のときの結果に符号をつけたものが返される. このメソッドでは虚数解を返さない.
   * 
   * @param a
   *          値
   * @return aの立方根のうち実数解を返す
   * @since 1.1
   */
  public BigDecimal cbrt(final BigDecimal a) {
    final CubeRoot cubeRoot = new CubeRoot(this.precision);
    return cubeRoot.getDependentVariable(a);
  }
}
