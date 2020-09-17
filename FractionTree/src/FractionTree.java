//Quan Do
//CECS 328 Sec 4 & 5

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class FractionTree {
    public static BigInteger M = new BigInteger("0");
    public static BigInteger N = new BigInteger("0");
    public static BigDecimal num = new BigDecimal("0");

    public static void main(String[] args) throws IOException {
        File fl = new File("input.txt");
        Scanner scan = new Scanner(fl);
        M = new BigInteger(scan.next());
        N = new BigInteger(scan.next());
        num = new BigDecimal(M).divide(new BigDecimal(N), new MathContext(5000, RoundingMode.HALF_EVEN)).sqrt(new MathContext(5000));

        ArrayList<BigInteger[]> tree = new ArrayList<>();
        tree.add(new BigInteger[] {BigInteger.valueOf(0), BigInteger.valueOf(1)});
        tree.add(new BigInteger[] {BigInteger.valueOf(1), BigInteger.valueOf(0)});

            ArrayList<BigInteger[]> modTree = (ArrayList<BigInteger[]>) tree.clone();
            for (int i = 0; i < tree.size() - 1; i++) {
                BigInteger[] left = tree.get(i);
                BigInteger[] right = tree.get(i + 1);
                modTree.add(modTree.indexOf(left) + 1, new BigInteger[]{left[0].add(right[0]), left[1].add(right[1])});
            }
            tree = modTree;
            BigInteger[] curr = tree.get(1);

        while (formula(curr).compareTo(curr[1])>=0) {
            tree = addBetween(tree);
            curr = tree.get(1);
        }
        String a = curr[0].toString();
        String b = curr[1].toString();
        FileWriter writer = new FileWriter("output.txt");
        writer.write(a);
        writer.write("\n");
        writer.write(b);
        writer.close();
    }
    public  static BigInteger formula(BigInteger[] f) {
        return ((N.multiply(f[0].multiply(f[0])).subtract(M.multiply(f[1].multiply(f[1])))).abs());
    }
    public static ArrayList<BigInteger[]> addBetween(ArrayList<BigInteger[]> tree){
        int pos = 0;
        int leftpos = 0;
        ArrayList<BigInteger[]> modTree = (ArrayList<BigInteger[]>) tree.clone();
        for (int i = 0; i < tree.size()-1; i++) {
            BigInteger[] left = tree.get(i);
            BigInteger[] right = tree.get(i + 1);
            if((right[1].compareTo(new BigInteger("0")) == 0)){
                modTree.add(modTree.indexOf(left)+1,new BigInteger[]{left[0].add(right[0]),left[1].add(right[1])});
                leftpos = modTree.indexOf(left);
                pos = modTree.indexOf(right);
                break;
            }
            else if(new BigDecimal(left[0]).divide(new BigDecimal(left[1]), new MathContext(5000, RoundingMode.HALF_EVEN)).compareTo(num)==-1 && new BigDecimal(right[0]).divide(new BigDecimal(right[1]), new MathContext(5000, RoundingMode.HALF_EVEN)).compareTo(num)==1){
                modTree.add(modTree.indexOf(left)+1,new BigInteger[]{left[0].add(right[0]),left[1].add(right[1])});
                leftpos = modTree.indexOf(left);
                pos = modTree.indexOf(right);
                break;
            }
        }
        modTree.subList(0, leftpos).clear();
        modTree.subList(pos-leftpos+1, modTree.size()).clear();
        return modTree;
    }
}