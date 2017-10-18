/*
 * Created on 2003/06/26
 *
 */
package name.sugawara.hiroshi.math.operation.integral;

/**
 * 数値積分例外.
 * @author   Hiroshi Sugawara
 * @version   $Id: IntegralException.java 109 2010-06-13 04:26:48Z sugawara $
 * @since   1.1
 * @uml.stereotype   uml_id="null" isDefined="true" name="tagged"
 */

public class IntegralException extends Exception {

  /**
   * シリアルバージョンID.
   * @since 2010/05/29　14:55:32
   */
  private static final long serialVersionUID = 833639232636859054L;

  /**
   * @param message 例外メッセージ
   */
  public IntegralException(final String message) {
    super(message);
  }

}
