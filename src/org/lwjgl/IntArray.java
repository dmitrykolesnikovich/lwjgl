package org.lwjgl;

public class IntArray {
  public int[] items;
  public int size;

  public IntArray() {
    this(16);
  }

  public IntArray(int capacity) {
    items = new int[capacity];
  }

  public IntArray(IntArray array) {
    size = array.size;
    items = new int[size];
    System.arraycopy(array.items, 0, items, 0, size);
  }

  public void add(int value) {
    int[] items = this.items;
    if (size == items.length) items = resize(Math.max(8, (int) (size * 1.75f)));
    items[size++] = value;
  }

  public int get(int index) {
    if (index >= size) throw new IndexOutOfBoundsException("index can't be >= size: " + index + " >= " + size);
    return items[index];
  }

  public int removeIndex(int index) {
    if (index >= size) throw new IndexOutOfBoundsException("index can't be >= size: " + index + " >= " + size);
    int[] items = this.items;
    int value = items[index];
    size--;
    System.arraycopy(items, index + 1, items, index, size - index);
    return value;
  }

  public int pop() {
    return items[--size];
  }

  public void clear() {
    size = 0;
  }

  public int[] ensureCapacity(int additionalCapacity) {
    int sizeNeeded = size + additionalCapacity;
    if (sizeNeeded > items.length) resize(Math.max(8, sizeNeeded));
    return items;
  }

  protected int[] resize(int newSize) {
    int[] newItems = new int[newSize];
    int[] items = this.items;
    System.arraycopy(items, 0, newItems, 0, Math.min(size, newItems.length));
    this.items = newItems;
    return newItems;
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) return true;
    if (!(object instanceof IntArray)) return false;
    IntArray array = (IntArray) object;
    int n = size;
    if (n != array.size) return false;
    for (int i = 0; i < n; i++)
      if (items[i] != array.items[i]) return false;
    return true;
  }
}
