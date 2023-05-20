#include <iostream>
using namespace std;

int N;
int arr[2187][2187]; // 3^7 = 2187
int answer[3]; // answer[0] : -1 로만 채워진거, answer[0] : 0 로만 채워진거, answer[1] : 1 로만 채워진거
void solve(int matrixSize, int startRow, int startCol, int endRow, int endCol) {
	int cand[3] = { -1,0,1 };
	for (int t = 0; t < 3; t++) {
		bool isPossible = true;
		for (int i = startRow; i <= endRow; i++) {
			for (int j = startCol; j <= endCol; j++) {
				if (arr[i][j] != cand[t]) {
					isPossible = false;
					break;
				}
			}
			if (!isPossible) {
				break;
			}
		}
		if (isPossible) {
			answer[t]++;
			return;
		}
	}
	if (matrixSize > 1) {
		int unit = matrixSize / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= 3; j++) {
				solve(unit, startRow + unit * i, startCol + unit * (j - 1), startRow + unit * (i + 1) - 1, startCol + unit * j - 1);
			}
		}
	}
}
int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
	solve(N,0, 0, N - 1, N - 1);
	for (int i = 0; i < 3; i++) {
		cout << answer[i] << endl;
	}
}