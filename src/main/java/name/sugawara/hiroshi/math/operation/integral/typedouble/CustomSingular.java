/*
 * 作成日: 2004/01/30
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * @author Hiroshi Sugawara
 * @version $Id: CustomSingular.java 109 2010-06-13 04:26:48Z sugawara $
 * @since 2004/01/30 23:54:56
 */
@Deprecated
public class CustomSingular extends Singular {
  // TODO 不要になり開発中断予定

  // private Double begin;
  // private boolean minus;

  // public CustomSingular(Integral integral ){
  //
  // }
  //

  /**
   * 積分開始区間をセットする.
   * 
   * @param begin
   *          積分開始区間
   * @return 演算子
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#setInterval(java.lang.Number)
   * @since 2004/01/30 23:56:09
   */
  @Override
  public Operation setInterval(final Number begin) {
    return null;
    // CustomSingular o = null;
    // try {
    // if (begin.getClass() != Class.forName("java.lang.Double")) {
    // throw new ClassCastException("The type of begin must be
    // java.lang.Double.");
    // }
    //
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // } catch (ClassCastException e) {
    // e.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    //
    // if (begin.doubleValue() < 0) {
    // this.minus = true;
    //
    // this.begin = new Double(StrictMath.abs(begin.doubleValue()));
    // } else {
    // this.begin = (Double) begin;
    // }
    //
    // o = new CustomSingular(this.integral);
    // o.begin = (Double) begin;
    //
    // return o;
  }

  /**
   * 積分する.
   * 
   * @param function
   *          関数
   * @return 従属変数
   * @see name.sugawara.hiroshi.math.operation.integral.typedouble.Singular#operate(Function)
   * @since 2004/01/30 23:56:09
   */
  @Override
  public Number operate(final Function function) {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

}