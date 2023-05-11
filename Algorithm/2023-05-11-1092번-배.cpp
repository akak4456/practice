#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

int main() {
	/*
	크레인이 들 수 있는 무게를 오름차순으로 정렬을 해보자. 그리고 모든 크레인은 동시에 움직인다.
	이러한 상황에서 가장 작은 무게를 들 수 있는 크레인을 a 라 하고 a 보다는 큰 무게를 들 수 있는 크레인을 b 라고 해보자.
	그렇다면 a가 들 수 있는 가장 무거운 박스의 무게를 c 라고 하자. 그럼 이걸 a 가 드는게 최적일까 b가 드는 게 최적일까?
	다시 말해 a가 들어야지만 최소의 시간이 들까? b가 들어야지만 최소의 시간이 들까? 이건 a가 들어야지만 최소의 시간이 들 것이다.
	왜냐하면 만약 이 상황에서 b가 c를 든다고 해보자. b 는 a보다 약간 더 무거운 것도 들 수 있으니 충분히 가능하다.
	그러나 a는 c보다 무거운 것은 들 수 없다. 따라서 만약 c가 남아있는 박스 중에서 가장 가벼운 것이었다면 a, b 동시에 작동할 수가 없다.
	어떠한 경우든 a가 c를 들어야지만 동시에 들 수가 있게 된다.

	일단 동시에 크레인이 돌아가므로 들 수 있는 무게가 작은 크레인까지도 무게를 드는것이 좋다.
	*/
	int N;
	cin >> N;
	vector<int> crane(N);
	for (int i = 0; i < N; i++) {
		cin >> crane[i];
	}
	sort(crane.begin(), crane.end());
	int M;
	cin >> M;
	multiset<int> box;
	for (int i = 0; i < M; i++) {
		int t;
		cin >> t;
		box.insert(t);
	}
	int cnt = 0;
	bool isPossible = true;
	while (box.size() > 0) {
		int beforeSz = box.size();
		for (int i = 0; i < crane.size(); i++) {
			if (box.size() == 0) break;
			auto it = box.lower_bound(crane[i]);
			if (it == box.end() || *it > crane[i]) 
				it--;
			if (it != box.end() && *it <= crane[i]) {
				box.erase(it);
			}
		}
		if (beforeSz == box.size()) {
			isPossible = false;
			break;
		}
		cnt++;
	}
	if (isPossible) {
		cout << cnt << endl;
	}
	else {
		cout << -1 << endl;
	}
}