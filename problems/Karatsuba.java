import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Karatsuba {

    private static BigInteger karatsuba(BigInteger x, BigInteger y) {
        if (x.compareTo(BigInteger.valueOf(10)) < 0 && y.compareTo(BigInteger.valueOf(10)) < 0)
            return x.multiply(y);

        int lenx = String.valueOf(x).length();
        int leny = String.valueOf(y).length();
        int n = Math.max(lenx, leny);
        int m = n / 2;

        BigInteger a = x.divide(BigInteger.valueOf(10).pow(m));
        BigInteger b = x.mod(BigInteger.valueOf(10).pow(m));
        BigInteger c = y.divide(BigInteger.valueOf(10).pow(m));
        BigInteger d = y.mod(BigInteger.valueOf(10).pow(m));

        BigInteger z2 = karatsuba(a, c);
        BigInteger z0 = karatsuba(b, d);
        BigInteger z1 = karatsuba(a.add(b), c.add(d));
        BigInteger z3 = z1.subtract(z2).subtract(z0);

        return z2.multiply(BigInteger.valueOf(10).pow(2 * m)).add(z0).add(z3.multiply(BigInteger.valueOf(10).pow(m)));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = bufferedReader.readLine().split("\\s+");
        String[] line2 = bufferedReader.readLine().split("\\s+");
        BigInteger x = new BigInteger(line1[0]);
        BigInteger y = new BigInteger(line2[0]);
        System.out.println(karatsuba(x, y));
    }
}
