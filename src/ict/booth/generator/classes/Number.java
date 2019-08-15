package ict.booth.generator.classes;

final public class Number {

    private final String number;

    private Number(String number) {
        this.number = number;
    }

    String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number;
    }

    static Number create(String number) {
        return new Number(number);
    }
}
