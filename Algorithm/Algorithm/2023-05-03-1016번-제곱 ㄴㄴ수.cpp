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
    �켱 �����佺�׳׽��� ü�� �̿��ؼ� sqrt(1,000,001,000,000) ������ �Ҽ� ����� ������.
    �׸��� ������������ �ִ� 100�����ִٴ� ���� �������.
    ������ min <= x <= max �� �����ϴ� x list �� �ִٰ� �� �� �����佺�׳׽��� ü�� ����ϰ�
    (�Ҽ��� ����) �� ������� �������� �ȴٴ� ���̴�.
    ���� ��� 2�� ������ ��� (4, 8, 12, ...) �� x list �� ����
    3�� ������ ��� (9, 18, 27, 36, ...) �� x list �� ����
    5�� ������ ��� (25, 50, 75, 100, ...) �� x list �� ���� ������ �ϸ� �ȴ�.
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