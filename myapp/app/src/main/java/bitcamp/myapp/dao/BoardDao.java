package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.vo.Board;

public class BoardDao extends ObjectDao {

  int no;

  @Override
  public void insert(Object obj) {
    Board b = (Board) obj;
    b.setNo(++no);
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    super.insert(b);
  }

  public Board findByNo(int no) {
    for (int i = 0; i < this.size(); i++) {
      Board b = (Board) this.objects[i];
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

  @Override
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      Board b = (Board) this.objects[i];
      if (b.getNo() == ((Board) obj).getNo()) {
        return i;
      }
    }
    return -1;
  }
}







