import java.util.*;

class Solution{
    public static int fixedPoint(int arr[],int low,int high){
        if(low>high){
            return -1;
        }
        int mid = (low+high)/2;
        if(mid==arr[mid]){
            int left = fixedPoint(arr,low,mid-1);
            return (left!=-1)?left:mid;
        }
        else if(mid>arr[mid]){
            return fixedPoint(arr,mid+1,high);
        }
        else{
            return fixedPoint(arr,low,mid-1);
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(fixedPoint(arr,0,n-1));
    }
}