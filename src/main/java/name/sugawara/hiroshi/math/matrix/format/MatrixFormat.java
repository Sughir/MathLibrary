package name.sugawara.hiroshi.math.matrix.format;

import java.math.BigDecimal;
import java.util.Formattable;
import java.util.Formatter;

/**
 * 行列フォーマット.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$
 * @since 2005/05/24
 */
public class MatrixFormat implements Formattable {

  /**
   * 値.
   * 
   * @since 2005/07/12 1:37:57
   */
  private BigDecimal value;

  // private String symbol,
  // companyName,
  // frenchCompanyName;

  /**
   * フォーマットを生成.
   * 
   * @param value
   *          BigDecimal型の値
   * @since 2005/05/27
   */
  public MatrixFormat(final BigDecimal value) {
    this.value = value;
  }

  /**
   * フォーマットする.
   * 
   * @see java.util.Formattable#formatTo(java.util.Formatter, int, int, int)
   * @since 2005/05/24
   * @param formatter
   *          フォーマッタ
   * @param flags
   *          フラグ
   * @param width
   *          幅
   * @param precision
   *          精度
   */
  public void formatTo(final Formatter formatter, final int flags, final int width,
      final int precision) {

    // public StockName(String symbol, String companyName, String
    // frenchCompanyName) { ... }
    StringBuilder sb = new StringBuilder();
    // decide form of name
    String name = "";
    String result = String.format("%+6.5e", new Object[]
    { this.value });

    // formatter.format(result);

  }

  /**
   * 文字列表現.
   * 
   * @return 文字列
   * @see java.lang.Object#toString()
   * @since 2005/07/12 1:39:01
   */
  @Override
  public final String toString() {
    return String.format("%s", new Object[]
    { this.value });
  }

}
