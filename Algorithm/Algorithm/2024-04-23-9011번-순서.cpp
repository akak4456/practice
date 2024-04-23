#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;
int T, n;
int arr[100];
bool used[100 + 1];
int answer[100];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> T;
	while (T--) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> arr[i];
			used[i + 1] = false;
		}
		bool isPossible = true;
		for (int i = n - 1; i >= 0; i--) {
			bool isAnswered = false;
			int idx = arr[i] + 1;
			while (idx <= n) {
				if (!used[idx]) {
					int cnt = 0;
					for (int j = 1; j <= n; j++) {
						if (!used[j] && j < idx) {
							cnt++;
						}
					}
					if (cnt == arr[i]) {
						used[idx] = true;
						isAnswered = true;
						answer[i] = idx;
						break;
					}
				}
				idx++;
			}
			if (!isAnswered) {
				isPossible = false;
				break;
			}
		}
		if (!isPossible) {
			cout << "IMPOSSIBLE\n";
		}
		else {
			for (int i = 0; i < n; i++) {
				cout << answer[i] << " ";
			}
			cout << "\n";
		}
	}
	return 0;
}