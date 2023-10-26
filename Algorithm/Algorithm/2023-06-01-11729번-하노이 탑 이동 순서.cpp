#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int>> sol;

void solve(int N, int from, int to) {
	if (N == 1) {
		sol.push_back(make_pair(from, to));
		return;
	}
	int remain = 0;
	for (int i = 1; i <= 3; i++) {
		if (i != from && i != to) {
			remain = i;
			break;
		}
	}
	solve(N - 1, from, remain);
	sol.push_back(make_pair(from, to));
	solve(N - 1, remain, to);
}

int main() {
	std::ios::sync_with_stdio(false);
	int N;
	cin >> N;
	/*
	solve �Լ��� N���� ����� from ��뿡�� to ���� �ű� �� �ִ� �Լ��̴�.
	�ٽø��� solve(N, 1, 3) �� �츮�� ����ϰ��� �ϴ� ������ ���̴�.
	�̰�
	- solve(N - 1, from, remain)
	- from ���� to �� �� �ϳ� �ű��
	- solve(N - 1, remain, to)
	�� ��Ȯ�� ����.
	*/
	solve(N, 1, 3);
	cout << sol.size() << "\n";
	for (int i = 0; i < sol.size(); i++) {
		cout << sol[i].first << ' ' << sol[i].second << "\n";
	}
	return 0;
}