package name.sugawara.hiroshi.math.complex;

import java.util.Arrays;

import name.sugawara.hiroshi.math.array.ArraysUtil;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 複素数を配列で扱うクラス. <br />
 * 使用例 :<br />
 * <code>
 * //&lt;i&gt;y = exp( x * i ) = cos( x ) + i * sin( x )&lt;/i&gt;の複素数配列を作成する.
 * double[] x = new double[1000];
 * double[] y = new double[1000];
 * double start = (-1.) * Math.PI;
 * double increment = 0.01;
 * for (int i = 0; i &lt; x.length; i++) {
 *   x[i] = Math.cos(start);
 *   y[i] = Math.sin(start);
 *   start += increment;
 * }
 * ComplexArray complexArray1 = new complexArray(x, y);
 * //この複素数配列 complexArray1 から実部と虚部の配列を取り出し
 * // &lt;i&gt;x + yi&lt;/i&gt; の形式で表示する.
 * double[] x = complexArray1.getRealArray();
 * double[] y = complexArray1.getImaginaryArray();
 * for (int i = 0; i &lt; x.length; i++) {
 *   System.out.println(x[i] + &quot; + &quot; + y[i] + &quot; i &quot;);
 * }
 * //複素数配列の累乗
 * //complexArray1 の complexArray2 乗を計算しそれをcomplexArray3配列に格納する.
 * double[] zR = new double[x.length];
 * double[] zI = new double[x.length];
 * java.util.Arrays.fill(zR, 0.1);
 * java.util.Arrays.fill(zI, 0.02);
 * ComplexArray complexArray2 = new complexArray(zR, zI);
 * ComplexArray complexArray3 = complexArray1.pow(complexArray2);
 * </code>
 * 
 * @see name.sugawara.hiroshi.math.complex.DoubleComplex *
 * @see name.sugawara.hiroshi.math.array.ArraysUtil *
 * @author Hiroshi Sugawara *
 * @version $Id: ComplexArray.java 109 2010-06-13 04:26:48Z sugawara $ *
 * @since 1.1 * *
 * @deprecated 代わりに{@link DoubleComplex}を使用する。
 */
