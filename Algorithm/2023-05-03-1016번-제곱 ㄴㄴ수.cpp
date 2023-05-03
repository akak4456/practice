#include <iostream>
#include <set>
#include <cmath>
#include <vector>
using namespace std;

bool isPrime[1000101];
vector<unsigned long long> prime;
set<unsigned long long> ran;

int main() {
    /*
    우선 에라토스테네스의 체를 이용해서 sqrt(1,000,001,000,000) 까지의 소수 목록을 구하자.
    그리고 제곱ㄴㄴ수는 최대 100만개있다는 점을 명심하자.
    요점은 min <= x <= max 를 만족하는 x list 가 있다고 할 때 에라토스테네스의 체와 비슷하게
    (소수의 제곱) 의 배수들을 빼나가면 된다는 것이다.
    예를 들어 2의 제곱의 배수 (4, 8, 12, ...) 을 x list 에 빼고
    3의 제곱의 배수 (9, 18, 27, 36, ...) 를 x list 에 빼고
    5의 제곱의 배수 (25, 50, 75, 100, ...) 을 x list 에 빼는 식으로 하면 된다.
    */
    unsigned long long minVal, maxVal;
    cin >> minVal >> maxVal;
    unsigned long long maxValSqrt = ceil(sqrt(maxVal));
    for (int i = 0; i < 1000101; i++) {
        isPrime[i] = true;
    }
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i <= maxValSqrt; i++) {
        if (isPrime[i]) {
            prime.push_back(i);
            for (int j = 2; i * j <= maxValSqrt; j++) {
                isPrime[i * j] = false;
            }
        }
    }
    for (unsigned long long i = minVal; i <= maxVal; i++) {
        ran.insert(i);
    }
   
    for (int i = 0; i < prime.size(); i++) {
        unsigned long long int p = prime[i];

        unsigned long long int lower = (unsigned long long)ceil(minVal * 1.0 / (p * p));
        unsigned long long int upper = (unsigned long long)floor(maxVal * 1.0 / (p * p));
        for (unsigned long long j = lower; j <= upper; j++) {
            ran.erase(p * p * j);
        }
    }
    cout << ran.size() << endl;
    return 0;
}