#include <iostream>
#include <algorithm>
using namespace std;
bool isPossible = false;
void solve(string S, string T) {
	if (isPossible) return;
	if (S.size() == T.size()) {
		if (S == T) {
			isPossible = true;
		}
		return;
	}
	if (T[T.size() - 1] == 'A') {
		string newT = string(T);
		newT.erase(newT.end() - 1);
		solve(S, newT);
	}
	else {
		string newT = string(T);
		newT.erase(newT.end() - 1);
		reverse(newT.begin(), newT.end());
		solve(S, newT);
	}
}
int main() {
	string S, T;
	cin >> S >> T;
	solve(S, T);
	if (isPossible) {
		cout << "1\n";
	}
	else {
		cout << "0\n";
	}
	return 0;
}