#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
using namespace std;
int N;
pair<int,int> arr[1000000];
int answer[1000000];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++) {
		int a;
		cin >> a;
		arr[i] = { a,i };
	}
	sort(arr, arr + N);
	int target = 0;
	int diffValue = arr[0].first;
	for (int i = 0; i < N; i++) {
		if (diffValue != arr[i].first) {
			target++;
			diffValue = arr[i].first;
		}
		answer[arr[i].second] = target;
	}
	for (int i = 0; i < N; i++) {
		cout << answer[i] << " ";
	}
	cout << "\n";
	return 0;
}