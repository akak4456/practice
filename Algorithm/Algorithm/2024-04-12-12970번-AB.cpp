#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, K;
string ans = "";
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> K;
	for (int B = N; B >= 1; B--) {
		int A = K / B;
		if (A + B <= N && A * B == K) {
			for (int i = 0; i < N - (A + B); i++) {
				ans += "B";
			}
			for (int i = 0; i < A; i++) {
				ans += "A";
			}
			for (int i = 0; i < B; i++) {
				ans += "B";
			}
			break;
		}
		else {
			vector<pair<int, int>> abList;
			int remainN = N - (A + B);
			int remainK = K - A * B;
			while (true) {
				bool continued = false;
				for (int a = 1; a <= N; a++) {
					int b = remainK / a;
					if (a <= remainN && a * b <= remainK) {
						remainN -= a;
						remainK -= a * b;
						abList.push_back({ a, b });
						continued = true;
						break;
					}
				}
				if (!continued) {
					break;
				}
			}

			if (remainN >= 0 && remainK == 0) {
				for (int i = 0; i < A; i++) {
					ans += "A";
				}
				for (int i = 0; i < B; i++) {
					ans += "B";
				}
				for (int idx = 0; idx < abList.size(); idx++) {
					int lastIdx = ans.length() - 1;
					int curBCnt = 0;
					while (lastIdx > 0 && curBCnt < abList[idx].second) {
						if (ans[lastIdx] == 'B') {
							curBCnt++;
						}
						lastIdx--;
					}
					string tmp = "";
					for (int i = 0; i <= lastIdx; i++) {
						tmp += ans[i];
					}
					for (int i = 0; i < abList[idx].first; i++) {
						tmp += "A";
					}
					for (int i = lastIdx + 1; i < ans.length(); i++) {
						tmp += ans[i];
					}
					ans = tmp;
				}
				break;
			}
		}
	}
	if (ans == "") {
		cout << "-1\n";
	}
	else {
		int ansLen = ans.length();
		for (int i = 0; i < N - ansLen; i++) {
			ans = "B" + ans;
		}
		cout << ans << "\n";
	}
	return 0;
}