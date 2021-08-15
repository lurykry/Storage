package edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.parenthesesValidator;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.exception.UnknownSymbolException;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.InitialValidator;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.parenthesesValidator.syntax.SymbolWithPosition;
import edu.mansurov.storage.structues.Stack;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.parenthesesValidator.syntax.SymbolsPairs.*;

public class ParenthesesValidator implements InitialValidator {
    private static final List<Character> OPENING_SYMBOLS = Arrays.asList(
            OPENING_ROUND_BRACKET,
            OPENING_CURLY_BRACKET,
            OPENING_BOX_BRACKET,
            OPENING_ANGLE_BRACKET,
            OPENING_DOUBLE_QUOTE,
            OPENING_SINGLE_QUOTE
    );

    private static final List<Character> CLOSING_SYMBOLS = Arrays.asList(
            CLOSING_ROUND_BRACKET,
            CLOSING_CURLY_BRACKET,
            CLOSING_BOX_BRACKET,
            CLOSING_ANGLE_BRACKET,
            CLOSING_DOUBLE_QUOTE,
            CLOSING_SINGLE_QUOTE
    );

    /**
     * Validates that parentheses are matched and come in the right order
     * Expects not empty, query-containing string
     */
    @Override
    public ValidatorResponse initialValidation(String query) {
        Stack<SymbolWithPosition> stack = new Stack<>();
        char[] queryAsChars = query.toCharArray();

        for(int position = 0; position < queryAsChars.length; position++) {
            char symbol = queryAsChars[position];

            if(OPENING_SYMBOLS.contains(symbol)) {
                stack.push(new SymbolWithPosition(symbol, position));
            }

            if(CLOSING_SYMBOLS.contains(symbol)) {
                if(stack.size() == 0) return ValidatorResponseBuilder.unexpectedToken(symbol, position);

                char lastSymbol = stack.pop().getSymbol();
                boolean matches = ParenthesesComparator.matchesLastSymbol(symbol, lastSymbol);

                if(!matches) return ValidatorResponseBuilder.unexpectedToken(symbol, position);
            }
        }

        //if query contains neither parenthesis nor quotes, it is considered to be valid
        return stack.size() > 0
                ? ValidatorResponseBuilder.expectingClosingSymbols(stack)
                : ValidatorResponseBuilder.validationSucceeded();
    }

    private static class ParenthesesComparator {

        /**
         * Compares current closing symbol with last opening one
         * @param current closing symbol
         * @param last opening symbol
         * @return true if closing symbol makes up for a pair with the opening one
         */
        public static boolean matchesLastSymbol(char current, char last) {
            if(CLOSING_ROUND_BRACKET == current) return OPENING_ROUND_BRACKET == last;
            if(CLOSING_CURLY_BRACKET == current) return OPENING_CURLY_BRACKET == last;
            if(CLOSING_BOX_BRACKET == current) return OPENING_BOX_BRACKET == last;
            if(CLOSING_ANGLE_BRACKET == current) return OPENING_ANGLE_BRACKET == last;
            if(CLOSING_DOUBLE_QUOTE == current) return OPENING_DOUBLE_QUOTE == last;
            if(CLOSING_SINGLE_QUOTE == current) return OPENING_SINGLE_QUOTE == last;

            throw new UnknownSymbolException("Unknown symbol - " + current);
        }
    }


    private static class ValidatorResponseBuilder {
        private ValidatorResponseBuilder() {}

        public static ValidatorResponse unexpectedToken(char symbol, int position) {
            return ValidatorResponse.validationFailed(
                    "Unexpected token '" + symbol + "' at position " + position
            );
        }

        public static ValidatorResponse expectingClosingSymbols(Stack<SymbolWithPosition> stack) {
            return ValidatorResponse.validationFailed(
                    "Expecting closing symbols for " + errorMessage(stack)
            );
        }

        public static ValidatorResponse validationSucceeded() {
            return ValidatorResponse.validationSucceeded();
        }

        /**
         * Builds string with information about all symbols in stack
         */
        private static String errorMessage(Stack<SymbolWithPosition> stack) {
            StringJoiner errorMessage = new StringJoiner(", ");

            for(SymbolWithPosition element : stack) {
                errorMessage.add(element.getSymbolAtPositionString());
            }

            return errorMessage.toString();
        }
    }
}
