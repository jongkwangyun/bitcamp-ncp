package bitcamp.myapp.dao;

import java.util.Arrays;

public abstract class ObjectDao {
  private static final int SIZE = 100;

  private int count;
  protected Object[] objects = new Object[SIZE];

  public void insert(Object obj) {
    this.objects[this.count++] = obj;
  }

  public Object[] findAll() {
    return Arrays.copyOf(objects, count);
  }

  public void update(Object obj) {
    this.objects[this.indexOf(obj)] = obj;
  }

  public void delete(Object obj) {
    for (int i = this.indexOf(obj) + 1; i < this.count; i++) {
      this.objects[i - 1] = this.objects[i];
    }
    this.objects[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  protected abstract int indexOf(Object obj);

  public int size() {
    return count;
  }
}