@Deprecated
public final strictfp class ComplexArray implements Cloneable {

  /**
   * 実数部配列.
   */
  private double[] realArray;

  /**
   * 虚数部配列.
   */
  private double[] imaginaryArray;

  /**
   * 実部,虚部共に要素の値が全て0で引数でサイズ1の複素数配列を生成.
   * 
   * @since 1.1
   */
  public ComplexArray() {
    this(new double[]
    { 0.0d }, new double[]
    { 0.0d });
  }

  /**
   * 実部,虚部共に要素の値が全て0で引数で指定したサイズの複素数配列を生成.
   * 
   * @since 1.1
   * @param arraysize
   *          複素数配列のサイズを指定する
   */
  public ComplexArray(final int arraysize) {
    final double[] x = new double[arraysize];
    final double[] y = new double[arraysize];
    Arrays.fill(x, 0.0d);
    Arrays.fill(y, 0.0d);
    this.realArray = x.clone();
    this.imaginaryArray = y.clone();
  }

  /**
   * 実部,虚部に要素の値が全て、指定されたComplex型の値で引数で指定したサイズの複素数配列を生成.
   * 
   * @since 1.1
   * @param value
   *          Complex型の複素数を指定する
   * @param arraysize
   *          複素数配列のサイズを指定する
   */
  public ComplexArray(final DoubleComplex value, final int arraysize) {
    final double[] x = new double[arraysize];
    final double[] y = new double[arraysize];
    Arrays.fill(x, value.getReal());
    Arrays.fill(y, value.getImaginary());
    this.realArray = x;
    this.imaginaryArray = y;
  }

  /**
   * 引数で指定したComplex[]型オブジェクトをコンバートしたの複素数配列を生成.
   * 
   * @since 1.1
   * @param value
   *          DoubleComplex[]型の複素数配列を指定する
   */
  public ComplexArray(final DoubleComplex[] value) {
    final double[] real = new double[value.length];
    final double[] imag = new double[value.length];
    for (int i = 0; i < value.length; i++) {
      real[i] = value[i].getReal();
      imag[i] = value[i].getImaginary();
    }
    this.realArray = real.clone();
    this.imaginaryArray = imag;
  }

  /**
   * 実部,虚部それぞれの要素が全て、指定された2つの値とした、引数で指定したサイズの複素数配列を生成.
   * 
   * @since 1.1
   * @param x
   *          複素数の実部を指定する
   * @param y
   *          複素数の虚部を指定する
   * @param arraysize
   *          複素数配列のサイズを指定する
   */
  public ComplexArray(final double x, final double y, final int arraysize) {
    final double[] real = new double[arraysize];
    final double[] imag = new double[arraysize];
    Arrays.fill(real, x);
    Arrays.fill(imag, y);
    this.realArray = real;
    this.imaginaryArray = imag;
  }

  /**
   * 実部の要素が全て0,虚部それぞれの要素が全て、指定された値とした、引数で指定したサイズの複素数配列を生成.
   * 
   * @since 1.1
   * @param y
   *          複素数の虚部を指定する
   * @param arraysize
   *          複素数配列のサイズを指定する
   */
  public ComplexArray(final double y, final int arraysize) {
    this(0.0d, y, arraysize);
  }

  /**
   * 実部が0で引数で指定した虚部の複素数(純虚数)配列を生成.
   * 
   * @since 1.1
   * @param y
   *          yに虚部の配列を指定する.
   */
  public ComplexArray(final double[] y) {
    this(new double[y.length], y);
    final double[] x = new double[y.length];
    Arrays.fill(x, 0.0d);
    this.realArray = x;
  }

  /**
   * 実部、虚部共に引数で指定した複素数配列を生成.
   * 
   * @since 1.1
   * @param x
   *          xに実部を配列で指定する.
   * @param y
   *          yに虚部を配列で指定する.
   */
  public ComplexArray(final double[] x, final double[] y) {
    try {
      if (x.length != y.length) {
        throw new ComplexArrayException("The sizes of both of array differ.");
      }

    } catch (ComplexArrayException e) {
      e.printStackTrace();
    }
    this.realArray = x.clone();
    this.imaginaryArray = y.clone();
  }

  /**
   * 現在のオブジェクトの実部に引数で指定した配列をセットする.
   * 
   * @since 1.1
   * @param x
   *          xに実部を配列で指定する.
   */
  public void setRealArray(final double[] x) {
    try {
      if (this.realArray.length < x.length) {
        throw new ComplexArrayException("The length of the "
            + "array of an argument cannot be made larger than " + "the length of "
            + "the arrangement of an object.");
      }
    } catch (ComplexArrayException e) {
      e.printStackTrace();
    }
    this.realArray = x.clone();
  }

  /**
   * <pre>
   *    現在のオブジェクトの複素数配列の実部に引数で指定した
   *    double型の実数を配列の指定された場所にセット(上書き)する.
   * </pre>
   * 
   * @since 1.1
   * @param d
   *          double型の値を実数として指定する.
   * @param index
   *          配列上で複素数を代入する位置.
   */
  public void setReal(final double d, final int index) {
    final double[] real = this.realArray.clone();
    final double[] imaginary = this.imaginaryArray.clone();
    imaginary[index] = d;
    this.realArray = real;
    this.imaginaryArray = imaginary;
  }

  /**
   * 現在のオブジェクトの虚部に引数で指定した配列をセットする.
   * 
   * @since 1.1
   * @param y
   *          yに虚部を配列で指定する.
   * @uml.property name="imaginaryArray"
   */
  public void setImaginaryArray(final double[] y) {
    try {
      if (this.realArray.length < y.length) {
        throw new ComplexArrayException("The length of the array of an"
            + " argument cannot be made larger than the length of the arrangement of an object.");
      }
    } catch (ComplexArrayException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.imaginaryArray = y.clone();
  }

  /**
   * <pre>
   *     現在のオブジェクトの複素数配列の虚部に
   *     引数で指定したdouble型の値を配列の指定された場所に虚数としてセット(上書き)する.
   * </pre>
   * 
   * @since 1.1
   * @param d
   *          double型の値を虚数として指定する.
   * @param index
   *          配列上で複素数を代入する位置.
   */
  public void setImaginary(final double d, final int index) {
    double[] imag = this.getImaginaryArray();
    imag[index] = d;
    this.realArray = this.getRealArray();
    this.imaginaryArray = imag;
  }

  /**
   * <pre>
   *      現在のオブジェクトの複素数配列の実部と虚部それぞれに
   *      引数で指定したComplexオブジェクトを配列の指定された場所に複素数としてセット(上書き)する.
   * </pre>
   * 
   * @since 1.1
   * @param c
   *          Complex型のオブジェクト(複素数)を指定する.
   * @param index
   *          配列上で複素数を代入する位置.
   */
  public void setComplex(final DoubleComplex c, final int index) {
    double[] real = this.getRealArray();
    double[] imag = this.getImaginaryArray();
    real[index] = c.getReal();
    imag[index] = c.getImaginary();

    this.realArray = real;
    this.imaginaryArray = imag;
  }

  /**
   * 現在のオブジェクトより実部配列を取り出す.
   * 
   * @since 1.1
   * @return 実部配列を返す
   * @uml.property name="realArray"
   */
  public double[] getRealArray() {
    return this.realArray.clone();
  }

  /**
   * 現在のオブジェクトの実部配列より指定された位置の要素を取り出す.
   * 
   * @since 1.1
   * @param index
   *          取り出す複素数の実部配列のインデックス
   * @return 実部配列の要素を返す
   */
  public double getReal(final int index) {
    double[] real = this.getRealArray();
    return real[index];
  }

  /**
   * 現在のオブジェクトより虚部配列を取り出す.
   * 
   * @since 1.1
   * @return 虚部配列を返す
   * @uml.property name="imaginaryArray"
   */
  public double[] getImaginaryArray() {
    return this.imaginaryArray.clone();
  }

  /**
   * 現在のオブジェクトの虚部配列より指定された位置の要素を取り出す.
   * 
   * @since 1.1
   * @param index
   *          取り出す複素数の虚部配列のインデックス
   * @return 虚部配列の要素を返す
   */
  public double getImaginary(final int index) {
    double[] imag = this.getImaginaryArray();
    return imag[index];
  }

  /**
   * 現在のオブジェクトの複素数配列より指定された位置の要素を取り出しComplex型で返す.
   * 
   * @since 1.1
   * @param index
   *          取り出す複素数配列のインデックス
   * @return 複素数配列の要素をComplex型で返す
   */
  public DoubleComplex getComplex(final int index) {
    return new DoubleComplex(this.getReal(index), this.getImaginary(index));
  }

  /**
   * 現在のオブジェクトの符号に負を掛けたComplexArrayオブジェクトを返す. <br />
   * 値が -this のComplexArrayを返す
   * 
   * @since 1.1
   * @return -this
   */
  public ComplexArray negate() {
    return this.multiply(new ComplexArray(-1.0d, 0.0d, this.length()));
  }

  /**
   * 現在のオブジェクトに指定されたComplexオブジェクトを指定されたインデックスに挿入しComplexArray型で返す.
   * 
   * @param element
   *          挿入する要素
   * @param index
   *          要素を挿入する位置
   * @return ComplexArrayオブジェクトを返す
   * @since 1.1
   */
  public ComplexArray insert(final DoubleComplex element, final int index) {

    double[] real = ArraysUtil.insert(this.getRealArray(), element.getReal(), index);
    double[] imag = ArraysUtil.insert(this.getImaginaryArray(), element.getImaginary(), index);
    return new ComplexArray(real, imag);
  }

  /**
   * 現在のオブジェクトの指定されたComplexオブジェクト要素を削除しComplexArray型で返す.
   * 
   * @param index
   *          要素を削除する位置
   * @return ComplexArrayオブジェクトを返す
   * @since 1.1
   */
  public ComplexArray delete(final int index) {
    double[] real = ArraysUtil.delete(this.getRealArray(), index);
    double[] imag = ArraysUtil.delete(this.getImaginaryArray(), index);
    return new ComplexArray(real, imag);
  }

  /**
   * 現在のオブジェクトの複素数配列の各要素を全て0にする(上書き).
   * 
   * @since 1.1
   */
  public void zeros() {
    double[] real = this.getRealArray();
    double[] imag = this.getImaginaryArray();
    Arrays.fill(real, 0.);
    Arrays.fill(imag, 0.);
    this.realArray = real;
    this.imaginaryArray = imag;
  }

  /**
   * 現在のオブジェクトの複素数配列の各要素を全て1にする.
   * 
   * @since 1.1
   * @return ComplexArray型でを返す
   */
  public ComplexArray ones() {
    double[] real = this.getRealArray();
    double[] imag = this.getImaginaryArray();
    Arrays.fill(real, 1.);
    Arrays.fill(imag, 1.);
    return new ComplexArray(real, imag);
  }

  /**
   * 現在のオブジェクトの複素数配列の後方に指定された複素数配列を連結する.
   * 
   * @since 1.1
   * @param c
   *          現在のオブジェクトの複素数配列の後方に連結されるComplexArrayの配列
   * @return 連結された複素数配列
   */
  public ComplexArray concatenate(final ComplexArray c) {
    double[] xfront = this.getRealArray();
    double[] yfront = this.getImaginaryArray();
    double[] xrear = c.getRealArray();
    double[] yrear = c.getImaginaryArray();
    double[] xcombine = ArraysUtil.concatenate(xfront, xrear);
    double[] ycombine = ArraysUtil.concatenate(yfront, yrear);
    return new ComplexArray(xcombine, ycombine);
  }

  /**
   * 現在のオブジェクトの複素数配列の配列の要素を左右逆順にする.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数配列の配列の要素を左右逆順にした複素数配列を返す
   */
  public ComplexArray fliplr() {
    double[] x = ArraysUtil.fliplr(this.getRealArray());
    double[] y = ArraysUtil.fliplr(this.getImaginaryArray());
    return new ComplexArray(x, y);
  }

  /**
   * 複素数の配列の要素を列挙する(Test表示用).
   * 
   * @since 1.1
   */
  public void display() {
    for (int i = 0; i < this.length(); i++) {
      double[] x = this.getRealArray();
      double[] y = this.getImaginaryArray();
      System.out.println("Array[" + i + "]=" + x[i] + " + " + y[i] + "i");
    }
  }

  /**
   * 複素数の配列のサイズを返す.
   * 
   * @since 1.1
   * @return 複素数の配列のサイズを返す.
   */
  public int length() {
    ArraysUtil.compareToEachArraySize(this.getRealArray(), this.getImaginaryArray());
    return this.getRealArray().length;
  }

  /**
   * 現在のオブジェクトの複素数配列の実部配列と虚部配列を交換したComplexArrayオブジェクトを返す.
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数配列の実部配列と虚部配列を交換たComplexArrayオブジェクト
   */
  public ComplexArray swap() {
    return new ComplexArray(this.getImaginaryArray(), this.getRealArray());
  }

  /**
   * 現在のオブジェクトの全要素に指定された単一複素数を代入した複素数配列を返す.
   * 
   * <pre>
   *       java.util.Arrays#fill(double[] a, double) の複素数対応型
   * </pre>
   * 
   * @since 1.1
   * @param c
   *          代入する複素数
   * @return 複素数配列
   */
  public ComplexArray fill(final DoubleComplex c) {
    double[] arrayReal = this.getRealArray();
    double[] arrayImaginary = this.getImaginaryArray();
    Arrays.fill(arrayReal, c.getReal());
    Arrays.fill(arrayImaginary, c.getImaginary());
    return new ComplexArray(arrayReal, arrayImaginary);
  }

  /**
   * 現在のオブジェクト(ComplexArray)に指定された範囲にある 各要素にに指定された単一複素数(DoubleComplex)を割り当てた複素数配列を返す.
   * 
   * <pre>
   *       java.util.Arrays#fill(double[] a, int fromIndex, int toIndex, double val)
   *       の複素数対応型
   * </pre>
   * 
   * @param fromIndex
   *          配列インデックス開始位置
   * @param toIndex
   *          配列インデックス終了位置
   * @param c
   *          代入する複素数
   * @return 現在のオブジェクト(ComplexArray)の全要素に指定された単一複素数(DoubleComplex)を代入した複素数配列を返す.
   * @since 1.1
   */
  public ComplexArray fill(final int fromIndex, final int toIndex, final DoubleComplex c) {
    double[] arrayReal = this.getRealArray();
    double[] arrayImaginary = this.getImaginaryArray();
    Arrays.fill(arrayReal, fromIndex, toIndex, c.getReal());
    Arrays.fill(arrayImaginary, fromIndex, toIndex, c.getImaginary());
    return new ComplexArray(arrayReal, arrayImaginary);
  }

  /**
   * 複素数の加算.
   * 
   * @since 1.1
   * @param b
   *          加算する複素数
   * @return 現在のオブジェクトの複素数から指定された複素数を加算した複素数を配列で返す.
   */
  public ComplexArray add(final ComplexArray b) {
    double[] x = ArraysUtil.add(this.getRealArray(), b.getRealArray());
    double[] y = ArraysUtil.add(this.getImaginaryArray(), b.getImaginaryArray());
    return new ComplexArray(x, y);
  }

  /**
   * 複素数配列に単一の複素数を引数とした、要素が全て同じ複素数オブジェクトの配列を加算.
   * 
   * @since 1.1
   * @param c
   *          加算する単一複素数(Complexオブジェクト)
   * @return 現在のComplexオブジェクトのから指定された単一の複素数からなる配列を加算した複素数配列を返す.
   */
  public ComplexArray add(final DoubleComplex c) {
    double real = c.getReal();
    double imag = c.getImaginary();
    double[] realarray = this.getRealArray();
    double[] imagarray = this.getImaginaryArray();
    double[] x = ArraysUtil.add(realarray, real);
    double[] y = ArraysUtil.add(imagarray, imag);

    return new ComplexArray(x, y);
  }

  /**
   * 複素数の減算.
   * 
   * @since 1.1
   * @param b
   *          減算する複素数
   * @return 現在のオブジェクトの複素数から指定された複素数を減算した複素数を返す.
   */
  public ComplexArray subtract(final ComplexArray b) {
    double[] x = ArraysUtil.subtract(this.getRealArray(), b.getRealArray());
    double[] y = ArraysUtil.subtract(this.getImaginaryArray(), b.getImaginaryArray());
    return new ComplexArray(x, y);
  }

  /**
   * 複素数の乗算. <br />
   * 
   * @since 1.1
   * @param b
   *          乗算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を乗算した複素数を返す.
   */
  public ComplexArray multiply(final ComplexArray b) {
    double[] x = ArraysUtil.subtract(ArraysUtil.multiply(this.getRealArray(), b.getRealArray()),
        ArraysUtil.multiply(this.getImaginaryArray(), b.getImaginaryArray()));
    double[] y = ArraysUtil.add(ArraysUtil.multiply(this.getRealArray(), b.getImaginaryArray()),
        ArraysUtil.multiply(b.getRealArray(), this.getImaginaryArray()));
    return new ComplexArray(x, y);
  }

  /**
   * 複素数の除算. <br />
   * November.13.2002 上位桁あふれ対策済み <br />
   * 
   * @since 1.1
   * @param b
   *          除算する複素数
   * @return 現在のオブジェクトの複素数に指定された複素数を除算した複素数を返す.
   */
  public ComplexArray divide(final ComplexArray b) {
    double[] bRealSquare = ArraysUtil.pow(b.getRealArray(), 2);
    double[] bImaginarySquare = ArraysUtil.pow(b.getImaginaryArray(), 2);
    double[] geometricNotMean = ArraysUtil.add(bRealSquare, bImaginarySquare);
    double w, d;
    double[] real = new double[this.length()];
    double[] imaginary = new double[this.length()];

    for (int i = 0; i < b.length(); i++) {
      if (geometricNotMean[i] <= Double.MAX_VALUE
          || geometricNotMean[i] == Double.POSITIVE_INFINITY) {
        real[i] = (b.getReal(i) * this.getReal(i) + this.getImaginary(i) * b.getImaginary(i))
            / geometricNotMean[i];
        imaginary[i] = (b.getReal(i) * this.getImaginary(i) - this.getReal(i) * b.getImaginary(i))
            / geometricNotMean[i];
      } else if (Math.abs(b.getReal(i)) >= Math.abs(b.getImaginary(i))) {
        w = b.getImaginary(i) / b.getReal(i);
        d = b.getReal(i) + b.getImaginary(i) * w;
        real[i] = (this.getReal(i) + this.getImaginary(i) * w) / d;
        imaginary[i] = (this.getImaginary(i) - this.getReal(i) * w) / d;
      } else {
        w = b.getReal(i) / b.getImaginary(i);
        d = b.getReal(i) * w + b.getImaginary(i);
        real[i] = (this.getReal(i) * w + this.getImaginary(i)) / d;
        imaginary[i] = (this.getImaginary(i) * w - this.getReal(i)) / d;
      }
    }
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の共役複素数を求める. <br />
   * 
   * @since 1.1
   * @return 現在のオブジェクトの共役複素数を返す.
   */
  public ComplexArray conjugate() {
    double[] x = this.getRealArray();
    double[] y = ArraysUtil.multiply(this.getImaginaryArray(), -1.0d);
    return new ComplexArray(x, y);
  }

  /**
   * 複素数の絶対値を求める. <br />
   * sqrt( real^2 + imag^2 )
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の絶対値を返す.
   */
  public double[] abs() {
    return ArraysUtil.hypot2(this.getRealArray(), this.getImaginaryArray());
  }

  /**
   * 複素数の偏角を求める. <br />
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の偏角をRadian(ラディアン)で返す.
   */
  public double[] arg() {
    double[] x = this.getRealArray();
    double[] y = this.getImaginaryArray();
    return ArraysUtil.atan2(y, x);
  }

  /**
   * 複素数の平方根を求める. <br />
   * this^(1/2) 複素数の平方にはドモアブルの定理を使用している. <br />
   * 
   * @since 1.1
   * @return 現在のオブジェクトの複素数の平方根を返す.
   */
  public ComplexArray sqrt() {
    final double[] x = new double[this.length()];
    final double[] y = new double[this.length()];
    final double[] r = this.abs();
    final double sqrt05 = Math.sqrt(0.5);
    final double[] w = ArraysUtil.sqrt(ArraysUtil.add(r, ArraysUtil.abs(this.getRealArray())));

    for (int i = 0; i < this.length(); i++) {
      if (this.getReal(i) >= 0) {
        x[i] = sqrt05 * w[i];
        y[i] = sqrt05 * this.getImaginary(i) / w[i];
      } else {
        x[i] = sqrt05 * Math.abs(this.getImaginary(i)) / w[i];
        if (this.getImaginary(i) >= 0) {
          y[i] = sqrt05 * w[i];
        } else {
          y[i] = -sqrt05 * w[i];
        }
      }
    }
    return new ComplexArray(x, y);
  }

  /**
   * 複素数のべき乗を実数乗で求める. <br />
   * this^d 複素数の累乗にはドモアブルの定理を使用している. <br />
   * 
   * @since 1.1
   * @param d
   *          累乗したいdouble型の値
   * @return 現在のオブジェクトの複素数の実数(double型)乗をComplexArray型(複素数)で返す.
   */
  public ComplexArray pow(final double d) {
    double[] r = this.abs();
    double[] theta = this.arg();
    double[] x = ArraysUtil.multiply(ArraysUtil.pow(r, d), ArraysUtil.cos(ArraysUtil.multiply(
        theta, d)));
    double[] y = ArraysUtil.multiply(ArraysUtil.pow(r, d), ArraysUtil.sin(ArraysUtil.multiply(
        theta, d)));
    return new ComplexArray(x, y);
  }

  /**
   * 複素数のべき乗を複素数乗で求める. <br />
   * <i>z = this^b </i> <br />
   * <i>this = real + i*imag </i> <br />
   * <i>b = u + i*v </i> <br />
   * <i>(real + i*imag)^(u + i*v) </i> <br />
   * 複素数の累乗にはドモアブルの定理を使用している. <br />
   * 
   * @since 1.1
   * @param b
   *          累乗したいComplex(複素数)型の値
   * @return 現在のオブジェクトの複素数の複素数(ComplexArray型)乗をComplexArray型(複素数)で返す.
   */
  public ComplexArray pow(final ComplexArray b) {
    double[] u = b.getRealArray();
    double[] v = b.getImaginaryArray();
    double[] r = this.abs();
    double[] theta = this.arg();
    double[] temp = ArraysUtil.exp(ArraysUtil.subtract(ArraysUtil.multiply(u, ArraysUtil.log(r)),
        ArraysUtil.multiply(v, theta)));
    double[] value = ArraysUtil.add(ArraysUtil.multiply(v, ArraysUtil.log(r)), ArraysUtil.multiply(
        u, theta));
    double[] x = ArraysUtil.multiply(temp, ArraysUtil.cos(value));
    double[] y = ArraysUtil.multiply(temp, ArraysUtil.sin(value));
    return new ComplexArray(x, y);
  }

  /**
   * 自然対数の底の複素数乗を求める. <br />
   * exp(x) を求める. 複素数の累乗にはドモアブルの定理を使用している. <br />
   * 
   * @return 自然対数の底の現在の複素数オブジェクト乗をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray exp() {
    double[] a = ArraysUtil.exp(this.getRealArray());
    double[] real = ArraysUtil.multiply(a, ArraysUtil.cos(this.getImaginaryArray()));
    double[] imaginary = ArraysUtil.multiply(a, ArraysUtil.sin(this.getImaginaryArray()));
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の自然対数を求める. <br />
   * log(x) を求める.
   * 
   * @return 現在のオブジェクトの自然対数をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray log() {
    final double half = 0.5d;
    double[] a = ArraysUtil.multiply(this.getRealArray(), this.getRealArray());
    double[] b = ArraysUtil.multiply(this.getImaginaryArray(), this.getImaginaryArray());
    double[] c = ArraysUtil.add(a, b);
    double[] d = ArraysUtil.log(c);
    double[] real = ArraysUtil.multiply(d, half);
    double[] imaginary = ArraysUtil.atan2(this.getImaginaryArray(), this.getRealArray());
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の常用対数を求める. <br />
   * log(x) を求める.
   * 
   * @return 現在のオブジェクトの自然対数をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray log10() {
    final double ten = 10.0d, zero = 0.0d;
    double[] logTen = new double[this.length()];
    double[] zeros = new double[this.length()];
    Arrays.fill(logTen, Math.log(ten));
    Arrays.fill(zeros, zero);
    ComplexArray c = new ComplexArray(logTen, zeros);
    return this.divide(c);
  }

  /**
   * 複素数の2を底とする対数を求める. <br />
   * log(x) を求める.
   * 
   * @return 現在のオブジェクトの2を底とする対数をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray log2() {
    double[] logTwo = new double[this.length()];
    double[] zeros = new double[this.length()];
    Arrays.fill(logTwo, Math.log(2.0d));
    Arrays.fill(zeros, 0.0d);
    ComplexArray c = new ComplexArray(logTwo, zeros);
    return this.divide(c);
  }

  /**
   * 複素数のサイン(正弦)を求める. <br />
   * sin(x) を求める.
   * 
   * @return 現在のオブジェクトのサイン(正弦)をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray sin() {
    final double half = 0.5d;
    double[] e = ArraysUtil.exp(this.getImaginaryArray());
    double[] f = ArraysUtil.divide(1, e);

    double[] eSubF = ArraysUtil.subtract(e, f);
    double[] cos = ArraysUtil.multiply(ArraysUtil.cos(this.getRealArray()), eSubF);
    double[] imaginary = ArraysUtil.multiply(cos, half);

    double[] eAddF = ArraysUtil.add(e, f);
    double[] sin = ArraysUtil.multiply(ArraysUtil.sin(this.getRealArray()), eAddF);
    double[] real = ArraysUtil.multiply(sin, half);
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数のコサイン(余弦)を求める. <br />
   * cos(x) を求める.
   * 
   * @return 現在のオブジェクトのコサイン(余弦)をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray cos() {
    final double half = 0.5d;
    double[] e = ArraysUtil.exp(this.getImaginaryArray());
    double[] f = ArraysUtil.divide(1, e);

    double[] fSubE = ArraysUtil.subtract(f, e);
    double[] sin = ArraysUtil.multiply(ArraysUtil.sin(this.getRealArray()), fSubE);
    double[] imaginary = ArraysUtil.multiply(sin, half);

    double[] fAddE = ArraysUtil.add(f, e);
    double[] cos = ArraysUtil.multiply(ArraysUtil.cos(this.getRealArray()), fAddE);
    double[] real = ArraysUtil.multiply(cos, half);

    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数のタンジェント(正接)を求める. <br />
   * tan(x) を求める.
   * 
   * @return 現在のオブジェクトのタンジェント(正接)をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray tan() {
    final double half = 0.5d;
    double[] e = ArraysUtil.exp(ArraysUtil.multiply(this.getImaginaryArray(), 2.0d));
    double[] f = ArraysUtil.divide(1, e);
    double[] eAddF = ArraysUtil.add(e, f);
    double[] cos = ArraysUtil.cos(ArraysUtil.multiply(this.getRealArray(), 2.0d));

    double[] d = ArraysUtil.add(cos, ArraysUtil.multiply(eAddF, half));
    double[] real = ArraysUtil.divide(ArraysUtil
        .sin(ArraysUtil.multiply(this.getRealArray(), 2.0d)), d);
    double[] eSubF = ArraysUtil.subtract(e, f);

    double[] imaginary = ArraysUtil.multiply(ArraysUtil.divide(eSubF, d), half);
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の hyperbolic sine(双曲線正弦)を求める. <br />
   * sinh(x) を求める.
   * 
   * @return 現在のオブジェクトのhyperbolic sine(双曲線正弦)をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray sinh() {
    final double half = 0.5d;
    double[] e = ArraysUtil.exp(this.getRealArray());
    double[] f = ArraysUtil.divide(1, e);

    double[] eSubF = ArraysUtil.subtract(e, f);
    double[] cos = ArraysUtil.multiply(ArraysUtil.cos(this.getImaginaryArray()), half);

    double[] real = ArraysUtil.multiply(eSubF, cos);

    double[] eAddF = ArraysUtil.add(e, f);
    double[] sin = ArraysUtil.multiply(ArraysUtil.sin(this.getImaginaryArray()), half);

    double[] imaginary = ArraysUtil.multiply(eAddF, sin);
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の hyperbolic cosine(双曲線余弦)を求める. <br />
   * sinh(x) を求める.
   * 
   * @return 現在のオブジェクトのhyperbolic cosine(双曲線余弦)をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray cosh() {
    final double half = 0.5d;
    double[] e = ArraysUtil.exp(this.getRealArray());
    double[] f = ArraysUtil.divide(1, e);

    double[] eAddF = ArraysUtil.add(e, f);
    double[] cos = ArraysUtil.multiply(ArraysUtil.cos(this.getImaginaryArray()), half);
    double[] real = ArraysUtil.multiply(eAddF, cos);

    double[] eSubF = ArraysUtil.subtract(e, f);

    double[] sin = ArraysUtil.multiply(ArraysUtil.sin(this.getImaginaryArray()), half);

    double[] imaginary = ArraysUtil.multiply(eSubF, sin);

    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の hyperbolic tangent(双曲線正接)を求める. <br />
   * sinh(x) を求める.
   * 
   * @return 現在のオブジェクトのhyperbolic tangent(双曲線正接)をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray tanh() {
    final double half = 0.5d;
    double[] e = ArraysUtil.exp(ArraysUtil.multiply(this.getRealArray(), 2.0d));
    double[] f = ArraysUtil.divide(1, e);

    double[] eAddF = ArraysUtil.add(e, f);
    double[] cos = ArraysUtil.cos(ArraysUtil.multiply(this.getImaginaryArray(), 2.0d));

    double[] d = ArraysUtil.add(ArraysUtil.multiply(eAddF, half), cos);

    double[] eSubF = ArraysUtil.subtract(e, f);

    double[] real = ArraysUtil.divide(ArraysUtil.multiply(eSubF, half), d);
    double[] imaginary = ArraysUtil.divide(ArraysUtil.sin(ArraysUtil.multiply(this
        .getImaginaryArray(), 2.0d)), d);
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の arc cosine(逆余弦)を求める. <br />
   * acos(x) を求める.
   * 
   * @return 現在のオブジェクトの arc cosine(逆余弦) をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray acos() {
    final double minusTwo = -2.0d;
    final double sqrt05 = Math.sqrt(0.5d);
    double[] x = this.getRealArray();
    double[] y = this.getImaginaryArray();
    double[] xSquare = ArraysUtil.multiply(x, x);
    double[] ySquare = ArraysUtil.multiply(y, y);
    double[] a = ArraysUtil.add(ArraysUtil.subtract(1.0d, xSquare), ySquare);

    double[] b = ArraysUtil.multiply(ArraysUtil.multiply(x, y), minusTwo);

    double[] r = new ComplexArray(a, b).abs();

    double[] sqrtReal = new double[x.length];
    double[] sqrtImaginary = new double[x.length];

    for (int i = 0; i < x.length; i++) {
      if (a[i] > 0.0d) {
        sqrtReal[i] = x[i] - (sqrt05 * b[i] / Math.sqrt(r[i] + a[i]));
        sqrtImaginary[i] = y[i] + sqrt05 * Math.sqrt(r[i] + a[i]);
      } else if (a[i] == 0.0d) {
        sqrtReal[i] = x[i] - Math.sqrt(b[i] / 2);
        sqrtImaginary[i] = y[i] + Math.sqrt(b[i] / 2);
      } else {
        sqrtReal[i] = x[i] - (sqrt05 * Math.sqrt(r[i] - a[i]));
        sqrtImaginary[i] = y[i] + (sqrt05 * b[i] / Math.sqrt(r[i] - a[i]));
      }
    }

    double[] real = ArraysUtil.atan2(sqrtImaginary, sqrtReal);

    double[] rArray = ArraysUtil.multiply(sqrtReal, sqrtReal);
    double[] iArray = ArraysUtil.multiply(sqrtImaginary, sqrtImaginary);

    final double minusHalf = -0.5d;
    double[] imaginary = ArraysUtil.multiply(ArraysUtil.log(ArraysUtil.add(rArray, iArray)),
        minusHalf);
    return new ComplexArray(real, imaginary);
  }

  /**
   * 複素数の arc sine(逆正弦)を求める. <br />
   * asin(x) を求める.
   * 
   * @return 現在のオブジェクトの arc sine(逆正弦) をComplexArray型(複素数)で返す.
   * @since 1.1
   */
  public ComplexArray asin() {
    final double sqrt05 = Math.sqrt(0.5d);
    double[] x = this.getRealArray();
    double[] y = this.getImaginaryArray();

    double[] xSquare = ArraysUtil.multiply(x, x);
    double[] ySquare = ArraysUtil.multiply(y, y);
    double[] a = ArraysUtil.add(ArraysUtil.subtract(xSquare, ySquare), 1.0d);

    double[] b = ArraysUtil.multiply(ArraysUtil.multiply(x, y), 2.0d);

    double[] r = new ComplexArray(a, b).abs();

    double[] sqrtReal = new double[x.length];
    double[] sqrtImaginary = new double[x.length];

    for (int i = 0; i < x.length; i++) {
      if (a[i] > 0.0d) {
        sqrtReal[i] = (sqrt05 * Math.sqrt(r[i] + a[i])) - y[i];
        sqrtImaginary[i] = x[i] + sqrt05 * b[i] / Math.sqrt(r[i] + a[i]);
      } else if (a[i] == 0.0d) {
        sqrtReal[i] = (sqrt05 * Math.sqrt(b[i])) - y[i];
        sqrtImaginary[i] = y[i] + sqrt05 * Math.sqrt(b[i]);
      } else {
        sqrtReal[i] = sqrt05 * b[i] / Math.sqrt(r[i] - a[i]) - y[i];
        sqrtImaginary[i] = x[i] + (sqrt05 * Math.sqrt(r[i] - a[i]));
      }
    }

    double[] real = ArraysUtil.atan2(sqrtImaginary, sqrtReal);

    double[] rArray = ArraysUtil.multiply(sqrtReal, sqrtReal);
    double[] iArray = ArraysUtil.multiply(sqrtImaginary, sqrtImaginary);
    final double munusHalf = -0.5d;

    double[] imaginary = ArraysUtil.multiply(ArraysUtil.log(ArraysUtil.add(rArray, iArray)),
        munusHalf);
    return new ComplexArray(real, imaginary);
  }

  /**
   * 現在のオブジェクトをComplex[]型に変換したオブジェクトを返す. <br />
   * 
   * @since 1.1
   * @return DoubleComplex[]オブジェクトを返す
   */
  public DoubleComplex[] convertToComplex() {

    double[] real = this.getRealArray();
    double[] imag = this.getImaginaryArray();
    DoubleComplex[] c = new DoubleComplex[this.length()];
    for (int i = 0; i < this.length(); i++) {
      c[i] = new DoubleComplex(real[i], imag[i]);
    }
    return c;
  }

  /**
   * 現在のComplexArrayオブジェクト内の配列の各要素の複素数のうち絶対値が最大の値を返す. <br />
   * 
   * @since 1.1
   * @return 現在のComplexArrayオブジェクト内の配列の各要素の複素数のうち絶対値が最大となる値
   */
  public double max() {
    return ArraysUtil.max(this.abs());
  }

  /**
   * 現在のComplexArrayオブジェクト内の配列の各要素の複素数のうち絶対値が最小の値を返す. <br />
   * 
   * @since 1.1
   * @return 現在のComplexArrayオブジェクト内の配列の各要素の複素数のうち絶対値が最小となる値
   */
  public double min() {
    return ArraysUtil.min(this.abs());
  }

  /**
   * 引数で指定された配列と現在のオブジェクトとのconvolution(畳み込み)をComplexArray型で返す. <br />
   * 返された配列のサイズは2つの配列のサイズの和から1を引いた値になる.
   * 
   * @param h
   *          システム(配列)
   * @return サイズが this + h - 1 のComplexArray型を返す
   * @since 1.1
   */
  public ComplexArray convolute(final ComplexArray h) {
    double[] r = ArraysUtil.convolute(this.getRealArray(), h.getRealArray());
    double[] i = ArraysUtil.convolute(this.getImaginaryArray(), h.getImaginaryArray());
    return new ComplexArray(r, i);
  }

  /**
   * 引数で指定された配列と現在のオブジェクトとのcross-correlation(相互相関)をComplexArray型で返す. <br />
   * 返された配列のサイズは2つの配列のサイズの和から1を引いた値になる.
   * 
   * @param h
   *          システム(配列)
   * @return サイズが this + h - 1 のComplexArray型を返す
   * @since 1.1
   */
  public ComplexArray correlate(final ComplexArray h) {
    double[] r = ArraysUtil.correlate(this.getRealArray(), h.getRealArray());
    double[] i = ArraysUtil.correlate(this.getImaginaryArray(), h.getImaginaryArray());
    return new ComplexArray(r, i);
  }

  /**
   * 現在のオブジェクトのauto-correlation(自己相関)をComplexArray型で返す. <br />
   * 返された配列のサイズは配列のサイズの2倍から1を引いた値になる.
   * 
   * @return サイズが 2 * this - 1 のComplexArray型の配列を返す
   * @since 1.1
   */
  public ComplexArray correlate() {
    double[] r = ArraysUtil.correlate(this.getRealArray());
    double[] i = ArraysUtil.correlate(this.getImaginaryArray());
    return new ComplexArray(r, i);
  }

  /**
   * 現在のオブジェクトとotherが同値であるときtrueを返す.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return thisとotherが同値であるときtrueを返す
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof ComplexArray)) {
      return false;
    }
    ComplexArray castOther = (ComplexArray) other;
    return new EqualsBuilder().append(this.realArray, castOther.realArray).append(
        this.imaginaryArray, castOther.imaginaryArray).isEquals();
  }

  /**
   * 現在のオブジェクトのハッシュコードを得る.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.realArray).append(this.imaginaryArray).toHashCode();
  }

}

/**
 * デモ.
 * 
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

final class ComplexArrayDemo {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/11 23:08:24
   */
  private ComplexArrayDemo() {
    // コンストラクタ使用禁止.
  }

  /**
   * メイン.
   * 
   * @param args
   *          引数
   * @since 2004/08/03
   */
  public static void main(final String[] args) {
    // DoubleComplex complex1 = new DoubleComplex(1, 2);
    final int size = 10;

    double[] arrayR = new double[size];
    double[] arrayI = new double[size];
    for (int i = 0; i < arrayR.length; i++) {
      arrayR[i] = i;
      arrayI[i] = -i;
    }

    ComplexArray p6 = new ComplexArray(arrayR, arrayI);
    p6.display();
    ComplexArray p7 = p6.sin();
    p7.display();

  }
}
