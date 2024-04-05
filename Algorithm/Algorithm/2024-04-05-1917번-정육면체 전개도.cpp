#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;
int board1[6][6];
int board2[6][6];
int board3[6][6];
vector<vector<pair<int, int>>> possible;
bool isCan(int board[6][6]) {
	for (int row = 0; row < 6; row++) {
		for (int col = 0; col < 6; col++) {
			if (board[row][col] == 1) {
				for (int i = 0; i < possible.size(); i++) {
					bool isPossible = true;
					for (int j = 0; j < 5; j++) {
						int nrow = row + possible[i][j].first;
						int ncol = col + possible[i][j].second;
						if (nrow < 0 || nrow >= 6 || ncol < 0 || ncol >= 6 || board[nrow][ncol] != 1) {
							isPossible = false;
							break;
						}
					}
					if (isPossible) {
						return true;
					}
				}
				return false;
			}
		}
	}
	return false;
}
void rotate(int board[6][6]) {
	int tmpBoard[6][6];
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			tmpBoard[j][6 - 1 - i] = board[i][j];
		}
	}
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			board[i][j] = tmpBoard[i][j];
		}
	}
}
void flip(int board[6][6]) {
	int tmpBoard[6][6];
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			tmpBoard[i][j] = board[5 - i][j];
		}
	}
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			board[i][j] = tmpBoard[i][j];
		}
	}
}
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			cin >> board1[i][j];
		}
	}
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			cin >> board2[i][j];
		}
	}
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
			cin >> board3[i][j];
		}
	}
	possible.push_back(
		{ {1,0},{1,1},{1,2},{1,3},{2,0} }
	);
	possible.push_back(
		{ {1,0},{1,1},{1,2},{1,3},{2,1} }
	);
	possible.push_back(
		{ {1,0},{1,1},{1,2},{1,3},{2,2} }
	);
	possible.push_back(
		{ {1,0},{1,1},{1,2},{1,3},{2,3} }
	);
	possible.push_back(
		{ {1,-1},{1,0},{1,1},{1,2},{2,0} }
	);
	possible.push_back(
		{ {1,-1},{1,0},{1,1},{1,2},{2,1} }
	);
	possible.push_back(
		{ {0,1},{1,1},{1,2},{1,3},{2,1} }
	);
	possible.push_back(
		{ {0,1},{1,-2},{1,-1},{1,0},{2,-2} }
	);
	possible.push_back(
		{ {0,1},{1,1},{1,2},{2,2},{2,3} }
	);
	possible.push_back(
		{ {0,1},{1,1},{1,2},{1,3},{2,2} }
	);
	possible.push_back(
		{ {0,1},{0,2},{1,2},{1,3},{1,4} }
	);
	bool canBoard1 = false;
	for (int i = 0; i < 4; i++) {
		canBoard1 |= isCan(board1);
		rotate(board1);
	}
	flip(board1);
	for (int i = 0; i < 4; i++) {
		canBoard1 |= isCan(board1);
		rotate(board1);
	}
	if (canBoard1) {
		cout << "yes\n";
	}
	else {
		cout << "no\n";
	}
	bool canBoard2 = false;
	for (int i = 0; i < 4; i++) {
		canBoard2 |= isCan(board2);
		rotate(board2);
 	}
	flip(board2);
	for (int i = 0; i < 4; i++) {
		canBoard2 |= isCan(board2);
		rotate(board2);
	}
	if (canBoard2) {
		cout << "yes\n";
	}
	else {
		cout << "no\n";
	}
	bool canBoard3 = false;
	for (int i = 0; i < 4; i++) {
		canBoard3 |= isCan(board3);
		rotate(board3);
	}
	flip(board3);
	for (int i = 0; i < 4; i++) {
		canBoard3 |= isCan(board3);
		rotate(board3);
	}
	if (canBoard3) {
		cout << "yes\n";
	}
	else {
		cout << "no\n";
	}

	return 0;
}