#include <iostream>
#include <algorithm>
using namespace std;

int N;
string input[15];
const int FULL = (1 << 16) - 1;
int dp[FULL][10][15 + 1];
int solve(int ownerList, int lastPeople,int lastPrice) {
	int& ret = dp[ownerList][lastPrice][lastPeople];
	if (ret != -1) return ret;
	ret = 1;
	for (int i = 0; i < input[lastPeople - 1].size(); i++) {
		int price = input[lastPeople - 1][i] - '0';
		int owner = 1 << i;
		if ((owner & ownerList) == 0 && price >= lastPrice) {
			ret = max(ret, solve(ownerList | owner, i + 1, price) + 1);
		}
	}
	return ret;
}
int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> input[i];
	}
	for (int i = 0; i < FULL; i++) {
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k <= 15; k++) {
				dp[i][j][k] = -1;
			}
		}
	}
	cout << solve((1 << (1 - 1)), 1,0) << endl;
}