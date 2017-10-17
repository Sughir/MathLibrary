/*
 * 作成日: 2003/09/23
 * 
 */
package name.sugawara.hiroshi.math.matrix;

import java.util.Arrays;

import name.sugawara.hiroshi.math.complex.DoubleComplex;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * doulble型複素数行ベクトル.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: ComplexVector.java 109 2010-06-13 04:26:48Z sugawara $
 * 
 * Created Date : 2005/07/24 19:40:34
 */

public final class ComplexVector extends RowVector implements Comparable<ComplexVector>, Cloneable {

  /**
   * 実数部. {@code real}
   * 
   * @since 2004/08/14
   */
  private double[] real;

  /**
   * 虚数部. {@code imaginary}
   * 
   * @since 2004/08/14
   */
  private double[] imaginary;

  /**
   * 配列から行ベクトルを生成.
   * 
   * @param complexVector
   *          配列
   * @since 1.1
   */
  public ComplexVector(final DoubleComplex[] complexVector) {
    this.real = new double[complexVector.length];
    this.imaginary = new double[complexVector.length];
    for (int i = 0; i < complexVector.length; i++) {
      this.real[i] = complexVector[i].getReal();
      this.imaginary[i] = complexVector[i].getImaginary();
    }
  }

  /**
   * DoubleMatrix型からdouble型行ベクトルを生成.
   * 
   * @param matrix
   *          Matrix型行列
   * @since 1.1
   */
  public ComplexVector(final ComplexMatrix matrix) {
    if (matrix.getColumnDimension() != 1) {
      throw new ArithmeticException("The matrix must be the one dimentional array.");
    }
    this.real = matrix.toVector(0).real;
    this.imaginary = matrix.toVector(0).imaginary;
  }

  /**
   * 二つの配列を実部、虚部として複素数ベクトルを作成.
   * 
   * @param real
   *          実部
   * @param imaginary
   *          虚部
   * @since 1.1
   */
  public ComplexVector(final double[] real, final double[] imaginary) {
    if (real.length != imaginary.length) {
      throw new ArithmeticException("実数部と虚数部の配列の長さは同一である必要があります。");
    }
    this.real = real.clone();
    this.imaginary = imaginary.clone();
  }

  /**
   * 二つのDoubleVector型オブジェクトを実部、虚部として複素数ベクトルを作成.
   * 
   * @param real
   *          実部
   * @param imaginary
   *          虚部
   * @since 1.1
   */
  public ComplexVector(final DoubleVector real, final DoubleVector imaginary) {
    if (real.size() != imaginary.size()) {
      throw new IllegalArgumentException("The each vector size must be same.");
    }
    this.real = real.getArray();
    this.imaginary = imaginary.getArray();
  }

  /**
   * サイズがrowの, 値がすべて0の行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @since 1.1
   */
  public ComplexVector(final int row) {
    this(row, new DoubleComplex());
  }

  /**
   * 値がすべてs, 長さが row の行ベクトルを生成.
   * 
   * @param row
   *          行ベクトルのサイズ
   * @param s
   *          行ベクトルの値
   * @since 1.1
   */
  public ComplexVector(final int row, final DoubleComplex s) {
    this.real = new double[row];
    this.imaginary = new double[row];
    Arrays.fill(this.real, s.getReal());
    Arrays.fill(this.imaginary, s.getImaginary());
  }

