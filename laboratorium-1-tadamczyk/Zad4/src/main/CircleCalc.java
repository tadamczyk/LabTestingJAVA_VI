package main;

public class CircleCalc {
  public double circumfence(Circle c) {
    return 2 * Math.PI * c.getR();
  }

  public double poleCircle(Circle c) {
    return Math.PI * (Math.pow(c.getR(), 2));
  }
}
