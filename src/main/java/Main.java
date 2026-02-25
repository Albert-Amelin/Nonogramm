import java.util.*;

public class Main {
    public static void main(String[] args){
        int rows = 5;
        int cols = 5;

        ArrayList<ArrayList<Integer>> rowSequences = new ArrayList<ArrayList<Integer>>(rows);
        ArrayList<Integer> row0 = new ArrayList<Integer>(List.of(1,1,1));
        ArrayList<Integer> row1 = new ArrayList<Integer>(List.of(2,2));
        ArrayList<Integer> row2 = new ArrayList<Integer>(List.of(1,1));
        ArrayList<Integer> row3 = new ArrayList<Integer>(List.of(4));
        ArrayList<Integer> row4 = new ArrayList<Integer>(List.of(2));
        rowSequences.add(row0);
        rowSequences.add(row1);
        rowSequences.add(row2);
        rowSequences.add(row3);
        rowSequences.add(row4);

        ArrayList<ArrayList<Integer>> colSequences = new ArrayList<ArrayList<Integer>>(cols);
        ArrayList<Integer> col0 = new ArrayList<Integer>(List.of(3));
        ArrayList<Integer> col1 = new ArrayList<Integer>(List.of(1,1));
        ArrayList<Integer> col2 = new ArrayList<Integer>(List.of(1,1));
        ArrayList<Integer> col3 = new ArrayList<Integer>(List.of(1,2));
        ArrayList<Integer> col4 = new ArrayList<Integer>(List.of(5));
        colSequences.add(col0);
        colSequences.add(col1);
        colSequences.add(col2);
        colSequences.add(col3);
        colSequences.add(col4);

        Nonogramm N = new Nonogramm(rowSequences, colSequences);

        char[][] picture = {{'1','0','1','0','1'},
                {'1','1','0','1','1'},
                {'1','0','0','0','1'},
                {'0','1','1','1','1'},
                {'0','0','0','1','1'}};
        N.setPicture(picture);
        for(int i = 0; i<rows; i++){
            System.out.println(N.isRowComplete(i));
        }
        System.out.println();
        for(int j = 0; j<cols; j++){
            System.out.println(N.isColComplete(j));
        }
    }
}