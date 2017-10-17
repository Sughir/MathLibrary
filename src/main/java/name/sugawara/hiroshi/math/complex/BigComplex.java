package name.sugawara.hiroshi.math.complex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import name.sugawara.hiroshi.math.function.decimal.BigMath;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 複素数を扱うクラス(BigDecimalを使用). <br />
 * <code>
 * //使用例 : //3.3-4&lt;i&gt;i&lt;/i&gt; (実部を 3.3, 虚部を -4,虚数をiとする)の
 * 複素数を作成する。 BigComplex bigComplex1 = new BigComplex( 3.3, -4);
 * //この複素数 complex1 から実部と虚部を取り出し &lt;i&gt;x + yi&lt;/i&gt;
 * //の形式で表示する。
 * java.math.BigDecimal x = bigComplex1.getReal();
 * java.math.BigDecimal y = bigComplex1.getImaginary();
 * System.out.println( x + &quot; + &quot; + y + &quot;i&quot;);
 * //複素数の除算
 * //bigComplex1 から bigComplex2 を除算しそれをbigComplex3に代入する。
 * BigComplex bigComplex2 = new BigComplex( 3.3, -4 );
 * BigComplex bigComplex3 = bigComplex1.divide(bigComplex2, BigDecimal.ROUND_HALF_EVEN);
 * </code> <h5>誤差</h5>
 * 
 * <pre>
 *                コンストラクタに誤差情報を入れなかったときの誤差のデフォルト値.
 *                項を演算する回数 N = 1000
 *                 epsilon = 0.0001
 *                機械エプシロン 
 *                scale = 1000;
 *                BigDecimalのスケール 
 *                roundingMode = BigDecimal.ROUND_HALF.EVEN BigDecimalの丸めモード
 * </pre>
 * 
 * <em>これらは、演算するメソッドによって、必要、不必要なものがある。</em>
 * 
 * @author Hiroshi Sugawara
 * @version $Id: BigComplex.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */
