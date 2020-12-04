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
//        ArrayList<Pad> path = new ArrayList<>();
//        int loop = 0;
//        Pad start = new Pad("1");
//        Pad curr = start;
//        Pad temp = start;
//        path.add(curr);
//        for(int i = 0; i < pads.size(); i++){
//            if(curr == start && pads.get(i).isMinimal() && !pads.get(i).isMaximal() && pads.get(i).isUseful()){
//                path.add(pads.get(i));
//                curr = pads.get(i);
//                loop = 0;
//                if(i == pads.size()-1){
//                    if(loop == 0){
//                        loop++;
//                        i = 0;
//                    }
//                    else if(loop != 0){
//                        temp.setUsefulFalse();
//                        temp = start;
//                        path = new ArrayList<>();
//                        curr = start;
//                        path.add(curr);
//                        i = 0;
//                        loop = 0;
//                    }
//                }
//            }
//            else if(curr != start && curr.getValue().compareTo(pads.get(i).getValue()) == -1 && !curr.getValue().gcd(pads.get(i).getValue()).equals(BigInteger.ONE) && !pads.get(i).isMinimal() && pads.get(i).isUseful()){
//                if(pads.get(i).isMaximal() && !pads.get(i).isMinimal()){
//                    path.add(pads.get(i));
//                    for(int j = 0; j < path.size(); j++){
//                        writer.write(path.get(j).getValue() + " ");
//                        pads.remove(path.get(j));
//                    }
//                    writer.write("\n");
//                    path = new ArrayList<>();
//                    curr = start;
//                    path.add(curr);
//                    i = 0;
//                    loop = 0;
//                }
//            }
//            else if(curr == start && pads.get(i).isMinimal() && pads.get(i).isMaximal() && pads.get(i).isUseful()){
//                path.add(pads.get(i));
//                for(int j = 0; j < path.size(); j++){
//                    pads.remove(path.get(j));
//                    writer.write(path.get(j).getValue() + " ");
//                }
//                writer.write("\n");
//                path = new ArrayList<>();
//                curr = start;
//                path.add(curr);
//                i = 0;
//                loop = 0;
//            }
//            else if(curr.isMinimal() && i == pads.size()-1 && (pads.get(pads.size()-1).getValue().gcd(curr.getValue()).equals(BigInteger.ONE))){
//                if(loop == 0){
//                    loop++;
//                    i = 0;
//                }
//                else if(loop != 0){
//                    curr.setUsefulFalse();
//                    path = new ArrayList<>();
//                    curr = start;
//                    path.add(curr);
//                    i = 0;
//                    loop = 0;
//                }
//            }
//        }
//        for(int i = 0; i < pads.size(); i++){
//            pads.get(i).setUseful();
//        }
//
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
//        ArrayList<Pad> min = new ArrayList<>();
//        ArrayList<Pad> max = new ArrayList<>();
//        ArrayList<Pad> minFactor = new ArrayList<>();
//        ArrayList<Pad> shared = new ArrayList<>();
//        for(int i = 0; i < pads.size(); i++){
//            if(pads.get(i).isMinimal()){
//                min.add(pads.get(i));
//            }
//            else if(pads.get(i).isMaximal()){
//                max.add(pads.get(i));
//            }
//        }
//
//        for(int i = 0; i < pads.size(); i++){
//            System.out.println(pads.get(i).toString());
//        }
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
//        private boolean useful;

        public Pad(String v){
            value = new BigInteger(v);
            minimal = false;
            maximal = false;
//            useful = true;
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
//        public boolean isUseful() { return useful; }
        public void setMinimal(){
            minimal = true;
        }
        public void setMaximal(){
            maximal = true;
        }
//        public void setUseful() { useful = true; }
//        public void setUsefulFalse() { useful = false; }
        public void setMinimalFalse(){ minimal = false; }
        public void setMaximalFalse(){
            maximal = false;
        }
//        public String toString(){
//            return value + " minimal: " + minimal + " maximal: " + maximal;
//        }
    }
}
