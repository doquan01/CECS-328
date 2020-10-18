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
//        for(int i = 0; i < 2; i++){
//            for(int j = 0; j < x.length(); j++){
//                System.out.print(inputMag[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        ArrayList<String> count = new ArrayList<>();

        while(count.size() < totMag){
            symbols(x, inputMag);
            starChange(x, inputMag);
            totEx(x, inputMag, count);
        }

        ArrayList<String> magnets = new ArrayList<>();
        totEx(x, inputMag, magnets);

//        System.out.println(magnets);
//        for(int i = 0; i < 2; i++){
//            for(int j = 0; j < x.length(); j++){
//                System.out.print(inputMag[i][j]);
//            }
//            System.out.println();
//        }

        String[][] coordinate = new String[totMag][4];
        toCoordinate(totMag, magnets, coordinate, inputMag);

        FileWriter writer = new FileWriter("output.txt");
        for(int i = 0; i < totMag; i++){
            for(int j = 0; j < 4; j++){
                writer.write(coordinate[i][j]);
                if(j == 0 || j == 1 || j == 2){
                    writer.write(" ");
                }
            }
            writer.write("\n");
        }
        writer.close();
    }
    public static void toCoordinate(int y, ArrayList<String> x, String[][] c, char[][] m){
        for(int j = 0; j < y; j++){
                if(x.get(j).length() < 3){
                    //vertical +
                    if(m[0][Integer.parseInt(x.get(j))] == '+'){
                        c[j][0] = "0";
                        c[j][1] = x.get(j);
                        c[j][2] = "1";
                        c[j][3] = x.get(j);
                    }
                    else if(m[0][Integer.parseInt(x.get(j))] == '-'){
                        //vertical -
                        c[j][0] = "1";
                        c[j][1] = x.get(j);
                        c[j][2] = "0";
                        c[j][3] = x.get(j);
                    }
                }
                else{
                    if(x.get(j).contains("top")){
                        if(m[0][(Integer.parseInt(x.get(j).split(",")[1]))] == '+'){
                            c[j][0] = "0";
                            c[j][1] = x.get(j).split(",")[1];
                            c[j][2] = "0";
                            c[j][3] = x.get(j).split(",")[2];
                        }
                        else if(m[0][(Integer.parseInt(x.get(j).split(",")[1]))] == '-'){
                            c[j][0] = "0";
                            c[j][1] = x.get(j).split(",")[2];
                            c[j][2] = "0";
                            c[j][3] = x.get(j).split(",")[1];
                        }
                    }
                    if(x.get(j).contains("bottom")){
                        if(m[1][(Integer.parseInt(x.get(j).split(",")[1]))] == '+'){
                            c[j][0] = "1";
                            c[j][1] = x.get(j).split(",")[1];
                            c[j][2] = "1";
                            c[j][3] = x.get(j).split(",")[2];
                        }
                        else if(m[1][Integer.parseInt(x.get(j).split(",")[1])] == '-'){
                            c[j][0] = "1";
                            c[j][1] = x.get(j).split(",")[2];
                            c[j][2] = "1";
                            c[j][3] = x.get(j).split(",")[1];
                        }
                }
            }
        }
    }
    public static void totEx(String y, char[][] c, ArrayList<String> e){
        int check = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < y.length(); j++){
                if(horizMag(y, i, j, c)){
//                    if(e.contains(("top:"+ j + "," + (j+1))) || e.contains(("bottom:"+ j + "," + (j+1)))) {
//                    }
//                    else{
                        if(i == 0){
                            e.add(("top,"+ j + "," + (j+1)));
                            check = j+1;
                        }
                        else{
                            e.add(("bottom,"+ j + "," + (j+1)));
                            check = j + 1;
                        }
//                    }
                }
                if(!e.contains(Integer.toString(j))){
                    if(i == 0){
                        if(vertMag(y, j, c)){
                            if(check == j && check != 0){
                            }
                            else{
                                e.add(Integer.toString(j));
                            }
                        }
                    }

                }
            }
        }
    }
    public static boolean vertMag(String y, int j, char[][] c){
        if(c[0][j] == '*' || c[1][j] == '*'){
            return false;
        }
        if((c[0][j] == '+' && c[1][j] == '+') || (c[0][j] == '-' && c[1][j] == '-')){
            return false;
        }
        else if(j-1 >= 0 && j+1 < y.length()){
            if((c[0][j-1] == '+' && c[1][j-1] == '*' && c[0][j] == '-' && c[1][j] == '+' && c[0][j+1] == '*' && c[1][j+1] == '-') || (c[0][j-1] == '-' && c[1][j-1] == '*' && c[0][j] == '+' && c[1][j] == '-' && c[0][j+1] == '*' && c[1][j+1] == '+')){
                return false;
            }
            else if((c[0][j-1] == '*' && c[1][j-1] == '+' && c[0][j] == '+' && c[1][j] == '-' && c[0][j+1] == '-' && c[1][j+1] == '*') || (c[0][j-1] == '*' && c[1][j-1] == '-' && c[0][j] == '-' && c[1][j] == '+' && c[0][j+1] == '+' && c[1][j+1] == '*')){
                return false;
            }
        }
        else if((c[0][j] == '+' && c[1][j] == '-') || (c[0][j] == '-' && c[1][j] == '+')){
            return true;
        }
        return true;
    }
    public static boolean horizMag(String y, int i, int j, char[][] c){
        if(vertMag(y, j, c) || c[i][j] == '*'){
            return false;
        }
        else if(j+1 < y.length() && ((c[i][j] == '+' && c[i][j+1] == '*') || (c[i][j] == '-' && c[i][j+1] == '*') || (c[i][j] == '*' && c[i][j+1] == '+') || (c[i][j] == '*' && c[i][j+1] == '-') || (c[i][j] == '*' && c[i][j+1] == '*'))){
            return false;
        }
        else if(j-1 > 0 && j + 1 < y.length()){
            if((c[i][j-1] == '-' && c[i][j] == '+' && c[i][j+1] == '-') || (c[i][j-1] == '+' && c[i][j] == '-' && c[i][j+1] == '+')){
                return false;
            }
        }
        else if(j+1 < y.length()){
            if((c[0][j] == '+' && c[0][j+1] == '-') || (c[0][j] == '-' && c[0][j+1] == '+') || (c[1][j] == '+' && c[1][j+1] == '-') || (c[1][j] == '-' && c[1][j+1] == '+')){
                return true;
            }
        }
        return true;
    }
    public static boolean checkRight(String y, int i, int j, char[][] c){
        if(j + 1 < y.length()){
            if((c[1][j] == '+' && c[0][j+1] == '-') || (c[1][j] == '-' && c[0][j+1] == '+')){
                return false;
            }
        }
        if(j + 3 < y.length()){
            if(c[i][j] == '+' && c[i][j+1] == '+' && c[i][j + 2] == '+' && c[i][j+3] == '+'){
                return false;
            }

        }
        return true;
    }
    public static boolean checkVert(String y, int i, int j, char[][] c){
        if(i == 0 && j+2 < y.length()){
            if((c[0][j] == '+' && c[1][j] == '-') || (c[0][j] == '+' && c[1][j+1] == '-') || (c[0][j] == '+' && c[1][j+2] == '+')){
                return false;
            }
            else if((c[0][j] == '-' && c[1][j] == '+') || (c[0][j] == '-' && c[1][j+1] == '+') || (c[0][j] == '-' && c[1][j+2] == '-')){
                return false;
            }
        }
        else if(i == 1){
            if(j + 2 < y.length()){
                if((c[1][j] == '+' && c[0][j] == '-') || (c[1][j] == '+' && c[0][j+1] == '-') || (c[1][j] == '+' && c[0][j+2] == '+')){
                    return false;
                }
                else if((c[1][j] == '-' && c[0][j] == '+') || (c[1][j] == '-' && c[0][j+1] == '+') || (c[1][j] == '-' && c[0][j+2] == '-')){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkLeft(int j, char[][] c){
            if(j - 1 >= 0){
                if((c[0][j] == '+' && c[1][(j - 1)] == '-') || (c[0][j] == '-' && c[1][j - 1] == '+')){
                    return false;
                }
                if((c[1][j] == '+' && c[0][j - 1] == '-') || (c[1][j] == '-' && c[0][j - 1] == '+')){
                    return false;
                }
            }
        return true;
    }
    public static void symbols(String y, char[][] c){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < y.length(); j++){
                if(checkVert(y, i, j, c) && checkLeft(j, c) && checkRight(y, i, j, c)){
                    if(c[0][j] == '+'){
                        c[1][j] = '-';
                    }
                    else if(c[0][j] == '-'){
                        c[1][j] = '+';
                    }
                    else if(c[1][j] == '+'){
                        c[0][j] = '-';
                    }
                    else if(c[1][j] == '-'){
                        c[0][j] = '+';
                    }
                }
            }
        }
    }
    public static boolean starCurr(String y, int j, char[][] c){
        if(c[0][j] == '*' || c[1][j] == '*'){
            if(j < y.length() && j-1 >= 0 && j+2 < y.length()){
                if((c[0][j-1] == '+' && c[0][j] == '*' && c[1][j] == '*' && c[0][j+1] == '*' && c[1][j+1] == '*' && c[0][j+2] == '+')){
                    return false;
                }
                else if((c[0][j-1] == '-' && c[0][j] == '*' && c[1][j] == '*' && c[0][j+1] == '*' && c[1][j+1] == '*' && c[0][j+2] == '-')){
                    return false;
                }
                else if((c[1][j-1] == '+' && c[0][j] == '*' && c[1][j] == '*' && c[0][j+1] == '*' && c[1][j+1] == '*' && c[1][j+2] == '+')){
                    return false;
                }
                else if((c[1][j-1] == '-' && c[0][j] == '*' && c[1][j] == '*' && c[0][j+1] == '*' && c[1][j+1] == '*' && c[1][j+2] == '-')){
                    return false;
                }
            }
            if(j - 1 >= 0 && j + 1 < y.length()){
                if((c[0][j] == '*' && c[0][j+1] == '-' && c[0][j-1] == '+') || (c[0][j] == '*' && c[0][j+1] == '+' && c[0][j-1] == '-') || (c[1][j] == '*' && c[1][j+1] == '-' && c[1][j-1] == '+') || (c[1][j] == '*' && c[1][j+1] == '+' && c[1][j-1] == '-')){
                    return false;
                }
            }
        }
        return true;
    }
    public static void starChange(String y, char[][] c){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < y.length(); j++){
                if(c[i][j] == '*' && j+1 < y.length() && j-1 >= 0){
                    if(starCurr(y, j, c) && checkVert(y, i, j, c) && checkLeft(j, c) && checkRight(y, i, j, c)){
                        if(c[0][j+1] == '+' && c[0][j] == '*'){
                            c[0][j] = '-';
                        }
                        else if(c[0][j+1] == '-' && c[0][j] == '*'){
                            c[0][j] = '+';
                        }
                        else if(c[1][j+1] == '+' && c[1][j] == '*'){
                            c[1][j] = '-';
                        }
                        else if(c[1][j+1] == '-' && c[1][j] == '*'){
                            c[1][j] = '+';
                        }
                        else if(c[0][j] == '*' && c[1][j] == '*' && c[0][j + 1] != '-' && c[0][j-1] == '+' && c[1][j-1] != '+' && c[1][j+1] != '+'){
                            c[0][j] = '-';
                        }
                        else if(c[0][j] == '*' && c[1][j] == '*' && c[0][j + 1] != '+' && c[0][j-1] == '-' && c[1][j-1] != '-' && c[1][j+1] != '-'){
                            c[0][j] = '+';
                        }
//                        else if(c[0][j] == '*' && c[1][j + 1] == '*' && c[1][j] == '*' && c[1][j-1] == '+' && c[0][j-1] != '+'){
//                            c[1][j] = '-';
//                        }
//                        else if(c[0][j] == '*' && c[1][j + 1] == '*' && c[1][j] == '*' && c[1][j-1] == '-' && c[0][j-1] != '-'){
//                            c[1][j] = '+';
//                        }
                    }
                }
                else if(c[i][j] == '*' && j-1 >= 0){
                    if(starCurr(y, j, c) && checkVert(y, i, j, c) && checkLeft(j, c) && checkRight(y, i, j, c)){
                        if(c[0][j] == '*' && c[0][j-1] == '+'){
                            c[0][j] = '-';
                        }
                        else if(c[0][j] == '*' && c[0][j-1] == '-'){
                            c[0][j] = '+';
                        }
                        if(c[1][j] == '*' && c[1][j-1] == '+'){
                            c[1][j] = '-';
                        }
                        else if(c[1][j] == '*' && c[1][j-1] == '-'){
                            c[1][j] = '+';
                        }
                    }
                }
                else if(c[i][j] == '*' && j+1 < y.length()){
                    if(starCurr(y, j, c) && checkVert(y, i, j, c) && checkLeft(j, c) && checkRight(y, i, j, c)){
                        if(c[0][j] == '*' && c[0][j+1] == '+' && c[1][j] != '-' && c[1][j+1] != '+'){
                            c[0][j] = '-';
                        }
                        if(c[0][j] == '*' && c[0][j+1] == '-' && c[1][j] != '+' && c[1][j+1] != '-'){
                            c[0][j] = '+';
                        }
                        else if(c[1][j] == '*' && c[1][j+1] == '+' && c[0][j] != '-' && c[0][j+1] != '+'){
                            c[1][j] = '-';
                        }
                        else if(c[1][j] == '*' && c[1][j+1] == '-' && c[0][j] != '+' && c[0][j+1] != '-'){
                            c[1][j] = '+';
                        }
                    }
                }
            }
        }
    }
}
