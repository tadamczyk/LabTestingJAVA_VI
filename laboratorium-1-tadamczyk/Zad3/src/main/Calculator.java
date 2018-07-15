package main;

public class Calculator {
  public Calculator() {
    System.out.println("Calculator: " + this);
  }

  public double add(double n1, double n2) {
    return n1 + n2;
  }

  public double sub(double n1, double n2) {
    return n1 - n2;
  }

  public double multi(double n1, double n2) {
    return n1 * n2;
  }

  public double div(double n1, double n2) {
    return n1 / n2;
  }

  public boolean greater(double a, double b) {
    if (a > b) {
      return true;
    } else {
      return false;
    }
  }

  public boolean less(double a, double b) {
    if (a < b) {
      return true;
    } else {
      return false;
    }
  }

  public boolean equality(double a, double b) {
    if (a == b) {
      return true;
    } else {
      return false;
    }
  }
}