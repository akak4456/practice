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
			// ���� �����̶��
			cnt++;
		}
		else {
			// �ٸ� �����̶��
			// �� �ܾ�� �� ���ڸ� ���ϰų�, ���ų�, �ϳ��� ���ڸ� �ٸ� ���ڷ� �ٲپ� ������ �� �ܾ�� ���� ����
			// ���� �������� �Ѵ�.
			// ���⿡���� �ߺ��� ����ϴ� multiset �� �̿��ߴ�.
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
				// �ѹ��ڸ� ���ϰų� �Ǵ� �� ���ڸ� �� �� ���� ������ ������ �ȴٸ�
				cnt++;
			}
			else if (result.size() == 1 && result2.size() == 1) {
				// �� ���ڸ� �ٲ� �� ���� ������ ������ �ȴٸ�
				cnt++;
			}
		}
	}
	cout << cnt << endl;
	return 0;
}