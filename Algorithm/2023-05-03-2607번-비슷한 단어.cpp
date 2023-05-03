#include <iostream>
#include <algorithm>
#include <set>
using namespace std;

int main() {
	int T;
	cin >> T;
	string origin;
	cin >> origin;
	sort(origin.begin(), origin.end());
	T--;
	int cnt = 0;
	while (T--) {
		string target;
		cin >> target;
		sort(target.begin(), target.end());
		if (origin == target) {
			// 같은 구성이라면
			cnt++;
		}
		else {
			// 다른 구성이라면
			// 한 단어에서 한 문자를 더하거나, 빼거나, 하나의 문자를 다른 문자로 바꾸어 나머지 한 단어와 같은 구성
			// 인지 따져봐야 한다.
			// 여기에서는 중복을 허용하는 multiset 을 이용했다.
			multiset<char> originSet;
			multiset<char> targetSet;
			for (int i = 0; i < origin.size(); i++) {
				originSet.insert(origin[i]);
			}
			for (int i = 0; i < target.size(); i++) {
				targetSet.insert(target[i]);
			}
			multiset<char> result;
			set_difference(
				make_move_iterator(originSet.begin()),
				make_move_iterator(originSet.end()),
				targetSet.begin(), targetSet.end(),
				inserter(result, result.begin())
			);
			multiset<char> result2;
			set_difference(
				make_move_iterator(targetSet.begin()),
				make_move_iterator(targetSet.end()),
				originSet.begin(), originSet.end(),
				inserter(result2, result2.begin())
			);
			if (result.size() + result2.size() == 1) {
				// 한문자를 더하거나 또는 한 문자를 뺄 때 같은 구성을 가지게 된다면
				cnt++;
			}
			else if (result.size() == 1 && result2.size() == 1) {
				// 한 문자를 바꿀 때 같은 구성을 가지게 된다면
				cnt++;
			}
		}
	}
	cout << cnt << endl;
	return 0;
}