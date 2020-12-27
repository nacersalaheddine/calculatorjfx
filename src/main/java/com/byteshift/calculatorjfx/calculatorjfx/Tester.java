package main.java.com.byteshift.calculatorjfx.calculatorjfx;

/**
 *
 * @author Nacer Salah Eddine
 */
public class Tester {

    public static String currResultStr;

    private static String filterTrailingZeros(String result) {
        if (result.matches(".+(\\.)") || result.matches(".+(\\.)0+")) {
            //remove the trailing dot or sequence of zeros
            result = result.substring(0, result.indexOf("."));
        } else if (result.matches(".+0+")) {
            //ex: 12.050500000 ==> 12.0505
            return result.replaceAll("0+$","");

        }
        return result;
    }

    public static void main(String[] args) {
        currResultStr = "12.050500000";
        System.out.println(filterTrailingZeros(currResultStr));;
        currResultStr = "12.";
        System.out.println(filterTrailingZeros(currResultStr));;
        currResultStr = "12.00000";
        System.out.println(filterTrailingZeros(currResultStr));;
        /*
        Tester tester = new Tester();
        tester.showFormatedResult("2456");
        tester.showFormatedResult("12345");
        tester.showFormatedResult("123456");
        tester.showFormatedResult("1234567");
        tester.showFormatedResult("12345678");
        tester.showFormatedResult("123456789");
        tester.showFormatedResult("123456781");

        System.out.println("-----------");

        tester.showFormatedResult("-12345");
        tester.showFormatedResult("-123456");
        tester.showFormatedResult("-1234567");
        tester.showFormatedResult("-12345678");
        tester.showFormatedResult("-123456789");
        tester.showFormatedResult("-1234567812");
        tester.showFormatedResult("-12345678123");
        tester.showFormatedResult("-123456781234");
        tester.showFormatedResult("-1234567812345");

        System.out.println("------/////-----");

        tester.showFormatedResult("1.2345");
        tester.showFormatedResult("9");
        tester.showFormatedResult("12.3456");
        tester.showFormatedResult("123.4567");
        tester.showFormatedResult("12345.678");
        tester.showFormatedResult("1234567.89");
        tester.showFormatedResult("123456781.2");
        tester.showFormatedResult("12345678123.0");
        tester.showFormatedResult("123456781234.");
        tester.showFormatedResult("0.1234567812345");

        System.out.println("------00-----");

        tester.showFormatedResult("-1.2345");
        tester.showFormatedResult("-12.3456");
        tester.showFormatedResult("-123.4567");
        tester.showFormatedResult("-12345.678");
        tester.showFormatedResult("-1234567.89");
        tester.showFormatedResult("-123456781.2");
        tester.showFormatedResult("-12345678123.0");
        tester.showFormatedResult("-123456781234.");
        tester.showFormatedResult("-0.1234567812345");
        tester.showFormatedResult("-1111.");
        tester.showFormatedResult("0");
         */
    }
    //https://www.facebook.com/yara.moharam.5/
    //https://www.instagram.com/raxvell_portraits/

    private void showFormatedResult(String s) {
        //-1,23456.25889
        String[] splt = s.split("\\.");
        String newS = splt[0];
        StringBuilder sb = null;
        //System.out.println(">>"+newS);
        int len = newS.length();
        int i = 0;
        if (s.charAt(0) == '-') {
            i++;
        }

        if (len - i >= 4) {
            sb = new StringBuilder();
            char[] sc = newS.toCharArray();

            for (; i < len; i++) {
                sb.append(sc[i]);
                if ((len - i - 1) % 3 == 0 && ((len - i - 1) > 0)) {
                    sb.append(",");
                }

            }

        }

        if (sb != null) {
            if (splt.length > 1) {
                sb.append(".").append(splt[1]);
            } else if (s.charAt(s.length() - 1) == '.') {
                sb.append(".");
            }
            if (s.charAt(0) == '-') {
                sb.insert(0, "-");
            }

            System.out.println(sb);
        } else {
            System.out.println(s);
        }
    }

}
