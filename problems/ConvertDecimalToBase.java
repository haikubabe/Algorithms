import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertDecimalToBase {

    private static char valueOfDigit(int d) {
        if (d >= 0 && d <= 9)
            return (char) (d + '0');
        else
            return (char) (d - 10 + 'A');
    }

    private static String decimalToBase(int num, int base) {
        StringBuilder stringBuilder = new StringBuilder();
        while (num > 0) {
            int rem = num % base;
            char val = valueOfDigit(rem);
            stringBuilder.append(val);
            num = num / base;
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        int num = Integer.parseInt(line.split("\\s+")[0]);
        int base = Integer.parseInt(line.split("\\s+")[1]);
        System.out.printf("Base %d equivalent of decimal number %d is %s\n", base, num, decimalToBase(num, base));
    }
}
