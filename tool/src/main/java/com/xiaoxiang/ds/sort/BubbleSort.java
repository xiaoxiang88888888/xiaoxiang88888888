package com.xiaoxiang.ds.sort;


/**
 * 冒泡排序
 *
 * @author xiang.xiaox
 */

public class BubbleSort {  
      
    public void swap(int[] array ,int one,int two){  
        int temp = array[one];  
        array[one]=array[two];  
        array[two]=temp;  
    }  
  
    //1、冒泡排序  
    public int[] bubbleSort(int[] array){  
        //不是正在意义的冒泡排序,更像选择排序  
        for (int i = 0; i < array.length; i++) {  
            for (int j = 0; j < array.length; j++) {  
                if(array[i]<array[j]){  
                    swap(array,i,j);  
                }  
            }  
        }  
        /* 
         * 正在意义的冒泡排序，效率也比上面的要高 
         * 以数组长度10个为例，第一次需要比较9次，第二次8次，以此类推， 
         * 每次比较之后，数组最右边的数据时有序的了。 
         * 所以时间复杂度为9+8+7。。。+1=N*(N-1)/2 
         * 算法作约，复杂度为N*N/2，几位O(N*N); 
         */  
        //注意外层循环的out>1,可以减少一次多余的循环。  
        for(int out = array.length-1;out>1;out--){  
            for (int in = 0; in < out; in++) {  
                if(array[in]>array[in+1]){  
                    swap(array,in,in+1);  
                }  
            }  
        }  
        return array;  
    }  
      
     public static void main(String args[]){  
             int[] array = new int[20];  
             for (int i = 0; i < array.length; i++) {  
                array[i] = (int)(Math.random()*45);  
            }  
            BubbleSort sort = new BubbleSort();  
            sort.bubbleSort(array);  
            for (int i = 0; i < array.length; i++) {  
                System.out.println(array[i]);  
            }  
              
        }  
} 