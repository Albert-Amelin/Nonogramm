import java.util.*;

public class Nonogramm {
    private int rows;
    private int cols;
    private ArrayList<ArrayList<Integer>> rowSequences;
    private ArrayList<ArrayList<Integer>> colSequences;
    private char[][] picture; // '?' = empty pixel, '0' = crossed pixel, '1' = colored pixel

    // Constructor
    public Nonogramm(ArrayList<ArrayList<Integer>> rowSequences,
                     ArrayList<ArrayList<Integer>> colSequences)
    {
        this.rows = rowSequences.size();
        this.cols = colSequences.size();
        this.rowSequences = rowSequences;
        this.colSequences = colSequences;
        this.picture = new char[this.rows][this.cols];
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                this.picture[i][j] = '?';
            }
        };
    }

    // Getter
    public ArrayList<Integer> getSpecificRowSequence(int row) throws ArrayIndexOutOfBoundsException
    {
        if(row >= this.rows || row < 0){
            throw new ArrayIndexOutOfBoundsException("row must be within 0,...,rows-1.");
        }else{
            return this.rowSequences.get(row);
        }
    }

    public ArrayList<Integer> getSpecificColSequence(int col) throws ArrayIndexOutOfBoundsException
    {
        if(col >= this.cols || col < 0){
            throw new ArrayIndexOutOfBoundsException("col must be within 0,...,cols-1.");
        }else{
            return this.colSequences.get(col);
        }
    }

    public char[] getRow(int row) throws ArrayIndexOutOfBoundsException
    {
        if(row >= this.rows || row < 0){
            throw new ArrayIndexOutOfBoundsException("row must be within 0,...,rows-1.");
        }else{
            return this.picture[row];
        }
    }

    public char[] getCol(int col) throws ArrayIndexOutOfBoundsException
    {
        if(col >= this.cols || col < 0){
            throw new ArrayIndexOutOfBoundsException("col must be within 0,...,cols-1.");
        }else{
            char[] pictureCol = new char[this.rows];
            for(int i = 0; i<this.rows; i++){
                pictureCol[i] = this.picture[i][col];
            }
            return pictureCol;
        }
    }

    // Setter
    public void setPixel(int row, int col, char pixel) throws IllegalArgumentException, ArrayIndexOutOfBoundsException
    {
        if(pixel != '?' && pixel != '0' && pixel != '1'){
            throw new IllegalArgumentException("pixel must be '?','0' or '1'.");
        }else if(row >= this.rows || row < 0){
            throw new ArrayIndexOutOfBoundsException("row must be within 0,...,rows-1.");
        }else if(col >= this.cols || col < 0){
            throw new ArrayIndexOutOfBoundsException("col must be within 0,...,cols-1.");
        }else {
            picture[row][col] = pixel;
        }
    }

    public void setPicture(char[][] picture) {
        this.picture = picture;
    }

    public void printPicture(){
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                System.out.print(picture[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isRowComplete(int row){
        char[] pictureRow = this.getRow(row);
        for(int i = 0; i < this.cols; i++){
            if(pictureRow[i] == '?') return false;
        }
        ArrayList<Integer> testRowSequence = new ArrayList<Integer>(List.of(0));
        int pictureRowIndex = 0;
        while(pictureRowIndex < this.cols){
            if(pictureRow[pictureRowIndex] == '1'){
                testRowSequence.set(testRowSequence.size() - 1, testRowSequence.get(testRowSequence.size() - 1) + 1);
            }else{
                while(pictureRowIndex < this.cols && pictureRow[pictureRowIndex] == '0'){
                    pictureRowIndex++;
                }
                if(pictureRowIndex < this.cols){
                    if(testRowSequence.getLast() != 0){
                        testRowSequence.add(1);
                    }else{
                        testRowSequence.set(testRowSequence.size() - 1, testRowSequence.get(testRowSequence.size() - 1) + 1);
                    }
                }
            }
            pictureRowIndex++;
        }
        return testRowSequence.equals(this.getSpecificRowSequence(row));
    }

    public boolean isColComplete(int col)
    {
        char[] pictureCol = this.getCol(col);
        for(int i = 0; i < this.rows; i++){
            if(pictureCol[i] == '?') return false;
        }
        ArrayList<Integer> testColSequence = new ArrayList<Integer>(List.of(0));
        int pictureColIndex = 0;
        while(pictureColIndex < this.rows){
            if(pictureCol[pictureColIndex] == '1'){
                testColSequence.set(testColSequence.size() - 1, testColSequence.get(testColSequence.size() - 1) + 1);
            }else{
                while(pictureColIndex < this.rows && pictureCol[pictureColIndex] == '0'){
                    pictureColIndex++;
                }
                if(pictureColIndex < this.rows){
                    if(testColSequence.getLast() != 0){
                        testColSequence.add(1);
                    }else{
                        testColSequence.set(testColSequence.size() - 1, testColSequence.get(testColSequence.size() - 1) + 1);
                    }
                }
            }
            pictureColIndex++;
        }
        return testColSequence.equals(this.getSpecificColSequence(col));
    }
}