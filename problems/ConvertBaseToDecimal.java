import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertBaseToDecimal {
    private static int valueOfChar(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        else
            return c - 'A' + 10;
    }

    private static int baseToDecimal(String num, int base) {
        int res = 0, power = 1;
        for (int i = num.length() - 1; i >= 0; i--) {
            int val = valueOfChar(num.charAt(i));
            if (val >= base)
                return -1;

            res += val * power;
            power *= base;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        String num = line.split("\\s+")[0];
        int base = Integer.parseInt(line.split("\\s+")[1]);
        int res = baseToDecimal(num, base);
        if (res == -1)
            System.out.println("Invalid number");
        else
            System.out.printf("Decimal equivalent of %s in base %d is %d\n", num, base, res);
    }
}
