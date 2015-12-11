/**
*
*You are given a square map of size n×n. Each cell of the map has a value denoting its depth. We will call a cell of the map a cavity if and only if this cell is not on the border of the map and each cell adjacent to it has strictly smaller depth. Two cells are adjacent if they have a common side (edge).
*
*You need to find all the cavities on the map and depict them with the uppercase character X.
*
*Input Format
*
*The first line contains an integer, n, denoting the size of the map. Each of the following n lines contains n positive digits without spaces. Each digit (1-9) denotes the depth of the appropriate area.
*
*Constraints 
*1≤n≤100
*Output Format
*
*Output n lines, denoting the resulting map. Each cavity should be replaced with character X.
*/

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
     Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String linha = scan.next();
            for (int j = 0; j < n; j++) {
                char c = linha.toCharArray()[j];
                map[i][j] = Integer.parseInt(String.valueOf(c));
            }
        }
        System.out.println(verificarCavidade(map));
    }

    private static String verificarCavidade(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if(i == 0 || i == map.length-1 || j == 0 || j == map.length-1) {
                    if (j == map.length - 1)
                        sb.append(map[i][j] + "\n");
                    else
                        sb.append(map[i][j]);
                }
                else if(map[i][j] <= map[i-1][j] || map[i][j] <= map[i+1][j] || map[i][j] <= map[i][j-1] || map[i][j] <= map[i][j+1])
                    sb.append(map[i][j]);
                else
                    sb.append("X");
            }
        }

        return sb.toString();
    }
}
