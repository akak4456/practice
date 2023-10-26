#include <iostream>
using namespace std;

int main() {
	unsigned long long int K;
	cin >> K;
	/*
	일단 피보나치 수열의 형태로 되는 것은 알겠다
	즉 1자리 수는 1개
	2자리 수는 1개
	3자리 수는 2개
	4자리 수는 3개
	인것은 알았다.
	따라서 K가 주어졌을 때 몇개의 자리수를 가지는 지는 알 수 있을 것 같다.
	문제는 정확히 어떤 수가 되냐는 것이다
	일단 몇개의 자리수 할 때 몇개를 n 으로 표시해보자
	max K 는 1000000000000000000 이다.
	피보나치 수열로 계산해본 결과
	max n 은 86 즉 100 이 안된다.
	따라서 최대 86 자리의 수가 들어갈 것임을 알 수 있다.

	임의의 n 자리수라고 하자.
	그럼 첫번째는 무조건 10000...000 이 될것임은 자명하다.
	즉 1이 1개 0이 n-1 개 있을 것이다.
	그럼 두번째는? 100...001 과 같이 될것이다.
	세번째는? 100..010과 같이 될 것이다.
	네번째는? 100..100 과 같이 될 것이다.
	다섯번째는? 100..101 과 같이 될 것이다.
	여섯번째는? 100..1000 과 같이 될 것이다.

	즉 첫번째 자리가 0인 것은 1개
	첫번째 자리가 1인 것은 1개
	두번째 자리가 1인 것은 1개
	세번째 자리가 1인 것은 2개
	네번째 자리가 1인 것인 3개
	이다 따라서 여기에서 추측할 수 있는 것은 몇번째 자리수에 1이 다시 나올것이냐다.
	다시 말해 첫번째 피보나치 수열을 계산해서 n자리 수에 1이 나올것을 예상했다.
	그럼 남은 수가 있을 것인데 거기에서 다시 계산해서 p자리 수에 1이 나올것을 예상할 수 있다.
	*/
	string sol = "";
	unsigned long long int prevN = 0;
	while (K > 1) {
		unsigned long long int prevSum = 0;
		unsigned long long int n = 1;
		unsigned long long int a = 0, b = 1;
		unsigned long long int sum = 1;
		while (K > sum) {
			unsigned long long int c = a + b;
			prevSum = sum;
			sum += c;
			a = b;
			b = c;
			n++;
		}
		for (int i = n + 1; i < prevN; i++) {
			sol += '0';
		}
		sol += '1';
		prevN = n;
		if (K >= prevSum + 1) {
			K -= prevSum + 1;
		}
		else {
			break;
		}
	}
	if (K == 1) {
		for (int i = 2; i < prevN; i++) {
			sol += '0';
		}
		sol += '1';
	}
	else {
		for (int i = 1; i < prevN; i++) {
			sol += '0';
		}
	}
	cout << sol << endl;
	return 0;
}