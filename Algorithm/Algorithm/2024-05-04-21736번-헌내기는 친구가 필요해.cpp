#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
int N, M;
char board[600][600];
int dcol[4] = { 1,0,-1,0 };
int drow[4] = { 0,1,0,-1 };
int cnt = 0;
void dfs(int row, int col) {
	if (board[row][col] == 'V') {
		return;
	}
	if (board[row][col] == 'P') {
		cnt++;
	}
	board[row][col] = 'V';
	for (int i = 0; i < 4; i++) {
		int nrow = row + drow[i];
		int ncol = col + dcol[i];
		if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= M || (board[nrow][ncol] != 'O' && board[nrow][ncol] != 'P')) continue;
		dfs(nrow, ncol);
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (board[i][j] == 'I') {
				dfs(i, j);
			}
		}
	}
	if (cnt == 0) {
		cout << "TT\n";
	}
	else {
		cout << cnt << "\n";
	}
	return 0;
}