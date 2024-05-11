#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
int board[1024][1024];
int dp[1024][1024];
int N, M;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			dp[i][j] = board[i][j];
			if (i > 0) {
				dp[i][j] += dp[i - 1][j];
			}
			if (j > 0) {
				dp[i][j] += dp[i][j - 1];
			}
			if (i > 0 && j > 0) {
				dp[i][j] -= dp[i - 1][j - 1];
			}
		}
	}
	while (M--) {
		int x1, y1, x2, y2;
		cin >> x1 >> y1 >> x2 >> y2;
		x1--;
		y1--;
		x2--;
		y2--;
		int ans = dp[x2][y2];
		if (x1 > 0) {
			ans -= dp[x1 - 1][y2];
		}
		if (y1 > 0) {
			ans -= dp[x2][y1 - 1];
		}
		if (x1 > 0 && y1 > 0) {
			ans += dp[x1 - 1][y1 - 1];
		}
		cout << ans << "\n";
	}
	return 0;
}