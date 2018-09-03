package com.surcreak;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySort implements IPlay{

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input 10 number.");
        int [] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = scanner.nextInt();
        }
        scanner.close();

        //binary1(a, 0, 9);
        int [] result = new int[a.length];
//        binarySort(a, result, 0, a.length-1);

        binarySort2(a);

        System.out.println(Arrays.toString(a));
    }

    private void binarySort2(int[] a) {
        int lenth = a.length;
        int[] result = new int[a.length];
        for (int block=1; block<=lenth; block*=2) {
            for (int start=0; start<lenth; start += 2*block) {
                int low = start;
                int mid = (start + block) > lenth ? lenth : start + block;
                int hight = (start + 2*block) > lenth ? lenth : start + 2*block;

                int start1 = start, end1 = mid;
                int start2 = mid + 1, end2 = hight;

                int k = low;
                while (start1<=end1 && start2<=end2) {
                    result[k++] = a[start1] < a[start2] ? a[start1++] : a[start2++];
                }
            }
        }
    }

    //wiki
    private void binarySort(int[] a, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end-start, mid = start + (len >> 1);
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        int k = start;
        binarySort(a, result, start1, end1);
        binarySort(a, result, start2, end2);
        while (start1<=end1 && start2<=end2) {
            result[k++] = a[start1] < a[start2] ? a[start1++] : a[start2++];
        }
        while (start1 <= end1) {
            result[k++] = a[start1++];
        }
        while (start2 <= end2) {
            result[k++] = a[start2++];
        }
        for (int i=start; i<=end; i++) {
            a[i] = result[i];
        }
    }

    //自己写的
    private int[] binary1(int[] a, int p, int q) {
        int[] temp = new int[q-p+1];
        if ((q - p) < 2) {
            if (a[p] > a[q]) {
                swap(a, p, q);
            }
        } else {
            int m = (p+q)/2;
            combine(a, p, m, q);
        }
        for (int i = 0; i < temp.length; i++) {
            temp[i] = a[p+i];
        }
        return  temp;
    }

    private void combine(int[] a, int p, int m, int q) {
        int [] temp1 = binary1(a, p, m);
        int [] temp2 = binary1(a, m+1, q);
        int inx1 = 0;
        int inx2 = 0;
        for (int i = p; i <= q; i++) {
            if (inx1 >= temp1.length) {
                a[i] = temp2[inx2];
                inx2++;
                continue;
            }
            if (inx2 >= temp2.length) {
                a[i] = temp1[inx1];
                inx1++;
                continue;
            }
            if (temp1[inx1] <= temp2[inx2]) {
                a[i] = temp1[inx1];
                inx1++;
            } else {
                a[i] = temp2[inx2];
                inx2++;
            }
        }
    }

    private void swap(int[] a, int p, int q) {
        a[p] = a[p] + a[q];
        a[q] = a[p] - a[q];
        a[p] = a[p] - a[q];
    }

}
