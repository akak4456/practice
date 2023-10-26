#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main() {
	string S;
	cin >> S;
	vector<string> suffix;
	for (int i = 0; i < S.size(); i++) {
		suffix.push_back(S.substr(i));
	}
	sort(suffix.begin(), suffix.end());
	for (int i = 0; i < suffix.size(); i++) {
		cout << suffix[i] << endl;
	}
	return 0;
}