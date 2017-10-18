/*
 * Created on 2004/07/12 $Id: ComplexVectorTest.java,v 1.1 2004/08/12 16:37:47
 * sugawara Exp $
 * 
 */
package name.sugawara.hiroshi.math.matrix;

import java.util.Arrays;

import junit.framework.TestCase;
import name.sugawara.hiroshi.math.complex.DoubleComplex;

/**
 * @author Hiroshi Sugawara
 * @version $Id: ComplexVectorTest.java 109 2010-06-13 04:26:48Z sugawara $
 *          Created Date : 2005/07/24 19:41:10
 * @date 2004/07/12
 */
public class ComplexVectorTest extends TestCase {

  /**
   * テスト用実数配列.
   * 
   * @since 2005/07/12 1:23:02
   */
  private static final double[]      REAL                = new double[]
                                                         { 1.22d, -2.9d, 7.35d, 6.28d };

  /**
   * テスト用虚数部配列.
   * 
   * @since 2005/07/12 1:22:45
   */
  private static final double[]      IMAGINARY           = new double[]
                                                         { 3.33d, -4.44d, 5.55d, 6.66d };

  /**
   * テスト用複素数ベクトル.
   * 
   * @since 2005/07/12 1:22:28
   */
  private static final ComplexVector v                   = new ComplexVector(REAL, IMAGINARY);

  /**
   * ベクトルのサイズ.
   * 
   * @since 2005/07/12 1:22:09
   */
  private static final int           N                   = 4;

  /**
   * ゼロベクトル. {@code ZERO} のコメント
   * 
   * @since 2005/01/02
   */
  private static final double[]      ZERO                = new double[N];

  /**
   * 単位ベクトル. {@code ONE} のコメント
   * 
   * @since 2005/01/02
   */
  private static final double[]      ONE                 = new double[N];

  static {
    Arrays.fill(ZERO, 0.0d);
    Arrays.fill(ONE, 1.0d);
  }

  /**
   * テスト用複素数ベクトル.
   * 
   * @since 2005/07/12 1:21:32
   */
  private ComplexVector              mutable;

  /**
   * テスト用複素数配列.
   * 
   * @since 2005/07/12 1:21:53
   * @uml.property name="mutableArray"
   * @uml.associationEnd multiplicity="(0 -1)"
   */
  private DoubleComplex[]            mutableArray;

  /**
   * サブ実数部配列. {@code SUB_REAL_ARRAY} のコメント
   * 
   * @since 2005/01/02
   */
  private static final double[]      SUB_REAL_ARRAY      = new double[]
                                                         { 9.99d, 11.11d };

  /**
   * サブ虚数部配列. {@code SUB_IMAGINARY_ARRAY} のコメント
   * 
   * @since 2005/01/02
   */
  private static final double[]      SUB_IMAGINARY_ARRAY = new double[]
                                                         { 9.99d, 11.11d };

  private static final ComplexVector SUB_VECTOR          = new ComplexVector(SUB_REAL_ARRAY,
                                                             SUB_IMAGINARY_ARRAY);

  private static final ComplexVector CV_ZERO             = new ComplexVector(SUB_REAL_ARRAY.length);

  private static final ComplexVector CV_ONE              = new ComplexVector(SUB_REAL_ARRAY.length,
                                                             new DoubleComplex());

  /**
   * セットアップ.
   * 
   * @throws Exception
   *           例外
   * 
   * @since 2004/08/04
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.mutable = new ComplexVector(REAL, IMAGINARY);
    this.mutableArray = new DoubleComplex[]
    {
        new DoubleComplex(1.22d, 3.33d),
        new DoubleComplex(-2.9d, -4.44d),
        new DoubleComplex(7.35d, 5.55d),
        new DoubleComplex(6.28d, 6.66d) };
  }

  /**
   * ティアーダウン.
   * 
   * @throws Exception
   *           例外.
   * @since 2004/08/04
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Constructor for ComplexVectorTest.
   * 
   * @param arg0
   *          引数.
   */
  public ComplexVectorTest(final String arg0) {
    super(arg0);
  }

