#include <iostream>
#include <algorithm>
using namespace std;

unsigned long long int nodeCnt[51];

int N;
string getPath(int root, int target, int ith) {
	if (root == target) return "";
	if (target <= root + nodeCnt[ith - 2]) {
		return "L" + getPath(root + 1, target, ith - 2);
	}
	else {
		return "R" + getPath(root + nodeCnt[ith - 2] + 1, target, ith - 1);
	}
}

int main() {
	nodeCnt[0] = 1;
	nodeCnt[1] = 1;
	for (int i = 2; i <= 50; i++) {
		nodeCnt[i] = nodeCnt[i - 1] + nodeCnt[i - 2] + 1;
	}
	int S, E;
	cin >> N >> S >> E;
	string pathFromRootToS = getPath(1, S, N);
	string pathFromRootToE = getPath(1, E, N);
	int prefixCount = 0;
	int suffixCount = 0;
	// cout << pathFromRootToS << ' ' << pathFromRootToE << endl;
	if (pathFromRootToS.size() > pathFromRootToE.size()) {
		prefixCount = pathFromRootToS.size() - pathFromRootToE.size();
	}
	if (pathFromRootToE.size() > pathFromRootToS.size()) {
		suffixCount = pathFromRootToE.size() - pathFromRootToS.size();
	}
	int commonIdx = 0;
	while (commonIdx < min(pathFromRootToS.size(), pathFromRootToE.size()) && pathFromRootToS[commonIdx] == pathFromRootToE[commonIdx]) {
		commonIdx++;
	}
	for (int i = 0; i < prefixCount; i++) {
		cout << "U";
	}
	for (int i = commonIdx; i < min(pathFromRootToS.size(), pathFromRootToE.size()); i++) {
		cout << "U";
	}
	for (int i = commonIdx; i < min(pathFromRootToS.size(), pathFromRootToE.size()); i++) {
		cout << pathFromRootToE[i];
	}
	for (int i = pathFromRootToE.size() - suffixCount; i < pathFromRootToE.size(); i++) {
		cout << pathFromRootToE[i];
	}

}