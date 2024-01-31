class Program{
 
    public static void main(String[] args) {

        int res;
        int number;
        int temp;

        res = 0;
        number = 479598;

        temp = number % 10;
        res += temp;
        number /= 10;
        

        temp = number % 10;
        res += temp;
        number /= 10;


        temp = number % 10;
        res += temp;
        number /= 10;

        temp = number % 10;
        res += temp;
        number /= 10;

        temp = number % 10;
        res += temp;
        number /= 10;
        
        temp = number % 10;
        res += temp;
        number /= 10;

        System.out.println(res);
    }

}
