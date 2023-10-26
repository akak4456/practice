#include <iostream>
using namespace std;

int main() {
	string N;
	cin >> N;
	/*
	30의 배수는 뭘까?
	3의 배수이면서 동시에 10의 배수인 것을 의미하겠지...
	3의 배수는 3,6,9,...,30,33,...,57,60 같이 있고
	10의 배수는 10,20,30,...,50,60 같이 있으니
	30의 배수는 3의 배수이면서 10의 배수인것을 찾으면 그만
	그럼 우선 10의 배수가 되려면 어떻게 해야할까? 1의 자리가 0이어야만 하지.
	그러니 반드시 0은 1개가 존재해야만 한다.
	3의 배수가 되려면 모든 자리수의 합이 3의 배수가 되어야만 한다.
	그렇다면 이게 존재하는지 안하는지는 충분히 따질 수 있겠지...
	가장 큰수가 되려면 이건 너무 명확하다. 그냥 큰수부터 먼저 배열하면 된다.
	*/
	unsigned long long int t = 0;
	for (int i = 0; i < N.size(); i++) {
		t += N[i] - '0';
	}
	if (t % 3 == 0) {
		unsigned long long int cnt[10] = { 0,0,0,0,0,0,0,0,0,0 };
		for (int i = 0; i < N.size(); i++) {
			cnt[N[i] - '0']++;
		}
		if (cnt[0] == 0) {
			cout << "-1" << endl;
		}
		else {
			string sol = "";
			for (int i = 9; i >= 0; i--) {
				for (int j = 0; j < cnt[i]; j++) {
					sol += (i + '0');
				}
			}
			cout << sol << endl;
		}
	}
	else {
		cout << "-1" << endl;
	}
	return 0;
}