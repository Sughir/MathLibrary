/*
 * 作成日: 2003/09/23
 */
package name.sugawara.hiroshi.math.matrix;

import java.util.Arrays;

import name.sugawara.hiroshi.math.complex.DoubleComplex;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 複素数行列(Complex型行列).
 * 
 * @author sugawara
 * @version $Id: ComplexMatrix.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class ComplexMatrix extends Matrix implements Cloneable {

  /**
   * 行数.
   * 
   * @serial column dimention
   * @uml.property name="col"
   */
  private int               col;

  /**
   * row * col サイズの行列の要素を格納するための配列.
   * 
   * @serial internal array storage.
   * @uml.property name="matrix"
   * @uml.associationEnd multiplicity="(0 -1)"
   */
  private DoubleComplex[][] matrix;

  /**
   * 列数.
   * 
   * @serial row dimention
   * @uml.property name="row"
   */
  private int               row;

  /**
   * 1次元配列から、配列を列数で区切って行列を作る. Construct a matrix from a one-dimensional packed array
   * 
   * @param vals
   *          One-dimensional array of doubles, packed by columns (ala Fortran).
   * @param row
   *          Number of rows.
   */
  public ComplexMatrix(final DoubleComplex[] vals, final int row) {
    if (row * this.col != vals.length) {
      throw new IllegalArgumentException("Array length must be a multiple of row.");
    }
    this.row = row;
    if (row != 0) {
      this.col = vals.length / row;
    } else {
      this.col = 0;
    }
    this.matrix = new DoubleComplex[row][this.col];
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
  public ComplexMatrix(final DoubleComplex[][] matrix) {
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
  public ComplexMatrix(final int row, final int col) {
    this(row, col, new DoubleComplex());
  }

  /**
   * 要素がすべてs の m * n サイズの行列を作る. Construct an m-by-n matrix of s.
   * 
   * @param row
   *          行数
   * @param col
   *          列数
   * @param s
   *          要素値
   */
  public ComplexMatrix(final int row, final int col, final DoubleComplex s) {
    this.matrix = new DoubleComplex[row][col];
    this.row = row;
    this.col = col;
    for (int r = 0; r < row; r++) {
      Arrays.fill(this.matrix[r], s);
    }
  }

  /**
   * 二つのDoubleMatrix型オブジェクトを実部、虚部として複素数行列を作成する.
   * 
   * @param real
   *          実部
   * @param imaginary
   *          虚部
   */
  public ComplexMatrix(final DoubleMatrix real, final DoubleMatrix imaginary) {
    this.matrix = new DoubleComplex[real.getRowDimension()][real.getColumnDimension()];

    for (int i = 0; i < real.getRowDimension(); i++) {

      for (int j = 0; j < real.getColumnDimension(); j++) {
        this.matrix[i][j] = new DoubleComplex(real.get(i, j), imaginary.get(i, j));
      }
    }
  }

  /**
   * 二つのDoubleMatrix型オブジェクトを実部、虚部として複素数行列を作成する.
   * 
   * @param real
   *          実部
   * @param imaginary
   *          虚部
   */
  public ComplexMatrix(final double[][] real, final double[][] imaginary) {
    this(new DoubleMatrix(real), new DoubleMatrix(imaginary));
  }

  /**
   * 行列の個々の要素に対する絶対値を行列で返す.
   * 
   * @since 1.1
   * @return ベクトル要素に対する絶対値行列
   */
  public DoubleMatrix abs() {
    double[][] c = new double[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).abs();
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
  public ComplexMatrix acos() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).acos();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * C = a + b.
   * 
   * @param b
   *          another matrix
   * @return a + b
   */
  public ComplexMatrix add(final ComplexMatrix b) {
    this.checkMatrixDimensions(b);
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].add(b.matrix[i][j]);
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の個々の要素の逆正弦(arc sine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する逆正弦
   */
  public ComplexMatrix asin() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).asin();
      }
    }
    return new ComplexMatrix(c);
  }

  // /**
  // * 行列の個々の要素の逆正接(arc tangent)
  // * @since 1.1
  // * @return 行列の個々の要素に対する逆正弦
  // */
  // public ComplexMatrix atan(){
  // DoubleComplex[][] c = new DoubleComplex[row][col];
  // for( int i = 0 ; i < row ; i++ ){
  // for( int j = 0 ; j < col ; j++ ){
  // c[i][j] = get(i, j).atan();
  // }
  // }
  // return new ComplexMatrix(c);
  // }

  // /**
  // * 行列の要素の4象限逆正接 Four Quadrant Arc Tangent
  // * 現在のオブジェクトを、ラジアンで表した、arctan関数でyを変数とする行列
  // * とし指定された角度の4象限逆正接 (4象限アークタンジェント) を返す
  // *
  // * @since 1.1
  // * @param x ラジアンで表した arctan関数でxを変数とする配列
  // * @return 指定された配列の個々の要素をarctan関数に代入した配列を返す.
  // */
  // public DoubleMatrix atan2( ComplexMatrix x ){
  // checkMatrixDimensions(x);
  // double[][] c = new double[row][col];
  // for( int i = 0 ; i < row ; i++ ){
  // for( int j = 0 ; j < col ; j++ ){
  // c[i][j] = get(i, j).atan2(x.get(i, j) );
  // }
  // }
  // return new DoubleMatrix(c);
  // }

  /**
   * Check if size(a) == size(b).
   * 
   * @param b
   *          matrix
   * @since 2004/08/03
   */
  private void checkMatrixDimensions(final ComplexMatrix b) {
    if (b.row != this.row || b.col != this.col) {
      throw new IllegalArgumentException("ComplexMatrix dimensions must agree.");
    }
  }

  /**
   * オブジェクトのクローン. <br />
   * 行列の要素はdeep copyされる.
   * 
   * @throws CloneNotSupportedException
   *           クローンがサポートされていないとき
   * @since 1.1
   * @return 行列
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    super.clone();
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];

    // c = (DoubleComplex[][])matrix.clone();

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j];
      }
    }

    return new ComplexMatrix(c);
  }

  /**
   * 行列の個々の要素の余弦(cosine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public ComplexMatrix cos() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).cos();
      }
    }
    return new ComplexMatrix(c);
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
  public ComplexMatrix deleteColumn(final int index) {
    DoubleComplex[][] deletedMatrix = new DoubleComplex[this.row][this.col - 1];

    if (index == deletedMatrix[0].length) {

      for (int i = 0; i < deletedMatrix.length; i++) {
        for (int j = 0; j < deletedMatrix[0].length; i++) {
          deletedMatrix[i][j] = this.get(i, j);
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
    return new ComplexMatrix(deletedMatrix);
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
  public ComplexMatrix deleteRow(final int index) {
    DoubleComplex[][] deletedMatrix = new DoubleComplex[this.row - 1][this.col];

    if (index == deletedMatrix.length) {

      for (int i = 0; i < deletedMatrix.length; i++) {
        for (int j = 0; j < deletedMatrix[0].length; i++) {
          deletedMatrix[i][j] = this.get(i, j);
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
    return new ComplexMatrix(deletedMatrix);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す.
   */
  public ComplexMatrix divide(final DoubleComplex value) {
    ComplexMatrix rv = new ComplexMatrix(this.row, this.col, value);
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
  public ComplexMatrix divide(final ComplexMatrix v) {

    this.checkMatrixDimensions(v);
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < c.length; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).divide(v.get(i, j));
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 配列の要素のexp関数 exp(x).
   * 
   * @since 1.1
   * @return 指定された配列の個々の要素をexp関数に代入した配列を返す.
   */
  public ComplexMatrix exp() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).exp();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の列を左右逆順に反転.
   * 
   * @return 列を左右逆順に反転した行列を返す
   * @since 1.1
   */
  public ComplexMatrix fliplr() {
    DoubleComplex[][] flipped = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.get(i, this.col - 1 - j);
      }
    }
    return new ComplexMatrix(flipped);
  }

  /**
   * 行列の行を上下逆順に反転.
   * 
   * @return 行を上下逆順に反転した行列を返す
   * @since 1.1
   */
  public ComplexMatrix flipud() {
    DoubleComplex[][] flipped = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        flipped[i][j] = this.get(this.row - 1 - i, j);
      }
    }
    return new ComplexMatrix(flipped);
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
  public DoubleComplex get(final int i, final int j) {
    if (i > this.getRowDimension() || j > this.getColumnDimension()) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return this.matrix[i][j];
    }
  }

  /**
   * 行列をComplex型2次元配列に変換する. Access the internal two-dimensional array.
   * 
   * @return Pointer to the two-dimensional array of matrix elements.
   */
  public DoubleComplex[][] getArray() {
    DoubleComplex[][] result = null;
    try {
      result = ((ComplexMatrix) this.clone()).matrix;
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
  public ComplexMatrix getMatrix(final int i0, final int i1, final int j0, final int j1) {
    DoubleComplex[][] x = new DoubleComplex[i1 - i0 + 1][j1 - j0 + 1];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          x[i - i0][j - j0] = this.matrix[i][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(x);
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
  public ComplexMatrix getMatrix(final int i0, final int i1, final int[] col) {

    DoubleComplex[][] x = new DoubleComplex[i1 - i0 + 1][col.length];

    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i - i0][j] = this.matrix[i][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(x);
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
  public ComplexMatrix getMatrix(final int[] row, final int j0, final int j1) {

    ComplexMatrix x = new ComplexMatrix(row.length, j1 - j0 + 1);
    DoubleComplex[][] b = x.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          b[i][j - j0] = this.matrix[row[i]][j];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
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
  public ComplexMatrix getMatrix(final int[] row, final int[] col) {

    DoubleComplex[][] x = new DoubleComplex[row.length][col.length];

    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          x[i][j] = this.matrix[row[i]][col[j]];
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(x);
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
  public double infinityNorm() {
    return this.transpose().abs().sum().max();
  }

  /**
   * 行列に指定された値を指定された列に挿入する. <br />
   * 列を挿入すると行列の列数が1大きくなる.
   * 
   * @param element
   *          挿入するComplex[]型の列
   * @param index
   *          列を挿入する位置
   * @return 列を挿入された行列を返す
   * @since 1.1
   */
  public ComplexMatrix insertColumn(final DoubleComplex[] element, final int index) {
    DoubleComplex[][] insertedMatrix = new DoubleComplex[this.row][this.col + 1];

    if (index == insertedMatrix[0].length) {

      for (int i = 0; i < insertedMatrix.length; i++) {
        for (int j = 0; j < insertedMatrix[0].length - 1; i++) {
          insertedMatrix[i][j] = this.get(i, j);
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
    return new ComplexMatrix(insertedMatrix);
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
  public ComplexMatrix insertColumn(final ComplexVector element, final int index) {
    return this.insertColumn(element.getArray(), index);
  }

  /**
   * 行列に指定された値を指定された行に挿入する. <br />
   * 行を挿入すると行列の行数が1大きくなる.
   * 
   * @param element
   *          挿入するComplex[]型の行
   * @param index
   *          行を挿入する位置
   * @return 行を挿入された行列を返す
   * @since 1.1
   */
  public ComplexMatrix insertRow(final DoubleComplex[] element, final int index) {
    DoubleComplex[][] insertedMatrix = new DoubleComplex[this.row + 1][this.col];

    if (index == insertedMatrix.length) {

      for (int i = 0; i < insertedMatrix.length - 1; i++) {
        for (int j = 0; j < insertedMatrix[0].length; i++) {
          insertedMatrix[i][j] = this.get(i, j);
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
    return new ComplexMatrix(insertedMatrix);
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
  public ComplexMatrix insertRow(final ComplexVector element, final int index) {
    return this.insertRow(element.getArray(), index);
  }

  /**
   * 行列の個々の要素の対数.
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する対数
   */
  public ComplexMatrix log() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).log();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の各列の最大要素をベクトルで求める.
   * 
   * @since 1.1
   * @return ベクトル
   */
  public ComplexVector max() {
    DoubleMatrix cm = this.abs();

    DoubleComplex[] c = new DoubleComplex[this.col];
    double max;
    int index;
    for (int j = 0; j < this.col; j++) {
      max = -Double.MAX_VALUE;
      index = 0;
      for (int i = 0; i < this.row; i++) {
        if (max < cm.get(i, j)) {
          index = i;
          max = cm.get(i, j);
        }
      }
      c[j] = this.get(index, j);
    }
    return new ComplexVector(c);
  }

  /**
   * 行列の列のみの各要素の平均(average)を行ベクトルで返す.
   * 
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public ComplexVector mean() {
    DoubleComplex[] c = new DoubleComplex[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = new DoubleComplex();
      for (int i = 0; i < this.row; i++) {
        c[j] = c[j].add(this.get(i, j));
      }
      c[j] = c[j].divide(new DoubleComplex(this.col, 0.0d));
    }
    return new ComplexVector(c);
  }

  /**
   * 行列の各列の最小要素をベクトル求める.
   * 
   * @since 1.1
   * @return ベクトル
   */
  public ComplexVector min() {
    DoubleMatrix cm = this.abs();
    DoubleComplex[] c = new DoubleComplex[this.col];
    double max;
    int index = 0;
    for (int j = 0; j < this.col; j++) {
      max = -Double.MAX_VALUE;
      for (int i = 0; i < this.row; i++) {
        if (max < this.get(i, j).abs()) {
          index = i;
          max = cm.get(i, j);
        }
      }
      c[j] = this.get(index, j);
    }
    return new ComplexVector(c);
  }

  /**
   * Linear algebraic matrix multiplication, A * b.
   * 
   * @param b
   *          another matrix
   * @return ComplexMatrix product, A * b
   */
  public ComplexMatrix multiply(final ComplexMatrix b) {
    if (b.row != this.col) {
      throw new IllegalArgumentException("ComplexMatrix inner dimensions must agree.");
    }

    DoubleComplex[][] c = new DoubleComplex[this.row][b.col];
    DoubleComplex[] bcolj = new DoubleComplex[this.col];
    for (int j = 0; j < b.col; j++) {
      for (int k = 0; k < this.col; k++) {
        bcolj[k] = b.matrix[k][j];
      }

      for (int i = 0; i < this.row; i++) {
        DoubleComplex[] arowi = this.matrix[i];
        DoubleComplex s = new DoubleComplex();
        for (int k = 0; k < this.col; k++) {
          s = s.add(arowi[k].multiply(bcolj[k]));
        }
        c[i][j] = s;
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * Unary minus.
   * 
   * @return -this
   */
  public ComplexMatrix negate() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].negate();
      }
    }
    return new ComplexMatrix(c);
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
  // public DoubleComplex norm(){
  // ComplexMatrix v = abs();
  // return StrictMath.sqrt(v.multiply(v).sum());
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
  public ComplexMatrix pow(final DoubleComplex d) {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).pow(d);
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列要素の累乗.
   * 
   * @param d
   *          another matrix
   * @return 行列の個々の要素を行列dの要素で累乗した行列を返す.
   */
  public ComplexMatrix pow(final ComplexMatrix d) {
    this.checkMatrixDimensions(d);
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].pow(d.matrix[i][j]);
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の列のみの各要素の積(product)を行ベクトルで返す.
   * 
   * @return 各列の要素の積の行ベクトル
   * @since 1.1
   */
  public ComplexVector product() {
    if (this.getRowDimension() < 2) {
      return new ComplexVector(this);
    }

    DoubleComplex[] c = new DoubleComplex[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = this.get(0, j);
      for (int i = 1; i < this.row; i++) {
        c[j] = c[j].multiply(this.get(i, j));
      }
    }
    return new ComplexVector(c);
  }

  /**
   * 行列を反時計回りに90°回転.
   * 
   * @return 行列
   * @since 1.1
   */
  public ComplexMatrix rot90() {
    DoubleComplex[][] rotated = new DoubleComplex[this.col][this.row];
    for (int i = 0; i < this.col; i++) {
      for (int j = 0; j < this.row; j++) {
        rotated[i][j] = this.get(j, this.col - 1 - i);
      }
    }
    return new ComplexMatrix(rotated);
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
  public ComplexMatrix set(final int i, final int j, final DoubleComplex s) {

    DoubleComplex[][] d = null;

    try {
      d = ((ComplexMatrix) this.clone()).getArray();
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }

    d[i][j] = s;
    return new ComplexMatrix(d);
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
  public ComplexMatrix setMatrix(final int i0, final int i1, final int j0, final int j1,
      final ComplexMatrix x) {
    DoubleComplex[][] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = j0; j <= j1; j++) {
          d[i][j] = x.get(i - i0, j - j0);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(d);
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
  public ComplexMatrix setMatrix(final int i0, final int i1, final int[] col, final ComplexMatrix x) {

    DoubleComplex[][] d = this.getArray();
    try {
      for (int i = i0; i <= i1; i++) {
        for (int j = 0; j < col.length; j++) {
          d[i][col[j]] = x.get(i - i0, j);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(d);
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
  public ComplexMatrix setMatrix(final int[] row, final int j0, final int j1, final ComplexMatrix x) {

    DoubleComplex[][] d = this.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = j0; j <= j1; j++) {
          d[row[i]][j] = x.get(i, j - j0);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(d);
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
  public ComplexMatrix setMatrix(final int[] row, final int[] col, final ComplexMatrix x) {
    DoubleComplex[][] d = this.getArray();
    try {
      for (int i = 0; i < row.length; i++) {
        for (int j = 0; j < col.length; j++) {
          d[row[i]][col[j]] = x.get(i, j);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException("Submatrix indices.");
    }
    return new ComplexMatrix(d);
  }

  /**
   * 行列の個々の要素の正弦(sine).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public ComplexMatrix sin() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).sin();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の要素の平方.
   * 
   * @since 1.1
   * @return 行列の個々の要素を平方した行列
   */
  public ComplexMatrix sqrt() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).sqrt();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * C = a - b.
   * 
   * @param b
   *          another matrix
   * @return a + b
   */
  public ComplexMatrix subtract(final ComplexMatrix b) {
    this.checkMatrixDimensions(b);
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].subtract(b.matrix[i][j]);
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の列のみの各要素の和を行ベクトルで返す.
   * 
   * @return 各列の平均値の行ベクトル
   * @since 1.1
   */
  public ComplexVector sum() {
    if (this.getRowDimension() < 2) {
      return new ComplexVector(this);
    }

    DoubleComplex[] c = new DoubleComplex[this.col];
    for (int j = 0; j < this.col; j++) {
      c[j] = new DoubleComplex();
      for (int i = 0; i < this.row; i++) {
        c[j] = c[j].add(this.get(i, j));
      }
    }
    return new ComplexVector(c);
  }

  /**
   * 行列の個々の要素の正接(tangent).
   * 
   * @since 1.1
   * @return 行列の個々の要素に対する正弦
   */
  public ComplexMatrix tan() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).tan();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * Multiply a matrix by a scalar, c = s * a.
   * 
   * @param s
   *          scalar
   * @return s * a
   */
  public ComplexMatrix times(final DoubleComplex s) {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = s.multiply(this.matrix[i][j]);
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * Element-by-element multiplication, C = a .* b.
   * 
   * @param b
   *          another matrix
   * @return a.*b
   */
  public ComplexMatrix times(final ComplexMatrix b) {
    this.checkMatrixDimensions(b);
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.matrix[i][j].multiply(b.matrix[i][j]);
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 転置行列を返す. ComplexMatrix transpose.
   * 
   * @return a'
   */
  public ComplexMatrix transpose() {
    DoubleComplex[][] c = new DoubleComplex[this.col][this.row];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[j][i] = this.matrix[i][j];
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行を指定して行ベクトル抽出. ComplexMatrix to ComplexVector.
   * 
   * @param col
   *          行
   * @return ComplexVector型の行ベクトル
   */
  public ComplexVector toVector(final int col) {
    return new ComplexVector(this.matrix[col]);
  }

  /**
   * 行列の個々の要素の双曲線正弦(Hyperbolic Sine).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する双曲線正弦
   */
  public ComplexMatrix sinh() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).sinh();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の個々の要素の双曲線余弦(Hyperbolic Cosine).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する双曲線余弦
   */
  public ComplexMatrix cosh() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).cosh();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * 行列の個々の要素の双曲線正接(Hyperbolic Tangent).
   * 
   * @since 2005/01/04
   * @return 行列の個々の要素に対する双曲線正接
   */
  public ComplexMatrix tanh() {
    DoubleComplex[][] c = new DoubleComplex[this.row][this.col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        c[i][j] = this.get(i, j).tanh();
      }
    }
    return new ComplexMatrix(c);
  }

  /**
   * thisとotherとが同値であればtrueを返す.
   * 
   * @param other
   *          比較対象オブジェクト
   * @return thisがotherと同値であればtrue
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof ComplexMatrix)) {
      return false;
    }
    ComplexMatrix castOther = (ComplexMatrix) other;
    return new EqualsBuilder().append(this.matrix, castOther.matrix).isEquals();
  }

  /**
   * ハッシュコードを返す.
   * 
   * @return ハッシュコード
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.matrix).toHashCode();
  }

}
