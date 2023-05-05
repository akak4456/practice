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

// ��İ� ���͸� �����ϴ� ����ü
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
// ��İ� ���͸� ������ ���ٸ��� ���·� ��ȯ�ϴ� �Լ�
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

// ���ٸ��� ��ķκ��� �ظ� ����ϴ� �Լ�
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

// ���콺 �ҰŹ��� ����Ͽ� ���� �������� �ظ� ���ϴ� �Լ�
vector<int> solveEquations(vector<vector<int>> A, vector<int> B) {
    int m = A.size();
    int n = A[0].size();

    // Ȯ�� ��� ����
    vector<vector<int>> AB(m, vector<int>(n + 1));
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            AB[i][j] = A[i][j];
        }
        AB[i][n] = B[i];
    }

    // ���콺 �ҰŹ� ����
    for (int i = 0; i < min(m, n); i++) {
        // �ǹ� ����� ����
        int max_idx = i;
        for (int j = i + 1; j < m; j++) {
            if (abs(AB[j][i]) > abs(AB[max_idx][i])) {
                max_idx = j;
            }
        }

        // �ǹ� ��ҿ��� �� ��ȯ
        swap(AB[i], AB[max_idx]);

        // ��� ��ٸ��÷� ��ȯ
        for (int j = i + 1; j < m; j++) {
            int f = AB[j][i] / AB[i][i];
            for (int k = i + 1; k <= n; k++) {
                AB[j][k] -= f * AB[i][k];
            }
        }
    }

    // ���� ������ ���� ��� ���
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
    // �ذ� Ȯ���� ���� ���
    // �Ʒ��� ���� �ظ� ���ϰ�
    // �� ������ �ʿ� ���� �ٷ� return �ϸ�
    // ������ ������ �ɰ� ����.
    // �ٵ� �ظ� ��� ������?
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
    1��° ĭ���� 1ĭ �������� �� �������� ���ڸ� x1
    1��° ĭ���� 2ĭ �������� �� �������� ���ڸ� x2
    2��° ĭ���� 2ĭ �������� �� �������� ���ڸ� x3
    1��° ĭ���� 3ĭ �������� �� �������� ���ڸ� x4
    2��° ĭ���� 3ĭ �������� �� �������� ���ڸ� x5
    3��° ĭ���� 3ĭ �������� �� �������� ���ڸ� x6
    1��° ĭ���� 4ĭ �������� �� �������� ���ڸ� x7
    2��° ĭ���� 4ĭ �������� �� �������� ���ڸ� x8
    3��° ĭ���� 4ĭ �������� �� �������� ���ڸ� x9
    4��° ĭ���� 4ĭ �������� �� �������� ���ڸ� x10
    1��° ĭ���� 5ĭ �������� �� �������� ���ڸ� x11
    2��° ĭ���� 5ĭ �������� �� �������� ���ڸ� x12
    3��° ĭ���� 5ĭ �������� �� �������� ���ڸ� x13
    4��° ĭ���� 5ĭ �������� �� �������� ���ڸ� x14
    5��° ĭ���� 5ĭ �������� �� �������� ���ڸ� x15
    1��° ĭ���� 6ĭ �������� �� �������� ���ڸ� x16
    2��° ĭ���� 6ĭ �������� �� �������� ���ڸ� x17
    3��° ĭ���� 6ĭ �������� �� �������� ���ڸ� x18
    4��° ĭ���� 6ĭ �������� �� �������� ���ڸ� x19
    5��° ĭ���� 6ĭ �������� �� �������� ���ڸ� x20
    6��° ĭ���� 6ĭ �������� �� �������� ���ڸ� x21

    Input 2
    11
    1 0 0 1 1 0 1 0 1 1 0

    Possible output 2
    3
    4 5
    5 5
    1 6
    �� �ݷʰ� �ִ�.
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
            if (row == 0 && row + 1 + distance > arrCount) break;//��� �ΰ��� ��ƾ� �ϹǷ�
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