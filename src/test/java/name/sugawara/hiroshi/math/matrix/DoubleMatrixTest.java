/*
 * Created on 2003/05/15
 * 
 */
package name.sugawara.hiroshi.math.matrix;

import junit.framework.TestCase;

/**
 * @author sugawara
 * @since 1.1
 * @version $Id: DoubleMatrixTest.java 102 2007-04-10 06:27:03Z sugawara $
 * @uml.stereotype uml_id="null" isDefined="true" name="tagged"
 */
public class DoubleMatrixTest extends TestCase {

  /**
   * 
   * @uml.property name="a"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleMatrix              a;

  /**
   * 
   * @uml.property name="b"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleMatrix              b;

  /**
   * 行列変数.
   * 
   * @uml.property name="XXM"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleMatrix              XXM;

  /**
   * 行列変数エイリアス.
   * 
   * @uml.property name="XXM_ALIAS"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleMatrix              XXM_ALIAS;

  /**
   * 行列変数.
   * 
   * @uml.property name="XXM2"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DoubleMatrix              XXM2;

  /**
   * 行列用に次元配列サンプル.
   * 
   * @uml.property name="Z" changeability="frozen" multiplicity="(0 -1)(0 -1)"
   */
  private static final double[][]   Z  = new double[][]
                                       {
                                       { 1.11d, 2.33d, -1.04d },
                                       { 1.31d, -2.63d, 1.64d },
                                       { -21.11d, 0.38d, -0.01d } };

  /**
   * 行列サンプル.
   * 
   * @uml.property name="ZM" changeability="frozen"
   */
  private static final DoubleMatrix ZM = new DoubleMatrix(Z);

  /**
   * Constructor for DoubleMatrixTest.
   * 
   * @param arg0
   *          テスト名.
   */
  public DoubleMatrixTest(final String arg0) {
    super(arg0);
  }

