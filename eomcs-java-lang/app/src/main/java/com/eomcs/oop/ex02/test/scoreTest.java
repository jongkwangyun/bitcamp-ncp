package com.eomcs.oop.ex02.test;

import com.eomcs.oop.ex02.test.vo.Score;

//0) 낱개 변수 사용
//
public class scoreTest {

  public static void main(String[] args) {

    Score s1 = new Score("홍길동", 100, 90, 85);
    printScore(s1);

    Score s2 = new Score("임꺽정", 90, 80, 75);
    printScore(s2);

    Score s3 = new Score("유관순", 80, 70, 65);
    printScore(s3);

  }

  public static void printScore(Score s) {
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
}
