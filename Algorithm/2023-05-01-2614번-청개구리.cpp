#include <iostream>
#include <map>
#include <vector>
#include<utility>
#include <stack>
#include <algorithm>
#include <cmath>
#include <set>
using namespace std;

const int N = 21;

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
        cout << A.b[i] << endl;
    }
}
// 행렬과 벡터를 간단한 행사다리꼴 형태로 변환하는 함수
Matrix row_echelon(Matrix A) {
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

    return A;
}

// 행사다리꼴 행렬로부터 해를 계산하는 함수
vector<int> solveExact(Matrix A) {
    int n = A.mat.size();
    int m = A.mat[0].size();

    vector<int> x(m, 0);
    for (int i = n - 1; i >= 0; i--) {
        int j = 0;
        while (j < m && A.mat[i][j] == 0) {
            j++;
        }

        if (j == m) {
            continue;
        }

        int sum = 0;
        for (int k = j + 1; k < m; k++) {
            sum += A.mat[i][k] * x[k];
        }

        x[j] = (A.b[i] - sum) / A.mat[i][j];
    }

    for (int i = 0; i < x.size(); i++) {
        if (x[i] < 0) return vector<int>();
    }

    return x;
}

// 가우스 소거법을 사용하여 연립 방정식의 해를 구하는 함수
vector<int> solveEquations(vector<vector<int>> A, vector<int> B) {
    int m = A.size();
    int n = A[0].size();

    // 확장 행렬 생성
    vector<vector<int>> AB(m, vector<int>(n + 1));
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            AB[i][j] = A[i][j];
        }
        AB[i][n] = B[i];
    }

    // 가우스 소거법 적용
    for (int i = 0; i < min(m, n); i++) {
        // 피벗 요소의 선택
        int max_idx = i;
        for (int j = i + 1; j < m; j++) {
            if (abs(AB[j][i]) > abs(AB[max_idx][i])) {
                max_idx = j;
            }
        }

        // 피벗 요소와의 행 교환
        swap(AB[i], AB[max_idx]);

        // 기약 사다리꼴로 변환
        for (int j = i + 1; j < m; j++) {
            int f = AB[j][i] / AB[i][i];
            for (int k = i + 1; k <= n; k++) {
                AB[j][k] -= f * AB[i][k];
            }
        }
    }

    // 후진 대입을 통한 결과 계산
    vector<int> X(n);
    for (int i = min(m, n) - 1; i >= 0; i--) {
        X[i] = AB[i][n];
        for (int j = i + 1; j < n; j++) {
            X[i] -= AB[i][j] * X[j];
        }
            X[i] /= AB[i][i];
    }

    return X;
}

void solve(Matrix A, int startIdx, int remainCount, vector<pair<int, int>> prior) {
    if (startIdx < 10) return;
    if (remainCount < 0 || startIdx >= N) return;
    if (remainCount == 0) {
        int cnt = 0;
        for (int i = 0; i < prior.size(); i++) {
            cnt += prior[i].second;
        }
        bool isPossible = true;
        for (int i = 0; i < A.b.size(); i++) {
            if (A.b[i] != 0) {
                isPossible = false;
                break;
            }
        }
        if (cnt > 0 && possibleCnt > cnt && isPossible) {
            possibleCnt = cnt;
            possible = prior;
        }
        return;
    }
    Matrix convertedA;
    convertedA.mat = vector<vector<int>>(A.mat);
    convertedA.b = vector<int>(A.b);
    for (int i = 0; i < convertedA.b.size(); i++) {
        if (convertedA.b[i] == 0) {
            for (int j = 0; j < convertedA.mat[i].size(); j++) {
                if (convertedA.mat[i][j] > 0) {
                    for (int k = 0; k < convertedA.mat.size(); k++) {
                        convertedA.mat[k][j] = 0;
                    }
                }
            }
        }
    }
    bool isAllZero = true;
    for (int i = 0; i < convertedA.mat.size(); i++) {
        if (convertedA.mat[i][startIdx] != 0) {
            isAllZero = false;
            break;
        }
    }
    // 해가 확정이 나는 경우
    // 아래와 같이 해를 구하고
    // 더 진행할 필요 없이 바로 return 하면
    // 빠르게 진행이 될것 같다.
    // 근데 해를 어떻게 구하지?
    Matrix B = row_echelon(A);
    vector<int> x = solveExact(B);
    if (x.size() > 0) {
        vector<pair<int, int>> newPrior = vector<pair<int, int>>(prior);
        for (int i = 0; i < x.size(); i++) {
            if (x[i] > 0) {
                newPrior.push_back(make_pair(i, x[i]));
            }
        }
        int cnt = 0;
        for (int i = 0; i < newPrior.size(); i++) {
            cnt += newPrior[i].second;
        }
        if (cnt > 0 && possibleCnt > cnt) {
            possibleCnt = cnt;
            possible = newPrior;
        }
        return;
    }
    if (isAllZero) {
        solve(convertedA, startIdx + 1, remainCount, prior);
    }
    else {
        for (int idx = remainCount; idx >= 0; idx--) {
            Matrix newA;
            newA.mat = vector<vector<int>>(convertedA.mat);
            newA.b = vector<int>(convertedA.b);
            bool isPossible = true;
            for (int i = 0; i < newA.b.size(); i++) {
                if (newA.mat[i][startIdx] > 0) {
                    newA.mat[i][startIdx] = 0;
                    newA.b[i] -= idx;
                    if (newA.b[i] < 0) {
                        isPossible = false;
                        break;
                    }
                }
            }
            vector<pair<int, int>> newPrior = vector<pair<int, int>>(prior);
            newPrior.push_back(make_pair(startIdx, idx));
            if (isPossible) {
                solve(newA, startIdx + 1, remainCount - idx, newPrior);
            }
        }
    }
}
int main() {
    int arrCount;
    cin >> arrCount;
    Matrix A;
    A.mat = vector<vector<int>>(arrCount, vector<int>(N));
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
    */
    for (int column = 0; column < N; column++) {
        int sum = 0;
        int startPoint, distance;
        int cnt = 1;
        while (sum < column) {
            cnt++;
            sum += cnt;
        }
        distance = cnt;
        startPoint = column + - (sum - cnt);
        for (int row = 0; row < arrCount; row++) {
            if (row == 0 && row + 1 + distance > arrCount) break;//적어도 두곳은 밟아야 하므로
            if (row + 1 < startPoint) continue;
            if ((row + 1 - startPoint) % distance == 0) {
                A.mat[row][column] = 1;
            }
        }
    }
    for (int i = 100; i >= 1; i--) {
        solve(A, 0, i, vector<pair<int, int>>());
    }
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