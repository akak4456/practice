#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <string>

using namespace std;
int N;
long long int rentmm;
long long int F;
/*
key.first: �뿩��
key.second: �뿩�� ��ǰ
value: �뿩�Ͻ� 2021�� 1�� 1�� 0�� 0�� ������ �ð���...
*/
map<pair<string, string>, int> m;
// ���ڿ� ������ ��¥�� �ð��� �������� �Ľ��ϴ� �Լ�
map<string, long long int> result;

int date[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

int minutesSince2021(string date1, string date2) {
	int MM = stoi(date1.substr(5, 2));
	int dd = stoi(date1.substr(8, 2));
	int hh = stoi(date2.substr(0, 2));
	int mm = stoi(date2.substr(3, 2));

	int duration = mm;
	duration += hh * 60;
	duration += (dd - 1) * 60 * 24;
	for (int i = 0; i < MM - 1; i++) {
		duration += date[i] * 60 * 24;
	}
    return duration;
}
int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	string rentdate;
	cin >> rentdate;
	rentmm = stoi(rentdate.substr(0, 3)) * 60 * 24 + stoi(rentdate.substr(4, 2)) * 60 +stoi(rentdate.substr(7, 2));
	cin >> F;
	for (int i = 0; i < N; i++) {
		string date1;
		string date2;
		string renter;
		string thing;
		cin >> date1 >> date2 >> thing >> renter;
		auto iter = m.find({ renter, thing });
		if (iter == m.end()) {
			// ����
            m.insert({ {renter, thing},minutesSince2021(date1, date2) });
		}
		else {
			// �ݳ�
            long long int diff = minutesSince2021(date1, date2) - iter->second;
            if (diff > rentmm) {
				string later = iter->first.first;
				auto iter2 = result.find(later);
				if (iter2 == result.end()) {
					result.insert({ iter->first.first, (diff - rentmm) * F });
				}
				else {
					result[later] = result[later] + (diff - rentmm) * F;
				}
            }
            m.erase(iter->first);
		}
	}
    if (result.empty()) {
        cout << "-1\n";
    }
    else {
        for (const auto& pair : result) {
            cout << pair.first << " " << pair.second << "\n";
        }
    }
	return 0;
}