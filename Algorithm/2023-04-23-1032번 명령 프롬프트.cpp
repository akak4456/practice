#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int N;
	cin >> N;
	string str[50];
	for (int i = 0; i < N; i++) {
		cin >> str[i];
	}
	for (int i = 0; i < str[0].size(); i++) {
		bool isAllSame = true;
		for (int j = 1; j < N; j++) {
			if (str[0][i] != str[j][i]) {
				isAllSame = false;
				break;
			}
		}
		if (isAllSame) {
			cout << str[0][i];
		}
		else {
			cout << '?';
		}
	}
}