  /**
   * ベクトルの個々の要素に対する絶対値をベクトル.
   * 
   * @since 1.1
   * @return ベクトル要素に対する絶対値をベクトル
   */
  public DoubleVector abs() {
    double[] c = new double[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = Math.hypot(this.real[i], this.imaginary[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆余弦(arc cosine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public ComplexVector acos() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).acos();
    }
    return new ComplexVector(c);
  }

  /**
   * ベクトルの各要素にvalueを加算する.
   * 
   * @param value
   *          値
   * @return thisの各要素にvalueを加えた値
   * @since 1.1
   */
  public ComplexVector add(final DoubleComplex value) {
    return this.add(new ComplexVector(this.real.length, value));
  }

  /**
   * ベクトル同士の加算.
   * 
   * @param v
   *          値
   * @return this + v を返す.
   * @since 1.1
   */
  public ComplexVector add(final ComplexVector v) {
    this.checkVectorSize(v);
    double[] realResult = new double[this.real.length];
    double[] imaginaryResult = new double[this.imaginary.length];

    for (int i = 0; i < this.real.length; i++) {
      realResult[i] = this.real[i] + v.real[i];
      imaginaryResult[i] = this.imaginary[i] + v.imaginary[i];
    }
    return new ComplexVector(realResult, imaginaryResult);
  }

  /**
   * ベクトルの個々の要素である複素数の偏角.
   * 
   * @return 偏角
   * @since 1.1
   */
  public DoubleVector arg() {
    double[] c = new double[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = Math.atan2(this.imaginary[i], this.real[i]);
    }
    return new DoubleVector(c);
  }

  /**
   * ベクトルの個々の要素の逆正弦(arc sine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する逆正弦
   */
  public ComplexVector asin() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).asin();
    }
    return new ComplexVector(c);
  }

  /**
   * Check if size(a) == size(b).
   * 
   * @param v
   *          チェックしたい対象となるベクトル
   * @since 2004/08/03
   */
  private void checkVectorSize(final ComplexVector v) {
    if (this.real.length != v.real.length || this.imaginary.length != v.imaginary.length) {
      throw new IllegalArgumentException("The each vector size must be same.");
    }
  }

  /**
   * オブジェクトのクローン. オブジェクトはディープコピーされる.
   * 
   * @return ベクトル
   * @throws CloneNotSupportedException クローンがサポートされていないとき
   * @since 1.1
   * @see java.lang.Object#clone()
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    super.clone();
    return new ComplexVector(this.real.clone(), this.imaginary.clone());
  }

  /**
   * 2つのベクトルを連結する.
   * 
   * @param rear
   *          後方に連結されるベクトル
   * @return 指定された2つのベクトルを連結したベクトルを返す
   * @since 1.1
   */
  public ComplexVector concatenate(final ComplexVector rear) {
    int size = this.real.length + rear.real.length;
    double[] concatReal = new double[size];
    double[] concatImaginary = new double[size];

    for (int i = 0; i < this.real.length; i++) {
      concatReal[i] = this.real[i];
      concatImaginary[i] = this.imaginary[i];
    }
    for (int i = 0; i < rear.real.length; i++) {
      concatReal[i + this.real.length] = rear.real[i];
      concatImaginary[i + this.real.length] = rear.imaginary[i];
    }
    return new ComplexVector(concatReal, concatImaginary);
  }

  /**
   * 複素数の共役複素数を求める. <br />
   * 
   * @since 1.1
   * @return 現在のオブジェクトの共役複素数を返す.
   */
  public ComplexVector conjugate() {
    return new ComplexVector(this.getReal(), this.getImaginary().negate());
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
  public ComplexVector convolute(final ComplexVector h) {
    this.checkVectorSize(h);

    int size = this.real.length + h.real.length - 1;
    double[] yReal = new double[size];
    double[] yImaginary = new double[size];

    Arrays.fill(yReal, 0.0d);
    Arrays.fill(yImaginary, 0.0d);

    for (int n = 0; n < h.real.length; n++) {
      for (int k = 0; k < this.real.length; k++) {
        int hIndex = n - k;
        // if ((hIndex < 0) || (hIndex >= h.real.length)) {
        if ((0 <= hIndex) && (hIndex < h.real.length)) {
          yReal[n] = yReal[n] + (this.real[k] * this.real[n - k]);
          yImaginary[n] = yImaginary[n] + (this.real[k] * this.real[n - k]);
        }
      }
    }
    return new ComplexVector(yReal, yImaginary);
  }

  /**
   * ベクトルのauto-correlation(自己相関関数)をベクトルで返す. <br />
   * 返されたベクトルのサイズはベクトルの2倍から1を引いた値になる.<br/> サイズが 2 * this.size() - 1
   * のdouble[]型を返す.
   * 
   * @return サイズが 2 * this.size() - 1 のdouble[]型
   * @since 1.1
   */
  public ComplexVector correlate() {
    return this.correlate(this);
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
  public ComplexVector correlate(final ComplexVector h) {
    return this.convolute(h.fliplr());
  }

  /**
   * d ベクトルの個々の要素の余弦(cosine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public ComplexVector cos() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).cos();
    }
    return new ComplexVector(c);
  }

  /**
   * 複素数の hyperbolic cosine(双曲線余弦)を求める. <br />
   * sinh(x) を求める.
   * 
   * @return 現在のオブジェクトのhyperbolic cosine(双曲線余弦)
   * @since 1.1
   */
  public ComplexVector cosh() {

    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).cosh();
    }
    return new ComplexVector(c);

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
  public ComplexVector delete(final int index) {
    DoubleComplex[] deletedArray = new DoubleComplex[this.real.length - 1];
    double[] deletedReal = new double[this.real.length - 1];
    double[] deletedImaginary = new double[this.real.length - 1];

    if (index == deletedReal.length) {
      for (int i = 0; i < deletedReal.length; i++) {
        deletedReal[i] = this.real[i];
        deletedImaginary[i] = this.imaginary[i];

      }
    } else {
      for (int i = 0, j = 0; j < deletedArray.length; i++, j++) {
        if (j == index) {
          i++;
          deletedReal[j] = this.real[i];
          deletedImaginary[j] = this.imaginary[i];
        } else {
          deletedReal[j] = this.real[i];
          deletedImaginary[j] = this.imaginary[i];
        }
      }
    }
    return new ComplexVector(deletedReal, deletedImaginary);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public ComplexVector divide(final DoubleComplex value) {
    ComplexVector rv = new ComplexVector(this.real.length, value);
    return this.divide(rv);
  }

  /**
   * 配列どうしの除算. 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param v
   *          除算する配列
   * @return 指定された配列aと指定された配列bを除算した配列を返す.
   */
  public ComplexVector divide(final ComplexVector v) {

    this.checkVectorSize(v);
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < c.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).divide(v.get(i));
    }
    return new ComplexVector(c);
  }

  /**
   * 配列の要素のexp関数 exp(x).
   * 
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列
   */
  public ComplexVector exp() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).exp();
    }
    return new ComplexVector(c);
  }

  /**
   * ベクトルの配列の要素を左右逆順にする.
   * 
   * @return 指定された配列要素を逆順にしたベクトル
   * @since 1.1
   */
  public ComplexVector fliplr() {
    int size = this.real.length;
    double[] realFlipped = new double[size];
    double[] imaginaryFlipped = new double[size];
    for (int i = 0; i < size; i++) {
      realFlipped[i] = this.real[size - 1 - i];
      imaginaryFlipped[i] = this.imaginary[size - 1 - i];
    }
    return new ComplexVector(realFlipped, imaginaryFlipped);
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
  public DoubleComplex get(final int i) {
    if (i > this.real.length) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return new DoubleComplex(this.real[i], this.imaginary[i]);
    }
  }

  /**
   * 行ベクトルから配列に変換する.
   * 
   * @return 配列
   * @since 1.1
   */
  public DoubleComplex[] getArray() {
    DoubleComplex[] d = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      d[i] = new DoubleComplex(this.real[i], this.imaginary[i]);
    }
    return d.clone();
  }

  /**
   * 現在のオブジェクトより虚部を取り出す.
   * 
   * @since 1.1
   * @return 虚部を返す
   * @uml.property name="imaginary"
   */
  public DoubleVector getImaginary() {
    return new DoubleVector(this.imaginary);
  }

  /**
   * 現在のオブジェクトより実部を取り出す.
   * 
   * @since 1.1
   * @return 実部を返す
   * @uml.property name="real"
   */
  public DoubleVector getReal() {
    return new DoubleVector(this.real);
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
  public ComplexVector getVector(final int i0, final int i1) {
    DoubleComplex[] x = new DoubleComplex[i1 - i0 + 1];
    try {
      for (int i = i0; i <= i1; i++) {
        x[i - i0] = new DoubleComplex(this.real[i], this.imaginary[i]);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    }
    return new ComplexVector(x);
  }

  /**
   * 特定の列のインデックスの部分ベクトルを取り出す. Get a subvector.
   * 
   * @param col
   *          Array of column indices.
   * @return x(c(:))
   */
  public ComplexVector getVector(final int[] col) {

    DoubleComplex[] x = new DoubleComplex[col.length];
    try {
      for (int j = 0; j < col.length; j++) {
        x[j] = new DoubleComplex(this.real[col[j]], this.imaginary[col[j]]);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexVector(x);
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
   * ベクトルの内積.
   * 
   * @param v
   *          vector
   * @return 内積
   * @since 1.1
   */
  public DoubleComplex innerProduct(final ComplexVector v) {
    DoubleComplex innerProduct = new DoubleComplex();
    for (int i = 0; i < this.real.length; i++) {
      innerProduct = innerProduct.add(new DoubleComplex(this.real[i], this.imaginary[i])).multiply(
          new DoubleComplex(v.real[i], v.imaginary[i]));
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
  public ComplexVector insert(final DoubleComplex element, final int index) {
    DoubleComplex[] insertedArray = new DoubleComplex[this.real.length + 1];
    if (index == insertedArray.length) {
      for (int i = 0; i <= insertedArray.length; i++) {
        if (i != index) {
          insertedArray[i] = new DoubleComplex(this.real[i], this.imaginary[i]);
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
          insertedArray[j] = new DoubleComplex(this.real[i], this.imaginary[i]);
        }
      }
    }
    return new ComplexVector(insertedArray);
  }

  /**
   * ベクトルの個々の要素の対数.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する対数
   */
  public ComplexVector log() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).log();
    }
    return new ComplexVector(c);
  }

  /**
   * 複素数の常用対数(底を10とする対数)を求める. <br />
   * log_10(x) を求める.
   * 
   * @return 現在のオブジェクトの常用対数をComplex型(複素数)で返す.
   * @since 1.1
   */
  public ComplexVector log10() {

    final double ten = 10.0d;
    double logTen = Math.log(ten);
    DoubleComplex[] c = new DoubleComplex[this.real.length];

    double r, i;
    for (int in = 0; in < this.real.length; in++) {

      if (this.real[in] > 0.0d && this.imaginary[in] == 0.0d) {
        c[in] = new DoubleComplex(Math.log(this.real[in]) / logTen, 0.0d);
      }

      r = this.log().real[in] / logTen;
      i = this.log().imaginary[in] / logTen;
      c[in] = new DoubleComplex(r, i);
    }
    return new ComplexVector(c);
  }

  /**
   * 複素数の底を2とする対数を求める. <br />
   * log_2(x) を求める.
   * 
   * @return 現在のオブジェクトの底を2とする対数をComplex型(複素数)で返す.
   * @since 1.1
   */
  public ComplexVector log2() {

    double logTwo = Math.log(2.0d);
    DoubleComplex[] c = new DoubleComplex[this.real.length];

    double r, i;
    for (int in = 0; in < this.real.length; in++) {

      if (this.real[in] > 0.0d && this.imaginary[in] == 0.0d) {
        c[in] = new DoubleComplex(Math.log(this.real[in]) / logTwo, 0.0d);
      }

      r = this.log().real[in] / logTwo;
      i = this.log().imaginary[in] / logTwo;
      c[in] = new DoubleComplex(r, i);
    }
    return new ComplexVector(c);

  }

  /**
   * ベクトルの最大要素を求める.
   * 
   * @since 1.1
   * @return 最大要素
   */
  public DoubleComplex max() {
    DoubleVector dv = this.abs();
    double max = (-1) * Double.MAX_VALUE;
    int index = 0;
    for (int i = 0; i < this.real.length; i++) {
      if (max < dv.get(i)) {
        index = i;
        max = dv.get(i);
      }
    }
    return new DoubleComplex(this.real[index], this.imaginary[index]);
  }

  // /**
  // * ベクトルの個々の要素の逆正接(arc tangent)
  // * @since 1.1
  // * @return ベクトルの個々の要素に対する逆正弦
  // */
  // public ComplexVector atan(){
  // DoubleComplex[] c = new DoubleComplex[ complexVector.length ];
  // for( int i = 0 ; i < complexVector.length ; i++ ){
  // c[i] = complexVector[i].atan();
  // }
  // return new ComplexVector(c);
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
  // public ComplexVector atan2( ComplexVector x ){
  // checkVectorSize(x);
  // DoubleComplex[] c = new DoubleComplex[ complexVector.length ];
  // for( int i = 0 ; i < complexVector.length ; i++ ){
  // c[ i ] = complexVector[i].atan2( x.complexVector[i] );
  // }
  // return new ComplexVector(c);
  // }

  /**
   * ベクトルの要素の平均(average).
   * 
   * @return 平均
   * @since 1.1
   */
  public DoubleComplex mean() {
    return this.sum().divide(new DoubleComplex(this.real.length, 0.0d));
  }

  /**
   * ベクトルの最小要素を求める.
   * 
   * @since 1.1
   * @return 最小要素
   */
  public DoubleComplex min() {
    DoubleVector dv = this.abs();
    double min = Double.MAX_VALUE;
    int index = 0;
    for (int i = 0; i < this.real.length; i++) {
      if (min > dv.get(i)) {
        index = i;
        min = dv.get(i);
      }
    }
    return new DoubleComplex(this.real[index], this.imaginary[index]);
  }

  /**
   * ベクトルの実数倍.
   * 
   * @since 1.1
   * @param times
   *          ベクトルを実数倍する値
   * @return ベクトルの個々の要素を実数倍した配列を返す.
   */
  public ComplexVector multiply(final DoubleComplex times) {
    ComplexVector rv = new ComplexVector(this.real.length, times);
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
  public ComplexVector multiply(final ComplexVector multiplier) {
    this.checkVectorSize(multiplier);

    double[] x = new double[this.real.length];
    double[] y = new double[this.real.length];

    for (int i = 0; i < this.real.length; i++) {

      x[i] = this.real[i] * multiplier.real[i] - this.imaginary[i] * multiplier.imaginary[i];
      y[i] = this.real[i] * multiplier.imaginary[i] + multiplier.real[i] * this.imaginary[i];

    }
    return new ComplexVector(x, y);
  }

  /**
   * 符号をマイナスにして返す. Unary minus.
   * 
   * @return -this
   */
  public ComplexVector negate() {
    // DoubleComplex[] c = new DoubleComplex[real.length];
    double[] x = new double[this.real.length];
    double[] y = new double[this.real.length];

    for (int i = 0; i < this.real.length; i++) {
      x[i] = -this.real[i];
      y[i] = -this.imaginary[i];
    }
    return new ComplexVector(x, y);

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
   * 2乗ノルムを返す.
   * 
   * @since 1.1
   * @return 2乗ノルム
   */
  public double norm() {
    DoubleVector v = this.abs();
    return Math.sqrt(v.multiply(v).sum());
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
   * 1乗ノルムを返す.
   * 
   * @since 1.1
   * @return 1乗ノルム
   */
  public double oneNorm() {
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
  public ComplexVector pow(final DoubleComplex d) {
    DoubleComplex[] c = new DoubleComplex[this.real.length];

    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).pow(d);
    }
    return new ComplexVector(c);
  }

  /**
   * ベクトルの要素の積(product).
   * 
   * @return 積
   * @since 1.1
   */
  public DoubleComplex product() {
    DoubleComplex product = new DoubleComplex(this.real[0], this.imaginary[0]);
    for (int i = 1; i < this.real.length; i++) {
      product = product.multiply(new DoubleComplex(this.real[i], this.imaginary[i]));
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
  public ComplexVector set(final int i, final DoubleComplex s) {

    this.real[i] = s.getReal();
    this.imaginary[i] = s.getImaginary();
    return new ComplexVector(this.real, this.imaginary);
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
   */
  public ComplexVector setVector(final int i0, final int i1, final ComplexVector x) {

    double[] dReal = this.real.clone();
    double[] dImaginary = this.imaginary.clone();

    try {
      for (int i = i0; i <= i1; i++) {
        dReal[i] = x.real[i - i0];
        dImaginary[i] = x.imaginary[i - i0];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ComplexVector(dReal, dImaginary);
  }

  /**
   * 部分ベクトルをセットする. Set a subvector.
   * 
   * @param col
   *          Array of col indices.
   * @param x
   *          A(c(:))
   * @return matrix
   */
  public ComplexVector setVector(final int[] col, final ComplexVector x) {

    double[] dReal = this.real.clone();
    double[] dImaginary = this.imaginary.clone();

    try {
      for (int i = 0; i < col.length; i++) {
        dReal[col[i]] = x.real[i];
        dImaginary[col[i]] = x.imaginary[i];
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
      throw new ArrayIndexOutOfBoundsException("Subvector indices.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ComplexVector(dReal, dImaginary);
  }

  /**
   * ベクトルの個々の要素の正弦(sine).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public ComplexVector sin() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).sin();
    }
    return new ComplexVector(c);
  }

  /**
   * 複素数の hyperbolic sine(双曲線正弦)を求める. <br />
   * sinh(x) を求める.
   * 
   * @return 現在のオブジェクトのhyperbolic sine(双曲線正弦)をComplex型(複素数)で返す.
   * @since 1.1
   */
  public ComplexVector sinh() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).sinh();
    }
    return new ComplexVector(c);
  }

  /**
   * 行ベクトルのサイズを返す.
   * 
   * @return 配列のサイズ
   * @since 1.1
   */
  public int size() {
    return this.real.length;
  }

  /**
   * ベクトルの要素の平方.
   * 
   * @since 1.1
   * @return ベクトルの個々の要素を平方したベクトル
   */
  public ComplexVector sqrt() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).sqrt();
    }
    return new ComplexVector(c);
  }

  /**
   * ベクトルの各要素にvalueを減算する.
   * 
   * @param value
   *          値
   * @return thisの各要素にvalueを引いた値
   * @since 1.1
   */
  public ComplexVector subtract(final DoubleComplex value) {
    return this.subtract(new ComplexVector(this.real.length, value));
  }

  /**
   * 減算.
   * 
   * @param v
   *          値
   * @return this - v を返す.
   * @since 1.1
   */
  public ComplexVector subtract(final ComplexVector v) {
    this.checkVectorSize(v);

    double[] realResult = new double[this.real.length];
    double[] imaginaryResult = new double[this.imaginary.length];

    for (int i = 0; i < this.real.length; i++) {
      realResult[i] = this.real[i] - v.real[i];
      imaginaryResult[i] = this.imaginary[i] - v.imaginary[i];
    }
    return new ComplexVector(realResult, imaginaryResult);
  }

  /**
   * ベクトルの要素の和(summation).
   * 
   * @return 和
   * @since 1.1
   */
  public DoubleComplex sum() {
    double realSum = 0.0d, imaginarySum = 0.0d;
    for (int i = 0; i < this.real.length; i++) {
      realSum += this.real[i];
      imaginarySum = this.imaginary[i];
    }
    return new DoubleComplex(realSum, imaginarySum);
  }

  /**
   * ベクトルの個々の要素の正接(tangent).
   * 
   * @since 1.1
   * @return ベクトルの個々の要素に対する正弦
   */
  public ComplexVector tan() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).tan();
    }
    return new ComplexVector(c);
  }

  /**
   * 複素数の hyperbolic tangent(双曲線正接)を求める. <br />
   * sinh(x) を求める.
   * 
   * @return 現在のオブジェクトのhyperbolic tangent(双曲線正接)をComplex型(複素数)で返す.
   * @since 1.1
   */
  public ComplexVector tanh() {
    DoubleComplex[] c = new DoubleComplex[this.real.length];
    for (int i = 0; i < this.real.length; i++) {
      c[i] = new DoubleComplex(this.real[i], this.imaginary[i]).tanh();
    }
    return new ComplexVector(c);
  }

  /**
   * 一行this.size()列の行列、Matrix型オブジェクトに変換して返す.
   * 
   * @return Matrix型のオブジェクト
   * @since 1.1
   */
  public ComplexMatrix toMatrix() {
    // DoubleComplex[][] dc = new DoubleComplex[1][this.real.length];
    double[][] realM = new double[1][this.real.length];
    double[][] imaginaryM = new double[1][this.real.length];
    realM[0] = this.real.clone();
    imaginaryM[0] = this.imaginary.clone();

    return new ComplexMatrix(realM, imaginaryM);

    // return new ComplexMatrix(new DoubleComplex[1][this.real.length]);
  }

  /**
   * 現在のオブジェクトがotherと同値であればtrueを返す.
   * 
   * @param other
   *          ComplexVectorオブジェクト
   * @return 同値であればtrueを返す
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof ComplexVector)) {
      return false;
    }
    ComplexVector castOther = (ComplexVector) other;
    return new EqualsBuilder().append(this.real, castOther.real).append(this.imaginary,
        castOther.imaginary).isEquals();
  }

  /**
   * オブジェクトのハッシュコードを求める.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.real).append(this.imaginary).toHashCode();
  }

  /**
   * 現在のオブジェクトの文字列表現を返す.
   * 
   * @return 文字列表現
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper(super.toString()).append("real", this.real)
        .append("imaginary", this.imaginary).toString();
  }

  /**
   * オブジェクトを比較する.
   * 
   * <pre>
   *      現在のオブジェクトの絶対値が対象となるオブジェクトより大きければ1,
   *      小さければ-1,同一であれば0を返す.
   * </pre>
   * 
   * @param other
   *          比較対象オブジェクト
   * @return int型の値
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(final ComplexVector other) {
    return new CompareToBuilder().append(this.abs(), other.abs()).toComparison();
  }

}
