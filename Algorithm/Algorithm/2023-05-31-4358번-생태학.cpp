#include <iostream>
#include <map>

using namespace std;

map<string, int> m;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string str;
    int cnt = 0;
    while (true) {
        string str;
        getline(cin, str);
        if (cin.eof()) break;
        auto it = m.find(str);
        if (it != m.end()) {
            it->second = it->second + 1;
        }
        else {
            m.insert({ str, 1 });
        }
        cnt++;
    }
    auto it = m.begin();
    while (it != m.end()) {
        cout << fixed;
        cout.precision(4);
        cout << it->first << " " << (float(it->second) / cnt) * 100 << endl;
        it++;
    }

    return 0;
}