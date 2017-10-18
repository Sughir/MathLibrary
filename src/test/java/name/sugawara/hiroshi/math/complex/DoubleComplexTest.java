/*
 * Created on 2003/08/05
 * 
 */
package name.sugawara.hiroshi.math.complex;

import junit.framework.TestCase;

/**
 * @author sugawara
 * @version $Id: DoubleComplexTest.java 102 2007-04-10 06:27:03Z sugawara $
 * @since 1.1
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */

public class DoubleComplexTest extends TestCase {

  /**
   * 
   * @uml.property name="x0" changeability="frozen"
   */
  private static final double x0 = 1.0d;

  /**
   * 
   * @uml.property name="y0" changeability="frozen"
   */
  private static final double y0 = 2.0d;

  /**
   * 
   * @uml.property name="x1" changeability="frozen"
   */
  private static final double x1 = 3.0d;

  /**
   * 
   * @uml.property name="y1" changeability="frozen"
   */
  private static final double y1 = 4.0d;

  /**
   * 
   * @uml.property name="pi" changeability="frozen"
   */
  private static final double pi = Math.PI;

  /**
   * 
   * @uml.property name="N" changeability="frozen"
   */
  private static final int    N  = 10;

  /**
   * 
   * @uml.property name="one"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleComplex       one;

  /**
   * 
   * @uml.property name="complex0"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleComplex       complex0;

  /**
   * 
   * @uml.property name="complexImag"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleComplex       complexImag;

  /**
   * 
   * @uml.property name="complex1"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleComplex       complex1;

  /**
   * 
   * @uml.property name="complex2"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleComplex       complex2;

  /**
   * @uml.property name="arrayR" multiplicity="(0 -1)"
   */
  private double[]            arrayR;

  /**
   * @uml.property name="arrayI" multiplicity="(0 -1)"
   */
  private double[]            arrayI;

  /**
   * @uml.property name="complexArray"
   * @uml.associationEnd multiplicity="(0 -1)"
   */
  private DoubleComplex[]     complexArray;

  /**
   * Constructor for DoubleComplexTest.
   * 
   * @param arg0
   */
  public DoubleComplexTest(String arg0) {
    super(arg0);
  }

  /*
   * @see TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.one = new DoubleComplex(1.0d, 0.0d);
    this.complex0 = new DoubleComplex();
    this.complexImag = new DoubleComplex(x0);
    this.complex1 = new DoubleComplex(x0, y0);
    this.complex2 = new DoubleComplex(x1, y1);

    this.arrayR = new double[N];
    this.arrayI = new double[N];

    this.complexArray = new DoubleComplex[this.arrayR.length];

    for (int i = 0; i < this.arrayR.length; i++) {
      this.arrayR[i] = i;
      this.arrayI[i] = -i;
    }

    for (int i = 0; i < this.arrayR.length; i++) {
      this.complexArray[i] = new DoubleComplex(this.arrayR[i], this.arrayI[i]);
      assertNotNull(this.complexArray[i]);
    }

    assertNotNull(this.complex0);
    assertNotNull(this.complexImag);
    assertNotNull(this.complex1);
    assertNotNull(this.complex2);
    assertNotNull(this.complexArray);
    assertNotNull(this.arrayR);
    assertNotNull(this.arrayI);

  }

  /*
   * @see TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testDoubleValue() {
    assertEquals(this.complex1.doubleValue(), Math.hypot(x0, y0), 0.0d);
    assertEquals(this.complex2.doubleValue(), Math.hypot(x1, y1), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testFloatValue() {
    assertEquals(this.complex1.floatValue(), (float) Math.hypot(x0, y0), 0.0d);
    assertEquals(this.complex2.floatValue(), (float) Math.hypot(x1, y1), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testIntValue() {
    assertEquals(this.complex1.intValue(), (int) Math.hypot(x0, y0));
    assertEquals(this.complex2.intValue(), (int) Math.hypot(x1, y1));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testLongValue() {
    assertEquals(this.complex1.longValue(), (long) Math.hypot(x0, y0));
    assertEquals(this.complex2.longValue(), (long) Math.hypot(x1, y1));
  }

  /**
   * void DoubleComplex のテスト()
   * 
   * @since 2004/08/04
   */
  final public void testComplex() {
    assertEquals(this.complex0.getReal(), 0.0d, 0.0d);
    assertEquals(this.complex0.getImaginary(), 0.0d, 0.0d);
  }

