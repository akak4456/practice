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

    return A;
}

// ���ٸ��� ��ķκ��� �ظ� ����ϴ� �Լ�
vector<int> solveExact(Matrix target) {
    Matrix A;
    A.mat = vector<vector<int>>(target.mat);
    A.b = vector<int>(target.b);

    vector<int> x(A.mat[0].size(), 0);
    for (int i = A.mat.size() - 1; i >= 0; i--) {
        // printMatrix(A);
        // cout << endl;
        int firstOnePosition = -1;
        for (int j = A.mat[i].size() - 1; j >= 0; j--) {
            if (abs(A.mat[i][j]) == 1) {
                if (firstOnePosition == -1) {
                    firstOnePosition = j;
                }
                else {
                    return vector<int>();
                }
            }
        }
        if (firstOnePosition == -1) {
            if (A.b[i] == 0) continue;
            else {
                vector<int> ret;
                ret.push_back(-987654321);
                return ret;
            }
        }
        int sign = 1;
        if (A.mat[i][firstOnePosition] < 0) {
            sign = -1;
        }
        x[firstOnePosition] = A.b[i] * sign;
        for (int t1 = 0; t1 < A.mat.size(); t1++) {
            if (A.mat[t1][firstOnePosition] > 0) {
                A.b[t1] -= A.b[i];
            }
            else if (A.mat[t1][firstOnePosition] < 0) {
                A.b[t1] += A.b[i];
            }
            A.mat[t1][firstOnePosition] = 0;
        }
    }

    return x;
}

void solve(Matrix A, int startIdx, vector<pair<int, int>> prior) {
    int oneCnt = 0;
    for (int i = 0; i < A.mat[0].size(); i++) {
        for (int j = 0; j < A.mat.size(); j++) {
            if (A.mat[j][i] != 0) {
                oneCnt++;
                break;
            }
        }
    }
    if (oneCnt == A.mat.size()) {
        Matrix B = row_echelon(A);
        printMatrix(B);
        cout << endl;
        return;
    }
    if (startIdx >= N) return;
    int cnt = 0;
    for (int i = 0; i < prior.size(); i++) {
        cnt += prior[i].second;
    }
    int maxA = 0;
    for (int i = 0; i < A.b.size(); i++) {
        maxA = max(maxA, A.b[i]);
    }
    if (cnt + maxA >= possibleCnt) return;
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
    map<int, int> validCheck;
    for (int i = 0; i < convertedA.mat.size(); i++) {
        int key = 0;
        for (int j = 0; j < convertedA.mat[i].size(); j++) {
            if (convertedA.mat[i][j] > 0) {
                key += (1 << j);
            }
        }
        if (key == 0 && convertedA.b[i] != 0) return;
        if (validCheck.find(key) != validCheck.end() && validCheck[key] != convertedA.b[i]) return;
        validCheck[key] = convertedA.b[i];
    }
    // �ذ� Ȯ���� ���� ���
    // �Ʒ��� ���� �ظ� ���ϰ�
    // �� ������ �ʿ� ���� �ٷ� return �ϸ�
    // ������ ������ �ɰ� ����.
    Matrix B = row_echelon(convertedA);
    vector<int> x = solveExact(B);
    if (x.size() > 0) {
        if (x.size() == 1 && x[0] == -987654321) return;
        for (int i = 0; i < x.size(); i++) {
            if (x[i] < 0) return;
        }
        vector<pair<int, int>> newPrior = vector<pair<int, int>>(prior);
        int sum = 0;
        for (int i = 0; i < x.size(); i++) {
            sum += x[i];
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
        solve(convertedA, startIdx + 1, prior);
    }
    else {
        int maxRange = 987654321;
        int sum = 0;
        int startPoint, distance;
        int cnt = 1;
        while (sum < startIdx) {
            cnt++;
            sum += cnt;
        }
        distance = cnt;
        startPoint = startIdx - (sum - cnt);
        for (int row = 0; row < A.mat.size(); row++) {
            if ((row + 1 - startPoint) % distance == 0) {
                maxRange = min(maxRange, A.b[row]);
            }
        }
        for (int idx = maxRange; idx >= 0; idx--) {
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
                solve(newA, startIdx + 1, newPrior);
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
        startPoint = column + -(sum - cnt);
        for (int row = 0; row < arrCount; row++) {
            if (row == 0 && row + 1 + distance > arrCount) break;//��� �ΰ��� ��ƾ� �ϹǷ�
            if (row + 1 < startPoint) continue;
            if ((row + 1 - startPoint) % distance == 0) {
                A.mat[row][column] = 1;
            }
        }
    }
    solve(A, 0, vector<pair<int, int>>());
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