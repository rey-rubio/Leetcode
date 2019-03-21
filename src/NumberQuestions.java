import java.util.HashMap;
import java.util.Map;

public class NumberQuestions {

    private int[] twoSum(int[] nums, int target)
    {
        Map<Integer, Integer>  numsMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++){

            if(numsMap.containsKey(target - nums[i]))
                return new int[] {numsMap.get(target - nums[i]), i};
            else
                numsMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No Two Sum Soltion");
    }


    private int USDtoEuro(double usd){
        final double[] EURO_BILLS = {500, 200, 100, 50, 20, 10, 5, 2, 1, .50, .25, .05, .02, .01};
        double euroAmount = usd * 0.89;
        int quantity = 0;
        System.out.println(String.format("USD amount: %.2f --> Euro amount: %.2f", usd, euroAmount));
        for(int i = 0; i < EURO_BILLS.length; i++)
        {
            while(euroAmount - EURO_BILLS[i] >= 0){
                System.out.print("Using: ");
                System.out.println(String.format( "%.2f", EURO_BILLS[i] ));
                euroAmount -= EURO_BILLS[i];
                quantity++;
            }
        }

        return quantity;
    }


    public int maxProfit(int[] prices) {

        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i = 0 ; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }

        return max;
    }

    private void printArray(int[] arr){
        for(int i: arr){
            System.out.print(i + " ");
        }
        System.out.println();

    }

    public static void main(String[] args){
        NumberQuestions test = new NumberQuestions();



        System.out.println("__________________________________________________________________\n");
        System.out.println("USDtoEuro(double usd)");
        double usdAmount = 1234.71;
        int euroAmount = test.USDtoEuro(usdAmount);
        System.out.println(euroAmount);



        System.out.println("__________________________________________________________________\n");
        System.out.println("twoSum(int[] arr, int target)");
        int[] arr = new int[] {12, 234, 65, 23, 765, 24,654,75, 21};
        int target = 33;
        System.out.println(String.format("target = %d", target));
        test.printArray(arr);
        test.printArray(test.twoSum(arr, target));

        System.out.println("__________________________________________________________________\n");
        System.out.println("maxProfit(int[] prices)");
        int[] prices = new int[] {7,1,5,3,6,4,15,45,134,61,43,1234};
        test.printArray(prices);
        System.out.println("Max Profit: " + test.maxProfit(prices));
    }

}
