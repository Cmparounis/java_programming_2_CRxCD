package util;

import java.util.*;

public class Error1 {

	public static void finder(ArrayList<String> text, String dictionary[]) {

		boolean match = false;
		int total = 0;

		// ������� �� � ����� ����� �����
		if(text != null && !text.isEmpty()) {

			// ������ ��� �������� ��� ������
			int sizeList = text.size();

			// ��������� ���� ��� ��������� �� ����
			text.replaceAll(String::toLowerCase);

			// �������� ��� ������������ ��� ������ �� �� ����������� ��� �������
			for (int i = 0; i <= sizeList; i++) {
				String word = text.get(i);

				for (int j = 0; j <= dictionary.length; j++) {

					if (word.equals(dictionary[j])) {
						match = true;
						break;
					}
				}

				if (match == false) {
					System.out.printf("� ���� %s ��� ������� ��� ������, text[i]");
				}
			}
		}
	}
}