  /**
   * Class under test for void ComplexVector(DoubleComplex[]).
   * 
   * @since 2004/08/04
   */
  public final void testComplexVectorDoubleComplexArray() {
    assertNotNull(new ComplexVector(REAL, IMAGINARY));
    assertNotNull(v);
    assertNotNull(this.mutable);
    assertNotNull(REAL);
    assertNotNull(IMAGINARY);
    assertNotNull(this.mutableArray);

    for (int i = 0; i < v.size(); i++) {
      assertTrue(this.mutable.get(i).equals(v.get(i)));
      assertEquals(this.mutable.get(i).getReal(), REAL[i], 0.0d);
      assertEquals(this.mutable.get(i).getImaginary(), IMAGINARY[i], 0.0d);
      assertTrue(this.mutable.get(i).equals(this.mutable.get(i)));
      assertTrue(this.mutable.get(i).equals(this.mutableArray[i]));
    }

    this.mutableArray[0] = new DoubleComplex(-100000.0d, 0.0d);

    for (int i = 0; i < v.size(); i++) {
      assertTrue(this.mutable.get(i).equals(v.get(i)));
      assertEquals(this.mutable.get(i).getReal(), REAL[i], 0.0d);
      assertEquals(this.mutable.get(i).getImaginary(), IMAGINARY[i], 0.0d);
      assertTrue(this.mutable.get(i).equals(this.mutable.get(i)));
      if (i == 0) {
        assertFalse(this.mutable.get(i) == this.mutableArray[i]);
      } else {
        assertEquals(this.mutable.get(i).getReal(), this.mutableArray[i].getReal(), 0.0d);
        assertEquals(this.mutable.get(i).getImaginary(), this.mutableArray[i].getImaginary(), 0.0d);
      }
    }

  }

  /**
   * Class under test for void ComplexVector(ComplexMatrix).
   * 
   * @since 2004/08/04
   */
  public final void testComplexVectorComplexMatrix() {
    // TODO Implement ComplexVector().
  }

  /**
   * Class under test for void ComplexVector(double[], double[]).
   * 
   * @since 2004/08/04
   */
  public final void testComplexVectordoubleArraydoubleArray() {
    // TODO Implement ComplexVector().
  }

  /**
   * Class under test for void ComplexVector(DoubleVector, DoubleVector).
   * 
   * @since 2004/08/04
   */
  public final void testComplexVectorDoubleVectorDoubleVector() {
    // TODO Implement ComplexVector().
  }

  /**
   * Class under test for void ComplexVector(int).
   * 
   * @since 2004/08/04
   */
  public final void testComplexVectorint() {
    // TODO Implement ComplexVector().
  }

  /**
   * Class under test for void ComplexVector(int, DoubleComplex).
   * 
   * @since 2004/08/04
   */
  public final void testComplexVectorintDoubleComplex() {
    // TODO Implement ComplexVector().
  }

  /**
   * abs()テスト.
   * 
   * @since 2004/08/04
   */
  public final void testAbs() {
    // TODO Implement abs().
  }

  /**
   * acos().
   * 
   * @since 2004/08/04
   */
  public final void testAcos() {
    // TODO Implement acos().
  }

  /**
   * Class under test for ComplexVector add(DoubleComplex).
   * 
   * @since 2004/08/04
   */
  public final void testAddDoubleComplex() {
    // TODO Implement add().
  }

  /**
   * Class under test for ComplexVector add(ComplexVector).
   * 
   * @since 2004/08/04
   */
  public final void testAddComplexVector() {
    // TODO Implement add().
  }

  /**
   * arg().
   * 
   * @since 2004/08/04
   */
  public final void testArg() {
    // TODO Implement arg().
  }

  /**
   * asin().
   * 
   * @since 2004/08/04
   */
  public final void testAsin() {
    // TODO Implement asin().
  }

  /**
   * Class under test for Object clone().
   * 
   * @since 2004/08/04
   */
  public final void testClone() {
    // TODO Implement clone().
  }

  /**
   * concatenate().
   * 
   * @since 2004/08/04
   */
  public final void testConcatenate() {
    // TODO Implement concatenate().
  }

  /**
   * conjugate().
   * 
   * @since 2004/08/04
   */
  public final void testConjugate() {
    // TODO Implement conjugate().
  }

  /**
   * convolute().
   * 
   * @since 2004/08/04
   */
  public final void testConvolute() {
    // TODO Implement convolute().
  }

  /**
   * Class under test for ComplexVector correlate().
   * 
   * @since 2004/08/04
   */
  public final void testCorrelate() {
    // TODO Implement correlate().
  }

  /**
   * Class under test for ComplexVector correlate(ComplexVector).
   * 
   * @since 2004/08/04
   */
  public final void testCorrelateComplexVector() {
    // TODO Implement correlate().
  }

  /**
   * cos().
   * 
   * @since 2004/08/04
   */
  public final void testCos() {
    // TODO Implement cos().
  }

  /**
   * cosh().
   * 
   * @since 2004/08/04
   */
  public final void testCosh() {
    // TODO Implement cosh().
  }

  /**
   * delete().
   * 
   * @since 2004/08/04
   */
  public final void testDelete() {
    // TODO Implement delete().
  }

