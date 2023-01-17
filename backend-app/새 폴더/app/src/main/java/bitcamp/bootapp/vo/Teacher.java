package bitcamp.bootapp.vo;

// 회원 데이터를 담을 메모리를 설계한다.
public class Teacher {

  private String name;
  private String tel;
  private String email;
  private byte degree;
  private String univ;
  private String major;
  private int pay;
  private int no;
  private String createdDate;


  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public byte getDegree() {
    return degree;
  }
  public void setDegree(byte degree) {
    this.degree = degree;
  }
  public String getUniv() {
    return univ;
  }
  public void setUniv(String univ) {
    this.univ = univ;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public int getPay() {
    return pay;
  }
  public void setPay(int pay) {
    this.pay = pay;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

}
