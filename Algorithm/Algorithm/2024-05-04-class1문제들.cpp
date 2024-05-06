#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
using namespace std;
int arr[300000];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n;
	string s;
	cin >> n >> s;
	long long int hash = 0;
	long long int mul = 1;
	for (int i = 0; i < n; i++) {
		hash += ((s[i] - 'a' + 1) * mul) % 1234567891;
		mul = (mul * 31) % 1234567891;
	}
	cout << (hash % 1234567891) << endl;
}