#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

bool isPossible = false;
void solve(string S, string T) {
	if (isPossible) return;
	if (S.size() == T.size()) {
		if (S == T) {
			isPossible = true;
		}
		return;
	}
	if (T[0] == 'A') {
		if (T[T.size() - 1] == 'A') {
			string newT = string(T);
			newT.erase(newT.end() - 1);
			solve(S, newT);
		}
 	}
	else {
		if (T[T.size() - 1] == 'A') {
			string newT = string(T);
			newT.erase(newT.end() - 1);
			solve(S, newT);
		}
		string newT = string(T);
		reverse(newT.begin(), newT.end());
		newT.erase(newT.end() - 1);
		solve(S, newT);
	}
}

int main() {
	/*
	S에서 어떤 연산을 통해 T로 가는 것은 경우의 수가 상당히 많다. 근데 반대로 T에서 S로 가는 것은 어떨가?
	그러면 반대 연산은 무엇일까?
	1) 문자열의 뒤에 A를 뺀다.
	2) 문자열을 뒤집고 문자열 뒤에 B를 뺀다.
	그럼 이걸 통해 알 수 있는 것은 T에서 가장 앞이 A라면 2) 연산을 수행할 수 없다는 것이다. 그러니까 가장 앞이 A라면 1) 연산을 수행해야 한다는 것이다.
	그러면 이번에는 T의 가장 앞이 B라고 해보자. 그럼 T의 가장 뒤가 A또는 B가 될 것인데 일단 T의 가장 뒤가 B라면 1) 연산을 수행할 수가 없다. 이때는 2) 연산을 수행해야만 한다.
	반대로 T의 가장 뒤가 A이면 1) 또는 2)의 연산을 수행할 수 있다.
	*/
	string S, T;
	cin >> S >> T;
	solve(S, T);
	if (isPossible) {
		cout << "1\n";
	}
	else {
		cout << "0\n";
	}
	return 0;
}