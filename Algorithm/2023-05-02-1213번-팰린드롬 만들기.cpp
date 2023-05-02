/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

using namespace std;

int cnt[26];

int main() {
    /*
    �Ӹ������ ���� 2���� �� �ϳ��̴�.
    1) ��� ���ĺ��� ¦���� �ִ�.
    2) ���ĺ��� Ȧ���� �ִ� ���� ���� 1�� �ְ� ������ ���ĺ��� ��� ¦���� �ִ�.
    �̸� �̿��ؼ� isCan �� �����Ͽ����� ���������� �ռ��� ���� ���� ����ؾ� �ϱ⿡
    ������ 'A' ���� �������� �Ͽ���.
    */
    string str;
    cin >> str;

    for (int i = 0; i < str.size(); i++) {
        cnt[str[i] - 'A']++;
    }
    bool isCan = true;
    char ch = '0';
    for (int i = 0; i < 26; i++) {
        if (cnt[i] % 2 != 0) {
            if (ch == '0') {
                ch = 'A' + i;
            }
            else {
                isCan = false;
                break;
            }
        }
    }
    if (!isCan) {
        cout << "I'm Sorry Hansoo" << endl;
    }
    else {
        string answerFirst = "";
        string answerLast = "";
        for (int i = 0; i < 26; i++) {
            if (i == ch - 'A') {
                for (int j = 0; j < cnt[i] - 1; j += 2) {
                    answerFirst += 'A' + i;
                    answerLast = (char)('A' + i) + answerLast;
                }
            }
            else {
                for (int j = 0; j < cnt[i]; j += 2) {
                    answerFirst += 'A' + i;
                    answerLast = (char)('A' + i) + answerLast;
                }
            }
        }
        if (ch != '0') {
            cout << answerFirst + ch + answerLast << endl;
        }
        else {
            cout << answerFirst + answerLast << endl;
        }
    }

    return 0;
}
