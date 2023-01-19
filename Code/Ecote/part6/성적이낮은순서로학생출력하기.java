package Ecote.part6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class 성적이낮은순서로학생출력하기 {
	public static void main(String[] args) {
		Student std1 = new Student("홍길동", 95);
		Student std2 = new Student("이순신", 77);

		Student [] arr = {std1, std2};

		mySolution(arr);
	}

	// 20m
	// 입력: 학생들의 수(int a), 학생들의 이름과 성적 배열(Student[] arr)
	// 출력: 성적 순으로 학생들의 이름을 출력(같은 성적은 순서 상관x)
	// 풀이 방향: 이름과 성적을 묶은 타입을 만들고, comparator를 구현하여 Collection.sort로 비교.
	// 시간 복잡도: n(nlogn)
	public static void mySolution(Student[] a) {
		List<Student> std = new ArrayList<>(Arrays.asList(a));

		Collections.sort(std, new Comparator<Student>() {
			@Override
			public int compare(Student a, Student b) {
				return b.getScore() - a.getScore();
			}
		});

		for (Student s: std) {
			System.out.println(s.getName());
		}
	}

	private static class Student {
		private String name;
		private int score;

		public Student(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}
}
