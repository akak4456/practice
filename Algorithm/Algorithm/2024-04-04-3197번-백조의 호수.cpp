#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;
int R, C;
char board[1500][1500];
bool visited[1500][1500];
queue<pair<int, int>> swanQ;
queue<pair<int, int>> waterQ;
queue<pair<int, int>> tmpSwanQ;
queue<pair<int, int>> tmpWaterQ;
int dr[4] = {1,0,0,-1};
int dc[4] = { 0,1,-1,0 };
bool swanBFS() {
	while (!swanQ.empty()) {
		pair<int, int> pos = swanQ.front();
		swanQ.pop();
	
		for (int i = 0; i < 4; i++) {
			int nr = pos.first + dr[i];
			int nc = pos.second + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
			visited[nr][nc] = true;

			if (board[nr][nc] == 'X') {
				tmpSwanQ.push({ nr, nc });
			}
			else if (board[nr][nc] == '.') {
				swanQ.push({ nr, nc });
			}
			else if (board[nr][nc] == 'L') {
				return true;
			}
		}
	}
	return false;
}
void waterBFS() {
	while (!waterQ.empty()) {
		pair<int, int> pos = waterQ.front();
		waterQ.pop();

		for (int i = 0; i < 4; i++) {
			int nr = pos.first + dr[i];
			int nc = pos.second + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

			if (board[nr][nc] == 'X') {
				board[nr][nc] = '.';
				tmpWaterQ.push({ nr,nc });
			}
		}
	}
}
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> R >> C;
	int swanR;
	int swanC;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> board[i][j];
			if (board[i][j] != 'X') {
				waterQ.push({ i, j });
			}
			if (board[i][j] == 'L') {
				swanR = i;
				swanC = j;
			}
		}
	}
	swanQ.push({ swanR, swanC });
	visited[swanR][swanC] = true;
	int day = 0;
	while (!swanBFS()) {
		waterBFS();

		swanQ = tmpSwanQ;
		waterQ = tmpWaterQ;

		tmpSwanQ = queue<pair<int, int>>();
		tmpWaterQ = queue<pair<int, int>>();
		day++;
	}
	cout << day;
	return 0;
}