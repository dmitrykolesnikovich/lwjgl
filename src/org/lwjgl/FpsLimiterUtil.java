package org.lwjgl;

public class FpsLimiterUtil {
  public static final long duration = 25;
  private long startTime;

  public void limit() {
    long finishTime = System.currentTimeMillis();
    if (startTime == 0) {
      startTime = finishTime;
    }
    long frameTime = finishTime - startTime;
    long sleepTime = duration - frameTime;
    if (sleepTime <= 0) {
      sleepTime = 1;
    }
    startTime = finishTime;
  }
}
