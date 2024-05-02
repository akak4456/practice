package org.example;
//백준 제출시에는 위에 package 는 제외한 나머지를 복사할 것
// package 까지 코드에 포함하면 런타임에러가 뜨면서 제대로 채점이 안된다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int y = Integer.parseInt(br.readLine());
        System.out.println(y - 543);
        /*
        공백으로 한 글자씩 구분되는 입력의 경우 StringTokenizer로 공백을 제외(대상을 " " 로)하고 입력받을 수 있다.
        int [] targetArray = new int[100];
        st = new StringTokenizer(sc.readLine()," ");
        for(int i = 0 ; i < M ; i++){
            targetArray[i] = Integer.parseInt(st.nextToken());
        }
         */
        Boolean b = Boolean.FALSE;
    }
}