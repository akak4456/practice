#include <iostream>
using namespace std;

int my[9];
int enemy[9];

int main() {
	bool isUpset = false;
	/*
	1ȸ��, 2ȸ��, nȸ�� (1 <= n <= 9) �� ������
	*/
	int mySum = 0;
	/*
	1ȸ��, 2ȸ��, nȸ�� (1 <= n <= 9) �� ������
	*/
	int enemySum = 0;
	for (int i = 0; i < 9; i++) {
		cin >> my[i];
	}
	for (int i = 0; i < 9; i++) {
		cin >> enemy[i];
	}
	for (int i = 0; i < 9; i++) {
		mySum += my[i];
		/*
		������ '�̱�� �ִ� ������' ��� ǥ�ÿ� �ָ�����.
		���⿡�� (nȸ�� ������ ��������) > ((n-1)ȸ�� ������ ���� ����)
		�� �Ǵ��ؾ� �Ѵٴ� ���� ���������� �������.
		*/
		if (mySum > enemySum) {
			isUpset = true;
		}
		enemySum += enemy[i];
	}
	if (isUpset) {
		cout << "Yes\n";
	}
	else {
		cout << "No\n";
	}

	return 0;
}