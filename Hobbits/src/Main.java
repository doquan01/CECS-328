//Quan Do
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

        ArrayList<Pad> pads = new ArrayList<>();
        while(scan.hasNextLine()){
            pads.add(new Pad(scan.nextLine()));
        }

        checkMinMax(pads);

//        for(int i = 0; i < pads.size(); i++){
//            System.out.println(pads.get(i).toString());
//        }
//        System.out.println("\n");

        FileWriter writer = new FileWriter("output.txt");

        ArrayList<Pad> path = new ArrayList<>();
        Pad start = new Pad("1");
        Pad curr = start;
        path.add(curr);
        for(int i = 0; i < pads.size(); i++){
            if(pads.get(i).isMinimal() && !pads.get(i).isMaximal()){
                for(int j = 0; j < pads.size(); j++){
                    if(pads.get(i).isMinimal() && pads.get(j).isMaximal() && (pads.get(i).getValue().compareTo(pads.get(j).getValue()) == -1) && !pads.get(j).getValue().gcd(pads.get(i).getValue()).equals(BigInteger.ONE)){
                        path.add(pads.get(i));
                        path.add(pads.get(j));
                        for(int k = 0; k < path.size(); k++){
                            writer.write(path.get(k).getValue() + " ");
                            pads.remove(path.get(k));
                        }
                        writer.write("\n");
                        path = new ArrayList<>();
                        curr = start;
                        path.add(curr);
                        i = 0;
                        j = 0;
                    }
                }
            }
            else if(pads.get(i).isMaximal() && pads.get(i).isMinimal()){
                writer.write("1 " + pads.get(i).getValue() + " ");
                writer.write("\n");
                pads.remove(pads.get(i));
            }
        }
        path = new ArrayList<>();
        curr = start;
        path.add(curr);
        for(int i = 0; i < pads.size(); i++){
            if(curr == start && pads.get(i).isMinimal() && !pads.get(i).isMaximal()){
                curr = pads.get(i);
                path.add(pads.get(i));
                for(int j = 0; j < pads.size(); j++){
                    if(curr.isMinimal() && !(pads.get(j).getValue().gcd(curr.getValue()).equals(BigInteger.ONE)) && pads.get(j).getValue().compareTo(curr.getValue()) == 1 && !curr.equals(pads.get(j))){
                        for(int k = 0; k < pads.size(); k++){
                            if(curr.isMinimal() && !(pads.get(j).getValue().gcd(pads.get(k).getValue()).equals(BigInteger.ONE)) && pads.get(k).isMaximal() && !curr.equals(pads.get(j)) && !curr.equals(pads.get(k)) && !pads.get(j).equals(pads.get(k))){
                                path.add(pads.get(j));
                                path.add(pads.get(k));
                                for(int l = 0; l < path.size(); l++){
                                    writer.write(path.get(l).getValue() + " ");
                                    pads.remove(path.get(l));
                                }
                                writer.write("\n");
                                path = new ArrayList<>();
                                curr = start;
                                path.add(curr);
                                i = 0;
                                j = 0;
                            }
                        }
                    }
                }
            }
        }
        writer.close();
    }
    public static void checkMinMax(ArrayList<Pad> p){
        for(int i = 0; i < p.size(); i++){
            p.get(i).setMinimal();
            for(int j = 0; j < p.size(); j++){
                if(i == j){
                    continue;
                }
                else{
                    if(p.get(i).getValue().compareTo(p.get(j).getValue()) == 1 && !(p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE))){
                        p.get(i).setMinimalFalse();
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < p.size(); i++){
            p.get(i).setMaximal();
            for(int j = 0; j < p.size(); j++){
                if(i == j){
                    continue;
                }
                else{
                    if(p.get(i).getValue().compareTo(p.get(j).getValue()) == -1 && !(p.get(i).getValue().gcd(p.get(j).getValue()).equals(BigInteger.ONE))){
                        p.get(i).setMaximalFalse();
                        break;
                    }
                }
            }
        }
    }
    static class Pad{
        private BigInteger value;
        private boolean minimal;
        private boolean maximal;

        public Pad(String v){
            value = new BigInteger(v);
            minimal = false;
            maximal = false;
        }
        public BigInteger getValue(){
            return value;
        }
        public boolean isMinimal(){
            return minimal;
        }
        public boolean isMaximal(){
            return maximal;
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
    }
}
