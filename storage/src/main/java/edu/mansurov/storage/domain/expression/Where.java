package edu.mansurov.storage.domain.expression;

import java.util.*;

/**
 * Represents where-clause as a set of predicates
 */
public class Where {
    private final Map<Integer, Predicate> predicates = new HashMap<>();

    private Where() {}

    private Where(Predicate predicate) {
        addPredicate(predicate);
    }

    private Where(List<Predicate> predicates) {
        addPredicate(predicates);
    }

    public static Where emptyWhere() {
        return new Where();
    }

    public static Where newWhere(Predicate predicate) {
        return new Where(predicate);
    }

    public static Where newWhere(List<Predicate> predicates) {
        return new Where(predicates);
    }

    public void addPredicate(Predicate predicate) {
        predicates.put(predicate.hashCode(), predicate);
    }

    public void addPredicate(List<Predicate> predicates) {
        for(Predicate predicate : predicates) {
            this.predicates.put(predicate.hashCode(), predicate);
        }
    }

    public Map<Integer, Predicate> getPredicates() {
        return Collections.unmodifiableMap(predicates);
    }

    public boolean isWhereEmpty() {
        return predicates.isEmpty();
    }

    @Override
    public String toString() {
        return predicates.toString();
    }

    /**
     * Predicate in Where-clause:
     * - arg1 operator arg2
     */
    private static class Predicate {
        /**
         * Arguments can be either concrete values, fields or functions
         */
        private final String arg1;
        private final String arg2;
        private final String operator;

        public Predicate(String arg1, String arg2, String operator) {
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.operator = operator;
        }

        public String getArg1() {
            return arg1;
        }

        public String getArg2() {
            return arg2;
        }

        public String getOperator() {
            return operator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Predicate predicate = (Predicate) o;

            return arg1.equals(predicate.arg1) &&
                    arg2.equals(predicate.arg2) &&
                    operator.equals(predicate.operator);
        }

        @Override
        public int hashCode() {
            return Objects.hash(arg1, arg2, operator);
        }

        @Override
        public String toString() {
            return "Predicate{" +
                    "arg1='" + arg1 + '\'' +
                    ", arg2='" + arg2 + '\'' +
                    ", operator='" + operator + '\'' +
                    '}';
        }
    }
}
