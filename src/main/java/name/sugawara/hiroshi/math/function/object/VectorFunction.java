/*
 * 作成日: 2003/10/06
 *
 */
package name.sugawara.hiroshi.math.function.object;

import name.sugawara.hiroshi.math.matrix.RowVector;

/**
 * 独立変数、従属変数が共にベクトルとなる関数.
 * @author   sugawara
 * @since   1.1
 * @version   $Id: VectorFunction.java 94 2006-11-05 18:59:55Z sugawara $
 * @param <T1>   独立変数ベクトル
 * @param <T2>   従属変数ベクトル
 * @uml.stereotype   uml_id="null" isDefined="true" name="interface" 
 */
public interface VectorFunction<T1 extends RowVector, T2 extends RowVector>
    extends Function {
  /**
   * 引数、戻り値がベクトルである関数.
   * @param vector 従属変数ベクトル
   * @return 独立変数ベクトル
   * @since 2005/07/13　2:21:55
   */
  T2 getDependentVariable(final T1 vector);
}
