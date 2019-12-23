package com.wfm.serviceactiv;

import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * description: demo
 * date: 2019-10-24 18:40
 * author: wfm
 * version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class demo {
    @Test
    public void test(){
        //获取所有的属性
        Properties properties = System.getProperties();
        //遍历所有的属性
        for (String key : properties.stringPropertyNames()) {
            //输出对应的键和值
            System.out.println(key + "=" + properties.getProperty(key));
        }

    }

    /**
     * 快速排序：首先是最简单的Arrays.sort
     */
    @Test
    public void arraySort() {
        Integer[] arry = {-1, -5, 0, 8, -3, 10};

        Integer[] arry_sq = new Integer[arry.length];

        for (int i = 0; i < arry.length; i++) {
            arry_sq[i] = arry[i] * arry[i];
        }


        /**
         * 一。直接Arrays.sort排序大小（快速排序）
         */
        //Arrays.sort(arry_sq);
        //System.out.println(Arrays.toString(arry_sq));

        /**
         * 从大到小
         */
        /*Arrays.sort(arry_sq, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });*/

        Arrays.sort(arry_sq,(a,b)->{ return b-a;});
        System.out.println(Arrays.toString(arry_sq));





    }

    /**
     * 冒泡排序：从下到上，两两进行比较，越大的越往后，从而形成由小到大的排序
     */
    @Test
    public void bubbleSort(){
        Integer[] arry = {-1, -5, 0, 8, -3, 10};

        Integer[] arry_sq = new Integer[arry.length];

        for (int i = 0; i < arry.length; i++) {
            arry_sq[i] = arry[i] * arry[i];
        }

        System.out.println("冒泡排序优化后...");
        int t = 0;
        for (int i = 0; i <arry.length ; i++) {

            // 若为 true，则表示此次循环没有进行交换，即待排序列已经有序，排序已然完成
            boolean success = true;
            for (int j = 0; j < arry.length - 1 - i; j++) {
                if (arry[j] > arry[j + 1]) {
                    //交换两个数组的位置,
                    //就是每次一轮找出最大值，放到最后的一个数据
                    t = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = t;
                    success = false;
                }
            }

            if (success) {
                break;
            }

            System.out.println("第" + (i + 1) + "轮后: " + Arrays.toString(arry));
        }

        //打印按钮的最大值
        System.out.println(Arrays.toString(arry));
    }

    /**
     * 选择排序
     */
    @Test
    public void selectSort(){

        Integer[] arry = {49,38,65,97,76,13,27,49};
        Integer tem=0;

        for (int i = 0; i < arry.length-1; i++) {

            int index_min=i;
            // 找出最小值得元素下标
            for (int j = i+1; j <arry.length ; j++) {
                if(arry[j]<arry[index_min]){
                    index_min=j;
                }
            }

            //交换顺序，获得前面的数组是最小的值
            tem=arry[index_min];
            arry[index_min]=arry[i];
            arry[i]=tem;

            System.out.println(Arrays.toString(arry));

        }
    }


    /**
     * 快速排序 给基准数据找其正确索引位置的过程
     * ①先从队尾开始向前扫描且当low < high时,如果a[high] > tmp,则high–,但如果a[high] < tmp,则将high的值赋值给low,即arr[low] = a[high],同时要转换数组扫描的方式,即需要从队首开始向队尾进行扫描了
     * ②同理,当从队首开始向队尾进行扫描时,如果a[low] < tmp,则low++,但如果a[low] > tmp了,则就需要将low位置的值赋值给high位置,即arr[low] = arr[high],同时将数组扫描方式换为由队尾向队首进行扫描.
     * ③不断重复①和②,知道low>=high时(其实是low=high),low或high的位置就是该基准数据在数组中的正确索引位置.
     */

    @Test
    public void quickSortDemo(){
        int[] arry = {49,38,65,97,76,13,27,48};
        quickSort2(arry, 0, arry.length - 1);

        System.out.println(Arrays.toString(arry));

    }

    private static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndex(arr, low, high);

            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);

            System.out.println(Arrays.toString(arr));
        }

    }
    // 分治法快速排序
    private static int getIndex(int[] arr, int low, int high) {
        // 基准数据
        int tmp = arr[low];
        while (low < high) {
            // 1.从后往前比较
            while (low < high && arr[high] >= tmp) {
                // 如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            if (low < high) {
                arr[low] = arr[high];
            }

            // 2.从前往后比较
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            if (low < high) {
                arr[high] = arr[low];
            }

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low] = tmp;
        return low; // 返回tmp的正确位置
    }

    public static void quickSort2(int arr[], int low, int high) {
        int l = low; //基准
        int h = high;
        int k = arr[low];
        while (l < h) {
            // 从后往前比较
            while (l < h && arr[h] >= k) { // 如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                h--;// h=6
            }
            if (l < h) {
                //拿到右边比标准值小的元素
                int temp = arr[h];
                //填充元素位置为标准值
                arr[h] = arr[l];
                //标准值位置为右边小的元素
                arr[l] = temp;
                //进行过一次替换后，没必要将替换后的两值再次比较，所以i++直接下一位与k对比
                l++;
            }
            // 从前往后比较
            while (l < h && arr[l] <= k) { // 如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                l++;
            }
            if (l < h) {
                //拿到左边比标准值大的元素
                int temp = arr[h];
                //填充元素位置为标准值
                arr[h] = arr[l];
                //标准值位置为左边大的元素
                arr[l] = temp;
                h--;
            }
            // 此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        System.out.println(Arrays.toString(arr));
        System.out.print("l=" + (l + 1) + ",h=" + (h + 1) + ",k=" + k + "\n");
        // 递归
        //l=h为标准值的位置
        if (l > low)//先判断l>low再次经行左边排序
            quickSort2(arr, low, l - 1);// 左边序列。第一个索引位置到关键值索引-1
        if (h < high)//左边依次排序执行完递归后，弹栈进行右边排序
            quickSort2(arr, l + 1, high);// 右边序列。从关键值索引+1到最后一个
    }
}
