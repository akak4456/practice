#include <iostream>
using namespace std;

int arr[26] = { 3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1 };
int solve[4000];
int main() {
	string A;
	string B;
	cin >> A >> B;
	for (int i = 0; i < A.size(); i++) {
		// A 와 B 의 size 는 같다
		solve[i * 2] = arr[A[i] - 'A'];
		solve[i * 2 + 1] = arr[B[i] - 'A'];
	}

	int cnt = A.size() * 2;
	while (cnt > 2) {
		for (int i = 0; i < cnt; i++) {
			solve[i] = (solve[i] + solve[i + 1]) % 10;
		}
		cnt--;
	}
	cout << solve[0] << solve[1] << endl;
}