  /*
   * @see TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();

    double[][] xx =
    {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 9 } };
    double[][] xx2 =
    {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 0 } };
    this.XXM = new DoubleMatrix(xx);
    this.XXM_ALIAS = new DoubleMatrix(xx);
    this.XXM2 = new DoubleMatrix(xx2);

    this.a = new DoubleMatrix(3, 3, 2);
    this.b = new DoubleMatrix(3, 3, -2);

    assertNotNull(this.XXM);
    assertNotNull(this.XXM_ALIAS);
    assertNotNull(this.XXM2);
    assertNotNull(this.a);
    assertNotNull(this.b);
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testAbs() {
    double minusD[][] = new double[][]
    {
    { 1.0d, 1.1d, 1.0d },
    { 1.2d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -1.4d } };
    double d[][] = new double[][]
    {
    { 1.0d, 1.1d, 1.0d },
    { 1.2d, 0.9d, 0.0d },
    { 0.2d, 0.1d, 1.4d } };

    DoubleMatrix minusDM = new DoubleMatrix(minusD);
    DoubleMatrix dM = new DoubleMatrix(d);
    assertFalse(minusDM.equals(dM));
    assertTrue(minusDM.abs().equals(dM));
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testAcos() {

    double d[][] = new double[][]
    {
    { 1.0d, 0.3d, -1.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -0.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dmNaN = dM.set(0, 0, StrictMath.acos(1.1d));

    assertTrue(Double.isNaN(dmNaN.acos().get(0, 0)));

    System.out.println("test");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.acos().get(i, j) + " ");
        System.out.print(Math.acos(dM.get(i, j)) + "  ");
        assertEquals(dM.acos().get(i, j), Math.acos(dM.get(i, j)), 0.0d);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testAdd() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double d2[][] = new double[][]
    {
    { 2.1d, 3.3d, -5.0d },
    { 0.25d, -4.9d, 1.0d },
    { 0.6d, -3.1d, 0.0d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dM2 = new DoubleMatrix(d2);

    System.out.println("add");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.add(dM2).get(i, j) + " ");
        System.out.print((d[i][j] + d2[i][j]) + "  ");
        assertEquals(dM.add(dM2).get(i, j), d[i][j] + d2[i][j], 0.0d);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testAsin() {

    double d[][] = new double[][]
    {
    { 1.0d, 0.3d, -1.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -0.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);

    DoubleMatrix dmNaN = dM.set(0, 0, Math.asin(1.1d));

    assertTrue(Double.isNaN(dmNaN.asin().get(0, 0)));

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.asin().get(i, j) + " ");
        System.out.print(Math.asin(dM.get(i, j)) + "  ");
        assertEquals(dM.asin().get(i, j), Math.asin(dM.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testAtan() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);

    System.out.println("Arc tangent");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.atan().get(i, j) + " ");
        System.out.print(Math.atan(dM.get(i, j)) + "  ");
        assertEquals(dM.atan().get(i, j), Math.atan(dM.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testAtan2() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double d2[][] = new double[][]
    {
    { 1.222d, 0.32d, -6.02d },
    { 0.252d, -0.92d, 0.02d },
    { 0.22d, -0.12d, -4.52d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dM2 = new DoubleMatrix(d2);

    System.out.println("atan2");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.atan2(dM2).get(i, j) + " ");
        System.out.print(Math.atan2(dM.get(i, j), dM2.get(i, j)) + "  ");
        assertEquals(dM.atan2(dM2).get(i, j), Math.atan2(dM.get(i, j), dM2.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testClone() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dM2 = new DoubleMatrix(d);
    DoubleMatrix dc = null;
    try {
      dc = (DoubleMatrix) dM.clone();
    } catch (CloneNotSupportedException e) {

      System.out.println(e);
      e.printStackTrace();
    }
    assertTrue(dM.equals(dM2));
    assertTrue(dM.equals(dc));

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.atan2(dM2).get(i, j) + " ");
        System.out.print(Math.atan2(dM.get(i, j), dM2.get(i, j)) + "  ");
        assertEquals(dM.atan2(dM2).get(i, j), Math.atan2(dM.get(i, j), dM2.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testCos() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);

    System.out.println("cos");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.cos().get(i, j) + " ");
        System.out.print(Math.cos(dM.get(i, j)) + "  ");
        assertEquals(dM.cos().get(i, j), Math.cos(dM.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testDividedouble() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double dd = 0.1d;
    DoubleMatrix dM = new DoubleMatrix(d);

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.divide(dd).get(i, j) + " ");
        System.out.print(dM.get(i, j) / dd + "  ");
        assertEquals(dM.divide(dd).get(i, j), dM.get(i, j) / dd, 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testDivideMatrix() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double d2[][] = new double[][]
    {
    { 1.222d, 0.32d, -6.02d },
    { 0.252d, -0.92d, 0.02d },
    { 0.22d, -0.12d, -4.52d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dM2 = new DoubleMatrix(d2);

    System.out.println("atan2");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.divide(dM2).get(i, j) + " ");
        System.out.print(dM.get(i, j) / dM2.get(i, j) + "  ");
        assertEquals(dM.divide(dM2).get(i, j), dM.get(i, j) / dM2.get(i, j), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testEquals() {
    assertTrue(this.XXM.equals(this.XXM_ALIAS));
    assertFalse(this.XXM.equals(this.XXM2));
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testExp() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.exp().get(i, j) + " ");
        System.out.print(Math.exp(dM.get(i, j)) + "  ");
        assertEquals(dM.exp().get(i, j), Math.exp(dM.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testFliplr() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    assertTrue(dM.fliplr().fliplr().equals(dM));

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.fliplr().get(i, j) + " ");
        System.out.print(dM.get(i, dM.getRowDimension() - 1 - j) + "  ");
        assertEquals(dM.fliplr().get(i, j), dM.get(i, dM.getRowDimension() - 1 - j), 0.0d);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testFlipud() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    assertTrue(dM.flipud().flipud().equals(dM));

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.flipud().get(i, j) + " ");
        System.out.print(dM.get(dM.getColumnDimension() - 1 - i, j) + "  ");
        assertEquals(dM.flipud().get(i, j), dM.get(dM.getColumnDimension() - 1 - i, j), 0.0d);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testGet() {
    double[][] a = new double[2][2];
    DoubleMatrix m = new DoubleMatrix(a);
    assertEquals(a[0][0], m.get(0, 0), 0.0d);
    assertEquals(a[0][1], m.get(0, 1), 0.0d);
    assertEquals(a[1][1], m.get(1, 1), 0.0d);
    assertEquals(a[1][0], m.get(1, 0), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testGetArray() {
    double[][] d = new double[100][100];

    for (int i = 0; i < d.length; i++) {
      java.util.Arrays.fill(d[i], 0.0d);
    }

    DoubleMatrix m = new DoubleMatrix(d);
    assertNotNull(m);
    assertNotNull(m.getArray());

    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d[i].length; j++) {
        assertEquals(m.getArray()[i][j], d[i][j], 0.0d);
      }
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testGetColumnDimension() {
    double[][] d = new double[100][1000];
    DoubleMatrix m = new DoubleMatrix(d);
    assertNotNull(m);
    assertEquals(m.getRowDimension(), 100);
    assertEquals(m.getColumnDimension(), 1000);
  }

  /**
   * Test for DoubleMatrix getMatrix(int[], int[])
   * 
   * @since 2004/08/04
   */
  public void testGetMatrixintArrayintArray() {
    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d } };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    int[] a = new int[]
    { 0, 1, 3 };
    int[] b = new int[]
    { 0, 2, 3 };

    DoubleMatrix sub = m.getMatrix(a, b);
    System.out.println(sub.getColumnDimension());
    System.out.println(sub.getRowDimension());
    assertEquals(sub.getColumnDimension(), 3);
    assertEquals(sub.getRowDimension(), 3);
    System.out.println("test");
    System.out.println(sub.get(0, 0) + " " + sub.get(0, 1) + " " + sub.get(0, 2));
    System.out.println(sub.get(1, 0) + " " + sub.get(1, 1) + " " + sub.get(1, 2));
    System.out.println(sub.get(2, 0) + " " + sub.get(2, 1) + " " + sub.get(2, 2));

    assertEquals(sub.get(0, 0), 1.1d, 0.0d);
    assertEquals(sub.get(0, 1), 3.2d, 0.0d);
    assertEquals(sub.get(0, 2), 4.0d, 0.0d);
    assertEquals(sub.get(1, 0), 0.0d, 0.0d);
    assertEquals(sub.get(1, 1), -0.4d, 0.0d);
    assertEquals(sub.get(1, 2), 1.8, 0.0d);
    assertEquals(sub.get(2, 0), 3.8d, 0.0d);
    assertEquals(sub.get(2, 1), -5.1d, 0.0d);
    assertEquals(sub.get(2, 2), 8.4, 0.0d);
  }

  /**
   * Test for DoubleMatrix getMatrix(int[], int, int)
   * 
   * @since 2004/08/04
   */
  public void testGetMatrixintArrayintint() {
    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d } };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    int[] indices = new int[]
    { 0, 2, 3 };

    DoubleMatrix sub = m.getMatrix(indices, 1, 3);
    System.out.println(sub.getColumnDimension());
    System.out.println(sub.getRowDimension());
    assertEquals(sub.getColumnDimension(), 3);
    assertEquals(sub.getRowDimension(), 3);

    System.out.println("\n\ntest getMatrix(int[], int, int)");
    System.out.println(sub.get(0, 0) + " " + sub.get(0, 1) + " " + sub.get(0, 2));
    System.out.println(sub.get(1, 0) + " " + sub.get(1, 1) + " " + sub.get(1, 2));
    System.out.println(sub.get(2, 0) + " " + sub.get(2, 1) + " " + sub.get(2, 2));

    assertEquals(sub.get(0, 0), 1.0d, 0.0d);
    assertEquals(sub.get(0, 1), 3.2d, 0.0d);
    assertEquals(sub.get(0, 2), 4.0d, 0.0d);
    assertEquals(sub.get(1, 0), -4.4d, 0.0d);
    assertEquals(sub.get(1, 1), -0.1d, 0.0d);
    assertEquals(sub.get(1, 2), 5.2d, 0.0d);
    assertEquals(sub.get(2, 0), -9.4d, 0.0d);
    assertEquals(sub.get(2, 1), -5.1d, 0.0d);
    assertEquals(sub.get(2, 2), 8.4, 0.0d);
  }

  /**
   * Test for DoubleMatrix getMatrix(int, int, int[])
   * 
   * @since 2004/08/04
   */
  public void testGetMatrixintintintArray() {
    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d }, };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    int[] a = new int[]
    { 0, 2, 3 };

    DoubleMatrix sub = m.getMatrix(1, 3, a);
    System.out.println(sub.getColumnDimension());
    System.out.println(sub.getRowDimension());
    System.out.println("test2");
    System.out.println(sub.get(0, 0) + " " + sub.get(0, 1) + " " + sub.get(0, 2));
    System.out.println(sub.get(1, 0) + " " + sub.get(1, 1) + " " + sub.get(1, 2));
    System.out.println(sub.get(2, 0) + " " + sub.get(2, 1) + " " + sub.get(2, 2));

    assertEquals(sub.get(0, 0), 0.0d, 0.0d);
    assertEquals(sub.get(0, 1), -0.4d, 0.0d);
    assertEquals(sub.get(0, 2), 1.8d, 0.0d);
    assertEquals(sub.get(1, 0), 3.4d, 0.0d);
    assertEquals(sub.get(1, 1), -0.1d, 0.0d);
    assertEquals(sub.get(1, 2), 5.2d, 0.0d);
    assertEquals(sub.get(2, 0), 3.8d, 0.0d);
    assertEquals(sub.get(2, 1), -5.1d, 0.0d);
    assertEquals(sub.get(2, 2), 8.4, 0.0d);

  }

  /**
   * Test for DoubleMatrix getMatrix(int, int, int, int)
   * 
   * @since 2004/08/04
   */
  public void testGetMatrixintintintint() {
    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d } };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    DoubleMatrix sub = m.getMatrix(0, 1, 0, 1);

    System.out.println("col=" + sub.getColumnDimension());
    System.out.println("row=" + sub.getRowDimension());

    System.out.println("subget=" + sub.get(0, 0));
    System.out.println("subget=" + sub.get(0, 1));
    System.out.println("subget=" + sub.get(1, 0));
    System.out.println("subget=" + sub.get(1, 1));

    assertEquals(sub.get(0, 0), 1.1d, 0.0d);
    assertEquals(sub.get(0, 1), 1.0d, 0.0d);
    assertEquals(sub.get(1, 0), 0.0d, 0.0d);
    assertEquals(sub.get(1, 1), 0.9d, 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testGetRowDimension() {
    double[][] d = new double[100][1000];
    DoubleMatrix m = new DoubleMatrix(d);
    assertNotNull(m);
    assertEquals(m.getRowDimension(), 100);
    assertEquals(m.getColumnDimension(), 1000);
  }

  /**
   * 
   * Test for void DoubleMatrix(double[][])
   * 
   * @since 2004/08/04
   */
  public void testDouleMatrixdoubleArrayArray() {
    int m = 4;
    int n = 5;
    double[][] x = new double[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        x[i][j] = i + j;
      }
    }
    DoubleMatrix matrix = new DoubleMatrix(x);
    assertEquals(x.length, m);
    assertEquals(x[0].length, n);
    assertEquals(x[1].length, n);
    assertEquals(x[2].length, n);
    assertEquals(x[3].length, n);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        assertEquals(x[i][j], matrix.get(i, j), 0.0d);
      }
    }

    double[][] x2 = new double[][]
    {
    { 1.1d, 1.0d, 3.2d },
    { 0.0d, 0.9d, -0.4d },
    { 3.4d, -4.4d, -0.1d },
    { 3.8d, -9.4d, -5.1d } };

    DoubleMatrix mx2 = new DoubleMatrix(x2);
    assertEquals(x2.length, 4);
    assertEquals(x2[0].length, 3);
    assertEquals(x2[1].length, 3);
    assertEquals(x2[2].length, 3);
    assertEquals(x2[3].length, 3);
    assertEquals(x2[0][0], 1.1d, 0.0d);
    assertEquals(x2[0][1], 1.0d, 0.0d);
    assertEquals(x2[0][2], 3.2d, 0.0d);
    assertEquals(x2[1][0], 0.0d, 0.0d);
    assertEquals(x2[1][1], 0.9d, 0.0d);
    assertEquals(x2[1][2], -0.4d, 0.0d);
    assertEquals(x2[2][0], 3.4d, 0.0d);
    assertEquals(x2[2][1], -4.4d, 0.0d);
    assertEquals(x2[2][2], -0.1d, 0.0d);
    assertEquals(x2[3][0], 3.8d, 0.0d);
    assertEquals(x2[3][1], -9.4d, 0.0d);
    assertEquals(x2[3][2], -5.1d, 0.0d);

    assertEquals(mx2.get(0, 0), 1.1d, 0.0d);
    assertEquals(mx2.get(0, 1), 1.0d, 0.0d);
    assertEquals(mx2.get(0, 2), 3.2d, 0.0d);
    assertEquals(mx2.get(1, 0), 0.0d, 0.0d);
    assertEquals(mx2.get(1, 1), 0.9d, 0.0d);
    assertEquals(mx2.get(1, 2), -0.4d, 0.0d);
    assertEquals(mx2.get(2, 0), 3.4d, 0.0d);
    assertEquals(mx2.get(2, 1), -4.4d, 0.0d);
    assertEquals(mx2.get(2, 2), -0.1d, 0.0d);
    assertEquals(mx2.get(3, 0), 3.8d, 0.0d);
    assertEquals(mx2.get(3, 1), -9.4d, 0.0d);
    assertEquals(mx2.get(3, 2), -5.1d, 0.0d);

    double[][] d = mx2.getArray();
    // java.util.Arrays.fill(d[0], 0.0d);
    // java.util.Arrays.fill(d[1], 0.0d);
    // java.util.Arrays.fill(d[2], 0.0d);
    // java.util.Arrays.fill(d[3], 0.0d);

    assertTrue(java.util.Arrays.equals(x2[0], d[0]));
    assertTrue(java.util.Arrays.equals(x2[1], d[1]));
    assertTrue(java.util.Arrays.equals(x2[2], d[2]));
    assertTrue(java.util.Arrays.equals(x2[3], d[3]));
  }

  /**
   * Test for void DoubleMatrix(double[], int)
   * 
   * @since 2004/08/04
   */
  public void testDoubleMatrixdoubleArrayint() {
    int n = 25;
    int s = 5;

    double[] d = new double[]
    { 2, 4, 5, 6, 7, 8, 9, 15, 0, 9, 6, 5, 4, 3, 2, 2, 2, 15, 16, 16, 8, 9, 0, 99, 8 };

    DoubleMatrix m = new DoubleMatrix(d, s);
    assertNotNull(m);
    assertEquals(m.get(0, 0), d[0], 0.0d);
    assertEquals(m.get(1, 0), d[1], 0.0d);
    assertEquals(m.get(2, 0), d[2], 0.0d);
    assertEquals(m.get(3, 0), d[3], 0.0d);
    assertEquals(m.get(4, 0), d[4], 0.0d);

    assertEquals(m.get(0, 1), d[5], 0.0d);
    assertEquals(m.get(1, 1), d[6], 0.0d);
    assertEquals(m.get(2, 1), d[7], 0.0d);
    assertEquals(m.get(3, 1), d[8], 0.0d);
    assertEquals(m.get(4, 1), d[9], 0.0d);

    assertEquals(m.get(0, 2), d[10], 0.0d);
    assertEquals(m.get(1, 2), d[11], 0.0d);
    assertEquals(m.get(2, 2), d[12], 0.0d);
    assertEquals(m.get(3, 2), d[13], 0.0d);
    assertEquals(m.get(4, 2), d[14], 0.0d);

    assertEquals(m.get(0, 3), d[15], 0.0d);
    assertEquals(m.get(1, 3), d[16], 0.0d);
    assertEquals(m.get(2, 3), d[17], 0.0d);
    assertEquals(m.get(3, 3), d[18], 0.0d);
    assertEquals(m.get(4, 3), d[19], 0.0d);

    assertEquals(m.get(0, 4), d[20], 0.0d);
    assertEquals(m.get(1, 4), d[21], 0.0d);
    assertEquals(m.get(2, 4), d[22], 0.0d);
    assertEquals(m.get(3, 4), d[23], 0.0d);
    assertEquals(m.get(4, 4), d[24], 0.0d);
  }

  /**
   * Test for void DoubleMatrix(int, int)
   * 
   * @since 2004/08/04
   */
  public void testDoubleMatrixintint() {
    int m = 4;
    int n = 5;
    double[][] d = new double[m][n];
    java.util.Arrays.fill(d[0], 0.0d);
    java.util.Arrays.fill(d[1], 0.0d);
    java.util.Arrays.fill(d[2], 0.0d);
    java.util.Arrays.fill(d[3], 0.0d);
    DoubleMatrix matrix2 = new DoubleMatrix(m, n);
    assertEquals(matrix2.getColumnDimension(), 5);
    assertEquals(matrix2.getRowDimension(), 4);
    assertNotNull(matrix2);
    assertNotNull(matrix2.getArray());

    double[][] result = matrix2.getArray();

    assertTrue(java.util.Arrays.equals(result[0], d[0]));
    assertTrue(java.util.Arrays.equals(result[1], d[1]));
    assertTrue(java.util.Arrays.equals(result[2], d[2]));
    assertTrue(java.util.Arrays.equals(result[3], d[3]));

    double s = 0.0d;
    assertEquals(matrix2.get(0, 0), s, 0.0d);
    assertEquals(matrix2.get(0, 1), s, 0.0d);
    assertEquals(matrix2.get(0, 2), s, 0.0d);
    assertEquals(matrix2.get(0, 3), s, 0.0d);
    assertEquals(matrix2.get(0, 4), s, 0.0d);

    assertEquals(matrix2.get(1, 0), s, 0.0d);
    assertEquals(matrix2.get(1, 1), s, 0.0d);
    assertEquals(matrix2.get(1, 2), s, 0.0d);
    assertEquals(matrix2.get(1, 3), s, 0.0d);
    assertEquals(matrix2.get(1, 4), s, 0.0d);

    assertEquals(matrix2.get(2, 0), s, 0.0d);
    assertEquals(matrix2.get(2, 1), s, 0.0d);
    assertEquals(matrix2.get(2, 2), s, 0.0d);
    assertEquals(matrix2.get(2, 3), s, 0.0d);
    assertEquals(matrix2.get(2, 4), s, 0.0d);

    assertEquals(matrix2.get(3, 0), s, 0.0d);
    assertEquals(matrix2.get(3, 1), s, 0.0d);
    assertEquals(matrix2.get(3, 2), s, 0.0d);
    assertEquals(matrix2.get(3, 3), s, 0.0d);
    assertEquals(matrix2.get(3, 4), s, 0.0d);
  }

  /**
   * Test for void DoubleMatrix(int, int, double)
   * 
   * @since 2004/08/04
   */
  public void testDoubleMatrixintintdouble() {
    int m = 4;
    int n = 5;
    double s = 0.0d;
    double[][] d = new double[m][n];
    java.util.Arrays.fill(d[0], 0.0d);
    java.util.Arrays.fill(d[1], 0.0d);
    java.util.Arrays.fill(d[2], 0.0d);
    java.util.Arrays.fill(d[3], 0.0d);

    DoubleMatrix matrix2 = new DoubleMatrix(m, n, s);
    assertEquals(matrix2.getColumnDimension(), 5);
    assertEquals(matrix2.getRowDimension(), 4);
    assertNotNull(matrix2);
    assertNotNull(matrix2.getArray());

    double[][] result = matrix2.getArray();

    assertTrue(java.util.Arrays.equals(result[0], d[0]));
    assertTrue(java.util.Arrays.equals(result[1], d[1]));
    assertTrue(java.util.Arrays.equals(result[2], d[2]));
    assertTrue(java.util.Arrays.equals(result[3], d[3]));

    assertEquals(matrix2.get(0, 0), s, 0.0d);
    assertEquals(matrix2.get(0, 1), s, 0.0d);
    assertEquals(matrix2.get(0, 2), s, 0.0d);
    assertEquals(matrix2.get(0, 3), s, 0.0d);
    assertEquals(matrix2.get(0, 4), s, 0.0d);

    assertEquals(matrix2.get(1, 0), s, 0.0d);
    assertEquals(matrix2.get(1, 1), s, 0.0d);
    assertEquals(matrix2.get(1, 2), s, 0.0d);
    assertEquals(matrix2.get(1, 3), s, 0.0d);
    assertEquals(matrix2.get(1, 4), s, 0.0d);

    assertEquals(matrix2.get(2, 0), s, 0.0d);
    assertEquals(matrix2.get(2, 1), s, 0.0d);
    assertEquals(matrix2.get(2, 2), s, 0.0d);
    assertEquals(matrix2.get(2, 3), s, 0.0d);
    assertEquals(matrix2.get(2, 4), s, 0.0d);

    assertEquals(matrix2.get(3, 0), s, 0.0d);
    assertEquals(matrix2.get(3, 1), s, 0.0d);
    assertEquals(matrix2.get(3, 2), s, 0.0d);
    assertEquals(matrix2.get(3, 3), s, 0.0d);
    assertEquals(matrix2.get(3, 4), s, 0.0d);

  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testNegate() {

    assertEquals(this.a.get(0, 0), this.b.negate().get(0, 0), 0.0d);
    assertEquals(this.a.get(0, 1), this.b.negate().get(0, 1), 0.0d);
    assertEquals(this.a.get(0, 2), this.b.negate().get(0, 2), 0.0d);

    assertEquals(this.a.get(1, 0), this.b.negate().get(1, 0), 0.0d);
    assertEquals(this.a.get(1, 1), this.b.negate().get(1, 1), 0.0d);
    assertEquals(this.a.get(1, 2), this.b.negate().get(1, 2), 0.0d);

    assertEquals(this.a.get(2, 0), this.b.negate().get(2, 0), 0.0d);
    assertEquals(this.a.get(2, 1), this.b.negate().get(2, 1), 0.0d);
    assertEquals(this.a.get(2, 2), this.b.negate().get(2, 2), 0.0d);

    System.out.println(this.a.get(0, 0) + " " + this.a.get(0, 1) + " " + this.a.get(0, 2));
    System.out.println(this.a.get(1, 0) + " " + this.a.get(1, 1) + " " + this.a.get(1, 2));
    System.out.println(this.a.get(2, 0) + " " + this.a.get(2, 1) + " " + this.a.get(2, 2));

    System.out.println(this.b.get(0, 0) + " " + this.b.get(0, 1) + " " + this.b.get(0, 2));
    System.out.println(this.b.get(1, 0) + " " + this.b.get(1, 1) + " " + this.b.get(1, 2));
    System.out.println(this.b.get(2, 0) + " " + this.b.get(2, 1) + " " + this.b.get(2, 2));

  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testSet() {
    System.out.println("testa = " + this.a.get(0, 0));
    DoubleMatrix v = this.a.set(0, 0, 0.9d);
    System.out.println("testv = " + v.get(0, 0));
    System.out.println("testa = " + this.a.get(0, 0));
    assertFalse(v.get(0, 0) == this.a.get(0, 0));
    assertEquals(v.get(0, 0), 0.9d, 0.0d);
    assertFalse(v.equals(this.a));
  }

  /**
   * Test for void setMatrix(int[], int[], DoubleMatrix)
   * 
   * @since 2004/08/04
   */
  public void testSetMatrixintArrayintArrayMatrix() {
    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d } };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    int[] a = new int[]
    { 0, 1, 3 };
    int[] b = new int[]
    { 0, 2, 3 };

    double[][] setArray = new double[][]
    {
    { 0.3d, 1.4d, 1.2d },
    { 0.8d, 7.9d, -4.4d },
    { 3.9d, -4.6d, -4.1d }, };

    DoubleMatrix setMatrix = new DoubleMatrix(setArray);
    DoubleMatrix afterSet = m.setMatrix(a, b, setMatrix);

    System.out.println(afterSet.getColumnDimension());
    System.out.println(afterSet.getRowDimension());
    assertEquals(afterSet.getColumnDimension(), 4);
    assertEquals(afterSet.getRowDimension(), 4);

    System.out.println("setMatrix(int[], int[], Matrix) : test");
    System.out.println(afterSet.get(0, 0) + " " + afterSet.get(0, 1) + " " + afterSet.get(0, 2));
    System.out.println(afterSet.get(1, 0) + " " + afterSet.get(1, 1) + " " + afterSet.get(1, 2));
    System.out.println(afterSet.get(2, 0) + " " + afterSet.get(2, 1) + " " + afterSet.get(2, 2));

    assertEquals(afterSet.get(0, 0), 0.3d, 0.0d);
    assertEquals(afterSet.get(0, 1), 1.0d, 0.0d);
    assertEquals(afterSet.get(0, 2), 1.4d, 0.0d);
    assertEquals(afterSet.get(0, 3), 1.2d, 0.0d);
    assertEquals(afterSet.get(1, 0), 0.8d, 0.0d);
    assertEquals(afterSet.get(1, 1), 0.9d, 0.0d);
    assertEquals(afterSet.get(1, 2), 7.9d, 0.0d);
    assertEquals(afterSet.get(1, 3), -4.4d, 0.0d);
    assertEquals(afterSet.get(2, 0), 3.4d, 0.0d);
    assertEquals(afterSet.get(2, 1), -4.4d, 0.0d);
    assertEquals(afterSet.get(2, 2), -0.1, 0.0d);
    assertEquals(afterSet.get(2, 3), 5.2d, 0.0d);
    assertEquals(afterSet.get(3, 0), 3.9d, 0.0d);
    assertEquals(afterSet.get(3, 1), -9.4d, 0.0d);
    assertEquals(afterSet.get(3, 2), -4.6, 0.0d);
    assertEquals(afterSet.get(3, 3), -4.1d, 0.0d);
  }

  /**
   * Test for void setMatrix(int[], int, int, DoubleMatrix)
   * 
   * @since 2004/08/04
   */
  public void testSetMatrixintArrayintintMatrix() {

    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d } };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    int[] a = new int[]
    { 1, 2, 3 };

    double[][] setArray = new double[][]
    {
    { 0.3d, 1.4d, 1.2d },
    { 0.8d, 7.9d, -4.4d },
    { 3.9d, -4.6d, -4.1d } };

    DoubleMatrix setMatrix = new DoubleMatrix(setArray);
    DoubleMatrix afterSet = m.setMatrix(a, 0, 2, setMatrix);

    System.out.println(afterSet.getColumnDimension());
    System.out.println(afterSet.getRowDimension());
    assertEquals(afterSet.getColumnDimension(), 4);
    assertEquals(afterSet.getRowDimension(), 4);

    System.out.println("setMatrix(int[], int[], Matrix) : test");
    System.out.println(afterSet.get(0, 0) + " " + afterSet.get(0, 1) + " " + afterSet.get(0, 2));
    System.out.println(afterSet.get(1, 0) + " " + afterSet.get(1, 1) + " " + afterSet.get(1, 2));
    System.out.println(afterSet.get(2, 0) + " " + afterSet.get(2, 1) + " " + afterSet.get(2, 2));

    assertEquals(afterSet.get(0, 0), 1.1d, 0.0d);
    assertEquals(afterSet.get(0, 1), 1.0d, 0.0d);
    assertEquals(afterSet.get(0, 2), 3.2d, 0.0d);
    assertEquals(afterSet.get(0, 3), 4.0d, 0.0d);
    assertEquals(afterSet.get(1, 0), 0.3d, 0.0d);
    assertEquals(afterSet.get(1, 1), 1.4d, 0.0d);
    assertEquals(afterSet.get(1, 2), 1.2d, 0.0d);
    assertEquals(afterSet.get(1, 3), 1.8d, 0.0d);
    assertEquals(afterSet.get(2, 0), 0.8d, 0.0d);
    assertEquals(afterSet.get(2, 1), 7.9d, 0.0d);
    assertEquals(afterSet.get(2, 2), -4.4d, 0.0d);
    assertEquals(afterSet.get(2, 3), 5.2d, 0.0d);
    assertEquals(afterSet.get(3, 0), 3.9d, 0.0d);
    assertEquals(afterSet.get(3, 1), -4.6d, 0.0d);
    assertEquals(afterSet.get(3, 2), -4.1d, 0.0d);
    assertEquals(afterSet.get(3, 3), 8.4d, 0.0d);
  }

  /**
   * Test for void setMatrix(int, int, int[], DoubleMatrix)
   * 
   * @since 2004/08/04
   */
  public void testSetMatrixintintintArrayMatrix() {

    double[][] x = new double[][]
    {
    { 1.1d, 1.0d, 3.2d, 4.0d },
    { 0.0d, 0.9d, -0.4d, 1.8d },
    { 3.4d, -4.4d, -0.1d, 5.2d },
    { 3.8d, -9.4d, -5.1d, 8.4d } };

    assertEquals(x.length, 4);
    assertEquals(x[0].length, 4);
    assertEquals(x[1].length, 4);
    assertEquals(x[2].length, 4);
    assertEquals(x[3].length, 4);

    DoubleMatrix m = new DoubleMatrix(x);
    int[] a = new int[]
    { 0, 1, 2 };

    double[][] setArray = new double[][]
    {
    { 0.3d, 1.4d, 1.2d },
    { 0.8d, 7.9d, -4.4d },
    { 3.9d, -4.6d, -4.1d } };

    DoubleMatrix setMatrix = new DoubleMatrix(setArray);
    DoubleMatrix afterSet = m.setMatrix(0, 2, a, setMatrix);

    System.out.println(afterSet.getColumnDimension());
    System.out.println(afterSet.getRowDimension());
    assertEquals(afterSet.getColumnDimension(), 4);
    assertEquals(afterSet.getRowDimension(), 4);

    System.out.println("setMatrix(int[], int[], Matrix) : test");
    System.out.println(afterSet.get(0, 0) + " " + afterSet.get(0, 1) + " " + afterSet.get(0, 2));
    System.out.println(afterSet.get(1, 0) + " " + afterSet.get(1, 1) + " " + afterSet.get(1, 2));
    System.out.println(afterSet.get(2, 0) + " " + afterSet.get(2, 1) + " " + afterSet.get(2, 2));

    assertEquals(afterSet.get(0, 0), 0.3d, 0.0d);
    assertEquals(afterSet.get(0, 1), 1.4d, 0.0d);
    assertEquals(afterSet.get(0, 2), 1.2d, 0.0d);
    assertEquals(afterSet.get(0, 3), 4.0d, 0.0d);
    assertEquals(afterSet.get(1, 0), 0.8d, 0.0d);
    assertEquals(afterSet.get(1, 1), 7.9d, 0.0d);
    assertEquals(afterSet.get(1, 2), -4.4d, 0.0d);
    assertEquals(afterSet.get(1, 3), 1.8d, 0.0d);
    assertEquals(afterSet.get(2, 0), 3.9d, 0.0d);
    assertEquals(afterSet.get(2, 1), -4.6d, 0.0d);
    assertEquals(afterSet.get(2, 2), -4.1d, 0.0d);
    assertEquals(afterSet.get(2, 3), 5.2d, 0.0d);
    assertEquals(afterSet.get(3, 0), 3.8d, 0.0d);
    assertEquals(afterSet.get(3, 1), -9.4d, 0.0d);
    assertEquals(afterSet.get(3, 2), -5.1d, 0.0d);
    assertEquals(afterSet.get(3, 3), 8.4d, 0.0d);

  }

  /**
   * Test for void setMatrix(int, int, int, int, DoubleMatrix)
   * 
   * @since 2004/08/04
   */
  public void testSetMatrixintintintintMatrix() {
    double[][] d = new double[][]
    {
    { 1.0d, 2.0d, 3.0d },
    { 4.0d, 1.1d, 1.1d },
    { 7.0d, 1.1d, 1.1d } };

    double[][] xx =
    {
    { 1.0d, 2.0d, 3.0d },
    { 4.0d, 5.0d, 6.0d },
    { 7.0d, 8.0d, 9.0d } };

    this.XXM = new DoubleMatrix(xx);

    DoubleMatrix comparedM = new DoubleMatrix(d);

    DoubleMatrix sub = new DoubleMatrix(2, 2, 1.1d);
    DoubleMatrix last = this.XXM.setMatrix(1, 2, 1, 2, sub);

    System.out.println("subgetR=" + sub.getRowDimension());
    System.out.println("subgetC=" + sub.getColumnDimension());

    System.out.println("sg(0,0)=" + sub.get(0, 0));
    System.out.println("sg(0,1)=" + sub.get(0, 1));
    System.out.println("sg(1,0)=" + sub.get(1, 0));
    System.out.println("sg(1,1)=" + sub.get(1, 1));

    System.out.println("last(0,0)=" + last.get(0, 0));
    System.out.println("last(0,1)=" + last.get(0, 1));
    System.out.println("last(0,2)=" + last.get(0, 2));

    System.out.println("last(1,0)=" + last.get(1, 0));
    System.out.println("last(1,1)=" + last.get(1, 1));
    System.out.println("last(1,2)=" + last.get(1, 2));

    System.out.println("last(2,0)=" + last.get(2, 0));
    System.out.println("last(2,1)=" + last.get(2, 1));
    System.out.println("last(2,2)=" + last.get(2, 2));

    System.out.println("lastgetR=" + last.getRowDimension());
    System.out.println("lastgetC=" + last.getColumnDimension());

    assertTrue(comparedM.equals(last));
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testSubtract() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double d2[][] = new double[][]
    {
    { 2.1d, 3.3d, -5.0d },
    { 0.25d, -4.9d, 1.0d },
    { 0.6d, -3.1d, 0.0d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dM2 = new DoubleMatrix(d2);

    System.out.println("subtract");

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.subtract(dM2).get(i, j) + " ");
        System.out.print((d[i][j] - d2[i][j]) + "  ");
        assertEquals(dM.subtract(dM2).get(i, j), d[i][j] - d2[i][j], 0.0d);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testTranspose() {
    double[][] xx =
    {
    { 1.0d, 2.0d, 3.0d },
    { 4.0d, 5.0d, 6.0d },
    { 7.0d, 8.0d, 9.0d } };

    this.XXM = new DoubleMatrix(xx);

    assertEquals(this.XXM.get(0, 0), this.XXM.transpose().get(0, 0), 0.0d);
    assertEquals(this.XXM.get(0, 1), this.XXM.transpose().get(1, 0), 0.0d);
    assertEquals(this.XXM.get(0, 2), this.XXM.transpose().get(2, 0), 0.0d);

    assertEquals(this.XXM.get(1, 0), this.XXM.transpose().get(0, 1), 0.0d);
    assertEquals(this.XXM.get(1, 1), this.XXM.transpose().get(1, 1), 0.0d);
    assertEquals(this.XXM.get(1, 2), this.XXM.transpose().get(2, 1), 0.0d);

    assertEquals(this.XXM.get(2, 0), this.XXM.transpose().get(0, 2), 0.0d);
    assertEquals(this.XXM.get(2, 1), this.XXM.transpose().get(1, 2), 0.0d);
    assertEquals(this.XXM.get(2, 2), this.XXM.transpose().get(2, 2), 0.0d);

    System.out.println(this.XXM.get(0, 0) + " " + this.XXM.get(0, 1) + " " + this.XXM.get(0, 2));
    System.out.println(this.XXM.get(1, 0) + " " + this.XXM.get(1, 1) + " " + this.XXM.get(1, 2));
    System.out.println(this.XXM.get(2, 0) + " " + this.XXM.get(2, 1) + " " + this.XXM.get(2, 2));
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testInfinityNorm() {

    assertEquals(ZM.infinityNorm(), ZM.transpose().abs().sum().max(), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testLog() {

    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.log().get(i, j) + " ");
        System.out.print(Math.log(ZM.get(i, j)) + "  ");
        if (ZM.get(i, j) < 0) {
          assertTrue(Double.isNaN(ZM.log().get(i, j)));
          assertTrue(Double.isNaN(Math.log(ZM.get(i, j))));
        } else {
          assertEquals(ZM.log().get(i, j), Math.log(ZM.get(i, j)), 0.0d);
        }
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testMax() {

    System.out.println("max");

    DoubleVector dv = ZM.max();
    for (int j = 0; j < dv.size(); j++) {
      System.out.print(dv.get(j) + " ");
    }

    assertEquals(ZM.max().max(), 2.33d, 0.0d);

    assertEquals(ZM.max().get(0), 1.31d, 0.0d);
    assertEquals(ZM.max().get(1), 2.33d, 0.0d);
    assertEquals(ZM.max().get(2), 1.64d, 0.0d);

  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testMin() {

    System.out.println("min");
    DoubleVector dv = ZM.min();
    for (int j = 0; j < dv.size(); j++) {
      System.out.print(dv.get(j) + " ");
    }

    assertEquals(ZM.min().min(), -21.11d, 0.0d);

    assertEquals(ZM.min().get(0), -21.11d, 0.0d);
    assertEquals(ZM.min().get(1), -2.63d, 0.0d);
    assertEquals(ZM.min().get(2), -1.04d, 0.0d);

  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testMean() {

    DoubleVector dv = ZM.mean();
    for (int j = 0; j < dv.size(); j++) {
      System.out.print(dv.get(j) + " ");
      System.out.print(((ZM.get(0, j) + ZM.get(1, j) + ZM.get(2, j)) / ZM.getColumnDimension())
          + " ");
      assertEquals(dv.get(j), (ZM.get(0, j) + ZM.get(1, j) + ZM.get(2, j))
          / ZM.getColumnDimension(), 0.0d);
    }

  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testMultiply() {

    double[][] md = new double[][]
    {
    { 26.24, -3.94, 2.68 },
    { -36.61, 10.59, -5.69 },
    { -22.72, -50.19, 22.58 } };

    DoubleMatrix dm = new DoubleMatrix(md);

    DoubleMatrix multiplied = ZM.multiply(ZM);
    System.out.println("mul");
    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.multiply(ZM).get(i, j) + " ");
        assertEquals(multiplied.get(i, j), dm.get(i, j), 10e-2);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testNegativeInfinityNorm() {
    assertEquals(ZM.negativeInfinityNorm(), ZM.transpose().abs().sum().max(), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testOneNorm() {
    assertEquals(ZM.oneNorm(), ZM.abs().sum().max(), 0.0d);
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testPowdouble() {
    double d = 1.1d;

    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.pow(d).get(i, j) + " ");
        System.out.print(Math.pow(ZM.get(i, j), d) + "  ");
        if (ZM.get(i, j) < 0) {
          assertTrue(Double.isNaN(ZM.pow(ZM).get(i, j)));
        } else {
          assertEquals(ZM.pow(d).get(i, j), Math.pow(ZM.get(i, j), d), 0.0d);
        }
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testPowDoubleMatrix() {

    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.pow(ZM).get(i, j) + " ");
        System.out.print(Math.pow(ZM.get(i, j), ZM.get(i, j)) + "  ");

        if (ZM.get(i, j) < 0) {
          assertTrue(Double.isNaN(ZM.pow(ZM).get(i, j)));
        } else {
          assertEquals(ZM.pow(ZM).get(i, j), Math.pow(ZM.get(i, j), ZM.get(i, j)), 0.0d);
        }
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testProduct() {
    DoubleVector v = ZM.product();
    double[] da = new double[v.size()];

    for (int j = 0; j < v.size(); j++) {
      da[j] = ZM.get(0, j);
      for (int i = 1; i < ZM.getRowDimension(); i++) {
        da[j] *= ZM.get(i, j);
      }
      assertEquals(da[j], v.get(j), 0.0d);

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testRot90() {
    DoubleMatrix rot = ZM.rot90();

    System.out.println("rot90");

    System.out.print(rot.get(0, 0) + " ");
    System.out.print(rot.get(0, 1) + " ");
    System.out.println(rot.get(0, 2) + " ");
    System.out.print(rot.get(1, 0) + " ");
    System.out.print(rot.get(1, 1) + " ");
    System.out.println(rot.get(1, 2) + " ");
    System.out.print(rot.get(2, 0) + " ");
    System.out.print(rot.get(2, 1) + " ");
    System.out.println(rot.get(2, 2) + " ");

    assertEquals(rot.get(0, 0), ZM.get(0, 2), 0.0d);
    assertEquals(rot.get(0, 1), ZM.get(1, 2), 0.0d);
    assertEquals(rot.get(0, 2), ZM.get(2, 2), 0.0d);

    assertEquals(rot.get(1, 0), ZM.get(0, 1), 0.0d);
    assertEquals(rot.get(1, 1), ZM.get(1, 1), 0.0d);
    assertEquals(rot.get(1, 2), ZM.get(2, 1), 0.0d);

    assertEquals(rot.get(2, 0), ZM.get(0, 0), 0.0d);
    assertEquals(rot.get(2, 1), ZM.get(1, 0), 0.0d);
    assertEquals(rot.get(2, 2), ZM.get(2, 0), 0.0d);

  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testSin() {
    System.out.println(" \n");

    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.sin().get(i, j) + " ");
        System.out.print(Math.sin(ZM.get(i, j)) + "  ");
        assertEquals(ZM.sin().get(i, j), Math.sin(ZM.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testSqrt() {
    System.out.println(" \n");
    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.sqrt().get(i, j) + " ");
        System.out.print(Math.sqrt(ZM.get(i, j)) + "  ");
        if (ZM.get(i, j) < 0) {
          assertTrue(Double.isNaN(ZM.sqrt().get(i, j)));
          assertTrue(Double.isNaN(Math.sqrt(ZM.get(i, j))));
        } else {
          assertEquals(ZM.sqrt().get(i, j), Math.sqrt(ZM.get(i, j)), 0.0d);
        }
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testSum() {
    DoubleVector v = ZM.sum();
    double[] da = new double[v.size()];

    for (int j = 0; j < v.size(); j++) {
      da[j] = 0.0d;
      for (int i = 0; i < ZM.getRowDimension(); i++) {
        da[j] += ZM.get(i, j);
      }
      assertEquals(da[j], v.get(j), 0.0d);

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testTan() {
    System.out.println(" \n");

    for (int i = 0; i < ZM.getRowDimension(); i++) {
      for (int j = 0; j < ZM.getColumnDimension(); j++) {
        System.out.print(ZM.tan().get(i, j) + " ");
        System.out.print(Math.tan(ZM.get(i, j)) + "  ");
        assertEquals(ZM.tan().get(i, j), Math.tan(ZM.get(i, j)), 0.0d);
      }
      System.out.println();

    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testTimesdouble() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double dd = 9.9d;

    DoubleMatrix dM = new DoubleMatrix(d);

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.times(dd).get(i, j) + " ");
        System.out.print((d[i][j] * dd) + "  ");
        assertEquals(dM.times(dd).get(i, j), d[i][j] * dd, 0.0d);
      }
      System.out.println();
    }
  }

  /**
   * 
   * @since 2004/08/04
   */
  public void testTimesDoubleMatrix() {

    double d[][] = new double[][]
    {
    { 1.2d, 0.3d, -6.0d },
    { 0.25d, -0.9d, 0.0d },
    { 0.2d, -0.1d, -4.5d } };

    double d2[][] = new double[][]
    {
    { 2.1d, 3.3d, -5.0d },
    { 0.25d, -4.9d, 1.0d },
    { 0.6d, -3.1d, 0.0d } };

    DoubleMatrix dM = new DoubleMatrix(d);
    DoubleMatrix dM2 = new DoubleMatrix(d2);

    for (int i = 0; i < dM.getRowDimension(); i++) {
      for (int j = 0; j < dM.getColumnDimension(); j++) {
        System.out.print(dM.times(dM2).get(i, j) + " ");
        System.out.print((d[i][j] * d2[i][j]) + "  ");
        assertEquals(dM.times(dM2).get(i, j), d[i][j] * d2[i][j], 0.0d);
      }
      System.out.println();
    }
  }
}