#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	/*
	��� �ȿ� � �ֻ����� �������� ����� ����. ���� �ѿ����� ���̴� �ֻ����� �߿��ϴ�.
	�׸��� Ź�ڷ� ���� ���� �Ʒ��� �ִ� ���� ������ ������ ��������.

	�ֻ����� ������ �ִ� ���� �򰥸��� �ϴ� �ֻ��� 1���� �ִٰ� ������ �غ���.
	�׸��� �׳� �Ϲ����� �ֻ��� 1 2 3 4 5 6 ���� �̷���� �ִٰ� ������ �غ���.
	�׷� �ֻ����� ����� �Ʒ��� ���� ���̴�.
	  4
	5 1 2 6
	  3
	�׷� �������� ���� 5���� ���ڸ� �̴� ���ϰ��̴�. �׷� ���� �ּ��ϱ�? ������ �ֻ����� ȸ����Ű�鼭 Ȯ���غ��� ������
	��� 6�� Ź�ڿ� �úٰ� �ϴ� ���� ���� �ּ����� �˰��̴�. �׷� �ֻ����� Ȯ���غ���
	NxNxN �� ������ü���� �Ѹ鸸 ������ ���� �׷� ���⿡�� �ּҰ� �ǰ� �Ϸ��� ��� �ؾ��ұ�? �׵θ� �κ��� �����ϰ� �Ѹ��� ��� �ֻ����� ���� ���� ����
	�����ϸ� �ȴ�. Ź�ڿ� ��� ���� �ش��� ���� ������ ������ 5���� �鿡 ���ؼ��� �ش��� �ȴ�. �׷� �׵θ��� �Ǵ� �κ��� ��� ó���ؾ� �ұ�?
	���� ���������� 3���� �鸸 ���δٰ� �����ϴ� ���� ���� �� ����. ��Ȯ����
	EAD, EAC, EDF, EFC, ADB, ACB, BDF, BFC �� ���ϰ��̴�. ������ �κ��� �� ���� ���̹Ƿ� 4���� �������� ���ؼ��� ������ ���� �ּҰ� �ǵ��� �ϴ°����� ���ϴ� ���� ����.
	������ �׵θ��� ���ؼ��� 2���� �鿡 ���ؼ��� ���� ���̴�. ��
	AB, AC, AD, AE, BC, BD, BF, CE, CF, DE, DF, EF �� ���ϰ��̴�. �� �̵� �� �ּ��� �͸� ��ġ�ϴ� ���� ���� �̵��ϰ��̴�.
	*/
	unsigned long long int N;
	cin >> N;
	unsigned long long int A, B, C, D, E, F;
	cin >> A >> B >> C >> D >> E >> F;
	unsigned long long int oneMin = A;
	oneMin = min(oneMin, B);
	oneMin = min(oneMin, C);
	oneMin = min(oneMin, D);
	oneMin = min(oneMin, E);
	oneMin = min(oneMin, F);
	unsigned long long int twoMin = A + B;
	twoMin = min(twoMin, A + C);
	twoMin = min(twoMin, A + D);
	twoMin = min(twoMin, A + E);
	twoMin = min(twoMin, B + C);
	twoMin = min(twoMin, B + D);
	twoMin = min(twoMin, B + F);
	twoMin = min(twoMin, C + E);
	twoMin = min(twoMin, C + F);
	twoMin = min(twoMin, D + E);
	twoMin = min(twoMin, D + F);
	twoMin = min(twoMin, E + F);
	unsigned long long int threeMin = E + A + D;
	threeMin = min(threeMin, E + A + D);
	threeMin = min(threeMin, E + A + C);
	threeMin = min(threeMin, E + D + F);
	threeMin = min(threeMin, E + F + C);
	threeMin = min(threeMin, A + D + B);
	threeMin = min(threeMin, A + C + B);
	threeMin = min(threeMin, B + D + F);
	threeMin = min(threeMin, B + F + C);
	if (N == 1) {
		unsigned long long int sum = A + B + C + D + E + F;
		unsigned long long int maxVal = A;
		maxVal = max(maxVal, B);
		maxVal = max(maxVal, C);
		maxVal = max(maxVal, D);
		maxVal = max(maxVal, E);
		maxVal = max(maxVal, F);
		cout << sum - maxVal << endl;
	}
	else {
		unsigned long long int sum = 0;
		sum += threeMin * 4;
		sum += twoMin * (N - 1) * 4 + twoMin * (N - 2) * 4;
		sum += oneMin * (N - 2) * (N - 2) * 5 + oneMin * (N - 2) * 4;
		cout << sum << endl;
	}
	return 0;
}