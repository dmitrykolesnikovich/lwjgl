package org.lwjgl;

final class MathUtil {
  private MathUtil() {
  }

  public static int nextPowerOfTwo(int value) {
    if (value == 0) {
      return 1;
    }
    value--;
    value |= value >> 1;
    value |= value >> 2;
    value |= value >> 4;
    value |= value >> 8;
    value |= value >> 16;
    return value + 1;
  }
}
