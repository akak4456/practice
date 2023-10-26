#include <iostream>
using namespace std;

int main() {
	string S;
	cin >> S;
	char lastCh = '2';
	int zeroGroup = 0;
	int oneGroup = 0;
	for (int i = 0; i < S.size(); i++) {
		if (S[i] != lastCh) {
			lastCh = S[i];
			if (S[i] == '0') {
				zeroGroup++;
			}
			else if (S[i] == '1') {
				oneGroup++;
			}
		}
	}
	if (zeroGroup < oneGroup) {
		cout << zeroGroup << endl;
	}
	else {
		cout << oneGroup << endl;
	}
	return 0;
}