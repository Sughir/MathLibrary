package name.sugawara.hiroshi.math.array;

import java.util.Arrays;

/**
 * 配列演算クラス.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: ArraysUtil.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */
public final strictfp class ArraysUtil implements Cloneable {
  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/11 2:57:42
   */
  private ArraysUtil() {
    // コンストラクタ使用禁止.
  }

  /**
   * 配列のサイズを比較する.<br />
   * サイズが異なればエラーとして処理を中断する
   * 
   * @param a
   *          比較の対照になる配列
   * @param b
   *          比較の対照になる配列
   * @since 1.1
   */
  public static void compareToEachArraySize(final double[] a, final double[] b) {
    if (a.length != b.length) {
      // String japanese = " 双方の配列のサイズが違います ";
      final String english = "The sizes of both of array differ.";

      throw new Error(english);
    }
  }

  /**
   * 配列をディープコピーする.
   * 
   * @param from
   *          コピー元の配列
   * @param to
   *          コピー先の配列
   * @since 1.1
   */
  public static void deepCopy(final double[] from, final double[] to) {
    ArraysUtil.compareToEachArraySize(from, to);
    for (int i = 0; i < from.length; i++) {
      to[i] = from[i];
    }
  }

  /**
   * 2つの配列を連結する.
   * 
   * @param front
   *          前方に連結される配列
   * @param rear
   *          後方に連結される配列
   * @return 指定された2つの配列を連結した配列を返す
   * @since 1.1
   */
  public static double[] concatenate(final double[] front, final double[] rear) {
    final int lengthes = front.length + rear.length;
    final double[] combine = new double[lengthes];
    for (int i = 0; i < front.length; i++) {
      combine[i] = front[i];
    }
    for (int i = 0; i < front.length; i++) {
      combine[i + front.length] = rear[i];
    }
    return combine;
  }

  /**
   * 配列に指定された値を指定されたインデックスに挿入する.<br />
   * 要素を挿入すると配列のサイズが1大きくなる。
   * 
   * @param array
   *          挿入される配列
   * @param element
   *          挿入する要素
   * @param index
   *          要素を挿入する位置
   * @return 要素を挿入された配列を返す
   * @since 1.1
   */
  public static double[] insert(final double[] array, final double element, final int index) {
    double[] insertedArray = new double[array.length + 1];
    if (index == insertedArray.length) {
      for (int i = 0; i <= insertedArray.length; i++) {
        if (i != index) {
          insertedArray[i] = array[i];
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
          insertedArray[j] = array[i];
        }
      }
    }
    return insertedArray;
  }

  /**
   * 配列の指定されたインデックスの要素を削除する.<br />
   * 要素を削除すると配列のサイズが1小さくなる。
   * 
   * @param array
   *          要素を一つ削除される配列
   * @param index
   *          要素を削除する位置
   * @return 要素を1つ削除された配列を返す
   * @since 1.1
   */
  public static double[] delete(final double[] array, final int index) {
    final double[] deletedArray = new double[array.length - 1];

    if (index == deletedArray.length) {
      for (int i = 0; i < deletedArray.length; i++) {
        deletedArray[i] = array[i];
      }
    } else {
      for (int i = 0, j = 0; j < deletedArray.length; i++, j++) {
        if (j == index) {
          i++;
          deletedArray[j] = array[i];
        } else {
          deletedArray[j] = array[i];
        }
      }
    }
    return deletedArray;
  }

  /**
   * 配列の要素を左右逆順にする.
   * 
   * @param array
   *          左右逆順にされる配列
   * @return 指定された配列要素を逆順にした配列を返す
   * @since 1.1
   */
  public static double[] fliplr(final double[] array) {
    final double[] flipped = new double[array.length];
    for (int i = 0; i < array.length; i++) {
      flipped[i] = array[array.length - 1 - i];
    }
    return flipped;
  }

  /**
   * 配列の要素の和(sum 記号Σ(Sigma))を値で返す. <br/>
   * n-1 y = Σ a_i i=0
   * 
   * @since 1.1
   * @param array
   *          加算される配列
   * @return 指定された配列arrayの要素を全て加算した値を返す。
   */
  public static double sum(final double[] array) {
    double sum = 0.0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
    }
    return sum;
  }

  /**
   * 配列の要素の平均値を返す. <br/>
   * n-1 y = ( Σ a_i )/ n i=0
   * 
   * @since 1.1
   * @param array
   *          配列
   * @return 指定された配列arrayの要素の平均値を返す。
   */
  public static double mean(final double[] array) {
    return ArraysUtil.sum(array) / array.length;
  }

  /**
   * 配列の要素の積(product 記号Π(Pi))を値で返す. 配列の要素を全て乗算した値を返す n-1 y = Π a_i i=0
   * 
   * @since 1.1
   * @param array
   *          乗算される配列
   * @return 指定された配列arrayの要素を全て乗算した値を返す。
   */
  public static double product(final double[] array) {
    double product = array[0];
    for (int i = 1; i < array.length; i++) {
      product *= array[i];
    }
    return product;
  }

  /**
   * 配列どうしの加算.
   * 
   * @since 1.1
   * @param a
   *          加算される配列
   * @param b
   *          加算する配列
   * @return 指定された配列aと指定された配列bを加算した配列を返す。
   */
  public static double[] add(final double[] a, final double[] b) {

    // test 5.9.2002 compareToEachArraySize( a, b );
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = a[i] + b[i];
    }
    return c;
  }

  /**
   * 配列の各要素に指定された値を加算.
   * 
   * @since 1.1
   * @param a
   *          加算される配列
   * @param value
   *          配列の各要素に加算する値
   * @return 指定された配列aの各要素に指定された値bを加算した配列を返す。
   */
  public static double[] add(final double[] a, final double value) {

    final double[] b = new double[a.length];
    Arrays.fill(b, value);
    return ArraysUtil.add(a, b);
  }

  /**
   * 配列どうしの減算.
   * 
   * @since 1.1
   * @param a
   *          減算される配列
   * @param b
   *          減算する配列
   * @return 指定された配列aと指定された配列bを減算した配列を返す。
   */
  public static double[] subtract(final double[] a, final double[] b) {

    ArraysUtil.compareToEachArraySize(a, b);
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length || i < b.length; i++) {
      c[i] = a[i] - b[i];
    }
    return c;
  }

  /**
   * 配列の各要素に指定された値を減算.
   * 
   * @since 1.1
   * @param a
   *          減算される配列
   * @param value
   *          配列の各要素に減算する値
   * @return 指定された配列aの各要素に指定された値valueを減算した配列を返す。
   */
  public static double[] subtract(final double[] a, final double value) {

    final double[] b = new double[a.length];
    Arrays.fill(b, value);
    return ArraysUtil.subtract(a, b);
  }

  /**
   * 配列の各要素に指定された値を減算.
   * 
   * @since 1.1
   * @param value
   *          配列の各要素に減算される値
   * @param a
   *          減算する側の配列
   * @return 指定された value 値から指定された配列 a の各要素を減算した配列を返す。
   */
  public static double[] subtract(final double value, final double[] a) {

    final double[] b = new double[a.length];
    Arrays.fill(b, value);
    return ArraysUtil.subtract(b, a);
  }

  /**
   * 配列どうしの乗算 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param multiplicand
   *          乗算される配列
   * @param multiplier
   *          乗算する配列
   * @return 指定された配列aと指定された配列bを乗算した配列を返す。
   */
  public static double[] multiply(final double[] multiplicand, final double[] multiplier) {
    ArraysUtil.compareToEachArraySize(multiplicand, multiplier);
    final double[] product = new double[multiplicand.length];
    for (int i = 0; i < multiplicand.length || i < multiplier.length; i++) {
      product[i] = multiplicand[i] * multiplier[i];
    }
    return product;
  }

  /**
   * 配列の要素の実数倍.
   * 
   * @since 1.1
   * @param a
   *          実数倍する配列
   * @param times
   *          配列を実数倍する値
   * @return 指定された配列の個々の要素を実数倍した配列を返す。
   */
  public static double[] multiply(final double[] a, final double times) {
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = a[i] * times;
    }
    return c;
  }

  /**
   * 配列どうしのベクトルのスカラー積(内積). <br/>
   * 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param a
   *          配列
   * @param b
   *          配列
   * @return 指定された配列aと指定された配列bを乗算した配列を返す。
   */
  public static double innerProduct(final double[] a, final double[] b) {
    ArraysUtil.compareToEachArraySize(a, b);

    return ArraysUtil.sum(ArraysUtil.multiply(a, b));
  }

  /**
   * 配列どうしの除算 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param a
   *          除算される配列
   * @param b
   *          除算する配列
   * @return 指定された配列aと指定された配列bを除算した配列を返す。
   */
  public static double[] divide(final double[] a, final double[] b) {

    ArraysUtil.compareToEachArraySize(a, b);
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length || i < b.length; i++) {
      c[i] = a[i] / b[i];
    }
    return c;
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param a
   *          除算される配列
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す。
   */
  public static double[] divide(final double[] a, final double value) {
    final double[] b = new double[a.length];
    Arrays.fill(b, value);
    return ArraysUtil.divide(a, b);
  }

  /**
   * 配列の各要素に指定された値を除算.
   * 
   * @since 1.1
   * @param a
   *          除算される配列
   * @param value
   *          配列の各要素に除算する値
   * @return 指定された配列aの各要素に指定された値bを除算した配列を返す。
   */
  public static double[] divide(final double value, final double[] a) {
    final double[] b = new double[a.length];
    Arrays.fill(b, value);
    return ArraysUtil.divide(b, a);
  }

  // /**
  // * 配列のベクトルaとベクトルbの逆数とのスカラー積(内積)
  // * 双方の配列はそれぞれサイズが同じである必要がある。
  // * @since 1.1
  // * @param a 配列
  // * @param b 配列
  // * @return 指定された配列aと指定された配列bの逆数を合計した値を返す。
  // */
  //
  // public static double innerProductOtherSideDivide( double[] a, double[] b ){
  //
  // compareToEachArraySize( a, b );
  // return sum( divide( a, b ) );
  // }

  /**
   * 配列の要素の累乗.
   * 
   * @since 1.1
   * @param a
   *          累乗する配列
   * @param d
   *          指数
   * @return 指定された配列の個々の要素をd乗した配列を返す。
   */
  public static double[] pow(final double[] a, final double d) {
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = Math.pow(a[i], d);
    }
    return c;
  }

  /**
   * 配列の要素の平方.
   * 
   * @since 1.1
   * @param a
   *          平方する配列
   * @return 指定された配列の個々の要素を平方した配列を返す。
   */
  public static double[] sqrt(final double[] a) {
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = Math.sqrt(a[i]);
    }
    return c;
  }

  /**
   * 配列の要素の正弦関数 Sine function.
   * 
   * @since 1.1
   * @param x
   *          sin関数でxを変数とする配列
   * @return 指定された配列の個々の要素をsin関数に代入した配列を返す。
   */
  public static double[] sin(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.sin(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素の余弦関数 Cosine function.
   * 
   * @since 1.1
   * @param x
   *          cos関数でxを変数とする配列
   * @return 指定された配列の個々の要素をsin関数に代入した配列を返す。
   */
  public static double[] cos(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.cos(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素の正接関数 Tangent.
   * 
   * @since 1.1
   * @param x
   *          tan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をtan関数に代入した配列を返す。
   */
  public static double[] tan(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.tan(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素の逆正弦関数 Arc Sine.
   * 
   * @since 1.1
   * @param x
   *          arcsin関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarcsin関数に代入した配列を返す。
   */
  public static double[] asin(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.asin(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素の逆余弦関数 Arc Cosine.
   * 
   * @since 1.1
   * @param x
   *          arccos関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarccos関数に代入した配列を返す。
   */
  public static double[] acos(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.acos(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素の逆正接関数 Arc Tangent.
   * 
   * @since 1.1
   * @param x
   *          arctan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarctan関数に代入した配列を返す。
   */
  public static double[] atan(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.atan(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素の4象限逆正接 Four Quadrant Arc Tangent. <br/>
   * 指定された角度の4象限逆正接 (4象限アークタンジェント)を返す.
   * 
   * @since 1.1
   * @param y
   *          ラジアンで表した arctan関数でyを変数とする配列
   * @param x
   *          ラジアンで表した arctan関数でxを変数とする配列
   * @return 指定された配列の個々の要素をarctan関数に代入した配列を返す。
   */
  public static double[] atan2(final double[] y, final double[] x) {
    ArraysUtil.compareToEachArraySize(y, x);
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.atan2(y[i], x[i]);
    }
    return c;
  }

  /**
   * 配列の要素のexp関数 exp(x).
   * 
   * @since 1.1
   * @param x
   *          exp関数でxを変数とする配列
   * @return 指定された配列の個々の要素をexp関数に代入した配列を返す。
   */
  public static double[] exp(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.exp(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素のlog関数 log(x).
   * 
   * @since 1.1
   * @param x
   *          log関数でxを変数とする配列
   * @return 指定された配列の個々の要素をlog関数に代入した配列を返す。
   */
  public static double[] log(final double[] x) {
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.log(x[i]);
    }
    return c;
  }

  /**
   * 配列の要素のlog_10関数(常用対数) log_10(x).
   * 
   * @since 1.1
   * @param x
   *          常用対数で x を変数とする配列
   * @return 指定された配列の個々の要素を常用対数に代入した配列を返す。
   */
  public static double[] log10(final double[] x) {
    final double ten = 10.0d;
    final double logTen = Math.log(ten);
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.log(x[i]) / logTen;
    }
    return c;
  }

  /**
   * 配列の要素のlog_2関数(底を2とする対数) log_2(x).
   * 
   * @since 1.1
   * @param x
   *          底を2とする対数で x を変数とする配列
   * @return 指定された配列の個々の要素を底を2とする対数に代入した配列を返す。
   */
  public static double[] log2(final double[] x) {
    final double logTwo = Math.log(2.0d);
    final double[] c = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      c[i] = Math.log(x[i]) / logTwo;
    }
    return c;
  }

  /**
   * 2つの配列の個々の値の2乗の和を配列で返す. a^2 + b^2 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param a
   *          配列
   * @param b
   *          配列
   * @return 指定された2つの配列の個々の値の2乗の和を配列で返す。
   */
  public static double[] sumOfSquare(final double[] a, final double[] b) {

    ArraysUtil.compareToEachArraySize(a, b);
    return ArraysUtil.add(ArraysUtil.pow(a, 2.0), ArraysUtil.pow(b, 2.0));
  }

  /**
   * 配列の個々の要素に対する絶対値を配列で返す.
   * 
   * @since 1.1
   * @param a
   *          配列
   * @return 指定された2つの配列の個々の要素に対する絶対値を配列で返す。
   */
  public static double[] abs(final double[] a) {
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = Math.abs(a[i]);
    }
    return c;
  }

  /**
   * 2つの配列の個々の要素に対する相乗平均を配列で返す. sqrt(a^2 + b^2) 双方の配列はそれぞれサイズが同じである必要がある.
   * 
   * @since 1.1
   * @param a
   *          配列
   * @param b
   *          配列
   * @return 指定された2つの配列の個々の要素に対する相乗平均を配列で返す。
   */
  public static double[] hypot2(final double[] a, final double[] b) {
    ArraysUtil.compareToEachArraySize(a, b);
    final double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = Math.hypot(a[i], b[i]);
    }
    return c;
  }

  /**
   * 配列の要素を列挙する(Test表示用).
   * 
   * @since 1.1
   * @param array
   *          表示される配列
   */
  public static void display(final double[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.println("Array[" + i + "]=" + array[i]);
    }
  }

  /**
   * 配列の要素の最大値を求める.
   * 
   * @since 1.1
   * @param array
   *          配列
   * @return 配列の最大値
   */
  public static double max(final double[] array) {
    double max = (-1) * Double.MAX_VALUE;
    for (int i = 0; i < array.length; i++) {
      max = Math.max(max, array[i]);
    }
    return max;
  }

  /**
   * 配列の要素の最小値を求める.
   * 
   * @since 1.1
   * @param array
   *          配列
   * @return 配列の最小値
   */
  public static double min(final double[] array) {
    double min = Double.MAX_VALUE;
    for (int i = 0; i < array.length; i++) {
      min = Math.min(min, array[i]);
    }
    return min;
  }

  /**
   * 引数で指定された2つの配列のconvolution(畳み込み)をdouble型の値を返す.<br />
   * 返された配列のサイズは2つの配列のサイズの和から1を引いた値になる.
   * 
   * @param x
   *          信号(配列)
   * @param h
   *          システム(配列)
   * @return サイズが x + h - 1 のdouble[]型を返す
   * @since 1.1
   */
  public static double[] convolute(final double[] x, final double[] h) {
    final double[] y = new double[x.length + h.length - 1];
    for (int n = 0; n < y.length; n++) {
      for (int k = 0; k < x.length; k++) {
        if (0 <= n - k && n - k < h.length) {
          y[n] += x[k] * h[n - k];
        }
      }
    }
    return y;
  }

  /**
   * 引数で指定された2つの配列のcross-correlation(相互相関関数)をdouble型の配列で返す.<br />
   * 返された配列のサイズは2つの配列のサイズの和から1を引いた値になる。
   * 
   * @param x
   *          信号(配列)
   * @param h
   *          システム(配列)
   * @return サイズが x + h - 1 のdouble[]型を返す
   * @since 1.1
   */
  public static double[] correlate(final double[] x, final double[] h) {
    return ArraysUtil.convolute(x, ArraysUtil.fliplr(h));
  }

  /**
   * 引数で指定された配列のauto-correlation(自己相関関数)をdouble型の配列で返す.<br />
   * 返された配列のサイズは配列のサイズの2倍から1を引いた値になる。
   * 
   * @param x
   *          信号(配列)
   * @return サイズが 2 * x - 1 のdouble[]型を返す
   * @since 1.1
   */
  public static double[] correlate(final double[] x) {
    return ArraysUtil.correlate(x, x);
  }

}

/**
 * 配列演算デモ.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: ArraysUtil.java 109 2010-06-13 04:26:48Z sugawara $ Created Date : 2005/07/11
 *          3:06:49
 */
final class ArraysUtilDemo {
  /**
   * 配列サイズ.
   * 
   * @since 2005/07/12 10:48:56
   */
  private static final int ARRAY_SIZE = 10;

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/11 3:07:14
   */
  private ArraysUtilDemo() {
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
    final double[] a = new double[ArraysUtilDemo.ARRAY_SIZE];
    // double[] b = new double[ARRAY_SIZE];
    final double tenInv = 0.1;
    for (int i = 0; i < a.length; i++) {
      a[i] = i * tenInv;
    }
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
    System.out.println("----");

    final double testValue = -3.33333;
    double[] insertedArray = ArraysUtil.insert(a, testValue, 0);
    System.out.println("InsertTest:insert(a,-3.33333,0):");

    for (int i = 0; i < insertedArray.length; i++) {
      System.out.println(" " + insertedArray[i]);
    }

    final float testValue2 = 9.1f;
    System.out.println("InsertTest:insert(a, 9.1, 1).");
    insertedArray = ArraysUtil.insert(insertedArray, testValue2, 1);

    for (int i = 0; i < insertedArray.length; i++) {
      System.out.println(" " + insertedArray[i]);
    }
    
    final int testValue3 = 11;
    System.out.println("InsertTest:insert(a, 11, 11)");
    insertedArray = ArraysUtil.insert(insertedArray, testValue3, testValue3);

    for (int i = 0; i < insertedArray.length; i++) {
      System.out.println(" " + insertedArray[i]);
    }

    final int testValue4 = 13;
    System.out.println("InsertTest:insert(a, 13, 13)(last)");
    insertedArray = ArraysUtil.insert(insertedArray, testValue4, testValue4);

    for (int i = 0; i < insertedArray.length; i++) {
      System.out.println(" " + insertedArray[i]);
    }

  }

}
