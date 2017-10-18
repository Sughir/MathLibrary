/*
 * Created on 2003/06/26
 * 
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import junit.framework.TestCase;

/**
 * @author   Hiroshi Sugawara
 * @since   1.1
 * @version   $Id: TrapezoidTest.java 108 2010-01-13 14:55:16Z sugawara $
 * @uml.stereotype   uml_id="null" isDefined="true" name="tagged" 
 */

public class TrapezoidTest extends TestCase {

  /**
   * Constructor for TrapezoidTest.
   * 
   * @param arg0
   */
  public TrapezoidTest(String arg0) {
    super(arg0);
  }

  /**
   * 
   * @uml.property name="n"
   */
  private static long   n;

  /**
   * 
   * @uml.property name="a"
   */
  private static double a;

  /**
   * 
   * @uml.property name="b"
   */
  private static double b;

  /*
   * @see TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    n = 8192;
    a = -20.0d;
    b = 30.0d;
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
   * @since 2004/08/03
   */
  public void testTrapezoid() {
    Trapezoid t = new Trapezoid(n);
    assertNotNull(t);
  }

  /**
   * 
   * @since 2004/08/03
   */
  public void testSetInterval() {

    Trapezoid t = new Trapezoid(n);
    t.setInterval(new Double(a), new Double(b));
  }

  /**
   * 
   * @since 2004/08/03
   */
  public void testOperate() {

    // 積分のためにsin(x^2) を定義する.
    Function o = new FunctionOfSingleVariable() {
      public Number getDependentVariable(Number x) {
        double d = x.doubleValue();
        return new Double(Math.sin(d * d));
      }
    };

    // 積分のためにexp(x^2) を定義する.
    Function exp = new FunctionOfSingleVariable() {
      public Number getDependentVariable(Number x) {
        double d = x.doubleValue();
        return new Double(Math.exp(-(d * d)));
      }
    };

    Trapezoid t = new Trapezoid(n);
    RiemannLeftEnd rl = new RiemannLeftEnd(n);
    RiemannRightEnd rr = new RiemannRightEnd(n);

    // Trapezoid t2 = new Trapezoid(16777216);
    RiemannLeftEnd rl2 = new RiemannLeftEnd(16777216);
    RiemannRightEnd rr2 = new RiemannRightEnd(16777216);

    double st = 0.0d, srl = 0.0d, srr = 0.0d, erl = 0.0d, err = 0.0d, et = 0.0d, erl2 = 0.0d, err2 = 0.0d, et2 = 0.0d;

    st = t.setInterval(new Double(a), new Double(b)).operate(o).doubleValue();
    srl = rl.setInterval(new Double(a), new Double(b)).operate(o).doubleValue();
    srr = rr.setInterval(new Double(a), new Double(b)).operate(o).doubleValue();

    erl = rl.setInterval(new Double(a), new Double(b)).operate(exp)
        .doubleValue();
    err = rr.setInterval(new Double(a), new Double(b)).operate(exp)
        .doubleValue();
    et = rl.setInterval(new Double(a), new Double(b)).operate(exp)
        .doubleValue();

    erl2 = rl2.setInterval(new Double(-10e307), new Double(10e307))
        .operate(exp).doubleValue();
    err2 = rr2.setInterval(new Double(-10e307), new Double(10e307))
        .operate(exp).doubleValue();
    et2 = rl2.setInterval(new Double(-10e307), new Double(10e307)).operate(exp)
        .doubleValue();

    System.out.println("srl =" + srl);
    System.out.println("srr =" + srr);
    System.out.println("st =" + st);

    assertEquals(srl, srr, 10.0d);
    assertEquals(st, srl, 10.0d);
    double approximate = Math.cos(20) - Math.cos(30);
    System.out.println("approximate =" + approximate);
    assertEquals(st, approximate, 1.0e-5);

    assertEquals(erl, err, 0.0d);
    assertEquals(erl, et, 0.0d);

    assertEquals(erl2, err2, 0.0d);
    assertEquals(erl2, et2, 0.0d);

    System.out.println("erl =" + erl);
    System.out.println("err =" + err);
    System.out.println("et =" + et);

    System.out.println("erl2 =" + erl2);
    System.out.println("err2 =" + err2);
    System.out.println("et2 =" + et2);

    System.out.println("end");

  }
}