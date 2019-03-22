package test;

public class CAalgorithm {
    public int[] RuleTable;
    private int CELLSIZE;
    private int RULE;
    private String Cell;
    private String Key;
    private char[] key;

    public CAalgorithm(String Rule, String cell) {
        CELLSIZE = cell.length();        //�趨CELL����
        Cell = cell;
        RULE = Integer.parseInt(Rule);                //�Զ���ȷ������
        RuleTable = new int[CELLSIZE];        //��������ӳ���

        //System.out.print("ruletable is: ");
        for (int i = CELLSIZE - 1; i >= 0; i--) {        //�ѹ�����ת��Ϊ���򣨶����ƣ��� ���Ҵ���������
            RuleTable[i] = RULE % 2;
            RULE = RULE / 2;
            //System.out.print(RuleTable[i]+",");
        }        //ע������ߵ�λ��ԭ���������ĸߵ�λ�պ��෴

    }


    public String CAcrypt(String text) {        //�ӽ���
        char[] txt = text.toCharArray();
        CreateKey(text.length());        //������Կ

        for (int i = 0; i < text.length(); i++) {            //������
            if (txt[i] == key[i]) txt[i] = '0';
            else if (txt[i] != key[i]) txt[i] = '1';
        }

        String finaltext = new String(txt);
        return finaltext;
    }

    /*
     * �����ǹ��߷���
     */
    public int GetNeighborNum(int i, String txt) {
        char[] s = txt.toCharArray();
        int mapnum = 0;
        if ((i % CELLSIZE) >= 0 && (i % CELLSIZE) <= CELLSIZE - 3)
            mapnum = 4 * (s[i] - '0') + 2 * (s[i + 1] - '0') + s[i + 2] - '0';
        else {
            if (i % CELLSIZE == CELLSIZE - 2)
                mapnum = 4 * (s[i] - '0') + 2 * (s[(i + 1)] - '0') + s[(i + 2) - CELLSIZE] - '0';
            else if (i % CELLSIZE == CELLSIZE - 1)
                mapnum = 4 * (s[i] - '0') + 2 * (s[(i + 1) - CELLSIZE] - '0') + s[(i + 2) - CELLSIZE] - '0';
        }
        return mapnum;
    }

    public int GetNeighborNum(int i, char[] s) {        //����GetNeighborNum(int, String)
        int mapnum = 0;
        if ((i % CELLSIZE) >= 0 && (i % CELLSIZE) <= CELLSIZE - 3)
            mapnum = 4 * (s[i] - '0') + 2 * (s[i + 1] - '0') + s[i + 2] - '0';
        else {
            if (i % CELLSIZE == CELLSIZE - 2)
                mapnum = 4 * (s[i] - '0') + 2 * (s[(i + 1)] - '0') + s[(i + 2) - CELLSIZE] - '0';
            else if (i % CELLSIZE == CELLSIZE - 1)
                mapnum = 4 * (s[i] - '0') + 2 * (s[(i + 1) - CELLSIZE] - '0') + s[(i + 2) - CELLSIZE] - '0';
        }
        return mapnum;
    }

    public String CreateKey(int len) {
        key = new char[len];
        System.out.print("\nkey is: ");
        //System.out.println(RuleTable[GetNeighborNum(1, Cell)]);

        for (int i = 0; i < CELLSIZE; i++) {
            key[i] = (char) (RuleTable[GetNeighborNum(i, Cell)] + '0');
            //System.out.print(key[i]);
        }

        for (int i = CELLSIZE; i < len; i++)
            key[i] = (char) (RuleTable[GetNeighborNum(i - CELLSIZE, key)] + '0');

        Key = new String(key);
        return Key;
    }
}
