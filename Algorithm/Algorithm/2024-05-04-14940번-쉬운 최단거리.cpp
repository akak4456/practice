#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
using namespace std;
int n, m;
int board[1000][1000];
int answer[1000][1000];
int dcol[4] = { 0,0,-1,1 };
int drow[4] = { -1,1,0,0 };
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> m;
	queue<pair<int, pair<int, int>>> q;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
			answer[i][j] = -1;
			if (board[i][j] == 2) {
				q.push({ 0,{i,j} });
			}
		}
	}
	while (!q.empty()) {
		int dist = q.front().first;
		int row = q.front().second.first;
		int col = q.front().second.second;
		q.pop();
		if (answer[row][col] != -1) {
			continue;
		}
		answer[row][col] = dist;
		for (int i = 0; i < 4; i++) {
			int nrow = row + drow[i];
			int ncol = col + dcol[i];
			if (nrow < 0 || nrow >= n || ncol < 0 || ncol >= m || board[nrow][ncol] == 0 || answer[nrow][ncol] != -1)
				continue;
			q.push({ dist + 1, {nrow, ncol} });
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (answer[i][j] == -1 && board[i][j] == 0) {
				cout << "0 ";
			}
			else {
				cout << answer[i][j] << " ";
			}
		}
		cout << "\n";
	}
	return 0;
}