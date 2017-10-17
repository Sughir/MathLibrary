package name.sugawara.hiroshi.math.function.object;

/**
 * 関数サンプル.
 * @author Hiroshi Sugawara
 * @version 0.0
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public final class SampleFunction implements FunctionOfSingleVariable<Double, Double> {

  /**
   * コンストラクタ.
   * 
   * @since 2004/08/03
   */
  public SampleFunction() {
    // Empty block
  }

  /**
   * 従属変数( y = f(x) の y )を求める.
   * 
   * @param independent
   *          独立変数
   * @return 従属変数 y = f(independent) の 計算した y の値
   */
  public Double getDependentVariable(final Double independent) {
    final Double d = independent;

    final double a = Math.sin(d.doubleValue());
    final double b = Math.sin(2 * d.doubleValue());
    return new Double(a + b);
  }
}

/**
 * Demo.
 * 
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

final class SampleFunctionDemo {
  /**
   * コンストラクタ使用禁止.
   * 
   * @since 2005/07/12 17:17:50
   */
  private SampleFunctionDemo() {
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
    final SampleFunction s = new SampleFunction();

    final double start = -10.0d;
    final double end = 10.0d;
    final double increment = 0.1d;
    double result;
    for (double d = start; d < end; d += increment) {
      result = ((s.getDependentVariable(new Double(d)))).doubleValue();

      System.out.println(result);
    }
  }

}
