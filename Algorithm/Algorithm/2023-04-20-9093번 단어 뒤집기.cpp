#include <iostream>
using namespace std;

int main() {
	int T;
	cin >> T;
	cin.ignore(); // �̰� ȣ������ ������ \n �� input ���� �޾Ƽ� ������ ����
	for (int i = 0; i < T; i++) {
		char input[1000];
		cin.getline(input, 1000, '\n');
		int startPos = 0, endPos = 0;
		while (true) {
			endPos++;
			if (input[endPos] == ' ' || input[endPos] == 0) {
				for (int j = endPos - 1; j >= startPos; j--) {
					cout << input[j];
				}
				cout << ' ';
				startPos = endPos + 1;
				if (input[endPos] == 0) {
					break;
				}
			}
		}
		cout << endl;
	}
	return 0;
}