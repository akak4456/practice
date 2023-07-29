#include <iostream>
#include <set>
#include <map>
using namespace std;

int N;
int P[250000];
set<int> s1; // P[i] 를 넣는다.
set<int> s2; // -P[i] 를 넣는다.
int height[250000];
bool hasLeft[250000];
bool hasRight[250000];
long long int heightsSum = 0;
int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> P[i];
	}
	s1.insert(P[0]);
	s2.insert(-P[0]);
	height[P[0]] = 1;
	for (int i = 1; i < N; i++) {
		auto it1 = s1.lower_bound(P[i]);
		int targetHeight = 987654321;
		int targetNode = -1;
		bool isLeftInserted = false;
		if (it1 != s1.end() && !hasLeft[*it1]) {
			targetHeight = height[*it1];
			targetNode = *it1;
			isLeftInserted = true;
		}
		auto it2 = s2.lower_bound(-P[i]);
		if (it2 != s2.end()) {
			// cout << P[i]<<' ' << -*it2 << endl;
			if (!hasRight[-*it2] && height[-*it2] < targetHeight && P[i] > -*it2) {
				targetHeight = height[-*it2];
				targetNode = -*it2;
				isLeftInserted = false;
			}
		}
		// cout << targetHeight << ' ' << targetNode << ' ' << isLeftInserted << endl;
		height[P[i]] = targetHeight + 1;
		if (isLeftInserted) {
			hasLeft[targetNode] = true;
		}
		else {
			hasRight[targetNode] = true;
		}
		s1.insert(P[i]);
		s2.insert(-P[i]);
	}
	for (int i = 0; i < N; i++) {
		heightsSum += height[i];
	}
	cout << heightsSum << endl;
}