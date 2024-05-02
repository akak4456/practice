#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
using namespace std;
int N, H;
vector<int> lowerH; // 홀수번째 장애물의 높이, 오름차순으로 정렬
vector<int> upperH; // 짝수번째 장애물의 높이,
queue<int> lowerHQ;
queue<int> upperHQ;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> H;
	for (int i = 0; i < N; i++) {
		int a;
		cin >> a;
		if (i % 2 == 1) {
			lowerH.push_back(a);
		}
		else {
			upperH.push_back(-a);
		}
	}
	sort(lowerH.begin(), lowerH.end());
	sort(upperH.begin(), upperH.end());
	for (int i = 0; i < lowerH.size(); i++) {
		lowerHQ.push(lowerH[i]);
	}
	for (int i = 0; i < upperH.size(); i++) {
		upperHQ.push(upperH[i]);
	}
	int minVal = 987654321;
	int minCnt = 0;
	for (int i = 1; i <= H; i++) {
		int cnt = 0;
		while (lowerHQ.size() > 0 && lowerHQ.front() < i) {
			lowerHQ.pop();
		}
		cnt += lowerHQ.size();
		while (upperHQ.size() > 0 && H + upperHQ.front() < i) {
			upperHQ.pop();
		}
		cnt += N / 2 - upperHQ.size();
		if (cnt < minVal) {
			minVal = cnt;
			minCnt = 1;
		}
		else if (cnt == minVal) {
			minCnt++;
		}
	}
	cout << minVal << " " << minCnt << "\n";
	return 0;
}