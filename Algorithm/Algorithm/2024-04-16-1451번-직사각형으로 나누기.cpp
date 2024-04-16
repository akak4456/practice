#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N,M;
string str[50];
/*
* case1 :
* -
* -
* 와 같이 직사각형에 가로선을 2개 그은것
*/
unsigned long long int calcCase1() {
	unsigned long long int ret = 0;
	for (int garo1 = 1; garo1 <= N; garo1++) {
		for (int garo2 = garo1 + 1; garo2 <= N; garo2++) {
			unsigned long long int area1 = 0;
			for (int row = 0; row < garo1; row++) {
				for (int col = 0; col < M; col++) {
					area1 += str[row][col] - '0';
				}
			}
			unsigned long long int area2 = 0;
			for (int row = garo1; row < garo2; row++) {
				for (int col = 0; col < M; col++) {
					area2 += str[row][col] - '0';
				}
			}
			unsigned long long int area3 = 0;
			for (int row = garo2; row < N; row++) {
				for (int col = 0; col < M; col++) {
					area3 += str[row][col] - '0';
				}
			}
			ret = max(ret, area1 * area2 * area3);
		}
	}
	return ret;
}
/*
case2 :
| |
와 같이 직사각형에 세로선 2개 그은것
*/
unsigned long long int calcCase2() {
	unsigned long long int ret = 0;
	for (int sero1 = 1; sero1 <= M; sero1++) {
		for (int sero2 = sero1 + 1; sero2 <= M; sero2++) {
			unsigned long long int area1 = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < sero1; col++) {
					area1 += str[row][col] - '0';
				}
			}
			unsigned long long int area2 = 0;
			for (int row = 0; row < N; row++) {
				for (int col = sero1; col < sero2; col++) {
					area2 += str[row][col] - '0';
				}
			}
			unsigned long long int area3 = 0;
			for (int row = 0; row < N; row++) {
				for (int col = sero2; col < M; col++) {
					area3 += str[row][col] - '0';
				}
			}
			ret = max(ret, area1 * area2 * area3);
		}
	}
	return ret;
}
/*
case3:
 |
- -
와 같이 직사각형에 가로선 1개 세로선 1개 그 세로선은 가로선보다 위에 있는 것
*/
unsigned long long int calcCase3() {
	unsigned long long int ret = 0;
	for (int garo = 1; garo <= N; garo++) {
		for (int sero = 1; sero <= M; sero++) {
			unsigned long long int area1 = 0;
			for (int row = 0; row < garo; row++) {
				for (int col = 0; col < sero; col++) {
					area1 += str[row][col] - '0';
				}
			}
			unsigned long long int area2 = 0;
			for (int row = 0; row < garo; row++) {
				for (int col = sero; col < M; col++) {
					area2 += str[row][col] - '0';
				}
			}
			unsigned long long int area3 = 0;
			for (int row = garo; row < N; row++) {
				for (int col = 0; col < M; col++) {
					area3 += str[row][col] - '0';
				}
			}
			ret = max(ret, area1 * area2 * area3);
		}
	}
	return ret;
}
/*
case4:
- -
 |
와 같이 직사각형에 가로선 1개 세로선 1개 그 세로선은 가로선보다 아래에 있는 것
*/
unsigned long long int calcCase4() {
	unsigned long long int ret = 0;
	for (int garo = 1; garo <= N; garo++) {
		for (int sero = 1; sero <= M; sero++) {
			unsigned long long int area1 = 0;
			for (int row = 0; row < garo; row++) {
				for (int col = 0; col < M; col++) {
					area1 += str[row][col] - '0';
				}
			}
			unsigned long long int area2 = 0;
			for (int row = garo; row < N; row++) {
				for (int col = 0; col < sero; col++) {
					area2 += str[row][col] - '0';
				}
			}
			unsigned long long int area3 = 0;
			for (int row = garo; row < N; row++) {
				for (int col = sero; col < M; col++) {
					area3 += str[row][col] - '0';
				}
			}
			ret = max(ret, area1 * area2 * area3);
		}
	}
	return ret;
}
/*
case5:
| _
|
와 같이 직사각형에 가로선 1개 세로선 1개 그 가로선은 세로선보다 오른쪽에 있는 것
*/
unsigned long long int calcCase5() {
	unsigned long long int ret = 0;
	for (int garo = 1; garo <= N; garo++) {
		for (int sero = 1; sero <= M; sero++) {
			unsigned long long int area1 = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < sero; col++) {
					area1 += str[row][col] - '0';
				}
			}
			unsigned long long int area2 = 0;
			for (int row = 0; row < garo; row++) {
				for (int col = sero; col < M; col++) {
					area2 += str[row][col] - '0';
				}
			}
			unsigned long long int area3 = 0;
			for (int row = garo; row < N; row++) {
				for (int col = sero; col < M; col++) {
					area3 += str[row][col] - '0';
				}
			}
			ret = max(ret, area1 * area2 * area3);
		}
	}
	return ret;
}
/*
case6:
_| 
 |
와 같이 직사각형에 가로선 1개 세로선 1개 그 가로선은 세로선보다 왼쪽에 있는 것
*/
unsigned long long int calcCase6() {
	unsigned long long int ret = 0;
	for (int garo = 1; garo <= N; garo++) {
		for (int sero = 1; sero <= M; sero++) {
			unsigned long long int area1 = 0;
			for (int row = 0; row < garo; row++) {
				for (int col = 0; col < sero; col++) {
					area1 += str[row][col] - '0';
				}
			}
			unsigned long long int area2 = 0;
			for (int row = garo; row < N; row++) {
				for (int col = 0; col < sero; col++) {
					area2 += str[row][col] - '0';
				}
			}
			unsigned long long int area3 = 0;
			for (int row = 0; row < N; row++) {
				for (int col = sero; col < M; col++) {
					area3 += str[row][col] - '0';
				}
			}
			ret = max(ret, area1 * area2 * area3);
		}
	}
	return ret;
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> str[i];
	}
	unsigned long long int ret = calcCase1();
	ret = max(ret, calcCase2());
	ret = max(ret, calcCase3());
	ret = max(ret, calcCase4());
	ret = max(ret, calcCase5());
	ret = max(ret, calcCase6());
	cout << ret << "\n";
	return 0;
}