#include <iostream>
using namespace std;

int cache[1000 + 1][2];
int solve(int n, int turn) {
	// 0: ����� ��, 1: â���� ��
	int& ret = cache[n][turn];
	if (ret != -1) return ret;
	bool calc = false;
	if (n - 1 >= 0) {
		calc = calc || !solve(n - 1, 1 - turn);
	}
	if (n - 3 >= 0) {
		calc = calc || !solve(n - 3, 1 - turn);
	}
	if (calc) {
		ret = 1;
	}
	else {
		ret = 0;
	}
	return ret;
}
int main() {
	/*
	N���� ���� ���� �� ����̰� �̱�ڴ���? �� ������ ��´ٰ� �غ���.
	�׷� �̰�
	(N-1)���� ���� ���� �� â���̰� ���ų� (N-3)���� ���� ���� ������ â���̰� ���� ���̶� ����.
	���⿡�� OR �����ӿ� ��������. ���� �ϳ��� â���̰� ���� ��찡 �ִٸ� �Ϻ��� ������ �ϹǷ�
	����̴� �̱�� �Ǳ� �����̴�.
	*/
	int N;
	cin >> N;
	for (int i = 0; i <= 1000; i++) {
		for (int j = 0; j < 2; j++) {
			cache[i][j] = -1;
		}
	}
	if (solve(N, 0) == 1) {
		cout << "SK" << endl;
	}
	else {
		cout << "CY" << endl;
	}
	return 0;
}