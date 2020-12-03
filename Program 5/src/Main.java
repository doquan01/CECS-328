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

        checkMin(pads);
        checkMax(pads);

//        for(int i = 0; i < pads.size(); i++){
//            System.out.println(pads.get(i).toString());
//        }

        FileWriter writer = new FileWriter("output.txt");
        ArrayList<Pad> path = new ArrayList<>();
        int loop = 0;
        Pad start = new Pad("1");
        Pad curr = start;
        path.add(curr);
        for(int i = 0; i < pads.size(); i++){
            if(curr == start && pads.get(i).isMinimal() && !pads.get(i).isMaximal() && !pads.get(i).isDropped() && pads.get(i).isUseful()){
                path.add(pads.get(i));
                curr = pads.get(i);
                loop = 0;
//                System.out.println("1");
            }
            else if(curr != start && curr.getValue().compareTo(pads.get(i).getValue()) == -1 && !curr.getValue().gcd(pads.get(i).getValue()).equals(BigInteger.ONE) && !pads.get(i).isDropped() && pads.get(i).isUseful()){
                if(pads.get(i).isMaximal() && !pads.get(i).isMinimal()){
                    path.add(pads.get(i));
                    for(int j = 0; j < path.size(); j++){
                        path.get(j).setDropped();
                        writer.write(path.get(j).getValue() + " ");
                        pads.remove(path.get(j));
                    }
                    writer.write("\n");
                    path = new ArrayList<>();
                    curr = start;
                    path.add(curr);
                    i = 0;
                    loop = 0;
//                    System.out.println("3");
                }
            }
            else if(curr == start && pads.get(i).isMinimal() && pads.get(i).isMaximal() && !pads.get(i).isDropped() && pads.get(i).isUseful()){
                path.add(pads.get(i));
                for(int j = 0; j < path.size(); j++){
                    path.get(j).setDropped();
                    pads.remove(path.get(j));
                    writer.write(path.get(j).getValue() + " ");
                }
                writer.write("\n");
                path = new ArrayList<>();
                curr = start;
                path.add(curr);
                i = 0;
                loop = 0;
//                System.out.println("4");
            }
            else if(curr.isMinimal() && i == pads.size()-1 && (pads.get(pads.size()-1).getValue().gcd(curr.getValue()).equals(BigInteger.ONE))){
                if(loop == 0){
                    loop++;
                    i = 0;
                }
                else if(loop != 0){
                    curr.setUsefulFalse();
                    path = new ArrayList<>();
                    curr = start;
                    path.add(curr);
                    i = 0;
                    loop = 0;
                }
            }
        }
        for(int i = 0; i < pads.size(); i++){
            pads.get(i).setUseful();
        }

        path = new ArrayList<>();
        loop = 0;
        curr = start;
        path.add(curr);
        for(int i = 0; i < pads.size(); i++){
            if(curr == start && pads.get(i).isMinimal() && !pads.get(i).isMaximal() && !pads.get(i).isDropped() && pads.get(i).isUseful()){
                path.add(pads.get(i));
                curr = pads.get(i);
                loop = 0;
//                System.out.println("1");
            }
            else if(!(pads.get(i).getValue().gcd(curr.getValue()).equals(BigInteger.ONE)) && curr.getValue().compareTo(pads.get(i).getValue()) == -1 && pads.get(i).isUseful()){
                if(!pads.get(i).isMaximal()){
                    path.add(pads.get(i));
                    curr = pads.get(i);
                    loop = 0;
                }
                else{
                    path.add(pads.get(i));
                    for(int j = 0; j < path.size(); j++){
                        path.get(j).setDropped();
                        writer.write(path.get(j).getValue() + " ");
                        pads.remove(path.get(j));
                    }
                    writer.write("\n");
                    path = new ArrayList<>();
                    curr = start;
                    path.add(curr);
                    i = 0;
                    loop = 0;
                }
            }
            else if((curr.isMinimal() || !curr.isMinimal()) && !curr.isMaximal() && i == pads.size()-1){
                if(loop == 0){
                    loop++;
                    i = 0;
                }
                else if(loop != 0){
                    path.get(1).setUsefulFalse();
                    path = new ArrayList<>();
                    curr = start;
                    path.add(curr);
                    i = 0;
                    loop = 0;
                }
            }
        }
        for(int i = 0; i < pads.size(); i++){
            System.out.println(pads.get(i).toString());
        }
//        for(int i = 0; i < pads.size(); i++){
//            if(curr == start && pads.get(i).isMinimal() && !pads.get(i).isMaximal() && !pads.get(i).isDropped()){
//                path.add(pads.get(i));
//                curr = pads.get(i);
//            }
//        }
//        System.out.println(curr);
//        for(int i = 0; i < pads.size(); i++){
//            if(!(pads.get(i).getValue().gcd(curr.getValue()).equals(BigInteger.ONE)) && pads.get(i).getValue().compareTo(curr.getValue()) == 1){
//                for(int j = 0; j < pads.size(); j++) {
//                    if(!(pads.get(i).getValue().gcd(pads.get(j).getValue()).equals(BigInteger.ONE)) && pads.get(j).isMaximal()){
//                        path.add(pads.get(i));
//                        path.add(pads.get(j));
//                        writer.write("\n1" + " " + curr.getValue() + " " + pads.get(i).getValue() + " " + pads.get(j).getValue() + " ");
//                        pads.remove(pads.get(i));
//                        pads.remove(pads.get(j));
//                        curr.setDropped();
//                        curr = start;
//                        pads.remove(curr);
//                        for(int k = 0; k < pads.size(); k++){
//                            if(curr == start && pads.get(i).isMinimal() && !pads.get(i).isMaximal() && !pads.get(i).isDropped()){
//                                path.add(pads.get(i));
//                                curr = pads.get(i);
//                            }
//                        }
//                    }
//                }
//            }
//        }

//        writer.write();
        writer.close();
    }
    public static void checkMin(ArrayList<Pad> p){
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
    }
    public static void checkMax(ArrayList<Pad> p){
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
        private boolean dropped;
        private boolean minimal;
        private boolean maximal;
        private boolean useful;

        public Pad(String v){
            value = new BigInteger(v);
            if(value.equals(BigInteger.ONE)){
                dropped = false;
            }
            minimal = false;
            maximal = false;
            useful = true;
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
        public boolean isUseful() { return useful; }
        public void setDropped(){
            dropped = true;
        }
        public void setMinimal(){
            minimal = true;
        }
        public void setMaximal(){
            maximal = true;
        }
        public void setUseful() { useful = true; }
        public void setUsefulFalse() { useful = false; }
        public void setMinimalFalse(){ minimal = false; }
        public void setMaximalFalse(){
            maximal = false;
        }
        public String toString(){
            return value + " minimal: " + minimal + " maximal: " + maximal;
        }
    }
}
