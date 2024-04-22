#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cassert>
using namespace std;
int T, A, B;
int mod[1024];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> T;
	while (T--) {
		for (int i = 0; i < 1024; i++) {
			mod[i] = -1;
		}
		cin >> A >> B;
		vector<int> v;
		int idx = 1;
		while (true) {
			if (mod[A % B] != -1) {
				cout << v[0];
				cout << ".";
				for (int i = 1; i < mod[A % B]; i++) {
					cout << v[i];
				}
				cout << "(";
				for (int i = mod[A % B]; i < v.size(); i++) {
					cout << v[i];
				}
				cout << A / B;
				cout << ")\n";
				break;
			}
			mod[A % B] = idx;
			v.push_back(A / B);
			idx++;
			A = (A % B) * 10;
		}
	}
	return 0;
}