#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include<cmath>
#include<string>
#include<queue>
#include <map>
using namespace std;
long long int N, M;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	long long int ans = N - M;
	if (ans < 0) {
		ans = -ans;
	}
	cout << ans << "\n";
	return 0;
}