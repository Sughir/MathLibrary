/*
 * Created on 2003/06/30
 * 
 */
package name.sugawara.hiroshi.math.precision;

import java.io.Serializable;
import java.math.MathContext;

/**
 * 精度情報.
 * 
 * <br />
 * 
 * <pre>
 *  BigDecimal関係の初等関数を級数展開などで求めるときに必要な項を演算する回数、
 *  許容誤差(機械epsilon)、 BigDecimal#divide()を使用するときに必要になってくる
 *  BigDecimal#scale()、 除算の丸めモードなどの情報を指定するために使う。
 * </pre>
 * 
 * @author sugawara
 * @version $Id: Precision.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 1.1
 */

public abstract class Precision implements Serializable {

  /**
   * 
   * @since 2010/06/11　3:09:10
   */
  private static final long serialVersionUID = 4842837009589166334L;
  /**
   * Java5から追加された精度情報.
   * 
   * @since 2005/07/25 13:08:10
   */
  private MathContext mathContext;

  /**
   * MathContextオブジェクトを指定する.
   * 
   * @param mathContext
   *          MathContext
   * @since 2007/03/09 12:50:42
   */
  public Precision(final MathContext mathContext) {
    this.mathContext = mathContext;
  }

  /**
   * Java SE 5から追加された精度情報を返す.
   * 
   * @return 精度情報
   * @since 2005/07/25 13:09:22
   */
  public final MathContext getMathContext() {
    return this.mathContext;
  }

}