  /**
   * void DoubleComplex のテスト(double)
   * 
   * @since 2004/08/04
   */
  final public void testComplexdouble() {
    assertEquals(this.complexImag.getReal(), 0.0d, 0.0d);
    assertEquals(this.complexImag.getImaginary(), x0, 0.0d);
  }

  /**
   * void DoubleComplex のテスト(double, double)
   * 
   * @since 2004/08/04
   */
  final public void testComplexdoubledouble() {
    assertEquals(this.complex1.getReal(), x0, 0.0d);
    assertEquals(this.complex1.getImaginary(), y0, 0.0d);
    assertEquals(this.complex2.getReal(), x1, 0.0d);
    assertEquals(this.complex2.getImaginary(), y1, 0.0d);

  }

  /**
   * boolean equals のテスト(Object)
   * 
   * @since 2004/08/04
   */
  final public void testEqualsObject() {
    assertTrue(this.complex1.equals(this.complex1));
    DoubleComplex complex1temp = new DoubleComplex(x0, y0);
    assertNotNull(complex1temp);
    assertEquals(this.complex1.getReal(), complex1temp.getReal(), 0.0d);
    assertEquals(this.complex1.getImaginary(), complex1temp.getImaginary(), 0.0d);

    assertTrue(this.complex1.equals(complex1temp));
    assertTrue(complex1temp.equals(this.complex1));
    DoubleComplex c = null;

    assertNull(c);
    assertFalse(this.complex1.equals(c));
    DoubleComplex c2 = new DoubleComplex();
    DoubleComplex c3 = new DoubleComplex();
    assertNotNull(c2);
    assertNotNull(c3);
    assertTrue(c2.equals(c2));
    assertTrue(c3.equals(c3));
    assertTrue(c2.equals(c3));
    assertTrue(c3.equals(c2));
    assertFalse(this.complex1.equals(this.complex2));
    assertFalse(this.complex2.equals(this.complex1));
  }

  /**
   * int compareTo のテスト(Object)
   * 
   * @since 2004/08/04
   */
  final public void testCompareToObject() {
    assertEquals(this.complex0.compareTo(this.complex0), 0);
    assertEquals(this.complex0.compareTo(this.complex1), -1);
    assertEquals(this.complex1.compareTo(this.complex0), 1);
  }

