package test;

public class Algorithm {

    public static boolean isOnlyletter(String s) {
        int flag = 0;
        char[] x = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!((x[i] >= 'a' && x[i] <= 'z') || (x[i] >= 'A' && x[i] <= 'Z'))) {
                flag = 1;
                break;
            }
        }
        return flag != 1;
    }

    public static boolean isOnlyzeroandone(String s) {
        int flag = 0;
        char[] x = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!(x[i] == '0' || x[i] == '1')) {
                flag = 1;
                break;
            }
        }
        return flag != 1;
    }

    public static boolean isOnlynumber(String s) {
        int flag = 0;
        char[] x = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!(x[i] == '0' || x[i] == '1' || x[i] == '2' || x[i] == '3' || x[i] == '4' || x[i] == '5' || x[i] == '6'
                    || x[i] == '7' || x[i] == '8' || x[i] == '9')) {
                flag = 1;
                break;
            }
        }
        return flag != 1;
    }

}
