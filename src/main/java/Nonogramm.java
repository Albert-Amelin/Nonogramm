import java.util.*;

public class Nonogramm {
    private final int rows = 10;
    private final int cols = 10;
    private ArrayList<ArrayList<Integer>> rowSequences;
    private ArrayList<ArrayList<Integer>> colSequences;
    private char[][] picture;

    public Nonogramm(ArrayList<ArrayList<Integer>> rowSequences,
                     ArrayList<ArrayList<Integer>> colSequences,
                     char[][] picture) {
        this.rowSequences = rowSequences;
        this.colSequences = colSequences;
        this.picture = picture;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ArrayList<ArrayList<Integer>> getRowSequences() {
        return rowSequences;
    }

    public void setRowSequences(ArrayList<ArrayList<Integer>> rowSequences) {
        this.rowSequences = rowSequences;
    }

    public ArrayList<ArrayList<Integer>> getColSequences() {
        return colSequences;
    }

    public void setColSequences(ArrayList<ArrayList<Integer>> colSequences) {
        this.colSequences = colSequences;
    }

    public char[][] getPicture() {
        return picture;
    }

    public void setPicture(char[][] picture) {
        this.picture = picture;
    }
}