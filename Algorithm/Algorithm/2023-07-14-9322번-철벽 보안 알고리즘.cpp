#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main() {
	int T;
	cin >> T;
	while (T--) {
		int n;
		cin >> n;
		map<string, int> m;
		for (int i = 0; i < n; i++) {
			string t;
			cin >> t;
			m.insert(make_pair(t, i));
		}
		vector<int> v;
		for (int i = 0; i < n; i++) {
			string t;
			cin >> t;
			v.push_back(m.find(t)->second);
		}
		vector<string> s(n);
		for (int i = 0; i < n; i++) {
			string t;
			cin >> t;
			s[v[i]] = t;
		}
		for (int i = 0; i < s.size(); i++) {
			cout << s[i] << ' ';
		}
		cout << endl;
	}
}