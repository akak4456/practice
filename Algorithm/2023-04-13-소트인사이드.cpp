#include <iostream>
using namespace std;

int main() {
	int N;
	cin >> N;
	int arr[10];
	fill_n(arr, 10, 0);
	while (N > 0) {
		arr[N % 10]++;
		N /= 10;
	}
	string sol;
	for (int i = 9; i >= 0; i--) {
		// ASCII Code 로 0은 48 1 은 49 이다.
		for (int j = 0; j < arr[i]; j++) {
			sol += (char)(i + 48);
		}
	}
	cout << sol;
	return 0;
}