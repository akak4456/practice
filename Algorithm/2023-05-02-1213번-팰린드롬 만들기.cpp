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
    팰린드롬은 다음 2가지 중 하나이다.
    1) 모든 알파벳이 짝수개 있다.
    2) 알파벳이 홀수개 있는 것이 오직 1개 있고 나머지 알파벳은 모두 짝수개 있다.
    이를 이용해서 isCan 을 결정하였으며 사전순으로 앞서는 것을 먼저 출력해야 하기에
    무조건 'A' 먼저 따지도록 하였다.
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
