#include <iostream>
using namespace std;
int N;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	int sol = 0;
	for (int i = 3; N - i > 0; i += 2) {
		sol += (N - i) * (N - i);
	}
	sol *= N;
	sol /= 4;
	cout << sol << endl;
	return 0;
}