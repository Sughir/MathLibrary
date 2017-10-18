/*
 * 作成日： 2004/08/14
 * 
 * @version $Id: ConstantTest.java 109 2010-06-13 04:26:48Z sugawara $
 */
package name.sugawara.hiroshi.math.constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import junit.framework.JUnit4TestAdapter;
import name.sugawara.hiroshi.math.precision.NumberOfTerm;
import name.sugawara.hiroshi.math.precision.Precision;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hiroshi Sugawara
 * @version $Id: ConstantTest.java 109 2010-06-13 04:26:48Z sugawara $ Created Date :
 *          2005/07/24　19:41:09
 */
public class ConstantTest {
  /**
   * 誤差.
   * 
   * @since 2005/07/11 2:54:30
   */
  private Precision    p;

  /**
   * 定数.
   * 
   * @since 2005/07/11 2:54:37
   */
  private Constant     c;

  /**
   * 定数.
   * 
   * @since 2005/07/11 2:54:37
   */
  private Constant     c2;

  /**
   * 定数.
   * 
   * @since 2005/07/11 2:54:37
   */
  private Constant     c3;

  /**
   * 演算回数.
   * 
   * @since 2005/07/11 2:54:43
   */
  private BigInteger   N;

  /**
   * 機械エプシロン.
   * 
   * @since 2005/07/11 2:55:06
   */
  // private BigDecimal eps;

  /**
   * スケール.
   * 
   * @since 2005/07/11 2:55:18
   */
  private int          scale;

  /**
   * RodudingMode型の丸めモード.
   * 
   * @since 2010/01/14　2:48:15
   */
  private RoundingMode roundingMode;

  /**
   * MathContectオブジェクト.
   * 
   * @since 2010/01/14　2:44:36
   */
  private MathContext  mc;

  /**
   * Junit3.8環境用.
   * @return テスト
   * @since 2010/06/10　15:53:46
   */
  public static junit.framework.Test suite() {
    return new JUnit4TestAdapter(ConstantTest.class);
}
  /**
   */
  @Before
  protected void setUp() {
    this.N = BigInteger.valueOf(100);
    // this.eps = new BigDecimal("10e-10");
    this.scale = 100;
    this.mc = new MathContext(this.scale, this.roundingMode);
    this.p = new NumberOfTerm(this.N, this.mc);
    this.c = null;
    this.c2 = new Constant(this.p);
    this.c3 = new Constant(this.p);
  }

  /**
   */
  @After
  protected void tearDown() throws Exception {
    // ?
  }

  /**
   * Constant().
   * 
   * @since 2004/08/14
   */
  @Test
  public void testConstant() {
    assertNull(this.c);
    this.c = new Constant(this.p);
    assertNotNull(this.c);
  }

  /**
   * BigDecimal pi のテスト().
   */
  @Test
  public void testPi() {
    assertNotNull(this.c2);
    assertNotNull(this.c3);
    assertTrue(this.c2.pi().compareTo(this.c3.pi()) == 0);
    assertTrue(this.c2.pi().equals(this.c3.pi()));
    assertEquals(this.c2.pi().doubleValue(), 3.141592, 0.0001d);
  }

  /**
   * BigDecimal exp のテスト().
   */
  @Test
  public void testExp() {
    assertTrue(this.c2.exp().compareTo(this.c3.exp()) == 0);
    assertTrue(this.c2.exp().equals(this.c3.exp()));
    assertEquals(this.c2.exp().doubleValue(), 2.71, 0.01d);
  }

}
