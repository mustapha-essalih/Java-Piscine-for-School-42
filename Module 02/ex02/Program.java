
public class Program {

    public static void main(String[] args) {
        
        if (args.length == 1) 
        {
            Shell shell = new Shell();
            shell.executeCommands(args[0]);

        }
        else
        {
            System.out.println("enter current directory");
        }
    }
}

 
        