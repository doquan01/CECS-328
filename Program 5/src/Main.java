import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File fl = new File("input.txt");
        Scanner scan = new Scanner(fl);

        ArrayList<Pad> path = new ArrayList<>();
        while(scan.hasNextLine()){
            path.add(new Pad(scan.nextLine()));
        }

        checkMinMax(path);

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i).toString());
        }

//        FileWriter writer = new FileWriter("output.txt");
////        writer.write();
//        writer.close();
    }
    public static void checkMinMax(ArrayList<Pad> p){
        int factor = 0;
        int genFactor = 0;
        for(int i = 0; i < p.size(); i++){
            for(int j = 0; j < p.size(); j++){
                if(i == j){
                    continue;
                }
                else{
                    if(!p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE)){
                        genFactor++;
                    }
                    if(p.get(i).getValue().isProbablePrime(1)){
                        p.get(i).setMinimal();
                    }
                    if(p.get(i).getValue().compareTo(p.get(j).getValue()) == -1 && !(p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE))) {
                        p.get(i).setMinimal();
                    }
                    if(p.get(i).isMaximal() && genFactor != 0){
                    p.get(i).setMinimalFalse();
                    }

                    if(p.get(i).getValue().compareTo(p.get(j).getValue()) == -1 && !(p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE))){
                        factor++;
                    }
                    if(factor == 0){
                        if(p.get(i).getValue().compareTo(p.get(j).getValue()) == 1 && !(p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE))){
                            p.get(i).setMaximal();
                        }
                        if(p.get(i).getValue().compareTo(p.get(j).getValue()) == 1 && p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE)){
                            p.get(i).setMaximal();
                        }
                        if(p.get(i).getValue().compareTo(p.get(j).getValue()) == 1 && (p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE))){
                            p.get(i).setMinimal();
                        }
                    }
                    if(factor != 0){
                        p.get(i).setMaximalFalse();
                    }
                }
            }
            factor = 0;
            genFactor = 0;
        }
    }
    static class Pad{
        private BigInteger value;
        private boolean dropped;
        private boolean minimal;
        private boolean maximal;

        public Pad(String v){
            value = new BigInteger(v);
            dropped = false;
            minimal = false;
            maximal = false;
        }
        public BigInteger getValue(){
            return value;
        }
        public boolean isDropped(){
            return dropped;
        }
        public boolean isMinimal(){
            return minimal;
        }
        public boolean isMaximal(){
            return maximal;
        }
        public void setDropped(){
            dropped = true;
        }
        public void setMinimal(){
            minimal = true;
        }
        public void setMaximal(){
            maximal = true;
        }
        public void setMinimalFalse(){ minimal = false; }
        public void setMaximalFalse(){
            maximal = false;
        }
        public String toString(){
            return value + " minimal: " + minimal + " maximal: " + maximal;
        }
    }
}
