/*
 * 作成日: 2003/10/20
 */
package name.sugawara.hiroshi.math.operation.integral.typedouble;

import junit.framework.TestCase;
import name.sugawara.hiroshi.math.complex.DoubleComplex;
import name.sugawara.hiroshi.math.function.object.Function;
import name.sugawara.hiroshi.math.function.object.FunctionOfSingleVariable;
import name.sugawara.hiroshi.math.operation.Operation;

/**
 * @author   Hiroshi Sugawara
 * @version   $Id: MatlabSingularTest.java 102 2007-04-10 06:27:03Z sugawara $
 * @since   2003/10/20 16:08:40
 * @uml.stereotype   uml_id="null" isDefined="true" name="tagged"
 */
public class MatlabSingularTest extends TestCase {
  private static final double eps = 10e-10;

  /**
   * Constructor for MatlabSingularTest.
   *
   * @param arg0
   */
  public MatlabSingularTest(String arg0) {
    super(arg0);
  }

  /**
   * @throws Exception
   * @since 2004/08/04
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    long numberOfTerm = 50;
    Function f = new FunctionOfSingleVariable() {
      public Number getDependentVariable(Number x) {
        return new Double(Math.exp(-(x.doubleValue())));
      }
    };

    Integral integral = new Trapezoid(numberOfTerm);
    // double increment = 2.0d;
    long n = 3;
    double gamma = 4.0d;
    MatlabSingular m = new MatlabSingular(integral, n, gamma, eps);

    double begin = 100.0d;

    try {
      Operation o = m.setInterval(new Double(begin));
      DoubleComplex c = (DoubleComplex) o.operate(f);
      System.out.println(c);

      // } catch (ClassCastException e) {
      // e.printStackTrace();
      // } catch (CloneNotSupportedException e) {
      // e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * @throws Exception
   * @since 2004/08/04
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   *
   * @since 2004/08/04
   */
  final public void testSetInterval() {
    // TODO setInterval() をインプリメントします。
  }

  /**
   *
   * @since 2004/08/04
   */
  final public void testOperate() {
    // TODO operate() をインプリメントします。
  }

  /**
   *
   * @since 2004/08/04
   */
  final public void testMatlabSingular() {
    // TODO ComplexMatlabSingular() をインプリメントします。
  }

  /**
   *
   * @since 2004/08/04
   */
  final public void testClone() {
    // TODO clone() をインプリメントします。
  }

  /**
   *
   * @since 2004/08/04
   */
  final public void testEquals() {
    // TODO equals() をインプリメントします。
  }

}
