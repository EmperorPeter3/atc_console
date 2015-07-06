import java.math.BigInteger;

public class FactorialUtil {
    public FactorialAlgorithm algorithm;

    public BigInteger doFactorial(int n)
    {
        //объявляем конструктор какой-нибудь реализации факториала
        algorithm = new CachedFactorialImplementation();
        //alorithm = new LoopedFactorialImplementation();

        // Используем текущий алгоритм
        return algorithm.factorial(n);
    }

}