#include <iostream>
using namespace std;

int main() {
	int A, B;
	cin >> A >> B;
	int a, b;
	a = A;
	b = B;
	while (b > 0) {
		// cout << a << ' ' << b << endl;
		int tempA = a;
		a = b;
		b = tempA % b;
	}
	cout << a << endl;
	cout << a * (A / a) * (B / a) << endl;
	return 0;
}