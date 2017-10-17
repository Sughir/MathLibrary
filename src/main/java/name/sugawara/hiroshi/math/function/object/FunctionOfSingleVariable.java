package name.sugawara.hiroshi.math.function.object;

/**
 * 1変数関数.
 * @author   Hiroshi Sugawara
 * @version   0.0
 * @param <T1>   独立変数の型
 * @param <T2>   従属変数の型
 * @since   1.1
 * @uml.stereotype   uml_id="null" isDefined="true" name="interface" 
 */
public interface FunctionOfSingleVariable<T1 extends Number, T2 extends Number>
    extends Function {

  /**
   * 1変数関数の従属変数の値( y = f(x) の y )を求める.
   *
   * @since 1.1
   * @param independent
   *          T 型の独立変数の値
   * @return T 型の従属変数の値
   */
  T2 getDependentVariable(final T1 independent);
}
