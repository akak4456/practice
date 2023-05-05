#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> ans;

void findSolutions(vector<int>& arr, int sum, int index) {
    // 모든 원소를 선택한 경우
    if (index == arr.size()) {
        // 조건을 만족하는 경우 결과에 추가
        if (sum >= 0 && sum <= 100) {
            ans.push_back(arr);
        }
        return;
    }

    // 현재 인덱스에서 가능한 값을 구하고 재귀 호출
    for (int i = 0; i <= sum; i++) {
        arr[index] = i;
        findSolutions(arr, sum - i, index + 1);
    }
}

int main() {
    vector<int> arr(21, 0);
    findSolutions(arr, 100, 0);

    // 결과 출력
    cout << "총 경우의 수: " << ans.size() << endl;
    for (int i = 0; i < ans.size(); i++) {
        cout << i + 1 << "번째: ";
        for (int j = 0; j < ans[i].size(); j++) {
            cout << ans[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}