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
        for(int i = 0; i < totalMazes; i++){ //run maze
            explore(i, mazes, 0, 0, path, size);
        }
        System.out.println(Arrays.toString(path));

        int count = 0;
        int same = 0;
        int c = 0;
        for(int i = 0; i < totalMazes; i++){
            for(int j = c; j < totalMazes; j++){
                if(i == j){
                    same++;
                }
                count++;
            }
            c++;
        }
        count = count - same;

        String[][] similarity = new String[count][2];
        int pos = 0;
        c = 0;
        for(int i = 0; i < totalMazes; i++){
            for(int j = c; j < totalMazes; j++){
                if(i == j){
                    pos--;
                }
                else{
                    similarity[pos][0] = i + " " + j;
                    similarity[pos][1] = Integer.toString(checkSimilarity(path,i,j));
                }
                pos++;
            }
            c++;
        }


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

        String[] test = new String[] {"sssnens", "snessne"};  //testing similarity
        System.out.println(checkSimilarity(test, 0,1));

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
//        String[] combo2 = new String[1];
//        String[] combo1 = new String[1];

        while(j2 < size1){ //y == x chars
            if(p[y].charAt(i2) == p[x].charAt(j2) && i2+1 < size2){
                place = j2;
                sim2 = sim2 + 1;
//                if(combo2[0] == null){
//                    combo2[0] = String.valueOf(p[y].charAt(i2));
//                }
//                else{
//                    combo2[0] = combo2[0] + p[y].charAt(i2);
//                }
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
                    if(i2+1 < size2){
                        i2++;
                    }
//                        System.out.println("Third");
                }
            }
        }
        place = 0;
        leng = 0;
        while(j1 < size2){ //x == y chars
            if(p[x].charAt(i1) == p[y].charAt(j1) && i1+1 < size1){
                place = j1;
                sim1 = sim1 + 1;
//                if(combo1[0] == null){
//                    combo1[0] = String.valueOf(p[x].charAt(i1));
//                }
//                else{
//                    combo1[0] = combo1[0] + p[x].charAt(i1);
//                }
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
                    if(i1+1 < size1){
                        i1++;
                    }
//                        System.out.println("Third");
                }
            }
        }
//        System.out.println(Arrays.toString(combo1));
//        System.out.println(Arrays.toString(combo2));
        diff = Math.max(sim1, sim2);
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
//    public static void explore(int i, Position[][][] m, String[] p, int s){
//        int x = 0;
//        int y = 0;
//        Position cur = m[i][y][x];
//        while(allExplored(s, i, m)){
//            if (cur.getNums().charAt(0) == '0' && !cur.isPrev()) {
//
//            }
//        }
//    }
public static boolean explore(int i, Position[][][] m, int x, int y, String[] p, int s){
    String curr = m[i][y][x].getNums();
    if(allExplored(s, i, m)){
        return true;
    }
    if(!m[i][y][x].isVisited()){
        m[i][y][x].setPrev();

        if(curr.charAt(0) == '0'){ //North
            String temp = m[i][y][x].getNums();
            temp = "2" + temp.substring(1);
            m[i][y][x].setNums(temp);
            p[i] = p[i] + "n";
            if(explore(i, m, x, y-1, p, s)){
                p[i] = p[i] + "n";
                return true;
            }
        }
        if(curr.charAt(1) == '0'){ //South
            String temp = m[i][y][x].getNums();
            temp = temp.substring(0,1) + "2" + temp.substring(2);
            m[i][y][x].setNums(temp);
            if(p[i] == null){
                p[i] = "s";
            }
            else{
                p[i] = p[i] + "s";
            }
            if(explore(i, m, x, y+1, p, s)){
                if(p[i] == null){
                    p[i] = "s";
                }
                else{
                    p[i] = p[i] + "s";
                }
                return true;
            }
        }
        if(curr.charAt(2) == '0'){ //West
            String temp = m[i][y][x].getNums();
            temp = temp.substring(0,2) + "2" + temp.substring(3);
            m[i][y][x].setNums(temp);
            if(p[i] == null){
                p[i] = "w";
            }
            else{
                p[i] = p[i] + "w";
            }
            if(explore(i, m, x-1, y, p, s)){
                if(p[i] == null){
                    p[i] = "w";
                }
                else{
                    p[i] = p[i] + "w";
                }
                return true;
            }
        }
        if(curr.charAt(3) == '0'){//East
            String temp = m[i][y][x].getNums();
            temp = temp.substring(0,3) + "2";
            m[i][y][x].setNums(temp);
            if(p[i] == null){
                p[i] = "e";
            }
            else{
                p[i] = p[i] + "e";
            }
            if(explore(i, m, x+1, y, p, s)){
                if(p[i] == null){
                    p[i] = "e";
                }
                else{
                    p[i] = p[i] + "e";
                }
                return true;
            }
        }
    }

    return false;
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