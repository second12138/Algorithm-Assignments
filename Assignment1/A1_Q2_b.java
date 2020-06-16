/*CSCI 3110 - Assignemnt 1 - Q2
Li, Yansong B00755354 Wang, Le B00761974 2020-5-15*/
public class A1_Q2_b {
	
	//main method
	public static void main(String[] args) {
		int [] list = {5,-3,5};
		System.out.println(maxSubarraySumCircular(list));
	}
	
	//new method that finds out the maximum-sum subarray in a circular array
	public static int maxSubarraySumCircular(int[] A) {
        int total = 0, maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
        maxSum = FindMax(0, A.length-1, A);
        for (int k = 0; k < A.length; k++) {
        	int a = A[k];
            total += a;
        }
        for (int i=0; i<A.length; i++) {
        	A[i] = A[i]*(-1);
        }
        minSum = FindMax(0, A.length-1, A)*(-1);
        if (maxSum > total - minSum) {
        	return maxSum;
        }else if ((total - minSum > maxSum)&&(total-minSum == 0)) {
        	return maxSum;
        }else {
        	return total - minSum;
        }
    }
	
	//below are the two method that finds out the maximum-sum subarray from part a
	public static int FindMax(int low,int high,int[] array){
		if(low == high) 
			return array[low];
		int mid = (low + high)/2;
		int firstHalfMax = FindMax(low, mid, array);
		int secondHalfMax = FindMax(mid+1, high, array);
		int midMax = FindMidMax(low, mid, high, array);
		//compare
		if (firstHalfMax >= secondHalfMax && firstHalfMax >= midMax){
             return firstHalfMax;
        }else if (secondHalfMax >= firstHalfMax && secondHalfMax >= midMax){
             return secondHalfMax;
        }else{
             return midMax;
        }
	}
	
	//thie method finds out the maximum-sum when it is ate the mid part array
	public static int FindMidMax(int low,int mid,int high,int[] array){
		// find maximum-sum subarray from low to mid;
        int left_sum = Integer.MIN_VALUE;
        //int max_left = mid;
        int sum = 0;
        for (int i=mid; i>=low; i--){
            sum += array[i];
            if (sum > left_sum)
            {
                left_sum = sum;
                //max_left = i;
            }
        }
        // find maximum-sum subarray from mid to high;
        int right_sum = Integer.MIN_VALUE;
        //int max_right = mid + 1;
        sum = 0;
        for (int j=mid+1; j<=high; j++){
            sum += array[j];
            if (sum > right_sum)
            {
                right_sum = sum;
                //max_right = j;
            }
        }
        return left_sum+right_sum;
	}
}
