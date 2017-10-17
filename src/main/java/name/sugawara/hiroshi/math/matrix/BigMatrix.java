/*
 * 作成日: 2003/09/23
 */
package name.sugawara.hiroshi.math.matrix;

import java.math.BigDecimal;
import java.util.Arrays;

import name.sugawara.hiroshi.math.function.decimal.BigMath;
import name.sugawara.hiroshi.math.precision.Precision;
import name.sugawara.hiroshi.math.precision.RoundingError;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BigDecimal 型対応行列クラス.
 * 
 * @author sugawara
 * @since 1.1
 * @version $Id: BigMatrix.java 109 2010-06-13 04:26:48Z sugawara $
 */

public final class BigMatrix extends Matrix {

  /**
   * 誤差,精度情報.
   * 
   * @uml.property name="precision"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Precision      precision;

  /**
   * 行数.
   * 
   * @serial column dimention
   * @uml.property name="col"
   */
  private int            col;

  /**
   * 初頭関数演算用オブジェクト.
   * 
   * @uml.property name="math"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final BigMath  math;

  // = new BigMath(BigMatrix.DEFAULT_PRECISION);

  /**
   * row * col サイズの行列の要素を格納するための配列.
   * 
   * @serial internal array storage.
   * @uml.property name="matrix" multiplicity="(0 -1)" dimension="1"
   */
  private BigDecimal[][] matrix;

  /**
   * 列数.
   * 
   * @serial row dimention
   * @uml.property name="row"
   */
  private int            row;

