package com.xiaoxiang.tool.ds.sort;


/**
 * 冒泡排序
 *
 * @author xiang.xiaox
 */

public class BubbleSort {

    public static <T extends Comparable> void swap(T[] array, int one, int two) {
        T temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }


    /**
     * 真正意义的冒泡排序，效率也比较高
     * 以数组长度10个为例，第一次需要比较9次，第二次8次，以此类推，
     * 每次比较之后，数组最右边的数据时有序的了。
     * 所以时间复杂度为9+8+7。。。+1=N*(N-1)/2
     * 算法作约，复杂度为N*N/2，几位O(N*N);
     * 注意外层循环的out>1,可以减少一次多余的循环。
     *
     * @param array
     * @param order desc从大到小 asc从小到大
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T[] sort(T[] array, String order) {
        if (order == null || !"desc".equalsIgnoreCase(order)) { //升序
            for (int out = array.length - 1; out > 1; out--) {
                for (int in = 0; in < out; in++) {
                    if (array[in].compareTo(array[in + 1]) > 0) {
                        swap(array, in, in + 1);
                    }
                }
            }
        } else {//降序
            for(int out=0;out<array.length-1;out++){
              for(int in=array.length-1;in>out;in--){
                  if (array[in].compareTo(array[in - 1]) > 0) {
                      swap(array, in, in - 1);
                  }
              }
            }
        }
        return array;
    }


} 