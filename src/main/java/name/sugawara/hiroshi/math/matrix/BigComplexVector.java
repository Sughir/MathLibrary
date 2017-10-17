/*
 * 作成日: 2003/09/23
 */
package name.sugawara.hiroshi.math.matrix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import name.sugawara.hiroshi.math.complex.BigComplex;
import name.sugawara.hiroshi.math.function.decimal.BigMath;
import name.sugawara.hiroshi.math.precision.NandEpsilon;
import name.sugawara.hiroshi.math.precision.Precision;
import name.sugawara.hiroshi.math.precision.RoundingError;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BigComplex(BigDecimal対応型複素数)型対応行列.
 * 
 * @author sugawara
 * @version $Id: BigComplexVector.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */

public final class BigComplexVector extends RowVector implements Comparable<BigComplexVector> {

  // コンストラクタに誤差情報を入れなかったときの誤差のデフォルト値
  /**
   * デフォルトの項の演算回数.
   * 
   * @since 2005/07/18 22:23:22
   */
  private static final BigInteger  DEFAULT_N         = new BigInteger("1000");

  /**
   * デフォルト機械エプシロン.
   * 
   * @since 2005/07/18 22:23:11
   */
  private static final BigDecimal  DEFAULT_EPSILON   = new BigDecimal("0.0001");

  /**
   * デフォルトスケール.
   * 
   * @since 2005/07/18 22:23:04
   */
  private static final int         DEFAULT_SCALE     = 1000;

  /**
   * デフォルトMathContextオブジェクト.
   */
  private static final MathContext MC                = new MathContext(
                                                         BigComplexVector.DEFAULT_SCALE,
                                                         RoundingMode.HALF_EVEN);

  /**
   * デフォルト精度.
   * 
   * @since 2005/07/18 22:22:38
   */
  private static final Precision   DEFAULT_PRECISION = new NandEpsilon(BigComplexVector.DEFAULT_N,
                                                         BigComplexVector.DEFAULT_EPSILON,
                                                         BigComplexVector.MC);

  /**
   * 実数部、虚数部.
   */
  // private BigDecimal[] real;
  // rivate BigDecimal[] imaginary;
  /**
   * 誤差,精度情報.
   */
  private Precision                precision;

  /**
   * BigDecimal用初頭関数用オブジェクト.
   * 
   * @uml.property name="math"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final BigMath            math;

  /**
   * ベクトルオブジェクトの本体.
   * 
   * @uml.property name="vector"
   * @uml.associationEnd multiplicity="(0 -1)"
   */
  private BigComplex[]             vector;

  /**
   * 精度を指定して配列から行ベクトルを生成.
   * 
   * @param bigComplexVector
   *          配列
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigComplexVector(final BigComplex[] bigComplexVector, final Precision precision) {

    this.vector = bigComplexVector.clone();
    this.precision = precision;
    this.math = new BigMath(this.precision);
  }

  /**
   * 配列から行ベクトルを生成.
   * 
   * @param bigComplexVector
   *          配列
   * @since 1.1
   */
  public BigComplexVector(final BigComplex[] bigComplexVector) {
    this(bigComplexVector.clone(), BigComplexVector.DEFAULT_PRECISION);
  }

  /**
   * 精度を指定してBigComplexMatrix型からBigDecimal型行ベクトルを生成.
   * 
   * @param matrix
   *          Matrix型行列
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigComplexVector(final BigComplexMatrix matrix, final Precision precision) {
    if (matrix.getColumnDimension() != 1) {
      throw new ArithmeticException("The vector must be the one dimentional array.");
    }
    this.vector = matrix.getArray()[0];
    this.precision = precision;
    this.math = new BigMath(precision);
  }

  /**
   * BigComplexMatrix型からBigDecimal型行ベクトルを生成.
   * 
   * @param matrix
   *          Matrix型行列
   * @since 1.1
   */
  public BigComplexVector(final BigComplexMatrix matrix) {
    this(matrix.getArray()[0], BigComplexVector.DEFAULT_PRECISION);
  }

