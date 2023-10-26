#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	/*
	사실 안에 어떤 주사위가 들어가는지는 상관이 없다. 그저 겉에서만 보이는 주사위가 중요하다.
	그리고 탁자로 인해 가장 아래에 있는 면은 보이지 않음에 주의하자.

	주사위가 여러개 있는 것은 헷갈리니 일단 주사위 1개만 있다고 가정을 해보자.
	그리고 그냥 일반적인 주사위 1 2 3 4 5 6 으로 이루어져 있다고 가정을 해보자.
	그럼 주사위의 모양은 아래와 같을 것이다.
	  4
	5 1 2 6
	  3
	그럼 합해지는 수는 5개의 숫자를 뽑는 것일것이다. 그럼 뭐가 최소일까? 일일이 주사위를 회전시키면서 확인해봐도 되지만
	사실 6을 탁자와 맡붙게 하는 것이 가장 최소임을 알것이다. 그럼 주사위를 확장해보자
	NxNxN 의 정육면체에서 한면만 보도록 하자 그럼 여기에서 최소가 되게 하려면 어떻게 해야할까? 테두리 부분을 제외하고 한면을 모두 주사위의 가장 작은 수로
	도배하면 된다. 탁자에 닿는 면은 해당이 되지 않지만 나머지 5개의 면에 대해서는 해당이 된다. 그럼 테두리가 되는 부분은 어떻게 처리해야 할까?
	위에 꼭지점에는 3개의 면만 보인다고 생각하는 것이 좋을 것 같다. 정확히는
	EAD, EAC, EDF, EFC, ADB, ACB, BDF, BFC 만 보일것이다. 나머지 부분은 안 보일 것이므로 4개의 꼭지점에 대해서는 저들의 합이 최소가 되도록 하는것으로 구하는 것이 좋다.
	나머지 테두리에 대해서는 2개의 면에 대해서만 보일 것이다. 즉
	AB, AC, AD, AE, BC, BD, BF, CE, CF, DE, DF, EF 만 보일것이다. 즉 이들 중 최소인 것만 배치하는 것이 제일 이득일것이다.
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