  /**
   * int compareTo のテスト(DoubleComplex)
   * 
   * @since 2004/08/04
   */
  final public void testCompareToComplex() {
    assertEquals(this.complex0.compareTo(this.complex0), 0);
    assertEquals(this.complex0.compareTo(this.complex1), -1);
    assertEquals(this.complex1.compareTo(this.complex0), 1);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testGetReal() {
    assertEquals(this.complex0.getReal(), 0.0d, 0.0d);
    assertEquals(this.complex1.getReal(), x0, 0.0d);
    assertEquals(this.complex2.getReal(), x1, 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testGetImaginary() {
    assertEquals(this.complex0.getImaginary(), 0.0d, 0.0d);
    assertEquals(this.complex1.getImaginary(), y0, 0.0d);
    assertEquals(this.complex2.getImaginary(), y1, 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testSwap() {
    assertEquals(this.complex1.swap().getReal(), this.complex1.getImaginary(), 0.0d);
    assertEquals(this.complex2.swap().getReal(), this.complex2.getImaginary(), 0.0d);
    assertEquals(this.complex1.getReal(), this.complex1.swap().getImaginary(), 0.0d);
    assertEquals(this.complex2.getReal(), this.complex2.swap().getImaginary(), 0.0d);

    assertTrue(this.complex0.equals(this.complex0.swap()));
    assertTrue(this.complex2.equals(this.complex2.swap().swap()));
    assertTrue(this.complex1.equals(this.complex1.swap().swap()));
    assertTrue(this.complex2.equals(this.complex2.swap().swap()));
    assertFalse(this.complex1.equals(this.complex1.swap()));
    assertFalse(this.complex2.equals(this.complex2.swap()));

    assertTrue(this.complex0.equals(this.complex0.swap()));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testNegate() {
    assertTrue(this.complex0.negate().equals(this.complex0));
    assertFalse(this.complex1.negate().equals(this.complex1));
    assertFalse(this.complex1.negate().equals(new DoubleComplex(y0, x0)));
    assertFalse(this.complex2.negate().equals(this.complex2));
    assertFalse(this.complex2.negate().equals(new DoubleComplex(y1, x1)));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testAdd() {
    assertTrue(this.complex0.add(this.complex1).equals(this.complex1));
    assertTrue(this.complex1.add(this.complex0).equals(this.complex1));
    assertTrue(this.complex1.add(this.complex1).equals(new DoubleComplex((x0 * 2), (y0 * 2))));
    assertTrue(this.complex1.add(this.complex2).equals(new DoubleComplex((x0 + x1), (y0 + y1))));
    assertTrue(this.complex1.add(this.complex2).equals(this.complex2.add(this.complex1)));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testSubtract() {
    assertTrue(this.complex0.subtract(this.complex1).equals(this.complex1.negate()));
    assertTrue(this.complex1.subtract(this.complex0).equals(this.complex1));
    assertTrue(this.complex1.subtract(this.complex1).equals(this.complex0));
    assertTrue(this.complex1.subtract(this.complex2)
        .equals(new DoubleComplex((x0 - x1), (y0 - y1))));

    assertTrue(this.complex1.subtract(this.complex2).equals(
        this.complex2.subtract(this.complex1).negate()));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testMultiply() {
    assertTrue(this.complex0.multiply(this.complex0).equals(this.complex0));
    assertTrue(this.complex1.multiply(this.complex0).equals(this.complex0));
    assertTrue(this.complex0.multiply(this.complex1).equals(this.complex0));
    assertTrue(this.complex1.multiply(this.complex1).equals(
        new DoubleComplex(x0 * x0 - y0 * y0, 2 * x0 * y0)));
    assertTrue(this.complex1.multiply(this.complex2).equals(
        new DoubleComplex(x0 * x1 - y0 * y1, x0 * y1 + y0 * x1)));

    assertTrue(this.complex1.multiply(this.complex2).equals(this.complex2.multiply(this.complex1)));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testDivide() {
    assertTrue(Double.isNaN(this.complex0.divide(this.complex0).getReal()));
    assertTrue(Double.isNaN(this.complex0.divide(this.complex0).getImaginary()));
    assertTrue(Double.isNaN(this.complex1.divide(this.complex0).getReal()));
    assertTrue(Double.isNaN(this.complex1.divide(this.complex0).getImaginary()));
    assertTrue(this.complex0.divide(this.complex1).equals(this.complex0));
    assertTrue(this.complex1.divide(this.complex1).equals(this.one));

    double d = x1 * x1 + y1 * y1;
    assertTrue(this.complex1.divide(this.complex2).equals(
        new DoubleComplex((x0 * x1 + y0 * y1) / d, (y0 * x1 - x0 * y1) / d)));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testConjugate() {
    assertTrue(this.complex0.conjugate().equals(this.complex0));
    assertTrue(this.complex1.conjugate().equals(new DoubleComplex(x0, -y0)));
    assertTrue(this.complex2.conjugate().equals(new DoubleComplex(x1, -y1)));

    assertTrue(this.complex1.multiply(this.complex1.conjugate()).equals(
        new DoubleComplex(x0 * x0 + y0 * y0, 0.0d)));

    assertTrue(this.complex2.multiply(this.complex2.conjugate()).equals(
        new DoubleComplex(x1 * x1 + y1 * y1, 0.0d)));

  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testAbs() {
    assertEquals(this.complex0.abs(), 0.0d, 0.0d);
    assertEquals(this.complex1.abs(), Math.hypot(x0, y0), 0.0d);
    assertEquals(this.complex2.abs(), Math.hypot(x1, y1), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testArg() {
    assertEquals(this.complex0.arg(), 0.0d, 0.0d);
    assertEquals(this.complex1.arg(), Math.atan2(y0, x0), 0.0d);
    assertEquals(this.complex2.arg(), Math.atan2(y1, x1), 0.0d);

    double oneD = 1.0d, zeroD = 0.0d;
    DoubleComplex a1 = new DoubleComplex(oneD, zeroD);
    assertEquals(a1.arg(), Math.atan2(zeroD, oneD), 0.0d);

    DoubleComplex a2 = new DoubleComplex(oneD, oneD);
    assertEquals(a2.arg(), Math.atan2(oneD, oneD), 0.0d);

    DoubleComplex a3 = new DoubleComplex(zeroD, oneD);
    assertEquals(a3.arg(), Math.atan2(oneD, zeroD), 0.0d);

    DoubleComplex a4 = new DoubleComplex(-oneD, oneD);
    assertEquals(a4.arg(), Math.atan2(oneD, -oneD), 0.0d);

    DoubleComplex a5 = new DoubleComplex(-oneD, zeroD);
    assertEquals(a5.arg(), Math.atan2(zeroD, -oneD), 0.0d);

    DoubleComplex a6 = new DoubleComplex(-oneD, -oneD);
    assertEquals(a6.arg(), Math.atan2(-oneD, -oneD), 0.0d);

    DoubleComplex a7 = new DoubleComplex(zeroD, -oneD);
    assertEquals(a7.arg(), Math.atan2(-oneD, zeroD), 0.0d);

    DoubleComplex a8 = new DoubleComplex(oneD, -oneD);
    assertEquals(a8.arg(), Math.atan2(-oneD, oneD), 0.0d);
  }

  /**
   * DoubleComplex pow のテスト(int)
   * 
   * @since 2004/08/04
   */
  final public void testPowint() {

    assertTrue(this.complex0.pow(0).equals(this.one));
    assertTrue(this.complex0.pow(1).equals(this.complex0));
    assertTrue(this.complex0.pow(2).equals(this.complex0));
    assertTrue(this.complex0.pow(3).equals(this.complex0));
    assertTrue(this.complex0.pow(4).equals(this.complex0));

    assertTrue(Double.isNaN(this.complex0.pow(-1).getReal()));
    assertTrue(Double.isNaN(this.complex0.pow(-1).getImaginary()));

    assertTrue(Double.isNaN(this.complex0.pow(-2).getReal()));
    assertTrue(Double.isNaN(this.complex0.pow(-2).getImaginary()));
    assertTrue(Double.isNaN(this.complex0.pow(-3).getReal()));
    assertTrue(Double.isNaN(this.complex0.pow(-3).getImaginary()));
    assertTrue(Double.isNaN(this.complex0.pow(-4).getReal()));
    assertTrue(Double.isNaN(this.complex0.pow(-4).getImaginary()));

    assertEquals(this.complex1.pow(-2).getReal(), -0.12d, 10e-17);
    assertEquals(this.complex1.pow(-2).getImaginary(), -0.16d, 10e-17);

    assertTrue(this.complex1.pow(-1).equals(new DoubleComplex(0.2d, -0.4d)));
    assertTrue(this.complex1.pow(0).equals(this.one));
    assertTrue(this.complex1.pow(1).equals(this.complex1));
    assertTrue(this.complex1.pow(2).equals(new DoubleComplex(-3.0d, 4.0d)));
    assertTrue(this.complex1.pow(3).equals(new DoubleComplex(-11.0d, -2.0d)));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testSqrt() {
    assertTrue(this.complex0.sqrt().equals(this.complex0));
    assertTrue(this.one.sqrt().equals(this.one));
    assertEquals(new DoubleComplex(0.0d, 2.0d).sqrt().getReal(), 1.0d, 0.0d);
    assertEquals(new DoubleComplex(0.0d, 2.0d).sqrt().getImaginary(), 1.0d, 10e-16);

    assertEquals(new DoubleComplex(0.0d, -2.0d).sqrt().getReal(), 1.0d, 0.0d);
    assertEquals(new DoubleComplex(0.0d, -2.0d).sqrt().getImaginary(), -1.0d, 10e-16);

  }

  /**
   * DoubleComplex pow のテスト(double)
   * 
   * @since 2004/08/04
   */
  final public void testPowdouble() {
    assertTrue(this.complex0.pow(0.0d).equals(this.one));
    assertTrue(this.complex0.pow(1.0d).equals(this.complex0));
    assertTrue(this.complex0.pow(1.1d).equals(this.complex0));
    assertTrue(this.complex0.pow(3.1d).equals(this.complex0));
    assertTrue(Double.isInfinite(this.complex0.pow(-1.0d).getReal()));
    assertTrue(Double.isNaN(this.complex0.pow(-1.0d).getImaginary()));

  }

  /**
   * DoubleComplex pow のテスト(DoubleComplex)
   * 
   * @since 2004/08/04
   */
  final public void testPowComplex() {
    assertTrue(this.complex1.pow(this.complex1).equals(
        new DoubleComplex(1.0d, 2.0d).pow(new DoubleComplex(1.0d, 2.0d))));
    assertTrue(this.complex0.pow(this.complex0).equals(this.one));
    assertTrue(this.complex0.pow(new DoubleComplex(0.1d, 0.0d)).equals(this.complex0));
    assertTrue(this.complex0.pow(new DoubleComplex(0.2d, 0.0d)).equals(this.complex0));
    assertTrue(this.complex0.pow(this.one).equals(this.complex0));
    assertTrue(this.complex0.pow(new DoubleComplex(1.1d, 0.0d)).equals(this.complex0));

    double inf = Double.POSITIVE_INFINITY;

    assertTrue(this.complex0.pow(new DoubleComplex(-0.9d, 0.0d)).equals(
        new DoubleComplex(inf, 0.0d)));
    assertTrue(this.complex0.pow(new DoubleComplex(-1.0d, 0.0d)).equals(
        new DoubleComplex(inf, 0.0d)));
    assertTrue(this.complex0.pow(new DoubleComplex(-1.1d, 0.0d)).equals(
        new DoubleComplex(inf, 0.0d)));
    assertTrue(this.complex0.pow(new DoubleComplex(-2.0d, 0.0d)).equals(
        new DoubleComplex(inf, 0.0d)));
    assertTrue(this.complex0.pow(new DoubleComplex(-3.0d, 0.0d)).equals(
        new DoubleComplex(inf, 0.0d)));
    assertTrue(this.complex0.pow(new DoubleComplex(-9.0d, 0.0d)).equals(
        new DoubleComplex(inf, 0.0d)));

  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testExp() {

    assertTrue(this.complex0.exp().equals(this.one));
    assertEquals(this.one.exp().getReal(), Math.E, 10e-16);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testLog() {

    assertTrue(this.one.log().equals(this.complex0));
    assertEquals(new DoubleComplex(Math.E, 0.0d).log().getReal(), 1.0d, 10e-17);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testLog10() {

    assertTrue(this.one.log10().equals(this.complex0));
    assertEquals(new DoubleComplex(10.0d, 0.0d).log10().getReal(), 1.0d, 10e-18);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testLog2() {

    assertTrue(this.one.log2().equals(this.complex0));
    assertEquals(new DoubleComplex(2.0d, 0.0d).log2().getReal(), 1.0d, 10e-18);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testSin() {

    assertTrue(this.complex0.sin().equals(this.complex0));

    assertEquals(new DoubleComplex(pi / 2, 0.0d).sin().getReal(), 1.0d, 10e-18);
    assertEquals(new DoubleComplex(pi, 0.0d).sin().getReal(), 0.0d, 10e-16);
    assertEquals(new DoubleComplex(pi / 2 * 3, 0.0d).sin().getReal(), -1.0d, 10e-18);
    assertEquals(new DoubleComplex(pi * 2, 0.0d).sin().getReal(), 0.0d, 10e-16);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testCos() {

    assertEquals(this.complex0.cos().getReal(), 1.0d, 10e-18);

    assertEquals(new DoubleComplex(pi / 2, 0.0d).cos().getReal(), 0.0d, 10e-17);
    assertEquals(new DoubleComplex(pi, 0.0d).cos().getReal(), -1.0d, 10e-18);
    assertEquals(new DoubleComplex(pi / 2 * 3, 0.0d).cos().getReal(), 0.0d, 10e-16);
    assertEquals(new DoubleComplex(pi * 2, 0.0d).cos().getReal(), 1.0d, 10e-18);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testTan() {

    assertEquals(this.complex0.tan().getReal(), 0.0d, 10e-18);
    assertEquals(new DoubleComplex(-pi / 4, 0.0d).tan().getReal(), -1.0d, 1);
    assertEquals(new DoubleComplex(pi / 4, 0.0d).tan().getReal(), 1.0d, 1);

    assertTrue(new DoubleComplex(pi / 2, 0.0d).tan().getReal() > 10e15);
    assertEquals(new DoubleComplex(pi, 0.0d).tan().getReal(), 0.0d, 10e-16);

    assertTrue(new DoubleComplex(-pi / 2, 0.0d).tan().getReal() < -10e15);
    assertEquals(new DoubleComplex(pi * 2, 0.0d).tan().getReal(), 0.0d, 10e-16);

  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testSinh() {
    assertTrue(this.complex0.sinh().equals(this.complex0));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testCosh() {
    assertTrue(this.complex0.cosh().equals(this.one));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testTanh() {
    assertTrue(this.complex0.tanh().equals(this.complex0));
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testAcos() {

    assertEquals(this.one.acos().getReal(), Math.acos(1.0d), 0.0d);
    assertEquals(this.complex0.acos().getReal(), Math.acos(0.0d), 0.0d);
    assertEquals(this.one.negate().acos().getReal(), Math.acos(-1.0d), 0.0d);

  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testAsin() {

    assertEquals(this.one.asin().getReal(), Math.asin(1.0d), 0.0d);
    assertEquals(this.complex0.asin().getReal(), Math.asin(0.0d), 0.0d);
    assertEquals(this.one.negate().asin().getReal(), Math.asin(-1.0d), 0.0d);

  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testMax() {
    assertEquals(this.complex0.max(this.complex1), this.complex1.abs(), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  final public void testMin() {
    assertEquals(this.complex0.min(this.complex1), this.complex0.abs(), 0.0d);
  }

  /**
   * String toString のテスト()
   * 
   * @since 2004/08/04
   */
  final public void testToString() {
    this.complex0.toString().equals("0.0 +0.0i");
    this.complex1.toString().equals("1.0 +2.0i");
    new DoubleComplex(-1.12d, -3.323d).toString().equals("-1.12 -3.323i");
  }

  /*
   * Object clone のテスト() 不変クラスになったため不要となったメソッド
   */
  /**
   * 
   * @since 2004/08/04
   */
  final public void testClone() {
    DoubleComplex a = this.complex0;
    DoubleComplex c = this.complex0;
    DoubleComplex c2 = c;

    assertTrue(a.equals(this.complex0));
    assertTrue(c2.equals(a));
    assertTrue(c.equals(a));

    assertTrue(c.equals(this.complex0));
    assertTrue(c2.equals(this.complex0));
    assertTrue(c.equals(c2));

    c = this.one;

    assertTrue(c2.equals(this.complex0));
    assertTrue(a.equals(this.complex0));
    assertTrue(c2.equals(a));

    assertFalse(c.equals(this.complex0));
    assertFalse(c.equals(c2));
    assertFalse(c.equals(a));

    c = new DoubleComplex(0.0d, 0.0d);

    assertTrue(c.equals(this.complex0));
    assertTrue(c2.equals(this.complex0));
    assertTrue(c.equals(c));

  }

}