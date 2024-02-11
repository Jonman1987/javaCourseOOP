package range_class;

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

    public Range getRangesIntersection(Range range) {
        if (range.to > this.from && range.from < this.to) {
            return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
        }

        return null;
    }

    public Range[] getRangesUnion(Range range) {
        if (range.from <= this.to && range.to >= this.from) {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        }

        return new Range[]{new Range(Math.min(this.from, range.from), Math.min(this.to, range.to)),
                new Range(Math.max(this.from, range.from), Math.max(this.to, range.to))};
    }

    public Range[] getRangesDifference(Range range) {
        if (range.to <= this.from || range.from >= this.to) {
            return new Range[]{this};
        }

        if (range.from <= this.from && range.to >= this.to) {
            return new Range[]{};
        }

        if (range.from > this.from && range.to < this.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        }

        if (range.from > this.from) {
            return new Range[]{new Range(this.from, range.from)};
        }

        return new Range[]{new Range(range.to, this.to)};
    }

    public String toString() {
        return "(" + from + ", " + to + ")";
    }
}
