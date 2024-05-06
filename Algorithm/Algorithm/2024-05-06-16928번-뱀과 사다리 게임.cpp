#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
int specialTo[100 + 1];
bool isVisited[100 + 1];
queue<pair<int, int>> q;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	for (int i = 1; i <= 100; i++) {
		specialTo[i] = -1;
	}
	int N, M;
	cin >> N >> M;
	for (int i = 0; i < N + M; i++) {
		int t1, t2;
		cin >> t1 >> t2;
		specialTo[t1] = t2;
	}
	q.push({ 1,0 });
	int minCnt = 987654321;
	while (!q.empty()) {
		int now = q.front().first;
		int rollCnt = q.front().second;
		q.pop();
		if (now >= 100) {
			if (rollCnt < minCnt) {
				minCnt = rollCnt;
			}
			continue;
		}
		if (isVisited[now]) continue;
		isVisited[now] = true;
		bool isSpecial[6] = { false,false,false,false,false,false };
		for (int i = 0; i < 6; i++) {
			if (specialTo[now + i + 1] != -1 && !isVisited[now + i + 1]) {
				q.push({ specialTo[now + i + 1], rollCnt + 1 });
				isSpecial[i] = true;
			}
		}
		for (int i = 5; i >= 0; i--) {
			if (!isSpecial[i] && !isVisited[now + i + 1]) {
				q.push({ now + i + 1, rollCnt + 1 });
				break;
			}
		}
	}
	cout << minCnt << "\n";
	return 0;
}