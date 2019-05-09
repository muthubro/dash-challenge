import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegistrationNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        String pattern = "KA51 (..) (\\d+), (\\d+)";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        if (m.find()) {
            Integer n = Integer.parseInt(m.group(3));
            Integer last = Integer.parseInt(m.group(2));
            String alpha = m.group(1);

            Boolean status = true;

            while (true) {
                Integer dif = 10000 - last;

                if (n < dif) {
                    last += n;
                    break;
                }

                last = 0;
                n -= dif;

                if (alpha.charAt(1) == 'Z') {
                    if (alpha.charAt(0) == 'Z') {
                        status = false;
                        break;
                    }
                    String temp = "";
                    temp += (char) (alpha.charAt(0) + 1);
                    temp += 'A';
                    alpha = temp;
                }

                else {
                    String temp = "";
                    temp += alpha.charAt(0);
                    temp += alpha.charAt(1) + 1;
                    alpha = temp;
                }
            }

            String l = String.format("%d", last);
            if (l.length() == 1) l = "000" + l;
            else if (l.length() == 2) l = "00" + l;
            else if (l.length() == 3) l = "0" + l;
            
            if (status == false) System.out.println("invalid number");

            else System.out.println("KA51 " + alpha + " " + l);
        } else {
            System.out.println("Invalid Registration");
        }
    }
}
