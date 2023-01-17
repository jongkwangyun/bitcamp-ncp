package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.vo.Student;

public class StudentDao extends ObjectDao {

  int no;

  @Override
  public void insert(Object obj) {
    Student s = (Student) obj;
    s.setNo(++no);
    s.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    super.insert(s);
  }

  public Student findByNo(int no) {
    for (int i = 0; i < this.size(); i++) {
      Student s = (Student) this.objects[i];
      if (s.getNo() == no) {
        return s;
      }
    }
    return null;
  }

  @Override
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      Student s = (Student) this.objects[i];
      if (s.getNo() == ((Student) obj).getNo()) {
        return i;
      }
    }
    return -1;
  }
}







