#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int R, C, N;
pair<char, int> board[200][200]; // first: ��ź����, �ƴϸ� �׳� ��ĭ����, second: ��ź ��ġ ����, ��ź�� �ƴ϶�� �ǹ̾��� ��
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
		// 2�ʺ��� ��ǻ� ��ȿ�� ��ȭ�� �Ͼ��
		if (t % 2 == 0) {
			// ��� ���� ��ź�� ��ġ�ؾ� �Ѵ�.
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
			// ��Ʈ������ ��ź�� ��Ʈ����.
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