/*
 * Created on 2003/05/03
 */
package name.sugawara.hiroshi.math.matrix;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import name.sugawara.hiroshi.math.function.typedouble.DoubleMath;

/**
 * double型行列. Mathworks, Inc のJAMA(A Java Matrix Package)を参考にして作成.
 *
 * @author Hiroshi Sugawara
 * @version $Id: DoubleMatrix.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final strictfp class DoubleMatrix extends Matrix implements Cloneable, Serializable {

  /**
   * シリアルバージョンID.
   *
   * @since 2005/10/28 14:11:40
   */
  private static final long serialVersionUID = 1L;

  /**
   * 行数.
   *
   * @serial 行の次元column dimention
   * @uml.property name="col"
   */
  private int               col;

  /**
   * row * col サイズの行列の要素を格納するための配列.
   *
   * @serial internal array storage.
   * @uml.property name="matrix" readOnly="true" multiplicity="(0 -1)" dimension="1"
   */
  private final double[][]  matrix;

  /**
   * 列数.
   *
   * @serial row dimention
   * @uml.property name="row"
   */
  private int               row;

  /**
   * 1次元配列から、配列を列数で区切って行列を作る.
   *
   * @param vals
   *          列でパックされたdouble型の一次元配列
   * @param row
   *          Number of rows.
   */
  public DoubleMatrix(final double[] vals, final int row) {
    if (row * this.col != vals.length) {
      throw new IllegalArgumentException("Array length must be a multiple of row.");
    }
    this.row = row;
    if (row != 0) {
      this.col = vals.length / row;
    } else {
      this.col = 0;
    }
    this.matrix = new double[row][this.col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.matrix[i][j] = vals[i + j * row];
      }
    }
  }

  /**
   * 既存の2次元配列から行列クラスを生成.
   *
   * @param matrix
   *          double型の2次元配列.
   */
  public DoubleMatrix(final double[][] matrix) {
    this.row = matrix.length;
    this.col = matrix[0].length;
    for (int i = 0; i < this.row; i++) {
      if (matrix[i].length != this.col) {
        throw new IllegalArgumentException("All rows must have the same length.");
      }
    }
    this.matrix = matrix.clone();
  }

  /**
   * 要素がすべて0 の m * n サイズの行列を作る. Construct an m-by-n matrix of zeros.
   *
   * @param row
   *          Number of rows.
   * @param col
   *          Number of colums.
   */
  public DoubleMatrix(final int row, final int col) {
    this(row, col, 0.0d);
  }

  /**
   * 要素がすべてs の m * n サイズの行列を作る. Construct an m-by-n matrix of s.
   *
   * @param row
   *          行数
   * @param col
   *          列数
   * @param s
   *          行列の要素値
   */
  public DoubleMatrix(final int row, final int col, final double s) {
    this.matrix = new double[row][col];
    this.row = row;
    this.col = col;
    for (int r = 0; r < row; r++) {
      Arrays.fill(this.matrix[r], s);
    }
  }

  /**
   * 行列の個々の要素に対する絶対値を行列で返す.
   *
   * @since 1.1
   * @return ベクトル要素に対する絶対値行列
   */
  public DoubleMatrix abs() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.abs(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の逆余弦(arc cosine).
   *
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public DoubleMatrix acos() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.acos(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * C = a + b.
   *
   * @param b
   *          another matrix
   * @return a + b
   */
  public DoubleMatrix add(final DoubleMatrix b) {
    this.checkMatrixDimensions(b);
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j] + b.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の逆正弦(arc sine).
   *
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public DoubleMatrix asin() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.asin(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の逆正接(arc tangent).
   *
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public DoubleMatrix atan() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.atan(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の要素の4象限逆正接 Four Quadrant Arc Tangent. 現在のオブジェクトを、ラジアンで表した、 arctan関数でyを変数とする行列 とし指定された
   * 角度の4象限逆正接 (4象限アークタンジェント) を返す
   *
   * @since 1.1
   * @param x
   *          ラジアンで表した arctan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarctan関数に代入した配列
   */
  public DoubleMatrix atan2(final DoubleMatrix x) {
    this.checkMatrixDimensions(x);
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.atan2(this.matrix[i][j], x.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * size(a) == size(b)　かどうかを確認する。
   *
   * @param b
   *          行列
   * @since 2004/08/03
   */
  private void checkMatrixDimensions(final DoubleMatrix b) {
    if (b.row != this.row || b.col != this.col) {
      throw new IllegalArgumentException("DoubleMatrix の次元は同一である必要があります。");
    }
  }

  /**
   * オブジェクトのクローン. <br />
   * 行列の要素はdeep copyされる.
   *
   * @since 1.1
   * @return 行列
   * @throws CloneNotSupportedException
   *           clone()をサポートしないとき
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    super.clone();
    final double[][] c = Arrays.copyOf(this.matrix, this.matrix.length);

    // c = (double[][])matrix.clone();
    // for (int i = 0; i < this.row; i++) {
    // c[i] = this.matrix[i].clone();
    // for (int j = 0; j < this.col; j++) {
    // c[i][j] = this.matrix[i][j];
    // }
    // }

    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の余弦(cosine).
   *
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public DoubleMatrix cos() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.cos(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 配列の各要素に指定された値を除算.
   *
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public DoubleMatrix divide(final double value) {
    final DoubleMatrix rv = new DoubleMatrix(this.row, this.col, value);
    return this.divide(rv);
  }

  /**
   * 配列どうしの除算. 双方の配列はそれぞれサイズが同じである必要がある.
   *
   * @since 1.1
   * @param v
   *          除算する配列
   * @return 指定された配列aと指定された配列bを除算した配列
   */
  public DoubleMatrix divide(final DoubleMatrix v) {

    this.checkMatrixDimensions(v);
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < c.length; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j] / v.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 配列の要素のexp関数 exp(x).
   *
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列
   */
  public DoubleMatrix exp() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.exp(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の列を左右逆順に反転.
   *
   * @return 列を左右逆順に反転した行列
   * @since 1.1
   */
  public DoubleMatrix fliplr() {
    final double[][] flipped = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.matrix[i][this.col - 1 - j];
      }
    }
    return new DoubleMatrix(flipped);
  }

  /**
   * 行列の行を上下逆順に反転.
   *
   * @return 行を上下逆順に反転した行列を返す
   * @since 1.1
   */
  public DoubleMatrix flipud() {
    final double[][] flipped = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.matrix[this.row - 1 - i][j];
      }
    }
    return new DoubleMatrix(flipped);
  }

  /**
   * 行列の要素を取得する. Get a single element.
   *
   * @param i
   *          Row index.
   * @param j
   *          Column index.
   * @return matrix(i,j)
   * @exception ArrayIndexOutOfBoundsException
   */
  public double get(final int i, final int j) {
    if (i > this.getRowDimension() || j > this.getColumnDimension()) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return this.matrix[i][j];
    }
  }

  /**
   * 行列をdouble型2次元配列に変換する. Access the internal two-dimensional array.
   *
   * @return Pointer to the two-dimensional array of matrix elements.
   */
  public double[][] getArray() {
    return Arrays.copyOf(this.matrix, this.matrix.length);
  }

  /**
   * 行列の行数を取得する. Get column dimension.
   *
   * @return col, the number of columns.
   */
  public int getColumnDimension() {
    return this.col;
  }

  /**
   * 始点と終点を指定して現在の行列から部分行列を取り出す. Get a submatrix.
   *
   * @param i0
   *          Initial row index
   * @param i1
   *          Final row index
   * @param j0
   *          Initial column index
   * @param j1
   *          Final column index
   * @return A(i0:i1,j0:j1)
   */
  public DoubleMatrix getMatrix(final int i0, final int i1, final int j0, final int j1) {
    final double[][] x = new double[i1 - i0 + 1][j1 - j0 + 1];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          x[i - i0][j - j0] = this.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new DoubleMatrix(x);
  }

  /**
   * 行開始位置、終了位置を指定して特定の列のインデックスの部分行列を取り出す. Get a submatrix.
   *
   * @param i0
   *          Initial row index
   * @param i1
   *          Final row index
   * @param col
   *          Array of column indices.
   * @return A(i0:i1,c(:))
   */
  public DoubleMatrix getMatrix(final int i0, final int i1, final int[] col) {

    final double[][] x = new double[i1 - i0 + 1][col.length];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i - i0][j] = this.matrix[i][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new DoubleMatrix(x);
  }

  /**
   * 部分行列を取得する。
   *
   * @param row
   *          行インデックスの配列
   * @param j0
   *          先頭列番号
   * @param j1
   *          末端列番号
   * @return A(r(:),j0:j1)
   */
  public DoubleMatrix getMatrix(final int[] row, final int j0, final int j1) {

    final double[][] b = new double[row.length][j1 - j0 + 1];
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          b[i][j - j0] = this.matrix[row[i]][j];
          System.out.println("matrix====" + this.matrix[row[i]][j]);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new DoubleMatrix(b);
  }

  /**
   * 特定の座標インデックスを複数個指定して現在の行列から部分行列を取り出す
   *
   * @param row
   *          行インデックスの配列
   * @param col
   *          列インデックスの配列
   * @return A(r(:),c(:))
   */
  public DoubleMatrix getMatrix(final int[] row, final int[] col) {

    final double[][] x = new double[row.length][col.length];

    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i][j] = this.matrix[row[i]][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("部分行列のインデックス。");
    }
    return new DoubleMatrix(x);
  }

  /**
   * 行列の行数を取得する。
   *
   * @return 行、行数
   */
  public int getRowDimension() {
    return this.row;
  }

  /**
   * 無限大ノルムを返す.
   *
   * @since 1.1
   * @return 無限大ノルム
   */
  public double infinityNorm() {
    return this.transpose().abs().sum().max();
  }

  /**
   * 行列の個々の要素の対数.
   *
   * @since 1.1
   * @return 行列の個々の要素に対する対数
   */
  public DoubleMatrix log() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.log(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の常用対数.
   *
   * @since 1.1
   * @return 行列の個々の要素に対する常用対数
   */
  public DoubleMatrix log10() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.log10(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の各列の最大要素をベクトルで求める.
   *
   * @since 1.1
   * @return ベクトル
   */
  public DoubleVector max() {
    final double[] c = new double[this.col];
    double max;
    for (int j = 0; j < this.col; j++) {
      max = (-1) * Double.MAX_VALUE;
      for (int i = 0; i < this.row; i++) {
        max = Math.max(max, this.matrix[i][j]);
      }
      c[j] = max;
    }
    return new DoubleVector(c);
  }

  /**
   * 行列の列のみの各要素の平均(average)を行ベクトルで返す.
   *
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public DoubleVector mean() {
    final double[] c = new double[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = 0.0d;
      for (int i = 0; i < this.row; i++) {
        c[j] += this.matrix[i][j];
      }
      c[j] /= this.col;
    }
    return new DoubleVector(c);
  }

  /**
   * 行列の各列の最小要素をベクトルで求める.
   *
   * @since 1.1
   * @return ベクトル
   */
  public DoubleVector min() {
    final double[] c = new double[this.col];
    double min;
    for (int j = 0; j < this.col; j++) {
      min = Double.MAX_VALUE;
      for (int i = 0; i < this.row; i++) {
        min = Math.min(min, this.matrix[i][j]);
      }
      c[j] = min;
    }
    return new DoubleVector(c);
  }

  /**
   * Linear algebraic matrix multiplication, A * b.
   *
   * @param b
   *          another matrix
   * @return DoubleMatrix product, A * b
   */
  public DoubleMatrix multiply(final DoubleMatrix b) {
    if (b.row != this.col) {
      throw new IllegalArgumentException("DoubleMatrix inner dimensions must agree.");
    }

    final double[][] c = new double[this.row][b.col];
    final double[] bcolj = new double[this.col];
    for (int j = 0; j < b.col; j++) {
      for (int k = 0; k < this.col; k++) {
        bcolj[k] = b.matrix[k][j];
      }

      for (int i = 0; i < this.row; i++) {
        final double[] arowi = this.matrix[i];
        double s = 0;
        for (int k = 0; k < this.col; k++) {
          s += arowi[k] * bcolj[k];
        }
        c[i][j] = s;
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 単行マイナス。
   *
   * @return -this
   */
  public DoubleMatrix negate() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = -this.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 無限小ノルムを返す.
   *
   * @since 1.1
   * @return 無限小ノルム
   */
  public double negativeInfinityNorm() {
    return this.transpose().abs().sum().max();
  }

  // /**
  // * 2乗ノルムを返す.
  // * @since 1.1
  // * @return 2乗ノルム
  // */
  // public double norm(){
  // DoubleMatrix v = abs();
  // return Math.sqrt(v.multiply(v).sum());
  // }

  /**
   * 1乗ノルムを返す.
   *
   * @since 1.1
   * @return 1乗ノルム
   */
  public double oneNorm() {
    return this.abs().sum().max();
  }

  /**
   * 行列要素の累乗.
   *
   * @since 1.1
   * @param d
   *          指数
   * @return 行列の個々の要素をd乗した行列を返す.
   */
  public DoubleMatrix pow(final double d) {
    final double[][] c = new double[this.row][this.col];

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.pow(this.matrix[i][j], d);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列要素の累乗.
   *
   * @param d
   *          another matrix
   * @return 行列の個々の要素を行列dの要素で累乗した行列を返す.
   */
  public DoubleMatrix pow(final DoubleMatrix d) {
    this.checkMatrixDimensions(d);
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.pow(this.matrix[i][j], d.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の列のみの各要素の積(product)を行ベクトルで返す.
   *
   * @return 各列の要素の積の行ベクトル
   * @since 1.1
   */
  public DoubleVector product() {
    if (this.getRowDimension() < 2) {
      return new DoubleVector(this.matrix[0].clone());
    }

    final double[] c = new double[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = this.matrix[0][j];
      for (int i = 1; i < this.row; i++) {
        c[j] *= this.matrix[i][j];
      }
    }
    return new DoubleVector(c);
  }

  /**
   * 行列を反時計回りに90°回転.
   *
   * @return 行列
   * @since 1.1
   */
  public DoubleMatrix rot90() {
    final double[][] rotated = new double[this.col][this.row];
    for (int i = 0; i < this.col; i++) {
      for (int j = 0; j < this.row; j++) {
        rotated[i][j] = this.matrix[j][this.col - 1 - i];
      }
    }
    return new DoubleMatrix(rotated);
  }

  /**
   * 行列の指定した位置に単一の要素をセットする。
   *
   * @param i
   *          行インデックス
   * @param j
   *          列インデックス
   * @param s
   *          A(i,j).
   * @return matrix
   * @exception ArrayIndexOutOfBoundsException
   */
  public DoubleMatrix set(final int i, final int j, final double s) {

    final double[][] d = Arrays.copyOf(this.matrix, this.matrix.length);
    d[i][j] = s;
    return new DoubleMatrix(d);
  }

  /**
   * 部分行列をセットする。
   *
   * @param i0
   *          先頭の行インデックス
   * @param i1
   *          末端の行インデックス
   * @param j0
   *          先頭の列インデックス
   * @param j1
   *          末端の列インデックス
   * @param x
   *          A(i0:i1,j0:j1)
   * @return matrix
   */
  public DoubleMatrix setMatrix(final int i0, final int i1, final int j0, final int j1,
      final DoubleMatrix x) {
    final double[][] d = Arrays.copyOf(this.matrix, this.matrix.length);
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          d[i][j] = x.matrix[i - i0][j - j0];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("部分行列のインデックス。");
    }
    return new DoubleMatrix(d);
  }

  /**
   * 部分行列をセットする。
   *
   * @param i0
   *          先頭の列インデックス
   * @param i1
   *          末端の列インデックス
   * @param col
   *          列インデックスの配列
   * @param x
   *          A(i0:i1,c(:))
   * @return 行列
   */
  public DoubleMatrix setMatrix(final int i0, final int i1, final int[] col, final DoubleMatrix x) {

    final double[][] d = Arrays.copyOf(this.matrix, this.matrix.length);
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          d[i][col[j]] = x.matrix[i - i0][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new DoubleMatrix(d);
  }

  /**
   * 部分行列をセットする。
   *
   * @param row
   *          行インデックスの配列
   * @param j0
   *          先頭の列インデックス
   * @param j1
   *          末端の列インデックス
   * @param x
   *          A(r(:),j0:j1)
   * @return 行列
   */
  public DoubleMatrix setMatrix(final int[] row, final int j0, final int j1, final DoubleMatrix x) {

    final double[][] d = Arrays.copyOf(this.matrix, this.matrix.length);
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          d[row[i]][j] = x.matrix[i][j - j0];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new DoubleMatrix(d);
  }

  /**
   * 部分行列をセットする。
   *
   * @param row
   *          行インデックスの配列。
   * @param col
   *          列インデックスの配列。
   * @param x
   *          A(r(:),c(:))
   * @return 行列
   */
  public DoubleMatrix setMatrix(final int[] row, final int[] col, final DoubleMatrix x) {
    final double[][] d = Arrays.copyOf(this.matrix, this.matrix.length);
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          d[row[i]][col[j]] = x.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("部分行列のインデックス。");
    }
    return new DoubleMatrix(d);
  }

  /**
   * 行列の個々の要素の正弦(sine).
   *
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public DoubleMatrix sin() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.sin(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の要素の平方.
   *
   * @since 1.1
   * @return 行列の個々の要素を平方した行列
   */
  public DoubleMatrix sqrt() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.sqrt(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の減算 (C = a - b).
   *
   * @param b
   *          もうひとつの行列
   * @return this - b
   */
  public DoubleMatrix subtract(final DoubleMatrix b) {
    this.checkMatrixDimensions(b);
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j] - b.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の列のみの各要素の和を行ベクトルで返す.
   *
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public DoubleVector sum() {
    if (this.getRowDimension() < 2) {
      return new DoubleVector(this.matrix[0].clone());
    }

    final double[] c = new double[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = 0.0d;
      for (int i = 0; i < this.row; i++) {
        c[j] += this.matrix[i][j];
      }
    }
    return new DoubleVector(c);
  }

  /**
   * 行列の個々の要素の正接(tangent).
   *
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public DoubleMatrix tan() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.tan(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列のスカラー倍 ( c = s * a).
   *
   * @param s
   *          スカラー
   * @return s * a
   */
  public DoubleMatrix times(final double s) {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = s * this.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素どうしの積　( C = a .* b).
   *
   * @param b
   *          もうひとつの行列
   * @return a.*b
   */
  public DoubleMatrix times(final DoubleMatrix b) {
    this.checkMatrixDimensions(b);
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j] * b.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 転置行列を返す.
   *
   * @return a'
   */
  public DoubleMatrix transpose() {
    final double[][] c = new double[this.col][this.row];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[j][i] = this.matrix[i][j];
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の指定されたインデックスの一列を削除する. <br />
   * 列を削除すると行列の列数が1小さくなる.
   *
   * @param index
   *          列を削除する位置
   * @return 列を1つ削除された行列を返す
   * @since 1.1
   */
  public DoubleMatrix deleteColumn(final int index) {
    // final double[][] deletedMatrix = new double[this.row][this.col - 1];
    final double[][] deletedMatrix = new double[this.row][this.col - 1];

    if (index == this.col - 1) {
      for (int i = 0; i < this.row; i++) {
        deletedMatrix[i] = Arrays.copyOf(this.matrix[i], this.col - 1);
      }
      // for (int i = 0; i < deletedMatrix.length; i++) {
      // for (int j = 0; j < deletedMatrix[0].length; j++) {
      // deletedMatrix[i][j] = this.matrix[i][j];
      // }
      // }
    } else {
      for (int i = 0; i < this.row; i++) {
        System.arraycopy(this.matrix[i], 0, deletedMatrix[i], 0, index);
        System.arraycopy(this.matrix[i], index + 1, deletedMatrix[i], index, this.col - 1 - index);
      }

      // for (int h = 0; h < deletedMatrix.length; h++) {
      // for (int i = 0, j = 0; j < deletedMatrix[0].length; i++, j++) {
      // if (j == index) {
      // i++;
      // deletedMatrix[h][j] = this.matrix[h][i];
      // } else {
      // deletedMatrix[h][j] = this.matrix[h][i];
      // }
      // }
      // }
    }
    return new DoubleMatrix(deletedMatrix);
  }

  /**
   * 行列の指定されたインデックスの一行を削除する. 行を削除すると行列の行数が1小さくなる.
   *
   * @param index
   *          行を削除する位置
   * @return 行を1つ削除された行列を返す
   * @since 1.1
   */
  public DoubleMatrix deleteRow(final int index) {
    final double[][] deletedMatrix;

    if (index == this.row - 1) {
      deletedMatrix = Arrays.copyOf(this.matrix, this.row - 1);
      // for (int i = 0; i < deletedMatrix.length; i++) {
      // for (int j = 0; j < deletedMatrix[0].length; j++) {
      // deletedMatrix[i][j] = this.matrix[i][j];
      // }
      // }
    } else {
      deletedMatrix = new double[this.row - 1][this.col];
      System.arraycopy(this.matrix, 0, deletedMatrix, 0, index);
      System.arraycopy(this.matrix, index + 1, deletedMatrix, index, this.row - 1 - index);
      // deletedMatrix = new double[this.row - 1][this.col];
      // for (int i = 0, j = 0; j < deletedMatrix.length; i++, j++) {
      //
      // if (j == index) {
      // i++;
      // }
      //
      // for (int k = 0; k < deletedMatrix[0].length; k++) {
      // deletedMatrix[j][k] = this.matrix[i][k];
      // }
      // }
    }
    return new DoubleMatrix(deletedMatrix);
  }

  /**
   * 行列に指定された値を指定された列に挿入する. <br />
   * 列を挿入すると行列の列数が1大きくなる.
   *
   * @param element
   *          挿入するdouble[]型の列
   * @param index
   *          列を挿入する位置
   * @return 列を挿入された行列を返す
   * @since 1.1
   */
  public DoubleMatrix insertColumn(final double[] element, final int index) {
    final double[][] insertedMatrix = new double[this.row][this.col + 1];

    if (index == insertedMatrix[0].length) {

      for (int i = 0; i < insertedMatrix.length; i++) {
        for (int j = 0; j < insertedMatrix[0].length - 1; j++) {
          insertedMatrix[i][j] = this.matrix[i][j];
        }
        insertedMatrix[i][index] = element[i];
      }
    } else {
      for (int h = 0; h < insertedMatrix.length; h++) {
        for (int i = 0, j = 0; j < insertedMatrix[0].length; i++, j++) {
          if (j == index) {
            insertedMatrix[h][j] = element[i];
            if (j != insertedMatrix[0].length) {
              i--;
            }
          } else {
            insertedMatrix[h][j] = this.matrix[h][i];
          }
        }
      }
    }
    return new DoubleMatrix(insertedMatrix);
  }

  /**
   * 行列に指定された値を指定された列に挿入する. <br />
   * 列を挿入すると行列の列数が1大きくなる.
   *
   * @param element
   *          挿入するDoubleVector型の列
   * @param index
   *          列を挿入する位置
   * @return 列を挿入された行列を返す
   * @since 1.1
   */
  public DoubleMatrix insertColumn(final DoubleVector element, final int index) {
    return this.insertColumn(element.getArray().clone(), index);
  }

  /**
   * 行列に指定された値を指定された行に挿入する. <br />
   * 行を挿入すると行列の行数が1大きくなる.
   *
   * @param element
   *          挿入するdouble[]型の行
   * @param index
   *          行を挿入する位置
   * @return 行を挿入された行列を返す
   * @since 1.1
   */
  public DoubleMatrix insertRow(final double[] element, final int index) {
    final double[][] insertedMatrix = new double[this.row + 1][this.col];

    if (index == insertedMatrix.length) {
      System.arraycopy(this.matrix, 0, insertedMatrix, 0, this.row);
      // for (int i = 0; i < insertedMatrix.length - 1; i++) {
      // for (int j = 0; j < insertedMatrix[0].length; i++) {
      // insertedMatrix[i][j] = this.matrix[i][j];
      // }
      // }
      // for (int k = 0; k < insertedMatrix[0].length; k++) {
      // insertedMatrix[index][k] = element[k];
      // }
    } else {
      System.arraycopy(this.matrix, 0, insertedMatrix, 0, index + 1);
      System.arraycopy(this.matrix, index, insertedMatrix, index + 1, this.row - index);
      // for (int i = 0, j = 0; j < insertedMatrix.length; i++, j++) {
      // if (j == index) {
      // for (int k = 0; k < insertedMatrix[0].length; k++) {
      // insertedMatrix[j][k] = element[k];
      // }
      // if (j != insertedMatrix.length) {
      // i--;
      // }
      // } else {
      // for (int k = 0; k < insertedMatrix[0].length; k++) {
      // insertedMatrix[j][k] = this.matrix[i][k];
      // }
      // }
      // }
    }
    insertedMatrix[index] = Arrays.copyOf(element, element.length);
    return new DoubleMatrix(insertedMatrix);
  }

  /**
   * 行列に指定された値を指定された行に挿入する. <br />
   * 行を挿入すると行列の行数が1大きくなる.
   *
   * @param element
   *          挿入するDoubleVector型の行
   * @param index
   *          行を挿入する位置
   * @return 行を挿入された行列を返す
   * @since 1.1
   */
  public DoubleMatrix insertRow(final DoubleVector element, final int index) {
    return this.insertRow(element.getArray(), index);
  }

  /**
   * 行列の個々の要素の双曲線正弦(Hyperolic Sine).
   *
   * @return 行列の個々の要素に対する双曲線正弦
   * @since 2005/01/04
   */
  public DoubleMatrix sinh() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.sinh(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の双曲線余弦(Hyperolic Cosine).
   *
   * @return 行列の個々の要素に対する双曲線余弦
   * @since 2005/01/04
   */
  public DoubleMatrix cosh() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.cosh(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の双曲線正接(Hyperolic Tangent).
   *
   * @return 行列の個々の要素に対する双曲線正接
   * @since 2005/01/04
   */
  public DoubleMatrix tanh() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.tanh(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の逆双曲線正弦(Inverse Hyperolic Sine).
   *
   * @return 行列の個々の要素に対する逆双曲線正弦
   * @since 2005/01/04
   */
  public DoubleMatrix asinh() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = DoubleMath.asinh(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の逆双曲線余弦(Inverse Hyperolic Cosine).
   *
   * @return 行列の個々の要素に対する逆双曲線余弦
   * @since 2005/01/04
   */
  public DoubleMatrix acosh() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = DoubleMath.acosh(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * 行列の個々の要素の逆双曲線正接(Inverse Hyperolic Tangent).
   *
   * @return 行列の個々の要素に対する逆双曲線正接
   * @since 2005/01/04
   */
  public DoubleMatrix atanh() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = DoubleMath.atanh(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * このオブジェクトの文字列表現.
   *
   * @return 文字列表現
   * @see java.lang.Object#toString()
   * @since 2005/05/17
   */
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder();

    // Format nf = NumberFormat.getPercentInstance(Locale.JAPAN);
    // DecimalFormat df = (DecimalFormat)nf;
    // DecimalFormat df = new DecimalFormat();

    // df.applyPattern("
    // ############.00000000000000000E00;-############.00000000000000000E00");
    // df.applyPattern(" ###.000E00;-###.000E00");
    // df.applyPattern("############.00000000000000000E00;-############.00000000000000000E00");
    // df.applyPattern(" ###.000;-###.000");

    // FieldPosition fieldPosition = new
    // FieldPosition(NumberFormat.FRACTION_FIELD);

    // df.setMinimumFractionDigits(3);
    // df.setMinimumIntegerDigits(1);
    // df.setMaximumIntegerDigits(1);
    // df.setGroupingUsed(false);
    // df.setNegativePrefix("-");
    // df.setPositivePrefix(" ");

    for (int i = 0; i < this.matrix.length; i++) {
      for (int j = 0; j < this.matrix[i].length; j++) {
        // result.append(df.format(this.matrix[i][j])).append(" ");
        // result = df.format(this.matrix[i][j], result, fieldPosition);
        result.append(String.format(Locale.JAPAN, "%+3.3e", Double.valueOf(this.matrix[i][j])));
        result.append(" ");
      }

      result.append("\n");
    }
    return result.toString();
  }

  /**
   * 行列の個々の要素の立方根(3√)(cube root)を求める.
   *
   * @return 行列の個々の要素に対する立方根
   * @since 2005/01/04
   */
  public DoubleMatrix cbrt() {
    final double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = Math.cbrt(this.matrix[i][j]);
      }
    }
    return new DoubleMatrix(c);
  }

  /**
   * ふたつのオブジェクトが同値であればtrueを返す.
   *
   * @param other
   *          比較対象
   * @return 同値であればtrue
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof DoubleMatrix)) {
      return false;
    }
    final DoubleMatrix castOther = (DoubleMatrix) other;
    return new EqualsBuilder().append(this.matrix, castOther.matrix).isEquals();
  }

  /**
   * ハッシュコードを求める.
   *
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.matrix).toHashCode();
  }

}
