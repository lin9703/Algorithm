package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 2798번 블랙잭
https://www.acmicpc.net/problem/2798

- 풀이법: 브루트포스
- 시간복잡도: for문 3개 -> 최대 O(N 세제곱)
  ㄴ 브루트포스의 시간복잡도는 O(경우의 수 * 경우 1개의 시간복잡도)
  ㄴ 경우의 수: N*(N-1)*(N-2)/3 => O(N 세제곱)
  ㄴ 3 <= N <= 100 이므로 최대 1,000,000 연산
*/
public class Problem2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // number of card
        int M = Integer.parseInt(st.nextToken());

        int[] cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int p = j + 1; p < N; p++) {
                    int temp = cards[i] + cards[j] + cards[p];
                    if (temp <= M) {
                        result = Math.max(temp, result);
                    }
                }
            }
        }

        System.out.println(result);
    }
}