  /**
   * 1次元配列から、配列を列数で区切って行列を作る.
   * 
   * @param vals
   *          列でパックされたdouble型の一次元配列
   * @param row
   *          Number of rows.
   * @param precision
   *          誤差
   */
  public BigMatrix(final BigDecimal[] vals, final int row, final Precision precision) {
    if (row * this.col != vals.length) {
      throw new IllegalArgumentException("Array length must be a multiple of row.");
    }
    this.row = row;
    this.precision = precision;
    if (row != 0) {
      this.col = vals.length / row;
    } else {
      this.col = 0;
    }

    this.matrix = new BigDecimal[row][this.col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.matrix[i][j] = vals[i + j * row];
      }
    }
    this.math = new BigMath(precision);
  }

  /**
   * 既存の2次元配列から行列クラスを生成. Construct a matrix from a 2-D array.
   * 
   * @param matrix
   *          2次元配列. Two-dimensional array of doubles.
   * @param precision
   *          誤差
   */
  public BigMatrix(final BigDecimal[][] matrix, final Precision precision) {
    this.row = matrix.length;
    this.col = matrix[0].length;
    this.precision = precision;
    for (int i = 0; i < this.row; i++) {
      if (matrix[i].length != this.col) {
        throw new IllegalArgumentException("All rows must have the same length.");
      }
    }
    this.matrix = matrix.clone();
    this.math = new BigMath(precision);
  }

  /**
   * 要素がすべてs の m * n サイズの行列を作る. Construct an m-by-n matrix of s.
   * 
   * @param row
   *          行数
   * @param col
   *          列数
   * @param s
   *          要素の値
   * @param precision
   *          誤差
   */
  public BigMatrix(final int row, final int col, final BigDecimal s, final Precision precision) {
    this.matrix = new BigDecimal[row][col];
    this.row = row;
    this.col = col;
    this.precision = precision;
    for (int r = 0; r < row; r++) {
      Arrays.fill(this.matrix[r], s);
    }
    this.math = new BigMath(precision);

  }

  /**
   * 要素がすべて0 の m * n サイズの行列を作る. Construct an m-by-n matrix of zeros.
   * 
   * @param row
   *          Number of rows.
   * @param col
   *          Number of colums.
   * @param precision
   *          誤差
   */
  public BigMatrix(final int row, final int col, final Precision precision) {
    this(row, col, BigDecimal.ZERO, precision);
  }

  /**
   * 行列の個々の要素に対する絶対値を行列で返す.
   * 
   * @since 1.1
   * @return ベクトル要素に対する絶対値行列
   */
  public BigMatrix abs() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].abs();
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の逆余弦(arc cosine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public BigMatrix acos() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.acos(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * C = a + b .
   * 
   * @param b
   *          another matrix
   * @return a + b
   */
  public BigMatrix add(final BigMatrix b) {
    this.checkMatrixDimensions(b);
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].add(b.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の逆正弦(arc sine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public BigMatrix asin() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.asin(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の逆正接(arc tangent).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public BigMatrix atan() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.atan(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の要素の4象限逆正接 Four Quadrant Arc Tangent. <br />
   * 現在のオブジェクトを、ラジアンで表した、arctan関数でyを変数とする行列 <br />
   * とし指定された角度の4象限逆正接 (4象限アークタンジェント) を返す <br />
   * 
   * @since 1.1
   * @param x
   *          ラジアンで表した arctan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarctan関数に代入した配列を返す.
   */
  public BigMatrix atan2(final BigMatrix x) {
    this.checkMatrixDimensions(x);
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.atan2(this.matrix[i][j], x.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * Check if size(a) == size(b).
   * 
   * @param b
   *          比較対照ベクトル
   */
  private void checkMatrixDimensions(final BigMatrix b) {
    if (b.row != this.row || b.col != this.col) {
      throw new IllegalArgumentException("BigMatrix dimensions must agree.");
    }
  }

  /**
   * オブジェクトのクローン. <br />
   * 行列の要素はdeep copyされる.
   * 
   * @since 1.1
   * @return 行列
   * @throws CloneNotSupportedException
   *           クローンをサポートしていないとき
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    super.clone();
    BigDecimal[][] c = new BigDecimal[this.row][this.col];

    // c = (BigDecimal[][])matrix.clone();

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j];
      }
    }

    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の余弦(cosine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public BigMatrix cos() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.cos(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
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
  public BigMatrix deleteColumn(final int index) {
    BigDecimal[][] deletedMatrix = new BigDecimal[this.row][this.col - 1];

    if (index == deletedMatrix[0].length) {

      for (int i = 0; i < deletedMatrix.length; i++) {
        for (int j = 0; j < deletedMatrix[0].length; i++) {
          deletedMatrix[i][j] = this.matrix[i][j];
        }
      }
    } else {
      for (int h = 0; h < deletedMatrix.length; h++) {

        for (int i = 0, j = 0; j < deletedMatrix[0].length; i++, j++) {
          if (j == index) {
            i++;
            deletedMatrix[h][j] = this.get(h, i);
          } else {
            deletedMatrix[h][j] = this.get(h, i);
          }
        }
      }
    }
    return new BigMatrix(deletedMatrix, this.precision);
  }

  /**
   * 行列の指定されたインデックスの一行を削除する. <br />
   * 行を削除すると行列の行数が1小さくなる.
   * 
   * @param index
   *          行を削除する位置
   * @return 行を1つ削除された行列を返す
   * @since 1.1
   */
  public BigMatrix deleteRow(final int index) {
    BigDecimal[][] deletedMatrix = new BigDecimal[this.row - 1][this.col];

    if (index == deletedMatrix.length) {

      for (int i = 0; i < deletedMatrix.length; i++) {
        for (int j = 0; j < deletedMatrix[0].length; i++) {
          deletedMatrix[i][j] = this.matrix[i][j];
        }
      }
    } else {
      for (int i = 0, j = 0; j < deletedMatrix.length; i++, j++) {

        if (j == index) {
          i++;
        }

        for (int k = 0; k < deletedMatrix[0].length; k++) {
          deletedMatrix[j][k] = this.get(i, k);
        }
      }
    }
    return new BigMatrix(deletedMatrix, this.precision);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public BigMatrix divide(final BigDecimal value) {
    BigMatrix rv = new BigMatrix(this.row, this.col, value, this.precision);
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
  public BigMatrix divide(final BigMatrix v) {

    this.checkMatrixDimensions(v);
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    int scale = ((RoundingError) this.precision).getScale();
    int mode = ((RoundingError) this.precision).getRoundingMode();
    for (int i = 0; i < c.length; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].divide(v.matrix[i][j], scale, mode);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 配列の要素のexp関数 exp(x).
   * 
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列を返す.
   */
  public BigMatrix exp() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.exp(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の列を左右逆順に反転.
   * 
   * @return 列を左右逆順に反転した行列を返す
   * @since 1.1
   */
  public BigMatrix fliplr() {
    BigDecimal[][] flipped = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.get(i, this.col - 1 - j);
      }
    }
    return new BigMatrix(flipped, this.precision);
  }

  /**
   * 行列の行を上下逆順に反転.
   * 
   * @return 行を上下逆順に反転した行列を返す
   * @since 1.1
   */
  public BigMatrix flipud() {
    BigDecimal[][] flipped = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.get(this.row - 1 - i, j);
      }
    }
    return new BigMatrix(flipped, this.precision);
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
  public BigDecimal get(final int i, final int j) {
    if (i > this.getRowDimension() || j > this.getColumnDimension()) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return this.matrix[i][j];
    }
  }

  /**
   * 行列をBigDecimal型2次元配列に変換する. Access the internal two-dimensional array.
   * 
   * @return Pointer to the two-dimensional array of matrix elements.
   */
  public BigDecimal[][] getArray() {
    BigDecimal[][] result = null;
    try {
      result = ((BigMatrix) this.clone()).matrix;
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
    return result;

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
  public BigMatrix getMatrix(final int i0, final int i1, final int j0, final int j1) {
    BigDecimal[][] x = new BigDecimal[i1 - i0 + 1][j1 - j0 + 1];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          x[i - i0][j - j0] = this.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigMatrix(x, this.precision);
  }

  /**
   * 行開始位置、終了位置を指定して特定の列の インデックスの部分行列を取り出す. Get a submatrix.
   * 
   * @param i0
   *          Initial row index
   * @param i1
   *          Final row index
   * @param col
   *          Array of column indices.
   * @return A(i0:i1,c(:))
   */
  public BigMatrix getMatrix(final int i0, final int i1, final int[] col) {

    BigDecimal[][] x = new BigDecimal[i1 - i0 + 1][col.length];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i - i0][j] = this.matrix[i][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigMatrix(x, this.precision);
  }

  /**
   * Get a submatrix.
   * 
   * @param row
   *          Array of row indices.
   * @param j0
   *          Initial column index
   * @param j1
   *          Final column index
   * @return A(r(:),j0:j1)
   */
  public BigMatrix getMatrix(final int[] row, final int j0, final int j1) {

    BigMatrix x = new BigMatrix(row.length, j1 - j0 + 1, this.precision);
    BigDecimal[][] b = x.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          b[i][j - j0] = this.matrix[row[i]][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return x;
  }

  /**
   * 特定の座標インデックスを複数個指定して 現在の行列から部分行列を取り出す. Get a submatrix.
   * 
   * @param row
   *          Array of row indices.
   * @param col
   *          Array of column indices.
   * @return A(r(:),c(:))
   */
  public BigMatrix getMatrix(final int[] row, final int[] col) {

    BigDecimal[][] x = new BigDecimal[row.length][col.length];

    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i][j] = this.matrix[row[i]][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigMatrix(x, this.precision);
  }

  /**
   * 精度情報を取得する.
   * 
   * @return 精度
   * @uml.property name="precision"
   */
  public Precision getPrecision() {
    return this.precision;
  }

  /**
   * 行列の列数を取得する. Get row dimension.
   * 
   * @return row, the number of rows.
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
  public BigDecimal infinityNorm() {
    return this.transpose().abs().sum().max();
  }

  /**
   * 行列に指定された値を指定された列に挿入する. <br />
   * 列を挿入すると行列の列数が1大きくなる.
   * 
   * @param element
   *          挿入するBigDecimal[]型の列
   * @param index
   *          列を挿入する位置
   * @return 列を挿入された行列を返す
   * @since 1.1
   */
  public BigMatrix insertColumn(final BigDecimal[] element, final int index) {
    BigDecimal[][] insertedMatrix = new BigDecimal[this.row][this.col + 1];

    if (index == insertedMatrix[0].length) {

      for (int i = 0; i < insertedMatrix.length; i++) {
        for (int j = 0; j < insertedMatrix[0].length - 1; i++) {
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
            insertedMatrix[h][j] = this.get(h, i);
          }
        }
      }
    }
    return new BigMatrix(insertedMatrix, this.precision);
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
  public BigMatrix insertColumn(final BigVector element, final int index) {
    return this.insertColumn(element.getArray(), index);
  }

  /**
   * 行列に指定された値を指定された行に挿入する. <br />
   * 行を挿入すると行列の行数が1大きくなる.
   * 
   * @param element
   *          挿入するBigDecimal[]型の行
   * @param index
   *          行を挿入する位置
   * @return 行を挿入された行列を返す
   * @since 1.1
   */
  public BigMatrix insertRow(final BigDecimal[] element, final int index) {
    BigDecimal[][] insertedMatrix = new BigDecimal[this.row + 1][this.col];

    if (index == insertedMatrix.length) {

      for (int i = 0; i < insertedMatrix.length - 1; i++) {
        for (int j = 0; j < insertedMatrix[0].length; i++) {
          insertedMatrix[i][j] = this.matrix[i][j];
        }
      }

      for (int k = 0; k < insertedMatrix[0].length; k++) {

        insertedMatrix[index][k] = element[k];
      }

    } else {

      for (int i = 0, j = 0; j < insertedMatrix.length; i++, j++) {

        if (j == index) {
          for (int k = 0; k < insertedMatrix[0].length; k++) {
            insertedMatrix[j][k] = element[k];
          }
          if (j != insertedMatrix.length) {
            i--;
          }
        } else {
          for (int k = 0; k < insertedMatrix[0].length; k++) {
            insertedMatrix[j][k] = this.get(i, k);
          }
        }
      }
    }
    return new BigMatrix(insertedMatrix, this.precision);
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
  public BigMatrix insertRow(final BigVector element, final int index) {
    return this.insertRow(element.getArray(), index);
  }

  /**
   * 行列の個々の要素の対数.
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する対数
   */
  public BigMatrix log() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.log(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の各列の最大要素をベクトルで求める.
   * 
   * @since 1.1
   * @return ベクトル
   */
  public BigVector max() {
    BigDecimal[] c = new BigDecimal[this.col];
    BigDecimal max;
    for (int j = 0; j < this.col; j++) {
      max = this.get(0, j);
      for (int i = 1; i < this.row; i++) {
        max = this.matrix[i][j].max(max);
      }
      c[j] = max;
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 行列の列のみの各要素の平均(average)を行ベクトルで返す.
   * 
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public BigVector mean() {
    BigDecimal[] c = new BigDecimal[this.col];
    int scale = ((RoundingError) this.precision).getScale();
    int mode = ((RoundingError) this.precision).getRoundingMode();

    for (int j = 0; j < this.col; j++) {
      c[j] = new BigDecimal("0.0d");
      for (int i = 0; i < this.row; i++) {
        c[j] = c[i].add(this.matrix[i][j]);
      }
      c[j] = c[j].divide(new BigDecimal(this.col), scale, mode);
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 行列の各列の最小要素をベクトル求める.
   * 
   * @since 1.1
   * @return ベクトル
   */
  public BigVector min() {
    BigDecimal[] c = new BigDecimal[this.col];
    BigDecimal min;
    for (int j = 0; j < this.col; j++) {
      min = this.get(j, 0);
      for (int i = 1; i < this.row; i++) {
        min = min.min(this.matrix[i][j]);
      }
      c[j] = min;
    }
    return new BigVector(c, this.precision);
  }

  /**
   * Linear algebraic matrix multiplication, A * b.
   * 
   * @param b
   *          another matrix
   * @return BigMatrix product, A * b
   */
  public BigMatrix multiply(final BigMatrix b) {
    if (b.row != this.col) {
      throw new IllegalArgumentException("BigMatrix inner dimensions must agree.");
    }

    BigDecimal[][] c = new BigDecimal[this.row][b.col];
    BigDecimal[] bcolj = new BigDecimal[this.col];
    for (int j = 0; j < b.col; j++) {
      for (int k = 0; k < this.col; k++) {
        bcolj[k] = b.matrix[k][j];
      }

      for (int i = 0; i < this.row; i++) {
        BigDecimal[] arowi = this.matrix[i];
        BigDecimal s = new BigDecimal("0.0d");
        for (int k = 0; k < this.col; k++) {
          s = s.add(arowi[k].multiply(bcolj[k]));
        }
        c[i][j] = s;
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * Unary minus.
   * 
   * @return -this
   */

  public BigMatrix negate() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].negate();
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 無限小ノルムを返す.
   * 
   * @since 1.1
   * @return 無限小ノルム
   */
  public BigDecimal negativeInfinityNorm() {
    return this.transpose().abs().sum().max();
  }

  // /**
  // * 2乗ノルムを返す.
  // * @since 1.1
  // * @return 2乗ノルム
  // */
  // public BigDecimal norm(){
  // BigMatrix v = abs();
  // return StrictMath.sqrt(v.multiply(v).sum());
  // }

  /**
   * 1乗ノルムを返す.
   * 
   * @since 1.1
   * @return 1乗ノルム
   */
  public BigDecimal oneNorm() {
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
  public BigMatrix pow(final BigDecimal d) {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.pow(this.matrix[i][j], d);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列要素の累乗.
   * 
   * @param d
   *          another matrix
   * @return 行列の個々の要素を行列dの要素で累乗した行列を返す.
   */
  public BigMatrix pow(final BigMatrix d) {
    this.checkMatrixDimensions(d);
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.pow(this.matrix[i][j], d.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の列のみの各要素の積(product)を行ベクトルで返す.
   * 
   * @return 各列の要素の積の行ベクトル
   * @since 1.1
   */
  public BigVector product() {
    if (this.getRowDimension() < 2) {
      return new BigVector(this);
    }

    BigDecimal[] c = new BigDecimal[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = this.get(0, j);
      for (int i = 1; i < this.row; i++) {
        c[j] = c[i].multiply(this.matrix[i][j]);
      }
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 行列を反時計回りに90°回転.
   * 
   * @return 行列
   * @since 1.1
   */
  public BigMatrix rot90() {
    BigDecimal[][] rotated = new BigDecimal[this.col][this.row];
    for (int i = 0; i < this.col; i++) {
      for (int j = 0; j < this.row; j++) {
        rotated[i][j] = this.get(j, this.col - 1 - i);
      }
    }
    return new BigMatrix(rotated, this.precision);
  }

  /**
   * 行列の指定した位置に要素をセットする. Set a single element.
   * 
   * @param i
   *          Row index.
   * @param j
   *          Column index.
   * @param s
   *          A(i,j).
   * @return matrix
   * @exception ArrayIndexOutOfBoundsException
   */
  public BigMatrix set(final int i, final int j, final BigDecimal s) {

    BigDecimal[][] d = null;

    try {
      d = ((BigMatrix) this.clone()).getArray();
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
    d[i][j] = s;
    return new BigMatrix(d, this.precision);
  }

  /**
   * 部分行列をセットする. Set a submatrix.
   * 
   * @param i0
   *          Initial row index
   * @param i1
   *          Final row index
   * @param j0
   *          Initial column index
   * @param j1
   *          Final column index
   * @param x
   *          A(i0:i1,j0:j1)
   * @return matrix
   */
  public BigMatrix setMatrix(final int i0, final int i1, final int j0, final int j1,
      final BigMatrix x) {
    BigDecimal[][] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          d[i][j] = x.get(i - i0, j - j0);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new BigMatrix(d, this.precision);
  }

  /**
   * Set a submatrix.
   * 
   * @param i0
   *          Initial row index
   * @param i1
   *          inal row index
   * @param col
   *          Array of column indices.
   * @param x
   *          A(i0:i1,c(:))
   * @return matrix
   * @since 2004/08/03
   */
  public BigMatrix setMatrix(final int i0, final int i1, final int[] col, final BigMatrix x) {

    BigDecimal[][] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          d[i][col[j]] = x.get(i - i0, j);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new BigMatrix(d, this.precision);
  }

  /**
   * Set a submatrix.
   * 
   * @param row
   *          Array of row indices.
   * @param j0
   *          Initial column index
   * @param j1
   *          Final column index
   * @param x
   *          A(r(:),j0:j1)
   * @return matrix
   */
  public BigMatrix setMatrix(final int[] row, final int j0, final int j1, final BigMatrix x) {

    BigDecimal[][] d = this.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          d[row[i]][j] = x.get(i, j - j0);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new BigMatrix(d, this.precision);
  }

  /**
   * Set a submatrix.
   * 
   * @param row
   *          Array of row indices.
   * @param col
   *          Array of column indices.
   * @param x
   *          A(r(:),c(:))
   * @return matrix
   */
  public BigMatrix setMatrix(final int[] row, final int[] col, final BigMatrix x) {
    BigDecimal[][] d = this.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          d[row[i]][col[j]] = x.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new BigMatrix(d, this.precision);
  }

  /**
   * 精度情報をセットした行列を新たに生成し、返す.
   * 
   * @param precision
   *          精度
   * @return 指定した制度を持つ行列
   * @uml.property name="precision"
   */
  public BigMatrix setPrecision(final Precision precision) {
    return new BigMatrix(this.getArray(), precision);
  }

  /**
   * 行列の個々の要素の正弦(sine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public BigMatrix sin() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.sin(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の要素の平方.
   * 
   * @since 1.1
   * @return 行列の個々の要素を平方した行列
   */
  public BigMatrix sqrt() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.sqrt(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * C = a - b.
   * 
   * @param b
   *          another matrix
   * @return a + b
   */
  public BigMatrix subtract(final BigMatrix b) {
    this.checkMatrixDimensions(b);
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].subtract(b.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の列のみの各要素の和を行ベクトルで返す.
   * 
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public BigVector sum() {
    if (this.getRowDimension() < 2) {
      return new BigVector(this);
    }

    BigDecimal[] c = new BigDecimal[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = new BigDecimal("0.0d");
      for (int i = 0; i < this.row; i++) {
        c[j] = c[i].add(this.matrix[i][j]);
      }
    }
    return new BigVector(c, this.precision);
  }

  /**
   * 行列の個々の要素の正接(tangent).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public BigMatrix tan() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.tan(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * Multiply a matrix by a scalar, c = s * a.
   * 
   * @param s
   *          scalar
   * @return s * a
   */
  public BigMatrix times(final BigDecimal s) {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = s.multiply(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * Element-by-element multiplication, C = a .* b.
   * 
   * @param b
   *          another matrix
   * @return a.*b
   */
  public BigMatrix times(final BigMatrix b) {
    this.checkMatrixDimensions(b);
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].multiply(b.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 転置行列を返す. <br />
   * BigMatrix transpose.
   * 
   * @return a'
   */
  public BigMatrix transpose() {
    BigDecimal[][] c = new BigDecimal[this.col][this.row];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[j][i] = this.matrix[i][j];
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の双曲線正弦(Hyperbolic Sine).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する双曲線正弦
   */
  public BigMatrix sinh() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.sinh(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の双曲線余弦(Hyperbolic Cosine).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する双曲線余弦
   */
  public BigMatrix cosh() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.cosh(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の双曲線正接(Hyperbolic Tangent).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する双曲線正接
   */
  public BigMatrix tanh() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.tanh(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の逆双曲線正弦(Inverse Hyperbolic Sine).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する逆双曲線正弦
   */
  public BigMatrix asinh() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.asinh(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の逆双曲線余弦(Inverse Hyperbolic Cosine).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する逆双曲線余弦
   */
  public BigMatrix acosh() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.acosh(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の個々の要素の逆双曲線正接(Inverse Hyperbolic Tangent).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する逆双曲線正接
   */
  public BigMatrix atanh() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.atanh(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 行列の要素の立方根.
   * 
   * @since 1.1
   * @return 行列の個々の要素を立方した行列
   */
  public BigMatrix cbrt() {
    BigDecimal[][] c = new BigDecimal[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.math.cbrt(this.matrix[i][j]);
      }
    }
    return new BigMatrix(c, this.precision);
  }

  /**
   * 現在のオブジェクトとotherが同一であればtrueを返す. 行列だけでなく精度も同一であるときのみtrueを返す.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return thisとotherが同値であるときtrueを返す.
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof BigMatrix)) {
      return false;
    }
    BigMatrix castOther = (BigMatrix) other;
    return new EqualsBuilder().append(this.precision, castOther.precision).append(this.matrix,
        castOther.matrix).isEquals();
  }

  /**
   * オブジェクトのハッシュコードを返す.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.precision).append(this.matrix).toHashCode();
  }

}
