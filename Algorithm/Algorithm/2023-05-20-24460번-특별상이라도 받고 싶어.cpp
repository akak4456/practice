#include <iostream>
#include <algorithm>
using namespace std;

int N;
int arr[1024][1024];
int solve(int matrixSize, int startRow, int startCol) {
	int cand[4];
	if (matrixSize == 2) {
		cand[0] = arr[startRow][startCol];
		cand[1] = arr[startRow][startCol + 1];
		cand[2] = arr[startRow + 1][startCol];
		cand[3] = arr[startRow + 1][startCol + 1];
		sort(cand, cand + 4);
		return cand[1];
	}
	cand[0] = solve(matrixSize / 2, startRow, startCol);
	cand[1] = solve(matrixSize / 2, startRow, startCol + matrixSize / 2);
	cand[2] = solve(matrixSize / 2, startRow + matrixSize / 2, startCol);
	cand[3] = solve(matrixSize / 2, startRow + matrixSize / 2, startCol + matrixSize / 2);
	sort(cand, cand + 4);
	return cand[1];
}
 int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
	if (N == 1) {
		cout << arr[0][0] << endl;
	}
	else {
		cout << solve(N, 0, 0) << endl;
	}
	return 0;
}