  /**
   * 精度を指定して値がすべてsの行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @param s
   *          行ベクトルの値
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigComplexVector(final int row, final BigComplex s, final Precision precision) {
    final BigComplex[] vectorResult = new BigComplex[row];
    Arrays.fill(vectorResult, s);
    this.vector = vectorResult.clone();
    this.precision = precision;
    this.math = new BigMath(precision);
  }

  /**
   * 値がすべてsの行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @param s
   *          行ベクトルの値
   * @since 1.1
   */
  public BigComplexVector(final int row, final BigComplex s) {
    this(row, s, BigComplexVector.DEFAULT_PRECISION);
  }

  /**
   * 精度を指定して値がすべて0の行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @param precision
   *          精度
   * @since 1.1
   */
  public BigComplexVector(final int row, final Precision precision) {
    this(row, new BigComplex(precision), precision);
  }

  /**
   * 値がすべて0の行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @since 1.1
   */
  public BigComplexVector(final int row) {
    this(row, new BigComplex(BigComplexVector.DEFAULT_PRECISION),
        BigComplexVector.DEFAULT_PRECISION);
  }

  /**
   * ベクトルの個々の要素に対する絶対値をベクトル.
   * 
   * @since 1.1
   * @return ベクトル要素に対する絶対値をベクトル
   */
  public BigVector abs() {
    final BigDecimal[] c = new BigDecimal[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].abs();
    }
    return new BigVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆余弦(arc cosine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public BigComplexVector acos() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].acos();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの各要素にvalueを加算する.
   * 
   * @param value
   *          値
   * @return thisの各要素にvalueを加えた値
   * @since 1.1
   */
  public BigComplexVector add(final BigComplex value) {
    return this.add(new BigComplexVector(this.vector.length, value, this.precision));
  }

  /**
   * ベクトル同士の加算.
   * 
   * @param v
   *          値
   * @return this + v を返す.
   * @since 1.1
   */
  public BigComplexVector add(final BigComplexVector v) {
    this.checkVectorSize(v);

    final BigComplex[] dc = new BigComplex[v.vector.length];
    for (int i = 0; i < v.vector.length; i++) {
      dc[i] = this.vector[i].add(v.vector[i]);
    }
    return new BigComplexVector(dc, this.precision);
  }

  /**
   * ベクトルの個々の要素の逆正弦(arc sine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public BigComplexVector asin() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].asin();
    }
    return new BigComplexVector(c, this.precision);
  }

  // /**
  // * ベクトルの個々の要素の逆正接(arc tangent)
  // * @since 1.1
  // * @return ベクトルの個々の要素に対する逆正弦
  // */
  // public BigComplexVector atan() {
  // BigComplex[] c = new BigComplex[this.vector.length];
  // for (int i = 0; i < this.vector.length; i++) {
  // c[i] = this.vector[i].atan();
  // }
  // return new BigComplexVector(c, precision);
  // }

  // /**
  // * ベクトルの要素の4象限逆正接 Four Quadrant Arc Tangent
  // * 現在のオブジェクトを、ラジアンで表した、arctan関数でyを変数とするベクトル
  // * とし指定された角度の4象限逆正接 (4象限アークタンジェント) を返す
  // *
  // * @since 1.1
  // * @param x ラジアンで表した arctan関数でxを変数とする配列
  // * @return 指定された配列の個々の要素をarctan関数に代入した配列を返す.
  // */
  // public BigComplexVector atan2(BigComplexVector x) {
  // checkVectorSize(x);
  // BigComplex[] c = new BigComplex[this.vector.length];
  // for (int i = 0; i < this.vector.length; i++) {
  // c[i] = this.vector[i].atan2(x.vector[i]);
  // }
  // return new BigComplexVector(c, precision);
  // }

