#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	while (true) {
		string str;
		cin >> str;
		if (str.compare("0") == 0) {
			break;
		}
		string reverseStr = str;
		reverse(reverseStr.begin(), reverseStr.end());
		if (str.compare(reverseStr) == 0) {
			cout << "yes" << endl;
		}
		else {
			cout << "no" << endl;
		}
	}
}