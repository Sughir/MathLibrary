/**
 * Created Date : 2006/07/06 15:41:55
 */
package name.sugawara.hiroshi.math.function.decimal;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * CoeRootのテスト.
 * 
 * @author Hiroshi Sugawara
 * @version $Id$ Created Date : 2006/07/06 15:41:55
 */
public class CubeRootTest {

  /**
   * テスト用変数.
   * 
   * @since 2006/07/06 15:43:16
   */
  private CubeRoot cube;

  /**
   * @since 2010/06/11　3:05:31
   */
  private CubeRoot cube2;

  /**
   * セットアップ.
   * 
   * @see TestCase#setUp()
   */
  @Before
  protected void setUp() throws Exception {

    this.cube = new CubeRoot(DecimalFunctionTest.PRECISION);
    this.cube2 = new CubeRoot(DecimalFunctionTest.PRECISION);
  }

  /**
   * 'CubeRoot.CubeRoot(Precision)' のためのテスト・メソッド.
   */
  @Test
  public final void testCubeRoot() {
    Assert.assertFalse(this.cube.equals(this.cube2));
    Assert.assertNotNull(this.cube);
    Assert.assertNotNull(this.cube2);

  }

  /**
   * 'CubeRoot.getDependentVariable(BigDecimal)' のためのテスト・メソッド.
   */
  @Test
  public final void testGetDependentVariable() {
    CubeRoot cubeRoot = new CubeRoot(DecimalFunctionTest.PRECISION);

    BigDecimal result = cubeRoot.getDependentVariable(new BigDecimal("-3"));
    System.out.println("cubeRoot-3= " + result);

    double doubleResult = Math.pow(-3.0d, 1.0d / 3.0d);
    System.out.println("Math.pow(-3,1/3)= " + doubleResult);

    BigDecimal result2 = cubeRoot.getDependentVariable(new BigDecimal("-1"));
    System.out.println("cubeRoot-1= " + result2);

    double doubleResult2 = Math.pow(-1.0d, 1.0d / 3.0d);
    System.out.println("Math.pow(-1,1/3)= " + doubleResult2);

    BigDecimal result3 = cubeRoot.getDependentVariable(new BigDecimal("1"));
    System.out.println("cubeRoot1= " + result3);

    double doubleResult3 = Math.pow(1.0d, 1.0d / 3.0d);
    System.out.println("Math.pow(1,1/3)= " + doubleResult3);

    BigDecimal result4 = cubeRoot.getDependentVariable(new BigDecimal("0"));
    System.out.println("cubeRoot0= " + result4);
    double doubleResult4 = Math.pow(0.0d, 1.0d / 3.0d);
    System.out.println("Math.pow(0,1/3)= " + doubleResult4);

    Assert.assertFalse(result.doubleValue() == doubleResult);
    Assert.assertFalse(result2.doubleValue() == doubleResult2);
    Assert.assertEquals(result3.doubleValue(), doubleResult3, 10e-324);
    Assert.assertEquals(result4.doubleValue(), doubleResult4, 10e-324);

  }

}