  /**
   * ベクトルの要素の平均(average).
   * 
   * @return 平均
   * @since 1.1
   */
  public BigComplex mean() {
    return this.sum().divide(
        new BigComplex(new BigDecimal(this.vector.length), BigDecimal.ZERO, this.precision));
  }

  /**
   * Check if size(a) == size(b).
   * 
   * @param v
   *          ベクトル
   */
  private void checkVectorSize(final BigComplexVector v) {
    if (this.vector.length != v.vector.length) {
      throw new IllegalArgumentException("The each vector size must be same.");
    }
  }

  /**
   * オブジェクトのクローン. オブジェクトはディープコピーされる.
   * 
   * @return ベクトル
   * @since 1.1
   */
  @Override
  public Object clone() {
    try {
      super.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    }
    return new BigComplexVector(this.vector.clone(), this.precision);
  }

  /**
   * 2つのベクトルを連結する.
   * 
   * @param rear
   *          後方に連結されるベクトル
   * @return 指定された2つのベクトルを連結したベクトルを返す
   * @since 1.1
   */
  public BigComplexVector concatenate(final BigComplexVector rear) {
    final int size = this.vector.length + rear.vector.length;
    final BigComplex[] combine = new BigComplex[size];
    for (int i = 0; i < size; i++) {
      combine[i] = this.vector[i];
    }
    for (int i = 0; i < rear.vector.length; i++) {
      combine[i + this.vector.length] = rear.vector[i];
    }
    return new BigComplexVector(combine);
  }

  /**
   * 2つのベクトルのconvolution(畳み込み)を返す. 返されたベクトルのサイズは2つの配列のサイズの和から1を引いた値になる.
   * 
   * @param h
   *          システム(配列)
   * @return サイズが x.size() + h.size() - 1 のdouble[]型を返す
   * @since 1.1
   */
  public BigComplexVector convolute(final BigComplexVector h) {
    final BigComplex[] y = new BigComplex[this.vector.length + h.vector.length - 1];
    Arrays.fill(y, new BigComplex(this.precision));
    int hIndex;
    for (int n = 0; n < h.vector.length; n++) {
      for (int k = 0; k < this.vector.length; k++) {
        hIndex = n - k;
        if ((0 <= hIndex) && (hIndex < h.vector.length)) {
          y[n] = y[n].add(this.vector[k].multiply(this.vector[n - k]));
        }
      }
    }
    return new BigComplexVector(y);
  }

  /**
   * ベクトルのauto-correlation(自己相関関数)をベクトルで返す. 返されたベクトルのサイズはベクトルの2倍から1を引いた値になる.
   * 
   * @return サイズが 2 * this.size() - 1 のdouble[]型を返す
   * @since 1.1
   */
  public BigComplexVector correlate() {
    return this.correlate(this);
  }

  /**
   * 2つのベクトルのcross-correlation(相互相関関数)を返す. 返されたベクトルのサイズは2つのベクトルのサイズの和から1を引いた値になる. <br />
   * システムhに対して現在のオブジェクトを信号としている.
   * 
   * @param h
   *          システム(ベクトル)
   * @return サイズが this.size() + h.size() - 1 のdouble[]型を返す
   * @since 1.1
   */
  public BigComplexVector correlate(final BigComplexVector h) {
    return this.convolute(h.fliplr());
  }

