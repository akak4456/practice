#include <iostream>
using namespace std;

/*
���� ���� ��ǥ�� (x,y)=(0,0) �̶� �ϰ�
���������� �� ���� x�� �����ϰ� �Ʒ������� �� ���� y �� �����ϴ� ������ �����Ѵ�.
*/
/*
isBarrierHere[x][y] �� true �̸� (x,y) �� ��ֹ��� ������ �ǹ��Ѵ�.
*/
bool isBarrierHere[10][10];
/*
first �� x, second �� y �� �ǹ��Ѵ�.
*/
pair<int, int> holePos;

int N, M;

int minCnt = -1;
void calculateMinCnt(int curCnt) {
	if (minCnt == -1 || curCnt < minCnt) {
		minCnt = curCnt;
	}
}

void simulate(int curCnt, pair<int, int> curRedPos, pair<int, int> curBluePos) {
	if (curCnt >= 10) return;
	int redX = curRedPos.first;
	int redY = curRedPos.second;
	int blueX = curBluePos.first;
	int blueY = curBluePos.second;

	// ���� ��￩.
	int newRedX = redX;
	int newRedY = redY;
	bool isRedInHole = false;

	int newBlueX = blueX;
	int newBlueY = blueY;
	bool isBlueInHole = false;

	if (redX == blueX) {
		if (redY < blueY) {
			while (newRedY > 0) {
				if (isBarrierHere[redX][newRedY - 1]) {
					break;
				}
				if (make_pair(redX, newRedY - 1) == holePos) {
					isRedInHole = true;
					newRedY--;
					break;
				}
				newRedY--;
			}
			while (newBlueY > 0) {
				if (isBarrierHere[blueX][newBlueY - 1]) {
					break;
				}
				if (make_pair(blueX, newBlueY - 1) == holePos) {
					isBlueInHole = true;
					newBlueY--;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newBlueY - 1 == newRedY) {
					break;
				}
				newBlueY--;
			}
		}
		else if (redY > blueY) {
			while (newBlueY > 0) {
				if (isBarrierHere[blueX][newBlueY - 1]) {
					break;
				}
				if (make_pair(blueX, newBlueY - 1) == holePos) {
					isBlueInHole = true;
					newBlueY--;
					break;
				}
				newBlueY--;
			}
			while (newRedY > 0) {
				if (isBarrierHere[redX][newRedY - 1]) {
					break;
				}
				if (make_pair(redX, newRedY - 1) == holePos) {
					isRedInHole = true;
					newRedY--;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newRedY - 1 == newBlueY) {
					break;
				}
				newRedY--;
			}
		}
	}
	else {
		while (newBlueY > 0) {
			if (isBarrierHere[blueX][newBlueY - 1]) {
				break;
			}
			if (make_pair(blueX, newBlueY - 1) == holePos) {
				isBlueInHole = true;
				newBlueY--;
				break;
			}
			newBlueY--;
		}
		while (newRedY > 0) {
			if (isBarrierHere[redX][newRedY - 1]) {
				break;
			}
			if (make_pair(redX, newRedY - 1) == holePos) {
				isRedInHole = true;
				newRedY--;
				break;
			}
			newRedY--;
		}
	}
	if(!isBlueInHole) {
		if (isRedInHole) {
			calculateMinCnt(curCnt + 1);
			return;
		}
		simulate(curCnt + 1, make_pair(newRedX, newRedY), make_pair(newBlueX, newBlueY));
	}
	// �Ʒ��� ��￩.
	newRedX = redX;
	newRedY = redY;
	isRedInHole = false;

	newBlueX = blueX;
	newBlueY = blueY;
	isBlueInHole = false;

	if (redX == blueX) {
		if (redY < blueY) {
			while (newBlueY < N) {
				if (isBarrierHere[blueX][newBlueY + 1]) {
					break;
				}
				if (make_pair(blueX, newBlueY + 1) == holePos) {
					isBlueInHole = true;
					newBlueY++;
					break;
				}
				newBlueY++;
			}
			while (newRedY < N) {
				if (isBarrierHere[redX][newRedY + 1]) {
					break;
				}
				if (make_pair(redX, newRedY + 1) == holePos) {
					isRedInHole = true;
					newRedY++;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newRedY + 1 == newBlueY) {
					break;
				}
				newRedY++;
			}
		}
		else if (redY > blueY) {
			while (newRedY < N) {
				if (isBarrierHere[redX][newRedY + 1]) {
					break;
				}
				if (make_pair(redX, newRedY + 1) == holePos) {
					isRedInHole = true;
					newRedY++;
					break;
				}
				newRedY++;
			}
			while (newBlueY < N) {
				if (isBarrierHere[blueX][newBlueY + 1]) {
					break;
				}
				if (make_pair(blueX, newBlueY + 1) == holePos) {
					isBlueInHole = true;
					newBlueY++;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newBlueY + 1 == newRedY) {
					break;
				}
				newBlueY++;
			}
		}
	}
	else {
		while (newBlueY < N) {
			if (isBarrierHere[blueX][newBlueY + 1]) {
				break;
			}
			if (make_pair(blueX, newBlueY + 1) == holePos) {
				isBlueInHole = true;
				newBlueY++;
				break;
			}
			newBlueY++;
		}
		while (newRedY < N) {
			if (isBarrierHere[redX][newRedY + 1]) {
				break;
			}
			if (make_pair(redX, newRedY + 1) == holePos) {
				isRedInHole = true;
				newRedY++;
				break;
			}
			newRedY++;
		}
	}
	if (!isBlueInHole) {
		if (isRedInHole) {
			calculateMinCnt(curCnt + 1);
			return;
		}
		simulate(curCnt + 1, make_pair(newRedX, newRedY), make_pair(newBlueX, newBlueY));
	}
	// �������� ��￩.
	newRedX = redX;
	newRedY = redY;
	isRedInHole = false;

	newBlueX = blueX;
	newBlueY = blueY;
	isBlueInHole = false;
	if (redY == blueY) {
		if (redX < blueX) {
			while (newRedX > 0) {
				if (isBarrierHere[newRedX - 1][redY]) {
					break;
				}
				if (make_pair(newRedX - 1, redY) == holePos) {
					isRedInHole = true;
					newRedX--;
					break;
				}
				newRedX--;
			}
			while (newBlueX > 0) {
				if (isBarrierHere[newBlueX - 1][blueY]) {
					break;
				}
				if (make_pair(newBlueX - 1, blueY) == holePos) {
					isBlueInHole = true;
					newBlueX--;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newBlueX - 1 == newRedX) {
					break;
				}
				newBlueX--;
			}
		}
		else if (redX > blueX) {
			while (newBlueX > 0) {
				if (isBarrierHere[newBlueX - 1][blueY]) {
					break;
				}
				if (make_pair(newBlueX-1, blueY) == holePos) {
					isBlueInHole = true;
					newBlueX--;
					break;
				}
				newBlueX--;
			}
			while (newRedX > 0) {
				if (isBarrierHere[newRedX - 1][redY]) {
					break;
				}
				if (make_pair(newRedX - 1, redY) == holePos) {
					isRedInHole = true;
					newRedX--;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newRedX - 1 == newBlueX) {
					break;
				}
				newRedX--;
			}
		}
	}
	else {
		while (newBlueX > 0) {
			if (isBarrierHere[newBlueX - 1][blueY]) {
				break;
			}
			if (make_pair(newBlueX - 1, blueY) == holePos) {
				isBlueInHole = true;
				newBlueX--;
				break;
			}
			newBlueX--;
		}
		while (newRedX > 0) {
			if (isBarrierHere[newRedX - 1][redY]) {
				break;
			}
			if (make_pair(newRedX - 1, redY) == holePos) {
				isRedInHole = true;
				newRedX--;
				break;
			}
			newRedX--;
		}
	}
	if (!isBlueInHole) {
		if (isRedInHole) {
			calculateMinCnt(curCnt + 1);
			return;
		}
		simulate(curCnt + 1, make_pair(newRedX, newRedY), make_pair(newBlueX, newBlueY));
	}
	// ���������� ��￩.
	newRedX = redX;
	newRedY = redY;
	isRedInHole = false;

	newBlueX = blueX;
	newBlueY = blueY;
	isBlueInHole = false;
	if (redY == blueY) {
		if (redX < blueX) {
			while (newBlueX < M) {
				if (isBarrierHere[newBlueX + 1][blueY]) {
					break;
				}
				if (make_pair(newBlueX + 1, blueY) == holePos) {
					isBlueInHole = true;
					newBlueX++;
					break;
				}
				newBlueX++;
			}
			while (newRedX < M) {
				if (isBarrierHere[newRedX + 1][redY]) {
					break;
				}
				if (make_pair(newRedX + 1, redY) == holePos) {
					isRedInHole = true;
					newRedX++;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newRedX + 1 == newBlueX) {
					break;
				}
				newRedX++;
			}
		}
		else if (redX > blueX) {
			while (newRedX < M) {
				if (isBarrierHere[newRedX + 1][redY]) {
					break;
				}
				if (make_pair(newRedX + 1, redY) == holePos) {
					isRedInHole = true;
					newRedX++;
					break;
				}
				newRedX++;
			}
			while (newBlueX < M) {
				if (isBarrierHere[newBlueX + 1][blueY]) {
					break;
				}
				if (make_pair(newBlueX + 1, blueY) == holePos) {
					isBlueInHole = true;
					newBlueX++;
					break;
				}
				// �߰��ؾ߸� ��.
				if (newBlueX + 1 == newRedX) {
					break;
				}
				newBlueX++;
			}
		}
	}
	else {
		while (newBlueX < M) {
			if (isBarrierHere[newBlueX + 1][blueY]) {
				break;
			}
			if (make_pair(newBlueX + 1, blueY) == holePos) {
				isBlueInHole = true;
				newBlueX++;
				break;
			}
			newBlueX++;
		}
		while (newRedX < M) {
			if (isBarrierHere[newRedX + 1][redY]) {
				break;
			}
			if (make_pair(newRedX + 1, redY) == holePos) {
				isRedInHole = true;
				newRedX++;
				break;
			}
			newRedX++;
		}
	}
	if (!isBlueInHole) {
		if (isRedInHole) {
			calculateMinCnt(curCnt + 1);
			return;
		}
		simulate(curCnt + 1, make_pair(newRedX, newRedY), make_pair(newBlueX, newBlueY));
	}
}
int main() {
	cin >> N >> M;
	pair<int, int> redPos;
	pair<int, int> bluePos;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < M; j++) {
			if (str[j] == '#') {
				isBarrierHere[j][i] = true;
			}
			if (str[j] == 'O') {
				holePos = make_pair(j, i);
			}
			if (str[j] == 'R') {
				redPos = make_pair(j, i);
			}
			if (str[j] == 'B') {
				bluePos = make_pair(j, i);
			}
		}
	}
	simulate(0, redPos, bluePos);
	cout << minCnt << endl;
}