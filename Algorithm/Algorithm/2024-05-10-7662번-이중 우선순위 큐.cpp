#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
priority_queue<int> maxQ;
priority_queue<int, vector<int>, greater<int>> minQ;
map<int, int> m;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	while (T--) {
		int k;
		cin >> k;
		maxQ = priority_queue<int>();
		minQ = priority_queue<int, vector<int>, greater<int>>();
		m.clear();
		while (k--) {
			char ch;
			cin >> ch;
			if (ch == 'I') {
				int t;
				cin >> t;
				maxQ.push(t);
				minQ.push(t);
				m[t]++;
			}
			else if (ch == 'D') {
				int t;
				cin >> t;
				if (t == 1) {
					while (maxQ.size() > 0 && m[maxQ.top()] == 0) {
						maxQ.pop();
					}
					if (maxQ.size() > 0) {
						m[maxQ.top()]--;
						maxQ.pop();
					}
				}
				else {
					while (minQ.size() > 0 && m[minQ.top()] == 0) {
						minQ.pop();
					}
					if (minQ.size() > 0) {
						m[minQ.top()]--;
						minQ.pop();
					}
				}
			}
			// showHeap();
		}
		while (maxQ.size() > 0 && m[maxQ.top()] == 0) {
			maxQ.pop();
		}
		while (minQ.size() > 0 && m[minQ.top()] == 0) {
			minQ.pop();
		}
		if (maxQ.size() == 0 && minQ.size() == 0) {
			cout << "EMPTY\n";
		}
		else {
			cout << maxQ.top() << " " << minQ.top() << "\n";
		}
	}
	return 0;
}