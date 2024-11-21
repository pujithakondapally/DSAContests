import java.util.*;

class Solution{
    public static int count(int arr[],int num,int left,int right){
        int c = 0;
        for(int i=left;i<=right;i++){
            if(arr[i]==num){
                c++;
            }
        }
        return c;
    }
    public static int majorityElement(int arr[],int low,int high){
        if(low==high){
            return arr[low];
        }
        int mid = (low+high)/2;
        int left = majorityElement(arr,low,mid);
        int right = majorityElement(arr,mid+1,high);
        
        if(left == right){
            return left;
        }
        
        int leftCount = count(arr,left,low,mid);
        int rightCount = count(arr,right,mid+1,high);
        return leftCount > rightCount ? left : right;
    }
    
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(majorityElement(arr,0,n-1));
    }
}