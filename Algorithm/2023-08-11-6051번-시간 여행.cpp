#include <iostream>
#include <vector>
#include <string>
using namespace std;

int N;
vector<pair<char, int>> querySt; // 쿼리 실행문 자체를 저장함
vector<int> cur;

void executeOldQuery(int lastPosition) {
	int startPos = 1;
	for (int i = lastPosition; i >= 1; i--) {
		if (querySt[i].first == 't') {
			startPos = i + 1;
			executeOldQuery(querySt[i].second - 1);
			break;
		}
	}
	for (int i = startPos; i <= lastPosition; i++) {
		if (querySt[i].first == 'a') {
			cur.push_back(querySt[i].second);
		}
		else {
			cur.pop_back();
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	querySt.push_back(make_pair('n', 0));
	while (N--) {
		char ch;
		cin >> ch;
		if (ch == 'a') {
			int t;
			cin >> t;
			querySt.push_back(make_pair('a', t));

			cur.push_back(t);
		}
		else if (ch == 's') {
			querySt.push_back(make_pair('s',0));

			cur.pop_back();
		}
		else if (ch == 't') {
			int t;
			cin >> t;
			querySt.push_back(make_pair('t', t));

			cur.clear();
			executeOldQuery(t - 1);
		}
		if (cur.size() == 0) {
			cout << -1 << '\n';
		}
		else {
			cout << cur[cur.size() - 1] << '\n';
		}
	}
	// for (int i = 0; i < ans.size(); i++) {
		// cout << ans[i] << '\n';
	// }
	return 0;
}