package edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.parenthesesValidator.syntax;

public class SymbolWithPosition {
    private final char symbol;
    private final int position;

    public SymbolWithPosition(char symbol, int position) {
        this.symbol = symbol;
        this.position = position;
    }

    /**
     * Builds string with symbol-at-position information
     */
    public String getSymbolAtPositionString() {
        return "'" + symbol + "' at position " + position;
    }

    public char getSymbol() {
        return symbol;
    }
}
