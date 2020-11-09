//Quan Do
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
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
//        ArrayList<String> test = new ArrayList<>();
        for(int i = 0; i < totalMazes; i++){ //run maze
            runMaze(i, mazes, path, size);
        }
//        System.out.println(Arrays.toString(path));


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
        
        int num = 0;
        int initial = Integer.parseInt(similarity[0][1]);
        for(int i = 0; i < count; i ++){
            if(Integer.parseInt(similarity[i][1]) < initial){
                num = i;
                initial = Integer.parseInt(similarity[i][1]);
            }
        }
//        for(int i = 0; i < count; i++){ //Print array with similarites
//            for(int j = 0; j < 2; j++){
//                System.out.print(similarity[i][j] + ": ");
//            }
//            System.out.println();
//        }
//        for(int i = 0; i < totalMazes; i++){ //Print mazes
//            for(int j = 0; j < size; j++){
//                for(int k = 0; k < size; k++){
//                    System.out.print(mazes[i][j][k].getNums());
//            }
//                System.out.println();
//            }
//            System.out.println();
//        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(similarity[num][0]);
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
public static void runMaze(int i, Position[][][] m, String[] p, int s){
        int x = 0;
        int y = 0;
        String dir = null;
        while(!allExplored(s, i , m)){
            if(!m[i][y][x].getNums().contains("b")){
                if(m[i][y][x].getNums().charAt(0) == '0' && !m[i][y-1][x].getNums().contains("v") && y-1 >= 0){ //North
                    String temp = m[i][y][x].getNums();
                    temp = "v" + temp.substring(1);
                    m[i][y][x].setNums(temp);
//                    p.add("n");
                    p[i] = p[i] + "n";
                    dir = "n";
                    y--;
                }
                else if(m[i][y][x].getNums().charAt(1) == '0' && !m[i][y+1][x].getNums().contains("v") && y+1 < s){ //South
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,1) + "v" + temp.substring(2);
                    m[i][y][x].setNums(temp);
//                    p.add("s");
                    if(p[i] == null){
                        p[i] = "s";
                    }
                    else{
                        p[i] = p[i] + "s";
                    }
                    dir = "s";
                    y++;
                }
                else if(m[i][y][x].getNums().charAt(2) == '0' && !m[i][y][x-1].getNums().contains("v") && x-1 >= 0){  //West
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,2) + "v" + temp.substring(3);
                    m[i][y][x].setNums(temp);
//                    p.add("w");
                    p[i] = p[i] + "w";
                    dir = "w";
                    x--;
                }
                else if(m[i][y][x].getNums().charAt(3) == '0' && !m[i][y][x+1].getNums().contains("v") && x+1 < s){ //East
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,3) + "v";
                    m[i][y][x].setNums(temp);
//                    p.add("e");
                    if(p[i] == null){
                        p[i] = "e";
                    }
                    else{
                        p[i] = p[i] + "e";
                    }
                    dir = "e";
                    x++;
                }
                else if(m[i][y][x].getNums().contains("0") && m[i][y][x].getNums().contains("v")){
                    if(m[i][y][x].getNums().charAt(0) == '0' && y-1 >= 0){
                        String temp = m[i][y][x].getNums();
                        temp = "v" + temp.substring(1);
                        m[i][y][x].setNums(temp);
//                        p.add("n");
                    p[i] = p[i] + "n";
                        dir = "n";
                        y--;
                    }
                    else if(m[i][y][x].getNums().charAt(1) == '0' && y+1 < s){
                        String temp = m[i][y][x].getNums();
                        temp = temp.substring(0,1) + "v" + temp.substring(2);
                        m[i][y][x].setNums(temp);
//                        p.add("s");
                    if(p[i] == null){
                        p[i] = "s";
                    }
                    else{
                        p[i] = p[i] + "s";
                    }
                        dir = "s";
                        y++;
                    }
                    else if(m[i][y][x].getNums().charAt(2) == '0' && x-1 >= 0){
                        String temp = m[i][y][x].getNums();
                        temp = temp.substring(0,2) + "v" + temp.substring(3);
                        m[i][y][x].setNums(temp);
//                        p.add("w");
                    p[i] = p[i] + "w";
                        dir = "w";
                        x--;
                    }
                    else if(m[i][y][x].getNums().charAt(3) == '0' && x+1 < s){
                        String temp = m[i][y][x].getNums();
                        temp = temp.substring(0,3) + "v";
                        m[i][y][x].setNums(temp);
//                        p.add("e");
                    if(p[i] == null){
                        p[i] = "e";
                    }
                    else{
                        p[i] = p[i] + "e";
                    }
                        dir = "e";
                        x++;
                    }
                }
            }
            if(!canUp(i,m,x,y) && !canDown(i,m,x,y,s) && !canLeft(i,m,x,y) && !canRight(i,m,x,y,s)){
                if(dir == "n" && y+1 < s && !m[i][y+1][x].isVisited()){
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,1) + "b" + temp.substring(2);
                    m[i][y][x].setNums(temp);
                    y++;
                    dir = "s";
                    if(p[i] == null){
                        p[i] = "s";
                    }
                    else{
                        p[i] = p[i] + "s";
                    }
//                    p.add("s");
                    if(m[i][y][x].getNums().contains("0")){
                        if(m[i][y][x].getNums().charAt(1) == '0' && y+1 < s && !m[i][y+1][x].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,1) + "v" + temp.substring(2);
                            m[i][y][x].setNums(temp);
//                            p.add("s");
                            if(p[i] == null){
                                p[i] = "s";
                            }
                            else{
                                p[i] = p[i] + "s";
                            }
                            dir = "s";
                            y++;
                        }
                        else if(m[i][y][x].getNums().charAt(2) == '0' && x-1 >= 0 && !m[i][y][x-1].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,2) + "v" + temp.substring(3);
                            m[i][y][x].setNums(temp);
//                            p.add("w");
                            p[i] = p[i] + "w";
                            dir = "w";
                            x--;
                        }
                        else if(m[i][y][x].getNums().charAt(3) == '0' && x+1 < s && !m[i][y][x+1].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,3) + "v";
                            m[i][y][x].setNums(temp);
