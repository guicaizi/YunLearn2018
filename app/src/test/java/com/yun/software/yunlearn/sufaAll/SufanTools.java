package com.yun.software.yunlearn.sufaAll;

import android.util.Log;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by yanliang
 * on 2018/4/13 11:30
 */

public class SufanTools {
    //    1.冒泡排序(BubleSort)：(n2)从大到小一次比较  每次第一位必然最小 每次去掉首位 及就是i
    public static void BubbleSort(int[] arr) {
        long starttime = System.currentTimeMillis();
        int totalnumber = 0;
        int temp;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {   //每次去掉i以前的数做比较 然后后面数作比较 因为i以前数 必然最小值 开始为0
            for (int j = arr.length - 1; j > i; j--) {
                System.out.println("执行次数" + totalnumber++ + "---i=" + i + "----j=" + j);

                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                System.out.println("第" + totalnumber + "次执行结果" + Arrays.toString(arr));
            }
        }
        long stoptime = System.currentTimeMillis();
        long zongshijian = stoptime - starttime;
        System.out.println("总共耗时" + zongshijian);
    }

    //2选择排序 (先找最小值，将最小值复制给第一位 然后轮流)
    public static void xier(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {

            int minvalue = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minvalue]) {
                    minvalue = j;
                }
            }
            if (minvalue != i) {
                int temp = arr[minvalue];
                arr[minvalue] = arr[i];
                arr[i] = temp;
            }
        }
    }
    //插入排序 一次和后一位做比较 把小的放在左边大大的放在右边
    public static void  insert_sort(int[] array){
        int temp;
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(array[j] < array[j-1]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }else{
                    break;//不需要交换

                }
                System.out.println("第" +i + "次执行"+"循环结果" + Arrays.toString(array));
            }

            System.out.println("第" +i + "次执行结果" + Arrays.toString(array));

        }
    }
//4.希尔排序(Shell Sort)：(n1.5)在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
    public static void shell_sort(int array[]){
//        int arr[]={4,8,5,7,9,6,15,11,12} ;
        int temp = 0;
        int incre = array.length;

        while(true){

            incre = incre/2;
            System.out.println("incre的值为"+incre);
            for(int k = 0;k<incre;k++){    //根据增量分为若干子序列
                for(int i=k+incre;i<array.length;i+=incre){
                    for(int j=i;j>k;j-=incre){
                        if(array[j]<array[j-incre]){
                            temp = array[j-incre];
                            array[j-incre] = array[j];
                            array[j] = temp;
                        }else{
                            break;
                        }
                    }
                }
            }

            if(incre == 1){
                break;
            }
        }
    }
    
    @Test
    public void test05(){
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4; j++) {
                for (int k = 0; k <4 ; k++) {
                    if(k==3){
                        break;
                    }

                    System.out.println("k"+"---第"+k+"次终止");
                }
                if(j==4){
                    break;
                }
                System.out.println("j"+"---第"+j+"次终止");
            }
            if(i==4){
                break;
            }
            System.out.println("i"+"---第"+i+"次终止");
        }
        
        
        
    }

    public static void quickSort(int a[],int l,int r){
        if(l>=r)
            return;
        int i = l; int j = r; int key = a[l];//选择第一个数为key

        while(i<j){
            while(i<j && a[j]>=key)//从右向左找第一个小于key的值
                j--;
            if(i<j){
                a[i] = a[j];
                i++;
            }
            while(i<j && a[i]<key)//从左向右找第一个大于key的值
                i++;
            if(i<j){
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        quickSort(a, l, i-1);//递归调用
        quickSort(a, i+1, r);//递归调用
    }
    //6.归并排序(Merge Sort): (N*logN)归并排序是建立在归并操作上的一种有效的排序算法。
    // 该算法是采用分治法的一个非常典型的应用。通过先递归的分解数列，再合并数列就完成了归并排序。
    public static void merge_sort(int a[],int first,int last,int temp[]){

        if(first < last){
            int middle = (first + last)/2;
            merge_sort(a,first,middle,temp);//左半部分排好序
            merge_sort(a,middle+1,last,temp);//右半部分排好序
            mergeArray(a,first,middle,last,temp); //合并左右部分
        }
    }
    //合并 ：将两个序列a[first-middle],a[middle+1-end]合并
    public static void mergeArray(int a[],int first,int middle,int end,int temp[]){
        int i = first;
        int m = middle;
        int j = middle+1;
        int n = end;
        int k = 0;
        while(i<=m && j<=n){
            if(a[i] <= a[j]){
                temp[k] = a[i];
                k++;
                i++;
            }else{
                temp[k] = a[j];
                k++;
                j++;
            }
        }
        while(i<=m){
            temp[k] = a[i];
            k++;
            i++;
        }
        while(j<=n){
            temp[k] = a[j];

            k++;
            j++;
        }

        for(int ii=0;ii<k;ii++){
            a[first + ii] = temp[ii];
        }
    }

//7.堆排序(HeapSort): (N*logN)
    //构建最小堆
    public static void MakeMinHeap(int a[], int n){
        for(int i=(n-1)/2 ; i>=0 ; i--){
            MinHeapFixdown(a,i,n);
        }
    }
    //从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
    public static void MinHeapFixdown(int a[],int i,int n){
        int j = 2*i+1; //子节点
        int temp = 0;

        while(j<n){
            //在左右子节点中寻找最小的
            if(j+1<n && a[j+1]<a[j]){
                j++;
            }
            if(a[i] <= a[j])
                break;
            //较大节点下移
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i = j;
            j = 2*i+1;
        }
    }
    public static void MinHeap_Sort(int a[],int n){
        int temp = 0;
        MakeMinHeap(a,n);

        for(int i=n-1;i>0;i--){
            temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            MinHeapFixdown(a,0,i);

        }
    }
//8.基数排序(RadioSort): (d(n+r))BinSort想法非常简单，首先创建数组A[MaxValue]；
// 然后将每个数放到相应的位置上（例如17放在下标17的数组位置）；最后遍历数组，即为排序后的结果。
    public static void RadixSort(int A[],int temp[],int n,int k,int r,int cnt[]){
        //A:原数组
        //temp:临时数组
        //n:序列的数字个数
        //k:最大的位数2
        //r:基数10
        //cnt:存储bin[i]的个数

        for(int i=0 , rtok=1; i<k ; i++ ,rtok = rtok*r){

            //初始化
            for(int j=0;j<r;j++){
                cnt[j] = 0;
            }
            //计算每个箱子的数字个数
            for(int j=0;j<n;j++){
                cnt[(A[j]/rtok)%r]++;
            }
            //cnt[j]的个数修改为前j个箱子一共有几个数字
            for(int j=1;j<r;j++){
                cnt[j] = cnt[j-1] + cnt[j];
            }
            for(int j = n-1;j>=0;j--){      //重点理解
                cnt[(A[j]/rtok)%r]--;
                temp[cnt[(A[j]/rtok)%r]] = A[j];
            }
            for(int j=0;j<n;j++){
                A[j] = temp[j];
            }
        }
    }
    
}
