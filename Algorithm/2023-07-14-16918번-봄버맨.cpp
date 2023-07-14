#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int R, C, N;
pair<char, int> board[200][200]; // first: 폭탄인지, 아니면 그냥 빈칸인지, second: 폭탄 설치 시점, 폭탄이 아니라면 의미없는 값
int main() {
	cin >> R >> C >> N;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			char tmp;
			cin >> tmp;
			board[i][j] = make_pair(tmp, 0);
		}
	}
	for (int t = 2; t <= N; t++) {
		// 2초부터 사실상 유효한 변화가 일어난다
		if (t % 2 == 0) {
			// 모든 곳에 폭탄을 설치해야 한다.
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (board[i][j].first == '.') {
						board[i][j].first = 'O';
						board[i][j].second = t;
					}
				}
			}
		}
		else {
			// 터트릴만한 폭탄을 터트린다.
			vector<pair<int, int>> cand;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (board[i][j].first == 'O' && board[i][j].second + 3 == t) {
						cand.push_back(make_pair(i, j));
					}
				}
			}
			for (int i = 0; i < cand.size(); i++) {
				int row = cand[i].first;
				int col = cand[i].second;
				board[row][col].first = '.';
				board[max(row - 1, 0)][col].first = '.';
				board[row][max(col - 1, 0)].first = '.';
				board[row][min(col + 1, C - 1)].first = '.';
				board[min(row + 1, R - 1)][col].first = '.';
			}
		}
	}
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cout << board[i][j].first;
		}
		cout << endl;
	}
}