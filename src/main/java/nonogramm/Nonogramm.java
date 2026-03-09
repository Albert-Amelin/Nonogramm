package nonogramm;

import java.util.*;

/**
 * Represents a Nonogram (Picross) puzzle grid.
 *
 * <p>A Nonogram is a logic puzzle where rows and columns contain sequences
 * describing groups of consecutive filled cells. The player fills the grid
 * so that each row and column matches its sequence.</p>
 *
 * <p>Cell encoding:</p>
 * <ul>
 *   <li>'?' — unknown / unassigned</li>
 *   <li>'0' — empty (crossed)</li>
 *   <li>'1' — filled (colored)</li>
 * </ul>
 */
public class Nonogramm {

    private int rows;
    private int cols;
    private ArrayList<ArrayList<Integer>> rowSequences;
    private ArrayList<ArrayList<Integer>> colSequences;
    private char[][] picture;

    /**
     * Constructs a new Nonogram puzzle.
     *
     * @param rowSequences sequences describing filled segments for each row
     * @param colSequences sequences describing filled segments for each column
     */
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
        }
    }

    /**
     * Constructs a Nonogram puzzle from a compact encoded string containing
     * both row and column sequences.
     *
     * <p>The encoding format is structured as follows:</p>
     *
     * <pre>
     * row1num1,row1num2,...,;row2num1,row2num2,...,;...,;/col1num1,col1num2,...,;col2num1,col2num2,...,;...,;/
     * </pre>
     *
     * <ul>
     *   <li>Numbers inside a sequence are terminated by commas <code>,</code></li>
     *   <li>Sequences are terminated by semicolons <code>;</code></li>
     *   <li>The row block and column block are terminated by a slash <code>/</code></li>
     *   <li>The column block also terminates with a slash <code>/</code></li>
     * </ul>
     *
     * <p>Example:</p>
     * <pre>
     * "1,1,1,;2,2,;1,1,;4,;2,;/3,;1,1,;1,1,;1,2,;5,;/"
     * </pre>
     *
     * <p>This constructor parses the encoded string, creates the corresponding
     * row and column sequences, and initializes an empty puzzle grid where all
     * pixels are set to <code>'?'</code>.</p>
     *
     * @param rowAndColCode encoded representation of row and column sequences
     * @throws NumberFormatException if a sequence value cannot be parsed as an integer
     * @throws StringIndexOutOfBoundsException if the encoding format is malformed
     */
    public Nonogramm(String rowAndColCode)
    {
        ArrayList<ArrayList<Integer>> rowSequences = new ArrayList<>();
        ArrayList<ArrayList<Integer>> colSequences = new ArrayList<>();
        int charIndex = 0;
        while (rowAndColCode.charAt(charIndex) != '/'){
            ArrayList<Integer> rowSequence = new ArrayList<>();
            while (rowAndColCode.charAt(charIndex) != ';'){
                StringBuilder currentNumber = new StringBuilder();
                while (rowAndColCode.charAt(charIndex) != ','){
                    currentNumber.append(rowAndColCode.charAt(charIndex));
                    charIndex++;
                }
                rowSequence.add(Integer.parseInt(currentNumber.toString()));
                charIndex++;
            }
            rowSequences.add(rowSequence);
            charIndex++;
        }
        charIndex++;
        while (rowAndColCode.charAt(charIndex) != '/'){
            ArrayList<Integer> colSequence = new ArrayList<>();
            while (rowAndColCode.charAt(charIndex) != ';'){
                StringBuilder currentNumber = new StringBuilder();
                while (rowAndColCode.charAt(charIndex) != ','){
                    currentNumber.append(rowAndColCode.charAt(charIndex));
                    charIndex++;
                }
                colSequence.add(Integer.parseInt(currentNumber.toString()));
                charIndex++;
            }
            colSequences.add(colSequence);
            charIndex++;
        }

        this.rows = rowSequences.size();
        this.cols = colSequences.size();
        this.rowSequences = rowSequences;
        this.colSequences = colSequences;
        this.picture = new char[this.rows][this.cols];

        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                this.picture[i][j] = '?';
            }
        }
    }

    /**
     * Returns the number of columns in the puzzle grid.
     *
     * @return the column count
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the number of rows in the puzzle grid.
     *
     * @return the row count
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the sequence describing a specific row.
     *
     * @param row row index
     * @return list of consecutive filled segment lengths
     * @throws ArrayIndexOutOfBoundsException if the row index is invalid
     */
    public ArrayList<Integer> getSpecificRowSequence(int row)
    {
        if(row >= this.rows || row < 0){
            throw new ArrayIndexOutOfBoundsException("row must be within 0,...,rows-1.");
        }
        return this.rowSequences.get(row);
    }

    /**
     * Returns the sequence describing a specific column.
     *
     * @param col column index
     * @return list of consecutive filled segment lengths
     * @throws ArrayIndexOutOfBoundsException if the column index is invalid
     */
    public ArrayList<Integer> getSpecificColSequence(int col)
    {
        if(col >= this.cols || col < 0){
            throw new ArrayIndexOutOfBoundsException("col must be within 0,...,cols-1.");
        }
        return this.colSequences.get(col);
    }

    /**
     * Returns the pixel values of a row.
     *
     * @param row row index
     * @return character array representing the row
     * @throws ArrayIndexOutOfBoundsException if the row index is invalid
     */
    public char[] getRow(int row)
    {
        if(row >= this.rows || row < 0){
            throw new ArrayIndexOutOfBoundsException("row must be within 0,...,rows-1.");
        }
        return this.picture[row];
    }

    /**
     * Returns the pixel values of a column.
     *
     * @param col column index
     * @return character array representing the column
     * @throws ArrayIndexOutOfBoundsException if the column index is invalid
     */
    public char[] getCol(int col)
    {
        if(col >= this.cols || col < 0){
            throw new ArrayIndexOutOfBoundsException("col must be within 0,...,cols-1.");
        }

        char[] pictureCol = new char[this.rows];
        for(int i = 0; i<this.rows; i++){
            pictureCol[i] = this.picture[i][col];
        }
        return pictureCol;
    }

    /**
     * Sets a pixel value in the puzzle grid.
     *
     * @param row row index
     * @param col column index
     * @param pixel pixel value ('?', '0', or '1')
     * @throws IllegalArgumentException if pixel is not valid
     * @throws ArrayIndexOutOfBoundsException if row or column index is invalid
     */
    public void setPixel(int row, int col, char pixel)
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

    /**
     * Replaces the entire picture grid.
     *
     * @param picture new grid state
     */
    public void setPicture(char[][] picture){
        this.picture = picture;
    }

    /**
     * Prints the current puzzle grid to standard output.
     */
    public void printPicture(){
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                System.out.print(picture[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Checks whether a completed stripe (row or column) matches a given sequence.
     *
     * <p>The stripe must not contain unknown cells ('?').</p>
     *
     * @param stripe row or column to evaluate
     * @param sequence expected segment sequence
     * @return true if stripe matches the sequence exactly
     */
    public static boolean pictureStripeAdheresSequence(char[] stripe, ArrayList<Integer> sequence){
        for (char c : stripe) if (c == '?') return false;

        ArrayList<Integer> testSequence = new ArrayList<Integer>(List.of(0));
        int stripeIndex = 0;

        while(stripeIndex < stripe.length){
            if(stripe[stripeIndex] == '1'){
                testSequence.set(testSequence.size() - 1,
                        testSequence.get(testSequence.size() - 1) + 1);
            }else{
                while(stripeIndex < stripe.length && stripe[stripeIndex] == '0'){
                    stripeIndex++;
                }
                if(stripeIndex < stripe.length){
                    if(testSequence.getLast() != 0){
                        testSequence.add(1);
                    }else{
                        testSequence.set(testSequence.size() - 1,
                                testSequence.get(testSequence.size() - 1) + 1);
                    }
                }
            }
            stripeIndex++;
        }
        return testSequence.equals(sequence);
    }

    /**
     *
     * Checks whether a stripe (row or column) could be completed to match a given sequence.
     *
     * <p>The stripe can contain unknown cells ('?').</p>
     *
     * @param stripe row or column to complete
     * @param sequence expected segment sequence
     * @return null, if no solution exists, else a possible solution
     */
    public static char[] stripeSolvable(char[] stripe, ArrayList<Integer> sequence){
        boolean isFull = true;
        for (char c : stripe) if (c == '?'){
            isFull = false;
            break;
        }
        if(isFull){
            if(pictureStripeAdheresSequence(stripe, sequence)){
                return stripe.clone();
            }
            else return null;
        }else{
            for(int i = 0; i < stripe.length; i++){
                if(stripe[i] == '?'){
                    stripe[i] = '0';
                    char[] possibleSolution0 = stripeSolvable(stripe.clone(), sequence);
                    stripe[i] = '1';
                    char[] possibleSolution1 = stripeSolvable(stripe.clone(), sequence);
                    if(possibleSolution0 != null) return possibleSolution0;
                    else if(possibleSolution1 != null) return possibleSolution1;
                    else{
                        stripe[i] = '?';
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks whether a row is completely and correctly filled.
     *
     * @param row row index
     * @return true if the row matches its sequence
     */
    public boolean isRowComplete(int row){
        char[] pictureRow = this.getRow(row);
        return pictureStripeAdheresSequence(pictureRow, this.getSpecificRowSequence(row));
    }

    /**
     * Checks whether a column is completely and correctly filled.
     *
     * @param col column index
     * @return true if the column matches its sequence
     */
    public boolean isColComplete(int col){
        char[] pictureCol = this.getCol(col);
        return pictureStripeAdheresSequence(pictureCol, this.getSpecificColSequence(col));
    }

    /**
     * Determines whether the entire puzzle is solved correctly.
     *
     * @return true if all rows and columns satisfy their sequences
     */
    public boolean isComplete(){
        for(int row = 0; row < this.cols; row++){
            if(!isRowComplete(row)) return false;
        }
        for(int col = 0; col < this.rows; col++){
            if(!isColComplete(col)) return false;
        }
        return true;
    }
}