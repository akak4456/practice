#include <iostream>
#include <algorithm>
using namespace std;

int N;
int arr[100000 + 1];
int M;
pair<int, int> segTree[4 * 100000];
pair<int, int> init(int node, int st, int end) {
	if (st == end) return segTree[node] = make_pair(arr[st], st);
	int mid = (st + end) / 2;
	pair<int, int> left = init(node * 2, st, mid);
	pair<int, int> right = init(node * 2 + 1, mid + 1, end);
	if (left.first < right.first) {
		return segTree[node] = left;
	}
	else if (left.first == right.first) {
		if (left.second < right.second) {
			return segTree[node] = left;
		}
		else {
			return segTree[node] = right;
		}
	}
	else {
		return segTree[node] = right;
	}
}

pair<int, int> update(int index, int value, int node, int st, int end) {
	if (index < st || end < index) return segTree[node];

	if (st == end) return segTree[node] = make_pair(value, st);
	int mid = (st + end) / 2;
	pair<int, int> left = update(index, value, node * 2, st, mid);
	pair<int, int> right = update(index, value, node * 2 + 1, mid + 1, end);
	if (left.first < right.first) {
		return segTree[node] = left;
	}
	else if (left.first == right.first) {
		if (left.second < right.second) {
			return segTree[node] = left;
		}
		else {
			return segTree[node] = right;
		}
	}
	else {
		return segTree[node] = right;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int t;
		cin >> arr[i];
	}
	init(1, 1, N);
	cin >> M;
	while (M--) {
		int q;
		cin >> q;
		if (q == 1) {
			int i, v;
			cin >> i >> v;
			update(i, v, 1, 1, N);
		}
		else if (q == 2) {
			cout << segTree[1].second << '\n';
		}
	}
	return 0;
}