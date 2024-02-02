package ru.academits.danilov_e.Range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range intersection(double from, double to) {
        if (from > this.from && to < this.to) {
            return new Range(from, to);
        } else if (from < this.from && to > this.to) {
            return new Range(this.from, this.to);
        } else if (from > this.from && from < this.to) {
            return new Range(from, this.to);
        } else if (to > this.from && to < this.to) {
            return new Range(this.from, to);
        }

        return null;
    }
}
