#include <iostream>
using namespace std;

int main() {
	int sol = 0;
	for (int i = 0; i < 8; i++) {
		string str;
		cin >> str;
		int startPos = 0;
		if (i % 2 == 1) {
			startPos = 1;
		}
		for (int j = startPos; j < 8; j += 2) {
			if (str[j] == 'F') {
				sol++;
			}
		}
	}
	cout << sol << endl;
	return 0;
}