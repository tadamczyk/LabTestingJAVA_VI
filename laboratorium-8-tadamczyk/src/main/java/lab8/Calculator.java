package lab8;

public class Calculator {
  public Calculator() {
    System.out.println("Calculator: " + this);
  }

  public int add(int n1, int n2) {
    return n1 + n2;
  }

  public int sub(int n1, int n2) {
    return n1 - n2;
  }

  public int multi(int n1, int n2) {
    return n1 * n2;
  }

  public int div(int n1, int n2) {
    return n1 / n2;
  }

  public boolean greater(int a, int b) {
    if (a > b) {
      return true;
    } else {
      return false;
    }
  }

  public boolean less(int a, int b) {
    if (a < b) {
      return true;
    } else {
      return false;
    }
  }

  public boolean equality(int a, int b) {
    if (a == b) {
      return true;
    } else {
      return false;
    }
  }
}