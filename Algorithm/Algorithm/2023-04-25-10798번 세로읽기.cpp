#include <iostream>
using namespace std;

int main() {
	string str[5];
	for (int i = 0; i < 5; i++) {
		cin >> str[i];
	}
	while (str[0].size() > 0 || str[1].size() > 0 || str[2].size() > 0 || str[3].size() > 0 || str[4].size() > 0) {
		for (int i = 0; i < 5; i++) {
			if (str[i].size() > 0) {
				cout << str[i][0];
				str[i] = str[i].substr(1);
			}
		}
	}
	cout << endl;
	return 0;
}