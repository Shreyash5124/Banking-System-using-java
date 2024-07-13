import java.io.*;
import java.lang.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Bank
{
  int dd,mm,yy; 
  String name,address,phone,type;
  float balance;
  Scanner sc = new Scanner (System.in);
  
  public int accno; 
  public void accOpen()
  {
      int ch,flag=0;
      int add=0,amm=0,ayy=0;
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date =new Date();
      String currdate=dateFormat.format(date);
      System.out.println(" Current Date:"+currdate);
      String temp="";
      
      for(int i=0;i<currdate.length();i++)
      {
          if(currdate.charAt(i)=='/'&&i==2)
          {
              add = Integer.parseInt(temp);
              temp="";
              continue;
            }
            if(currdate.charAt(i)=='/' && i==5)
            {
                amm=Integer.parseInt(temp);
                temp="";
                continue;
            }
            temp+=currdate.charAt(i);
        }
        ayy=Integer.parseInt(temp);
        System.out.println("Enter account number");
        accno=sc.nextInt();
        System.out.println("Select your account type...");
        System.out.println("1. Saving     2. Current");
        ch=sc.nextInt();
        
        switch(ch)
        {
            case 1:
            type="saving";
            break;
            
            case 2:
            type="current";
            break;
            
            default:
            System.out.println("Wrong choice entered");
        }
        System.out.println("Enter account holder name :");
        name=sc.next();
        System.out.println("Enter account holder address:");
        address=sc.next();
        System.out.println("Enter account holder phone no:");
        phone=sc.next();
        
        do
        {
            flag=0;
            System.out.println("Enter account opening date;");
            System.out.println("Enter date:");
            dd=sc.nextInt();
            System.out.println("Enter month:");
            mm=sc.nextInt();
            System.out.println("Enter year");
            yy=sc.nextInt();
            if(dd>add && mm==amm)
            {
                System.out.println("Date cannot be more than current date:");
                flag=1;
            }
            if(mm>amm||yy>ayy)
            {
                System.out.println("Date cannot be more than current date");
                flag=1;
            }
        }while(flag==1);
        
        do
        {
            flag=0;
            System.out.println("Enter account opening balance:");
            balance=sc.nextFloat();
            
            if(type.equals("saving")&& balance<500)
            {
                System.out.println("Minimum balance for saving account is Rs.500");
                flag=1;
            }
            if(type.equals("current")&& balance<5000)
            {
                System.out.println("Minimum value for current account is Rs.5000");
                flag=1;
            }
        }

        while(flag==1);
        System.out.println("New account has been created...");
    }
    
    public void deposit()
    
    {
        float amount;
        String dname;
        System.out.println("Enter depositer name ");
        dname = sc.next();
        System.out.println("Enter amount to be deposited: ");
        amount=sc.nextFloat();
        balance+=amount;
        System.out.println("Amount deposited successfully for account number:"+accno);
        System.out.println("Depositer name="+dname);
        System.out.println("New balance:"+balance);
    }
    
    public void withdraw()
    
    {
        float amount;
        String wname;
        float balance1;
        System.out.println("Enter withdrawer name");
        wname=sc.next();
        System.out.println("Enter amount you want to withdraw:");
        amount=sc.nextFloat();
        
        if(type.equals("saving"))
        {
            if(balance-amount>=500)
            {
            balance1=balance-amount;
            System.out.println("Withdrawl successfull for account number:"+accno);
            System.out.println("name="+wname);
            System.out.println("New balance:"+balance1);
        }
        else
        {
            System.out.println("Insufficient Balance....");
        }
    }
    else
    {
            if(balance-amount>=5000)
            {
                balance=amount;
                System.out.println("Withdraw successfull for account number:"+accno);
                System.out.println("New balance:"+balance);
            }
            else
            {
                System.out.println("Insufficient balance...");
            }
        }
    }
    
    public void balanceEnquiry()
    
    {
        
        System.out.println("Displying account detials...");
        System.out.println("Account number:"+accno);
        System.out.println("Account type:"+type);
        System.out.println("Account holder name:"+name);
        System.out.println("Account holder address:"+address);
        System.out.println("Account holder phone:"+phone);
        System.out.println("Account opening date:"+dd+"/"+mm+"/"+yy);   
        System.out.println("Account balance:"+balance);
    }
    
    public static void main (String args[])
    
    {
      int no,ch,accno,flag=0,count=0;
      String username,password;
      Scanner sc= new Scanner (System.in);
      System.out.println("Welcome to SBI Klhapur");
      System.out.println("Authentication required...");
      
      do
      {
          System.out.println("Enter username:");
          username=sc.next();
          System.out.println("Enter password:");
          password=sc.next();
          
          if(username.equals("admin") && password.equals("*****"))
          {
              System.out.println("Authentication successfull");
              System.out.println("Enter number of accounts:");
              no=sc.nextInt();
              Bank b[]=new Bank [no];
              
              for(int i=0;i<no;i++)
              {
                  b[i]=new Bank();
                  b[i].accOpen();
              }
              
              do
              {
                  flag=0;
                  
                  System.out.println("Select your choice...");
                  System.out.println("1. Deposit");
                  System.out.println("2. Withdraw");
                  System.out.println("3. Balance Enquiry");
                  System.out.println("4. Exit");
                 ch=sc.nextInt();
                 
                 switch(ch)
                 {
                    case 1:
                     System.out.println("Enter account number:");
                     accno=sc.nextInt();
                     for(int i=0;i<no;i++)
                     {
                     if(b[i].accno==accno)
                     {
                       b[i].deposit();
                       flag=1;
                       break;
                    }
                }
                
                if(flag==0)
                {
                    System.out.println("Account number not found...");
                }
                break;
                
                case 2:
                System.out.println("Enter account number:");
                accno=sc.nextInt();
                
                for(int i=0;i<no;i++)
                {
                    if(b[i].accno==accno)
                    {
                        b[i].withdraw();
                        flag=1;
                        break;
                    }
                }
                
                if(flag==0)
                {
                    System.out.println("Account number not found...");
                }
                break;
                
                case 3:
                System.out.println("Enter account number:");
                accno=sc.nextInt();
                
                for(int i=0;i<no;i++)
                {
                    if(b[i].accno==accno)
                    {
                        b[i].balanceEnquiry();
                        flag=1;
                        break;
                    }
                }
                
                if(flag==0)
                {
                    System.out.println("Account number not found...");
                }
                break;
                
                case 4:
                System.out.println("Thank you for banking with us");
                System.exit(0);
                
                default:
                System.out.println("Wrong choice");
            }
        }while(ch!=4);
    }
    
    else
    {
        count++;
        
        if(count>=5)
        {
            System.out.println("Maximum attempts overed...");
            System.exit(0);
        }
        
        System.out.println("Authentication failed...");
        System.out.println("Remaining attempts:"+(5-count));
    }
}while(count<=5);
}
}



