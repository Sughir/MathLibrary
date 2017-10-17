package name.sugawara.hiroshi.math.complex;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * double型対応複素数クラス. 使用例 :<br /> {@code //3.3-4&lt;i&gt;i&lt;/i&gt; (実部を 3.3, 虚部を -4,虚数をiとする)の複素数を作成する。
 * DoubleComplex complex1 = new complex(3.3, -4); //この複素数 complex1 から 実部と虚部を取り出し &lt;i&gt;x +
 * yi&lt;/i&gt; の形式で表示する. double x = complex1.getReal(); double y = complex1.getImaginary();
 * System.out.println(x + &quot; + &quot; + y + &quot;i&quot;); //複素数の除算 //complex1 から complex2
 * を除算しそれをcomplex3に代入する。 DoubleComplex complex2 = new complex(3.3, -4); DoubleComplex complex3 =
 * complex1.divide(complex2); }
 * 
 * @author Hiroshi Sugawara
 * @version $Id: DoubleComplex.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */
public final strictfp class DoubleComplex extends Complex implements Comparable<DoubleComplex>,
    Serializable {

  /**
   * 虚数単位.
   */
  public static final DoubleComplex IMAGINARY_UNIT   = new DoubleComplex(1);

  /**
   * 1. 実数部が1、虚数部が0である、実数の1をあらわすDoubleComplexオブジェクト.
   */
  public static final DoubleComplex ONE              = new DoubleComplex(1.0, 0.0);

  /**
   * ゼロ. 実数部、虚数部ともに0であるDoubleComplexオブジェクト.
   */
  public static final DoubleComplex ZERO             = new DoubleComplex();

  /**
   * シリアルバージョンID.
   * 
   * @since 2006/02/22 3:16:04
   */
  private static final long         serialVersionUID = -6571583606333378559L;

  /**
   * sqrt(0.5).
   * 
   * @since 2005/07/16 21:45:35
   */
  private static final double       SQRT05           = Math.sqrt(0.5d);

  /**
   * 虚数部.
   */
  private double                    imaginary;

  /**
   * 実数部.
   */
  private double                    real;

  /**
   * 実部、虚部ともに0の複素数を生成(0の実数を生成).
   * 
   * @since 1.1
   */
  public DoubleComplex() {
    this(0.0d, 0.0d);
  }

  /**
   * 実部が0で引数で指定した虚部の複素数(純虚数)を生成.
   * 
   * @since 1.1
   * @param y
   *          yに虚部を指定する。
   */
  public DoubleComplex(final double y) {
    this(0.0d, y);
  }

  /**
   * 実部、虚部共に引数で指定した複素数を生成.
   * 
   * @since 1.1
   * @param x
   *          xに実部を指定する。
   * @param y
   *          yに虚部を指定する。
   */
  public DoubleComplex(final double x, final double y) {
    if (Double.isNaN(x)) {
      throw new ArithmeticException("The argument x(the real number) must not be Not a Number.");
    } else if (Double.isNaN(y)) {
      throw new ArithmeticException(
          "The argument y(the imaginary number) must not be Not a Number.");
    } else if (Double.isInfinite(x)) {
      throw new ArithmeticException("The argument x(the real number) must not be infinity.");
    } else if (Double.isInfinite(y)) {
      throw new ArithmeticException("The argument y(the imaginary number) must not be infinity.");
    }

    this.real = x;
    this.imaginary = y;
  }

  /**
   * 複素数の絶対値を求める.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の絶対値を返す。
   */
  public double abs() {
    return Math.hypot(this.real, this.imaginary);
  }

  /**
   * 複素数の arc cosine(逆余弦)を求める. <br />
   * acos(x) を求める。
   * 
   * @return 現在のオブジェクトの arc cosine(逆余弦) をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex acos() {
    if (this.imaginary == 0.0d && -1.0d <= this.real && this.real <= 1.0d) {
      return new DoubleComplex(Math.acos(this.real), 0.0d);
    }

    final double sqrt05 = Math.sqrt(0.5d);

    final double x = this.real;
    final double y = this.imaginary;
    final double a = 1 - x * x + y * y;
    final double b = (-2) * x * y;
    final double r = new DoubleComplex(a, b).abs();

    double sqrtReal, sqrtImaginary;

    if (a > 0.0d) {
      sqrtReal = x - (sqrt05 * b / Math.sqrt(r + a));
      sqrtImaginary = y + sqrt05 * Math.sqrt(r + a);
    } else if (a == 0.0d) {
      sqrtReal = x - Math.sqrt(b / 2);
      sqrtImaginary = y + Math.sqrt(b / 2);
    } else {
      sqrtReal = x - (sqrt05 * Math.sqrt(r - a));
      sqrtImaginary = y + (sqrt05 * b / Math.sqrt(r - a));
    }

    final double realResult = Math.atan2(sqrtImaginary, sqrtReal);
    final double imaginaryResult = -0.5d
        * Math.log(sqrtReal * sqrtReal + sqrtImaginary * sqrtImaginary);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の加算. {@code c = a + d}
   * 
   * @since 1.1
   * @param b
   *          加算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を加算した複素数を返す。
   */
  public DoubleComplex add(final DoubleComplex b) {
    return new DoubleComplex(this.real + b.real, this.imaginary + b.imaginary);
  }

  /**
   * 複素数の偏角を求める.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の偏角をRadian(ラディアン)で返す。
   */
  public double arg() {
    return Math.atan2(this.imaginary, this.real);
  }

  /**
   * 複素数の arc sine(逆正弦)を求める. <br />
   * asin(x) を求める。
   * 
   * @return 現在のオブジェクトの arc sine(逆正弦) をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex asin() {

    if (this.imaginary == 0.0d && -1.0d <= this.real && this.real <= 1.0d) {
      return new DoubleComplex(Math.asin(this.real), 0.0d);
    }

    final double x = this.real;
    final double y = this.imaginary;
    final double a = 1 + x * x - y * y;
    final double b = 2 * x * y;
    final double r = new DoubleComplex(a, b).abs();
    double sqrtReal, sqrtImaginary;

    if (a > 0.0d) {
      sqrtReal = (DoubleComplex.SQRT05 * Math.sqrt(r + a)) - y;
      sqrtImaginary = x + DoubleComplex.SQRT05 * b / Math.sqrt(r + a);
    } else if (a == 0.0d) {
      sqrtReal = (DoubleComplex.SQRT05 * Math.sqrt(b)) - y;
      sqrtImaginary = y + DoubleComplex.SQRT05 * Math.sqrt(b);
    } else {
      sqrtReal = DoubleComplex.SQRT05 * b / Math.sqrt(r - a) - y;
      sqrtImaginary = x + (DoubleComplex.SQRT05 * Math.sqrt(r - a));
    }

    final double realResult = Math.atan2(sqrtImaginary, sqrtReal);
    final double imaginaryResult = -0.5d
        * Math.log(sqrtReal * sqrtReal + sqrtImaginary * sqrtImaginary);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 指定されたオブジェクトと現在のオブジェクトの絶対値を比較する.
   * 
   * @param c
   *          指定されたComplex型オブジェクト
   * @return このComplexの絶対値がobjより小さい場合は負の数、等しい場合は 0、大きい場合は正の数
   * @since 1.1
   */
  public int compareTo(final DoubleComplex c) {
    if (this == c) {
      return 0;
    }
    int result;

    if (c == null || this == null) {
      throw new NullPointerException();
    } else {
      if (this.abs() == c.abs()) {
        result = 0;
      } else if (this.abs() < c.abs()) {
        result = -1;
      } else if (this.abs() > c.abs()) {
        result = 1;
      } else {
        // 予期せぬ状況 null値出現など
        throw new ArithmeticException();
      }
    }
    return result;
  }

  /**
   * 複素数の共役複素数を求める.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの共役複素数を返す。
   */
  public DoubleComplex conjugate() {
    return new DoubleComplex(this.real, -this.imaginary);
  }

  /**
   * 複素数のコサイン(余弦)を求める. {@code cos(x)} を求める。
   * 
   * @return 現在のオブジェクトのコサイン(余弦)をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex cos() {

    if (this.imaginary == 0.0d) {
      return new DoubleComplex(Math.cos(this.real), 0.0d);
    }
    final double half = 0.5d;
    final double e = Math.exp(this.imaginary);
    final double f = 1 / e;
    final double imaginaryResult = half * Math.sin(this.real) * (f - e);
    final double realResult = half * Math.cos(this.real) * (f + e);

    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の hyperbolic cosine(双曲線余弦)を求める. <br />
   * sinh(x) を求める。
   * 
   * @return 現在のオブジェクトのhyperbolic cosine(双曲線余弦)をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex cosh() {
    final double e = Math.exp(this.real);
    final double f = 1 / e;
    final double realResult = 0.5d * (e + f) * Math.cos(this.imaginary);
    final double imaginaryResult = 0.5d * (e - f) * Math.sin(this.imaginary);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の除算. October.13.2002 上位桁あふれ対策済み <br />
   * 
   * @since 1.1
   * @param b
   *          除算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を除算した複素数を返す。
   */
  public DoubleComplex divide(final DoubleComplex b) {
    final double bRealSquare = Math.pow(b.real, 2);
    final double bImaginarySquare = Math.pow(b.imaginary, 2);
    final double geometricNotMean = bRealSquare + bImaginarySquare;
    final double w, d, realResult, imaginaryResult;
    DoubleComplex result;
    if (geometricNotMean <= Double.MAX_VALUE || geometricNotMean == Double.POSITIVE_INFINITY) {
      final double x = (b.real * this.real + this.imaginary * b.imaginary) / geometricNotMean;
      final double y = (b.real * this.imaginary - this.real * b.imaginary) / geometricNotMean;
      result = new DoubleComplex(x, y);
    } else if (Math.abs(b.real) >= Math.abs(b.imaginary)) {
      w = b.imaginary / b.real;
      d = b.real + b.imaginary * w;
      realResult = (this.real + this.imaginary * w) / d;
      imaginaryResult = (this.imaginary - this.real * w) / d;
      result = new DoubleComplex(realResult, imaginaryResult);
    } else {
      w = b.real / b.imaginary;
      d = b.real * w + b.imaginary;
      realResult = (this.real * w + this.imaginary) / d;
      imaginaryResult = (this.imaginary * w - this.real) / d;
      result = new DoubleComplex(realResult, imaginaryResult);
    }
    return result;
  }

  /**
   * 現在のオブジェクトの値をdouble型の複素数の絶対値としてlong型に変換して返す.
   * 
   * @since 1.1
   * @return 複素数の絶対値をlong型で返す。
   */
  @Override
  public double doubleValue() {
    return this.abs();
  }

  /**
   * 指定されたオブジェクトと現在のオブジェクトの要素(複素数の実部、虚部の値を)が等しいかどうかを比較する. {@link Object#getClass()}
   * を使用しているので、Complexクラスの派生クラスのオブジェクトとを比較する場合は必ずfalseとなる。
   * 
   * @param obj
   *          指定されたオブジェクト
   * @return 2つの値が等しいとき真を返す
   * @since 1.1
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj != null && this.getClass() == obj.getClass()) {
      final DoubleComplex c = (DoubleComplex) obj;
      if (this.real == c.real && this.imaginary == c.imaginary) {
        return true;
      }
    }
    return false;
  }

  /**
   * 自然対数の底の複素数乗を求める. {@code exp(x)} を求める。 複素数の累乗にはドモアブルの定理を使用している。
   * 
   * @return 自然対数の底の現在の複素数オブジェクト乗をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex exp() {
    final double a = Math.exp(this.real);
    final double realResult = a * Math.cos(this.imaginary);
    final double imaginaryResult = a * Math.sin(this.imaginary);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 現在のオブジェクトの値をdouble型の複素数の絶対値としてfloat型に変換して返す.
   * 
   * @since 1.1
   * @return 複素数の絶対値をfloat型で返す。
   */
  @Override
  public float floatValue() {
    return new Float(this.abs()).floatValue();
  }

  /**
   * 現在のオブジェクトより虚部を取り出す.
   * 
   * @since 1.1
   * @return 虚部を返す
   */
  public double getImaginary() {
    return this.imaginary;
  }

  /**
   * 現在のオブジェクトより実部を取り出す.
   * 
   * @since 1.1
   * @return 実部を返す
   */
  public double getReal() {
    return this.real;
  }

  /**
   * 現在のオブジェクトの値をdouble型の複素数の絶対値としてint型に変換して返す.
   * 
   * @since 1.1
   * @return 複素数の絶対値をint型で返す。
   */
  @Override
  public int intValue() {
    return Double.valueOf(this.abs()).intValue();
  }

  /**
   * 複素数の自然対数を求める. {@code log(x)} を求める.
   * 
   * @return 現在のオブジェクトの自然対数をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex log() {

    if (this.real > 0.0d && this.imaginary == 0.0d) {
      return new DoubleComplex(Math.log(this.real), 0.0d);
    }

    final double realResult = 0.5d * Math.log(this.real * this.real + this.imaginary
        * this.imaginary);
    final double imaginaryResult = Math.atan2(this.imaginary, this.real);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の常用対数(底を10とする対数)を求める. log_10(x) を求める。
   * 
   * @return 現在のオブジェクトの常用対数をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex log10() {
    final double logTen = Math.log(10.0d);

    if (this.real > 0.0d && this.imaginary == 0.0d) {
      return new DoubleComplex(Math.log(this.real) / logTen, 0.0d);
    }

    final double r = this.log().real / logTen;
    final double i = this.log().imaginary / logTen;
    return new DoubleComplex(r, i);
  }

  /**
   * 複素数の底を2とする対数を求める. <br />
   * log_2(x) を求める。
   * 
   * @return 現在のオブジェクトの底を2とする対数をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex log2() {

    final double logTwo = Math.log(2.0d);

    if (this.real > 0.0d && this.imaginary == 0.0d) {
      return new DoubleComplex(Math.log(this.real) / logTwo, 0.0d);
    }

    final double r = this.log().real / logTwo;
    final double i = this.log().imaginary / logTwo;
    return new DoubleComplex(r, i);
  }

  /**
   * 現在のオブジェクトの値をdouble型の複素数の絶対値としてlong型に変換して返す.
   * 
   * @since 1.1
   * @return 複素数の絶対値をlong型で返す。
   */
  @Override
  public long longValue() {
    return new Double(this.abs()).longValue();
  }

  /**
   * 2つのComplex値の絶対値のうち大きい方を返す.
   * 
   * @since 1.1
   * @param b
   *          比較対照のComplex(複素数)型の値のひとつ
   * @return 現在のオブジェクトとbのどちらか大きい方
   */
  public double max(final DoubleComplex b) {
    return Math.max(this.abs(), b.abs());
  }

  /**
   * 2つのComplex値の絶対値のうち小さい方を返す.
   * 
   * @since 1.1
   * @param b
   *          比較対照のComplex(複素数)型の値のひとつ
   * @return 現在のオブジェクトとbのどちらか小さい方
   */
  public double min(final DoubleComplex b) {
    return Math.min(this.abs(), b.abs());
  }

  /**
   * 複素数の乗算.
   * 
   * @since 1.1
   * @param b
   *          乗算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を乗算した複素数を返す。
   */
  public DoubleComplex multiply(final DoubleComplex b) {
    final double x = this.real * b.real - this.imaginary * b.imaginary;
    final double y = this.real * b.imaginary + b.real * this.imaginary;
    return new DoubleComplex(x, y);
  }

  /**
   * 現在のオブジェクトの符号に負を掛けたComplexオブジェクトを返す. 値が {@code -this (this.new DoubleComplex(-1.0d, 0.0d))}
   * のComplexを返す
   * 
   * @since 1.1
   * @return -this
   */
  public DoubleComplex negate() {
    return new DoubleComplex(-this.real, -this.imaginary);
  }

  /**
   * 複素数のべき乗を複素数乗で求める. 複素数の累乗にはドモアブルの定理を使用している。
   * 
   * @since 1.1
   * @param b
   *          累乗したいComplex(複素数)型の値
   * @return 現在のオブジェクトの複素数の複素数(Complex型)乗をComplex型(複素数)で返す。
   */
  public DoubleComplex pow(final DoubleComplex b) {
    DoubleComplex result;
    if (this.equals(new DoubleComplex()) && b.equals(new DoubleComplex())) {
      result = new DoubleComplex(1.0d, 0.0d);
    } else if (this.imaginary == 0.0d && b.real == 1.0d && b.imaginary == 0.0d) {
      result = this;
    } else if (this.equals(new DoubleComplex()) && b.real < 0.0d && b.imaginary == 0.0d) {
      result = new DoubleComplex(Double.POSITIVE_INFINITY, 0.0d);
    } else if (this.equals(new DoubleComplex()) && b.real > 0.0d && b.imaginary == 0.0d) {
      result = new DoubleComplex();
    } else if (this.real < 0.0d && this.imaginary == 0.0d
        && b.real == Long.valueOf(Double.valueOf(b.real).longValue()).doubleValue()
        && b.imaginary == 0.0d) {

      final DoubleComplex c = new DoubleComplex(Math.pow(Math.abs(this.real), b.real), 0.0d);

      if ((long) b.real % 2 == 0) {
        result = c;
      } else {
        result = c.negate();
      }

    } else {
      final double u = b.real;
      final double v = b.imaginary;
      final double r = this.abs();
      final double theta = Math.atan2(this.imaginary, this.real);
      final double temp = Math.exp(u * Math.log(r) - v * theta);
      final double value = v * Math.log(r) + u * theta;
      final double x = temp * Math.cos(value);
      final double y = temp * Math.sin(value);
      result = new DoubleComplex(x, y);
    }
    return result;
  }

  /**
   * 複素数のべき乗を実数乗で求める. 複素数の累乗にはドモアブルの定理を使用している。
   * 
   * @since 1.1
   * @param d
   *          累乗したいdouble型の値
   * @return 現在のオブジェクトの複素数の実数(double型)乗をComplex型(複素数)で返す。
   */
  public DoubleComplex pow(final double d) {
    // if( this.equals(new DoubleComplex()) && d == 0.0d){
    // return new DoubleComplex(1.0d, 0.0d);
    // }
    final double r = this.abs();
    final double theta = Math.atan2(this.imaginary, this.real);
    final double x = Math.pow(r, d) * Math.cos(d * theta);
    final double y = Math.pow(r, d) * Math.sin(d * theta);
    return new DoubleComplex(x, y);
  }

  /**
   * 複素数のべき乗を整数乗で求める. 引数を0にすると実部1,虚部0の複素数を返す。
   * 
   * @since 1.1
   * @param n
   *          べき乗したい整数値
   * @return 現在のオブジェクトの複素数を整数乗した値を返す。
   */
  public DoubleComplex pow(final int n) {
    DoubleComplex c = this;
    DoubleComplex result;
    if (n == 0) {
      result = DoubleComplex.ONE;
    } else if (n == 1) {
      result = this;
    } else if (n > 1) {
      for (int i = 1; i < n; i++) {
        c = this.multiply(c);
      }
      result = c;
    } else {
      final double denominator = this.real * this.real + this.imaginary * this.imaginary;
      final DoubleComplex inverseC = new DoubleComplex(this.conjugate().real / denominator, this
          .conjugate().imaginary
          / denominator);
      DoubleComplex next = inverseC;
      for (int i = -1; i > n; i--) {
        next = inverseC.multiply(next);
      }
      result = next;
    }
    return result;
  }

  /**
   * 複素数のサイン(正弦)を求める. sin(x) を求める.
   * 
   * @return 現在のオブジェクトのサイン(正弦)をComplex型(複素数)で返す.
   * @since 1.1
   */
  public DoubleComplex sin() {

    if (this.imaginary == 0.0d) {
      return new DoubleComplex(Math.sin(this.real), 0.0d);
    }

    final double e = Math.exp(this.imaginary);
    final double f = 1 / e;
    final double imaginaryResult = 0.5d * Math.cos(this.real) * (e - f);
    final double realResult = 0.5d * Math.sin(this.real) * (e + f);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の hyperbolic sine(双曲線正弦)を求める. sinh(x) を求める。
   * 
   * @return 現在のオブジェクトのhyperbolic sine(双曲線正弦)をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex sinh() {
    final double e = Math.exp(this.real);
    final double f = 1 / e;
    final double realResult = 0.5d * (e - f) * Math.cos(this.imaginary);
    final double imaginaryResult = 0.5d * (e + f) * Math.sin(this.imaginary);
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の平方根を求める. 複素数の平方にはドモアブルの定理を使用している.
   * October.13.2002 桁落ち対策対応済み.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の平方根を返す。
   */
  public DoubleComplex sqrt() {
    double x, y;

    DoubleComplex result;
    final double r = this.abs();
    final double w = Math.sqrt(r + Math.abs(this.real));
    if (this.equals(DoubleComplex.ZERO)) {
      result = DoubleComplex.ZERO;
    } else if (this.equals(DoubleComplex.ONE)) {
      result = DoubleComplex.ONE;
    } else if (this.real > 0 && this.imaginary == 0) {
      result = new DoubleComplex(Math.sqrt(this.real), 0.0d);
    } else if (this.real > 0) {
      x = DoubleComplex.SQRT05 * w;
      y = DoubleComplex.SQRT05 * this.imaginary / w;
      result = new DoubleComplex(x, y);
    } else {
      x = DoubleComplex.SQRT05 * Math.abs(this.imaginary) / w;
      if (this.imaginary >= 0) {
        y = DoubleComplex.SQRT05 * w;
      } else {
        y = -DoubleComplex.SQRT05 * w;
      }
      result = new DoubleComplex(x, y);
    }
    return result;
  }

  /**
   * 複素数の減算.
   * 
   * @since 1.1
   * @param b
   *          減算する複素数
   * @return 現在のオブジェクトの複素数から指定された複素数を減算した複素数を返す。
   */
  public DoubleComplex subtract(final DoubleComplex b) {
    return new DoubleComplex(this.real - b.real, this.imaginary - b.imaginary);
  }

  /**
   * 現在のオブジェクトの実部と虚部を交換したComplexオブジェクトを返す.
   * 
   * @since 1.1
   * @return 現在のComplexオブジェクトから実部と虚部を交換したComplexオブジェクトを返す
   */
  public DoubleComplex swap() {
    return new DoubleComplex(this.imaginary, this.real);
  }

  /**
   * 複素数のタンジェント(正接)を求める.
   * tan(x) を求める。
   * 
   * @return 現在のオブジェクトのタンジェント(正接)をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex tan() {

    if (this.imaginary == 0.0d) {
      return new DoubleComplex(Math.tan(this.real), 0.0d);
    }

    final double half = 0.5;
    final double e = Math.exp(2 * this.imaginary);
    final double f = 1 / e;
    final double d = Math.cos(2 * this.real) + half * (e + f);
    final double realResult = Math.sin(2 * this.real) / d;
    final double imaginaryResult = half * (e - f) / d;
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の hyperbolic tangent(双曲線正接)を求める.
   * sinh(x) を求める。
   * 
   * @return 現在のオブジェクトのhyperbolic tangent(双曲線正接)をComplex型(複素数)で返す。
   * @since 1.1
   */
  public DoubleComplex tanh() {
    final double half = 0.5;
    final double e = Math.exp(2 * this.real);
    final double f = 1 / e;
    final double d = half * (e + f) + Math.cos(2 * this.imaginary);
    final double realResult = half * (e - f) / d;
    final double imaginaryResult = Math.sin(2 * this.imaginary) / d;
    return new DoubleComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数 z = x + i * y を文字列に変換.
   * 
   * @since 1.1
   * @return "実部 + i虚部" 形式で出力し String 型で返す。
   */
  @Override
  public String toString() {

    // NumberFormat df = DecimalFormat.getInstance();
    // df.format(abs())
    // df.parse("-###.##");

    String operator = "+";
    if (this.imaginary < 0.0d) {
      operator = " ";
    }

    final String realResult = Double.toString(this.real);
    final String imaginaryResult = Double.toString(this.imaginary);

    return new StringBuilder(realResult).append(operator).append(imaginaryResult).append("i")
        .toString();
  }

  /**
   * ハッシュコードを返す.
   * 
   * @return ハッシュコード
   * @since 2004/08/24
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.real).append(this.imaginary).toHashCode();
  }

  /**
   * 複素数オブジェクトを生成するFactory Method.
   * 
   * @param x
   *          実数部
   * @param y
   *          虚数部
   * @return 複素数オブジェクト
   * @since 2007/02/23 17:06:58
   */
  public static DoubleComplex valueOf(final double x, final double y) {
    return new DoubleComplex(x, y);
  }
}
// class ComplexDemo {
// public static void main(String[] args){
// DoubleComplex complex1 = new DoubleComplex(1,2);
// DoubleComplex complex2 = new DoubleComplex(3,4);
// System.out.println("complex1 = " + complex1.getReal() + " + i" +
// complex1.getImaginary() );
// System.out.println("complex2 = " + complex2.getReal() + " + i" +
// complex2.getImaginary() );
//
// DoubleComplex complexE = complex1.add( complex2 );
// System.out.println("add complex2 = " + complexE.getReal() + " + i" +
// complexE.getImaginary() );
//
// DoubleComplex complexF = complex1.divide( complex2 );
// System.out.println("divide = " + complexF.getReal() + " + i" +
// complexF.getImaginary() );
//
// DoubleComplex complexI = complex1.conjugate();
// System.out.println("conjugate = " + complexI.getReal() + " + i" +
// complexI.getImaginary() );
//
// double abs = complex1.abs();
// System.out.println("abs = " + abs );
//
// double arg = complex1.arg();
// System.out.println("arg = " + arg );
//
// DoubleComplex p1 = complex1.pow(-1);
// System.out.println("p1 = " + p1.getReal() + " + " + p1.getImaginary() + "i"
// );
//
// DoubleComplex p2 = complex1.pow(0);
// System.out.println("p2 = " + p2.getReal() + " + " + p2.getImaginary() + "i"
// );
//
// DoubleComplex p3 = complex1.pow( 1.1 );
// System.out.println("p3 = " + p3.getReal() + " + " + p3.getImaginary() + "i"
// );
//
// DoubleComplex p4 = complex1.sqrt();
// System.out.println("p4 = " + p4.getReal() + " + " + p4.getImaginary() + "i"
// );
//
// DoubleComplex p5 = complex1.pow(complex1);
// System.out.println("p5 = " + p5.getReal() + " + " + p5.getImaginary() + "i"
// );
//
// double[] arrayR = new double[10];
// double[] arrayI = new double[10];
//
// DoubleComplex[] p6 = new DoubleComplex[ arrayR.length ];
//
// for(int i = 0 ; i < arrayR.length ; i++ ){
// arrayR[i] = i;
// arrayI[i] = -i;
// }
//
// for(int i = 0 ; i < arrayR.length ; i++ ){
// p6[i] = new DoubleComplex( arrayR[i], arrayI[i] );
//
// }
//
// double[] complexArrayR = new double[ arrayR.length ];
// double[] complexArrayI = new double[ arrayR.length ];
//
// for(int i = 0 ; i < arrayR.length ; i++ ){
// complexArrayR[i] = p6[i].getReal();
// complexArrayI[i] = p6[i].getImaginary();
// }
// for(int i = 0 ; i < complexArrayR.length ; i++ ){
// System.out.println("complexArrayR[" + i + "]=" + complexArrayR[i]
// + "complexArrayI[" + i + "]=" + complexArrayI[i] );
// }
//
// DoubleComplex sin1 = new DoubleComplex(1,1);
// System.out.println( sin1.sin().toString() );
// System.out.println( sin1.cos().toString() );
// System.out.println( sin1.tan().toString() );
// System.out.println( sin1.sinh().toString() );
// System.out.println( sin1.cosh().toString() );
// System.out.println( sin1.tanh().toString() );
// System.out.println( sin1.acos().toString() );
// sin1 = new DoubleComplex(3,3);
// for(int i = -10; i <= 10; i++ ){
// for(int j = -10; j <= 10; j++ ){
// sin1 = new DoubleComplex(i, j);
// System.out.println("acos(" + i + ", " + j + "i)=" + sin1.acos().toString() );
// }
// }
//
// for(int i = -10; i <= 10; i++ ){
// for(int j = -10; j <= 10; j++ ){
// sin1 = new DoubleComplex(i, j);
// System.out.println("asin(" + i + ", " + j + "i)=" + sin1.asin().toString() );
// }
// }
// }
// }