public final strictfp class BigComplex extends Complex implements Cloneable,
    Comparable<BigComplex>, Serializable {

  /**
   * 虚数単位.
   * 
   * @since 1.1
   */
  public static final BigComplex   I                 = new BigComplex(BigDecimal.ONE);

  /**
   * 零.
   * 
   * @since 2007/03/05 17:53:03
   */
  public static final BigComplex   ZERO              = new BigComplex(BigDecimal.ZERO,
                                                         BigDecimal.ZERO);

  /**
   * 1.
   * 
   * @since 2007/03/05 17:53:03
   */
  public static final BigComplex   ONE               = new BigComplex(BigDecimal.ONE,
                                                         BigDecimal.ZERO);

  /**
   * シリアルバージョンID.
   * 
   * @since 2005/12/06 14:40:11
   */
  private static final long        serialVersionUID  = 8376686889110057014L;

  /**
   * コンストラクタに誤差情報を入れなかったときの誤差のデフォルト値.
   */
  private static final BigInteger  DEFINED_N         = new BigInteger("1000");

  /**
   * デフォルトの機械イプシロン.
   */
  private static final BigDecimal  DEFINED_EPSILON   = new BigDecimal("0.0001");

  /**
   * デフォルトのスケール.
   */
  private static final int         DEFINED_SCALE     = 1000;

  /**
   * デフォルトのMathContextオブジェクト.
   * 
   * @since 2005/07/25 15:18:55
   */
  private static final MathContext MATH_CONTEXT      = new MathContext(BigComplex.DEFINED_SCALE,
                                                         RoundingMode.HALF_EVEN);

  /**
   * 許容誤差.
   */
  private static final Precision   DEFINED_PRECISION = new NandEpsilon(BigComplex.DEFINED_N,
                                                         BigComplex.DEFINED_EPSILON,
                                                         BigComplex.MATH_CONTEXT);

  /**
   * 実数部.
   */
  private BigDecimal               real;

  /**
   * 虚数部.
   */
  private BigDecimal               imaginary;

  /**
   * 誤差,精度情報.
   */
  private Precision                precision;

  /**
   * 初等関数.
   */
  private final BigMath            math;

  /**
   * 実部、虚部ともに0の複素数を生成(0の実数を生成).
   * 
   * @since 2005/01/04
   */
  @Deprecated
  public BigComplex() {
    this(BigComplex.DEFINED_PRECISION);
  }

  /**
   * 精度を指定して実部、虚部ともに0の複素数を生成(0の実数を生成).
   * 
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigComplex(final Precision precision) {
    this(BigDecimal.ZERO, BigDecimal.ZERO, precision);
  }

  /**
   * 精度を指定して複素数を生成(0の実数を生成).
   * 
   * @param precision
   *          精度
   * @param x
   *          実部
   * @param y
   *          虚部
   * @since 1.1
   */
  public BigComplex(final BigDecimal x, final BigDecimal y, final Precision precision) {
    this.real = x;
    this.imaginary = y;
    this.precision = precision;
    this.math = new BigMath(precision);
  }

  /**
   * 実部が0でBigDecimal型引数で指定した虚部の複素数(純虚数)を生成.
   * 
   * @since 1.1
   * @param y
   *          yに虚部を指定する。
   */
  public BigComplex(final BigDecimal y) {
    this(BigDecimal.ZERO, y, BigComplex.DEFINED_PRECISION);
  }

  /**
   * 精度を指定して実部が0でBigDecimal型引数で指定した虚部の複素数(純虚数)を生成.
   * 
   * @param precision
   *          精度
   * @param y
   *          yに虚部を指定する。
   * @since 1.1
   */
  public BigComplex(final BigDecimal y, final Precision precision) {
    this(BigDecimal.ZERO, y, precision);
  }

  /**
   * 実部、虚部共にBigDecimal型引数で指定した複素数を生成.
   * 
   * @since 1.1
   * @param x
   *          xに実部を指定する。
   * @param y
   *          yに虚部を指定する。
   */
  public BigComplex(final BigDecimal x, final BigDecimal y) {
    this(x, y, BigComplex.DEFINED_PRECISION);
  }

  /**
   * 指定されたオブジェクトと現在のオブジェクトの要素 (複素数の実部、虚部の値を)が等しいかどうかを比較する. Object#getClass()を使用しているので、BigComplexクラスの
   * 派生クラスのオブジェクトとを比較する場合は必ずfalseとなる.
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
      final BigComplex c = (BigComplex) obj;
      if (this.real.equals(c.real) && this.imaginary.equals(c.imaginary)
          && this.precision.equals(c.precision)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 現在のオブジェクトより実部を取り出す.
   * 
   * @since 1.1
   * @return 実部を返す
   */
  public BigDecimal getReal() {
    return this.real;
  }

  /**
   * 現在のオブジェクトより虚部を取り出す.
   * 
   * @since 1.1
   * @return 虚部を返す
   */
  public BigDecimal getImaginary() {
    return this.imaginary;
  }

  /**
   * 現在のオブジェクトより誤差情報を取り出す.
   * 
   * @since 1.1
   * @return 誤差情報
   */
  public Precision getPrecision() {
    return this.precision;
  }

  /**
   * 現在のオブジェクトの実部と虚部を交換したComplexオブジェクトを返す.
   * 
   * @since 1.1
   * @return 現在のComplexオブジェクトから実部と虚部を交換したComplexオブジェクトを返す
   */
  public BigComplex swap() {
    return new BigComplex(this.imaginary, this.real);
  }

  /**
   * この BigComplex の実数部の「スケール」を返す.
   * 
   * @return この BigComplex 実数部のスケール
   * @since 1.1
   */
  public int realScale() {
    return this.real.scale();
  }

  /**
   * この BigComplex の虚数部の「スケール」を返す.
   * 
   * @return この BigComplexの 虚数部のスケール
   * @since 1.1
   */
  public int imaginaryScale() {
    return this.imaginary.scale();
  }

  /**
   * この BigComplex の実数部と虚数部との「スケール」のうち、大きい方を返す.
   * 
   * @return この BigComplex の実数部、虚数部のスケールのどちらか大きい方
   * @since 1.1
   */
  public int scale() {
    return Math.max(this.imaginary.scale(), this.real.scale());
  }

  /**
   * 複素数の加算. <i>c = a + d </i>
   * 
   * @since 1.1
   * @param b
   *          加算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を加算した複素数を返す。
   */
  public BigComplex add(final BigComplex b) {
    final BigDecimal x = this.real.add(b.real);
    final BigDecimal y = this.imaginary.add(b.imaginary);
    return new BigComplex(x, y, this.precision);
  }

  /**
   * 複素数の減算.
   * 
   * @since 1.1
   * @param b
   *          減算する複素数
   * @return 現在のオブジェクトの複素数から指定された複素数を減算した複素数を返す。
   */
  public BigComplex subtract(final BigComplex b) {
    final BigDecimal x = this.real.subtract(b.real);
    final BigDecimal y = this.imaginary.subtract(b.imaginary);
    return new BigComplex(x, y, this.precision);
  }

  /**
   * 複素数の乗算.
   * 
   * @since 1.1
   * @param b
   *          乗算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を乗算した複素数を返す。
   */
  public BigComplex multiply(final BigComplex b) {
    final BigDecimal xTemp1 = this.real.multiply(b.real);
    final BigDecimal xTemp2 = this.imaginary.multiply(b.imaginary);
    final BigDecimal x = xTemp1.subtract(xTemp2);

    final BigDecimal yTemp1 = this.real.multiply(b.imaginary);
    final BigDecimal yTemp2 = b.real.multiply(this.imaginary);
    final BigDecimal y = yTemp1.add(yTemp2);

    return new BigComplex(x, y, this.precision);
  }

  /**
   * 複素数の除算.
   * 
   * @since 1.1
   * @param b
   *          除算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を除算した複素数を返す。
   */
  public BigComplex divide(final BigComplex b) {
    final BigDecimal bReal = b.real;
    final BigDecimal bImaginary = b.imaginary;
    final BigDecimal geometricNotMean = bReal.multiply(bReal).add(bImaginary.multiply(bImaginary));

    final BigDecimal tempX1 = bReal.multiply(this.real);
    final BigDecimal tempX2 = this.imaginary.multiply(bImaginary);
    BigDecimal x;
    x = (tempX1.add(tempX2)).divide(geometricNotMean, this.precision.getMathContext());

    final BigDecimal tempY1 = bReal.multiply(this.imaginary);
    final BigDecimal tempY2 = this.real.multiply(bImaginary);
    final BigDecimal y = (tempY1.subtract(tempY2)).divide(geometricNotMean, this.precision
        .getMathContext());

    return new BigComplex(x, y, this.precision);
  }

  /**
   * 複素数の共役複素数を求める.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの共役複素数を返す。
   */
  public BigComplex conjugate() {
    return new BigComplex(this.real, this.imaginary.negate(), this.precision);
  }

  /**
   * 複素数の絶対値を求める.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の絶対値を返す。
   */
  public BigDecimal abs() {
    return this.math.hypot2(this.real, this.imaginary);

  }

  /**
   * 複素数のべき乗を整数乗で求める.
   * 
   * @param n
   *          BigInteger型整数指数
   * @since 1.1 引数を0にすると実部1,虚部0の複素数を返す。
   * @return 現在のオブジェクトの複素数を整数乗した値を返す。
   * @deprecated 代わりに{@link BigComplexUtils#pow(BigComplex,BigComplex)}を使用
   * @see BigComplexUtils#pow(BigComplex,BigComplex)
   */
  @Deprecated
  public BigComplex pow(final BigInteger n) {

    BigComplex result;

    BigComplex c = this;
    if (n.compareTo(BigInteger.ZERO) == 0) {
      result = new BigComplex(BigDecimal.ONE, BigDecimal.ZERO);
    } else if (n.compareTo(BigInteger.ONE) == 0) {
      result = this;
    } else if (n.compareTo(BigInteger.ONE) > 0) {
      for (BigInteger i = BigInteger.ONE; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
        c = this.multiply(c);
      }
      result = c;
    } else {

      final BigDecimal geometricNotMean = this.real.multiply(this.real).add(
          this.imaginary.multiply(this.imaginary));

      final BigDecimal realResult = this.conjugate().real.divide(geometricNotMean, this.precision
          .getMathContext());
      final BigDecimal imaginaryResult = this.conjugate().imaginary.divide(geometricNotMean,
          this.precision.getMathContext());

      final BigComplex inverseC = new BigComplex(realResult, imaginaryResult);
      BigComplex next = inverseC;
      for (BigInteger i = BigInteger.ZERO; i.compareTo(n) == -1; i = i.subtract(BigInteger.ONE)) {
        next = inverseC.multiply(next);
      }
      result = next;
    }
    return result;
  }

  /**
   * 複素数の平方根を求める. 複素数の平方にはドモアブルの定理を使用している。
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の平方根を返す。
   * @deprecated 代わりに{@link BigComplexUtils#sqrt(BigComplex)}を使用
   * @see BigComplexUtils#sqrt(BigComplex)
   */
  @Deprecated
  public BigComplex sqrt() {
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal rootTwo = this.math.sqrt(two);
    final BigDecimal r = this.abs();

    final BigDecimal realResult = this.math.sqrt(r.add(this.real));
    final BigDecimal imaginaryResult = this.math.sqrt(r.subtract(this.imaginary));

    return new BigComplex(
        rootTwo.multiply(realResult).divide(two, this.precision.getMathContext()), rootTwo
            .multiply(imaginaryResult).divide(two, this.precision.getMathContext()));
  }

  /**
   * 複素数のべき乗を実数乗で求める. 複素数の累乗にはドモアブルの定理を使用している.
   * 
   * @since 1.1
   * @param d
   *          累乗したいBigDecimal型の値
   * @return 現在のオブジェクトの複素数の実数(double型)乗をComplex型(複素数)で返す。
   * @deprecated 代わりに{@link BigComplexUtils#pow(BigDecimal)}を使用
   * @see BigComplexUtils#pow(BigDecimal)
   */
  @Deprecated
  public BigComplex pow(final BigDecimal d) {
    final BigDecimal r = this.abs();
    final BigDecimal theta = this.math.atan2(this.imaginary, this.real);
    final BigDecimal x = this.math.pow(r, d).multiply(this.math.cos(d.multiply(theta)));
    final BigDecimal y = this.math.pow(r, d).multiply(this.math.sin(d.multiply(theta)));
    return new BigComplex(x, y);
  }

  /**
   * 複素数のべき乗を複素数乗で求める. 複素数の累乗にはドモアブルの定理を使用している.
   * 
   * @since 1.1
   * @param b
   *          累乗したいBigComplex(複素数)型の値
   * @return 現在のオブジェクトの複素数の複素数(BigComplex型)乗をBigComplex型(複素数)で返す。
   * @deprecated 代わりに{@link BigComplexUtils#pow(BigComplex,BigComplex)}を使用
   * @see BigComplexUtils#pow(BigComplex,BigComplex)
   */
  @Deprecated
  public BigComplex pow(final BigComplex b) {
    final BigDecimal u = b.real;
    final BigDecimal v = b.imaginary;
    final BigDecimal r = this.abs();
    final BigDecimal theta = this.math.atan2(this.imaginary, this.real);
    final BigDecimal temp = this.math.exp(u.multiply(this.math.log(r)).subtract(v.multiply(theta)));
    final BigDecimal value = v.multiply(this.math.log(r)).add(u.multiply(theta));
    final BigDecimal x = temp.multiply(this.math.cos(value));
    final BigDecimal y = temp.multiply(this.math.sin(value));
    return new BigComplex(x, y);
  }

  /**
   * 2つのBigComplex値の絶対値のうち大きい方を返す.
   * 
   * @since 1.1
   * @param b
   *          比較対照のBigComplex(複素数)型の値のひとつ
   * @return 現在のオブジェクトとbのどちらか大きい方
   */
  public BigComplex max(final BigComplex b) {
    final boolean result = this.abs().max(b.abs()).compareTo(this.abs()) >= 0;
    if (result) {
      return this;
    } else {
      return b;
    }
  }

  /**
   * 2つのBigComplex値の絶対値のうち小さい方を返す.
   * 
   * @since 1.1
   * @param b
   *          比較対照のBigComplex(複素数)型の値のひとつ
   * @return 現在のオブジェクトとbのどちらか小さい方
   */
  public BigComplex min(final BigComplex b) {
    final boolean result = this.abs().min(b.abs()).compareTo(this.abs()) >= 0;
    if (result) {
      return this;
    } else {
      return b;
    }
  }

  /**
   * 自然対数の底の複素数乗を求める.
   * exp(x) を求める。 複素数の累乗にはドモアブルの定理を使用している。
   * 
   * @return 自然対数の底の現在の複素数オブジェクト乗をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#exp(BigComplex)}を使用
   * @see BigComplexUtils#exp(BigComplex)
   */
  @Deprecated
  public BigComplex exp() {
    final BigDecimal a = this.math.exp(this.real);
    final BigDecimal realResult = a.multiply(this.math.cos(this.imaginary));
    final BigDecimal imaginaryResult = a.multiply(this.math.sin(this.imaginary));
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の自然対数を求める. <br />
   * log(x) を求める。
   * 
   * @return 現在のオブジェクトの自然対数をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#log(BigComplex)}を使用
   * @see BigComplexUtils#log(BigComplex)
   */
  @Deprecated
  public BigComplex log() {
    final BigDecimal realResult = new BigDecimal("0.5").multiply(this.math.log(this.real.multiply(
        this.real).add(this.imaginary.multiply(this.imaginary))));

    final BigDecimal imaginaryResult = this.math.atan2(this.imaginary, this.real);
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の常用対数(底を10とする対数)を求める. <br />
   * log_10(x) を求める。
   * 
   * @return 現在のオブジェクトの常用対数をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#log10(BigComplex)}を使用
   * @see BigComplexUtils#log10(BigComplex)
   */
  @Deprecated
  public BigComplex log10() {
    final BigComplex logTen = new BigComplex(this.math.log(BigDecimal.TEN), BigDecimal.ZERO);
    return this.divide(logTen);
  }

  /**
   * 複素数の底を2とする対数を求める. <br />
   * log_2(x) を求める。
   * 
   * @return 現在のオブジェクトの底を2とする対数をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#log2(BigComplex)}を使用
   * @see BigComplexUtils#log2(BigComplex)
   */
  @Deprecated
  public BigComplex log2() {
    final BigComplex logTwo = new BigComplex(this.math.log(new BigDecimal("2.0")), BigDecimal.ZERO,
        this.precision);
    return this.divide(logTwo);
  }

  /**
   * 複素数のサイン(正弦)を求める. <br />
   * sin(x) を求める。
   * 
   * @return 現在のオブジェクトのサイン(正弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#sin(BigComplex)}を使用
   * @see BigComplexUtils#sin(BigComplex)
   */
  @Deprecated
  public BigComplex sin() {
    final BigDecimal e = this.math.exp(this.imaginary);
    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal imaginaryResult = new BigDecimal("0.5").multiply(this.math.cos(this.real)
        .multiply(e.subtract(f)));
    final BigDecimal realResult = new BigDecimal("0.5").multiply(this.math.sin(this.real))
        .multiply(e.add(f));
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数のコサイン(余弦)を求める. <br />
   * cos(x) を求める。
   * 
   * @return 現在のオブジェクトのコサイン(余弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#cos(BigComplex)}を使用
   * @see BigComplexUtils#cos(BigComplex)
   */
  @Deprecated
  public BigComplex cos() {
    final BigDecimal e = this.math.exp(this.imaginary);

    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());

    final BigDecimal imaginaryResult = new BigDecimal("0.5").multiply(this.math.sin(this.real))
        .multiply(f.subtract(e));

    final BigDecimal realResult = new BigDecimal("0.5").multiply(this.math.cos(this.real))
        .multiply(f.add(e));

    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数のタンジェント(正接)を求める. <br />
   * tan(x) を求める。
   * 
   * @return 現在のオブジェクトのタンジェント(正接)をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#tan(BigComplex)}を使用
   * @see BigComplexUtils#tan(BigComplex)
   */
  @Deprecated
  public BigComplex tan() {
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal e = this.math.exp(this.imaginary.multiply(two));
    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal d = this.math.cos(this.real.multiply(two)).add(
        new BigDecimal("0.5").multiply(e.add(f)));
    final BigDecimal realResult = this.math.sin(this.real.multiply(two)).divide(d,
        this.precision.getMathContext());
    final BigDecimal imaginaryResult = new BigDecimal("0.5").multiply(e.subtract(f)).divide(d,
        this.precision.getMathContext());
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の hyperbolic sine(双曲線正弦)を求める. <br />
   * sinh(x) を求める。
   * 
   * @return 現在のオブジェクトのhyperbolic sine(双曲線正弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#sinh(BigComplex)}を使用
   * @see BigComplexUtils#sinh(BigComplex)
   */
  @Deprecated
  public BigComplex sinh() {
    final BigDecimal e = this.math.exp(this.real);
    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal realResult = new BigDecimal("0.5").multiply(e.subtract(f)).multiply(
        this.math.cos(this.imaginary));
    final BigDecimal imaginaryResult = new BigDecimal("0.5").multiply(e.add(f)).multiply(
        this.math.sin(this.imaginary));
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の hyperbolic cosine(双曲線余弦)を求める. <br />
   * sinh(x) を求める。
   * 
   * @return 現在のオブジェクトのhyperbolic cosine(双曲線余弦)をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#cosh(BigComplex)}を使用
   * @see BigComplexUtils#cosh(BigComplex)
   */
  @Deprecated
  public BigComplex cosh() {
    final BigDecimal e = this.math.exp(this.real);

    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());
    final BigDecimal realResult = new BigDecimal("0.5").multiply(e.add(f)).multiply(
        this.math.cos(this.imaginary));
    final BigDecimal imaginaryResult = new BigDecimal("0.5").multiply(e.subtract(f)).multiply(
        this.math.sin(this.imaginary));
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の hyperbolic tangent(双曲線正接)を求める. <br />
   * sinh(x) を求める。
   * 
   * @return 現在のオブジェクトのhyperbolic tangent(双曲線正接)をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#tanh(BigComplex)}を使用
   * @see BigComplexUtils#tanh(BigComplex)
   */
  @Deprecated
  public BigComplex tanh() {
    final BigDecimal two = new BigDecimal("2.0");
    final BigDecimal e = this.math.exp(this.real.multiply(two));

    final BigDecimal f = BigDecimal.ONE.divide(e, this.precision.getMathContext());

    final BigDecimal d = new BigDecimal("0.5").multiply(e.add(f)).add(
        this.math.cos(this.imaginary.multiply(two)));
    final BigDecimal realResult = new BigDecimal("0.5").multiply(e.subtract(f)).divide(d,
        this.precision.getMathContext());
    final BigDecimal imaginaryResult = this.math.sin(this.imaginary.multiply(two)).divide(d,
        this.precision.getMathContext());
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の arc cosine(逆余弦)を求める. acos(x) を求める.
   * 
   * @return 現在のオブジェクトの arc cosine(逆余弦) をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#acos(BigComplex)}を使用
   * @see BigComplexUtils#acos(BigComplex)
   */
  @Deprecated
  public BigComplex acos() {
    final BigDecimal sqrt05 = this.math.sqrt(new BigDecimal("0.5"));
    final BigDecimal x = this.real;
    final BigDecimal y = this.imaginary;
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

    final BigDecimal realResult = this.math.atan2(sqrtImaginary, sqrtReal);
    final BigDecimal imaginaryResult = new BigDecimal("-0.5").multiply(this.math.log(sqrtReal
        .multiply(sqrtReal).add(sqrtImaginary.multiply(sqrtImaginary))));
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数の arc sine(逆正弦)を求める. <br />
   * asin(x) を求める。
   * 
   * @return 現在のオブジェクトの arc sine(逆正弦) をBigComplex型(複素数)で返す。
   * @since 1.1
   * @deprecated 代わりに{@link BigComplexUtils#asin(BigComplex)}を使用
   * @see BigComplexUtils#asin(BigComplex)
   */
  @Deprecated
  public BigComplex asin() {
    final BigDecimal sqrt05 = this.math.sqrt(new BigDecimal("0.5"));
    final BigDecimal x = this.real;
    final BigDecimal y = this.imaginary;
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

    final BigDecimal realResult = this.math.atan2(sqrtImaginary, sqrtReal);
    final BigDecimal imaginaryResult = new BigDecimal("-0.5").multiply(this.math.log(sqrtReal
        .multiply(sqrtReal).add(sqrtImaginary.multiply(sqrtImaginary))));
    return new BigComplex(realResult, imaginaryResult);
  }

  /**
   * 複素数 z = x + i * y を文字列に変換.
   * 
   * @since 1.1
   * @return "実部 + i虚部" 形式で出力し String 型で返す。
   */
  @Override
  public String toString() {
    return this.real.toString() + " + " + this.imaginary.toString() + "i";
  }

  /**
   * BigComplexをComplex型に変換.
   * 
   * @since 1.1
   * @return Complex型で返す。
   */
  public DoubleComplex toComplex() {
    return new DoubleComplex(this.real.doubleValue(), this.imaginary.doubleValue());
  }

  /**
   * 指定されたオブジェクトと現在のオブジェクトの絶対値を比較する.
   * 
   * @param c
   *          指定されたオブジェクト
   * @return このBigComplexの絶対値がobjより小さい場合は負の数、等しい場合は 0、大きい場合は正の数
   * @since 1.1
   */
  public int compareTo(final BigComplex c) {
    if (this.equals(c)) {
      return 0;
    }
    int result;

    if (c != null) {
      if (this.abs().equals(c.abs())) {
        result = 0;
      } else if (this.abs().compareTo(c.abs()) < 0) {
        result = -1;
      } else if (this.abs().compareTo(c.abs()) > 0) {
        result = 1;
      } else {
        // 予期せぬ状況 null値出現など
        throw new ArithmeticException();
      }
    } else {
      throw new NullPointerException();
    }
    return result;
  }

  /**
   * 現在のオブジェクトの符号に負を掛けたBigComplexオブジェクトを返す. <br />
   * 値が -this (this.new BigComplex(-1.0d, 0.0d)) のBigComplexを返す
   * 
   * @since 1.1
   * @return -this
   */
  public BigComplex negate() {
    return this.multiply(new BigComplex(BigDecimal.ONE.negate(), BigDecimal.ZERO));
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
    final int seventeen = 17, tirtySeven = 37;
    return new HashCodeBuilder(seventeen, tirtySeven).append(this.real).append(this.imaginary)
        .append(this.precision).toHashCode();
  }

  /**
   * このオブジェクトの複素数の絶対値をdouble型で返す.
   * 
   * @return double型の値
   * @see java.lang.Number#doubleValue()
   * @since 2007/03/01 11:25:29
   */
  @Override
  public double doubleValue() {
    return this.abs().doubleValue();
  }

  /**
   * このオブジェクトの複素数の絶対値をfloat型で返す.
   * 
   * @return float型の値
   * @see java.lang.Number#floatValue()
   * @since 2007/03/01 11:25:30
   */
  @Override
  public float floatValue() {
    return this.abs().floatValue();
  }

  /**
   * このオブジェクトの複素数の絶対値をint型で返す.
   * 
   * @return int型の値
   * @see java.lang.Number#intValue()
   * @since 2007/03/01 11:25:30
   */
  @Override
  public int intValue() {
    return this.abs().intValue();
  }

  /**
   * このオブジェクトの複素数の絶対値をlong型で返す.
   * 
   * @return long型の値
   * @see java.lang.Number#longValue()
   * @since 2007/03/01 11:25:30
   */
  @Override
  public long longValue() {
    return this.abs().longValue();
  }

}
