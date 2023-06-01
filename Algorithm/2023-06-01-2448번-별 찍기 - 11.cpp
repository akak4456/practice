#include <iostream>
using namespace std;

bool isThereStar[4072][8144]; // (y,x) 임에 주의

void solve(int lineCount, int startX, int startY) {
	if (lineCount == 3) {
		isThereStar[startY][startX + 2] = true;

		isThereStar[startY + 1][startX + 1] = true;
		isThereStar[startY + 1][startX + 3] = true;
		for (int i = 0; i < 5; i++) {
			isThereStar[startY + 2][startX + i] = true;
		}
		return;
	}
	solve(lineCount / 2, startX + lineCount / 2, startY);
	solve(lineCount / 2, startX, startY + lineCount / 2);
	solve(lineCount / 2, startX + lineCount, startY + lineCount / 2);
}

int main() {
	int N;
	cin >> N;
	solve(N, 0, 0);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N * 2; j++) {
			if (isThereStar[i][j]) {
				cout << "*";
			}
			else {
				cout << " ";
			}
		}
		cout << "\n";
	}
	return 0;
}