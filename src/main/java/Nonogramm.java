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
            return this.rowSequences.get(col);
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

    public void printPicture(){
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                System.out.print(picture[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}