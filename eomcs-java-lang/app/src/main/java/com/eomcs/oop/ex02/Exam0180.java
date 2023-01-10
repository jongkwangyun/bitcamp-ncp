package com.eomcs.oop.ex02;

//0) 낱개 변수 사용
//1) 성적 데이터를 저장할 사용자 정의 데이터 타입을 만든다.
//2) 리팩토링: 메서드 추출(extract method), static nested class
//3) 리팩토링: 메서드 추출(extract method) = 한 개의 메서드는 한 개의 기능을 수행해야 한다.
//4) GRASP(General Responsibility Assignment Software Patterns) 패턴
//5) 인스턴스 메서드: 인스턴스 주소를 받는 더 쉬운 문법
//6) 패키지 멤버 클래스:
//7) 클래스를 역할에 따라 패키지로 분류
//8) 생성자 도입: 인스턴스를 생성할 때 값을 초기화시키는 특별한 메서드
//
import com.eomcs.oop.ex02.domain.Score2;

public class Exam0180 {

  public static void main(String[] args) {

    Score2 s1 = new Score2("홍길동", 100, 90, 85);
    printScore(s1);

    Score2 s2 = new Score2("임꺽정", 90, 80, 75);
    printScore(s2);

    Score2 s3 = new Score2("유관순", 80, 70, 65);
    printScore(s3);
  }

  static void printScore(Score2 s) {
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }


}



