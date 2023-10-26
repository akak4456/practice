#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main() {
	int N;
	cin >> N;
	vector<int> plus;
	vector<int> minus;
	bool isThereZero = false;
	for (int i = 0; i < N; i++) {
		int input;
		cin >> input;
		if (input > 0) {
			plus.push_back(input);
		}
		else if (input == 0) {
			isThereZero = true;
		}
		else {
			minus.push_back(input);
		}
	}
	sort(plus.begin(), plus.end());
	sort(minus.begin(), minus.end());
	int sol = 0;
	for (int i = plus.size() - 1; i >= 0; i -= 2) {
		if (i >= 1) {
			if (plus[i] * plus[i - 1] > plus[i] + plus[i - 1]) {
				sol += plus[i] * plus[i - 1];
			}
			else {
				sol += plus[i] + plus[i - 1];
			}
		}
	}
	if (plus.size() % 2 == 1) {
		sol += plus[0];
	}
	for (int i = 0; i < minus.size(); i += 2) {
		if (i + 1 < minus.size()) {
			sol += minus[i] * minus[i + 1];
		}
	}
	if (minus.size() % 2 == 1 && !isThereZero) {
		sol += minus[minus.size() - 1];
	}
	cout << sol << endl;
	return 0;
}