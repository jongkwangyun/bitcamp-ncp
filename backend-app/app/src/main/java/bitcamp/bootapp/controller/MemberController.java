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
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

//@CrossOrigin(origins = "http://127.0.0.1:5500")  // origins = {}, 값 한개일 때 중괄호 생략 가능
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class MemberController {

  MemberDao memberDao = new MemberDao();

  @PostMapping("/members")  // localhost:8080 생략됨
  public Object addMember(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) boolean working,
      @RequestParam(required = false) char gender,
      @RequestParam(required = false) byte level,
      @RequestParam(required = false) String createdDate) {
    // @RequestParam("title") String title 이렇게 작성해야 하지만 괄호 지정하지 않으면 해당 이름으로 파라미터 찾는다.
    // @String title 처럼 RequestParam 생략 가능. @에서 생략하면 RequestParam 이다.
    // @RequestParam(required = false) String title 처럼 필수가 아니라고 지정할 수 있다. 값이 없어도 에러 띄우지 말도록 한다.

    Member m = new Member();
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.memberDao.insert(m);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status",  "success");

    return contentMap;  // 객체를 던지면 SpringBoot가 json 형식으로 바꾼다.
  }


  @GetMapping("/members")
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status",  "success");
    contentMap.put("data",  members);

    return contentMap;
  }

  @GetMapping("/members/{memberNo}")
  public Object getMember(@PathVariable int memberNo) {
    // 주소에 있는 변수를 저장할때는 @PathVariable라 하면 SpringBoot가 저장해준다.

    Member m = this.memberDao.findByNo(memberNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("message", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status",  "success");
      contentMap.put("data", m);
    }

    return contentMap;
  }

  @PutMapping("/members/{memberNo}")
  public Object updateMember(
      @PathVariable int memberNo,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) String working) {

    Map<String, Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(memberNo);
    if (old == null) {
      contentMap.put("status",  "failure");
      contentMap.put("data",  "회원이 없습니다.");
      return contentMap;
    }

    Member m = new Member();
    m.setNo(memberNo);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(m);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
      @PathVariable int memberNo) {
    // 주소에 있는 변수를 저장할때는 @PathVariable라 하면 SpringBoot가 저장해준다.

    Member m = this.memberDao.findByNo(memberNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("message", "회원이 없습니다.");
    } else {
      this.memberDao.delete(m);
      contentMap.put("status",  "success");
    }

    return contentMap;
  }

}
