import java.util.*;

class Solution{
    
    public static double median(int arr1[],int arr2[]){
        int x = arr1.length;
        int y = arr2.length;
        int low = 0;
        int high = x;
        double result = 0;
        while(low<=high){
            int midX = (low+high)/2;
            int midY = (x+y+1)/2 - midX;
            int aLeftMax = (midX==0)?Integer.MIN_VALUE:arr1[midX-1];
            int bLeftMax = (midY==0)?Integer.MIN_VALUE:arr2[midY-1];
            int aRightMin = (midX==x)?Integer.MAX_VALUE:arr1[midX];
            int bRightMin = (midY==y)?Integer.MAX_VALUE:arr2[midY];
            if(aLeftMax<=bRightMin && bLeftMax<=aRightMin){
                if((x+y)%2==0){
                    result = (Math.max(aLeftMax,bLeftMax)+Math.min(aRightMin,bRightMin))/2.0;
                }
                else{
                    result = Math.max(aLeftMax,bLeftMax);
                }
                break;
            }
            else if(aLeftMax > bRightMin){
                high = midX - 1;
            }
            else{
                low = midX + 1;
            }
        }
        return result;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr1[] = new int[m];
        int arr2[] = new int[n];
        for(int i=0;i<m;i++){
            arr1[i] = sc.nextInt();
        }
        for(int j=0;j<n;j++){
            arr2[j] = sc.nextInt();
        }
        if(arr1.length<arr2.length){
            System.out.println(median(arr1,arr2));
        }
        else{
            System.out.println(median(arr2,arr1));
        }
    }
}