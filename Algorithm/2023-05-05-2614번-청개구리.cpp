#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> ans;

void findSolutions(vector<int>& arr, int sum, int index) {
    // ��� ���Ҹ� ������ ���
    if (index == arr.size()) {
        // ������ �����ϴ� ��� ����� �߰�
        if (sum >= 0 && sum <= 100) {
            ans.push_back(arr);
        }
        return;
    }

    // ���� �ε������� ������ ���� ���ϰ� ��� ȣ��
    for (int i = 0; i <= sum; i++) {
        arr[index] = i;
        findSolutions(arr, sum - i, index + 1);
    }
}

int main() {
    vector<int> arr(21, 0);
    findSolutions(arr, 100, 0);

    // ��� ���
    cout << "�� ����� ��: " << ans.size() << endl;
    for (int i = 0; i < ans.size(); i++) {
        cout << i + 1 << "��°: ";
        for (int j = 0; j < ans[i].size(); j++) {
            cout << ans[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}