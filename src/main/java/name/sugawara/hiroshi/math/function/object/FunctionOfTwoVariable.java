package name.sugawara.hiroshi.math.function.object;

/**
 * 2変数関数.
 * 
 * @author Hiroshi Sugawara
 * @version 0.0
 * @param <T1>
 *          Number型の第一引数
 * @param <T2>
 *          Number型の第二引数
 * @param <T3>
 *          Number型の戻り値
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="interface"
 */

public interface FunctionOfTwoVariable<T1 extends Number, T2 extends Number, T3 extends Number>
    extends Function {

  /**
   * 2変数関数の従属変数の値( y = f(x) の y )を求める.
   * 
   * @since 1.1
   * @param independent1
   *          Number 型の独立変数の値、第1引数
   * @param independent2
   *          Number 型の独立変数の値、第2引数
   * @return Number 型の従属変数の値
   */
  T3 getDependentVariable(final T1 independent1, final T2 independent2);

}
