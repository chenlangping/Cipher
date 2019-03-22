package test;

public class Decrypt {

    public String Caesar(String ciphertext, String key) {
        /*********************/
        char[] cipherrecord = ciphertext.toCharArray();
        int[] record = new int[cipherrecord.length];
        for (int i = 0; i < record.length; i++) {
            if (cipherrecord[i] >= 'a' && cipherrecord[i] <= 'z') {
                record[i] = 0;
            } else if (cipherrecord[i] >= 'A' && cipherrecord[i] <= 'Z') {
                record[i] = 1;
            } else
                record[i] = -1;
        }
        ciphertext = ciphertext.toLowerCase();
        key = key.toLowerCase();
        // ��¼�´���ʱ��Ĵ�Сд������ת��ΪСд
        /**********************/

        String plain;
        char[] plaintext = ciphertext.toCharArray();
        int a;
        a = ciphertext.length();
        for (int i = 0; i < a; i++) {
            if (ciphertext.charAt(i) >= 'a' && ciphertext.charAt(i) <= 'z') {
                if (ciphertext.charAt(i) - key.charAt(0) + 'a' - 1 < 'a')
                    plaintext[i] = (char) (plaintext[i] - (key.charAt(0) - 'a') + 25);
                else
                    plaintext[i] = (char) (plaintext[i] - (key.charAt(0) - 'a') - 1);

            }
            if (ciphertext.charAt(i) >= 'A' && ciphertext.charAt(i) <= 'Z') {
                if (ciphertext.charAt(i) - key.charAt(0) + 'a' - 1 < 'A')
                    plaintext[i] = (char) (plaintext[i] - (key.charAt(0) - 'a') + 25);
                else
                    plaintext[i] = (char) (plaintext[i] - (key.charAt(0) - 'a') - 1);

            }
        }
        plain = new String(plaintext);

        /*****************/
        char[] plainrecord = plain.toCharArray();
        for (int i = 0; i < record.length; i++) {
            if (record[i] == 0) {
            } else if (record[i] == 1) {
                plainrecord[i] = Character.toUpperCase(plainrecord[i]);
            }
        }
        plain = new String(plainrecord);
        // ���Ҫ����ȥ�����ݸ�ԭ
        /******************/

        return plain;
    }

    public String Autocipher(String ciphertext, String key) {
        char[] cipherrecord = ciphertext.toCharArray();
        int[] record = new int[cipherrecord.length];
        for (int i = 0; i < record.length; i++) {
            if (cipherrecord[i] >= 'a' && cipherrecord[i] <= 'z') {
                record[i] = 0;
            } else if (cipherrecord[i] >= 'A' && cipherrecord[i] <= 'Z') {
                record[i] = 1;
            } else
                record[i] = -1;
        }
        ciphertext = ciphertext.toLowerCase();
        key = key.toLowerCase();
        // ��¼�´���ʱ��Ĵ�Сд������ת��ΪСд

        char[][] alphbet = new char[26][26];
        char[] alpht = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int i, j, size, sizet, num;
        char[] text = ciphertext.toCharArray();
        char[] plain = ciphertext.toCharArray();
        char[] keynew = key.toCharArray();
        for (i = 0; i < 26; i++) {
            for (j = 0; j < 26; j++) {
                alphbet[i][j] = alpht[(j + i) % 26];
            }
        } // �õ���
        size = keynew.length;
        for (i = 0; i < text.length; i++) {
            // ����λ��С�ڵ�����Կʱ ������-97+��Կ=����
            // ����λ��������Կʱ ������-97+����=������
            if (i < size) {
                num = (26 + (text[i] - keynew[i])) % 26;
                plain[i] = alpht[num];
            } else if (i >= size) {
                num = (26 + (text[i] - plain[i - size])) % 26;
                plain[i] = alpht[num];
            }
        } // ����������ת������
        String plaintext = new String(plain);

        /*****************/
        char[] plainrecord = plaintext.toCharArray();
        for (int count = 0; count < record.length; count++) {
            if (record[count] == 0) {
            } else if (record[count] == 1) {
                plainrecord[count] = Character.toUpperCase(plainrecord[count]);
            }
        }
        plaintext = new String(plainrecord);
        // ���Ҫ����ȥ������String cipher��ԭ
        /******************/

        return plaintext;
    }

