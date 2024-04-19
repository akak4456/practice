#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int A, B, C;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> A >> B >> C;
	int newA = A;
	int newB = B + C;
	if (newB >= 60) {
		newA = newA + newB / 60;
		newB = newB % 60;
	}
	if (newA >= 24) {
		newA = newA % 24;
	}
	cout << newA << " " << newB << "\n";
	return 0;
}