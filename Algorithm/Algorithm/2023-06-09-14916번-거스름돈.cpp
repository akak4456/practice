#include <iostream>
#include<cstring>
using namespace std;

int cache[100000 + 1];

int main() {
	int n;
	cin >> n;
	memset(cache, -1, sizeof(cache));
	cache[2] = 1;
	cache[4] = 2;
	cache[5] = 1;
	for (int i = 6; i <= 100000; i++) {
		int calc = -1;
		if (cache[i - 2] != -1) {
			calc = cache[i - 2] + 1;
		}
		if (cache[i - 5] != -1) {
			if (calc == -1) {
				calc = cache[i - 5] + 1;
			}
			else {
				calc = min(calc, cache[i - 5] + 1);
			}
		}
		cache[i] = calc;
	}
	cout << cache[n];
}