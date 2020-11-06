//Quan Do
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        for(int i = 0; i < totalMazes; i++){ //run maze dfs
            explore(i, mazes, path, size);
        }
        System.out.println(Arrays.toString(path));
//        System.out.println(temp);

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
//        for(int i = 0; i < count; i++){
//            for(int j = 0; j < 2; j++){
//                System.out.print(similarity[i][j] + ": ");
//            }
//            System.out.println();
//        }
//        for(int i = 0; i < totalMazes; i++){
//            for(int j = 0; j < size; j++){
//                for(int k = 0; k < size; k++){
//                    System.out.print(mazes[i][j][k].getNums());
//            }
//                System.out.println();
//            }
//            System.out.println();
//        }


        FileWriter writer = new FileWriter("output.txt");

        writer.close();
    }
    public static int checkSimilarity(String[] p, int x, int y){
        int sim1 = 0;
        int sim2 = 0;
        int diff;
        int leng = 0;
        int size1 = p[x].length();
        int size2 = p[y].length();
        int i1 = 0;
        int j1 = 0;
        int i2 = 0;
        int j2 = 0;
        int place = 0;
//
        while(leng < size1-1 && j2 < size1-1){
            if(j2 >= size1){
                j2 = place;
            }
            else{
                if(p[y].charAt(i2) == p[x].charAt(j2) && i2+1 < size1){
                    place = j2;
                    sim2 = sim2 + 1;
                    i2++;
                    j2++;
                    leng++;
//                    System.out.println("first");
                }
                else if(p[y].charAt(i2) != p[x].charAt(j2)){
                    if(j2+1 < size1){
                        j2++;
//                        System.out.println("Second");
                    }
                    else if(j2 >= size1){
                        j2 = place;
                        if(i2+1 < size1){
                            i2++;
                        }
//                        System.out.println("Third");
                    }
                }
            }
        }
        while(leng < size2-1 && j1 < size2-1){
            if(j1 >= size2){
                j1 = place;
            }
            else{
                if(p[x].charAt(i1) == p[y].charAt(j1) && i1+1 < size2){
                    place = j1;
                    sim1 = sim1 + 1;
                    i1++;
                    j1++;
                    leng++;
//                    System.out.println("first");
                }
                else if(p[x].charAt(i1) != p[y].charAt(j1)){
                    if(j1+1 < size2){
                        j1++;
//                        System.out.println("Second");
                    }
                    else if(j1 >= size2){
                        j1 = place;
                        if(i1+1 < size2){
                            i1++;
                        }
//                        System.out.println("Third");
                    }
                }
            }
        }
        diff = Math.abs(sim1 + sim2);
        return diff;
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
    public static void explore(int i, Position[][][] m, String[] p, int s){
        int x = 0;
        int y = 0;
        Position cur = m[i][y][x];
        while(allExplored(s, i, m)){
            if (cur.getNums().charAt(0) == '0' && !cur.isPrev()) {
                
            }
        }
    }
    static class Position{
        private Position before;
        private String dir;
        private String nums;
        private boolean prev;
        private boolean visited;

        public Position(String x){
            nums = x;
            before = null;
            dir = null;
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
        public void setDir(String x){
            dir = x;
        }
        public String getDir(){
            return dir;
        }
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
