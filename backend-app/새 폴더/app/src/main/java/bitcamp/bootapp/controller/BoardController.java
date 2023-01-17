package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.BoardDao;
import bitcamp.bootapp.vo.Board;

//@CrossOrigin(origins = "http://127.0.0.1:5500")  // origins = {}, 값 한개일 때 중괄호 생략 가능
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class BoardController {

  BoardDao boardDao = new BoardDao();

  @PostMapping("/boards")  // localhost:8080 생략됨
  public Object addBoard(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password) {
    // @RequestParam("title") String title 이렇게 작성해야 하지만 괄호 지정하지 않으면 해당 이름으로 파라미터 찾는다.
    // @String title 처럼 RequestParam 생략 가능. @에서 생략하면 RequestParam 이다.
    // @RequestParam(required = false) String title 처럼 필수가 아니라고 지정할 수 있다. 값이 없어도 에러 띄우지 말도록 한다.

    Board b = new Board();
    b.setTitle(title);
    b.setContent(content);
    b.setPassword(password);
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());


    this.boardDao.insert(b);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status",  "success");

    return contentMap;  // 객체를 던지면 SpringBoot가 json 형식으로 바꾼다.
  }


  @GetMapping("/boards")
  public Object getBoards() {

    Board[] boards = this.boardDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status",  "success");
    contentMap.put("data",  boards);

    return contentMap;
  }

  @GetMapping("/boards/{boardNo}")
  public Object getBoard(@PathVariable int boardNo) {
    // 주소에 있는 변수를 저장할때는 @PathVariable라 하면 SpringBoot가 저장해준다.

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("message", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status",  "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }

  @PutMapping("/boards/{boardNo}")
  public Object updateBoard(
      @PathVariable int boardNo,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password) {

    Map<String, Object> contentMap = new HashMap<>();

    Board old = this.boardDao.findByNo(boardNo);
    if (old == null || !old.getPassword().equals(password)) {
      contentMap.put("status",  "failure");
      contentMap.put("data",  "게시글이 없거나 암호가 맞지 않습니다.");
      return contentMap;
    }

    Board b = new Board();
    b.setNo(boardNo);
    b.setTitle(title);
    b.setContent(content);
    b.setPassword(password);
    b.setCreatedDate(old.getCreatedDate());
    b.setViewCount(old.getViewCount());

    this.boardDao.update(b);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/boards/{boardNo}")
  public Object deleteBoard(
      @PathVariable int boardNo,
      @RequestParam(required = false) String password) {
    // 주소에 있는 변수를 저장할때는 @PathVariable라 하면 SpringBoot가 저장해준다.

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();

    if (b == null || !b.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("message", "게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.boardDao.delete(b);
      contentMap.put("status",  "success");
    }

    return contentMap;
  }

}
