/**
 * Created Date : 2007/02/28 1:37:53
 */
package name.sugawara.hiroshi.math.complex;

import java.math.BigDecimal;

import name.sugawara.hiroshi.math.function.decimal.BigMath;
import name.sugawara.hiroshi.math.precision.Precision;

/**
 * ComplexUtilsのBigComplex版.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * 
 * Created Date : 2007/02/28 1:37:53
 * 
 */
public final class BigComplexUtils {

  /**
   * 精度.
   * 
   * @since 2007/02/28 14:58:24
   */
  private Precision precision;

  /**
   * BigMathオブジェクト.
   * 
   * @since 2007/02/28 8:34:24
   */
  private BigMath   math;

  /**
   * 精度を指定してBigComplex計算用オブジェクトを生成.
   * 
   * @param precision
   *          精度
   * 
   * @since 2007/02/28 1:38:39
   */
  public BigComplexUtils(final Precision precision) {
    this.precision = precision;
    this.math = new BigMath(precision);
  }

  /**
   * 複素数の偏角を求める.
   * 
   * @param z
   *          複素数
   * 
   * @since 1.1
   * @return 引数で指定した複素数の偏角をRadian(ラディアン)で返す。
   */
  public BigDecimal arg(final BigComplex z) {
    return this.math.atan2(z.getImaginary(), z.getReal());
  }

