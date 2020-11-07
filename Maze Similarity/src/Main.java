//Quan Do
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File fl = new File("input.txt");
        Scanner scan = new Scanner(fl);

        int totalMazes = Integer.parseInt(scan.next());
        int size = Integer.parseInt(scan.next());
        Position[][][] mazes = new Position[totalMazes][size][size];
        String[] path = new String[totalMazes];
        int first = 0;
        int last = 4;
        for(int i = 0; i < totalMazes; i++){    //maze number
            if(i != 0){
                String skip = scan.nextLine();
            }
            for(int j = 0; j < size; j++){      //row
                String temp = scan.next();
                for(int k = 0; k < size; k++){  //column
                    mazes[i][j][k] = new Position(temp.substring(first, last));
                    first+=4;
                    last+=4;
                }
                first = 0;
                last = 4;
            }
        }

//        for(int i = 0; i < totalMazes; i++){ //run maze
            dfs(0, mazes, path, size);
//        }
//        System.out.println(Arrays.toString(path));


//        int count = 0;
//        int same = 0;
//        int c = 0;
//        for(int i = 0; i < totalMazes; i++){
//            for(int j = c; j < totalMazes; j++){
//                if(i == j){
//                    same++;
//                }
//                count++;
//            }
//            c++;
//        }
//        count = count - same;
//
//        String[][] similarity = new String[count][2];
//        int pos = 0;
//        c = 0;
//        for(int i = 0; i < totalMazes; i++){
//            for(int j = c; j < totalMazes; j++){
//                if(i == j){
//                    pos--;
//                }
//                else{
//                    similarity[pos][0] = i + " " + j;
//                    similarity[pos][1] = Integer.toString(checkSimilarity(path,i,j));
//                }
//                pos++;
//            }
//            c++;
//        }


//        for(int i = 0; i < count; i++){ //Print array with similarites
//            for(int j = 0; j < 2; j++){
//                System.out.print(similarity[i][j] + ": ");
//            }
//            System.out.println();
//        }


        for(int i = 0; i < totalMazes; i++){
            for(int j = 0; j < size; j++){
                for(int k = 0; k < size; k++){
                    System.out.print(mazes[i][j][k].getNums());
            }
                System.out.println();
            }
            System.out.println();
        }

        FileWriter writer = new FileWriter("output.txt");

        writer.close();
    }
    public static int checkSimilarity(String[] p, int x, int y){
        int size1 = p[x].length();
        int size2 = p[y].length();
        int[][] sim = new int[size1+1][size2+1];

        for(int i = 0; i <= size1; i++){
            for(int j = 0; j <= size2; j++){
                if(i == 0 || j == 0){
                    sim[i][j] = 0;
                }
                else if(p[x].charAt(i-1) == p[y].charAt(j-1)){
                    sim[i][j] = 1 + sim[i-1][j-1];
                }
                else{
                    sim[i][j] = Math.max(sim[i-1][j], sim[i][j-1]);
                }
            }
        }
        return sim[size1][size2];
    }
    public static boolean allExplored(int s, int m, Position[][][] p){
        for(int i = 0; i < s; i++){
            for(int j = 0; j < s; j++){
                if(!p[m][i][j].isVisited()){
                    return false;
                }
            }
        }
        return true;
    }
public static void dfs(int i, Position[][][] m, String[] p, int s){
        int x = 0;
        int y = 0;
        while(!allExplored(s, i , m)){
            if(!m[i][y][x].getNums().contains("2")){
                if(m[i][y][x].getNums().charAt(0) == '0' && !m[i][y-1][x].getNums().contains("2") && y-1 >= 0){
                    String temp = m[i][y][x].getNums();
                    temp = "2" + temp.substring(1);
                    m[i][y][x].setNums(temp);
                    p[i] = p[i] + "n";
                    y--;
                }
                if(m[i][y][x].getNums().charAt(1) == '0' && !m[i][y+1][x].getNums().contains("2") && y+1 < s){
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,1) + "2" + temp.substring(2);
                    m[i][y][x].setNums(temp);
                    if(p[i] == null){
                        p[i] = "s";
                    }
                    else{
                        p[i] = p[i] + "s";
                    }
                    y++;
                }
                if(m[i][y][x].getNums().charAt(2) == '0' && !m[i][y][x-1].getNums().contains("2") && x-1 >= 0){
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,2) + "2" + temp.substring(3);
                    m[i][y][x].setNums(temp);
                    p[i] = p[i] + "w";
                    x--;
                }
                if(m[i][y][x].getNums().charAt(3) == '0' && !m[i][y][x+1].getNums().contains("2") && x+1 < s){
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,3) + "2";
                    m[i][y][x].setNums(temp);
                    if(p[i] == null){
                        p[i] = "e";
                    }
                    else{
                        p[i] = p[i] + "e";
                    }
                    x++;
                }
            }
        }
    }
    public static boolean canRight(int i,Position[][][] m, int x, int y, int s){
        if(x+1 >= s){
            return false;
        }
        if(m[i][y][x].getNums().charAt(3) == '1'){
            return false;
        }
        return true;
    }
    public static boolean canLeft(int i,Position[][][] m, int x, int y){
        if(x-1 < 0){
            return false;
        }
        if(m[i][y][x].getNums().charAt(2) == '1'){
            return false;
        }
        return true;
    }
    static class Position{
        private Position before;
//        private String dir;
        private String nums;
        private boolean prev;
        private boolean visited;

        public Position(String x){
            nums = x;
            before = null;
//            dir = null;
            prev = false;
            if(x.charAt(0) == '0' || x.charAt(1) == '0' || x.charAt(2) == '0' || x.charAt(3) == '0'){
                visited = false;
            }else{
                visited = true;
            }
        }
        public void setNums(String x){
            nums = x;
            if(x.charAt(0) == '0' || x.charAt(1) == '0' || x.charAt(2) == '0' || x.charAt(3) == '0'){
                visited = false;
            }else{
                visited = true;
            }
        }
        public void setBefore(Position p){
            before = p;
        }
        public Position getBefore(){
            return before;
        }
//        public void setDir(String x){
//            dir = x;
//        }
//        public String getDir(){
//            return dir;
//        }
        public void setPrev(){
            prev = true;
        }
        public String getNums(){
            return nums;
        }
        public boolean isPrev(){
            return prev;
        }
        public boolean isVisited(){
            return visited;
        }
    }
}
