package name.sugawara.hiroshi.math.matrix;

/*
 * Created on 2003/07/25
 */

import java.io.Serializable;
import java.util.Arrays;

import name.sugawara.hiroshi.math.function.typedouble.DoubleMath;

/**
 * double型行ベクトル.
 *
 * @author sugawara
 * @version $Id: DoubleVector.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */
public final class DoubleVector extends RowVector implements Cloneable, Serializable {

  /**
   * シリアルバージョンID.
   *
   * @since 2007/02/25 19:33:22
   */
  private static final long serialVersionUID = -7708761953748281551L;

  /**
   * ハッシュコードを求めるときに使用する.
   *
   * @since 2007/02/21 17:36:54
   */
  private transient int     hashCode;

  /**
   * toString()メソッドを実行するために使用する.
   *
   * @since 2007/02/21 19:05:20
   */
  private transient String  toString;

  /**
   * ベクトル{@code vector}.
   *
   * @since 2005/10/28 4:09:48
   */
  private final double[]    vector;

  /**
   * 配列から行ベクトルを生成.
   *
   * @param vector
   *          配列
   * @since 1.1
   */
  public DoubleVector(final double[] vector) {
    this.vector = vector.clone();
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
  public DoubleVector(final int row, final double s) {
    final double[] array = new double[row];
    Arrays.fill(array, s);
    this.vector = array;
  }

  /**
   * 値がすべて0の行ベクトルを生成.
   *
   * @param row
   *          行ベクトルのサイズ
   * @since 1.1
   */
  public DoubleVector(final int row) {
    this(row, 0.0d);
  }

  /**
   * 行ベクトルから配列に変換する.
   *
   * @return 配列
   * @since 1.1
   */
  public double[] getArray() {
    return this.vector.clone();
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
  public double get(final int i) {
    if (i > this.vector.length) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return this.vector[i];
    }
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
   * ベクトル同士の加算.
   *
   * @param v
   *          値
   * @return this + v を返す.
   * @since 1.1
   */
  public DoubleVector add(final DoubleVector v) {
    this.checkVectorSize(v);
    final double[] d = new double[this.vector.length];
    for (int i = 0; i < d.length; i++) {
      d[i] = this.vector[i] + v.vector[i];
    }
    return new DoubleVector(d);
  }

  /**
   * ベクトルの各要素にvalueを加算する.
   *
   * @param value
   *          値
   * @return thisの各要素にvalueを加えた値
   * @since 1.1
   */
  public DoubleVector add(final double value) {
    return this.add(new DoubleVector(this.vector.length, value));
  }

  /**
   * 減算.
   *
   * @param v
   *          値
   * @return this - v を返す.
   * @since 1.1
   */
  public DoubleVector subtract(final DoubleVector v) {
    this.checkVectorSize(v);
    final double[] d = new double[this.vector.length];
    for (int i = 0; i < d.length; i++) {
      d[i] = this.vector[i] - v.vector[i];
    }
    return new DoubleVector(d);
  }

  /**
   * ベクトルの各要素にvalueを減算する.
   *
   * @param value
   *          値
   * @return thisの各要素にvalueを引いた値
   * @since 1.1
   */
  public DoubleVector subtract(final double value) {
    return this.subtract(new DoubleVector(this.vector.length, value));
  }

  /**
   * ベクトルの要素の和(summation).
   *
   * @return 和
   * @since 1.1
   */
  public double sum() {
    double sum = 0.0d;
    for (int i = 0; i < this.vector.length; i++) {
      sum += this.vector[i];
    }
    return sum;
  }

  /**
   * ベクトルの要素の積(product).
   *
   * @return 積
   * @since 1.1
   */
  public double product() {
    double product = this.vector[0];

    for (int i = 1; i < this.vector.length; i++) {
      product *= this.vector[i];
    }
    return product;
  }

  /**
   * ベクトルの要素の平均(average).
   *
   * @return 平均
   * @since 1.1
   */
  public double avg() {
    return this.sum() / this.vector.length;
  }

  /**
   * ベクトルの内積.
   *
   * @param v
   *          ベクトル
   * @return 内積
   * @since 1.1
   */
  public double innerProduct(final DoubleVector v) {
    double innerProduct = 0.0d;
    for (int i = 0; i < this.vector.length; i++) {
      innerProduct += this.vector[i] * v.vector[i];
    }
    return innerProduct;
  }

  /**
   *  size(a) == size(b) かどうかを確認する。
   *
   * @param v
   *          チェックしたい対象となるベクトル
   * @since 2004/08/03
   */
  private void checkVectorSize(final DoubleVector v) {
    if (this.vector.length != v.vector.length) {
      throw new IllegalArgumentException("双方のベクトルのサイズは同一です。");
    }
  }

  /**
   * ベクトルの配列の要素を左右逆順にする.
   *
   * @return 指定された配列要素を逆順にしたベクトルを返す
   * @since 1.1
   */
  public DoubleVector fliplr() {
    final int size = this.vector.length;
    final double[] flipped = new double[size];
    for (int i = 0; i < size; i++) {
      flipped[i] = this.vector[size - 1 - i];
    }
    return new DoubleVector(flipped);
  }

  /**
   * ベクトルの最大要素を求める.
   *
   * @since 1.1
   * @return 最大要素
   */
  public double max() {
    double max = (-1) * Double.MAX_VALUE;
    for (int i = 0; i < this.vector.length; i++) {
      max = Math.max(max, this.vector[i]);
    }
    return max;
  }

  /**
   * ベクトルの最小要素を求める.
   *
   * @since 1.1
   * @return 最小要素
   */
  public double min() {
    double min = Double.MAX_VALUE;
    for (int i = 0; i < this.vector.length; i++) {
      min = Math.min(min, this.vector[i]);
    }
    return min;
  }

  /**
   * 配列どうしの除算. 双方の配列はそれぞれサイズが同じである必要がある.
   *
   * @since 1.1
   * @param v
   *          除算する配列
   * @return 指定された配列aと指定された配列bを除算した配列
   */
  public DoubleVector divide(final DoubleVector v) {

    this.checkVectorSize(v);
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < c.length; i++) {
      c[i] = this.vector[i] / v.vector[i];
    }
    return new DoubleVector(c);
  }

  /**
   * 配列の各要素に指定された値を除算.
   *
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public DoubleVector divide(final double value) {
    final DoubleVector rv = new DoubleVector(this.vector.length, value);
    return this.divide(rv);
  }

  /**
   * 配列どうしの乗算 双方の配列はそれぞれサイズが同じである必要がある.
   *
   * @since 1.1
   * @param multiplier
   *          乗算する配列
   * @return 指定された配列aと指定された配列bを乗算した配列を返す.
   */
  public DoubleVector multiply(final DoubleVector multiplier) {
    this.checkVectorSize(multiplier);
    final double[] product = new double[this.vector.length];

    for (int i = 0; i < product.length; i++) {
      product[i] = this.vector[i] * multiplier.vector[i];
    }
    return new DoubleVector(product);
  }

  /**
   * ベクトルの実数倍.
   *
   * @since 1.1
   * @param times
   *          ベクトルを実数倍する値
   * @return ベクトルの個々の要素を実数倍した配列を返す.
   */
  public DoubleVector multiply(final double times) {
    final DoubleVector rv = new DoubleVector(this.vector.length, times);
    return this.multiply(rv);
  }

  /**
   * ベクトル要素の累乗.
   *
   * @since 1.1
   * @param d
   *          指数
   * @return ベクトルの個々の要素をd乗した配列を返す.
   */
  public DoubleVector pow(final double d) {
    final double[] c = new double[this.vector.length];

    for (int i = 0; i < c.length; i++) {
      c[i] = Math.pow(this.vector[i], d);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの要素の平方.
   *
   * @since 1.1
   * @return ベクトルの個々の要素を平方したベクトル
   */
  public DoubleVector sqrt() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < c.length; i++) {
      c[i] = Math.sqrt(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素に対する絶対値をベクトル.
   *
   * @since 1.1
   * @return ベクトル要素に対する絶対値をベクトル
   */
  public DoubleVector abs() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.abs(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の対数.
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する対数
   */
  public DoubleVector log() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.log(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の常用対数.
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する常用対数
   */
  public DoubleVector log10() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.log10(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の常用対数.
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する常用対数
   */
  public DoubleVector log2() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = DoubleMath.log2(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の正弦(sine).
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public DoubleVector sin() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.sin(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の余弦(cosine).
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public DoubleVector cos() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.cos(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の正接(tangent).
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public DoubleVector tan() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.tan(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆正弦(arc sine).
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public DoubleVector asin() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.asin(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆余弦(arc cosine).
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public DoubleVector acos() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.acos(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆正接(arc tangent).
   *
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public DoubleVector atan() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.atan(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの要素の4象限逆正接 Four Quadrant Arc Tangent.
   * 現在のオブジェクトを、ラジアンで表した、arctan関数でyを変数とする ベクトル とし指定された角度の4象限逆正接 (4象限アークタンジェント)
   * を返す
   *
   * @since 1.1
   * @param x
   *          ラジアンで表した arctan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarctan関数に代入した配列
   */
  public DoubleVector atan2(final DoubleVector x) {
    this.checkVectorSize(x);
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.atan2(this.vector[i], x.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * 配列の要素のexp関数 exp(x).
   *
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列
   */
  public DoubleVector exp() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.exp(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * n乗ノルムを返す.
   *
   * @since 1.1
   * @param exponent
   *          累乗
   * @return exponent乗ノルム
   */
  public double norm(final double exponent) {
    return Math.pow(this.abs().pow(exponent).sum(), 1.0d / exponent);
  }

  /**
   * 2乗ノルムを返す.
   *
   * @since 1.1
   * @return 2乗ノルム
   */
  public double norm() {
    final DoubleVector v = this.abs();
    return Math.sqrt(v.multiply(v).sum());
  }

  /**
   * 1乗ノルムを返す.
   *
   * @since 1.1
   * @return 1乗ノルム
   */
  public double oneNorm() {
    return this.abs().sum();
  }


  /**
   * 無限大ノルムを返す.
   *
   * @since 1.1
   * @return 無限大ノルム
   */
  public double infinityNorm() {
    return this.abs().max();
  }

  /**
   * 無限小ノルムを返す.
   *
   * @since 1.1
   * @return 無限小ノルム
   */
  public double negativeInfinityNorm() {
    return this.abs().min();
  }

  /**
   * 2つのベクトルのconvolution(畳み込み)を返す. <br />
   * 返されたベクトルのサイズは2つの配列のサイズの和から1を引いた値になる.
   *
   * @param h
   *          システム(配列)
   * @return サイズが x.size() + h.size() - 1 のdouble[]型を返す
   * @since 1.1
   */
  public DoubleVector convolute(final DoubleVector h) {
    final double[] y = new double[this.vector.length + h.vector.length - 1];
    Arrays.fill(y, 0.0d);
    int hofIndex;
    for (int n = 0; n < y.length; n++) {
      for (int k = 0; k < this.vector.length; k++) {
        hofIndex = n - k;
        if ((hofIndex >= 0) && (hofIndex < h.vector.length)) {
          y[n] += this.vector[k] * h.vector[n - k];
        }
      }
    }
    return new DoubleVector(y);
  }

  /**
   * 2つのベクトルのcross-correlation(相互相関関数)を返す. <br />
   * 返されたベクトルのサイズは2つのベクトルのサイズの和から1を引いた値になる. <br />
   * システムhに対して現在のオブジェクトを信号としている.
   *
   * @param h
   *          システム(ベクトル)
   * @return サイズが this.size() + h.size() - 1 のdouble[]型を返す
   * @since 1.1
   */
  public DoubleVector correlate(final DoubleVector h) {
    return this.convolute(h.fliplr());
  }

  /**
   * ベクトルのauto-correlation(自己相関関数)をベクトルで返す. <br />
   * 返されたベクトルのサイズはベクトルの2倍から1を引いた値になる.
   *
   * @return サイズが 2 * this.size() - 1 のdouble[]型を返す
   * @since 1.1
   */
  public DoubleVector correlate() {
    return this.correlate(this);
  }

  /**
   * 一行this.size()列の行列、Matrix型オブジェクトに変換して返す.
   *
   * @return Matrix型のオブジェクト
   * @since 1.1
   */
  public DoubleMatrix toMatrix() {
    final double[][] d = new double[1][this.vector.length];
    d[0] = Arrays.copyOf(this.vector, this.vector.length);
    // for (int i = 0; i < this.vector.length; i++) {
    // d[0][i] = this.vector[i];
    // }
    return new DoubleMatrix(d);
  }

  /**
   * オブジェクトのクローン. オブジェクトはディープコピーされる.
   *
   * @return ベクトル
   * @since 1.1
   * @throws CloneNotSupportedException
   *           clone()をサポートしていないとき
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    super.clone();
    final double[] d = Arrays.copyOf(this.vector, this.vector.length);
    // final double[] d = new double[this.vector.length];
    // for (int i = 0; i < this.vector.length; i++) {
    // d[i] = this.vector[i];
    // }
    return new DoubleVector(d);
  }

  /**
   * 符号をマイナスにして返す. Unary minus.
   *
   * @return -this
   * @since 1.1
   */
  public DoubleVector negate() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < c.length; i++) {
      c[i] = -this.vector[i];
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの指定した位置に要素をセットする. Set a single element.
   *
   * @param i
   *          インデックス
   * @param s
   *          A(i).
   * @return ベクトル
   * @exception ArrayIndexOutOfBoundsException
   * @since 1.1
   */
  public DoubleVector set(final int i, final double s) {
    final double[] d = this.vector.clone();
    d[i] = s;
    return new DoubleVector(d);
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
   * @return ベクトル
   * @since 1.1
   */
  public DoubleVector setVector(final int i0, final int i1, final DoubleVector x) {
    final double[] d = this.vector.clone();
    try {
      for (int i = i0; i <= i1; i++) {
        d[i] = x.vector[i - i0];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    }
    return new DoubleVector(d);
  }

  /**
   * 部分ベクトルをセットする. Set a subvector.
   *
   * @param col
   *          列インデックスの配列
   * @param x
   *          A(c(:))
   * @return ベクトル
   * @since 1.1
   */
  public DoubleVector setVector(final int[] col, final DoubleVector x) {
    final double[] d = this.vector.clone();
    try {
      for (int i = 0; i < col.length; i++) {
        d[col[i]] = x.vector[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    }
    return new DoubleVector(d);
  }

  /**
   * 始点と終点を指定して部分ベクトルを取り出す. Get a subvector.
   *
   * @param i0
   *          開始-列インデックス
   * @param i1
   *          終了-列インデックス
   * @return x(i0:i1)
   */
  public DoubleVector getVector(final int i0, final int i1) {
    final double[] x = new double[i1 - i0 + 1];
    try {
      for (int i = i0; i <= i1; i++) {
        x[i - i0] = this.vector[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    }
    return new DoubleVector(x);
  }

  /**
   * 特定の列のインデックスの部分ベクトルを取り出す. Get a subvector.
   *
   * @param col
   *          列インデックスの配列
   * @return x(c(:))
   */
  public DoubleVector getVector(final int[] col) {

    final double[] x = new double[col.length];
    try {
      for (int j = 0; j < col.length; j++) {
        x[j] = this.vector[col[j]];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new DoubleVector(x);
  }

  /**
   * 2つのベクトルを連結する.
   *
   * @param rear
   *          後方に連結されるベクトル
   * @return 指定された2つのベクトルを連結したベクトルを返す
   * @since 1.1
   */
  public DoubleVector concatenate(final DoubleVector rear) {
    final double[] result = new double[this.vector.length + rear.vector.length];
    System.arraycopy(this.vector, 0, result, 0, this.vector.length);
    System.arraycopy(rear.vector, 0, result, this.vector.length, rear.vector.length);

    // for (int i = 0; i < size; i++) {
    // combine[i] = this.vector[i];
    // }
    // for (int i = 0; i < rear.vector.length; i++) {
    // combine[i + this.vector.length] = rear.vector[i];
    // }
    return new DoubleVector(result);
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
  public DoubleVector delete(final int index) {
    final double[] deletedArray;

    if (index == this.vector.length - 1) {

      deletedArray = Arrays.copyOf(this.vector, this.vector.length - 1);

      // for (int i = 0; i < deletedArray.length; i++) {
      // deletedArray[i] = this.vector[i];
      // }
    } else {

      deletedArray = new double[this.vector.length - 1];
      System.arraycopy(this.vector, 0, deletedArray, 0, index);
      System.arraycopy(this.vector, index + 1, deletedArray, index, deletedArray.length - index);

      // for (int i = 0, j = 0; j < deletedArray.length; i++, j++) {
      // if (j == index) {
      // i++;
      // deletedArray[j] = this.vector[i];
      // } else {
      // deletedArray[j] = this.vector[i];
      // }
      // }
    }
    return new DoubleVector(deletedArray);
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
  public DoubleVector insert(final double element, final int index) {
    final double[] insertedArray = new double[this.vector.length + 1];
    if (index == this.vector.length) {
      System.arraycopy(this.vector, 0, insertedArray, 0, this.vector.length);

      // for (int i = 0; i <= insertedArray.length; i++) {
      // if (i != index) {
      // insertedArray[i] = this.vector[i];
      // } else {
      // insertedArray[i] = element;
      // }
      // }
    } else {
      System.arraycopy(this.vector, 0, insertedArray, 0, index + 1);
      System.arraycopy(this.vector, index, insertedArray, index + 1, insertedArray.length
          - (index + 1));

      // for (int i = 0, j = 0; j < insertedArray.length; i++, j++) {
      // if (j == index) {
      // insertedArray[j] = element;
      // if (j != insertedArray.length) {
      // i--;
      // }
      // } else {
      // insertedArray[j] = this.vector[i];
      // }
      // }
    }
    insertedArray[index] = element;
    return new DoubleVector(insertedArray);
  }

  /**
   * ベクトルの個々の要素の双曲線正弦(Hyperbolic Sine).
   *
   * @return ベクトルの個々の要素に対する双曲線正弦
   * @since 2005/01/04
   */
  public DoubleVector sinh() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.sinh(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の双曲線余弦(Hyperbolic Cosine).
   *
   * @return ベクトルの個々の要素に対する双曲線余弦
   * @since 2005/01/04
   */
  public DoubleVector cosh() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.cosh(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の双曲線正接(Hyperbolic Tangent).
   *
   * @return ベクトルの個々の要素に対する双曲線正接
   * @since 2005/01/04
   */
  public DoubleVector tanh() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.tanh(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆双曲線正弦(Inverse Hyperbolic Sine).
   *
   * @return ベクトルの個々の要素に対する逆双曲線正弦
   * @since 2005/01/04
   */
  public DoubleVector asinh() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = DoubleMath.asinh(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆双曲線余弦(Inverse Hyperbolic Cosine).
   *
   * @return ベクトルの個々の要素に対する逆双曲線余弦
   * @since 2005/01/04
   */
  public DoubleVector acosh() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = DoubleMath.acosh(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆双曲線正接(Inverse Hyperbolic Tangent).
   *
   * @return ベクトルの個々の要素に対する逆双曲線正接
   * @since 2005/01/04
   */
  public DoubleVector atanh() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = DoubleMath.atanh(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の立方根(3√)(cube root)を求める.
   *
   * @return ベクトルの個々の要素に対する立方根
   * @since 2005/01/04
   */
  public DoubleVector cbrt() {
    final double[] c = new double[this.vector.length];
    for (int i = 0; i < this.vector.length; i++) {
      c[i] = Math.cbrt(this.vector[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * 比較対象オブジェクトが同値であるかどうかを判定する.
   *
   * @param other
   *          比較対象オブジェクト.
   * @return ふたつのオブジェクトが同値であればtrueを返す
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof DoubleVector)) {
      return false;
    }
    final DoubleVector castOther = (DoubleVector) other;
    return Arrays.equals(this.vector, castOther.vector);
    // return new EqualsBuilder().append(this.vector,
    // castOther.vector).isEquals();
  }

  /**
   * オブジェクトのハッシュコードを求める.
   *
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    if (this.hashCode == 0) {
      this.hashCode = Arrays.hashCode(this.vector);
    }
    return this.hashCode;
  }

  /**
   * ベクトルの文字列表現を返す.
   *
   * @return 文字列
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    if (this.toString == null) {
      this.toString = Arrays.toString(this.vector);
    }
    return this.toString;
  }

}
