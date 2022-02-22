package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model;
import org.mariuszgromada.math.mxparser.*;

public class Calculations {
    private String expression;
    private String result;

    private Calculations(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }
    public Calculations(String expression){
        Expression e = new Expression(expression);
        this.expression = expression;
        if(!e.checkSyntax()){
            this.result = e.getErrorMessage();
        }else{
            this.result = String.valueOf(e.calculate());
        }
    }

    //Using third party library: https://github.com/mariuszgromada/MathParser.org-mXparser
    public static Calculations calculate(String expression){
        Expression e = new Expression(expression);
        if(!e.checkSyntax()){
            return new Calculations(expression, e.getErrorMessage()); //Returning error message if something wrong with syntax
        }
        return new Calculations(expression, String.valueOf(e.calculate()));
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return expression + " = " + result;
    }

    public static void main(String[] args) { //For testing purposes
        Expression e = new Expression("0/0");
        System.out.println(e.getErrorMessage());
        System.out.println(e.checkSyntax());
        System.out.println(e.calculate());
    }
}
