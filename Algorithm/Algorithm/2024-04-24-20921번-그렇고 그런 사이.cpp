#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;
int N, K;
bool used[4242 + 1];
int answer[4242];
int main() {
	cin >> N >> K;
	for (int idx = 0; idx < N; idx++) {
		int remainBlank = N - 1 - idx;
		int cnt = 0;
		int lastIdx = 0;
		for (int i = 1; i <= N; i++) {
			if (!used[i]) {
				lastIdx = i;
				cnt++;
				if (cnt > K || cnt > remainBlank) {
					break;
				}
			}
		}
		answer[idx] = lastIdx;
		used[lastIdx] = true;
		K -= cnt - 1;
	}
	for (int i = 0; i < N; i++) {
		cout << answer[i] << " ";
	}
	cout << "\n";
	return 0;
}