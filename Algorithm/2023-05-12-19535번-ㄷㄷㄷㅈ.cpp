#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>
#include <cassert>
using namespace std;

int N;
const int MAX_N = 300000;
vector<int> graph[MAX_N + 1];
bool visitedD[MAX_N + 1];
bool visitedG[MAX_N + 1];
unsigned long long int D = 0;
unsigned long long int G = 0;

void calculateD(int node) {
	visitedD[node] = true;
	for (int i = 0; i < graph[node].size(); i++) {
		int target = graph[node][i];
		if (!visitedD[target]) {
			if (graph[node].size() >= 2 && graph[target].size() >= 2) {
				D += (graph[node].size() - 1) * (graph[target].size() - 1);
			}
			calculateD(target);
		}
	}
}

void calculateG(int node) {
	visitedG[node] = true;
	if (graph[node].size() >= 3) {
		unsigned long long int sz = graph[node].size();
		G += sz * (sz - 1) * (sz - 2) / 6;
	}
	for (int i = 0; i < graph[node].size(); i++) {
		int target = graph[node][i];
		if (!visitedG[target]) {
			calculateG(target);
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin >> N;
	for (int i = 0; i < N - 1; i++) {
		int a, b;
		cin >> a >> b;
		graph[a].push_back(b);
		graph[b].push_back(a);
	}
	calculateD(1);
	calculateG(1);
	cout << D << ' ' << G << endl;
	if (D > G * 3) {
		cout << "D" << endl;
	}
	else if (D < G * 3) {
		cout << "G" << endl;
	}
	else {
		cout << "DUDUDUNGA" << endl;
	}
	return 0;
}