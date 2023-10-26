#include <iostream>
#include <vector>
using namespace std;

vector<char> arr;

int main() {
	string s;
	cin >> s;
	for (int i = 0; i < s.size(); i++) {
		arr.push_back(s[i]);
		if (arr.size() >= 4) {
			int lastIndexOfArr = arr.size() - 1;
			if (arr[lastIndexOfArr - 3] == 'P' && arr[lastIndexOfArr - 2] == 'P' && arr[lastIndexOfArr - 1] == 'A' && arr[lastIndexOfArr] == 'P') {
				arr.pop_back();
				arr.pop_back();
				arr.pop_back();
				arr.pop_back();
				arr.push_back('P');
			}
		}
	}
	if (arr.size() == 1 && arr[0] == 'P') {
		cout << "PPAP" << endl;
	}
	else {
		cout << "NP" << endl;
	}
}