  /**
   * Class under test for ComplexVector divide(DoubleComplex).
   * 
   * @since 2004/08/04
   */
  public final void testDivideDoubleComplex() {
    // TODO Implement divide().
  }

  /**
   * Class under test for ComplexVector divide(ComplexVector).
   * 
   * @since 2004/08/04
   */
  public final void testDivideComplexVector() {
    // TODO Implement divide().
  }

  /**
   * Class under test for boolean equals(Object).
   * 
   * @since 2004/08/04
   */
  public final void testEqualsObject() {
    // TODO Implement equals().
  }

  /**
   * exp().
   * 
   * @since 2004/08/04
   */
  public final void testExp() {
    // TODO Implement exp().
  }

  /**
   * fliplr().
   * 
   * @since 2004/08/04
   */
  public final void testFliplr() {
    // TODO Implement fliplr().
  }

  /**
   * get().
   * 
   * @since 2004/08/04
   */
  public final void testGet() {
    // TODO Implement get().
  }

  /**
   * getArray().
   * 
   * @since 2004/08/04
   */
  public final void testGetArray() {
    // TODO Implement getArray().
  }

  /**
   * getImaginary().
   * 
   * @since 2004/08/04
   */
  public final void testGetImaginary() {
    // TODO Implement getImaginary().
  }

  /**
   * getReal().
   * 
   * @since 2004/08/04
   */
  public final void testGetReal() {
    // TODO Implement getReal().
  }

  /**
   * Class under test for ComplexVector getVector(int, int)
   * 
   * @since 2004/08/04
   */
  public final void testGetVectorintint() {
    // TODO Implement getVector().
  }

  /**
   * Class under test for ComplexVector getVector(int[])
   * 
   * @since 2004/08/04
   */
  public final void testGetVectorintArray() {
    // TODO Implement getVector().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testInfinityNorm() {
    // TODO Implement infinityNorm().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testInnerProduct() {
    // TODO Implement innerProduct().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testInsert() {
    // TODO Implement insert().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testLog() {
    // TODO Implement log().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testLog10() {
    // TODO Implement log10().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testLog2() {
    // TODO Implement log2().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testMax() {
    // TODO Implement max().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testMean() {
    // TODO Implement mean().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testMin() {
    // TODO Implement min().
  }

  /**
   * Class under test for ComplexVector multiply(DoubleComplex)
   * 
   * @since 2004/08/04
   */
  public final void testMultiplyDoubleComplex() {
    // TODO Implement multiply().
  }

  /**
   * Class under test for ComplexVector multiply(ComplexVector)
   * 
   * @since 2004/08/04
   */
  public final void testMultiplyComplexVector() {
    // TODO Implement multiply().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testNegate() {
    // TODO Implement negate().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testNegativeInfinityNorm() {
    // TODO Implement negativeInfinityNorm().
  }

  /**
   * Class under test for double norm()
   * 
   * @since 2004/08/04
   */
  public final void testNorm() {
    // TODO Implement norm().
  }

  /**
   * Class under test for double norm(double)
   * 
   * @since 2004/08/04
   */
  public final void testNormdouble() {
    // TODO Implement norm().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testOneNorm() {
    // TODO Implement oneNorm().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testPow() {
    // TODO Implement pow().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testProduct() {
    // TODO Implement product().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testSet() {
    // TODO Implement set().
  }

  /**
   * Class under test for ComplexVector setVector(int, int, ComplexVector)
   * 
   * @since 2004/08/04
   */
  public final void testSetVectorintintComplexVector() {
    // TODO Implement setVector().
  }

  /**
   * Class under test for ComplexVector setVector(int[], ComplexVector)
   * 
   * @since 2004/08/04
   */
  public final void testSetVectorintArrayComplexVector() {
    // TODO Implement setVector().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testSin() {
    // TODO Implement sin().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testSinh() {
    // TODO Implement sinh().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testSize() {
    // TODO Implement size().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testSqrt() {
    // TODO Implement sqrt().
  }

  /**
   * Class under test for ComplexVector subtract(DoubleComplex)
   * 
   * @since 2004/08/04
   */
  public final void testSubtractDoubleComplex() {
    // TODO Implement subtract().
  }

  /**
   * Class under test for ComplexVector subtract(ComplexVector)
   */
  public final void testSubtractComplexVector() {
    // TODO Implement subtract().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testSum() {
    // TODO Implement sum().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testTan() {
    // TODO Implement tan().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testTanh() {
    // TODO Implement tanh().
  }

  /**
   * 
   * @since 2004/08/04
   */
  public final void testToMatrix() {
    // TODO Implement toMatrix().
  }

}