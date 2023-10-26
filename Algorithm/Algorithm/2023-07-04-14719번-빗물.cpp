#include <iostream>
using namespace std;

int H, W;
int arr[500];
int main() {
	cin >> H >> W;
	for (int i = 0; i < W; i++) {
		cin >> arr[i];
	}
	int startIdx = 0;
	while (startIdx + 1 < W && arr[startIdx] == 0) {
		startIdx++;
	}
	int solve = 0;
	while (startIdx < W) {
		int endIdx = startIdx + 1;
		int endHeight = arr[endIdx];
		for (int i = startIdx + 2; i < W; i++) {
			if (arr[i] > endHeight) {
				endIdx = i;
				endHeight = arr[i];
			}
			if (endHeight > arr[startIdx]) break;
		}
		int targetHeight = arr[startIdx];
		if (endIdx != -1 && arr[endIdx] < targetHeight) {
			targetHeight = arr[endIdx];
		}
		// cout << startIdx << ' ' << endIdx << ' ' << targetHeight << endl;
		for (int i = startIdx; i <= endIdx; i++) {
			if (targetHeight - arr[i] > 0) {
				solve += targetHeight - arr[i];
			}
		}
		startIdx = endIdx;
	}
	cout << solve << endl;
}