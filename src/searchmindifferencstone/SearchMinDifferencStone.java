/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchmindifferencstone;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.*;


/**
 *
 * @author Константин
 */
public class SearchMinDifferencStone {
    
    public static boolean[] ArrayKeysFirstArray;
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        int n=0;
        int i;
        int b = 0;
        int SummArray = 0;
        boolean BoolRandomEntry=true;
        int RandomEntry;
        boolean NeedChooce=true;
        Scanner scanner = new Scanner(System.in);
        final Random random = new Random();
        System.out.println("Select the type of input values.");
        System.out.println("Enter 0 if you want to enter the values ​​yourself.");
        System.out.println("Enter 1 if you want to random values.");
        while(NeedChooce){
            RandomEntry = scanner.nextInt();
            switch(RandomEntry){
                case 0: {BoolRandomEntry=false;NeedChooce=false;};break;
                case 1: {BoolRandomEntry=true;NeedChooce=false;};break;
                default: System.out.println("Please select the type of input values.");
            }
        }
        boolean FirstTry = true;
        while(n<1||n>20){
            if(FirstTry){
                System.out.println("Enter the number of stones from 1 to 20");
                FirstTry = false;
            }else{
                System.out.println("Error! Please enter value from 1 to 20.");
            }
            try{
                if(!BoolRandomEntry){
                    n = scanner.nextInt();
                }else{
                    n = random.nextInt(20);
                    System.out.println(n);
                }       
                //n=6;
            } catch(Exception e){
                scanner.nextLine();
            }
        }  

        if(BoolRandomEntry){
            System.out.println("Array weight stones:");
        }
        int[] ArrayStones;
        boolean FirstInput = true;
        ArrayStones = new int[n];
        for(i=0;i<n;i++){
            while(ArrayStones[i]<1){
                if(!BoolRandomEntry){
                    FirstTry = true;
                    while(ArrayStones[i]<1||ArrayStones[i]>100000){
                        if(FirstTry&&FirstInput){
                            System.out.println("Enter the number of stones from 1 to 100000.");
                            FirstTry=false;
                            FirstInput=false;
                        }else{
                            System.out.println("Error! Please enter value from 1 to 100000.");
                        }
                        try{
                            ArrayStones[i] = scanner.nextInt();
                            SummArray+=ArrayStones[i];
                            scanner.nextLine();      
                            //n=6;
                        } catch(Exception e){
                            scanner.nextLine();
                        }
                    }
                }else{
                    ArrayStones[i] = random.nextInt(100000);
                    SummArray+=ArrayStones[i];
                    System.out.print(ArrayStones[i]+" ");
                }
            }
        }
        if(!BoolRandomEntry){
            System.out.println("Array weight stones:");
            for(i=0;i<n;i++){
                System.out.print(ArrayStones[i]+" ");
            }  
        }
        System.out.println("\nSumm array:" + SummArray);
        System.out.println("\nSort array weight stones:");
        SortArray(ArrayStones);
        for(i=0;i<n;i++){
            System.out.print(ArrayStones[i]+" ");
        }
        
        long startTime = System.currentTimeMillis();
        int MinDifferent = BalancedPatrition(ArrayStones,SummArray);
        int halfsumcloser =(SummArray-MinDifferent)/2;
        boolean[] ArrayKey;
        ArrayKey = new boolean[n];
        boolean[] Flag={false};
        /*Значения которые я использовал для теста. Сумма 222071 = 97255 + 69520+ 55296
        int[] ArrayStonesStandart={52117, 55296, 69520, 82205, 93857, 97255};
        int halfsumcloserStandart = 222071;*/ 
        isSubsetSum(ArrayStones,n,halfsumcloser,
               ArrayKey,Flag);
        ArrayKey = SearchMinDifferencStone.ArrayKeysFirstArray.clone();
        int nInFirstArray=0;
        for(boolean FlagArray : ArrayKey){
            if(FlagArray){
                nInFirstArray++;
            }
        }
        
        int[] FirstArray;
        FirstArray = new int[nInFirstArray];
        int iForFirstArray=0;
        int iForSecondArray=0;
        int[] SecondArray;
        SecondArray = new int[n-nInFirstArray];
        for(i=0;i<n;i++){
           if(ArrayKey[i]){
               FirstArray[iForFirstArray] = ArrayStones[i];
               iForFirstArray++;
           }else{
               SecondArray[iForSecondArray] = ArrayStones[i];
               iForSecondArray++;
           }
        }
        int SummFirstArray=0;
        int SummSecondArray=0;
        System.out.println("\nFirst array stones:");
        for(i=0;i<FirstArray.length;i++){
            SummFirstArray+=FirstArray[i];
            System.out.print(FirstArray[i]+" ");
        }
        System.out.println("\nSumm first array: "+SummFirstArray);
        
        System.out.println("\nSecond array stones:");
        for(i=0;i<SecondArray.length;i++){
            SummSecondArray+=SecondArray[i];
            System.out.print(SecondArray[i]+" ");
        }
        System.out.println("\nSumm second array: "+SummSecondArray);
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Time algoritm in ms: "+timeSpent);
    }
    
    //
    public static void Recurs(int N,int[] arr){
        for(int i=0; i<=arr.length;i++){
            
        }
    }
    //Sort array stones
    public static int[] SortArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int min_i = i; 
            
            //Search min element array
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
            
            //Swap elements array
            if (i != min_i) {
                int tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
        return arr;
    }
    
    public static void isSubsetSum(int[] arr, int n, int sum,
            boolean[] ArrayKey,boolean[] Flag)
    {
        boolean[] StartArrayKey;
        StartArrayKey = ArrayKey.clone();
        int ThisRealSum;
        for (int k=n-1;k>=0;k--) 
        {
            sum=sum-arr[k];
            if(sum==0){
                Flag[0]=true;
                ArrayKey[k] = true;
                SearchMinDifferencStone.ArrayKeysFirstArray = ArrayKey.clone();
                return;
            }else if(sum>0){
                ArrayKey[k] = true;
                isSubsetSum(arr,k,sum,ArrayKey,Flag);
                if(Flag[0]==true){
                    return;
                }else{
                    ArrayKey = StartArrayKey.clone();
                    sum+=arr[k];
                    Flag[0] = false;
                }
            }else{
                Flag[0]=false;
                sum+=arr[k];
            }
        }
    }
    
    //Search min different
    public static int BalancedPatrition(int[] arr,int SummArray){
        boolean [] sol = new boolean [SummArray / 2 + 1];
         
        sol [0] = true;//empty array
        for (int k : arr) 
        {
            for (int j = SummArray / 2; j >= k; j--) 
            {
                if (sol [j - k]) sol [j] = true;
            }
        }
        
        int halfsumcloser = SummArray / 2;
        for (; !sol [halfsumcloser]; halfsumcloser--);
        int MinDifferent = (SummArray - halfsumcloser) - halfsumcloser;
        System.out.println ("\nThe minimum difference after dividing the array:" +((SummArray - halfsumcloser) - halfsumcloser));
        return MinDifferent;
    }
   
}
       
        // TODO code application logic here
    
    

