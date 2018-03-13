package util;

public class Line{
    public double slope;//斜率
    public double intercept;//截距
    public Line(){}
    public Line(double a, double b){
        slope = a;
        intercept = b;
    }

    @Override
    public boolean equals(Object obj) {
        return  obj instanceof Line &&
                slope == ((Line) obj).slope && intercept == ((Line) obj).intercept;
    }
}
