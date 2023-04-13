#include <iostream>
using namespace std;

int main()
{
    int A, B;
    cin >> A >> B;
    /*
    �迭�� 1, 2, 2, 3, 3, 3, 4, 4, 4, 4 �� ���� ǥ���� �� ���̴�.
    �̶� A�� 3, B�� 7�̶�� �غ���
    �׷��� aNumber �� 3��°�� ����Ű�� ���ڸ� �ǹ��ϹǷ� 2
    bNumber �� 7��°�� ����Ű�� ���ڸ� �ǹ��ϹǷ� 4 ���ȴ�.

    ���� number �� aNumber, bNumber �� ���ϱ� ���� �ӽú����̴�.
    pos �� �迭�� 1 based index �̴�. (0 based index �� �ƴԿ� ����)

    aDistance �� A�� 2��� �����ϸ� 1, 2, 2 ���� A �� ù��° `2`�� ����Ű�Ƿ� 2�� �ȴ�.
    A�� 3�̶�� �����ϸ� 1, 2, 2 ���� A �� �ι�° `2`�� ����Ű�Ƿ� 1�� �ȴ�.
    �� aDistance �� (A�� ����Ű�� ���ڰ� �迭���� ���� �������� ���� �� �� �ε���)-(A�� ����Ű�� ���� �ε���) + 1 �̴�.

    bDistance �� B �� 7 �̶�� �����ϸ� B �� ù��° `4` �� ����Ű�Ƿ� 1�̵ȴ�.
    B �� 8�̶�� �����ϸ� B �� �ι�° `4`�� ����Ű�Ƿ� 2�� �ȴ�.
    �� bDistance �� (B�� ����Ű�� ���� �ε���) - (B�� ����Ű�� ���ڰ� �迭���� ���� ó���� ���� �� �� �ε���) + 1 �̴�.
    */
    int aNumber = -1;
    int bNumber = -1;
    int number = 1;
    int pos = 1;
    int aDistance = -1;
    int bDistance = -1;

    while (true) {
        if (aNumber == -1 && pos >= A) {
            aNumber = number;
            aDistance = pos - A + 1;
        }
        if (bNumber == -1 && pos >= B) {
            bNumber = number;
            bDistance = B - (pos - number);
            break;
        }
        number++;
        pos += number;
    }
    int sol = 0;
    if (aNumber == bNumber) {
        sol = aNumber * (B - A + 1);
    }
    else {
        sol += aNumber * aDistance;
        sol += bNumber * bDistance;
        for (int i = aNumber + 1; i < bNumber; i++) {
            sol += i * i;
        }
    }
    cout << sol;

}