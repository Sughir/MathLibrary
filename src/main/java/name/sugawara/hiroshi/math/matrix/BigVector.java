/*
 * 作成日: 2003/09/23
 * 
 */
package name.sugawara.hiroshi.math.matrix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import name.sugawara.hiroshi.math.function.decimal.BigMath;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;
import name.sugawara.hiroshi.math.precision.RoundingError;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BigDecimal型対応ベクトルクラス.
 * 
 * @author sugawara
 * @since 1.1
 * @version $Id: BigVector.java 109 2010-06-13 04:26:48Z sugawara $
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public final class BigVector extends RowVector {

  /**
   * BigDecimal用初頭関数用オブジェクト.
   * 
   * @uml.property name="math"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final BigMath             math              = new BigMath(DEFAULT_PRECISION);

  /**
   * デフォルト機械イプシロン.
   * 
   * @since 2005/10/28 16:32:03
   */
  private static final BigDecimal   DEFAULT_EPSILON   = new BigDecimal("0.0001");

  /**
   * デフォルト丸めモード.
   * 
   * @since 2005/07/25 16:32:25
   */
  private static final RoundingMode DEFAULT_MODE      = RoundingMode.HALF_EVEN;

  /**
   * コンストラクタに誤差情報を入れなかったときの誤差のデフォルト値.
   * 
   * @since 2005/10/28 16:32:32
   */
  private static final BigInteger   DEFAULT_N         = new BigInteger("1000");

  /**
   * デフォルトスケール.
   * 
   * @since 2005/10/28 16:32:46
   */
  private static final int          DEFAULT_SCALE     = 1000;

  /**
   * デフォルトMathContextオブジェクト.
   */
  private static final MathContext  MATH_CONTEXT      = new MathContext(BigVector.DEFAULT_SCALE,
                                                          BigVector.DEFAULT_MODE);

  /**
   * デフォルト精度.
   * 
   * @since 2005/07/25 16:31:01
   */
  private static final Precision    DEFAULT_PRECISION = new NandEpsilon(BigVector.DEFAULT_N,
                                                          BigVector.DEFAULT_EPSILON,
                                                          BigVector.MATH_CONTEXT);

  /**
   * 誤差,精度情報.
   * 
   */
  private Precision                 precision;

  /**
   * ベクトル {@code bigVector}.
   * 
   * @since 2004/08/22
   */
  private BigDecimal[]              bigVector;

  /**
   * 配列から行ベクトルを生成.
   * 
   * @param bigVector
   *          配列
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigVector(final BigDecimal[] bigVector, final Precision precision) {
    this.bigVector = bigVector.clone();
    this.precision = precision;
  }

  /**
   * DoubleMatrix型からBigDecimal型行ベクトルを生成.
   * 
   * @param matrix
   *          Matrix型行列
   * @since 1.1
   */
  public BigVector(final BigMatrix matrix) {
    if (matrix.getColumnDimension() != 1) {
      throw new ArithmeticException("The matrix must be the one dimentional array.");
    }
    BigDecimal[][] bd = matrix.getArray();
    this.bigVector = bd[0];
    this.precision = matrix.getPrecision();

  }

  /**
   * 値がすべて0, サイズが row の行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigVector(final int row, final Precision precision) {
    this(row, new BigDecimal("0.0d"), precision);
  }

  /**
   * 値がすべてs, サイズが row の行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @param s
   *          行ベクトルの値
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigVector(final int row, final BigDecimal s, final Precision precision) {
    BigDecimal[] b = new BigDecimal[row];
    Arrays.fill(b, s);

    this.bigVector = b.clone();
    this.precision = precision;
  }

  /**
   * ベクトルの個々の要素に対する絶対値をベクトル.
   * 
   * @since 1.1
   * @return ベクトル要素に対する絶対値をベクトル
   */
  public BigVector abs() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.bigVector[i].abs();
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆余弦(arc cosine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public BigVector acos() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.acos(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの各要素にvalueを加算する.
   * 
   * @param value
   *          値
   * @return thisの各要素にvalueを加えた値
   * @since 1.1
   */
  public BigVector add(final BigDecimal value) {
    return this.add(new BigVector(this.bigVector.length, value, this.precision));
  }

  /**
   * ベクトル同士の加算.
   * 
   * @param v
   *          値
   * @return this + v を返す.
   * @since 1.1
   */
  public BigVector add(final BigVector v) {
    this.checkVectorSize(v);

    BigDecimal[] bd = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      bd[i] = this.bigVector[i].add(v.bigVector[i]);
    }
    return new BigVector(bd, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆正弦(arc sine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public BigVector asin() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.asin(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆正接(arc tangent).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public BigVector atan() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.atan(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの要素の4象限逆正接 Four Quadrant Arc Tangent.
   * 
   * <pre>
   *        現在のオブジェクトを、ラジアンで表した、arctan関数でyを
   *        変数とするベクトル とし指定された角度の 4象限逆正接 (4象限アークタンジェント)
   *        を返す.
   * </pre>
   * 
   * @since 1.1
   * @param x
   *          ラジアンで表した arctan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarctan関数に代入した配列
   */
  public BigVector atan2(final BigVector x) {
    this.checkVectorSize(x);
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.atan2(this.bigVector[i], x.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの要素の平均(average).
   * 
   * @return 平均
   * @since 1.1
   */
  public BigDecimal avg() {
    int scale = ((RoundingError) this.precision).getScale();
    int mode = ((RoundingError) this.precision).getRoundingMode();
    return this.sum().divide(new BigDecimal(this.bigVector.length), scale, mode);
  }

  /**
   * Check if size(a) == size(b).
   * 
   * @param v
   *          ベクトル
   * @since 2004/08/03
   */
  private void checkVectorSize(final BigVector v) {
    if (this.bigVector.length != v.bigVector.length) {
      throw new IllegalArgumentException("The each vector size must be same.");
    }
  }

  /**
   * オブジェクトのクローン. オブジェクトはディープコピーされる.
   * 
   * @return ベクトル
   * @since 1.1
   * @throws CloneNotSupportedException
   *           クローンをサポートしてないとき
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    super.clone();

    return new BigVector(this.bigVector.clone(), this.precision);
  }

  /**
   * 2つのベクトルを連結する.
   * 
   * @param rear
   *          後方に連結されるベクトル
   * @return 指定された2つのベクトルを連結したベクトルを返す
   * @since 1.1
   */
  public BigVector concatenate(final BigVector rear) {
    int size = this.bigVector.length + rear.bigVector.length;
    BigDecimal[] combine = new BigDecimal[size];
    for (int i = 0; i < size; i++) {
      combine[i] = this.bigVector[i];
    }
    for (int i = 0; i < rear.bigVector.length; i++) {
      combine[i + this.bigVector.length] = rear.bigVector[i];
    }
    return new BigVector(combine, this.precision);
  }

  /**
   * 2つのベクトルのconvolution(畳み込み)を返す. <br />
   * 返されたベクトルのサイズは2つの配列のサイズの和から1を引いた値になる.
   * 
   * @param h
   *          システム(配列)
   * @return サイズが x.bigVector.length + h.bigVector.length - 1 のdouble[]型を返す
   * @since 1.1
   */
  public BigVector convolute(final BigVector h) {
    BigDecimal[] y = new BigDecimal[this.bigVector.length + h.bigVector.length - 1];
    Arrays.fill(y, new BigDecimal("0.0d"));
    for (int n = 0; n < h.bigVector.length; n++) {
      for (int k = 0; k < this.bigVector.length; k++) {
        int hIndex = n - k;
        if ((0 <= hIndex) && (hIndex < h.bigVector.length)) {
          y[n] = y[n].add(this.bigVector[k].multiply(this.bigVector[n - k]));
        }
      }
    }
    return new BigVector(y, this.precision);
  }

  /**
   * ベクトルのauto-correlation(自己相関関数)をベクトルで返す. <br />
   * 返されたベクトルのサイズはベクトルの2倍から1を引いた値になる.
   * 
   * @return サイズが 2 * this.bigVector.length - 1 のdouble[]型を返す
   * @since 1.1
   */
  public BigVector correlate() {
    return this.correlate(this);
  }

  /**
   * 2つのベクトルのcross-correlation(相互相関関数)を返す. <br />
   * 返されたベクトルのサイズは2つのベクトルのサイズの和から1を引いた値になる. <br />
   * システムhに対して現在のオブジェクトを信号としている.
   * 
   * @param h
   *          システム(ベクトル)
   * @return サイズが this.bigVector.length + h.bigVector.length - 1 のdouble[]型を返す
   * @since 1.1
   */
  public BigVector correlate(final BigVector h) {
    return this.convolute(h.fliplr());
  }

  /**
   * ベクトルの個々の要素の余弦(cosine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public BigVector cos() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.cos(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの指定されたインデックスの要素を削除する. <br />
   * 要素を削除するとベクトルのサイズが1小さくなる.
   * 
   * @param index
   *          要素を削除する位置
   * @return 要素を1つ削除されたベクトルを返す
   * @since 1.1
   */
  public BigVector delete(final int index) {
    BigDecimal[] deletedArray = new BigDecimal[this.bigVector.length - 1];

    if (index == deletedArray.length) {
      for (int i = 0; i < deletedArray.length; i++) {
        deletedArray[i] = this.bigVector[i];
      }
    } else {
      for (int i = 0, j = 0; j < deletedArray.length; i++, j++) {
        if (j == index) {
          i++;
          deletedArray[j] = this.bigVector[i];
        } else {
          deletedArray[j] = this.bigVector[i];
        }
      }
    }
    return new BigVector(deletedArray, this.precision);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public BigVector divide(final BigDecimal value) {

    BigVector rv = new BigVector(this.bigVector.length, value, this.precision);
    return this.divide(rv);
  }

  /**
   * 配列どうしの除算 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param v
   *          除算する配列
   * @return 指定された配列aと指定された配列bを除算した配列を返す.
   */
  public BigVector divide(final BigVector v) {
    this.checkVectorSize(v);
    BigDecimal[] bd = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      bd[i] = this.bigVector[i].divide(v.bigVector[i], ((RoundingError) this.precision).getScale(),
          ((RoundingError) this.precision).getRoundingMode());
    }
    return new BigVector(bd, this.precision);
  }

  /**
   * 配列の要素のexp関数 exp(x).
   * 
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列を返す.
   */
  public BigVector exp() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.exp(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの配列の要素を左右逆順にする.
   * 
   * @return 指定された配列要素を逆順にしたベクトルを返す
   * @since 1.1
   */
  public BigVector fliplr() {
    int size = this.bigVector.length;
    BigDecimal[] flipped = new BigDecimal[size];
    for (int i = 0; i < size; i++) {
      flipped[i] = this.bigVector[size - 1 - i];
    }
    return new BigVector(flipped, this.precision);
  }

  /**
   * 行ベクトルの要素を取得する. Get a single element.
   * 
   * @param i
   *          Row index.
   * @return vector(i)
   * @exception ArrayIndexOutOfBoundsException
   * @since 1.1
   */
  public BigDecimal get(final int i) {
    if (i < 0 || this.bigVector.length < i) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return this.bigVector[i];
    }
  }

  /**
   * 行ベクトルから配列に変換する.
   * 
   * @return 配列
   * @since 1.1
   */
  public BigDecimal[] getArray() {
    return this.bigVector.clone();
  }

  /**
   * 始点と終点を指定して部分ベクトルを取り出す. Get a subvector.
   * 
   * @param i0
   *          Initial col index
   * @param i1
   *          Final col index
   * @return x(i0:i1)
   */
  public BigVector getVector(final int i0, final int i1) {
    BigDecimal[] x = new BigDecimal[i1 - i0 + 1];
    try {
      for (int i = i0; i <= i1; i++) {
        x[i - i0] = this.bigVector[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices");
    }
    return new BigVector(x, this.precision);
  }

  /**
   * 特定の列のインデックスの部分ベクトルを取り出す. Get a subvector.
   * 
   * @param col
   *          Array of column indices.
   * @return x(c(:))
   */
  public BigVector getVector(final int[] col) {

    BigDecimal[] x = new BigDecimal[col.length];
    try {
      for (int j = 0; j < col.length; j++) {
        x[j] = this.bigVector[col[j]];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigVector(x, this.precision);
  }

  /**
   * 無限大ノルムを返す.
   * 
   * @since 1.1
   * @return 無限大ノルム
   */
  public BigDecimal infinityNorm() {
    return this.abs().max();
  }

  /**
   * ベクトルの内積.
   * 
   * @return 内積
   * @param v
   *          ベクトル
   * @since 1.1
   */
  public BigDecimal innerProduct(final BigVector v) {
    BigDecimal innerProduct = new BigDecimal("0.0d");
    for (int i = 0; i < this.bigVector.length; i++) {
      innerProduct = innerProduct.add(this.bigVector[i].multiply(v.bigVector[i]));
    }
    return innerProduct;
  }

  /**
   * 配列に指定された値を指定されたインデックスに挿入する. <br />
   * 要素を挿入すると配列のサイズが1大きくなる.
   * 
   * @param element
   *          挿入する要素
   * @param index
   *          要素を挿入する位置
   * @return 要素を挿入された配列を返す
   * @since 1.1
   */
  public BigVector insert(final BigDecimal element, final int index) {
    BigDecimal[] insertedArray = new BigDecimal[this.bigVector.length + 1];
    if (index == insertedArray.length) {
      for (int i = 0; i <= insertedArray.length; i++) {
        if (i != index) {
          insertedArray[i] = this.bigVector[i];
        } else {
          insertedArray[i] = element;
        }
      }
    } else {
      for (int i = 0, j = 0; j < insertedArray.length; i++, j++) {
        if (j == index) {
          insertedArray[j] = element;
          if (j != insertedArray.length) {
            i--;
          }
        } else {
          insertedArray[j] = this.bigVector[i];
        }
      }
    }
    return new BigVector(insertedArray, this.precision);
  }

  /**
   * ベクトルの個々の要素の対数.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する対数
   */
  public BigVector log() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.log(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの最大要素を求める.
   * 
   * @since 1.1
   * @return 最大要素
   */
  public BigDecimal max() {
    BigDecimal max = this.bigVector[0];
    for (int i = 1; i < this.bigVector.length; i++) {
      max = max.max(this.bigVector[i]);
    }
    return max;
  }

  /**
   * ベクトルの最小要素を求める.
   * 
   * @since 1.1
   * @return 最小要素
   */
  public BigDecimal min() {
    BigDecimal min = this.bigVector[0];
    for (int i = 1; i < this.bigVector.length; i++) {
      min = min.min(this.bigVector[i]);
    }
    return min;
  }

  /**
   * ベクトルの実数倍.
   * 
   * @since 1.1
   * @param times
   *          ベクトルを実数倍する値
   * @return ベクトルの個々の要素を実数倍した配列を返す.
   */
  public BigVector multiply(final BigDecimal times) {
    BigVector rv = new BigVector(this.bigVector.length, times, this.precision);
    return this.multiply(rv);
  }

  /**
   * 配列どうしの乗算. 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param multiplier
   *          乗算する配列
   * @return 指定された配列aと指定された配列bを乗算した配列を返す.
   */
  public BigVector multiply(final BigVector multiplier) {
    this.checkVectorSize(multiplier);

    BigDecimal[] bd = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      bd[i] = this.bigVector[i].multiply(multiplier.bigVector[i]);
    }
    return new BigVector(bd, this.precision);
  }

  /**
   * 符号をマイナスにして返す. Unary minus.
   * 
   * @return -this
   */
  public BigVector negate() {

    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.bigVector[i].negate();
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 無限小ノルムを返す.
   * 
   * @since 1.1
   * @return 無限小ノルム
   */
  public BigDecimal negativeInfinityNorm() {
    return this.abs().min();
  }

  /**
   * 2乗ノルムを返す.
   * 
   * @since 1.1
   * @return 2乗ノルム
   */
  public BigDecimal norm() {
    BigVector v = this.abs();
    return this.math.sqrt(v.multiply(v).sum());
  }

  /**
   * n乗ノルムを返す.
   * 
   * @since 1.1
   * @param exponent
   *          累乗
   * @return exponent乗ノルム
   */
  public BigDecimal norm(final BigDecimal exponent) {
    int scale = ((RoundingError) this.precision).getScale();
    int mode = ((RoundingError) this.precision).getRoundingMode();
    return this.math.pow(this.abs().pow(exponent).sum(), new BigDecimal("1.0d").divide(exponent,
        scale, mode));
  }

  /**
   * 1乗ノルムを返す.
   * 
   * @since 1.1
   * @return 1乗ノルム
   */
  public BigDecimal oneNorm() {
    return this.abs().sum();
  }

  /**
   * ベクトル要素の累乗.
   * 
   * @since 1.1
   * @param d
   *          指数
   * @return ベクトルの個々の要素をd乗した配列を返す.
   */
  public BigVector pow(final BigDecimal d) {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];

    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.pow(this.bigVector[i], d);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの要素の積(product).
   * 
   * @return 積
   * @since 1.1
   */
  public BigDecimal product() {
    BigDecimal product = new BigDecimal("0.0d");
    for (int i = 0; i < this.bigVector.length; i++) {
      product = product.multiply(this.bigVector[i]);
    }
    return product;
  }

  /**
   * ベクトルの指定した位置に要素をセットする. Set a single element.
   * 
   * @param i
   *          index.
   * @param s
   *          A(i).
   * @return vector
   * @exception ArrayIndexOutOfBoundsException
   */
  public BigVector set(final int i, final BigDecimal s) {

    BigDecimal[] d = this.bigVector.clone();
    d[i] = s;
    return new BigVector(d, this.precision);
  }

  /**
   * 部分ベクトルをセットする. Set a subvector.
   * 
   * @param i0
   *          Initial index
   * @param i1
   *          Final index
   * @param x
   *          x(i0:i1)
   * @return vector
   * @since 2004/08/03
   */
  public BigVector setVector(final int i0, final int i1, final BigVector x) {
    BigDecimal[] d = this.bigVector.clone();
    try {
      for (int i = i0; i <= i1; i++) {
        d[i] = x.bigVector[i - i0];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    }
    return new BigVector(d, this.precision);
  }

  /**
   * 部分ベクトルをセットする. Set a subvector.
   * 
   * @param col
   *          Array of col indices.
   * @param x
   *          A(c(:))
   * @return vector
   * @since 2004/08/03
   */
  public BigVector setVector(final int[] col, final BigVector x) {
    BigDecimal[] d = this.bigVector.clone();
    try {
      for (int i = 0; i < col.length; i++) {
        d[col[i]] = x.bigVector[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    }
    return new BigVector(d, this.precision);
  }

  /**
   * ベクトルの個々の要素の正弦(sine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public BigVector sin() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.sin(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 行ベクトルのサイズを返す.
   * 
   * @return 配列のサイズ
   * @since 1.1
   */
  public int size() {
    return this.bigVector.length;
  }

  /**
   * ベクトルの要素の平方.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素を平方したベクトル
   */
  public BigVector sqrt() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.sqrt(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの各要素にvalueを減算する.
   * 
   * @param value
   *          値
   * @return thisの各要素にvalueを引いた値
   * @since 1.1
   */
  public BigVector subtract(final BigDecimal value) {
    return this.subtract(new BigVector(this.bigVector.length, value, this.precision));
  }

  /**
   * 減算.
   * 
   * @param v
   *          値
   * @return this - v を返す.
   * @since 1.1
   */
  public BigVector subtract(final BigVector v) {
    this.checkVectorSize(v);
    BigDecimal[] bd = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      bd[i] = this.bigVector[i].subtract(v.bigVector[i]);
    }
    return new BigVector(bd, this.precision);
  }

  /**
   * ベクトルの要素の和(summation).
   * 
   * @return 和
   * @since 1.1
   */
  public BigDecimal sum() {
    BigDecimal sum = new BigDecimal("0.0d");
    for (int i = 0; i < this.bigVector.length; i++) {
      sum = sum.add(this.bigVector[i]);
    }
    return sum;
  }

  /**
   * ベクトルの個々の要素の正接(tangent).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正接
   */
  public BigVector tan() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.tan(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 一行this.size()列の行列、Matrix型オブジェクトに変換して返す.
   * 
   * @return Matrix型のオブジェクト
   * @since 1.1
   */
  public BigMatrix toMatrix() {
    BigDecimal[][] bigMatrix = new BigDecimal[1][this.bigVector.length];
    bigMatrix[0] = this.bigVector.clone();
    return new BigMatrix(bigMatrix, this.precision);
  }

  /**
   * 精度情報を取得する.
   * 
   * @return 精度
   * @since 1.1
   * @uml.property name="precision"
   */
  public Precision getPrecision() {
    return this.precision;
  }

  /**
   * ベクトルの個々の要素の双曲線正弦(Hyperbolic Sine).
   * 
   * @return ベクトルの個々の要素に対する双曲線正弦
   * @since 2005/01/04
   */
  public BigVector sinh() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.sinh(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の双曲線余弦(Hyperbolic Cosine).
   * 
   * @return ベクトルの個々の要素に対する双曲線余弦
   * @since 2005/01/04
   */
  public BigVector cosh() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.cosh(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の双曲線正接(Hyperbolic Tangent).
   * 
   * @return ベクトルの個々の要素に対する双曲線正接
   * @since 2005/01/04
   */
  public BigVector tanh() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.tanh(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆双曲線正弦(Inverse Hyperbolic Sine).
   * 
   * @return ベクトルの個々の要素に対する逆双曲線正弦
   * @since 2005/01/04
   */
  public BigVector asinh() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.asinh(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆双曲線余弦(Inverse Hyperbolic Cosine).
   * 
   * @return ベクトルの個々の要素に対する逆双曲線余弦
   * @since 2005/01/04
   */
  public BigVector acosh() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.acosh(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆双曲線正接(Inverse Hyperbolic Tangent).
   * 
   * @return ベクトルの個々の要素に対する逆双曲線正接
   * @since 2005/01/04
   */
  public BigVector atanh() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.atanh(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの要素の立方根.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素を立方したベクトル
   */
  public BigVector cbrt() {
    BigDecimal[] c = new BigDecimal[this.bigVector.length];
    for (int i = 0; i < this.bigVector.length; i++) {
      c[i] = this.math.cbrt(this.bigVector[i]);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 現在のオブジェクトがotherと同一であればtrueを返す.
   * 
   * ベクトルの個々の要素だけでなく、精度も同一であるときtrueを返す.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return thisとotherが同一であるとき
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof BigVector)) {
      return false;
    }
    BigVector castOther = (BigVector) other;
    return new EqualsBuilder().append(this.precision, castOther.precision).append(this.bigVector,
        castOther.bigVector).isEquals();
  }

  /**
   * オブジェクトのハッシュコードを返す.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.precision).append(this.bigVector).toHashCode();
  }

}
