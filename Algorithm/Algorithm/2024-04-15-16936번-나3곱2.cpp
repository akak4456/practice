#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
unsigned long long int arr[100];
int dCnt[100];
vector<unsigned long long int> ans;
void solve(vector<unsigned long long int> nums) {
	if (nums.size() == N) {
		ans = nums;
		return;
	}
	if (!ans.empty()) {
		return;
	}
	for (int i = 0; i < N; i++) {
		unsigned long long int target = nums[nums.size() - 1];
		if (arr[i] == target * 2 || (target % 3 == 0 && arr[i] == target / 3)) {
			nums.push_back(arr[i]);
			solve(nums);
			nums.pop_back();
		}
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (i == j) continue;
			if (arr[i] * 2 == arr[j] || (arr[i] % 3 == 0 && arr[i] / 3 == arr[j])) {
				dCnt[j]++;
			}
		}
	}
	for (int i = 0; i < N; i++) {
		if (dCnt[i] == 0) {
			vector<unsigned long long int> nums = vector<unsigned long long int>();
			nums.push_back(arr[i]);
			solve(nums);
			if (!ans.empty()) {
				break;
			}
		}
	}
	for (int i = 0; i < N; i++) {
		cout << ans[i] << " ";
	}
	cout << "\n";
	return 0;
}