    public String ColumnPermutationcipher(String ciphertext, String key) {
        String plaintext;
        int[] num = new int[100];
        int[] nnum = new int[100];
        int j;
        int clen = ciphertext.length();
        int len = key.length();
        for (int i = 0; i < len; i++) {
            num[i] = 1;
            for (j = 0; j < len; j++) {
                if (key.charAt(j) < key.charAt(i))
                    num[i]++;
            }
            for (j = 0; j < i; j++) {
                if (key.charAt(j) == key.charAt(i))
                    num[i]++;
            }
        }
        for (int i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (i + 1 == num[j])
                    nnum[i] = j;
            }
        }

        int k = 0;
        char[] cipher = new char[clen];
        for (int i = 0; i < clen / len; i++) {
            for (j = 0; j < len; j++) {
                cipher[k] = ciphertext.charAt(nnum[j] * clen / len + i);
                k++;
            }
        }
        plaintext = new String(cipher);
        return plaintext;
    }

    public String Vigenere(String ciphertext, String key) {
        /***************************/
        char[] cipherrecord = ciphertext.toCharArray();
        int[] record = new int[cipherrecord.length];
        for (int i = 0; i < record.length; i++) {
            if (cipherrecord[i] >= 'a' && cipherrecord[i] <= 'z') {
                record[i] = 0;
            } else if (cipherrecord[i] >= 'A' && cipherrecord[i] <= 'Z') {
                record[i] = 1;
            } else
                record[i] = -1;
        }
        ciphertext = ciphertext.toLowerCase();
        key = key.toLowerCase();
        // ��¼�´���ʱ��Ĵ�Сд������ת��ΪСд
        /*****************************/
        String a = "abcdefghijklmnopqrstuvwxyz";
        char[] b = a.toCharArray();
        char[][] table = new char[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++)
                table[i][j] = b[(j + i) % 26];
        }
        char[] plain = ciphertext.toCharArray();
        String plaintext;
        char[] cipher = ciphertext.toCharArray();
        int c = key.length();
        int d = ciphertext.length();
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < 26; j++) {
                if ((table[key.charAt(i % c) - 97][j]) == cipher[i]) {
                    plain[i] = table[0][j];
                    break;
                }
            }
        }
        plaintext = new String(plain);
        /*****************/
        char[] plainrecord = plaintext.toCharArray();
        for (int count = 0; count < record.length; count++) {
            if (record[count] == 0) {
            } else if (record[count] == 1) {
                plainrecord[count] = Character.toUpperCase(plainrecord[count]);
            }
        }
        plaintext = new String(plainrecord);
        // ���Ҫ����ȥ������String cipher��ԭ
        /******************/
        return plaintext;
    }

    public String Keyword(String ciphertext, String key) {
        /***************************/
        char[] cipherrecord = ciphertext.toCharArray();
        int[] record = new int[cipherrecord.length];
        for (int i = 0; i < record.length; i++) {
            if (cipherrecord[i] >= 'a' && cipherrecord[i] <= 'z') {
                record[i] = 0;
            } else if (cipherrecord[i] >= 'A' && cipherrecord[i] <= 'Z') {
                record[i] = 1;
            } else
                record[i] = -1;
        }
        ciphertext = ciphertext.toLowerCase();
        key = key.toLowerCase();
        // ��¼�´���ʱ��Ĵ�Сд������ת��ΪСд
        /*****************************/
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabe = alphabet.toCharArray();// alphabeΪ�ַ�������ʽ ��ʼ��ĸ��
        char[] keyy = key.toCharArray();// keyyΪ�ַ�������ʽ ��Կ
        char[] text = ciphertext.toCharArray();// textΪ�ַ�������ʽ ����
        char[] alphabett = new char[26];// alphabettΪ����ĸ����ַ�������ʽ
        int size, i, k = 1, j, l, m, sizet;
        size = keyy.length;
        for (i = 1; i < size; i++) {
            for (j = 0; j < i; j++) {
                if (keyy[i] == keyy[j])
                    break;
                if (j == i - 1) {
                    keyy[k] = keyy[i];
                    k++;
                }
            }
        } // ɾ���ظ�������Կ
        m = k;
        for (j = 0; j < k; j++)
            alphabett[j] = keyy[j];// ������ĸ���ײ�����
        for (j = 0; j < 26; j++) {
            for (l = 0; l < k; l++) {
                if (alphabe[j] == keyy[l])
                    break;
                if (l == k - 1) {
                    alphabett[m] = alphabe[j];
                    m++;
                }
            }
        } // �õ�����Կ������ĸ��
        String alphabettt = new String(alphabett);
        sizet = text.length;
        for (i = 0; i < sizet; i++) {
            for (j = 0; j < 26; j++) {
                if (text[i] - alphabett[j] == 0)
                    k = j;
            }
            text[i] = alphabe[k];
        }
        String plaintext = new String(text);
        /*****************/
        char[] plainrecord = plaintext.toCharArray();
        for (int count = 0; count < record.length; count++) {
            if (record[count] == 0) {
            } else if (record[count] == 1) {
                plainrecord[count] = Character.toUpperCase(plainrecord[count]);
            }
        }
        plaintext = new String(plainrecord);
        // ���Ҫ����ȥ������String cipher��ԭ
        /******************/
        return plaintext;
    }

    public String Permutationcipher(String ciphertext, String key) {

        char[] keytext = key.toCharArray();
        int[] order = new int[keytext.length];

        char[] cipher = ciphertext.toCharArray();
        double c = Math.ceil((double) (cipher.length) / (double) (keytext.length));
        char[][] newcipher = new char[(int) c][keytext.length];
        char[][] plain = new char[(int) c][keytext.length];
        int i = 0, j;
        char[] newplain = new char[(int) c * keytext.length];
        for (i = keytext.length - 1; i >= 0; i--) {
            for (j = i - 1; j >= 0; j--) {
                if (keytext[i] < keytext[j])
                    order[j]++;
                else
                    order[i]++;
            }
        } // �õ���Կ˳��ļ���㷨��
        /*
         * for(i=0;i<5;i++) System.out.println(order[i]);
         */
        for (i = 0; i < (int) c; i++) {
            for (j = 0; j < keytext.length; j++) {
                newcipher[i][j] = cipher[(keytext.length) * i + j];
            }
        } // ������ ����洢��
        for (i = 0; i < (int) c; i++) {
            for (j = 0; j < keytext.length; j++) {
                plain[i][j] = newcipher[i][order[j]];
            }
        }
        for (i = 0; i < (int) c; i++) {
            for (j = 0; j < keytext.length; j++) {
                newplain[keytext.length * i + j] = plain[i][j];
            }
        }
        String plaintext = new String(newplain);
        return plaintext;
    }

    public String DES(String ciphertext, String key) {

        String ciphertextcopy = ciphertext;
        String finalciphertext = "";
        for (int cyle = 0; cyle < ciphertextcopy.length(); cyle = cyle + 64) {
            ciphertext = ciphertextcopy.substring(cyle, cyle + 64);

            // ��ԿPC-1��
            final int[] PC1_Table = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27,
                    19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45,
                    37, 29, 21, 13, 5, 28, 20, 12, 4};

            // ��ԿPC-2��
            final int[] PC2_Table = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20,
                    13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
                    32};

            // ��Կÿ�׶�����λ���б�����
            final int[] LOOP_Table = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

            // ���ĳ�ʼ��λ�� IP
            final int[] IP_Table = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30,
                    22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11,
                    3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};

            // ���ĵ�16�λ�λ��
            final int[] IPR_Table = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54,
                    22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59,
                    27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};

            // E-boxes
            final int[] E_Table = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

            // P-boxes
            final int[] PBox = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3,
                    9, 19, 13, 30, 6, 22, 11, 4, 25};

            // S-boxes
            final int[][][] S_Box = {
                    // S1
                    {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},
                    // S2
                    {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},
                    // S3
                    {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},
                    // S4
                    {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},
                    // S5
                    {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},
                    // S6
                    {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},
                    // S7
                    {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},
                    // S8
                    {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}};

            int[] key1 = new int[56]; // �����û�����Ĺؼ��ֵõ���56λ��Կ
            int[] key2 = new int[64]; // �������żУ��λ��64λ��Կ
            int[] key3 = new int[56]; // ����PC-1֮�����Կ
            int i, j; // ���ѭ������
            int temp; // ����м����
            int r; // ����
            int[] p = new int[64]; // ����ת����ASCII��֮��
            int[] p2 = new int[64]; // ���ľ���IT�任
            char[] keyword = new char[7]; // ����û������7λ�ĵ���
            char[] plaintxt = new char[8]; // �������
            int[] lp = new int[32];
            int[] rp = new int[32];// �ֱ��Ӧ����벿�ֺ��Ұ벿��
            int[] erp = new int[48]; // ��ž���EBOX֮���
            int[] srp = new int[48]; // ��Ž���SBOX֮��������Կ�������֮���
            int[][] subkey = new int[16][48];// ��ά������16������Կ
            int[] sp = new int[32]; // ��ž���SBOX֮���
            int[] prp = new int[32]; // ��ž���PBOS֮���
            int[] ciphertext1 = new int[64]; // �������
            int[] last = new int[64]; // ������һ�β���֮ǰ��
            int[] copyrp = new int[32];// �Ұ벿�ֵı���
            int column, line; // ��Ӧ��BOX��������

            keyword = key.toCharArray();
            for (i = 0; i < 64; i++) {
                ciphertext1[i] = Integer.parseInt(String.valueOf(ciphertext.charAt(i)));
            }

            for (i = 0; i < 7; i++) {
                temp = (int) keyword[i];
                for (j = 8 * i + 7; j >= 8 * i; j--) {

                    key1[j] = temp % 2;
                    temp = temp / 2;
                }
            } // ���ݹؼ���������Կ

            for (i = 0; i < 64; i++) {
                j = i + i / 7;
                if (j < 64) {

                    key2[i + i / 7] = key1[i];
                }
            }
            for (i = 7; i < 64; i = i + 8) {
                key2[i] = (key2[i - 1] + key2[i - 2] + key2[i - 3] + key2[i - 4] + key2[i - 5] + key2[i - 6]
                        + key2[i - 7] + 1) % 2;
            } // �������żУ��λ

            for (i = 0; i < 56; i++) {
                key3[i] = key2[PC1_Table[i] - 1];
            } // ����PC-1

            for (r = 0; r < 16; r++) {
                if (r == 0 || r == 1 || r == 8 || r == 15) {
                    temp = key3[0];
                    for (i = 0; i < 27; i++) {
                        key3[i] = key3[i + 1];
                    }
                    key3[27] = temp;

                    temp = key3[28];
                    for (i = 28; i < 55; i++) {
                        key3[i] = key3[i + 1];
                    }
                    key3[55] = temp;
                } // ����ǵ�1,2,9,16�֣�ֻ��Ҫѭ������һ��
                else {
                    temp = key3[0];
                    for (i = 0; i < 27; i++) {
                        key3[i] = key3[i + 1];
                    }
                    key3[27] = temp;
                    temp = key3[0];
                    for (i = 0; i < 27; i++) {
                        key3[i] = key3[i + 1];
                    }
                    key3[27] = temp;

                    temp = key3[28];
                    for (i = 28; i < 55; i++) {
                        key3[i] = key3[i + 1];
                    }
                    key3[55] = temp;
                    temp = key3[28];
                    for (i = 28; i < 55; i++) {
                        key3[i] = key3[i + 1];
                    }
                    key3[55] = temp;
                } // �������������ѭ������

                for (i = 0; i < 48; i++) {
                    subkey[r][i] = key3[PC2_Table[i] - 1];
                }

            } // ����Կ�������
            for (i = 0; i < 64; i++) {
                p[i] = ciphertext1[i];
            }

            for (i = 0; i < 64; i++) {
                p2[i] = p[IP_Table[i] - 1];
            } // ����IT����

            for (i = 0; i < 32; i++) {
                lp[i] = p2[i];
                rp[i] = p2[i + 32];
                copyrp[i] = p2[i + 32];
            } // �ֳ����Ҳ���

            // ����ѭ����ʼ
            for (r = 15; r >= 0; r--) {
                for (i = 0; i < 48; i++) {
                    erp[i] = rp[E_Table[i] - 1];
                } // ����E-BOX

                for (i = 0; i < 48; i++) {
                    if (subkey[r][i] == 1 && erp[i] == 1)
                        srp[i] = 0;
                    else if (subkey[r][i] == 1 && erp[i] == 0)
                        srp[i] = 1;
                    else if (subkey[r][i] == 0 && erp[i] == 1)
                        srp[i] = 1;
                    else if (subkey[r][i] == 0 && erp[i] == 0)
                        srp[i] = 0;
                } // ����������֮��

                for (i = 0; i < 8; i++) {
                    line = srp[6 * i] * 2 + srp[6 * i + 5] * 1;
                    column = srp[6 * i + 1] * 8 + srp[6 * i + 2] * 4 + srp[6 * i + 3] * 2 + srp[6 * i + 4] * 1;
                    temp = S_Box[i][line][column];
                    for (j = i * 4 + 3; j >= i * 4; j--) {
                        sp[j] = temp % 2;
                        temp = temp / 2;
                    }
                }

                for (i = 0; i < 32; i++) {
                    prp[i] = sp[PBox[i] - 1];
                } // ����PBOX����

                for (i = 0; i < 32; i++) {
                    if (lp[i] == 1 && prp[i] == 1)
                        rp[i] = 0;
                    else if (lp[i] == 1 && prp[i] == 0)
                        rp[i] = 1;
                    else if (lp[i] == 0 && prp[i] == 1)
                        rp[i] = 1;
                    else if (lp[i] == 0 && prp[i] == 0)
                        rp[i] = 0;
                } // ��lp��򣬲���ֵ���Ұ벿��

                for (i = 0; i < 32; i++) {
                    lp[i] = copyrp[i];
                    copyrp[i] = rp[i];
                }
            } // ����ѭ������

            for (i = 0; i < 32; i++) {
                last[i] = rp[i];
            }
            for (i = 0; i < 32; i++) {
                last[i + 32] = lp[i];
            } // ���Һϲ���������һ�ν���

            for (i = 0; i < 64; i++) {
                ciphertext1[i] = last[IPR_Table[i] - 1];
                System.out.print(ciphertext1[i]);
                if ((i + 1) % 8 == 0)
                    System.out.println("\n");
            }
            int[] Dec = new int[8];
            Dec[0] = ciphertext1[0];
            for (int i1 = 1, k = 0; i1 < 64; i1++) {
                if (i1 % 8 != 0) {
                    Dec[k] = (Dec[k] * 2) + ciphertext1[i1];
                } else {
                    k++;
                    Dec[k] = ciphertext1[i1];
                }

            }
            for (i = 0; i < 8; i++) {
                finalciphertext = finalciphertext + (char) Dec[i];
            }
            System.out.println(finalciphertext);
        }
        return finalciphertext;
    }

    public String CA(String ciphertext, String key, String Cell) {

        CAalgorithm ca = new CAalgorithm(key, Cell);
        String plainptext = ca.CAcrypt(ciphertext);
        return plainptext;
    }

    public String RSA(String ciphertext) {
        String s = ciphertext;
        String s2 = "";
        try {
            //RSAalgorithm.generateKey();
            s2 = RSAalgorithm.decrypt(s);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return s2;
    }

}
