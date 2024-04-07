#include <iostream>
#include <vector>
using namespace std;
int dx[8] = {0,-1,-1,-1,0,1,1,1};
int dy[8] = {-1,-1,0,1,1,1,0,-1};
int answer = 0;
void fishTurn(vector<vector<pair<int, int>>>& matrix) {
	bool visited[4][4];
	int visitedCount = 0;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (matrix[i][j].first == 0 || matrix[i][j].first == -1) {
				visitedCount++;
				visited[i][j] = true;
			}
			else {
				visited[i][j] = false;
			}
		}
	}
	while (visitedCount < 16) {
		int minNumber = 987654321;
		int minRow = -1;
		int minCol = -1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!visited[i][j]) {
					if (matrix[i][j].first < minNumber) {
						minNumber = matrix[i][j].first;
						minRow = i;
						minCol = j;
					}
				}
			}
		}
		visited[minRow][minCol] = true;
		int turnNumber = matrix[minRow][minCol].second;
		for (int turnCount = 0; turnCount < 8; turnCount++) {
			int nrow = minRow + dy[turnNumber];
			int ncol = minCol + dx[turnNumber];
			if (nrow < 0 || nrow >= 4 || ncol < 0 || ncol >= 4 || matrix[nrow][ncol].first == -1) {
				turnNumber = (turnNumber + 1) % 8;
				continue;
			}
			matrix[minRow][minCol].second = turnNumber;
			pair<int, int> tmp = matrix[minRow][minCol];
			matrix[minRow][minCol] = matrix[nrow][ncol];
			matrix[nrow][ncol] = tmp;

			visited[minRow][minCol] = visited[nrow][ncol];
			visited[nrow][ncol] = true;
			break;
		}
		visitedCount++;
	}
}
void solve(int curSum, vector<vector<pair<int,int>>> matrix) {
	if (answer < curSum) {
		answer = curSum;
	}
	fishTurn(matrix);
	//shark turn
	int sharkRow = -1;
	int sharkCol = -1;
	int sharkDirection = -1;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (matrix[i][j].first == -1) {
				sharkRow = i;
				sharkCol = j;
				sharkDirection = matrix[i][j].second;
			}
		}
	}
	for (int step = 1; step <= 3; step++) {
		int nrow = sharkRow + dy[sharkDirection] * step;
		int ncol = sharkCol + dx[sharkDirection] * step;
		if (nrow < 0 || nrow >= 4 || ncol < 0 || ncol >= 4) {
			break;
		}
		if (matrix[nrow][ncol].first == 0) {
			continue;
		}
		int fishNumber = matrix[nrow][ncol].first;
		int fishDirection = matrix[nrow][ncol].second;
		matrix[sharkRow][sharkCol] = { 0,0 };
		matrix[nrow][ncol] = { -1, fishDirection };
		solve(curSum + fishNumber, matrix);
		matrix[sharkRow][sharkCol] = { -1, sharkDirection };
		matrix[nrow][ncol] = { fishNumber, fishDirection };
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	vector<vector<pair<int, int>>> matrix = vector<vector<pair<int, int>>>(4, vector<pair<int, int>>(4, { 0,0 }));
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			int a, b;
			cin >> a >> b;
			matrix[i][j] = { a, b - 1 };
		}
	}
	int firstA = matrix[0][0].first;
	int firstB = matrix[0][0].second;
	matrix[0][0] = { -1,firstB };
	solve(firstA, matrix);
	cout << answer << "\n";
	return 0;
}