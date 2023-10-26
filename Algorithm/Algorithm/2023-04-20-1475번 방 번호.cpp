#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main() {
	int N;
	cin >> N;
	int cnt[10] = { 0,0,0,0,0,0,0,0,0,0 };
	int sol = 0;
	while (N) {
		int target = N % 10;
		if ((
			target != 6 && target != 9 &&
			cnt[target] == 0
			) || (
				(target == 6 || target == 9)
				&& cnt[6] == 0 && cnt[9] == 0
				)) {
			for (int i = 0; i < 10; i++) {
				cnt[i]++;
			}
			sol++;
		}
		if (target != 6 && target != 9) {
			cnt[target]--;
		}
		else {
			if (cnt[6] > 0) {
				cnt[6]--;
			}
			else {
				cnt[9]--;
			}
		}
		N /= 10;
	}
	cout << sol << endl;
	return 0;
}