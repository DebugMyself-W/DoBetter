import java.util.*;

/**
 *
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeTest {
    public static int findRepeatNumber(int[] nums) {

        Map<Integer, Integer> exist = new HashMap();
        for (int num : nums) {
            if (!exist.containsKey(num)) {
                exist.put(num, 1);
            } else {
                exist.put(num, exist.get(num) + 1);
            }
        }
        List<Integer> list= new ArrayList();;
        for (int key : exist.keySet()) {
            if(exist.get(key)>1){
                list.add(key);
            }
        }
        if(list.size()>0){
            Collections.shuffle(list);
            System.out.println(list.get(list.size()-1));
            return list.get(list.size()-1);
        }else{
            System.out.println(-1);
            return -1;
        }
    }


    public static int findRepeatNumber2(int[] nums) {

        Map<Integer, Integer> exist = new HashMap();
        List<Integer> list= new ArrayList();;
        for (int num : nums) {
            if (!exist.containsKey(num)) {
                exist.put(num, 1);
            } else {
                list.add(num);
            }
        }
        if(list.size()>0){
            Collections.shuffle(list);
            System.out.println(list.get(list.size()-1));
            return list.get(list.size()-1);
        }else{
            System.out.println(-1);
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        findRepeatNumber2(nums);
    }
}
