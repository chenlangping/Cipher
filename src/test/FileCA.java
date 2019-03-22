package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCA {
    public char[] Cell;
    private int CELLSIZE;
    private int RULE;
    private char HEAD;
    private int[] RuleTable;

    public FileCA(String Rule, String Cell) {
        CELLSIZE = Cell.length();

        this.Cell = Cell.toCharArray();                //转换Cell to int
        int[] cell = new int[Cell.length()];
        for (int i = 0; i < Cell.length(); i++) {
            cell[i] = this.Cell[i] - '0';
        }


        for (int i = 0; i < Rule.length(); i++) {
            RULE = RULE * 10 + (Rule.charAt(i) - '0');
        }


        RuleTable = new int[CELLSIZE];

        for (int i = CELLSIZE - 1; i >= 0; i--) {
            RuleTable[i] = RULE % 2;
            RULE = RULE / 2;
        }

        for (int i = CELLSIZE - 1; i > 0; i--) {
            HEAD = (char) ((int) HEAD + cell[i] * (int) Math.pow(2, 7 - i));
        }
    }


    public void filecryp(File f1, File f2) {
        byte[] buf = new byte[1024];
        byte[] key;
        int recv = 0;
        try {
            FileInputStream in = new FileInputStream(f1);
            FileOutputStream out = new FileOutputStream(f2);
            while ((recv = in.read(buf)) != -1) {
                key = this.CreateKey(recv).getBytes();
                for (int i = 0; i < recv; i++) {
                    buf[i] = (byte) (buf[i] ^ key[i]);
                }
                out.write(buf);
            }
        } catch (Exception e) {
        }
    }

    /*
     * 以下是工具方法
     */
    public int GetNeighborNum(int i, char Chr) {
        int MASK = 7 * (int) Math.pow(2, 5 - i);
        int num = 0;
        if (i >= 0 && i <= 5)
            num = ((int) Chr & MASK) / (int) Math.pow(2, 5 - i);
        else {
            int flag = 0;
            flag = ((int) Math.pow(2, CELLSIZE - 1) & Chr) / (int) Math.pow(2, CELLSIZE - 1);
            for (int j = i - 5; j > 0; j--) {
                int temp = (int) Chr;
                Chr = (char) (temp * 2 + flag);
                //System.out.println("plainchar: " + (int)Chr);
                flag = ((int) Math.pow(2, CELLSIZE - 1) & Chr) / (int) Math.pow(2, CELLSIZE - 1);
            }
            //System.out.println("final Chr: " + (int)Chr);
            num = (int) Chr & 7;
        }
        return num;
    }

    public char CellAutomaton(char SupCell) {
        char SubCell = 0;
        for (int i = CELLSIZE - 1; i > 0; i--) {
            SubCell = (char) ((int) SubCell + (int) Math.pow(2, 7 - i) * RuleTable[GetNeighborNum(i, SupCell)]);
        }
        return SubCell;
    }

    public String CreateKey(int len) {
        Cell = new char[len];
        //Cell[len] = '\0';
        for (int i = CELLSIZE - 1; i > 0; i--)
            Cell[0] = CellAutomaton(HEAD);

        for (int i = 1; i < len; i++)
            Cell[i] = CellAutomaton(Cell[i - 1]);

        return new String(Cell);

    }

    public void show() {
        System.out.println("CELLSIZE: " + CELLSIZE);
        System.out.println("RULE: " + RULE);
        System.out.print("RuleTable: ");
        for (int i = 0; i < CELLSIZE; i++) {
            System.out.print(RuleTable[i] + " ");
        }

    }
}
