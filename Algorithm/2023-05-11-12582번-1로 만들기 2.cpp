#include <iostream>
#include <algorithm>
using namespace std;
const int MAX_N = 1000000;
const int INF = 987654321;
int DP[MAX_N + 1];
int N;
void go(int n) {
	if (n <= 0)
		return;
	cout << n << ' ';
	int ans[3] = { INF, INF, INF };
	if (n % 3 == 0) {
		ans[0] = DP[n / 3];
	}
	if (n % 2 == 0) {
		ans[1] = DP[n / 2];
	}
	ans[2] = DP[n - 1];
	int minIdx = -1;
	int minVal = INF;
	for (int i = 0; i < 3; i++) {
		if (ans[i] < minVal) {
			minVal = ans[i];
			minIdx = i;
		}
	}
	if (minIdx == 0)
		go(n / 3);
	else if (minIdx == 1)
		go(n / 2);
	else if (minIdx == 2)
		go(n - 1);
}
int main() {
	std::ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	//memset(DP, -1, sizeof(DP));
	cin >> N;
	DP[1] = 0;
	for (int i = 2; i <= N; i++) {
		DP[i] = DP[i - 1] + 1;
		if (i % 2 == 0) {
			DP[i] = min(DP[i], DP[i / 2] + 1);
		}
		if (i % 3 == 0) {
			DP[i] = min(DP[i], DP[i / 3] + 1);
		}
	}
	cout << DP[N] << '\n';
	go(N);
}