#include <iostream>
using namespace std;

int fib[41];

int main() {
	int N;
	cin >> N;
	/*
	코드 2의 실행횟수는 N-2 임은 자연스럽게 알 수 있다. 그렇다면 코드 1의 실행횟수는?
	fib(n) 의 실행횟수는 fib(n-1), fib(n-2) 의 실행횟수를 합친 것과 같다.
	fib(n-1) 의 실행횟수는 fib(n-2), fib(n-3) 의 실행횟수를 합친것과 같다.
	fib(n-2) 의 실행횟수는 fib(n-3), fib(n-4) 의 실행횟수를 합친 것과 같다.
	여기에서 fib(n-2), fib(n-3) 가 중복해서 계산됨을 알 수 있다.
	사실 실행횟수는 간단히 구할 수 있다. fib(n) 의 실행횟수는 fib(n-1) + fib(n-2) 일것이다.
	그러나 중요한 것은 저렇게 불필요한 연산이 중복이 된다는 부분이다.
	*/
	fib[1] = 1;
	fib[2] = 1;
	for (int i = 3; i <= 40; i++) {
		fib[i] = fib[i - 1] + fib[i - 2];
	}
	cout << fib[N - 1] + fib[N - 2] << " " << N - 2 << endl;
	return 0;
}