package com.zhuyifan.util;

public class Rotate {
	public static void main(String[] args) {
		fun(10);
		name1(7);
	}

	private static void fun(int N) {
		int DEFAULT = -1;
		int MIN_VALUE = 0;
		int MAX_VALUE = 9;

		int[][] array = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array[i][j] = DEFAULT;
			}
		}

		int row = 0;
		int column = 0;
		int value = 0;
		int type = 1;

		for (int i = 0; i < N * N; i++) {
			// 赋值
			array[row][column] = value;

			if (i == N * N - 1) {
				break;
			}

			// 增值
			value = (value == MAX_VALUE) ? MIN_VALUE : value + 1;

			a: while (true) {
				switch (type) {
				case 1:
					if (column + 1 < N && array[row][column + 1] == DEFAULT) {
						column++;
						type = 1;
						break a;
					}
				case 2:
					if (row + 1 < N && array[row + 1][column] == DEFAULT) {
						row++;
						type = 2;
						break a;
					}
				case 3:
					if (column - 1 >= 0 && array[row][column - 1] == DEFAULT) {
						column--;
						type = 3;
						break a;
					}
				case 4:
					if (row - 1 >= 0 && array[row - 1][column] == DEFAULT) {
						row--;
						type = 4;
						break a;
					}
				default:
					type = 1;
				}
			}

		}

		// 打印结果
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}



	public static void name1(int n) {
		int[][] a = new int[n][n];
		a[0][0] = 0;
		int m;
		for (int i = 0; i <= (n + 1) / 2; i++) {
			m = n - i - 1;
			// i行
			for (int j = i + 1; j < n - i; j++) {
				a[i][j] = a[i][j - 1] + 1;
			}
			// m列
			for (int j = i + 1; j <= m; j++) {
				a[j][m] = a[j - 1][m] + 1;
			}
			// m行
			for (int j = m - 1; j > i - 1; j--) {
				a[m][j] = a[m][j + 1] + 1;
			}
			// i列
			for (int j = m - 1; j > i; j--) {
				a[j][i] = a[j + 1][i] + 1;
			}
			if (i != (n + 1) / 2) {
				a[i + 1][i + 1] = a[i + 1][i] + 1;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((a[i][j]) % 10 + " ");
			}
			System.out.println();
		}
	}
}