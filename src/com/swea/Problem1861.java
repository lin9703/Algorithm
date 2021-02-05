package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @since Feb 5, 2021
 * @author lin9703
 * @problem SWEA 1861번 정사각형 방
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc&categoryId=AV5LtJYKDzsDFAXc&categoryType=CODE&problemTitle=1861&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 * @mem 34,372
 * @time 160
 * @caution BFS 이용 
 */
public class Problem1861 {
	static int N;
	static int[][] room;
	static boolean[][] isVisited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			room = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			isVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(isVisited[i], false);
			}

			int maxNum = -1;
			int maxRoom = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isVisited[i][j]) {
						int temp = findNumPassingManyRooms(room, i, j);

						if (maxRoom < temp) {
							maxRoom = temp;
							maxNum = room[i][j];
						} else if (maxRoom == temp) {
							maxNum = Math.min(room[i][j], maxNum);
						}
					}
				}
			}

			// print
			sb.append("#").append(t).append(" ").append(maxNum).append(" ").append(maxRoom).append("\n");
		}

		System.out.println(sb);
	}

	static class Point {
		int x;
		int y;
		int roomCount;

		public Point(int x, int y, int roomCount) {
			super();
			this.x = x;
			this.y = y;
			this.roomCount = roomCount;
		}

	}

	private static int findNumPassingManyRooms(int[][] room, int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y, 1));
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				} else if (room[nx][ny] == room[p.x][p.y] + 1) {
					queue.add(new Point(nx, ny, p.roomCount + 1));
					isVisited[nx][ny] = true;
				}
			}

			if (queue.isEmpty()) {
				return p.roomCount;
			}
		}

		return 0;
	}

}
