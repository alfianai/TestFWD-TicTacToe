package com.test.fwd.tictactoe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {

    public int boardSize;

    private Tile[][] tiles;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        tiles = new Tile[boardSize][boardSize];

        for (int rowIndex = 0; rowIndex < tiles.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < tiles.length; columnIndex++) {
                tiles[rowIndex][columnIndex] = new Tile(rowIndex, columnIndex);
            }
        }
    }

    public Tile get(String tileId) {
        String[] indices = tileId.split("-");
        if (indices.length != 2) {
            return null;
        }

        int rowIndex = Integer.valueOf(indices[0]);
        int columnIndex = Integer.valueOf(indices[1]);
        return get(rowIndex, columnIndex);
    }

    public Tile get(int rowIndex, int columnIndex) {
        return tiles[rowIndex][columnIndex];
    }

    public Tile getRandomAvailable() {
        List<Tile> available = new ArrayList<>();

        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.isEmpty()) {
                    available.add(tile);
                }
            }
        }

        if (available.isEmpty()) {
            return null;
        }

        int randomNum = new Random().nextInt(available.size());
        return available.get(randomNum);
    }

    public boolean isFull() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if(tile.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public List<Tile> getRow(int rowIndex) {
        return Arrays.asList(tiles[rowIndex]);
    }

    public List<Tile> getColumn(int columnIndex) {
        List<Tile> column = new ArrayList<>();

        for (Tile[] row : tiles) {
            column.add(row[columnIndex]);
        }

        return column;
    }

    public List<Tile> getDiagonalLeftTopBottomRight() {
        List<Tile> diagonalLines = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < tiles.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < tiles.length; columnIndex++) {
                if (rowIndex == columnIndex) {
                    diagonalLines.add(tiles[rowIndex][columnIndex]) ;   
                }
            }
        }
        return diagonalLines;
    }

    public List<Tile> getDiagonalRightTopBottomLeft() {
        List<Tile> diagonalLines = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < tiles.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < tiles.length; columnIndex++) {
                if (rowIndex + columnIndex == tiles.length - 1) {
                    diagonalLines.add(tiles[rowIndex][columnIndex]) ;
                }
            }
        }
        return diagonalLines;
    }

    public List<List<Tile>> getAllLines() {
        List<List<Tile>> lines = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            lines.add(getRow(i));
        }

        for (int j = 0; j < boardSize; j++) {
            lines.add(getColumn(j));
        }

        lines.add(getDiagonalLeftTopBottomRight());
        lines.add(getDiagonalRightTopBottomLeft());

        return lines;
    }

    public void reset() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.setValue(Tile.Value.EMPTY);
            }
        }
    }
}