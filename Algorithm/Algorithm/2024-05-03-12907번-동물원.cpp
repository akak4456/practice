#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
using namespace std;
int N;
int arr[40];
bool visited[40];
vector<int> group1;
vector<int> group2;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	for (int step = 0; step < N; step++) {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				if (group1.size() == arr[i]) {
					group1.push_back(arr[i]);
					visited[i] = true;
				}
				else if (group2.size() == arr[i]) {
					group2.push_back(arr[i]);
					visited[i] = true;
				}
			}
		}
	}
	if (group1.size() + group2.size() == N) {
		int result = 2;
		int minVal = group1.size();
		if (group2.size() < minVal) {
			minVal = group2.size();
		}
		for (int i = 0; i < minVal; i++) {
			result *= 2;
		}
		if (group1.size() == group2.size()) {
			result /= 2;
		}
		cout << result << "\n";
	}
	else {
		cout << "0\n";
	}
	return 0;
}