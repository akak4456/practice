#include <iostream>
#include <set>
#include <cmath>
#include <vector>
#include <string>
using namespace std;

bool isPrime[100000];
vector<unsigned long long> prime;
unsigned long long int squareCount = 0;
set<unsigned long long> nSet;
void getSquareCount(int startPrimeIndex, int k, unsigned long long prior, bool isMinus, string priorString) {
    //cout << "call to " << startPrimeIndex << ' ' << priorString << endl;
    for (int i = startPrimeIndex; prime[i] * prime[i] * prior * prior <= k; i++) {
        unsigned long long n = prime[i] * prime[i] * prior * prior;
        /*
        if (nSet.find(n) == nSet.end()) {
            if (!isMinus) {
                squareCount += k / n;
            }
            else {
                squareCount -= k / n;
            }
            nSet.insert(n);
        }
        */
        if (!isMinus) {
            squareCount += k / n;
        }
        else {
            squareCount -= k / n;
        }
        unsigned long long newPrior = prior;
        getSquareCount(i+1, k, prior * prime[i], !isMinus, priorString + to_string(prime[i]) + ",");
        //for (int j = i + 1; (prior * prime[j - 1]) * (prior * prime[j - 1]) <= k; j++) {
           // cout << "call from " << startPrimeIndex << ' ' << priorString << ' ' << j << endl;
            
        //}
    }
    //cout << "call finish " << startPrimeIndex << ' ' << priorString << endl;
}
int main() {
    int K;
    cin >> K;
    for (int i = 0; i < 100000; i++) {
        isPrime[i] = true;
    }
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i < 100000; i++) {
        if (isPrime[i]) {
            prime.push_back(i);
            for (int j = 2; i * j < 100000; j++) {
                isPrime[i * j] = false;
            }
        }
    }
    /*
    K 의 최대값은 1000000000 이다.
    1부터 K까지의 제곱수 개수를 a 라고 하자.
    (K + 1) 부터 (K + a)까지의 제곱수 개수를 b라고 하자.
    (K + a + 1) 부터 (K + a + b)까지의 제곱수 개수를 c라고 하자.
    (K번째 제곱ㄴㄴ수) = K + a + b + c + ...
    일것이다.
    정확히 어떻게 될지는 모르겠지만 아마도 (K번째 제곱ㄴㄴ수) 는 수렴하는 형태가 될 것이다.
    더해지는 항들이 점점 0에 가까워 질것이기 때문이다.

    그럼 일단 고민되는 것. a를 빠르게 구할 수 있는 방법이 있을까?
    일단 2^2 의 배수는 K / (2^2) 개 만큼 있을것이다.(소수점 버림)
    3^2 의 배수는 K / (3^2) 개 만큼 있을것이다.
    n^2 의 배수는 K / (n^2) 개 만큼 있을것이다. 여기에서 n은 소수이고 n^2 <= K 이다.
    따라서 우선 이것들을 무지성으로 더하면 K(1/2^2 + 1/3^2 + ... 1/n^2) 이 될 것이다.
    포함 배제의 원리에 따라 a 를 어떻게든 구해보도록 하자.
    */
    unsigned long long sol = K;
    unsigned long long additional = 0;
    while (true) {
        squareCount = 0;
        nSet.clear();
        getSquareCount(0, K + additional, 1, false, "");
        // cout << squareCount - additional << endl;
        if (squareCount - additional <= 0) break;
        additional += squareCount - additional;
    }
    cout << sol + additional << endl;
    return 0;
}