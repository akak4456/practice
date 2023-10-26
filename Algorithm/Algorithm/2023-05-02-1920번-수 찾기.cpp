/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>
#include <algorithm>
using namespace std;

int N;
int A[100000];
int Q[100000];

bool binarySearch(int target) {
    int start = 0;
    int end = N - 1;
    while (start <= end) {
        int mid = (start + end) / 2;
        if (A[mid] == target) {
            return true;
        }
        else if (A[mid] < target) {
            start = mid + 1;
        }
        else {
            end = mid - 1;
        }
    }
    return false;
}

int main() {
    /*
    binary search �� �̿��ϸ� �ȴ�.
    �̶� �Է°� ����� ���� �ؾ� �ϹǷ�
    �� �κп� ���� ����ȭ�� �ؾ߸� �Ѵ�.
    �׷��� �Է��ϴ� �κ� ����, ����ϴ� �κ� ���� �ϵ��� ��ġ�ߴ�.
    */
    std::ios::sync_with_stdio(false);
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }
    sort(A, A + N);
    int M;
    cin >> M;
    for (int i = 0; i < M; i++) {
        int t;
        cin >> Q[i];

    }
    for (int i = 0; i < M; i++) {
        if (binarySearch(Q[i])) {
            cout << "1\n";
        }
        else {
            cout << "0\n";
        }
    }

    return 0;
}
