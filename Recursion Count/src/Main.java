//Quan Do
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int counter = 0;
    public static void main(String[] args) throws IOException {
        File fl = new File("input.txt");
        Scanner scan = new Scanner(fl);
        int divisors = scan.nextInt();
        int[] score = new int[divisors];

        BigInteger[] n = new BigInteger[divisors];
        for(int i = 0; i < divisors; i++){
            n[i] = new BigInteger(scan.next());
        }

        ArrayList<BigInteger> a = new ArrayList<>();
        ArrayList<BigInteger> b = new ArrayList<>();
        while(scan.hasNext()){
            a.add(new BigInteger(scan.next()));
            b.add(new BigInteger(scan.next()));
        }

        for(int i = 0; i < n.length; i++){
            for(int j = 0; j < a.size(); j++){
                gcd(n[i], a.get(j), b.get(j));
            }
            score[i] = counter;
            counter = 0;
        }
        FileWriter writer = new FileWriter("output.txt");
        for(int i = 0; i < n.length; i++){
            writer.write(n[i].toString());
            writer.write(" ");
            writer.write(Integer.toString(score[i]));
            writer.write("\n");
        }
        writer.close();
    }
    public static BigInteger gcd(BigInteger n, BigInteger a, BigInteger b){
        BigInteger zero = new BigInteger("0");
        if(a.compareTo(zero) == 0){
            return b;
        }
        else if(b.compareTo(zero) == 0){
            return a;
        }
        else if(a.compareTo(zero) == 0 && b.compareTo(zero) == 0){
            return zero;
        }
        else if(a.mod(n).equals(zero) && b.mod(n).equals(zero)){
            counter += 2;
            return n.multiply(gcd(n, a.divide(n), b.divide(n)));
        }
        else if(a.mod(n).equals(zero) && b.mod(n).compareTo(zero) != 0){
            counter += 1;
            return gcd(n, a.divide(n), b);
        }
        else if(a.mod(n).compareTo(zero) != 0 && b.mod(n).equals(zero)){
            counter += 1;
            return gcd(n, a, b.divide(n));
        }
        else{
//            return gcd(n, a.max(b).subtract(a.min(b)), a.min(b)); fits with original recursion process
            return(gcd(n, zero, zero)); // stop adding score
        }
    }
}
