#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
using namespace std;
string input;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> input;
	bool xIncluded = false;
	for (int i = 0; i < input.size(); i++) {
		if (input[i] == 'x') {
			xIncluded = true;
			break;
		}
	}
	if (xIncluded) {
		if (input[input.size() - 1] == 'x') {
			//일차항만 있는 경우
			int a = stoi(input.substr(0, input.size() - 1));
			if (a / 2 == 1) {
				cout << "xx+W\n";
			}
			else if (a / 2 == -1) {
				cout << "-xx+W\n";
			}
			else {
				cout << a / 2 << "xx+W\n";
			}
		}
		else {
			int xIndex = -1;
			for (int i = 0; i < input.size(); i++) {
				if (input[i] == 'x') {
					xIndex = i;
					break;
				}
			}
			int a = stoi(input.substr(0, xIndex));
			int b = stoi(input.substr(xIndex + 1, input.size() - xIndex - 1));
			if (a / 2 == 1) {
				cout << "xx";
			}
			else if (a / 2 == -1) {
				cout << "-xx";
			}
			else {
				cout << a / 2 << "xx";
			}
			if (b == 1) {
				cout << "+x+W\n";
			}
			else if (b == -1) {
				cout << "-x+W\n";
			}
			else if (b > 0) {
				cout << "+" << b << "x+W\n";
			}
			else {
				cout << b << "x+W\n";
			}
		}
	}
	else {
		//상수항만 있는 경우
		if (input == "0") {
			cout << "W\n";
		}
		else {
			if (input == "1") {
				cout << "x+W\n";
			}
			else if (input == "-1") {
				cout << "-x+W\n";
			}
			else {
				cout << input << "x+W\n";
			}
		}
	}
	return 0;
}