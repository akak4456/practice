#include <iostream>

#include <string>

using namespace std;

unsigned long long int cnt[10];

unsigned long long int arr[2];

unsigned long long int solution = 0;
int main() {

    cin >> arr[0] >> arr[1];
    if (arr[0] > 0)
        arr[0]--;
    for (int a = 1; a >= 0; a--) {
        for (int t = 0; t <= 9; t++) {
            cnt[t] = 0;
        }
        string str = to_string(arr[a]);
        string str2 = "";

        for (unsigned long long int i = 0; i < str.size(); i++) {

            if (i > 0) {

                str2[i - 1] = str[i - 1];

            }

            if (str[i] == '0') str2 += '0';

            for (unsigned long long int p = 0; p < str[i] - '0'; p++) {



                string add = "";

                string sub = "";

                add += (str.size() - i - 1) + '0';

                if (str.size() > i + 2) {

                    for (unsigned long long int t = 0; t < str.size() - i - 2; t++) {

                        add += '0';

                    }

                }

                if (str.size() > i + 1) {

                    for (unsigned long long int t = 0; t < str.size() - i - 1; t++) {

                        sub += '1';

                    }

                }

                if (i < str.size() - 1) {

                    unsigned long long int addVal = stoi(add);

                    unsigned long long int subVal = stoi(sub);

                    for (unsigned long long int t = 0; t <= 9; t++) {

                        cnt[t] += addVal;

                        if (t == 0) {

                            cnt[0] -= subVal;

                        }

                    }

                }



                if (str2.size() < i + 1) {

                    str2 += p + '0';

                }
                else {

                    str2[i] = p + '0';

                }



                if (str2.size() > 1 || str2[0] != '0') {

                    unsigned long long int beforeM = 0;

                    unsigned long long int m = 1;

                    unsigned long long int dis = str.size() - str2.size() + 1;

                    for (unsigned long long int t = 0; t < dis; t++) {

                        for (unsigned long long int k = 0; k < str2.size(); k++) {

                            cnt[str2[k] - '0'] += m - beforeM;

                        }

                        cnt[0] += (m - beforeM) * (dis - t - 1);

                        beforeM = m;

                        m *= 10;

                    }

                }

            }

        }



        for (unsigned long long int i = 0; i < str.size(); i++) {

            cnt[str[i] - '0']++;

        }
        for (unsigned long long int u = 0; u <= 9; u++) {
            if (a == 0) {
                solution -= cnt[u] * u;
            }
            else {
                solution += cnt[u] * u;
            }

        }
    }
    cout << solution << endl;

}