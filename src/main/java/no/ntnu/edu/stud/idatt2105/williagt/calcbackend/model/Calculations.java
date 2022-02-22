package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model;
import org.mariuszgromada.math.mxparser.*;

public class Calculations {
    private final String expression;

    public Calculations(String expression) {
        this.expression = expression;
    }

    //Using third party library: https://github.com/mariuszgromada/MathParser.org-mXparser
    public static double calculate(String expression){
        Expression e = new Expression(expression);
        if(e.checkSyntax()){
            return e.calculate();
        }else{
            //Throw exception or something
        }
        return -9999999;
    }
    public static void main(String[] args) { //For testing purposes
        Expression e = new Expression("2..2+4");
        System.out.println(e.checkSyntax());
        System.out.println(e.calculate());
    }
}
