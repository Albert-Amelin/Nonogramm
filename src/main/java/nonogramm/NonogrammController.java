package nonogramm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

public class NonogrammController {
    @FXML
    private GridPane nonogramGrid;

    private Nonogramm puzzle;
    private Button[][] cells;
    private int rows = 5;
    private int cols = 5;

    @FXML
    public void initialize() {
        // Puzzle aus Main.java übernommen
        ArrayList<ArrayList<Integer>> rowSeqs = new ArrayList<>();
        rowSeqs.add(new ArrayList<>(List.of(1,1,1)));
        rowSeqs.add(new ArrayList<>(List.of(2,2)));
        rowSeqs.add(new ArrayList<>(List.of(1,1)));
        rowSeqs.add(new ArrayList<>(List.of(4)));
        rowSeqs.add(new ArrayList<>(List.of(2)));

        ArrayList<ArrayList<Integer>> colSeqs = new ArrayList<>();
        colSeqs.add(new ArrayList<>(List.of(3)));
        colSeqs.add(new ArrayList<>(List.of(1,1)));
        colSeqs.add(new ArrayList<>(List.of(1,1)));
        colSeqs.add(new ArrayList<>(List.of(1,2)));
        colSeqs.add(new ArrayList<>(List.of(5)));

        puzzle = new Nonogramm(rowSeqs, colSeqs);

        // Grid befüllen
        cells = new Button[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Button cell = new Button();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-background-color: white;");

                final int row = r;
                final int col = c;
                cell.setOnAction(e -> handleCellClick(row, col));

                cells[r][c] = cell;
                nonogramGrid.add(cell, c, r);
            }
        }
    }

    private void handleCellClick(int row, int col) {
        char current = puzzle.getRow(row)[col];
        if (current == '?') {
            puzzle.setPixel(row, col, '1');
            cells[row][col].setStyle("-fx-background-color: black;");
        } else if (current == '1') {
            puzzle.setPixel(row, col, '0');
            cells[row][col].setStyle("-fx-background-color: lightgray;");
        } else {
            puzzle.setPixel(row, col, '?');
            cells[row][col].setStyle("-fx-background-color: white;");
        }
    }

    @FXML
    private void handleCheck() {
        if (puzzle.isComplete()) {
            System.out.println("Gelöst!");
        } else {
            System.out.println("Noch nicht korrekt.");
        }
    }
}