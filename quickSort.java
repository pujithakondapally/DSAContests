import java.util.*;

class Solution{
    public static void swap(int arr[],int i,int k){
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }
    
    public static void quickSort(int arr[],int low,int high){
        if(low<high){
            int pivot_index = partition(arr,low,high);
            quickSort(arr,low,pivot_index-1);
            quickSort(arr,pivot_index+1,high);
        }
    }
    
    public static int partition(int arr[],int low,int high){
        int pivot_index = high;
        int pivot = arr[high];
        int k = low;
        for(int i=low;i<high;i++){
            if(arr[i]<pivot){
                swap(arr,i,k);
                k++;
            }
        }
        swap(arr,k,pivot_index);
        return k;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        quickSort(arr,0,n-1);
        for(int i=0;i<n;i++){
            System.out.print(arr[i] + " ");
        }
    }
}