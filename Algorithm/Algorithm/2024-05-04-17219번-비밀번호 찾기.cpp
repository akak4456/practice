#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
int N;
int M;
map<string, string> m;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string a, b;
		cin >> a >> b;
		m.insert({ a,b });
	}
	for (int i = 0; i < M; i++) {
		string a;
		cin >> a;
		auto it = m.find(a);
		cout << it->second << "\n";
	}
	return 0;
}