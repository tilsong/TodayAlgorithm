package Programmers.미분류;

public class 스킬트리 {
	// Input/Output Sample
	// skill	skill_trees							return
	// "CBD"	["BACDE", "CBADF", "AECB", "BDA"]	2

	// 820-
	// 입력: 스킬 순서(String skill), 스킬 트리 목록(String[] skill_trees)
	// 출력: 가능한 스킬 트리 수(int count)
	// 풀이 방향: 스킬 목록이 순서에 맞게 있는지 확인한다. indexOf를 사용하고, 상하관계가 일치하지 않으면 false 아니면 count++
	// 시간 복잡도: (N)

	public static void main(String[] args) {
		String[] arr = {"BACDE", "CBADF", "AECB", "BDA"};

		System.out.println(mySolution("CBD", arr));
	}

	public static int mySolution(String skill, String[] skill_trees) {
		int count = 0;

		String [] sk = new String[skill.length()];
		for (int i = 0; i < sk.length; i++) {
			sk[i] = Character.toString(skill.charAt(i));
		}

		for (int i = 0; i < skill_trees.length; i++) {
			String tree = skill_trees[i];

			int before = 0;
			for (int j = 0; j < sk.length; j++) {
				int ind = tree.indexOf(sk[i]);
				if(ind != -1 && ind >= before) {
					before = tree.indexOf(sk[i]);
				} else if(ind < before) {
					count++;
					break;
				}
			}

		}
		return count;
	}

}
