import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

public class NonogrammTest {
    @BeforeEach
    void setup(){

    }

    @Test
    void testRowAndColComplete(){
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
            assertTrue(N.isRowComplete(i));
        }
        System.out.println();
        for(int j = 0; j<cols; j++){
            assertTrue(N.isColComplete(j));
        }
    }

    @Test
    void testRowAndColSolvable(){
        char[] stripe0 = {'?','?','?','?','?'};
        char[] stripe1 = {'?','?','1','1','?'};
        char[] stripe2 = {'1','0','1','1','0'};
        char[] stripe3 = {'1','?','?','?','?'};
        char[] stripe4 = {'1','1','1','0','1'};
        char[] stripe5 = {'1','1','0','0','0'};
        char[] stripe6 = {'1','1','1','1','1'};
        char[] stripe7 = {'1','0','1','1','?'};
        char[] stripe8 = {'1','0','?','1','0'};
        char[] stripe9 = {'1','0','1','?','?'};

        ArrayList<Integer> seq0 = new ArrayList<Integer>(List.of(3,1));
        ArrayList<Integer> seq1 = new ArrayList<Integer>(List.of(5));
        ArrayList<Integer> seq2 = new ArrayList<Integer>(List.of(2));
        ArrayList<Integer> seq3 = new ArrayList<Integer>(List.of(1,2));

        assertArrayEquals(stripe2, Nonogramm.stripeSolvable(stripe2, seq3));
        assertArrayEquals(stripe2, Nonogramm.stripeSolvable(stripe7, seq3));
        assertArrayEquals(stripe2, Nonogramm.stripeSolvable(stripe8, seq3));
        assertArrayEquals(stripe2, Nonogramm.stripeSolvable(stripe9, seq3));
        assertArrayEquals(stripe4, Nonogramm.stripeSolvable(stripe0, seq0));
        assertArrayEquals(null, Nonogramm.stripeSolvable(stripe1, seq0));
        assertArrayEquals(stripe6, Nonogramm.stripeSolvable(stripe3, seq1));
        assertArrayEquals(stripe5, Nonogramm.stripeSolvable(stripe3, seq2));
        assertArrayEquals(null, Nonogramm.stripeSolvable(stripe2, seq1));
    }

    @Test
    void testNonogrammComplete(){
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
        assertTrue(N.isComplete());
        N.setPixel(4,0,'1');
        assertFalse(N.isComplete());
        N.setPixel(4,0,'0');
        N.setPixel(4,4,'?');
        assertFalse(N.isComplete());
    }

    @Test
    void testSetPixel(){
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
        N.setPixel(0,0,'1');
        N.setPixel(1,1,'0');
        N.setPixel(1,4,'1');
        N.setPixel(1,0,'0');
        N.setPixel(2,3,'1');
        N.setPixel(3,3,'0');
        N.setPixel(4,4,'1');

        char[] expectedRow0 = {'1','?','?','?','?'};
        char[] expectedRow1 = {'0','0','?','?','1'};
        char[] expectedRow2 = {'?','?','?','1','?'};
        char[] expectedRow3 = {'?','?','?','0','?'};
        char[] expectedRow4 = {'?','?','?','?','1'};

        char[] expectedCol0 = {'1','0','?','?','?'};
        char[] expectedCol1 = {'?','0','?','?','?'};
        char[] expectedCol2 = {'?','?','?','?','?'};
        char[] expectedCol3 = {'?','?','1','0','?'};
        char[] expectedCol4 = {'?','1','?','?','1'};

        assertArrayEquals(expectedRow0, N.getRow(0));
        assertArrayEquals(expectedRow1, N.getRow(1));
        assertArrayEquals(expectedRow2, N.getRow(2));
        assertArrayEquals(expectedRow3, N.getRow(3));
        assertArrayEquals(expectedRow4, N.getRow(4));

        assertArrayEquals(expectedCol0, N.getCol(0));
        assertArrayEquals(expectedCol1, N.getCol(1));
        assertArrayEquals(expectedCol2, N.getCol(2));
        assertArrayEquals(expectedCol3, N.getCol(3));
        assertArrayEquals(expectedCol4, N.getCol(4));
    }
}
