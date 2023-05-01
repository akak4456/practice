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
	// ���� �� �������� ������ �ִ�.
	// root - child level1 - child leve2 �� �̾����ٰ� �� ��
	// root �� �������� ������ child level1 �߿� �ִ� �� �߿� �ϳ��� �ݵ�� ������ �Ǿ�� �ϴµ�
	// ���⿡���� �ݿ��� ���� �ʾҴ�.
	
	return solDp[n];
}
*/
/*
graph[0] �� ������� �ʴ´�.
graph[1] �� 1���濡 ����� �ٸ� ���� �ǹ��Ѵ�.
graph[n] �� n���濡 ����� �ٸ� ���� �ǹ��Ѵ�.
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
int solution(int n, int len) {
	visited[n] = true;
	int ret = -1;
	for (int i = 0; i < graph[n].size(); i++) {
		int target = graph[n][i];
		if (!visited[target]) {
			if (graph[target].size() <= 2) {
				ret = max(ret, solution(target, len + 1));
			}
			else {
				ret = max(ret, solution(target, 1));
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
int main() {
	/*
	n���� ������ �̷���� �̷ΰ� �ִ�. �� �̷� ���� ������ �� �� ���̿��� �ݵ�� �ϳ��� ��ΰ� �����ϰ�, �� ��δ� �����ϴ�.
	�� ��� �� �� �濡�� ���ּ� ������ ������ ���� ���Ҵµ�, ������ ������ �� ������ ã�� ���Ѵ�. �׷��� ���ؼ�, ������ ������ ���ּ� �������� Ư���� �濡 ������ �ִ��� ���� ����. ģ���� ���ּ� ������ ������ ������ ���� ���� ������� �׷��ٰ� ���� �ְ�, ���� ���� ���� �ʾҴٸ� �� �濡 ����� ���� �� ��� ������ ���� ���߸� ������ ã�� �� �ִ��� ���� �ش�.
	�������� �� ���� �̷��� ������ �־����� �� ������ ������ �־��� ��쿡 �� ���� ������ ������ �ϴ��� ����� ���� ���̴�. ����, ������ ������ ������ �׻� �ּ��� ������ �Ѵ�.
	
	��� �� ������ � �� ���� üũ���� �� �� ���� ����Ǵ� ���鵵 üũ�� �ȴٰ� �Ѵٸ�, ��� ������ üũ�ϰ� �ʹٰ� �� �� ��� �ּ����� ���� �����̳� ��� �ؼ��� �� �ִ�.
	���� ������ ����� ���� ��� ���� ��� �׽�Ʈ �غ��°Ű����� �׷� �Ƹ� �ð� �ʰ��� �߰���...

	ù° �ٿ� n�� �־�����. (1 �� n �� 50,000) ���� n-1���� �ٿ��� ���� �� ���� ���ڰ� �־�����. a�� b�� �־����ٸ�, a�� ��� b�� �� ���̿� ������ �־� �շ��� �� �ִٴ� �ǹ��̴�. ���� ��ȣ�� 1������ n������ �����ؼ� �پ� �ִ�.
	�̴� Ʈ������ �ǹ��Ѵ�.

	Ʈ�� Ư���� � ���� root �� �ص� ����� ����. �� min(root �� �������� ��, root �� �������� �ʾ��� ��) �� ����� �����غ���.

	�ϴ� solution �� O(N) �ϰ� ����� ������ ���� �̷��� �ϸ� �ð� �ʰ��� �߱� ��������...

	root �������� ������ �����ϴ�. leaf �������� ��������?

	���� ������ �׷������� ��������?
	1����-2���� �� �̷� �׷����� ��� � �� �����ص� �������.
	1����-2����, 1����-3���� �̷������� ������ �Ǹ� ���� 1������ �����ؾ߸� �ϰ���...
	�ٵ� ��� 1����-2���� �̰͵� ��� ������ ���� Ʈ����� �׸��� ������ 1���� �����ؾ� �Ѵٸ� 1������ �����ϴ� ���� �̵�ƴұ�?

	�׷��� leaf �� �θ�� set �� �ǹ��ϴ� �ǰ�? �ƴ� �װ� �ƴѰ� ����.
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

	5
	1 2
	2 3
	2 4
	1 5

	��� �������� ���� �ϴ����� ����� �Ŵ� �� ������ ������ Ʋ�ȴ�. �̷��� ������ �� �о���� �Ѵ�.
	��� ���������� �˷��شٰ� ������

	


	������ 2�̴�.
	�ٵ� �̰� �´� leaf�� �θ� ���ؼ��� �ѹ� ������߸� �Ѵ�.

	8
	1 2
	2 3
	2 4
	2 5
	3 6
	4 7
	5 8

	8
	1 4
	2 4
	3 4
	4 5
	5 6
	5 7
	5 8
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
		if (graph[i].size() > 2) {
			cout << solution(i, 0) << endl;
			break;
		}
	}
	return 0;
}