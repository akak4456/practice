#include <iostream>
using namespace std;

unsigned long long int ans[35 + 1];
int main() {
    unsigned long long int n;
    cin >> n;
    ans[0] = 1;
    for(unsigned long long int i = 1; i<=n;i++) {
        unsigned long long int sum = 0;
        for(unsigned long long int t=0; t <= i - 1; t++) {
            sum += ans[t] * ans[i - 1 - t];
        }
        ans[i] = sum;
    }
    cout << ans[n] << endl;
}