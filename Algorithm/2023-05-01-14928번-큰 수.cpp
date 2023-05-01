#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main() {
	string N;
	cin >> N;
	/*
	a = Q * 20000303 + R 이라고 해보자.
	그럼 내가 알고 싶은 것은 R 이다.
	20000303 은 소수이다.
	*/
	reverse(N.begin(), N.end());
	while (N.size() >= 8) {
		string str1 = N.substr(N.size() - 8);
		reverse(str1.begin(), str1.end());
		int topMost = stoi(str1);
		if (topMost > 20000303) {
			topMost = topMost % 20000303;
			N.erase(N.size() - 8, 8);
			string target = to_string(topMost);
			reverse(target.begin(), target.end());
			N += target;
		}
		else if (topMost == 20000303) {
			N.erase(N.size() - 8, 8);
		}
		else if(N.size() >= 9){
			string str2 = N.substr(N.size() - 9);
			reverse(str2.begin(), str2.end());
			topMost = stoi(str2) % 20000303;
			N.erase(N.size() - 9, 9);
			string target = to_string(topMost);
			reverse(target.begin(), target.end());
			N += target;
		}
		else {
			break;
		}
	}
	if (N.size() == 0) {
		cout << 0 << endl;
	}
	else {
		reverse(N.begin(), N.end());
		cout << N << endl;
	}
	return 0;
}