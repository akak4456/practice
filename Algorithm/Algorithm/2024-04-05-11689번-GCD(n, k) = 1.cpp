#include <iostream>
using namespace std;
long long int N;
const int MAX_PRIME = 1000001;
bool isPrime[MAX_PRIME];
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < MAX_PRIME; i++) {
		isPrime[i] = true;
	}
	long long int sol = N;
	for (long long int i = 2; i * i <= N; i++) {
		if (N % i == 0) {
			sol = sol / i * (i - 1);
		}
		while (N % i == 0) {
			N /= i;
		}
	}
	if (N != 1) {
		sol = sol / N * (N - 1);
	}
	cout << sol << endl;
	return 0;
}