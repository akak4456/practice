#include <iostream>
using namespace std;

int my[9];
int enemy[9];

int main() {
	bool isUpset = false;
	/*
	1회초, 2회초, n회초 (1 <= n <= 9) 의 구간합
	*/
	int mySum = 0;
	/*
	1회말, 2회말, n회말 (1 <= n <= 9) 의 구간합
	*/
	int enemySum = 0;
	for (int i = 0; i < 9; i++) {
		cin >> my[i];
	}
	for (int i = 0; i < 9; i++) {
		cin >> enemy[i];
	}
	for (int i = 0; i < 9; i++) {
		mySum += my[i];
		/*
		문제에 '이기고 있는 순간에' 라는 표시에 주목하자.
		여기에는 (n회초 까지의 나의점수) > ((n-1)회말 까지의 적의 점수)
		를 판단해야 한다는 것이 숨어있음을 명심하자.
		*/
		if (mySum > enemySum) {
			isUpset = true;
		}
		enemySum += enemy[i];
	}
	if (isUpset) {
		cout << "Yes\n";
	}
	else {
		cout << "No\n";
	}

	return 0;
}