//Quan Do
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File fl = new File("input.txt");
        Scanner scan = new Scanner(fl);
        int totMag = scan.nextInt();
        String x = scan.next();
        String y = scan.next();
        char[][] inputMag = new char[y.length()][x.length()];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < y.length(); j++){
                if(i == 0){
                    inputMag[i][j] = x.charAt(j);
                }
                else{
                    inputMag[i][j] = y.charAt(j);
                }
            }
        }


        ArrayList<Integer> existing = new ArrayList<>();
        checkExisting(y, inputMag, existing);
        if(!existing.isEmpty()){
            existingfill(y, inputMag, existing);
        }
        else{
            addNoEx(y, inputMag, existing);
        }

        int[][] coord = new int[totMag][4];


        for(int i = 0; i < 2; i++){
            for(int j = 0; j < y.length(); j++){
                System.out.print(inputMag[i][j]);
            }
            System.out.println();
        }
        for(int i = 0; i < existing.size(); i++){
            System.out.println(existing.get(i));
        }


        FileWriter writer = new FileWriter("output.txt");
//        writer.write();
        writer.close();
    }
    public static void checkExisting(String y, char[][] c, ArrayList<Integer> e){
        for(int i = 0; i < y.length(); i++){
            if((c[0][i] == '+' && c[1][i] == '-') || (c[0][i] == '-' && c[1][i] == '+')){
                e.add(i);
            }
        }
    }
//    public static void existingfill(String y, char[][] c, ArrayList<Integer> e){
//        if(e.get(0) == 0){
//            if(c[0][0] == '+'){
//                for(int i = 0; i < y.length(); i++){
//                    if(i % 2 == 0){
//                        c[0][i] = '+';
//                        c[1][i] = '-';
//                    }
//                    else{
//                        c[0][i] = '-';
//                        c[1][i] = '+';
//                    }
//                }
//            }
//            else if(c[0][0] == '-'){
//                for(int i = 0; i < y.length(); i++){
//                    if(i % 2 == 0){
//                        c[0][i] = '-';
//                        c[1][i] = '+';
//                    }
//                    else{
//                        c[0][i] = '+';
//                        c[1][i] = '-';
//                    }
//                }
//            }
//        }
//        else{
//            if(c[0][e.get(0)] == '+'){
//                for(int i = e.get(0); i < y.length(); i++){
////                    if(e.get(0) % 2 == 0) {
////                        if (i % 2 == 0) {
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        } else {
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                    }
////                    else{
////                        if (i % 2 != 0) {
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        } else {
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                    }
////                }
////                for(int i = e.get(0); i >= 0; i--){
////                    if(e.get(0) % 2 == 0){
////                        if(i % 2 == 0){
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        }
////                        else{
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                    }
////                    else{
////                        if(i % 2 != 0){
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        }
////                        else{
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                    }
//                }
//            }
////            if(c[0][e.get(0)] == '-'){
////                for(int i = e.get(0); i < y.length(); i++){
////                    if(e.get(0) % 2 == 0){
////                        if(i % 2 == 0){
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                        else{
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        }
////                    }
////                    else{
////                        if(i % 2 != 0){
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                        else{
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        }
////                    }
////                }
////                for(int i = e.get(0); i >= 0; i--){
////                    if(e.get(0) % 2 == 0){
////                        if(i % 2 == 0){
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                        else{
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        }
////                    }
////                    else{
////                        if(i % 2 != 0){
////                            c[0][i] = '-';
////                            c[1][i] = '+';
////                        }
////                        else{
////                            c[0][i] = '+';
////                            c[1][i] = '-';
////                        }
////                    }
////                }
////            }
//        }
//    }
    public static void addNoEx(String y, char[][] c, ArrayList<Integer> t){
        for(int i = 0; i < y.length(); i++){
            if(c[0][i] != '*' || c[1][i] != '*'){
                t.add(i);
            }
        }
        if(!t.isEmpty()){
            if(c[0][t.get(0)] == '+'){
                c[1][t.get(0)] = '-';
            }
            else if(c[0][t.get(0)] == '-'){
                c[1][t.get(0)] = '+';
            }
            else if(c[1][t.get(0)] == '+'){
                c[0][t.get(0)] = '-';
            }
            else if(c[1][t.get(0)] == '-'){
                c[0][t.get(0)] = '+';
            }
        }
        else{
            c[0][0] = '+';
            c[1][0] = '-';
            t.add(0);
        }
//        existingfill(y, c, t);
        t.clear();
    }

}
