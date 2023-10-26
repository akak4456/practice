#include <iostream>
using namespace std;

int cache[1000 + 1][2];
int solve(int n, int turn) {
	// 0: 상근이 턴, 1: 창영이 턴
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
	N개의 돌이 있을 때 상근이가 이기겠느냐? 를 문제로 얻는다고 해보자.
	그럼 이건
	(N-1)개의 돌이 있을 때 창영이가 지거나 (N-3)개의 돌이 있을 때에도 창영이가 지는 것이랑 같다.
	여기에서 OR 조건임에 주의하자. 둘중 하나라도 창영이가 지는 경우가 있다면 완벽한 게임을 하므로
	상근이는 이기게 되기 때문이다.
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