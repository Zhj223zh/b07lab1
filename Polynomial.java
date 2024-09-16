public class Polynomial
{
	double[] coef;
	
	public Polynomial() 
	{
		coef = new double[] {0};
	}
	
	public Polynomial(double[] A)
	{
		coef = A.clone();
	}
	
	public Polynomial add(Polynomial A)
	{
		int maxDegree = Math.max(this.coef.length, A.coef.length);
        double[] resultCoef = new double[maxDegree];
        for (int i = 0; i < this.coef.length; i++)
        {
            resultCoef[i] = this.coef[i];
        }
        for (int i = 0; i < A.coef.length; i++)
        {
            resultCoef[i] += A.coef[i];
        }
        return new Polynomial(resultCoef);
	}
	public double evaluate(double x) 
	{
		int len = coef.length;
		double result = 0;
		for(int i = 0; i < len; i++) 
		{
			result += coef[i] * Math.pow(x, i);
		}
		return result;
	}
	public boolean hasRoot(double x) 
	{
		if(evaluate(x) == 0)
			return true;
		return false;
	}
}