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
    K �� �ִ밪�� 1000000000 �̴�.
    1���� K������ ������ ������ a ��� ����.
    (K + 1) ���� (K + a)������ ������ ������ b��� ����.
    (K + a + 1) ���� (K + a + b)������ ������ ������ c��� ����.
    (K��° ����������) = K + a + b + c + ...
    �ϰ��̴�.
    ��Ȯ�� ��� ������ �𸣰����� �Ƹ��� (K��° ����������) �� �����ϴ� ���°� �� ���̴�.
    �������� �׵��� ���� 0�� ����� �����̱� �����̴�.

    �׷� �ϴ� ��εǴ� ��. a�� ������ ���� �� �ִ� ����� ������?
    �ϴ� 2^2 �� ����� K / (2^2) �� ��ŭ �������̴�.(�Ҽ��� ����)
    3^2 �� ����� K / (3^2) �� ��ŭ �������̴�.
    n^2 �� ����� K / (n^2) �� ��ŭ �������̴�. ���⿡�� n�� �Ҽ��̰� n^2 <= K �̴�.
    ���� �켱 �̰͵��� ���������� ���ϸ� K(1/2^2 + 1/3^2 + ... 1/n^2) �� �� ���̴�.
    ���� ������ ������ ���� a �� ��Ե� ���غ����� ����.
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