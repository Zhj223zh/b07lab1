import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Polynomial
{
	double[] coef;
	int[] degree;
	
	public Polynomial() 
	{
		coef = new double[] {0};
		degree = new int[] {0};
	}
	
	public Polynomial(double[] A, int[] B)
	{
		this.coef = A;
		this.degree = B;
	}

	public Polynomial(File file) throws FileNotFoundException 
	{    
        Scanner scanner = new Scanner(file);
        String poly = scanner.nextLine();
        scanner.close();
        coef = new double[poly.length()];
        degree = new int[poly.length()];
        int count = 0;
		int sign = 1;
		for (int i = 0; i < poly.length(); i++)
		{
            if(poly.charAt(i) == '-')
			{
                sign = -1;
                i++;
            }
			else if(poly.charAt(i) == '+')
			{
				sign = 1;
                i++;
            }
            double coefficient = 0;
            while(i < poly.length() && Character.isDigit(poly.charAt(i)))
			{
                coefficient = coefficient * 10 + (poly.charAt(i) - '0');
                i++;
            }
            coefficient *= sign;
            int degreeValue = 0;
            if(i < poly.length() && poly.charAt(i) == 'x')
			{
                i++;
				if(i < poly.length() && Character.isDigit(poly.charAt(i)))
				{
					while(i < poly.length() && Character.isDigit(poly.charAt(i)))
					{
                    	degreeValue = degreeValue * 10 + (poly.charAt(i) - '0');
                    	i++;
                	}
				}
				else
				{
                    degreeValue = 1;
                }
            }
			else
			{
                degreeValue = 0;
            }
            coef[count] = coefficient;
            degree[count] = degreeValue;
            count++;
        }
        double[] finalCoef = new double[count];
        int[] finalDegree = new int[count];
        for (int j = 0; j < count; j++) {
            finalCoef[j] = coef[j];
            finalDegree[j] = degree[j];
        }
        this.coef = finalCoef;
        this.degree = finalDegree;
    }
	
	public Polynomial add(Polynomial A)
	{
		int maxLen = this.degree.length + A.degree.length;
		double[] S = new double[maxLen];
		int[] B = new int[maxLen];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < this.coef.length && j < A.coef.length)
		{
			if(this.degree[i] == A.degree[j])
			{
				double cof = this.coef[i] + A.coef[j];
				S[k] = cof;
				B[k] = this.degree[i];
				i++;
				j++;
				k++;
			}
			else if(this.degree[i] < A.degree[j])
			{
				S[k] = this.coef[i];
				B[k] = this.degree[i];
				i++;
				k++;
			}
			else
			{
				S[k] = A.coef[j];
				B[k] = A.degree[j];
				j++;
				k++;
        	}
		}
		while (i < this.coef.length)
		{
        	S[k] = this.coef[i];
        	B[k] = this.degree[i];
        	i++;
        	k++;
    	}
    	while (j < A.coef.length)
		{
        	S[k] = A.coef[j];
        	B[k] = A.degree[j];
        	j++;
        	k++;
		}
		int len2 = 0;
		for(int t = 0; t < k; t++)
		{
			if(S[t] != 0) t++;
		}
		double[] L = new double[len2];
    	int[] P = new int[len2];
    	int h = 0;
		for(int r = 0; r < len2; r++)
		{
        	if (S[r] != 0)
			{
            	L[h] = S[r];
            	P[h] = B[r];
            	h++;
        	}
    	}
        return new Polynomial(L, P);
	}

	public double evaluate(double x) 
	{
		int len = coef.length;
		double result = 0;
		for(int i = 0; i < len; i++) 
		{
			result += coef[i] * Math.pow(x, degree[i]);
		}
		return result;
	}

	public boolean hasRoot(double x) 
	{
		if(evaluate(x) == 0)
			return true;
		return false;
	}

	public void saveToFile(String fileName) throws IOException 
	{
        FileWriter writer = new FileWriter(fileName, false);
		for(int i = 0; i < coef.length; i++)
		{
            if(i > 0 && coef[i] > 0)
			{
                writer.write("+");
            }
            writer.write(String.valueOf(coef[i]));
            if(degree[i] > 0)
			{
                writer.write("x");
                if(degree[i] > 1)
				{
                    writer.write(String.valueOf(degree[i]));
                }
            }
        }
        writer.close();
    }

	public Polynomial multiply(Polynomial A)
	{
        int maxDegree = this.degree[this.degree.length - 1] + A.degree[A.degree.length - 1];
        double[] resultCoef = new double[maxDegree + 1];
        for (int i = 0; i < this.coef.length; i++)
		{
            for (int j = 0; j < A.coef.length; j++)
			{
                int newDegree = this.degree[i] + A.degree[j];
                resultCoef[newDegree] += this.coef[i] * A.coef[j];
            }
        }
        int nonZero = 0;
        for (int i = 0; i < maxDegree + 1; i++)
		{
            if (resultCoef[i] != 0) nonZero++;
        }
        if (nonZero == 0) return new Polynomial(new double[]{0}, new int[]{0});
        double[] finalCoef = new double[nonZero];
        int[] finalDegree = new int[nonZero];
        int index = 0;
        for (int i = 0; i < resultCoef.length; i++)
		{
            if (resultCoef[i] != 0)
			{
                finalCoef[index] = resultCoef[i];
                finalDegree[index] = i;
                index++;
            }
        }
        return new Polynomial(finalCoef, finalDegree);
    }
}