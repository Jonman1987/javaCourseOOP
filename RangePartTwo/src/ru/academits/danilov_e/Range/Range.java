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

    public Range getIntersectionInterval(double from, double to) {
        if (from >= this.from && to <= this.to) {
            return new Range(from, to);
        } else if (from < this.from && to > this.to) {
            return new Range(this.from, this.to);
        } else if (from >= this.from && from < this.to) {
            return new Range(from, this.to);
        } else if (to > this.from && to <= this.to) {
            return new Range(this.from, to);
        }

        return null;
    }

    public Range[] getCombinedIntervalArray(double from, double to) {
        int arrayLength;

        if (to < this.from || from > this.to) {
            arrayLength = 2;
        } else {
            arrayLength = 1;
        }

        Range[] array = new Range[arrayLength];

        if (to < this.from || from > this.to) {
            if (to < this.from) {
                array[0] = new Range(from, to);
                array[1] = new Range(this.from, this.to);
            } else {
                array[0] = new Range(this.from, this.to);
                array[1] = new Range(from, to);
            }
        } else if (from >= this.from && to <= this.to) {
            array[0] = new Range(this.from, this.to);
        } else if (from < this.from && to > this.to) {
            array[0] = new Range(from, to);
        } else if (from >= this.from && from < this.to) {
            array[0] = new Range(this.from, to);
        } else if (to > this.from && to <= this.to) {
            array[0] = new Range(from, this.to);
        }

        return array;
    }

    public Range[] getIntervalDifferenceArray(double from, double to) {
        int arrayLength;

        if (from == this.from && to == this.to) {
            return null;
        } else if(from == this.from || to == this.to){
            arrayLength = 1;
        }else {
            arrayLength = 2;
        }

        Range[] array = new Range[arrayLength];

        if(from == this.from || to == this.to){

        }

        return array;
    }
}
