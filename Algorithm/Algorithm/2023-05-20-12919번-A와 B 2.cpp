#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

bool isPossible = false;
void solve(string S, string T) {
	if (isPossible) return;
	if (S.size() == T.size()) {
		if (S == T) {
			isPossible = true;
		}
		return;
	}
	if (T[0] == 'A') {
		if (T[T.size() - 1] == 'A') {
			string newT = string(T);
			newT.erase(newT.end() - 1);
			solve(S, newT);
		}
 	}
	else {
		if (T[T.size() - 1] == 'A') {
			string newT = string(T);
			newT.erase(newT.end() - 1);
			solve(S, newT);
		}
		string newT = string(T);
		reverse(newT.begin(), newT.end());
		newT.erase(newT.end() - 1);
		solve(S, newT);
	}
}

int main() {
	/*
	S���� � ������ ���� T�� ���� ���� ����� ���� ����� ����. �ٵ� �ݴ�� T���� S�� ���� ���� ���?
	�׷��� �ݴ� ������ �����ϱ�?
	1) ���ڿ��� �ڿ� A�� ����.
	2) ���ڿ��� ������ ���ڿ� �ڿ� B�� ����.
	�׷� �̰� ���� �� �� �ִ� ���� T���� ���� ���� A��� 2) ������ ������ �� ���ٴ� ���̴�. �׷��ϱ� ���� ���� A��� 1) ������ �����ؾ� �Ѵٴ� ���̴�.
	�׷��� �̹����� T�� ���� ���� B��� �غ���. �׷� T�� ���� �ڰ� A�Ǵ� B�� �� ���ε� �ϴ� T�� ���� �ڰ� B��� 1) ������ ������ ���� ����. �̶��� 2) ������ �����ؾ߸� �Ѵ�.
	�ݴ�� T�� ���� �ڰ� A�̸� 1) �Ǵ� 2)�� ������ ������ �� �ִ�.
	*/
	string S, T;
	cin >> S >> T;
	solve(S, T);
	if (isPossible) {
		cout << "1\n";
	}
	else {
		cout << "0\n";
	}
	return 0;
}