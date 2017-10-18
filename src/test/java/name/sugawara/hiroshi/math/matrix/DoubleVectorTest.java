/*
 * 作成日: 2003/09/24
 * 
 */
package name.sugawara.hiroshi.math.matrix;

import junit.framework.TestCase;
import java.util.Arrays;

/**
 * DoubleVectorテスト
 * 
 * @author Hiroshi Sugawara
 * @since 1.1
 * @version $Id: DoubleVectorTest.java 109 2010-06-13 04:26:48Z sugawara $
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public class DoubleVectorTest extends TestCase {

  /**
   * サンプル配列. {@code d} のコメント
   * 
   * @since 2005/01/24
   */
  private static final double[]     d                  = new double[]
                                                       { 1.22d, -2.9d, 7.35d, 6.28d };

  /**
   * サンプル配列から生成したサンプルベクトル. {@code v} のコメント
   * 
   * @since 2005/01/24
   */
  private static final DoubleVector v                  = new DoubleVector(d);

  /**
   * サンプルのベクトルサイズ. {@code N} のコメント
   * 
   * @since 2005/01/24
   */
  private static final int          N                  = 4;

  /**
   * 零ベクトルを生成するための配列. {@code ZERO} のコメント
   * 
   * @since 2005/01/24
   */
  private static final double[]     ZERO               = new double[N];

  /**
   * 単位ベクトルを生成するための配列. {@code ONE} のコメント
   * 
   * @since 2005/01/24
   */
  private static final double[]     ONE                = new double[N];

  /**
   * setSubVector()のテストに使用するサンプル配列. {@code SUB_ARRAY} のコメント
   * 
   * @since 2005/01/24
   */
  private static final double[]     SUB_ARRAY          = new double[]
                                                       { 9.99d, 11.11d };

  /**
   * setSubVector()のテストに使用するサンプルベクトル.
   * 
   * {@code SUB_VECTOR} のコメント
   * 
   * @since 2005/01/24
   */
  private static final DoubleVector SUB_VECTOR         = new DoubleVector(SUB_ARRAY);

  static {
    Arrays.fill(ZERO, 0.0d);
    Arrays.fill(ONE, 1.0d);
  }

  /**
   * 零ベクトル. {@code DOUBLE_VECTOR_ZERO} のコメント
   * 
   * @since 2005/01/24
   */
  private static final DoubleVector DOUBLE_VECTOR_ZERO = new DoubleVector(ZERO);

  /**
   * 単位ベクトル. {@code DOUBLE_VECTOR_ONE} のコメント
   * 
   * @since 2005/01/24
   */
  private static final DoubleVector DOUBLE_VECTOR_ONE  = new DoubleVector(ONE);

  /**
   * 可変ベクトル. {@code mutable} のコメント
   * 
   * @since 2005/01/24
   */
  private DoubleVector              mutable;

  /**
   * 可変配列. {@code mutableArray} のコメント
   * 
   * @since 2005/01/24
   */
  private double[]                  mutableArray;

  /**
   * Constructor for DoubleVectorTest.
   * 
   * @param arg0
   */
  public DoubleVectorTest(String arg0) {
    super(arg0);
  }

  /**
   * 可変ベクトルを初期化.
   * 
   * @see TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.mutable = new DoubleVector(d);
    this.mutableArray = new double[]
    { 1.22d, -2.9d, 7.35d, 6.28d };
  }

  /**
   * @see TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * void DoubleVector のテスト(double[])
   * 
   * @since 2004/08/03
   */
  final public void testDoubleVectordoubleArray() {
    assertNotNull(new DoubleVector(d));
    assertNotNull(v);
    assertNotNull(this.mutable);
    assertNotNull(d);
    assertNotNull(this.mutableArray);

    for (int i = 0; i < v.size(); i++) {
      assertEquals(this.mutable.get(i), v.get(i), 0.0d);
      assertEquals(this.mutable.get(i), d[i], 0.0d);
      assertEquals(this.mutable.get(i), this.mutable.get(i), 0.0d);
      assertEquals(this.mutable.get(i), this.mutableArray[i], 0.0d);
    }

    this.mutableArray[0] = -100000.0d;

    for (int i = 0; i < v.size(); i++) {
      assertEquals(this.mutable.get(i), v.get(i), 0.0d);
      assertEquals(this.mutable.get(i), d[i], 0.0d);
      assertEquals(this.mutable.get(i), this.mutable.get(i), 0.0d);
      if (i == 0) {
        assertFalse(this.mutable.get(i) == this.mutableArray[i]);
      } else {
        assertEquals(this.mutable.get(i), this.mutableArray[i], 0.0d);
      }
    }

  }

  /**
   * void DoubleVector のテスト(int, double)
   * 
   * @since 2004/08/03
   */
  final public void testDoubleVectorintdouble() {
    DoubleVector dv3 = new DoubleVector(3, 0.3d);
    double[] dv3Array = new double[]
    { 0.3d, 0.3d, 0.3d };
    assertNotNull(dv3);
    assertNotNull(dv3Array);

    for (int i = 0; i < dv3.size(); i++) {
      assertEquals(dv3.get(i), dv3Array[i], 0.0d);
      assertEquals(dv3.get(i), 0.3d, 0.0d);
      assertEquals(dv3.get(i), dv3.get(0), 0.0d);
      assertEquals(dv3.get(i), dv3Array[0], 0.0d);
    }

    dv3Array[2] = -100000.0d;

    for (int i = 0; i < dv3.size(); i++) {
      assertEquals(dv3.get(i), 0.3d, 0.0d);
      assertEquals(dv3.get(i), dv3.get(0), 0.0d);

      if (i == 2) {
        assertFalse(dv3.get(i) == dv3Array[i]);
        assertFalse(dv3.get(i) == dv3Array[2]);
      } else {
        assertEquals(dv3.get(i), dv3Array[i], 0.0d);
        assertEquals(dv3.get(i), dv3Array[0], 0.0d);
      }
    }

  }

  /**
   * void DoubleVector のテスト(int)
   * 
   * @since 2004/08/03
   */
  final public void testDoubleVectorint() {
    DoubleVector dvZero = new DoubleVector(10);
    double[] arrayZero = new double[]
    { 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d };
    assertNotNull(new DoubleVector(10));

    for (int i = 0; i < dvZero.size(); i++) {
      assertEquals(dvZero.get(i), arrayZero[i], 0.0d);
      assertEquals(dvZero.get(i), 0.0d, 0.0d);
      assertEquals(dvZero.get(i), dvZero.get(0), 0.0d);
      assertEquals(dvZero.get(i), arrayZero[0], 0.0d);
    }

    arrayZero[2] = -100000.0d;

    for (int i = 0; i < dvZero.size(); i++) {
      assertEquals(dvZero.get(i), 0.0d, 0.0d);
      assertEquals(dvZero.get(i), dvZero.get(0), 0.0d);

      if (i == 2) {
        assertFalse(dvZero.get(i) == arrayZero[i]);
        assertFalse(dvZero.get(i) == arrayZero[2]);
      } else {
        assertEquals(dvZero.get(i), arrayZero[i], 0.0d);
        assertEquals(dvZero.get(i), arrayZero[0], 0.0d);
      }
    }

  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testGetArray() {
    assertTrue(Arrays.equals(v.getArray(), d));

    for (int i = 0; i < v.size(); i++) {
      assertEquals(v.getArray()[i], d[i], 0.0d);
      assertEquals(v.get(i), d[i], 0.0d);
    }
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testGet() {
    assertEquals(v.get(0), 1.22d, 0.0d);
    assertEquals(v.get(1), -2.9d, 0.0d);
    assertEquals(v.get(2), 7.35d, 0.0d);
    assertEquals(v.get(3), 6.28d, 0.0d);

    for (int i = 0; i < v.size(); i++) {
      assertEquals(v.get(i), d[i], 0.0d);
    }
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testSize() {
    assertEquals(v.size(), 4);
    assertEquals(v.size(), d.length);
  }

  /**
   * boolean equals のテスト(Object)
   * 
   * @since 2004/08/03
   */
  final public void testEqualsObject() {
    assertTrue(v.equals(v));
    DoubleVector dv = new DoubleVector(d);
    assertTrue(v.equals(dv));
    assertTrue(dv.equals(v));
  }

  /**
   * DoubleVector add のテスト(DoubleVector)
   * 
   * @since 2004/08/03
   */
  final public void testAddDoubleVector() {
    assertTrue(v.add(DOUBLE_VECTOR_ZERO).equals(v));
    assertTrue(v.add(v).equals(v.add(v)));

    double[] sum = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      sum[i] = d[i] + 1;
    }

    DoubleVector v2 = v.add(DOUBLE_VECTOR_ONE);

    assertTrue(v2.equals(new DoubleVector(sum)));

    for (int i = 0; i < v2.size(); i++) {
      assertEquals(v2.get(i), sum[i], 0.0d);
    }
  }

  /**
   * DoubleVector add のテスト(double)
   * 
   * @since 2004/08/03
   */
  final public void testAdddouble() {
    assertTrue(v.add(0.0d).equals(v));
    assertTrue(v.add(0.0d).equals(v.add(DOUBLE_VECTOR_ZERO)));
    assertTrue(v.add(1.0d).equals(v.add(DOUBLE_VECTOR_ONE)));

    double[] sum = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      sum[i] = d[i] + 1.0d;
    }
    DoubleVector v2 = v.add(DOUBLE_VECTOR_ONE);
    assertTrue(v2.equals(new DoubleVector(sum)));

    for (int i = 0; i < v2.size(); i++) {
      assertEquals(v2.get(i), sum[i], 0.0d);
    }

  }

  /**
   * DoubleVector subtract のテスト(DoubleVector)
   * 
   * @since 2004/08/03
   */
  final public void testSubtractDoubleVector() {
    assertTrue(v.subtract(v).equals(DOUBLE_VECTOR_ZERO));

    assertTrue(v.subtract(DOUBLE_VECTOR_ZERO).equals(v));
    assertTrue(v.subtract(v).equals(v.subtract(v)));

    double[] sum = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      sum[i] = d[i] - 1;
    }

    DoubleVector v2 = v.subtract(DOUBLE_VECTOR_ONE);

    assertTrue(v2.equals(new DoubleVector(sum)));

    for (int i = 0; i < v2.size(); i++) {
      assertEquals(v2.get(i), sum[i], 0.0d);
    }

  }

  /**
   * DoubleVector subtract のテスト(double)
   * 
   * @since 2004/08/03
   */
  final public void testSubtractdouble() {
    assertTrue(v.subtract(0.0d).equals(v));
    assertTrue(v.subtract(0.0d).equals(v));
    assertTrue(v.subtract(0.0d).equals(v.subtract(DOUBLE_VECTOR_ZERO)));
    assertTrue(v.subtract(1.0d).equals(v.subtract(DOUBLE_VECTOR_ONE)));

    double[] sum = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      sum[i] = d[i] - 1.0d;
    }
    DoubleVector v2 = v.subtract(DOUBLE_VECTOR_ONE);
    assertTrue(v2.equals(new DoubleVector(sum)));

    for (int i = 0; i < v2.size(); i++) {
      assertEquals(v2.get(i), sum[i], 0.0d);
    }

  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testSum() {
    double sum = 0.0d;

    for (int i = 0; i < v.size(); i++) {
      sum += v.get(i);
    }
    assertEquals(v.sum(), sum, 0.0d);
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testProduct() {
    double product = v.get(0);

    for (int i = 1; i < v.size(); i++) {
      product *= v.get(i);
    }
    assertEquals(v.product(), product, 0.0d);

  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testAvg() {
    assertEquals(v.avg(), v.sum() / v.size(), 0.0d);

    double average = 0.0d;
    for (int i = 0; i < v.size(); i++) {
      average += v.get(i);

    }

    assertEquals(average / v.size(), v.avg(), 0.0d);

  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testInnerProduct() {
    assertEquals(v.innerProduct(v), v.multiply(v).sum(), 0.0d);

    double[] array = new double[v.size()];
    double result = 0.0d;
    for (int i = 0; i < array.length; i++) {
      result += v.get(i) * v.get(i);
    }

    assertEquals(result, v.innerProduct(v), 0.0d);

  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testFliplr() {
    assertFalse(v.fliplr().equals(v));
    assertTrue(v.fliplr().fliplr().equals(v));
    for (int i = 0; i < v.size(); i++) {
      assertEquals(v.fliplr().get(i), v.get(v.size() - 1 - i), 0.0d);
    }
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testMax() {
    assertEquals(v.max(), 7.35d, 0.0d);
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testMin() {
    assertEquals(v.min(), -2.9d, 0.0d);
  }

  /**
   * DoubleVector divide のテスト(DoubleVector)
   * 
   * @since 2004/08/03
   */
  final public void testDivideDoubleVector() {

    for (int i = 0; i < v.size(); i++) {
      System.out.println(DOUBLE_VECTOR_ONE.get(i));
      assertEquals(v.divide(v).get(i), DOUBLE_VECTOR_ONE.get(i), 0.0d);
    }
  }

  /**
   * DoubleVector divide のテスト(double)
   * 
   * @since 2004/08/03
   */
  final public void testDividedouble() {
    assertTrue(v.divide(1.0d).equals(v));
  }

  /**
   * DoubleVector multiply のテスト(DoubleVector)
   * 
   * @since 2004/08/03
   */
  final public void testMultiplyDoubleVector() {
    for (int i = 0; i < v.size(); i++) {
      System.out.println(DOUBLE_VECTOR_ONE.get(i));

      assertEquals(v.multiply(DOUBLE_VECTOR_ONE).get(i), v.get(i), 0.0d);
    }

    System.out.println("v = " + v);
    System.out.println("one = " + DOUBLE_VECTOR_ONE);
    System.out.println("zero = " + DOUBLE_VECTOR_ZERO);
    System.out.println("v*zero = " + v.multiply(DOUBLE_VECTOR_ZERO));

    
    assertTrue(v.multiply(DOUBLE_VECTOR_ONE).equals(v));
    assertTrue(v.multiply(DOUBLE_VECTOR_ZERO).abs().equals(DOUBLE_VECTOR_ZERO));
  }

  /**
   * DoubleVector multiply のテスト(double)
   * 
   * @since 2004/08/03
   */
  final public void testMultiplydouble() {
    assertTrue(v.multiply(1.0d).equals(v));
    assertTrue(v.multiply(0.0d).abs().equals(DOUBLE_VECTOR_ZERO));
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testPow() {
    assertTrue(v.pow(1.0d).equals(v));
    assertTrue(v.pow(0.0d).equals(DOUBLE_VECTOR_ONE));
    assertTrue(v.pow(-1.0d).equals(DOUBLE_VECTOR_ONE.divide(v)));
    assertTrue(v.pow(2.0d).equals(v.multiply(v)));

    System.out.println("testPow");
    DoubleVector v05 = v.pow(0.5d);
    DoubleVector vsqrt = v.sqrt();
    for (int i = 0; i < v.size(); i++) {
      System.out.println(v05.get(i) + " " + vsqrt.get(i));

      if (Double.isNaN(v05.get(i)) || Double.isNaN(vsqrt.get(i))) {
        assertTrue(Double.isNaN(v05.get(i)));
        assertTrue(Double.isNaN(vsqrt.get(i)));
      } else {
        assertEquals(v.pow(0.5d).get(i), v.sqrt().get(i), 0.0d);
      }
    }
    System.out.println("testPow - end");
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testSqrt() {
    for (int i = 0; i < v.size(); i++) {
      if (v.sqrt().get(i) > 0 || v.get(i) > 0) {

        assertEquals(v.sqrt().get(i), Math.sqrt(v.get(i)), 0.0d);
      } else {
        assertTrue(Double.isNaN(v.sqrt().get(i)));
        assertTrue(Double.isNaN(Math.sqrt(v.get(i))));

      }
    }
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testAbs() {
    double minusD[] = new double[]
    { -1.0d, 1.1d, -1.0d };
    double d[] = new double[]
    { 1.0d, 1.1d, 1.0d };

    DoubleVector minusDM = new DoubleVector(minusD);
    DoubleVector dM = new DoubleVector(d);
    assertFalse(minusDM.equals(dM));
    assertTrue(minusDM.abs().equals(dM));
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testLog() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.log(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.log().get(i) + " ");
      System.out.print(Math.log(v.get(i)) + "  ");
      if (v.get(i) < 0) {
        assertTrue(Double.isNaN(v.log().get(i)));
        assertTrue(Double.isNaN(Math.log(v.get(i))));
      } else {
        assertEquals(v.log().get(i), Math.log(v.get(i)), 0.0d);
      }
    }
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testSin() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.sin(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.sin().get(i) + " ");
      System.out.print(Math.sin(v.get(i)) + "  ");
      assertEquals(v.sin().get(i), Math.sin(v.get(i)), 0.0d);
    }
    System.out.println();

  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testCos() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.cos(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.cos().get(i) + " ");
      System.out.print(Math.cos(v.get(i)) + "  ");
      assertEquals(v.cos().get(i), Math.cos(v.get(i)), 0.0d);
    }
    System.out.println();
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testTan() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.tan(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.tan().get(i) + " ");
      System.out.print(Math.tan(v.get(i)) + "  ");
      assertEquals(v.tan().get(i), Math.tan(v.get(i)), 0.0d);
    }
    System.out.println();
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testAsin() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.asin(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.asin().get(i) + " ");
      System.out.print(Math.asin(v.get(i)) + "  ");
      if (v.get(i) < -1 || 1 < v.get(i)) {
        assertTrue(Double.isNaN(v.asin().get(i)));
        assertTrue(Double.isNaN(Math.asin(v.get(i))));
      } else {
        assertEquals(v.asin().get(i), Math.asin(v.get(i)), 0.0d);
      }
    }
    System.out.println();
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testAcos() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.acos(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.acos().get(i) + " ");
      System.out.print(Math.acos(v.get(i)) + "  ");
      if (v.get(i) < -1 || 1 < v.get(i)) {
        assertTrue(Double.isNaN(v.acos().get(i)));
        assertTrue(Double.isNaN(Math.acos(v.get(i))));
      } else {
        assertEquals(v.acos().get(i), Math.acos(v.get(i)), 0.0d);
      }
    }
    System.out.println();
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testAtan() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.atan(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.atan().get(i) + " ");
      System.out.print(Math.atan(v.get(i)) + "  ");
      assertEquals(v.atan().get(i), Math.atan(v.get(i)), 0.0d);
    }
    System.out.println();
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testAtan2() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.atan2(v.get(i), v.fliplr().get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.atan2(v.fliplr()).get(i) + " ");
      System.out.print(Math.atan2(v.get(i), v.fliplr().get(i)) + "  ");
      assertEquals(v.atan2(v.fliplr()).get(i), Math.atan2(v.get(i), v.fliplr().get(i)), 0.0d);
    }
    System.out.println();
  }

  /**
   * 
   * @since 2004/08/03
   */
  final public void testExp() {
    double[] c = new double[v.size()];
    for (int i = 0; i < v.size(); i++) {
      c[i] = Math.exp(v.get(i));
    }

    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.exp().get(i) + " ");
      System.out.print(Math.exp(v.get(i)) + "  ");
      assertEquals(v.exp().get(i), Math.exp(v.get(i)), 0.0d);
    }
    System.out.println();
  }

  /**
   * double norm のテスト(double)
   * 
   * @since 2004/08/04
   */
  final public void testNormdouble() {
    // double exponent = 1.1d;
    for (double i = -10.00d; i < 10.00d; i += 0.01d) {
      if (i == 0.0d) {
        // blank
      } else {
        assertEquals(v.norm(i), Math.pow(v.abs().pow(i).sum(), 1.0d / i), 0.0d);
      }
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testOneNorm() {
    assertEquals(v.oneNorm(), v.abs().sum(), 0.0d);
  }

  /**
   * double norm のテスト()
   * 
   * @since 2004/08/04
   */
  final public void testNorm() {
    DoubleVector v2 = v.abs();
    assertEquals(v.norm(), Math.sqrt(v2.multiply(v2).sum()), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testInfinityNorm() {
    assertEquals(v.infinityNorm(), v.abs().max(), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testNegativeInfinityNorm() {
    assertEquals(v.negativeInfinityNorm(), v.abs().min(), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testConvolute() {
    double[] d = new double[]
    { 1.4884, -7.0760, 26.3440, -27.3068, 17.5985, 92.3160, 39.4384 };
    DoubleVector dv = new DoubleVector(d);
    for (int i = 0; i < dv.size(); i++) {
      assertEquals(v.convolute(v).get(i), dv.get(i), 10e-3);
    }

    double[] one = new double[]
    { 1.0d };
    DoubleVector dVone = new DoubleVector(one);
    assertEquals(dVone.convolute(dVone).get(0), one[0], 0.0d);
    for (int i = 0; i < dVone.size(); i++) {
      assertEquals(v.convolute(dVone).get(i), v.get(i), 10e-3);
    }
    // assertEquals(v.convolute(v));
  }

  /**
   * DoubleVector correlate のテスト(DoubleVector)
   * 
   * @since 2004/08/04
   */
  final public void testCorrelateDoubleVector() {
    double[] d = new double[]
    { 7.6616d, -9.2450d, 21.3050d, 103.3593d, 21.3050d, -9.2450d, 7.6616 };
    DoubleVector dv = new DoubleVector(d);
    for (int i = 0; i < dv.size(); i++) {
      assertEquals(v.correlate(v).get(i), dv.get(i), 10e-3);
    }
  }

  /**
   * DoubleVector correlate のテスト()
   * 
   * @since 2004/08/04
   */
  final public void testCorrelate() {
    double[] d = new double[]
    { 7.6616d, -9.2450d, 21.3050d, 103.3593d, 21.3050d, -9.2450d, 7.6616 };
    DoubleVector dv = new DoubleVector(d);
    for (int i = 0; i < dv.size(); i++) {
      assertEquals(v.correlate().get(i), dv.get(i), 10e-3);
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testToMatrix() {
    assertEquals(v.toMatrix().get(0, 0), v.get(0), 0.0d);
    assertEquals(v.toMatrix().get(0, 1), v.get(1), 0.0d);
    assertEquals(v.toMatrix().get(0, 2), v.get(2), 0.0d);
    assertEquals(v.toMatrix().get(0, 3), v.get(3), 0.0d);
  }

  /**
   * Object clone のテスト()
   * 
   * @since 2004/08/04
   */
  final public void testClone() {
    DoubleVector a = v;
    DoubleVector c = v;
    DoubleVector c2 = c;

    assertTrue(a.equals(v));
    assertTrue(c2.equals(a));
    assertTrue(c.equals(a));

    assertTrue(c.equals(v));
    assertTrue(c2.equals(v));
    assertTrue(c.equals(c2));

    System.out.println(a.get(0));
    System.out.println(a.get(1));
    System.out.println(a.get(2));
    System.out.println(a.get(3));
    System.out.println(c.get(0));
    System.out.println(c.get(1));
    System.out.println(c.get(2));
    System.out.println(c.get(3));
    System.out.println(c2.get(0));
    System.out.println(c2.get(1));
    System.out.println(c2.get(2));
    System.out.println(c2.get(3));
    System.out.println(v.get(0));
    System.out.println(v.get(1));
    System.out.println(v.get(2));
    System.out.println(v.get(3));

    System.out.println("DOUBLE_VECTOR_ZERO");

    c = DOUBLE_VECTOR_ZERO;

    assertTrue(c2.equals(v));
    assertTrue(a.equals(v));
    assertTrue(c2.equals(a));

    assertFalse(c.equals(v));
    assertFalse(c.equals(c2));
    assertFalse(c.equals(a));

    System.out.println(a.get(0));
    System.out.println(a.get(1));
    System.out.println(a.get(2));
    System.out.println(a.get(3));
    System.out.println(c.get(0));
    System.out.println(c.get(1));
    System.out.println(c.get(2));
    System.out.println(c.get(3));
    System.out.println(c2.get(0));
    System.out.println(c2.get(1));
    System.out.println(c2.get(2));
    System.out.println(c2.get(3));
    System.out.println(v.get(0));
    System.out.println(v.get(1));
    System.out.println(v.get(2));
    System.out.println(v.get(3));

    System.out.println("clone");

    DoubleVector c3 = null;
    try {
      c3 = (DoubleVector) c.clone();
      System.out.println(c3.get(0));
      System.out.println(c3.get(1));
      System.out.println(c3.get(2));
      System.out.println(c3.get(3));
    } catch (CloneNotSupportedException e) {
      System.out.println(e);
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }

    assertFalse(c.equals(v));
    assertTrue(c2.equals(v));
    assertTrue(c.equals(c));

  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testNegate() {
    assertTrue(v.negate().equals(DOUBLE_VECTOR_ZERO.subtract(v)));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testSet() {
    DoubleVector v2 = v.set(1, 10.0d);
    double[] d2 = d.clone();
    d2[1] = 10.0d;

    System.out.println("set");

    System.out.println(v2.get(0));
    System.out.println(v2.get(1));
    System.out.println(v2.get(2));
    System.out.println(v2.get(3));

    System.out.println(new DoubleVector(d2).get(0));
    System.out.println(new DoubleVector(d2).get(1));
    System.out.println(new DoubleVector(d2).get(2));
    System.out.println(new DoubleVector(d2).get(3));

    assertEquals(new DoubleVector(d2).get(0), v2.get(0), 0.0d);
    assertEquals(new DoubleVector(d2).get(1), v2.get(1), 0.0d);
    assertEquals(new DoubleVector(d2).get(2), v2.get(2), 0.0d);
    assertEquals(new DoubleVector(d2).get(3), v2.get(3), 0.0d);
    assertTrue(new DoubleVector(d2).equals(v2));

  }

  /**
   * DoubleVector setVector のテスト(int, int, DoubleVector)
   * 
   * @since 2004/08/04
   */
  final public void testSetVectorintintDoubleVector() {
    DoubleVector setVector = v.setVector(1, 2, SUB_VECTOR);
    assertNotNull(SUB_VECTOR);
    assertNotNull(v);
    assertEquals(setVector.get(1), SUB_VECTOR.get(0), 0.0d);
    assertEquals(setVector.get(2), SUB_VECTOR.get(1), 0.0d);
    assertFalse(setVector.equals(v));
    assertEquals(setVector.get(0), v.get(0), 0.0d);
    assertFalse(setVector.get(1) == v.get(1));
    assertFalse(setVector.get(2) == v.get(2));
    assertEquals(setVector.get(3), v.get(3), 0.0d);
  }

  /**
   * DoubleVector setVector のテスト(int[], DoubleVector)
   * 
   * @since 2004/08/04
   */
  final public void testSetVectorintArrayDoubleVector() {

    int[] index = new int[]
    { 1, 3 };

    DoubleVector setVector = v.setVector(index, SUB_VECTOR);
    assertNotNull(SUB_VECTOR);
    assertNotNull(v);
    assertEquals(setVector.get(1), SUB_VECTOR.get(0), 0.0d);
    assertEquals(setVector.get(3), SUB_VECTOR.get(1), 0.0d);
    assertFalse(setVector.equals(v));
    assertEquals(setVector.get(0), v.get(0), 0.0d);
    assertFalse(setVector.get(1) == v.get(1));
    assertEquals(setVector.get(2), v.get(2), 0.0d);
    assertFalse(setVector.get(3) == v.get(3));
  }

  /**
   * DoubleVector getVector のテスト(int, int)
   * 
   * @since 2004/08/04
   */
  final public void testGetVectorintint() {
    double[] getV = v.getVector(1, 2).getArray();
    assertEquals(getV.length, 2);
    assertEquals(getV[0], -2.9d, 0.0d);
    assertEquals(getV[1], 7.35d, 0.0d);
  }

  /**
   * DoubleVector getVector のテスト(int[])
   * 
   * @since 2004/08/04
   */
  final public void testGetVectorintArray() {
    int[] i = new int[2];
    i[0] = 1;
    i[1] = 3;
    double[] getV = v.getVector(i).getArray();
    assertEquals(getV.length, 2);
    assertEquals(getV[0], -2.9d, 0.0d);
    assertEquals(getV[1], 6.28d, 0.0d);
  }

}