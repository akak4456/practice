#include <iostream>
using namespace std;

int main()
{
    int A, B;
    cin >> A >> B;
    /*
    배열은 1, 2, 2, 3, 3, 3, 4, 4, 4, 4 와 같이 표현이 될 것이다.
    이때 A가 3, B가 7이라고 해보자
    그러면 aNumber 는 3번째가 가리키는 숫자를 의미하므로 2
    bNumber 는 7번째가 가리키는 숫자를 의미하므로 4 가된다.

    한편 number 는 aNumber, bNumber 를 구하기 위한 임시변수이다.
    pos 는 배열의 1 based index 이다. (0 based index 가 아님에 주의)

    aDistance 는 A가 2라고 가정하면 1, 2, 2 에서 A 는 첫번째 `2`를 가리키므로 2가 된다.
    A가 3이라고 가정하면 1, 2, 2 에서 A 는 두번째 `2`를 가리키므로 1이 된다.
    즉 aDistance 는 (A가 가리키는 숫자가 배열에서 가장 마지막에 있을 때 그 인덱스)-(A가 가리키는 숫자 인덱스) + 1 이다.

    bDistance 는 B 가 7 이라고 가정하면 B 는 첫번째 `4` 를 가리키므로 1이된다.
    B 가 8이라고 가정하면 B 는 두번째 `4`를 가리키므로 2가 된다.
    즉 bDistance 는 (B가 가리키는 숫자 인덱스) - (B가 가리키는 숫자가 배열에서 가장 처음에 있을 때 그 인덱스) + 1 이다.
    */
    int aNumber = -1;
    int bNumber = -1;
    int number = 1;
    int pos = 1;
    int aDistance = -1;
    int bDistance = -1;

    while (true) {
        if (aNumber == -1 && pos >= A) {
            aNumber = number;
            aDistance = pos - A + 1;
        }
        if (bNumber == -1 && pos >= B) {
            bNumber = number;
            bDistance = B - (pos - number);
            break;
        }
        number++;
        pos += number;
    }
    int sol = 0;
    if (aNumber == bNumber) {
        sol = aNumber * (B - A + 1);
    }
    else {
        sol += aNumber * aDistance;
        sol += bNumber * bDistance;
        for (int i = aNumber + 1; i < bNumber; i++) {
            sol += i * i;
        }
    }
    cout << sol;

}