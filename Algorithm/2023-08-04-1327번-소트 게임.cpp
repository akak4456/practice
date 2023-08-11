#include <iostream>
#include <map>
#include <queue>
#include <algorithm>
using namespace std;

int N, K;
string origin;
map<string, bool> visited;

int bfs(string s) {
	string correct = s;
	sort(correct.begin(), correct.end());
	queue<pair<string, int>> Q;
	Q.push(make_pair(s, 0));
	visited[s] = true;

	while (!Q.empty()) {
		string now = Q.front().first;
		int cnt = Q.front().second;
		Q.pop();
		if (now == correct) return cnt;
		for (int i = 0; i <= N - K; ++i) {
			string temp = now.substr(i, K);
			reverse(temp.begin(), temp.end());
			string next = now.substr(0, i);
			next += temp;
			next += now.substr(i + K);
			// 중복체크
			if (!visited.count(next)) {
				Q.push(make_pair(next, cnt + 1));
				visited[next] = true;
			}
		}
	}
	return -1;
}
int minCnt = -1;
void dfs(string s, int cnt) {
	string correct = s;
	sort(correct.begin(), correct.end());
	visited[s] = true;
	if (correct == s) {
		if (minCnt == -1 || minCnt > cnt) {
			minCnt = cnt;
		}
		return;
	}

	for (int i = 0; i <= N - K; ++i) {
		string temp = s.substr(i, K);
		reverse(temp.begin(), temp.end());
		string next = s.substr(0, i);
		next += temp;
		next += s.substr(i + K);
		// 중복체크
		if (!visited.count(next)) {
			dfs(next, cnt + 1);
			visited[next] = true;
		}
	}
}

int main() {
	cin >> N >> K;
	for (int i = 0; i < N; ++i) {
		char a;
		cin >> a;
		origin += a;
	}

	// cout << bfs(origin) << '\n';

	dfs(origin, 0);
	cout << minCnt << endl;
	return 0;
}