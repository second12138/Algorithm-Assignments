/*CSCI 3110 - Assignemnt 1 - Q2
Li, Yansong B00755354 Wang, Le B00761974 2020-5-15*/
public class A1_Q2 {
	
	//main method that finds out the maximum-sum subarray
	public static void main(String[] args) {
		int [] list = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		System.out.println(maxSubArray(list));
	}
	
	//I add this stupid method just because LeetCode won't accepted a function with three parameters, so I have to change it to one
	public static int maxSubArray(int[] array) {
		return FindMax(0,array.length-1,array);
	}
	
	//this method finds out the the maximum-sum of the first half array, the second half array, and the mid part array
	//then compare them to find out the real maximum
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
	
	//this method finds out the maximum-sum when it is ate the mid part array
	public static int FindMidMax(int low,int mid,int high,int[] array){
		// find maximum-sum subarray from low to mid;
        int left_sum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i=mid; i>=low; i--){
            sum += array[i];
            if (sum > left_sum)
            {
                left_sum = sum;
            }
        }
     // find maximum-sum subarray from mid to high;
        int right_sum = Integer.MIN_VALUE;
        sum = 0;
        for (int j=mid+1; j<=high; j++){
            sum += array[j];
            if (sum > right_sum)
            {
                right_sum = sum;
            }
        }
        return left_sum+right_sum;
	}
}