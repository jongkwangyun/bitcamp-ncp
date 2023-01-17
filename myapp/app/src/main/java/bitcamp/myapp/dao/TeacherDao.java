package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.vo.Teacher;

public class TeacherDao extends ObjectDao {

  int no;

  @Override
  public void insert(Object obj) {
    Teacher t = (Teacher) obj;
    t.setNo(++no);
    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    super.insert(t);
  }

  public Teacher findByNo(int no) {
    for (int i = 0; i < this.size(); i++) {
      Teacher t = (Teacher) this.objects[i];
      if (t.getNo() == no) {
        return t;
      }
    }
    return null;
  }

  @Override
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      Teacher t = (Teacher) this.objects[i];
      if (t.getNo() == ((Teacher) obj).getNo()) {
        return i;
      }
    }
    return -1;
  }
}







