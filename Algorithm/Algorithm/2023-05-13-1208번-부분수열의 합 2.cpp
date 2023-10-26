#include <iostream>
#include <vector>
#include <cstring>
#include <string>
#include <algorithm>
#include <set>
#include <map>
using namespace std;
int arr[40];
int N, S;
long long int A[8000001];
long long int B[8000001];
void getMap(int prevsum, long long int used, int start, int end, bool isAMap) {
	for (int i = start; i <= end; i++) {
		if ((used & ((long long int)1 << i)) == 0) {
			if (isAMap) {
				A[prevsum + arr[i] + 4000000]++;
			}
			else {
				B[prevsum + arr[i] + 4000000]++;
			}
			getMap(prevsum + arr[i], used + ((long long int)1 << i), i + 1, end, isAMap);
		}
	}
}
int main() {
	/*
	a b c d e 수열이 있다고 하자.
	일단 무지성으로 부분집합을 구하도록 하자. (공집합은 제외)
	{a}, {b}, {c}, {d}, {e},
	{a, b}, {a, c}, {a, d}, {a, e}, {b, c}, {b, d}, {b, e}, {c, d}, {c, e}, {d, e},
	{a, b, c}, {a, b, d}, {a, b, e}, {a, c, d}, {a, c, e}, {a, d, e}, {b, c, d}, {b, c, e}, {b, d, e}, {c, d, e},
	{a, b, c, d}, {a, b, c, e}, {a, b, d, e}, {a, c, d, e}, {b, c, d, e}, {a, b, c, d, e}
	총 31개 있다. 다시 말해 5개가 주어질 때 합이 S 가 될 가능성은 최대 31개 있다는 것이다.
	N개가 주어질 때 합이 S 가 될 가능성은 최대 2^N - 1 개 있다. 그러나 문제는 N = 40 일때로 이와 같이 부분집합을 이용해서
	구하면 시간초과가 뜸은 당연하다. 근데 여기에서 한 번 생각을 해보자. {a, b}의 합들이 만약 S라고 해보자. 그럼 만약에 {c, d} 의 합이
	0이라면 {a, b, c, d} 의 합도 당연히 S가 되는 것이다. 그럼 이번엔 {a, b, c, d, e} 에 대해서 생각해보자. 근데 만약에 이들의 합이 S보다
	크다고 가정해보자. 근데 일단 a, b, c, d, e 중에 하나를 빼서 S가 된다고 해보자. 예컨대 a를 뺀다고 하면 {b, c, d, e} 는 S일 것이다.
	그리고 만약에 {d, e}의 합들이 0이라고 한다면 {b, c} 도 역시 마찬가지로 S일 것이다. 그럼 반대로 {a, b, c, d, e} 의 합들이 S 라고 해보자.
	그럼 {a, b, c, d, e} 의 부분집합 중에서 합이 0이 되는 것이 있다면 {a, b, c, d, e} 에서 그 부분집합을 빼도 합들이 S가 됨은 당연하다
	한편 여기에서 a <= b <= c <= d <= e 와 같이 수열을 정렬할 수도 있다. 그리고 절반을 쪼개보자. 예컨대 {a, b, c}, {d, e} 와 같이 쪼갰다고 해보자.
	근데 여기에서 예컨대 {a, c} 가 S보다는 작다고 해보자. 그럼 {a, c, d} 를 고려하고 {a, c, e} 를 고려하고 {a, c, d, e} 를 고려하면 되지 않을까?
	여기에서 S가 나올수도 있기 마련이다. 그러니까 저기에서 절반을 나누면 2^20 을 두번 고려하게 되는 것이다. 이건 충분히 시간복잡도 내에 갈 것 같기도 하다.

	40 0
	100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000
	에 반례가 있다. 정답 : 137846528819 이어야 한다.
	*/
	cin.tie(NULL);
	cout.tie(NULL);
	std::ios::sync_with_stdio(false);
	cin >> N >> S;
	for (int i = 0; i < N; i++)
		cin >> arr[i];
	getMap(0, 0, 0, N / 2, true);
	getMap(0, 0, N / 2 + 1, N - 1, false);
	long long int result = 0;
	result += A[S + 4000000];
	result += B[S + 4000000];
	for (int i = 0; i <= 8000000; i++) {
		int remainS = S - (i - 4000000);
		int properBIdx = remainS + 4000000;
		if (properBIdx >= 0 && properBIdx <= 8000000) {
			result += A[i] * B[properBIdx];
		}
	}
	cout << result << "\n";
	return 0;
}