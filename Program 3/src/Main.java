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
        String y = scan.next();
        String x = scan.next();
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
        for(int i = 0; i < y.length(); i++){
            if((inputMag[0][i] == '+' && inputMag[1][i] == '-') || (inputMag[0][i] == '-' && inputMag[1][i] == '+')){
                existing.add(i);
            }
        }
        int count = 0;
        
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
}