//                            p.add("e");
                            if(p[i] == null){
                                p[i] = "e";
                            }
                            else{
                                p[i] = p[i] + "e";
                            }
                            dir = "e";
                            x++;
                        }
                    }
                }
                else if(dir == "s" && y-1 >=0 && !m[i][y-1][x].isVisited()){
                    String temp = m[i][y][x].getNums();
                    temp = "b" + temp.substring(1);
                    m[i][y][x].setNums(temp);
                    y--;
                    dir = "n";
//                    p.add("n");
                    p[i] = p[i] + "n";
                    if(m[i][y][x].getNums().contains("0")){
                        if(m[i][y][x].getNums().charAt(0) == '0' && y-1 >= 0 && !m[i][y-1][x].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = "v" + temp.substring(1);
                            m[i][y][x].setNums(temp);
//                            p.add("n");
                            p[i] = p[i] + "n";
                            dir = "n";
                            y--;
                        }
                        else if(m[i][y][x].getNums().charAt(2) == '0' && x-1 >= 0 && !m[i][y][x-1].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,2) + "v" + temp.substring(3);
                            m[i][y][x].setNums(temp);
//                            p.add("w");
                            p[i] = p[i] + "w";
                            dir = "w";
                            x--;
                        }
                        else if(m[i][y][x].getNums().charAt(3) == '0' && x+1 < s && !m[i][y][x+1].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,3) + "v";
                            m[i][y][x].setNums(temp);
//                            p.add("e");
                            if(p[i] == null){
                                p[i] = "e";
                            }
                            else{
                                p[i] = p[i] + "e";
                            }
                            dir = "e";
                            x++;
                        }
                    }
                }
                else if(dir == "w" && x+1 < s && !m[i][y][x+1].isVisited()){
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,3) + "b";
                    m[i][y][x].setNums(temp);
                    x++;
                    dir = "e";
//                    p.add("e");
                    if(p[i] == null){
                        p[i] = "e";
                    }
                    else{
                        p[i] = p[i] + "e";
                    }
                    if(m[i][y][x].getNums().contains("0")){
                        if(m[i][y][x].getNums().charAt(0) == '0' && y-1 >= 0 && !m[i][y-1][x].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = "v" + temp.substring(1);
                            m[i][y][x].setNums(temp);
//                            p.add("n");
                            p[i] = p[i] + "n";
                            dir = "n";
                            y--;
                        }
                        else if(m[i][y][x].getNums().charAt(1) == '0' && y+1 < s && !m[i][y+1][x].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,1) + "v" + temp.substring(2);
                            m[i][y][x].setNums(temp);
//                            p.add("s");
                            if(p[i] == null){
                                p[i] = "s";
                            }
                            else{
                                p[i] = p[i] + "s";
                            }
                            dir = "s";
                            y++;
                        }
                        else if(m[i][y][x].getNums().charAt(3) == '0' && x-1 >= 0 && !m[i][y][x+1].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,3) + "v";
                            m[i][y][x].setNums(temp);