  /**
   * 複素数の arc sine(逆正弦)を求める.
   * 
   * asin(x) を求める.
   * 
   * @param z
   *          複素数
   * 
   * @return 引数 の arc sine(逆正弦) をBigComplex型(複素数)で返す
   * @since 1.1
   */
  public BigComplex asin(final BigComplex z) {
    final BigDecimal sqrt05 = this.math.sqrt(new BigDecimal("0.5"));
    final BigDecimal x = z.getReal();
    final BigDecimal y = z.getImaginary();
    final BigDecimal a = x.multiply(x).subtract(y.multiply(y)).add(BigDecimal.ONE);
    final BigDecimal b = new BigDecimal("2.0").multiply(x).multiply(y);
    final BigDecimal r = new BigComplex(a, b).abs();
    BigDecimal sqrtReal, sqrtImaginary;

    if (a.compareTo(BigDecimal.ZERO) > 0) {
      sqrtReal = sqrt05.multiply(this.math.sqrt(r.add(a))).subtract(y);
      sqrtImaginary = sqrt05.multiply(b).divide(this.math.sqrt(r.add(a)),
          this.precision.getMathContext()).add(x);
    } else if (a.compareTo(BigDecimal.ZERO) == 0) {
      sqrtReal = sqrt05.multiply(this.math.sqrt(b)).subtract(y);
      sqrtImaginary = y.add(sqrt05.multiply(this.math.sqrt(b)));
    } else {
      sqrtReal = sqrt05.multiply(b).divide(this.math.sqrt(r.subtract(a)),
          this.precision.getMathContext()).subtract(y);
      sqrtImaginary = x.add(sqrt05.multiply(this.math.sqrt(r.subtract(a))));
    }

    final BigDecimal real = this.math.atan2(sqrtImaginary, sqrtReal);
    final BigDecimal imaginary = new BigDecimal("-0.5").multiply(this.math.log(sqrtReal.multiply(
        sqrtReal).add(sqrtImaginary.multiply(sqrtImaginary))));
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の arc cosine(逆余弦)を求める. acos(x) を求める.
   * 
   * @param z
   *          複素数
   * 
   * @return arc cosine(逆余弦) をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex acos(final BigComplex z) {
    final BigDecimal sqrt05 = this.math.sqrt(new BigDecimal("0.5"));
    final BigDecimal x = z.getReal();
    final BigDecimal y = z.getImaginary();
    final BigDecimal a = BigDecimal.ONE.subtract(x.multiply(x).add(y.multiply(y)));
    final BigDecimal b = new BigDecimal("-2.0").multiply(x).multiply(y);
    final BigDecimal r = new BigComplex(a, b).abs();
    BigDecimal sqrtReal, sqrtImaginary;

    if (a.compareTo(BigDecimal.ZERO) > 0) {
      sqrtReal = x.subtract(sqrt05.multiply(b).divide(this.math.sqrt(r.add(a)),
          this.precision.getMathContext()));
      sqrtImaginary = y.add(sqrt05.multiply(this.math.sqrt(r.add(a))));
    } else if (a.compareTo(BigDecimal.ZERO) == 0) {
      sqrtReal = x.subtract(this.math.sqrt(b.multiply(new BigDecimal("0.5"))));
      sqrtImaginary = y.add(this.math.sqrt(b.multiply(new BigDecimal("0.5"))));
    } else {
      sqrtReal = x.subtract(sqrt05.multiply(this.math.sqrt(r.subtract(a))));
      sqrtImaginary = y.add(sqrt05.multiply(b).divide(this.math.sqrt(r.subtract(a)),
          this.precision.getMathContext()));
    }

    final BigDecimal real = this.math.atan2(sqrtImaginary, sqrtReal);
    final BigDecimal imaginary = new BigDecimal("-0.5").multiply(this.math.log(sqrtReal.multiply(
        sqrtReal).add(sqrtImaginary.multiply(sqrtImaginary))));
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数のコサイン(余弦)を求める. cos(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトのコサイン(余弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex cos(final BigComplex z) {
    final BigDecimal e = this.math.exp(z.getImaginary());

    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());

    final BigDecimal imaginary = new BigDecimal("0.5").multiply(this.math.sin(z.getReal()))
        .multiply(f.subtract(e));

    final BigDecimal real = new BigDecimal("0.5").multiply(this.math.cos(z.getReal())).multiply(
        f.add(e));

    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の hyperbolic cosine(双曲線余弦)を求める.
   * 
   * sinh(x) を求める.
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトのhyperbolic cosine(双曲線余弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex cosh(final BigComplex z) {
    final BigDecimal e = this.math.exp(z.getReal());

    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal real = new BigDecimal("0.5").multiply(e.add(f)).multiply(
        this.math.cos(z.getImaginary()));
    final BigDecimal imaginary = new BigDecimal("0.5").multiply(e.subtract(f)).multiply(
        this.math.sin(z.getImaginary()));
    return new BigComplex(real, imaginary);
  }

  /**
   * 自然対数の底の複素数乗を求める.
   * 
   * exp(x) を求める。 複素数の累乗にはドモアブルの定理を使用している.
   * 
   * @param z
   *          複素数
   * @return 自然対数の底の現在の複素数オブジェクト乗をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex exp(final BigComplex z) {
    final BigDecimal a = this.math.exp(z.getReal());
    final BigDecimal real = a.multiply(this.math.cos(z.getImaginary()));
    final BigDecimal imaginary = a.multiply(this.math.sin(z.getImaginary()));
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の自然対数を求める.
   * 
   * log(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトの自然対数をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex log(final BigComplex z) {
    final BigDecimal real = new BigDecimal("0.5").multiply(this.math.log(z.getReal().multiply(
        z.getReal()).add(z.getImaginary().multiply(z.getImaginary()))));

    final BigDecimal imaginary = this.math.atan2(z.getImaginary(), z.getReal());
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の常用対数(底を10とする対数)を求める.
   * 
   * log_10(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトの常用対数をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex log10(final BigComplex z) {
    final BigComplex logTen = new BigComplex(this.math.log(BigDecimal.TEN), BigDecimal.ZERO);
    return z.divide(logTen);
  }

  /**
   * 複素数の底を2とする対数を求める.
   * 
   * log_2(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトの底を2とする対数をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex log2(final BigComplex z) {
    final BigComplex logTwo = new BigComplex(this.math.log(new BigDecimal("2.0")), BigDecimal.ZERO,
        this.precision);
    return z.divide(logTwo);
  }

  /**
   * 複素数のべき乗を複素数乗で求める. 複素数の累乗にはドモアブルの定理を使用している。
   * 
   * @since 1.1
   * @param z
   *          基数
   * @param b
   *          指数
   * @return zの複素数のb乗をComplex型(複素数)で返す。
   */
  public BigComplex pow(final BigComplex z, final BigComplex b) {
    final BigDecimal real = z.getReal();
    final BigDecimal imag = z.getImaginary();
    final BigDecimal u = b.getReal();
    final BigDecimal v = b.getImaginary();
    final BigDecimal r = z.abs();
    final BigDecimal theta = this.math.atan2(imag, real);
    final BigDecimal temp = this.math.exp(u.multiply(this.math.log(r)).subtract(v.multiply(theta)));
    final BigDecimal value = v.multiply(this.math.log(r)).add(u.multiply(theta));
    final BigDecimal x = temp.multiply(this.math.cos(value));
    final BigDecimal y = temp.multiply(this.math.sin(value));
    return new BigComplex(x, y);
  }

  /**
   * 複素数のサイン(正弦)を求める.
   * 
   * sin(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトのサイン(正弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex sin(final BigComplex z) {
    final BigDecimal e = this.math.exp(z.getImaginary());
    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal imaginary = new BigDecimal("0.5").multiply(this.math.cos(z.getReal())
        .multiply(e.subtract(f)));
    final BigDecimal real = new BigDecimal("0.5").multiply(this.math.sin(z.getReal())).multiply(
        e.add(f));
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の hyperbolic sine(双曲線正弦)を求める.
   * 
   * sinh(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトのhyperbolic sine(双曲線正弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex sinh(final BigComplex z) {
    final BigDecimal e = this.math.exp(z.getReal());
    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal real = new BigDecimal("0.5").multiply(e.subtract(f)).multiply(
        this.math.cos(z.getImaginary()));
    final BigDecimal imaginary = new BigDecimal("0.5").multiply(e.add(f)).multiply(
        this.math.sin(z.getImaginary()));
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の平方根を求める. 複素数の平方にはドモアブルの定理を使用している。
   * 
   * @param z
   *          複素数
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の平方根を返す。
   */

  public BigComplex sqrt(final BigComplex z) {
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal rootTwo = this.math.sqrt(two);
    final BigDecimal r = z.abs();

    final BigDecimal real = this.math.sqrt(r.add(z.getReal()));
    final BigDecimal imaginary = this.math.sqrt(r.subtract(z.getImaginary()));

    return new BigComplex(rootTwo.multiply(real).divide(two, this.precision.getMathContext()),
        rootTwo.multiply(imaginary).divide(two, this.precision.getMathContext()));
  }

  /**
   * 複素数のタンジェント(正接)を求める. tan(x) を求める。
   * 
   * @param z
   *          複素数
   * 
   * @return 現在のオブジェクトのタンジェント(正接)をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex tan(final BigComplex z) {
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal e = this.math.exp(z.getImaginary().multiply(two));
    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal d = this.math.cos(z.getReal().multiply(two)).add(
        new BigDecimal("0.5").multiply(e.add(f)));
    final BigDecimal real = this.math.sin(z.getReal().multiply(two)).divide(d,
        this.precision.getMathContext());
    final BigDecimal imaginary = new BigDecimal("0.5").multiply(e.subtract(f)).divide(d,
        this.precision.getMathContext());
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数の hyperbolic tangent(双曲線正接)を求める. sinh(x) を求める。
   * 
   * @param z
   *          複素数
   * @return 現在のオブジェクトのhyperbolic tangent(双曲線正接)をBigComplex型(複素数)で返す。
   * @since 1.1
   */
  public BigComplex tanh(final BigComplex z) {
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal e = this.math.exp(z.getReal().multiply(two));

    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());

    final BigDecimal d = new BigDecimal("0.5").multiply(e.add(f)).add(
        this.math.cos(z.getImaginary().multiply(two)));
    final BigDecimal real = new BigDecimal("0.5").multiply(e.subtract(f)).divide(d,
        this.precision.getMathContext());
    final BigDecimal imaginary = this.math.sin(z.getImaginary().multiply(two)).divide(d,
        this.precision.getMathContext());
    return new BigComplex(real, imaginary);
  }

  /**
   * 複素数のinverse tangent(逆正接)を求める.tan(x).
   * 
   * i * ()
   * 
   * BigComplex.I.multiply( log((i+z)/(i-z))/2;
   * 
   * @param z
   *          複素数
   * @return atan(z)
   * @since 2007/03/05 17:25:10
   */
  public BigComplex atan(final BigComplex z) {

    return BigComplex.I.multiply(this.log(BigComplex.I.add(z).divide(BigComplex.I.subtract(z))))
        .divide(new BigComplex(new BigDecimal("2.0"), BigDecimal.ZERO));
  }

  /**
   * 与えられた極座標表現から複素数を生成する.
   * 
   * <p>
   * 値は{@code r&middot;cos(theta) + r&middot;sin(theta)i}として計算され
   * {@code r&middot;e<sup>i&middot;theta</sup>}によって返される。
   * </p>
   * 
   * @param r
   *          複素数のモジュール(大きさ)
   * @param theta
   *          複素数の偏角
   * @return {@code r&middot;e<sup>i&middot;theta</sup>}
   * @since 2007/03/05 18:14:12
   */
  public BigComplex polar2Complex(final BigDecimal r, final BigDecimal theta) {
    if (r.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Complex modulus must not be negative");
    }
    return new BigComplex(r.multiply(this.math.cos(theta)), r.multiply(this.math.sin(theta)));
  }

  /**
   * 引数で与えられた複素数zの1 - {@code z}<sup>2</sup>の平方を求める.
   * 
   * <p>
   * {@code sqrt(BigComplex.ONE.subtract(z.multiply(z)))}として直接演算される.
   * </p>
   * 
   * @param z
   *          複素数
   * @return sqrt(1 - {@code z}<sup>2</sup>)
   * @since 2007/03/05 18:37:00
   */
  public BigComplex sqrt1z(final BigComplex z) {
    return this.sqrt(BigComplex.ONE.subtract(z.multiply(z)));
  }

}
