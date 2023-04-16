#include <iostream>
using namespace std;

int solution(int A, int B) {
	int cnt = 0;
	while (B > 0) {
		if (A == B) {
			return cnt + 1;
		}
		if (B % 10 == 1) {
			B = B / 10;
			cnt++;
		}
		else if (B % 2 == 0) {
			B = B / 2;
			cnt++;
		}
		else {
			break;
		}
	}
	return -1;
}

int main() {
	int A, B;
	cin >> A >> B;
	/*
	2를 곱한다.
	1을 수의 가장 오른쪽에 추가한다.
	가 있다고 한다. 근데 여기에서 A에 대해서 2를 곱하면 항상 짝수가 나오고
	1을 수의 가장 오른쪽에 추가하면 항상 홀수가 나오는 것 아닌가?
	그렇다면 반대로 B에서 역산한다고 해보자
	100 40021
	와 같이 주어진다고 할 때
	B는 홀수이다. 그렇다면 가장 오른쪽에 1을 빼자
	그럼 4002 가 되겠지 이를 2로 나누자
	그럼 2001 이 되겠지. 이와 같이 하면 되지 않을까?
	*/
	cout << solution(A, B);
	return 0;
}