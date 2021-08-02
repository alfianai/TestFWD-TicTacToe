package com.test.fwd.tictactoe.model;

public class Tile {

    public enum Value {
        EMPTY(""),
        X("X"),
        O("O");

        private String text;

        private Value(String text) {
            this.text = text;
        }

        @Override
        public String toString(){
            return text;
        }
    }

    private Value value;
    private int rowIndex;
    private int columnIndex;

    public Tile(int rowIndex, int columnIndex) {
        this.value = Value.EMPTY;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public String getId() {
        return this.rowIndex + "-" + this.columnIndex;
    }

    public boolean isEmpty() {
        return this.value == Value.EMPTY;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