//                            p.add("e");
                            if(p[i] == null){
                                p[i] = "e";
                            }
                            else{
                                p[i] = p[i] + "e";
                            }
                            dir = "e";
                            x++;
                        }
                    }
                }
                else if(dir == "e" && x-1 >= 0 && !m[i][y][x - 1].isVisited()){
                    String temp = m[i][y][x].getNums();
                    temp = temp.substring(0,2) + "b" + temp.substring(3);
                    m[i][y][x].setNums(temp);
                    x--;
                    dir = "w";
//                    p.add("w");
                    p[i] = p[i] + "w";
                    if(m[i][y][x].getNums().contains("0")){
                        if(m[i][y][x].getNums().charAt(0) == '0' && y-1 >= 0 && !m[i][y-1][x].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = "v" + temp.substring(1);
                            m[i][y][x].setNums(temp);
//                            p.add("n");
                            p[i] = p[i] + "n";
                            dir = "n";
                            y--;
                        }
                        else if(m[i][y][x].getNums().charAt(1) == '0' && y+1 < s && !m[i][y+1][x].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,1) + "v" + temp.substring(2);
                            m[i][y][x].setNums(temp);
//                            p.add("s");
                            if(p[i] == null){
                                p[i] = "s";
                            }
                            else{
                                p[i] = p[i] + "s";
                            }
                            dir = "s";
                            y++;
                        }
                        else if(m[i][y][x].getNums().charAt(2) == '0' && x-1 >= 0 && !m[i][y][x-1].getNums().contains("v")){
                            temp = m[i][y][x].getNums();
                            temp = temp.substring(0,2) + "v" + temp.substring(3);
                            m[i][y][x].setNums(temp);
//                            p.add("w");
                            p[i] = p[i] + "w";
                            dir = "w";
                            x--;
                        }
                    }
                }
                else if(m[i][y][x].getNums().contains("0")){
                    if(m[i][y][x].getNums().charAt(0) == '0' && y-1 >= 0){ //north
                        String temp = m[i][y][x].getNums();
                        temp = "v" + temp.substring(1);
                        m[i][y][x].setNums(temp);
//                        p.add("n");
                        p[i] = p[i] + "n";
                        dir = "n";
                        y--;
                    }
                    else if(m[i][y][x].getNums().charAt(1) == '0' && y+1 < s){ //south
                        String temp = m[i][y][x].getNums();
                        temp = temp.substring(0,1) + "v" + temp.substring(2);
                        m[i][y][x].setNums(temp);
//                        p.add("s");
                        if(p[i] == null){
                            p[i] = "s";
                        }
                        else{
                            p[i] = p[i] + "s";
                        }
                        dir = "s";
                        y++;
                    }
                    else if(m[i][y][x].getNums().charAt(2) == '0' && x-1 >= 0){ // west
                        String temp = m[i][y][x].getNums();
                        temp = temp.substring(0,2) + "v" + temp.substring(3);
                        m[i][y][x].setNums(temp);
//                        p.add("w");
                        p[i] = p[i] + "w";
                        dir = "w";
                        x--;
                    }
                    else if(m[i][y][x].getNums().charAt(3) == '0' && x+1 < s){ //east
                        String temp = m[i][y][x].getNums();
                        temp = temp.substring(0,3) + "v";
                        m[i][y][x].setNums(temp);
//                        p.add("e");
                        if(p[i] == null){
                            p[i] = "e";
                        }
                        else{
                            p[i] = p[i] + "e";
                        }
                        dir = "e";
                        x++;
                    }
                }
            }
//            System.out.println(p);
//            for(int j = 0; j < s; j++){
//                for(int k = 0; k < s; k++){
//                    System.out.print(m[i][j][k].getNums());
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
    }
    public static boolean canUp(int i,Position[][][] m, int x, int y){
        if(y-1 < 0){
            return false;
        }
        if(m[i][y][x].getNums().charAt(0) == '1'){
            return false;
        }
        if(m[i][y-1][x].getNums().contains("v") && y-1 >= 0){
            return false;
        }
        return true;
    }
    public static boolean canDown(int i,Position[][][] m, int x, int y, int s){
        if(y + 1 >= s){
            return false;
        }
        if(m[i][y][x].getNums().charAt(1) == '1'){
            return false;
        }
        if(m[i][y+1][x].getNums().contains("v") && y+1 < s){
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
        if(m[i][y][x-1].getNums().contains("v") && x-1 >= 0){
            return false;
        }
        return true;
    }
    public static boolean canRight(int i,Position[][][] m, int x, int y, int s){
        if(x+1 >= s){
            return false;
        }
        if(m[i][y][x].getNums().charAt(3) == '1'){
            return false;
        }
        if(m[i][y][x+1].getNums().contains("v") && x+1 < s){
            return false;
        }
        return true;
    }

    static class Position{
        private String nums;
        private boolean visited;

        public Position(String x){
            nums = x;
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
        public String getNums(){
            return nums;
        }
        public boolean isVisited(){
            return visited;
        }
    }
}
