package neu.kaishen.connecteddevices.labs.module07;

import java.util.HashMap;
import java.util.Map;

class test {
	public int[] intersect(int[] nums1, int[] nums2) {
    	Map<Integer,Integer> map = new HashMap<>();
        int n1=nums1.length,n2=nums2.length;
        int ans[] = new int[Math.min(n1,n2)];
        for(int i=0;i<n1;i++){
            map.put(nums1[i],i);
        }
         
        for(int i=0;i<n2;i++){
            int a=0;
            if(map.containsKey(nums2[i])) ans[a++] = nums2[i];
            System.out.println(nums2[i]);
        }

        
        return ans;
	}
	
    public static void main(String[] args) {
    	int[] nums1 = {1,2,2,1};
    	int[] nums2 = {2,2};
    	test t = new test();
    	t.intersect(nums1, nums2);

    }
}
