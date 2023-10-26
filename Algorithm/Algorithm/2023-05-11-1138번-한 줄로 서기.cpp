#include <iostream>
using namespace std;

int main() {
	int N;
	cin >> N;
	int arr[10];
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	/*
	1의 키를 가진 사람은 (arr[0] + 1) 번째에 위치함은 자명한다.
	2의 키를 가진 사람은 어떻게 될까? arr[0] = 2라고 해보자.
	만약 arr[1] = 0 또는 1 이라면 2의 키를 가진 사람은 (arr[1] + 1) 번째에 위치한다.
	arr[1] = 2 라면 (arr[1] + 1) 번째에 사람이 이미 존재하므로 (arr[1] + 1 + 1) 번째에 위치함을 알 수 있다.
	이런 상황에서 또 arr[2] = 2 라면 어떻게 할까? 그렇다면 아마도 arr[2] + 1 + 1 + 1 번째에 위치함을 알 수 있다.
	이와 같이 1의 키를 가진 사람을 우선 배치하고 2의 키를 가진 사람을 나중에 배치하는 식으로 구현하면 편함을 알 수 있다.
	*/
	int solution[10] = {0,0,0,0,0,0,0,0,0,0};
	for (int i = 0; i < N; i++) {
		int cnt = 0;
		for (int j = 0; j < N; j++) {
			if (cnt == arr[i] && solution[j] == 0) {
				solution[j] = i + 1;
				break;
			}
			if (solution[j] == 0) {
				cnt++;
			}
		}
	}
	for (int i = 0; i < N; i++) {
		cout << solution[i] << ' ';
	}
	return 0;
}