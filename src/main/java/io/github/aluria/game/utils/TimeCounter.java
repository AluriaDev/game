package io.github.aluria.game.utils;

public class TimeCounter {
  
  private long start;
  
  public static TimeCounter fromNow() {
    return new TimeCounter(System.currentTimeMillis());
  }
  
  private TimeCounter(long start) {
    this.start = start;
  }
  
  public void reset() {
    this.start = System.currentTimeMillis();
  }
  
  public double elapsedTime() {
    long now = System.currentTimeMillis();
    return (now - start) / 1000.0;
  }
}
