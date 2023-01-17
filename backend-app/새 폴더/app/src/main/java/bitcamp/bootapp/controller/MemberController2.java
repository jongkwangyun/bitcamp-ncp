package bitcamp.bootapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

//@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class MemberController2 {

  MemberDao memberDao = new MemberDao();

  @PostMapping("/members")
  public Object addMember(Member member) {

    member.setName(name);

  }

}