#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	string str;
	cin >> str;
	string reverseStr = str;
	reverse(reverseStr.begin(), reverseStr.end());
	if (str.compare(reverseStr) == 0) {
		cout << "1" << endl;
	}
	else {
		cout << "0" << endl;
	}
}