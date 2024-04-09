#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
int arr[1000];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	sort(arr, arr + N);
	int ans = 1;
	for (int i = 0; i < N; i++) {
		if (ans < arr[i]) {
			break;
		}
		ans += arr[i];
	}
	cout << ans;
	return 0;
}