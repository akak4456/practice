#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;

int arr[201];

int solution(int n) {
	if (n < 1) return 0;
	int ret = 0;
	int originArr = arr[n];
	for (int step = 1; step <= 6; step++) {
		bool isAbleToProceed = true;
		for (int i = n; i >= 1; i -= step) {
			if (arr[i] < originArr) {
				isAbleToProceed = false;
			}
			arr[i] -= originArr;
		}
		if (isAbleToProceed) {
			for (int i = 1; i <= n; i++) {
				cout << arr[i] << ' ';
			}
			cout << endl;
			int sol = originArr + solution(n - 1);
			if (ret == 0) {
				ret = sol;
			}
			else {
				ret = min(ret, sol);
			}
		}
		for (int i = n; i >= 1; i -= step) {
			arr[i] += originArr;
		}
	}
	return ret;
}

int main() {
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	cout << solution(N) << endl;
	return 0;
}