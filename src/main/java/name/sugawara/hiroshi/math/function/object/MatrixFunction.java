/*
 * Created on 2003/07/29
 * 
 */
package name.sugawara.hiroshi.math.function.object;

import name.sugawara.hiroshi.math.matrix.Matrix;

/**
 * 引数と戻り値が行列である関数.
 * 
 * @author sugawara
 * @since 1.1
 * @version $Id: MatrixFunction.java 94 2006-11-05 18:59:55Z sugawara $
 * @param <T1>
 *          独立変数行列
 * @param <T2>
 *          従属変数行列
 * @uml.stereotype uml_id="null" isDefined="true" name="interface"
 */

public interface MatrixFunction<T1 extends Matrix, T2 extends Matrix> extends
    Function {

  /**
   * 引数、戻り値双方が行列である関数.
   * 
   * @param matrix
   *          行列
   * @return 行列を返す
   * @since 2004/08/03
   */
  T2 getDependentVariable(final T1 matrix);

}
