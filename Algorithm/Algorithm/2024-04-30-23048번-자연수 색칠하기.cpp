#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
using namespace std;
int N;
int K;
int colors[500000 + 1];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		colors[i] = -1;
	}
	colors[1] = 1;
	K = 2;
	for (int i = 2; i <= N; i++) {
		if (colors[i] == -1) {
			int nextIdx = i;
			while (nextIdx <= N) {
				colors[nextIdx] = K;
				nextIdx += i;
			}
			K++;
		}
	}
	cout << K - 1 << "\n";
	for (int i = 1; i <= N; i++) {
		cout << colors[i] << " ";
	}
	cout << "\n";
	return 0;
}