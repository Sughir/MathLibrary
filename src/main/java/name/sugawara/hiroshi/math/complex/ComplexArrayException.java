package name.sugawara.hiroshi.math.complex;

/**
 * 
 * 複素数配列例外.
 * 
 * @author Hiroshi Sugawara
 * @version $Id: ComplexArrayException.java 94 2006-11-05 18:59:55Z sugawara $
 * 
 * 
 */
public class ComplexArrayException extends Exception {

  /**
   * シリアル.
   * @since 2006/02/20　18:08:05
   */
  private static final long serialVersionUID = 8124591161438707200L;

  /**
   * コンストラクタ.
   * @param message メッセージ
   * @since 2006/02/20　18:06:35
   */
  public ComplexArrayException(final String message) {
    super(message);
  }
}
