#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <set>
#include <cmath>
using namespace std;

/*
int visited[50001];
int solDp[50001];

int solution(int n, int isSelected) {
	if (solDp[n] != -1) return solDp[n];
	visited[n] = 1;
	int rootSelected = 0;
	for (int i = 0; i < graph[n].size(); i++) {
		int childNode = graph[n][i];
		for (int j = 0; j < graph[childNode].size(); j++) {
			if (visited[graph[childNode][j]] == 0) {
				rootSelected += solution(graph[childNode][j], 1);
			}
		}
	}
	int rootUnselected = 0;
	for (int i = 0; i < graph[n].size(); i++) {
		if (visited[graph[n][i]] == 0) {
			rootUnselected += solution(graph[n][i], 1);
		}
	}
	visited[n] = 0;
	solDp[n] = min(rootSelected, rootUnselected) + 1;
	// 지금 이 로직에는 문제가 있다.
	// root - child level1 - child leve2 로 이어진다고 할 때
	// root 를 선택하지 않으면 child level1 중에 있는 것 중에 하나는 반드시 선택이 되어야 하는데
	// 여기에서는 반영이 되지 않았다.
	
	return solDp[n];
}
*/
/*
graph[0] 은 사용하지 않는다.
graph[1] 은 1번방에 연결된 다른 방을 의미한다.
graph[n] 은 n번방에 연결된 다른 방을 의미한다.
*/
vector<int> graph[50001];
bool visited[50001];
/*
int maxDepth = -1;
void dfs(int n, int depth) {
	maxDepth = max(depth, maxDepth);
	visited[n] = true;
	for (auto target : graph[n]) {
		if (!visited[target]) {
			dfs(target, depth + 1);
		}
	}
}
*/
/*
int solution(int n, int len) {
	// cout << n << ' ' << len << endl;
	visited[n] = true;
	int ret = -1;
	for (int i = 0; i < graph[n].size(); i++) {
		int target = graph[n][i];
		if (!visited[target]) {
			if (graph[n].size() > 2) {
				// 그러니까 이 부분이 문제인건데...
				if (len == 1) {
					ret = max(ret, solution(target, 1) + 1);
				}
				else {
					ret = max(ret, solution(target, 1) + (int)ceil(log2(len)));
				}
			}
			else {
				ret = max(ret, solution(target, len + 1));
			}
		}
	}
	if (ret == -1) {
		return (int)log2(len);
	}
	else {
		return ret;
	}
}
*/
int solution(int node, int depth, bool isFromBranch) {
	visited[node] = true;
	if (graph[node].size() <= 2) {
		for (int i = 0; i < graph[node].size(); i++) {
			int target = graph[node][i];
			if (!visited[target]) {
				return solution(target, depth + 1, isFromBranch);
			}
		}
		if (!isFromBranch) {
			return (int)log2(depth);
		}
		else {
			if (depth - 1 >= 1) {
				return (int)log2(depth - 1);
			}
			else {
				return 0;
			}
		}
	}
	else {
		int ret = 0;
		for (int i = 0; i < graph[node].size(); i++) {
			int target = graph[node][i];
			if (!visited[target]) {
				ret = max(ret, solution(target, 1, true));
			}
		}
		if (!isFromBranch) {
			int additional = 0;
			if (depth - 1 >= 1) {
				additional = (int)log2(depth - 1);
			}
			return ret + 1 + additional;
		}
		else {
			int additional = 0;
			if (depth - 2 >= 1) {
				additional = (int)log2(depth - 2);
			}
			return ret + 1 + additional;
		}
	}
}
int main() {
	/*
	n개의 방으로 이루어진 미로가 있다. 이 미로 내의 임의의 두 방 사이에는 반드시 하나의 경로가 존재하고, 그 경로는 유일하다.
	이 방들 중 한 방에는 김주성 조교가 보물을 숨겨 놓았는데, 김진영 조교는 이 보물을 찾길 원한다. 그러기 위해서, 김진영 조교는 김주성 조교에게 특정한 방에 보물이 있는지 물어 본다. 친절한 김주성 조교는 김진영 조교가 옳은 방을 골랐으면 그렇다고 말해 주고, 옳은 방을 고르지 않았다면 그 방에 연결된 복도 중 어느 복도를 따라 가야만 보물을 찾을 수 있는지 말해 준다.
	여러분이 할 일은 미로의 구조가 주어졌을 때 김진영 조교가 최악의 경우에 몇 번의 질문을 던져야 하는지 계산해 내는 것이다. 물론, 영리한 김진영 조교는 항상 최선의 질문을 한다.
	
	사실 이 문제는 어떤 한 점을 체크했을 때 그 점에 연결되는 점들도 체크가 된다고 한다면, 모든 점들을 체크하고 싶다고 할 때 어떻게 최소한의 점만 고를것이냐 라고 해석할 수 있다.
	제일 무식한 방법은 역시 모든 점을 골라서 테스트 해보는거겠지만 그럼 아마 시간 초과가 뜨겠지...

	첫째 줄에 n이 주어진다. (1 ≤ n ≤ 50,000) 이후 n-1개의 줄에는 각각 두 개의 숫자가 주어진다. a와 b가 주어졌다면, a번 방과 b번 방 사이에 복도가 있어 왕래할 수 있다는 의미이다. 방의 번호는 1번부터 n번까지 연속해서 붙어 있다.
	이는 트리임을 의미한다.

	트리 특성상 어떤 것을 root 로 해도 상관은 없다. 단 min(root 를 선택했을 때, root 를 선택하지 않았을 때) 로 나누어서 구현해보자.

	일단 solution 은 O(N) 일것 같기는 하지만 역시 이렇게 하면 시간 초과가 뜨기 마련이지...

	root 에서부터 따지면 복잡하다. leaf 에서부터 따져볼가?

	가장 간단한 그래프부터 따져볼까?
	1번방-2번방 딱 이런 그래프는 사실 어떤 걸 선택해도 상관없다.
	1번방-2번방, 1번방-3번방 이런식으로 연결이 되면 이제 1번방을 선택해야만 하겠지...
	근데 사실 1번방-2번방 이것도 어느 구조의 서브 트리라면 그리고 어차피 1번은 선택해야 한다면 1번방을 선택하는 것이 이득아닐까?

	그러면 leaf 의 부모들 set 을 의미하는 건가? 아니 그건 아닌것 같다.

	어느 방향으로 가야 하는지를 물어보는 거니 내 생각이 완전히 틀렸다. 이래서 문제를 잘 읽어봐야 한다.
	어느 방향인지를 알려준다고 했으니
	정답은 2이다.
	근데 이건 맞다 leaf의 부모에 대해서는 한번 물어봐야만 한다.

	13
	1 2
	1 3
	1 4
	2 5
	3 8
	4 11
	5 6
	5 7
	8 9
	8 10
	11 12
	11 13
	답:2

	5
	1 2
	2 3
	4 3
	5 3
	답:2

	8
	1 4
	2 4
	3 4
	4 5
	5 6
	5 7
	5 8
	답:2

	9
	1 4
	2 4
	3 4
	4 5
	5 6
	6 7
	6 8
	6 9
	답:2

	현재 시도한 방법은 다음과 같다.
	1 2
	2 3
	2 4
	4 5
	4 6
	과 같이 연결되었다고 할 때(대략 아령 모양)
	2번과 4번은 분기점이니까 일단 이건 무조건 선택한다고 가정하고
	트리가 만약 일직선 일시에 노드의 개수가 N 개 이면 log2(N) 이 정답인 것을
	이용해서 구하고자 하였는데 이 부분에서 잘 안된다...
	*/
	int n;
	cin >> n;
	for (int i = 0; i < n - 1; i++) {
		int a, b;
		cin >> a >> b;
		graph[a].push_back(b);
		graph[b].push_back(a);
	}
	/*
	int sol = 987654321;
	for (int i = 1; i <= n; i++) {
		memset(visited, 0, sizeof(visited));
		memset(solDp, -1, sizeof(solDp));
		sol = min(sol, solution(i));
	}
	*/
	/*
	int targetNode = 1;
	for (int i = 2; i <= n; i++) {
		if (graph[targetNode].size() < graph[i].size()) {
			targetNode = i;
		}
	}
	*/
	/*
	set<int> solSet;
	while (true) {
		bool isAbleToContinue = false;
		for (int i = 1; i <= n; i++) {
			if (graph[i].size() == 1) {
				isAbleToContinue = true;
				int parent = *graph[i].begin();
				solSet.insert(parent);
			}
		}
		if (!isAbleToContinue) {
			break;
		}
	}
	*/
	for (int i = 1; i <= n; i++) {
		if (graph[i].size() == 1) {
			cout << solution(i, 1, false) << endl;
			break;
		}
	}
	return 0;
}