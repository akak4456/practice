#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

int main() {
	/*
	ũ������ �� �� �ִ� ���Ը� ������������ ������ �غ���. �׸��� ��� ũ������ ���ÿ� �����δ�.
	�̷��� ��Ȳ���� ���� ���� ���Ը� �� �� �ִ� ũ������ a �� �ϰ� a ���ٴ� ū ���Ը� �� �� �ִ� ũ������ b ��� �غ���.
	�׷��ٸ� a�� �� �� �ִ� ���� ���ſ� �ڽ��� ���Ը� c ��� ����. �׷� �̰� a �� ��°� �����ϱ� b�� ��� �� �����ϱ�?
	�ٽ� ���� a�� �������� �ּ��� �ð��� ���? b�� �������� �ּ��� �ð��� ���? �̰� a�� �������� �ּ��� �ð��� �� ���̴�.
	�ֳ��ϸ� ���� �� ��Ȳ���� b�� c�� ��ٰ� �غ���. b �� a���� �ణ �� ���ſ� �͵� �� �� ������ ����� �����ϴ�.
	�׷��� a�� c���� ���ſ� ���� �� �� ����. ���� ���� c�� �����ִ� �ڽ� �߿��� ���� ������ ���̾��ٸ� a, b ���ÿ� �۵��� ���� ����.
	��� ���� a�� c�� �������� ���ÿ� �� ���� �ְ� �ȴ�.

	�ϴ� ���ÿ� ũ������ ���ư��Ƿ� �� �� �ִ� ���԰� ���� ũ���α����� ���Ը� ��°��� ����.
	*/
	int N;
	cin >> N;
	vector<int> crane(N);
	for (int i = 0; i < N; i++) {
		cin >> crane[i];
	}
	sort(crane.begin(), crane.end());
	int M;
	cin >> M;
	multiset<int> box;
	for (int i = 0; i < M; i++) {
		int t;
		cin >> t;
		box.insert(t);
	}
	int cnt = 0;
	bool isPossible = true;
	while (box.size() > 0) {
		int beforeSz = box.size();
		for (int i = 0; i < crane.size(); i++) {
			if (box.size() == 0) break;
			auto it = box.lower_bound(crane[i]);
			if (it == box.end() || *it > crane[i]) 
				it--;
			if (it != box.end() && *it <= crane[i]) {
				box.erase(it);
			}
		}
		if (beforeSz == box.size()) {
			isPossible = false;
			break;
		}
		cnt++;
	}
	if (isPossible) {
		cout << cnt << endl;
	}
	else {
		cout << -1 << endl;
	}
}