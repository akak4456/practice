#include <iostream>
using namespace std;

int main() {
	string str;
	cin >> str;
	string sol = "";
	for (int i = 0; i < str.size(); i++) {
		if (str[i] >= 'a' && str[i] <= 'z') {
			sol += (str[i] - 'a' + 'A');
		}
		else {
			sol += (str[i] - 'A' + 'a');
		}
	}
	cout << sol << endl;
	return 0;
}