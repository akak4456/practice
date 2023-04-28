#include <iostream>
#include <set>
using namespace std;

int main() {
	int N, M;
	cin >> N >> M;
	set<string> S;
	int sol = 0;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		S.insert(str);
	}
	for (int i = 0; i < M; i++) {
		string str;
		cin >> str;
		if (S.find(str) != S.end()) {
			sol++;
		}
	}
	cout << sol << endl;
	return 0;
}