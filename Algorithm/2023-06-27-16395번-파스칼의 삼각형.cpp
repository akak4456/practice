#include <iostream>
using namespace std;

int arr[30 + 1][30 + 1];
int main() {
	int n, k;
	cin >> n >> k;
	arr[1][1] = 1; // 1행 첫번째 수
	arr[2][1] = 1; // 2행 첫번째 수
	arr[2][2] = 1; // 1행 두번째 수
	for (int i = 3; i <= n; i++) {
		arr[i][1] = 1;
		arr[i][i] = 1;
		for (int j = 2; j <= i - 1; j++) {
			arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
		}
	}
	cout << arr[n][k] << endl;
}