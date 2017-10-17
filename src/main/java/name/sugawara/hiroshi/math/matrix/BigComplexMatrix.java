/*
 * 作成日: 2003/09/23
 */
package name.sugawara.hiroshi.math.matrix;

import java.math.BigDecimal;
import java.util.Arrays;

import name.sugawara.hiroshi.math.complex.BigComplex;
import name.sugawara.hiroshi.math.precision.Precision;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BigDecimal型, BigComplex型対応複素数行列.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: BigComplexMatrix.java 109 2010-06-13 04:26:48Z sugawara $ Created Date : 2005/07/24
 *          19:40:30
 */

public final class BigComplexMatrix extends Matrix {

  /**
   * 行数.
   * 
   * @serial column dimention
   */
  private int            col;

  /**
   * 誤差,精度情報.
   */
  private Precision      precision;

  /**
   * row * col サイズの行列の要素を格納するための配列.
   * 
   * @serial internal array storage.
   * @uml.property name="matrix"
   * @uml.associationEnd multiplicity="(0 -1)"
   */
  private BigComplex[][] matrix;

  /**
   * 列数.
   * 
   * @serial row dimention
   */
  private int            row;

  /**
   * 1次元配列から、配列を列数で区切って行列を作る. Construct a matrix from a one-dimensional packed array.
   * 
   * @param vals
   *          One-dimensional array of doubles, packed by columns (ala Fortran).
   * @param row
   *          Number of rows.
   */
  public BigComplexMatrix(final BigComplex[] vals, final int row) {
    this.row = row;
    this.precision = vals[0].getPrecision();
    if (row != 0) {
      this.col = (vals.length / row);
    } else {
      this.col = 0;
    }
    if (row * this.col != vals.length) {
      throw new IllegalArgumentException("Array length must be a multiple of row.");
    }
    this.matrix = new BigComplex[row][this.col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.matrix[i][j] = vals[i + j * row];
      }
    }
  }

  /**
   * 既存の2次元配列から行列クラスを生成. Construct a matrix from a 2-D array.
   * 
   * @param matrix
   *          2次元配列. Two-dimensional array of doubles.
   */
  public BigComplexMatrix(final BigComplex[][] matrix) {
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
   * 要素がすべてs の m * n サイズの行列を作る. Construct an m-by-n matrix of s.
   * 
   * @param row
   *          行番号
   * @param col
   *          列番号
   * @param s
   *          BigComplex型の複素数
   */
  public BigComplexMatrix(final int row, final int col, final BigComplex s) {
    this.matrix = new BigComplex[row][col];
    this.row = row;
    this.col = col;
    for (int r = 0; r < row; r++) {
      Arrays.fill(this.matrix[r], s);
    }
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
  public BigComplexMatrix(final int row, final int col, final Precision precision) {
    this(row, col, new BigComplex(precision));
  }

  /**
   * 行列の個々の要素に対する絶対値を行列で返す.
   * 
   * @since 1.1
   * @return ベクトル要素に対する絶対値行列
   */
  public BigMatrix abs() {
    final BigDecimal[][] c = new BigDecimal[this.row][this.col];
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
  public BigComplexMatrix acos() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].acos();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * C = a + b.
   * 
   * @param b
   *          another matrix
   * @return a + b
   */
  public BigComplexMatrix add(final BigComplexMatrix b) {
    this.checkMatrixDimensions(b);
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].add(b.matrix[i][j]);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の個々の要素の逆正弦(arc sine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public BigComplexMatrix asin() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].asin();
      }
    }
    return new BigComplexMatrix(c);
  }

  // /**
  // * 行列の個々の要素の逆正接(arc tangent)
  // * @since 1.1
  // * @return 行列の個々の要素に対する逆正弦
  // */
  // public BigComplexMatrix atan() {
  // BigComplex[][] c = new BigComplex[row][col];
  // for (int i = 0; i < row; i++) {
  // for (int j = 0; j < col; j++) {
  // c[i][j] = this.matrix[i][j].atan();
  // }
  // }
  // return new BigComplexMatrix(c);
  // }

  // /**
  // * 行列の要素の4象限逆正接 Four Quadrant Arc Tangent.<br />
  // * 現在のオブジェクトを、ラジアンで表した、arctan関数でyを変数とする行列<br />
  // * とし指定された角度の4象限逆正接 (4象限アークタンジェント) を返す<br />
  // *
  // * @since 1.1
  // * @param x ラジアンで表した arctan関数でxを変数とする配列
  // * @return 指定された配列の個々の要素をarctan関数に代入した配列を返す。
  // */
  // public BigComplexMatrix atan2(BigComplexMatrix x) {
  // this.checkMatrixDimensions(x);
  // BigDecimal[][] c = new BigDecimal[row][col];
  // for (int i = 0; i < row; i++) {
  // for (int j = 0; j < col; j++) {
  // c[i][j] = this.matrix[i][j].atan2(x.matrix[i][j]);
  // }
  // }
  // return new BigComplexMatrix(c);
  // }

  /**
   * Check if size(a) == size(b).
   * 
   * @param b
   *          比較対照ベクトル
   */
  private void checkMatrixDimensions(final BigComplexMatrix b) {
    if (b.row != this.row || b.col != this.col) {
      throw new IllegalArgumentException("BigComplexMatrix dimensions must agree.");
    }
  }

  /**
   * オブジェクトのクローン. 行列の要素はdeep copyされる.
   * 
   * @since 1.1
   * @return 行列
   */
  @Override
  public Object clone() {
    try {
      super.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    }

    final BigComplex[][] c = new BigComplex[this.row][this.col];

    // c = (BigDecimal[][])matrix.clone();

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j];
      }
    }

    return new BigComplexMatrix(c);
  }

  /**
   * 行列の個々の要素の余弦(cosine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public BigComplexMatrix cos() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].cos();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の指定されたインデックスの一列を削除する. 列を削除すると行列の列数が1小さくなる.
   * 
   * @param index
   *          列を削除する位置
   * @return 列を1つ削除された行列を返す
   * @since 1.1
   */
  public BigComplexMatrix deleteColumn(final int index) {
    final BigComplex[][] deletedMatrix = new BigComplex[this.row][this.col - 1];

    if (index == deletedMatrix[0].length) {

      for (int i = 0; i < deletedMatrix.length; i++) {
        for (int j = 0; j < deletedMatrix[0].length; j++) {
          deletedMatrix[i][j] = this.matrix[i][j];
        }
      }
    } else {
      for (int h = 0; h < deletedMatrix.length; h++) {

        for (int i = 0, j = 0; j < deletedMatrix[0].length; i++, j++) {
          if (j == index) {
            i++;
            deletedMatrix[h][j] = this.matrix[h][i];
          } else {
            deletedMatrix[h][j] = this.matrix[h][i];
          }
        }
      }
    }
    return new BigComplexMatrix(deletedMatrix);
  }

  /**
   * 行列の指定されたインデックスの一行を削除する. 行を削除すると行列の行数が1小さくなる.
   * 
   * @param index
   *          行を削除する位置
   * @return 行を1つ削除された行列を返す
   * @since 1.1
   */
  public BigComplexMatrix deleteRow(final int index) {
    final BigComplex[][] deletedMatrix = new BigComplex[this.row - 1][this.col];

    if (index == deletedMatrix.length) {

      for (int i = 0; i < deletedMatrix.length; i++) {
        for (int j = 0; j < deletedMatrix[0].length; j++) {
          deletedMatrix[i][j] = this.matrix[i][j];
        }
      }
    } else {
      for (int i = 0, j = 0; j < deletedMatrix.length; i++, j++) {

        if (j == index) {
          i++;
        }

        for (int k = 0; k < deletedMatrix[0].length; k++) {
          deletedMatrix[j][k] = this.matrix[i][k];
        }
      }
    }
    return new BigComplexMatrix(deletedMatrix);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す。
   */
  public BigComplexMatrix divide(final BigComplex value) {
    final BigComplexMatrix rv = new BigComplexMatrix(this.row, this.col, value);
    return this.divide(rv);
  }

  /**
   * 配列どうしの除算 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param v
   *          除算する配列
   * @return 指定された配列aと指定された配列bを除算した配列を返す。
   */
  public BigComplexMatrix divide(final BigComplexMatrix v) {

    this.checkMatrixDimensions(v);
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < c.length; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].divide(v.matrix[i][j]);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 配列の要素のexp関数. exp(x).
   * 
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列を返す。
   */
  public BigComplexMatrix exp() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].exp();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の列を左右逆順に反転.
   * 
   * @return 列を左右逆順に反転した行列を返す
   * @since 1.1
   */
  public BigComplexMatrix fliplr() {
    final BigComplex[][] flipped = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.matrix[i][this.col - 1 - j];
      }
    }
    return new BigComplexMatrix(flipped);
  }

  /**
   * 行列の行を上下逆順に反転.
   * 
   * @return 行を上下逆順に反転した行列を返す
   * @since 1.1
   */
  public BigComplexMatrix flipud() {
    final BigComplex[][] flipped = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.matrix[this.row - 1 - i][j];
      }
    }
    return new BigComplexMatrix(flipped);
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
  public BigComplex get(final int i, final int j) {
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
  public BigComplex[][] getArray() {
    return ((BigComplexMatrix) this.clone()).matrix;
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
  public BigComplexMatrix getMatrix(final int i0, final int i1, final int j0, final int j1) {
    final BigComplex[][] x = new BigComplex[i1 - i0 + 1][j1 - j0 + 1];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          x[i - i0][j - j0] = this.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(x);
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
  public BigComplexMatrix getMatrix(final int i0, final int i1, final int[] col) {

    final BigComplex[][] x = new BigComplex[i1 - i0 + 1][col.length];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i - i0][j] = this.matrix[i][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(x);
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
  public BigComplexMatrix getMatrix(final int[] row, final int j0, final int j1) {

    final BigComplexMatrix x = new BigComplexMatrix(row.length, j1 - j0 + 1, this.precision);
    final BigComplex[][] b = x.getArray();
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
   * 特定の座標インデックスを複数個指定して現在の行列から部分行列を取り出す. Get a submatrix.
   * 
   * @param row
   *          Array of row indices.
   * @param col
   *          Array of column indices.
   * @return A(r(:),c(:))
   */
  public BigComplexMatrix getMatrix(final int[] row, final int[] col) {

    final BigComplex[][] x = new BigComplex[row.length][col.length];

    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i][j] = this.matrix[row[i]][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(x);
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
   * 行列の列数を取得する。 Get row dimension.
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
   * 行列に指定された値を指定された列に挿入する. 列を挿入すると行列の列数が1大きくなる.
   * 
   * @param element
   *          挿入するBigDecimal[]型の列
   * @param index
   *          列を挿入する位置
   * @return 列を挿入された行列を返す
   * @since 1.1
   */
  public BigComplexMatrix insertColumn(final BigComplex[] element, final int index) {
    final BigComplex[][] insertedMatrix = new BigComplex[this.row][this.col + 1];

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
    return new BigComplexMatrix(insertedMatrix);
  }

  /**
   * 行列に指定された値を指定された列に挿入する. 列を挿入すると行列の列数が1大きくなる.
   * 
   * @param element
   *          挿入するDoubleVector型の列
   * @param index
   *          列を挿入する位置
   * @return 列を挿入された行列を返す
   * @since 1.1
   */
  public BigComplexMatrix insertColumn(final BigComplexVector element, final int index) {
    return this.insertColumn(element.getArray(), index);
  }

  /**
   * 行列に指定された値を指定された行に挿入する. 行を挿入すると行列の行数が1大きくなる.
   * 
   * @param element
   *          挿入するBigDecimal[]型の行
   * @param index
   *          行を挿入する位置
   * @return 行を挿入された行列を返す
   * @since 1.1
   */
  public BigComplexMatrix insertRow(final BigComplex[] element, final int index) {
    final BigComplex[][] insertedMatrix = new BigComplex[this.row + 1][this.col];

    if (index == insertedMatrix.length) {

      for (int i = 0; i < insertedMatrix.length - 1; i++) {
        for (int j = 0; j < insertedMatrix[0].length; j++) {
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
            insertedMatrix[j][k] = this.matrix[i][k];
          }
        }
      }
    }
    return new BigComplexMatrix(insertedMatrix);
  }

  /**
   * 行列に指定された値を指定された行に挿入する. 行を挿入すると行列の行数が1大きくなる.
   * 
   * @param element
   *          挿入するDoubleVector型の行
   * @param index
   *          行を挿入する位置
   * @return 行を挿入された行列を返す
   * @since 1.1
   */
  public BigComplexMatrix insertRow(final BigComplexVector element, final int index) {
    return this.insertRow(element.getArray(), index);
  }

  /**
   * 行列の個々の要素の対数.
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する対数
   */
  public BigComplexMatrix log() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].log();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の各列の最大要素をベクトルで求める.
   * 
   * @since 1.1
   * @return ベクトル
   */
  public BigComplexVector max() {
    final BigComplex[] c = new BigComplex[this.col];
    BigComplex max;
    for (int j = 0; j < this.col; j++) {
      max = this.matrix[0][j];
      for (int i = 1; i < this.row; i++) {
        max = this.matrix[i][j].max(max);
      }
      c[j] = max;
    }
    return new BigComplexVector(c);
  }

  /**
   * 行列の列のみの各要素の平均(average)を行ベクトルで返す.
   * 
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public BigComplexVector mean() {
    final BigComplex[] c = new BigComplex[this.col];

    for (int j = 0; j < this.col; j++) {
      c[j] = new BigComplex(this.precision);
      for (int i = 0; i < this.row; i++) {
        c[j] = c[i].add(this.matrix[i][j]);
      }
      c[j] = c[j].divide(new BigComplex(new BigDecimal(this.col), new BigDecimal("0.0d"),
          this.precision));
    }
    return new BigComplexVector(c);
  }

  /**
   * 行列の各列の最小要素をベクトル求める.
   * 
   * @since 1.1
   * @return ベクトル
   */
  public BigComplexVector min() {
    final BigComplex[] c = new BigComplex[this.col];
    BigComplex min;
    for (int j = 0; j < this.col; j++) {
      min = this.matrix[j][0];
      for (int i = 1; i < this.row; i++) {
        min = min.min(this.matrix[i][j]);
      }
      c[j] = min;
    }
    return new BigComplexVector(c);
  }

  /**
   * Linear algebraic matrix multiplication, A * b.
   * 
   * @param b
   *          another matrix
   * @return BigComplexMatrix product, A * b
   */
  public BigComplexMatrix multiply(final BigComplexMatrix b) {
    if (b.row != this.col) {
      throw new IllegalArgumentException("BigComplexMatrix inner dimensions must agree.");
    }

    final BigComplex[][] c = new BigComplex[this.row][b.col];
    final BigComplex[] bcolj = new BigComplex[this.col];
    for (int j = 0; j < b.col; j++) {
      for (int k = 0; k < this.col; k++) {
        bcolj[k] = b.matrix[k][j];
      }
      BigComplex[] arowi;
      for (int i = 0; i < this.row; i++) {
        arowi = this.matrix[i];
        BigComplex s = new BigComplex(this.precision);
        for (int k = 0; k < this.col; k++) {
          s = s.add(arowi[k].multiply(bcolj[k]));
        }
        c[i][j] = s;
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * Unary minus.
   * 
   * @return -this
   */
  public BigComplexMatrix negate() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].negate();
      }
    }
    return new BigComplexMatrix(c);
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
  // * 2乗ノルムを返す。
  // * @since 1.1
  // * @return 2乗ノルム
  // */
  // public BigDecimal norm(){
  // BigComplexMatrix v = abs();
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
   * @return 行列の個々の要素をd乗した行列を返す。
   */
  public BigComplexMatrix pow(final BigComplex d) {
    final BigComplex[][] c = new BigComplex[this.row][this.col];

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].pow(d);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列要素の累乗.
   * 
   * @param d
   *          another matrix
   * @return 行列の個々の要素を行列dの要素で累乗した行列を返す。
   */
  public BigComplexMatrix pow(final BigComplexMatrix d) {
    this.checkMatrixDimensions(d);
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].pow(this.matrix[i][j]);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の列のみの各要素の積(product)を行ベクトルで返す.
   * 
   * @return 各列の要素の積の行ベクトル
   * @since 1.1
   */
  public BigComplexVector product() {
    if (this.getRowDimension() < 2) {
      return new BigComplexVector(this);
    }

    final BigComplex[] c = new BigComplex[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = this.matrix[0][j];
      for (int i = 1; i < this.row; i++) {
        c[j] = c[i].multiply(this.matrix[i][j]);
      }
    }
    return new BigComplexVector(c);
  }

  /**
   * 行列を反時計回りに90°回転.
   * 
   * @return 行列
   * @since 1.1
   */
  public BigComplexMatrix rot90() {
    final BigComplex[][] rotated = new BigComplex[this.col][this.row];
    for (int i = 0; i < this.col; i++) {
      for (int j = 0; j < this.row; j++) {
        rotated[i][j] = this.matrix[j][this.col - 1 - i];
      }
    }
    return new BigComplexMatrix(rotated);
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
   * @since 2004/08/03
   */
  public BigComplexMatrix set(final int i, final int j, final BigComplex s) {

    final BigComplex[][] d = ((BigComplexMatrix) this.clone()).getArray();
    d[i][j] = s;
    return new BigComplexMatrix(d);
  }

  /**
   * 部分行列をセットする。 Set a submatrix.
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
   * @since 2004/08/03
   */
  public BigComplexMatrix setMatrix(final int i0, final int i1, final int j0, final int j1,
      final BigComplexMatrix x) {
    final BigComplex[][] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          d[i][j] = x.matrix[i - i0][j - j0];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(d);
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
   */
  public BigComplexMatrix setMatrix(final int i0, final int i1, final int[] col,
      final BigComplexMatrix x) {

    final BigComplex[][] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          d[i][col[j]] = x.matrix[i - i0][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(d);
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
  public BigComplexMatrix setMatrix(final int[] row, final int j0, final int j1,
      final BigComplexMatrix x) {

    final BigComplex[][] d = this.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          d[row[i]][j] = x.matrix[i][j - j0];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(d);
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
  public BigComplexMatrix setMatrix(final int[] row, final int[] col, final BigComplexMatrix x) {
    final BigComplex[][] d = this.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          d[row[i]][col[j]] = x.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices");
    }
    return new BigComplexMatrix(d);
  }

  /**
   * 精度情報をセットした行列を新たに生成し、返す.
   * 
   * @param precision
   *          精度
   * @return 指定した制度を持つ行列
   * @uml.property name="precision"
   */
  public BigComplexMatrix setPrecision(final Precision precision) {
    final BigComplex[][] bc = this.getArray();
    final BigComplex[][] bc2 = new BigComplex[this.row][this.col];

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.row; j++) {
        bc2[i][j] = new BigComplex(bc[i][j].getReal(), bc[i][j].getImaginary(), precision);
      }
    }

    return new BigComplexMatrix(bc2);
  }

  /**
   * 行列の個々の要素の正弦(sine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public BigComplexMatrix sin() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].sin();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の要素の平方.
   * 
   * @since 1.1
   * @return 行列の個々の要素を平方した行列
   */
  public BigComplexMatrix sqrt() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].sqrt();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * C = a - b.
   * 
   * @param b
   *          another matrix
   * @return a + b
   */
  public BigComplexMatrix subtract(final BigComplexMatrix b) {
    this.checkMatrixDimensions(b);
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].subtract(b.matrix[i][j]);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 行列の列のみの各要素の和を行ベクトルで返す.
   * 
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public BigComplexVector sum() {
    if (this.getRowDimension() < 2) {
      return new BigComplexVector(this);
    }

    final BigComplex[] c = new BigComplex[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = new BigComplex(this.precision);
      for (int i = 0; i < this.row; i++) {
        c[j] = c[i].add(this.matrix[i][j]);
      }
    }
    return new BigComplexVector(c);
  }

  /**
   * 行列の個々の要素の正接(tangent).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public BigComplexMatrix tan() {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].tan();
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * Multiply a matrix by a scalar, c = s * a.
   * 
   * @param s
   *          scalar
   * @return s * a
   */
  public BigComplexMatrix times(final BigComplex s) {
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = s.multiply(this.matrix[i][j]);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * Element-by-element multiplication, C = a .* b.
   * 
   * @param b
   *          another matrix
   * @return a.*b
   */
  public BigComplexMatrix times(final BigComplexMatrix b) {
    this.checkMatrixDimensions(b);
    final BigComplex[][] c = new BigComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].multiply(b.matrix[i][j]);
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 転置行列を返す. BigComplexMatrix transpose.
   * 
   * @return a'
   */
  public BigComplexMatrix transpose() {
    final BigComplex[][] c = new BigComplex[this.col][this.row];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[j][i] = this.matrix[i][j];
      }
    }
    return new BigComplexMatrix(c);
  }

  /**
   * 現在のオブジェクトがotherと同値であるときtrueを返す. 行列だけでなく精度も同値であるときのみ、trueを返す.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return thisとotherが同値であるときtrueを返す
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof BigComplexMatrix)) {
      return false;
    }
    final BigComplexMatrix castOther = (BigComplexMatrix) other;
    return new EqualsBuilder().append(this.precision, castOther.precision).append(this.matrix,
        castOther.matrix).isEquals();
  }

  /**
   * 現在のオブジェクトのハッシュコードを返す.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.precision).append(this.matrix).toHashCode();
  }
}
