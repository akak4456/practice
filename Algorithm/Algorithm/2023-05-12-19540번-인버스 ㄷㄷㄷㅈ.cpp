#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>
#include <cassert>
#include <set>
using namespace std;

int N;
const int MAX_N = 300000;
set<int> graph[MAX_N + 1];

int main() {
	/*
	https://static.ucpc.me/files/2020/ucpc20-prelim-solutions.pdf
	를 참조해서 풀이를 제작함.
	근데 이거 말고 다른 방법도 있다고 하는데 시간이 되면 시도해보도록 할까?
	*/
	ios::sync_with_stdio(false);
	cin >> N;
	if (N > 8) {
		int x = 1;
		if (N % 2 == 1) {
			while (true) {
				if (4 * x + 5 <= N && N <= 6 * x + 9) {
					break;
				}
				x++;
			}
			for (int i = 1; i < x; i++) {
				graph[i].insert(i + 1);
				graph[i + 1].insert(i);
			}
			for (int i = x + 1; i <= x + x; i++) {
				graph[i].insert(i - x);
				graph[i - x].insert(i);
			}
			for (int i = x + x + 1; i <= x + x + x; i++) {
				graph[i].insert(i - x - x);
				graph[i - x - x].insert(i);
			}
			graph[x + x + x + 1].insert(1);
			graph[1].insert(x + x + x + 1);
			graph[x + x + x + 2].insert(x);
			graph[x].insert(x + x + x + 2);
		}
		else {
			while (true) {
				if (4 * x + 6 <= N && N <= 6 * x + 8) {
					break;
				}
				x++;
			}
			for (int i = 1; i < x; i++) {
				graph[i].insert(i + 1);
				graph[i + 1].insert(i);
			}
			for (int i = x + 1; i <= x + x; i++) {
				graph[i].insert(i - x);
				graph[i - x].insert(i);
			}
			for (int i = x + x + 1; i <= x + x + x; i++) {
				graph[i].insert(i - x - x);
				graph[i - x - x].insert(i);
			}
			graph[x + x + x + 1].insert(1);
			graph[1].insert(x + x + x + 1);
			graph[x + x + x + 2].insert(x);
			graph[x].insert(x + x + x + 2);
			graph[x + x + x + 3].insert(x + x + x + 2);
			graph[x + x + x + 2].insert(x + x + x + 3);
			graph[x + x + x + 4].insert(x + x + x + 2);
			graph[x + x + x + 2].insert(x + x + x + 4);
		}

		int nextNodeNum = 3 * x + 3;
		if (N % 2 == 0) {
			nextNodeNum = 3 * x + 5;
		}
		int twoNext = nextNodeNum;
		int cntTwoCase = (N - (4 * x + 5)) / 2;
		if (N % 2 == 0) {
			cntTwoCase = (N - (4 * x + 6)) / 2;
		}
		int cntOneCase = (x + 3) - cntTwoCase;
		if (N % 2 == 0) {
			cntOneCase = (x + 2) - cntTwoCase;
		}
		for (int i = 0; i < cntOneCase; i++) {
			for (int j = 1; j <= x; j++) {
				if (graph[j].size() == 4) {
					auto it = graph[j].begin();
					bool isUpdate = false;
					while (it != graph[j].end()) {
						if (graph[*it].size() == 1) {
							int a = j;
							int b = *it;
							graph[a].erase(b);
							graph[b].erase(a);
							graph[a].insert(nextNodeNum);
							graph[nextNodeNum].insert(a);
							graph[b].insert(nextNodeNum);
							graph[nextNodeNum].insert(b);
							nextNodeNum++;
							isUpdate = true;
							break;
						}
						it++;
					}
					if (isUpdate) break;
				}
			}
		}
		while (nextNodeNum <= N) {
			auto it = graph[twoNext].begin();
			while (it != graph[twoNext].end()) {
				if (graph[*it].size() == 1) {
					int a = twoNext;
					int b = *it;
					graph[a].erase(b);
					graph[b].erase(a);
					graph[a].insert(nextNodeNum);
					graph[nextNodeNum].insert(a);
					graph[b].insert(nextNodeNum);
					graph[nextNodeNum].insert(b);
					twoNext = nextNodeNum;
					nextNodeNum++;
					break;
				}
				it++;
			}
		}

		for (int i = 1; i <= N; i++) {
			auto it = graph[i].begin();
			while (it != graph[i].end()) {
				if (*it > i) {
					cout << i << ' ' << *it << "\n";
				}
				it++;
			}
		}
	}
	else {
		if (N == 6) {
			cout << "1 2" << endl;
			cout << "1 3" << endl;
			cout << "1 4" << endl;
			cout << "4 5" << endl;
			cout << "5 6" << endl;
		}
		else if (N == 7) {
			cout << "1 2" << endl;
			cout << "1 3" << endl;
			cout << "1 4" << endl;
			cout << "4 5" << endl;
			cout << "4 6" << endl;
			cout << "6 7" << endl;
		}
		else if (N == 8) {
			cout << "1 2" << endl;
			cout << "1 3" << endl;
			cout << "1 4" << endl;
			cout << "4 5" << endl;
			cout << "5 6" << endl;
			cout << "5 7" << endl;
			cout << "7 8" << endl;
		}
	}
	return 0;
}