  /**
   * ベクトルの個々の要素の余弦(cosine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public BigComplexVector cos() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].cos();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの指定されたインデックスの要素を削除する. 要素を削除するとベクトルのサイズが1小さくなる.
   * 
   * @param index
   *          要素を削除する位置
   * @return 要素を1つ削除されたベクトルを返す
   * @since 1.1
   */
  public BigComplexVector delete(final int index) {
    final BigComplex[] deletedArray = new BigComplex[this.vector.length - 1];

    if (index == deletedArray.length) {
      for (int i = 0; i < deletedArray.length; i++) {
        deletedArray[i] = this.vector[i];
      }
    } else {
      for (int i = 0, j = 0; j < deletedArray.length; i++, j++) {
        if (j == index) {
          i++;
          deletedArray[j] = this.vector[i];
        } else {
          deletedArray[j] = this.vector[i];
        }
      }
    }
    return new BigComplexVector(deletedArray);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public BigComplexVector divide(final BigComplex value) {

    final BigComplexVector rv = new BigComplexVector(this.vector.length, value, this.precision);
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
  public BigComplexVector divide(final BigComplexVector v) {
    this.checkVectorSize(v);
    final BigComplex[] dc = new BigComplex[v.vector.length];
    for (int i = 0; i < v.vector.length; i++) {
      dc[i] = this.vector[i].divide(v.vector[i]);
    }
    return new BigComplexVector(dc, this.precision);

  }

  /**
   * 指定されたオブジェクトと現在のオブジェクトの要素(複素数の実部、虚部の値を)が等しいかどうかを比較する.
   * Object#getClass()を使用しているので、派生クラスのオブジェクトとを比較する場合は必ずfalseとなる。
   * this.precision、this.mathの値が異なる場合はfalseを返す。 同値かどうか値を直接比較するにはcompareTo()メソッドを使用すること。
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
    if (!(obj instanceof BigComplexVector)) {
      return false;
    }
    if (obj != null && this.getClass() == obj.getClass()) {
      BigComplexVector c = (BigComplexVector) obj;
      if (this.vector.length != c.vector.length) {
        return false;
      }
      return new EqualsBuilder().append(this.precision, c.precision).append(this.math, c.math)
          .append(this.vector, c.vector).isEquals();
    }
    return false;
  }

  /**
   * このオブジェクトのハッシュコードを返す. ハッシュコードの計算にはApache Commons LangのHashCodeBuilderを使用.
   * たとえベクトルの個々の要素がすべて同じ値であってもthis.precision、this.mathの値が異なれば違う値を返す可能性がある。
   * 
   * @see java.lang.Object#hashCode()
   * @return ハッシュコード
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.precision).append(this.math).append(this.vector)
        .toHashCode();
  }

  /**
   * 配列の要素のexp関数 exp(x).
   * 
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列を返す.
   */
  public BigComplexVector exp() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].exp();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの配列の要素を左右逆順にする.
   * 
   * @return 指定された配列要素を逆順にしたベクトルを返す
   * @since 1.1
   */
  public BigComplexVector fliplr() {
    final int size = this.vector.length;
    final BigComplex[] flipped = new BigComplex[size];
    for (int i = 0; i < size; i++) {
      flipped[i] = this.vector[size - 1 - i];
    }
    return new BigComplexVector(flipped, this.precision);
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
  public BigComplex get(final int i) {
    if (i > this.vector.length) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return this.vector[i];
    }
  }

  /**
   * 行ベクトルから配列に変換する.
   * 
   * @return 配列
   * @since 1.1
   */
  public BigComplex[] getArray() {
    return this.vector.clone();
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
   * 始点と終点を指定して部分ベクトルを取り出す. Get a subvector.
   * 
   * @param i0
   *          Initial col index
   * @param i1
   *          Final col index
   * @return x(i0:i1)
   */
  public BigComplexVector getVector(final int i0, final int i1) {
    final BigComplex[] x = new BigComplex[i1 - i0 + 1];
    try {
      for (int i = i0; i <= i1; i++) {
        x[i - i0] = this.vector[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices");
    }
    return new BigComplexVector(x);
  }

  /**
   * 特定の列のインデックスの部分ベクトルを取り出す. Get a subvector.
   * 
   * @param col
   *          Array of column indices.
   * @return x(c(:))
   */
  public BigComplexVector getVector(final int[] col) {

    final BigComplex[] x = new BigComplex[col.length];
    try {
      for (int j = 0; j < col.length; j++) {
        x[j] = this.vector[col[j]];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexVector(x);
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
   * @param v
   *          ベクトル
   * @return 内積
   * @since 1.1
   */
  public BigComplex innerProduct(final BigComplexVector v) {
    BigComplex innerProduct = new BigComplex(this.precision);
    for (int i = 0; i < this.vector.length; i++) {
      innerProduct = innerProduct.add(this.vector[i].multiply(v.vector[i]));
    }
    return innerProduct;
  }

  /**
   * 配列に指定された値を指定されたインデックスに挿入する. 要素を挿入すると配列のサイズが1大きくなる.
   * 
   * @param element
   *          挿入する要素
   * @param index
   *          要素を挿入する位置
   * @return 要素を挿入された配列を返す
   * @since 1.1
   */
  public BigComplexVector insert(final BigComplex element, final int index) {
    final BigComplex[] insertedArray = new BigComplex[this.vector.length + 1];
    if (index == insertedArray.length) {
      for (int i = 0; i <= insertedArray.length; i++) {
        if (i != index) {
          insertedArray[i] = this.vector[i];
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
          insertedArray[j] = this.vector[i];
        }
      }
    }
    return new BigComplexVector(insertedArray);
  }

  /**
   * ベクトルの個々の要素の対数.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する対数
   */
  public BigComplexVector log() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].log();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの最大要素を求める.
   * 
   * @since 1.1
   * @return 最大要素
   */
  public BigComplex max() {
    BigComplex max = this.vector[0];
    for (int i = 1; i < this.vector.length; i++) {
      max = max.max(this.vector[i]);
    }
    return max;
  }

  /**
   * ベクトルの最小要素を求める.
   * 
   * @since 1.1
   * @return 最小要素
   */
  public BigComplex min() {
    BigComplex min = this.vector[0];
    for (int i = 1; i < this.vector.length; i++) {
      min = min.min(this.vector[i]);
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
  public BigComplexVector multiply(final BigComplex times) {
    final BigComplexVector rv = new BigComplexVector(this.vector.length, times, this.precision);
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
  public BigComplexVector multiply(final BigComplexVector multiplier) {
    this.checkVectorSize(multiplier);

    final BigComplex[] dc = new BigComplex[multiplier.vector.length];
    for (int i = 0; i < multiplier.vector.length; i++) {
      dc[i] = this.vector[i].multiply(multiplier.vector[i]);
    }
    return new BigComplexVector(dc, this.precision);

  }

  /**
   * 符号をマイナスにして返す. Unary minus.
   * 
   * @return -this
   */
  public BigComplexVector negate() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].negate();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * 無限小ノルムを返す.
   * 
   * @since 1.1
   * @return 無限小ノルム
   */
  public BigDecimal negativeInfinityNorm() {
    return this.abs().min().abs();
  }

  /**
   * 2乗ノルムを返す.
   * 
   * @since 1.1
   * @return 2乗ノルム
   */
  public BigDecimal norm() {
    final BigVector v = this.abs();
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
    final int scale = ((RoundingError) this.getPrecision()).getScale();
    final int mode = ((RoundingError) this.getPrecision()).getRoundingMode();
    return this.math.pow(this.abs().pow(exponent).sum(), BigDecimal.ONE.divide(exponent, scale,
        mode));
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
  public BigComplexVector pow(final BigComplex d) {
    final BigComplex[] c = new BigComplex[this.vector.length];

    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].pow(d);
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの要素の積(product).
   * 
   * @return 積
   * @since 1.1
   */
  public BigComplex product() {
    BigComplex product = this.vector[0];
    for (int i = 1; i < this.vector.length; i++) {
      product = product.multiply(this.vector[i]);
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
   * @since 2004/08/03
   */
  public BigComplexVector set(final int i, final BigComplex s) {

    final BigComplex[] d = ((BigComplexVector) this.clone()).getArray();
    d[i] = s;
    return new BigComplexVector(d, this.precision);
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
  public BigComplexVector setVector(final int i0, final int i1, final BigComplexVector x) {
    final BigComplex[] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        d[i] = x.vector[i - i0];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices");
    }
    return new BigComplexVector(d);
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
  public BigComplexVector setVector(final int[] col, final BigComplexVector x) {
    final BigComplex[] d = this.getArray();
    try {
      for (int i = 0; i < col.length; i++) {
        d[col[i]] = x.vector[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices");
    }
    return new BigComplexVector(d);
  }

  /**
   * ベクトルの個々の要素の正弦(sine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public BigComplexVector sin() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].sin();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * 行ベクトルのサイズを返す.
   * 
   * @return 配列のサイズ
   * @since 1.1
   */
  public int size() {
    return this.vector.length;
  }

  /**
   * ベクトルの要素の平方.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素を平方したベクトル
   */
  public BigComplexVector sqrt() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < c.length; i++) {
      c[i] = this.vector[i].sqrt();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの各要素にvalueを減算する.
   * 
   * @param value
   *          値
   * @return thisの各要素にvalueを引いた値
   * @since 1.1
   */
  public BigComplexVector subtract(final BigComplex value) {
    return this.subtract(new BigComplexVector(this.vector.length, value, this.precision));
  }

  /**
   * 減算.
   * 
   * @param v
   *          値
   * @return this - v を返す.
   * @since 1.1
   */
  public BigComplexVector subtract(final BigComplexVector v) {
    this.checkVectorSize(v);

    final BigComplex[] dc = new BigComplex[v.vector.length];
    for (int i = 0; i < v.vector.length; i++) {
      dc[i] = this.vector[i].subtract(v.vector[i]);
    }
    return new BigComplexVector(dc, this.precision);

  }

  /**
   * ベクトルの要素の和(summation).
   * 
   * @return 和
   * @since 1.1
   */
  public BigComplex sum() {
    BigComplex sum = new BigComplex(this.precision);
    for (int i = 0; i < this.vector.length; i++) {
      sum = sum.add(this.vector[i]);
    }
    return sum;
  }

  /**
   * ベクトルの個々の要素の正接(tangent).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public BigComplexVector tan() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].tan();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * 一行this.size()列の行列、Matrix型オブジェクトに変換して返す.
   * 
   * @return Matrix型のオブジェクト
   * @since 1.1
   */
  public BigComplexMatrix toMatrix() {
    final BigComplex[][] bc = new BigComplex[1][this.vector.length];
    return new BigComplexMatrix(bc.clone());
  }

  /**
   * ベクトルの個々の要素の双曲線正弦(Hyperbolic Sine).
   * 
   * @since 2005/01/04
   * @return ベクトルの個々の要素に対する双曲線正弦
   */
  public BigComplexVector sinh() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].sinh();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の双曲線余弦(Hyperbolic Cosine).
   * 
   * @since 2005/01/04
   * @return ベクトルの個々の要素に対する双曲線余弦
   */
  public BigComplexVector cosh() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].cosh();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素の双曲線正接(Hyperbolic Tangent).
   * 
   * @since 2005/01/04
   * @return ベクトルの個々の要素に対する双曲線正接
   */
  public BigComplexVector tanh() {
    final BigComplex[] c = new BigComplex[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = this.vector[i].tanh();
    }
    return new BigComplexVector(c, this.precision);
  }

  /**
   * ベクトルの個々の要素を比較する. this>otherのとき1、this == otherのとき0、this < otherのとき-1を返す。 {@inheritDoc}
   * 
   * @param other
   *          比較対象
   * @return 比較対象結果を-1、0、1で返す。
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(final BigComplexVector other) {
    return new CompareToBuilder().append(this.vector, other.vector).toComparison();
  }

}
