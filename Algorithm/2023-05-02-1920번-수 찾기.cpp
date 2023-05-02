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
    binary search 를 이용하면 된다.
    이때 입력과 출력을 많이 해야 하므로
    이 부분에 대한 최적화를 해야만 한다.
    그래서 입력하는 부분 따로, 출력하는 부분 따로 하도록 조치했다.
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
