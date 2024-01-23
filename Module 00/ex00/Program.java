class Program{
 
    public static void main(String[] args) {

        int number = 479598;
        int res = 0;
         res = getSum(number  , res);
        System.out.println(res);
    }

    private static int getSum(int number   , int res)
    {
        int sum;
        if (number == 0)
        {
            return res;
        }
        sum = number % 10;
        number /= 10;
        res += sum;
        return getSum(number  , res);
    }

}
