// Minimum Size Subarray Sum t: O(n)
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i = 0;
        int j = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;
        
        while (j < n) {
            sum += nums[j];
            while (sum >= s) {
                len = Math.min(len, j - i + 1);
                sum -= nums[i++];
            }
            j++;
        }
        return len == Integer.MAX_VALUE?0:len;
    }
}

/ prefix sum + binary searhc t: O(nlgn)
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i = 0;
        int j = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;
        
        int[] ps = new int[n + 1];
        ps[0] = 0;
        for (int k = 1; k <= n; k++) {
            ps[k] = ps[k - 1] + nums[k - 1];
        }
        
        for (int k = 0; k < n; k++) {
            int lo = k + 1;
            int hi = n;
            while (lo + 1 < hi) { // find the first position where sum >= s
                int mid = lo + (hi - lo)/2;
                if (ps[mid] - ps[k] >= s) { 
                    len = Math.min(len, mid - k);
                    hi = mid;
                }
                else lo = mid;
            }
            if (ps[lo] - ps[k] >= s) len = Math.min(len, lo - k); // if lo is the first position
            else if (ps[hi]- ps[k] >= s) len = Math.min(len, hi - k); // if hi is the first position
            // if they all smaller than s, not find the first position, go to next loop
        }
        return len == Integer.MAX_VALUE?0:len; // in case of search miss
    }
}
