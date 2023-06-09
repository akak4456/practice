#include <iostream>
using namespace std;

long long int fibLike[116 + 1];
int main() {
	int n;
	cin >> n;
	fibLike[1] = 1;
	fibLike[2] = 1;
	fibLike[3] = 1;
	for (int i = 4; i <= n; i++) {
		fibLike[i] = fibLike[i - 1] + fibLike[i - 3];
	}
	cout << fibLike[n] << endl;
	return 0;
}