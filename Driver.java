import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Driver {
public static void main(String [] args) throws Exception {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,5};
int [] c3 = {0,3};
Polynomial p1 = new Polynomial(c1, c3);
double [] c2 = {-2,-9};
int [] c4 = {1,4};
Polynomial p2 = new Polynomial(c2, c4);
Polynomial s = p1.add(p2);
System.out.println("s(0.1) = " + s.evaluate(0.1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
Polynomial product = p1.multiply(p2);
System.out.println("p1 * p2 evaluated at x = 1 is: " + product.evaluate(1));
product.saveToFile("polynomial.txt");
System.out.println("Polynomial product saved to polynomial.txt");
Polynomial pFile = new Polynomial(new File("polynomial.txt"));
System.out.println("Polynomial read from file: " + pFile.evaluate(1));}
}
