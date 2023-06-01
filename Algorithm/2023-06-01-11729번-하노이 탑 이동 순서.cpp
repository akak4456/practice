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
	solve 함수는 N개의 블록을 from 장대에서 to 장대로 옮길 수 있는 함수이다.
	다시말해 solve(N, 1, 3) 은 우리가 출력하고자 하는 정답일 것이다.
	이건
	- solve(N - 1, from, remain)
	- from 에서 to 로 블럭 하나 옮기기
	- solve(N - 1, remain, to)
	랑 정확히 같다.
	*/
	solve(N, 1, 3);
	cout << sol.size() << "\n";
	for (int i = 0; i < sol.size(); i++) {
		cout << sol[i].first << ' ' << sol[i].second << "\n";
	}
	return 0;
}