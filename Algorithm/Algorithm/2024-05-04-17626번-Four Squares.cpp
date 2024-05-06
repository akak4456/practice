#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
int dp[50000 + 1];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n;
	cin >> n;
	for (int i = 0; i <= n; i++) {
		dp[i] = -1;
	}
	for (int i = 1; i * i <= n; i++) {
		dp[i * i] = 1;
	}
	for (int i = 1; i <= n; i++) {
		if (dp[i] == -1) {
			dp[i] = 987654321;
			for (int j = 1; j * j < i; j++) {
				if (dp[i - j * j] + 1 < dp[i]) {
					dp[i] = dp[i - j * j] + 1;
				}
			}
		}
	}
	cout << dp[n] << "\n";
	return 0;
}