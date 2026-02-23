import java.util.*;

public class Main {
    public static void main(String[] args){
        int rows = 3;
        int cols = 3;
        ArrayList<ArrayList<Integer>> rowSequences = new ArrayList<ArrayList<Integer>>(rows);
        ArrayList<Integer> l0 = new ArrayList<Integer>(List.of(1,1));
        ArrayList<Integer> l1 = new ArrayList<Integer>(List.of(2));
        ArrayList<Integer> l2 = new ArrayList<Integer>(List.of(3));
        rowSequences.add(l0);
        rowSequences.add(l1);
        rowSequences.add(l2);
        ArrayList<ArrayList<Integer>> colSequences = rowSequences;

        Nonogramm N = new Nonogramm(rowSequences, colSequences);
        N.printPicture();
        N.setPixel(0,0,'1');
        N.printPicture();
    }
}