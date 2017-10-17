package name.sugawara.hiroshi.math.function.typedouble;

/**
 * double 型初等関数.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: Hypotenuse.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

@Deprecated
public final class Hypotenuse {

  /**
   * 使用禁止.
   * 
   * @since 2007/02/22 13:58:20
   */
  private Hypotenuse() {
    // Empty block
  }

  /**
   * abs(y) * sqrt(1*(x/y)^2) による方法 で sqrt(x^2 + y^2) を求める.
   * 
   * @param x
   *          x
   * @param y
   *          y
   * @return sqrt(x^2 + y^2)
   */
  public static double hypot1(final double x, final double y) {
    double result = 0;
    if (x == 0) {
      result = Math.abs(y);
    }
    if (y == 0) {
      result = Math.abs(x);
    } else {
      double t;
      if (Math.abs(y) > Math.abs(x)) {
        t = x / y;
        result = Math.abs(y) * Math.sqrt(1 + t * t);
      } else {
        t = y / x;
        result = Math.abs(x) * Math.sqrt(1 + t * t);
      }
    }
    return result;
  }

  /**
   * strictfpを用い、abs(y) * sqrt(1*(x/y)^2) による方法 で sqrt(x^2 + y^2) を求める.
   * 
   * @param x
   *          x
   * @param y
   *          y
   * @return sqrt(x^2 + y^2)
   */
  public static strictfp double strictHypot1(final double x, final double y) {
    double result = 0;
    if (x == 0) {
      result = Math.abs(y);
    }
    if (y == 0) {
      result = Math.abs(x);
    } else {
      double t;
      if (Math.abs(y) > Math.abs(x)) {
        t = x / y;
        result = Math.abs(y) * Math.sqrt(1 + t * t);
      } else {
        t = y / x;
        result = Math.abs(x) * Math.sqrt(1 + t * t);
      }
    }
    return result;
  }

  /**
   * C. B. Moler and D. Morrison による方法でhypotenuseの長さを求める 演算繰り返し数は単精度で2,倍精度で3でよい.
   * 
   * @param x
   *          x
   * @param y
   *          y
   * @param n
   *          演算繰り返し数
   * @return sqrt(x^2 + y^2)
   */
  public static strictfp double strictHypot2(final double x, final double y, final long n) {
    double t;
    double mutableX = Math.abs(x);
    double mutableY = Math.abs(y);
    if (mutableX < y) {
      t = mutableX;
      mutableX = mutableY;
      mutableY = t;
    }
    if (y == 0) {
      return mutableX;
    }
    for (long i = 0; i < n; i++) {
      t = mutableY / mutableX;
      t *= t;
      t /= 4 + t;
      mutableX += 2 * mutableX * t;
      mutableY *= t;
    }
    return mutableX;
  }

}
