#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
string mbti[16] = { "ISTJ", "ISFJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP", "ESTP", "ESFP", "ENFP", "ENTP", "ESTJ", "ESFJ", "ENFJ", "ENTJ" };
int mbtiCnt[16];
int minCnt = 987654321;
void solve(vector<string> mbtis) {
	if (mbtis.size() == 3) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			if (mbtis[0][i] != mbtis[1][i]) {
				cnt++;
			}
			if (mbtis[1][i] != mbtis[2][i]) {
				cnt++;
			}
			if (mbtis[0][i] != mbtis[2][i]) {
				cnt++;
			}
		}
		if (cnt < minCnt) {
			minCnt = cnt;
		}
		return;
	}
	for (int i = 0; i < 16; i++) {
		if (mbtiCnt[i] > 0) {
			mbtis.push_back(mbti[i]);
			mbtiCnt[i]--;
			solve(mbtis);
			mbtiCnt[i]++;
			mbtis.pop_back();
		}
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	while (T--) {
		for (int i = 0; i < 16; i++) {
			mbtiCnt[i] = 0;
		}
		minCnt = 987654321;
		int N;
		cin >> N;
		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;
			for (int t = 0; t < 16; t++) {
				if (mbti[t] == s) {
					mbtiCnt[t]++;
					break;
				}
			}
		}
		solve(vector<string>());
		cout << minCnt << "\n";
	}
	return 0;
}