package name.sugawara.hiroshi.math.function.typeinteger;

/**
 * 組み合わせ数 (number of combinations) n個の ものからk個のものを選ぶ組み合わせ数 nCk を求める.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Combination.java 83 2005-09-12 16:18:56Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class Combination {

  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/25 14:18:41
   */
  private Combination() {
    // Empty block
  }

  /**
   * n個のものからk個のものを選ぶ組み合わせ数 nCk をBigInteger型で求める.
   * 
   * このアルゴリズムでは、100個のものから4個のものを選ぶ組み合わせ数は3921225通りであり、
   * 再帰的に呼び出すメソッドの呼び出し回数も3921225回となり遅い。
   * 
   * @param n
   *          値
   * @param k
   *          値
   * @return n個のものからk個のものを選ぶ組み合わせ数 nCk をlong型で返す
   */
  public static long getNumberOfCombinations(final long n, final long k) {
    if (n < 0 || k < 0) {
      throw new ArithmeticException("The value of an argument must be a positive integer.\n"
          + "The value of the first argument must not exceed the "
          + "value of the second argument.");

    } else if (k == 0 || k == n) {
      return 1;
    }
    return Combination.getNumberOfCombinations(n - 1, k - 1)
        + Combination.getNumberOfCombinations(n - 1, k);
  }

  /**
   * n個のものからk個のものを選ぶ組み合わせ数 nCk をlong型で求める.
   * 
   * 組み合わせ数の公式を使用し高速化. getNumberOfCombinations() よりも高速
   * 
   * @param n
   *          値
   * @param k
   *          値
   * @return n個のものからk個のものを選ぶ組み合わせ数 nCk をlong型で返す
   */
  public static long getNumberOfCombinationsWithFast(final long n, final long k) {

    long result;
    long mutableK = k;
    if (n < 0 || mutableK < 0) {
      throw new ArithmeticException("The value of an argument must be a positive integer.\n"
          + "The value of the first argument must not exceed the "
          + "value of the second argument.");
    } else if (n - mutableK < mutableK) {
      mutableK = n - mutableK;
    }

    if (k == 0) {
      result = 1;
    } else if (k == 1) {
      result = n;
    } else {

      final long numerator = IntegerUtility.factorial(n);
      final long denominator = IntegerUtility.factorial(mutableK)
          * IntegerUtility.factorial(n - mutableK);

      if (numerator % denominator != 0) {
        throw new Error("The result has not been a integer.");
      }
      result = numerator / denominator;
    }
    return result;

  }
}
