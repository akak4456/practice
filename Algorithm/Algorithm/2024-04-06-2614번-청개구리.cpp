#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
int arr[200 + 1];
int minCnt = 987654321;
vector<int> minSol;
/*
https://www.acmicpc.net/board/view/32136
�� ����
���� ������ �����ؼ� �׽�Ʈ �غ�����

1�� ������

X1 = 1x+1�� ����

X2 = 2x+1�� ����

X3 = 2x+2 �� ����

...

X21 = 6x+6�� ����

X1 ~ X21�� ���� ã�� ����

backtracking���� 3depth (X1 ~ X3) ������ ���� 0 ���� 100 ���� min, max range�� ���µ� �� ���� ���ʹ� ����(?) ��������

8~9 depth ���� ���� ���������� ��� ���������󱸿�.. (���� input�� ���� �ٸ���������..)

0 ~ 100 ���� range�� ���� ������ 5�� �̻��� �Ǹ� TLE�� ���;� ���ٵ�,



������ �׷� input�� ���� �� �ϳ׿�.
idx getDistnace(idx) getStartPos(idx)
1 1 1
2 2 1
3 2 2
4 3 1
5 3 2
6 3 3
7 4 1
8 4 2
9 4 3
10 4 4
11 5 1
12 5 2
13 5 3
14 5 4
15 5 5
16 6 1
17 6 2
18 6 3
19 6 4
20 6 5
21 6 6
*/
int getDistance(int idx) {
	int i = idx;
	int ret = 1;
	while (i > ret) {
		i -= ret;
		ret++;
	}
	return ret;
}
int getStartPos(int idx) {
	int i = idx;
	int ret = 1;
	while (i > ret) {
		i -= ret;
		ret++;
	}
	return i;
}
void solve(int remainCnt,int idx, vector<vector<int>> matrix,vector<int> sol) {
	if (idx > 1) {
		for (int row = 1; row <= N; row++) {
			if (matrix[row][idx - 1] == 1) {
				matrix[row][idx - 1] = 0;
				matrix[row][22] -= sol[idx - 1];
			}
		}
		for (int row = 1; row <= N; row++) {
			if (matrix[row][22] == 0) {
				for (int col = 1; col <= 21; col++) {
					if (matrix[row][col] == 1) {
						sol[col] = 0;
						for (int row2 = 1; row2 <= N; row2++) {
							matrix[row2][col] = 0;
						}
					}
				}
			}
		}
		for (int row = 1; row <= N; row++) {
			int oneCount = 0;
			int oneIdx = -1;
			for (int col = 1; col <= 21; col++) {
				if (matrix[row][col] == 1) {
					oneCount++;
					oneIdx = col;
				}
			}
			if (oneCount == 1) {
				sol[oneIdx] = matrix[row][22];
				for (int row2 = 1; row2 <= N; row2++) {
					if (matrix[row2][oneIdx] == 1) {
						matrix[row2][oneIdx] = 0;
						matrix[row2][22] -= sol[oneIdx];
						if (matrix[row2][22] < 0) return;
					}
				}
			}
		}
		for (int row = 1; row <= N; row++) {
			int zeroCount = 0;
			for (int col = 1; col <= 21; col++) {
				if (matrix[row][col] == 0) {
					zeroCount++;
				}
			}
			if (zeroCount == 21 && matrix[row][22] != 0) {
				return;
			}
		}
	}
	if (remainCnt < 0) {
		return;
	}
	if (idx == 21 + 1) {
		int sum = 0;
		for (int i = 1; i <= 21; i++) {
			sum += sol[i];
		}
		for (int row = 1; row <= N; row++) {
			if (matrix[row][22] > 0) return;
		}
		if (minCnt > sum) {
			minCnt = sum;
			minSol = sol;
		}
		return;
	}
	if (sol[idx] >= 0) {
		solve(remainCnt, idx + 1, matrix, sol);
		return;
	}
	int upperBound = 100;
	for (int row = 1; row <= N; row++) {
		if (matrix[row][idx] == 1) {
			upperBound = min(upperBound, matrix[row][22]);
		}
	}
	int lowerBound = 0;
	for (int cnt = upperBound; cnt >= lowerBound; cnt--) {
		sol[idx] = cnt;
		solve(remainCnt - cnt, idx + 1, matrix, sol);
	}
}
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	vector<int> sol = vector<int>(21 + 1, -1);
	vector<vector<int>> matrix(200 + 1,vector<int>(22 + 1, 0));
	for (int i = 1; i <= 21; i++) {
		int startPos = getStartPos(i);
		int distance = getDistance(i);
		if (startPos + distance > N) {
			sol[i] = 0;
		}
		int pos = startPos;
		while (pos <= N) {
			if (sol[i] == -1) {
				matrix[pos][i] = 1;
			}
			pos += distance;
		}
	}
	for (int i = 1; i <= N; i++) {
		matrix[i][22] = arr[i];
	}
	solve(100,1, matrix, sol);
	int tmpArr[200 + 1];
	for (int i = 0; i <= 200; i++) {
		tmpArr[i] = 0;
	}
	for (int i = 1; i < minSol.size(); i++) {
		for (int cnt = 0; cnt < minSol[i]; cnt++) {
			int pos = getStartPos(i);
			while (pos <= N) {
				tmpArr[pos]++;
				pos += getDistance(i);
			}
		}
	}

	cout << minCnt << endl;
	for (int i = 1; i < minSol.size(); i++) {
		for (int cnt = 0; cnt < minSol[i]; cnt++) {
			cout << getStartPos(i) << " " << getDistance(i) << endl;
		}
	}
	return 0;
}