#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
using namespace std;
int N;
int M;
int dist[100 + 1][100 + 1];
const int INF = 987654321;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i == j) dist[i][j] = 0;
			else dist[i][j] = INF;
		}
	}
	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		dist[a][b] = 1;
		dist[b][a] = 1;
	}
	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (dist[i][k] + dist[k][j] < dist[i][j]) {
					dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
	}
	int minValue = INF;
	int minTarget = INF;
	for (int i = 1; i <= N; i++) {
		int sum = 0;
		for (int j = 1; j <= N; j++) {
			sum += dist[i][j];
		}
		if (sum < minValue) {
			minValue = sum;
			minTarget = i;
		}
	}
	cout << minTarget << "\n";
	return 0;
}