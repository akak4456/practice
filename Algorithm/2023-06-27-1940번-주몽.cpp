#include <iostream>
#include <algorithm>
#include <set>
using namespace std;

int N, M;
set<int> arr;
int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int tmp;
		cin >> tmp;
		arr.insert(tmp);
	}
	int cnt = 0;
	auto iter = arr.begin();
	while (iter != arr.end()) {
		int value = *iter;
		auto otherIter = arr.find(M - value);
		if (otherIter != arr.end() && iter != otherIter) {
			cnt++;
		}
		iter++;
	}

	cout << cnt / 2;
}