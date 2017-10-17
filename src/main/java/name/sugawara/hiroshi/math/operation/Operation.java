package name.sugawara.hiroshi.math.operation;

import name.sugawara.hiroshi.math.function.object.Function;

/**
 * 演算を定義する.
 * 
 * @author Hiroshi Sugawara
 * @version 0.0
 * @param <F>
 *          関数クラス
 * @param <T>
 *          演算結果の型
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="interface"
 */
public interface Operation<F extends Function, T extends Number> {

  /**
   * 関数を指定して演算する.
   * 
   * @since 1.1
   * @param function
   *          関数
   * @return 従属変数の値
   */

  T operate(final F function);

}
