/*** InnerProgram
 */

 


public class Program {

    public static void main(String[] args) {
        
        
        if(args.length == 1 && args[0].equals("--profile=production"))
        {
            Menu menu = new Menu(false);
            menu.callApp();
        } 
        else if(args.length == 1 && args[0].equals("--profile=dev"))
        {
            Menu menu = new Menu(true);
            menu.callApp();

        }
        else
        {
            System.out.println("should enter dev or production mode.");
        }
            
    }
}

 