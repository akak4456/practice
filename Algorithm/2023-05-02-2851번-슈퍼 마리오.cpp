/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>
#include <algorithm>
#include <cstdlib>
using namespace std;


int main() {
    int sum = 0;
    int solution = 0;
    for (int i = 0; i < 10; i++) {
        int t;
        cin >> t;
        sum += t;
        if (abs(sum - 100) < abs(solution - 100) || (abs(sum - 100) == abs(solution - 100) && sum > solution)) {
            solution = sum;
        }
    }
    cout << solution << endl;

    return 0;
}