package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 整数判定器.
 *
 * @author Hiroshi Sugawara
 * @version $Id: IntegerJudgement.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @deprecated BigDecimal#toBigIntegerExact()の登場によりこのクラスは不要になった.
 */
@Deprecated
final strictfp class IntegerJudgement {

  /**
   * コンストラクタ使用禁止.
   *
   * @since 2005/07/13 1:36:58
   */
  private IntegerJudgement() {
    // Empty block
  }

  /**
   * BigDecimal型の値の小数点以下の値を破棄しても元の値と等値かどうか判定する.
   *
   * @param bigDecimal
   *          判定したいBigDecimal型の値
   * @return BigDecimalが整数と等値のとき真を返す
   */
  public static boolean judge(final BigDecimal bigDecimal) {
    final BigInteger bigInteger = bigDecimal.toBigInteger();
    final BigDecimal cutOffDecimal = new BigDecimal(bigInteger);
    final BigDecimal setScaleDecimal = cutOffDecimal.setScale(bigDecimal.scale());
    if (setScaleDecimal.compareTo(bigDecimal) == 0) {
      return true;
    }
    return false;
  }

  /**
   * double型の値の小数点以下の値を破棄しても元の値と等値かどうか判定する.
   *
   * @param doubleNumber
   *          判定したいBigDecimal型の値
   * @return doubleが整数と等値のとき真を返す
   */
  public static boolean judge(final double doubleNumber) {
    final Double wrappedDouble = new Double(doubleNumber);
    final long longNumber = wrappedDouble.longValue();
    final Long wrappedLong = Long.valueOf(longNumber);
    final double cutOffDouble = wrappedLong.doubleValue();
    if (cutOffDouble == doubleNumber) {
      return true;
    }
    return false;
  }

}
//
// /**
// * デモ.
// * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
// */
// class IntegerJudgementDemo {
// /**
// * メイン.
// *
// * @param args
// * 引数
// * @since 2004/08/03
// */
// public static void main(final String[] args) {
// StringBuilder sb = new StringBuilder();
// BigDecimal bdInt = new BigDecimal("123456.0000000000000000");
// BigDecimal bd = new BigDecimal("123456.00005746464650000");
// sb.append("The integer : " + IntegerJudgement.judge(bdInt) + "\n");
// sb.append("The decimal : " + IntegerJudgement.judge(bd));
//
// sb.append("\n");
//
// double dInt = 1234.0000d;
// double d = 1234.0456340d;
//
// sb.append("The int : " + IntegerJudgement.judge(dInt) + "\n");
// sb.append("The double : " + IntegerJudgement.judge(d));
//
// sb.append("\n");
//
// double dInt2 = 1234.0000d;
// double d2 = 1234.0456346d;
//
// sb.append("The int : " + IntegerJudgement.judge(dInt2) + "\n");
// sb.append("The double : " + IntegerJudgement.judge(d2));
//
// sb.append("\n");
//
// double dInt3 = 1234.0000d;
// double d3 = 1234.0456376d;
//
// sb.append("The int : " + IntegerJudgement.judge(dInt3) + "\n");
// sb.append("The double : " + IntegerJudgement.judge(d3));
//
// System.out.println(new String(sb));
// }
// }
