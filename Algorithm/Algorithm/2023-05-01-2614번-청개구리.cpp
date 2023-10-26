#include <iostream>
#include <map>
#include <vector>
#include<utility>
#include <stack>
#include <algorithm>
#include <cmath>
#include <set>
using namespace std;

const int MAX_COLUMN = 21;

// 행렬과 벡터를 저장하는 구조체
struct Matrix {
    vector<vector<int>> mat;
    vector<int> b;
};
vector<pair<int, int>> possible;
int possibleCnt = 987654321;
void printMatrix(Matrix A) {
    for (int i = 0; i < A.mat.size(); i++) {
        for (int j = 0; j < A.mat[i].size(); j++) {
            cout << A.mat[i][j] << ' ';
        }
        cout << " | " << A.b[i] << endl;
    }
}
// 행렬과 벡터를 간단한 행사다리꼴 형태로 변환하는 함수
Matrix row_echelon(Matrix target) {
    Matrix A;
    A.mat = vector<vector<int>>(target.mat);
    A.b = vector<int>(target.b);
    int n = A.mat.size();
    int m = A.mat[0].size();

    int i = 0, j = 0;
    while (i < n && j < m) {
        int pivot = i;
        for (int k = i + 1; k < n; k++) {
            if (abs(A.mat[k][j]) > abs(A.mat[pivot][j])) {
                pivot = k;
            }
        }

        if (A.mat[pivot][j] == 0) {
            j++;
            continue;
        }

        if (pivot != i) {
            swap(A.mat[i], A.mat[pivot]);
            swap(A.b[i], A.b[pivot]);
        }

        for (int k = i + 1; k < n; k++) {
            int factor = A.mat[k][j] / A.mat[i][j];
            A.b[k] -= factor * A.b[i];
            for (int l = j; l < m; l++) {
                A.mat[k][l] -= factor * A.mat[i][l];
            }
        }

        i++;
        j++;
    }
    /*
    for (int i = 0; i < A.mat.size(); i++) {
        for (int j = i; j < A.mat[i].size(); j++) {
            if (A.mat[i][j] < 0) {
                for (int k = 0; k < A.mat[i].size(); k++) {
                    A.mat[i][k] *= -1;
                }
                A.b[i] *= -1;
                break;
            }
            else if (A.mat[i][j] > 0) {
                break;
            }
        }
    }
    */
    return A;
}
void generateExample(int n, vector<pair<int, int>> example) {
    vector<int> result = vector<int>(n + 1, 0);
    for (int i = 0; i < example.size(); i++) {
        int idx = example[i].first;
        while (idx <= n) {
            result[idx]++;
            idx += example[i].second;
        }
    }
    cout << n << endl;
    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += result[i];
        cout << result[i] << ' ';
    }
    cout << endl;
    cout << sum << endl;
}
int main() {
    vector<pair<int, int>> ex;
    int remainCount = 100;
    for (int i = 1; i <= 6; i++) {
        if (i == 2 || i == 3) continue;
        for (int j = i; j <= 6; j++) {
            if (j == 1 || j == 3) continue;
            for(int k=0; k<= i + j;k++)
            ex.push_back(make_pair(i, j));
            //remainCount -= i + j;
        }
    }
    // generateExample(3, ex);
    int arrCount;
    cin >> arrCount;
    Matrix A;
    A.mat = vector<vector<int>>(arrCount, vector<int>(MAX_COLUMN));
    A.b = vector<int>(arrCount);
    for (int i = 0; i < arrCount; i++) {
        cin >> A.b[i];
    }
    /*
    1번째 칸에서 1칸 간격으로 뛴 개구리의 숫자를 x1
    1번째 칸에서 2칸 간격으로 뛴 개구리의 숫자를 x2
    2번째 칸에서 2칸 간격으로 뛴 개구리의 숫자를 x3
    1번째 칸에서 3칸 간격으로 뛴 개구리의 숫자를 x4
    2번째 칸에서 3칸 간격으로 뛴 개구리의 숫자를 x5
    3번째 칸에서 3칸 간격으로 뛴 개구리의 숫자를 x6
    1번째 칸에서 4칸 간격으로 뛴 개구리의 숫자를 x7
    2번째 칸에서 4칸 간격으로 뛴 개구리의 숫자를 x8
    3번째 칸에서 4칸 간격으로 뛴 개구리의 숫자를 x9
    4번째 칸에서 4칸 간격으로 뛴 개구리의 숫자를 x10
    1번째 칸에서 5칸 간격으로 뛴 개구리의 숫자를 x11
    2번째 칸에서 5칸 간격으로 뛴 개구리의 숫자를 x12
    3번째 칸에서 5칸 간격으로 뛴 개구리의 숫자를 x13
    4번째 칸에서 5칸 간격으로 뛴 개구리의 숫자를 x14
    5번째 칸에서 5칸 간격으로 뛴 개구리의 숫자를 x15
    1번째 칸에서 6칸 간격으로 뛴 개구리의 숫자를 x16
    2번째 칸에서 6칸 간격으로 뛴 개구리의 숫자를 x17
    3번째 칸에서 6칸 간격으로 뛴 개구리의 숫자를 x18
    4번째 칸에서 6칸 간격으로 뛴 개구리의 숫자를 x19
    5번째 칸에서 6칸 간격으로 뛴 개구리의 숫자를 x20
    6번째 칸에서 6칸 간격으로 뛴 개구리의 숫자를 x21

    Input 2
    11
    1 0 0 1 1 0 1 0 1 1 0

    Possible output 2
    3
    4 5
    5 5
    1 6
    에 반례가 있다.

    예제 set
    8
    2 1 2 1 2 0 3 1

    8
    0 3 1 1 2 1 1 3
    */
    for (int column = 0; column < MAX_COLUMN; column++) {
        int sum = 0;
        int startPoint, distance;
        int cnt = 1;
        while (sum < column) {
            cnt++;
            sum += cnt;
        }
        distance = cnt;
        startPoint = column + -(sum - cnt);
        for (int row = 0; row < arrCount; row++) {
            if (row == 0 && row + 1 + distance > arrCount) break;//적어도 두곳은 밟아야 하므로
            if (row + 1 < startPoint) continue;
            if ((row + 1 - startPoint) % distance == 0) {
                A.mat[row][MAX_COLUMN - 1 - column] = 1;
            }
        }
    }
    printMatrix(A);
    vector<int> tmpMatrix = vector<int>(A.mat[0].size(), 0);
    int tmpB = 0;
    for (int i = 0; i < A.mat.size(); i++) {
        for (int j = 0; j < A.mat[i].size(); j++) {
            tmpMatrix[j] += A.mat[i][j];
        }
        tmpB += A.b[i];
    }
    for (int i = 0; i < tmpMatrix.size(); i++) {
        cout << tmpMatrix[i] << ' ';
    }
    cout << endl;
    cout << tmpB << endl;
    // solve(A, 0, vector<pair<int, int>>());
    cout << possibleCnt << endl;
    for (int i = 0; i < possible.size(); i++) {
        int sum = 0;
        int startPoint, distance;
        int cnt = 1;
        while (sum < possible[i].first) {
            cnt++;
            sum += cnt;
        }
        distance = cnt;
        startPoint = possible[i].first + -(sum - cnt);
        for (int t = 0; t < possible[i].second; t++) {
            cout << startPoint << ' ' << distance << endl;
        }
    }
    return 0;
}