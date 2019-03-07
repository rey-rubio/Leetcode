import java.util.*;


class CreditCard {

    private User user;
    private String bank;
    private String accountNumber;
    private Calendar expirationDate;
    private String cvcCode;
    private double balance;
    private double limit;
    private Stack<Transaction> transactionsStack;

    public CreditCard(User user, String b, String an, Calendar exp, String cvc, double bal, double l){
        this.user = user;
        this.bank = b;
        this.accountNumber = an;
        this.expirationDate = exp;
        this.cvcCode = cvc;
        this.balance = bal;
        this.limit = l;
        this.transactionsStack = new Stack<>();
    }

    public User getUser() {
        return user;
    }

    public String getBank() {
        return bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getExpirationDate() {
        return expirationDate.toString();
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public double getBalance() {
        return balance;
    }

    public double getLimit() {
        return limit;
    }

    public Stack<Transaction> getTransactionsStack(){return transactionsStack;}

    public boolean charge(double amount){

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Transaction t = new Transaction(Transaction.TYPE_CHARGE, amount, cal);
        addTransaction(t);

        return true;
    }

    public boolean payment(double amount){

        Date date = new Date();
        date.setMonth(date.getMonth());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Transaction t = new Transaction(Transaction.TYPE_PAYMENT, amount, cal);
        addTransaction(t);

        return true;
    }



    public void addTransaction(Transaction t){
        transactionsStack.push(t);
    }

    public Transaction seeMostRecentTransaction(){
        if(this.transactionsStack.size() > 0)
            return transactionsStack.peek();
        else{
            System.out.println("There are no transactions!");
            return null;
        }
    }

    public boolean processTransaction(Transaction t){
        Date date = new Date();
        date.setMonth(date.getMonth() - 6);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(t.getDate().getTime().before(date)){
            System.out.println("Transaction is too old");
            return false;
        }
        else if(t.getType() == Transaction.TYPE_PAYMENT){
            if(this.balance - t.getAmount() >= 0){
                this.balance -= t.getAmount();
                return true;
            }
            else{
                System.out.println("Your payment is too large");
                return false;
            }

        }
        else if(t.getType() == Transaction.TYPE_CHARGE){
            if(this.balance + t.getAmount() < this.limit){
                this.balance += t.getAmount();
                return true;
            }
            else{
                System.out.println("You are over your credit limit");
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void printInfo(){
        System.out.println("User = " + this.user.printName());
        System.out.println("Bank = " + this.bank);
        System.out.println("Account Number = " + this.accountNumber);
        System.out.println("Expiration Date = " + this.expirationDate.getTime());
        System.out.println("CVC = " + this.cvcCode);
        System.out.println("Balance = " + this.balance);
        System.out.println("Limit = " + this.limit);

        for(int i = 0; i < transactionsStack.size(); i++)
            System.out.println(transactionsStack.get(i));

        System.out.println();
    }

    public void process(){
        Iterator<Transaction> iter = getTransactionsStack().iterator();
        while(iter.hasNext()){
            Transaction t = iter.next();
            if(processTransaction(t)){
                System.out.println("Transaction Successfully Processed: " + t.toString());
                iter.remove();
                printInfo();
            }
            else {
                System.out.println("Transaction DID NOT Process: " + t.toString());
                printInfo();
            }
            System.out.println();
        }

    }
}

class Transaction {

    public static String TYPE_CHARGE = "Charge";
    public static String TYPE_PAYMENT = "Payment";

    private String type;
    private double amount;
    private Calendar date;

    public Transaction(String type, double amount, Calendar date){
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public String toString(){
        return "Type: " + type + ", Amount: " + amount + ", Date: " + date.getTime();
    }
}

class User {
    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private List<CreditCard> cards;

    public User(String fn, String ln, String email, String phoneNumber){
        this.firstName = fn;
        this.lastName = ln;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cards = new ArrayList<CreditCard>();
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void addCard(CreditCard cc){
        cards.add(cc);
    }

    public void printCards(){
        for(int i = 0; i < cards.size(); i++){
            cards.get(i).printInfo();
        }
    }

    public String printName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}


class CreditCardSystem{

    public static void main(String[] args){

        User u1 = new User("Reynerio", "Rubio", "reynerio.r.rubio@gmail.com", "347-684-1703");
        User u2 = new User ("Pamela", "Decolongon", "pamela.decolongon@gmail.com", "917-291-0806");

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2022, 1, 1);

        CreditCard cc1 = new CreditCard(u1, "Capital One", "1234 5678 9012 3456" , cal1, "111", 0, 1500 );
        CreditCard cc2 = new CreditCard(u1, "Capital One", "6198 6849 3218 3841" , cal1, "222", 0, 2000 );
        CreditCard cc3 = new CreditCard(u1, "Capital One", "1324 5835 5132 9125" , cal1, "262", 0, 5000 );

        u1.addCard(cc1);
        u1.addCard(cc2);
        u1.addCard(cc3);

        u1.printCards();

        cc1.charge(200);
        cc1.charge(350);
        cc1.charge(100);
        cc1.payment(50);
        cc1.payment(150);
        cc1.payment(175);
        cc1.charge(500);
        cc1.printInfo();


        cc1.process();

        CreditCard cc4 = new CreditCard(u2, "Chase", "1234 5678 9012 3456" , cal1, "111", 0, 15000 );
        CreditCard cc5 = new CreditCard(u2, "Capital One", "6198 6849 3218 3841" , cal1, "222", 0, 2000 );
        u2.addCard(cc4);
        u2.addCard(cc5);
        u2.printCards();

        for(int i = 0; i < 10; i++){
            cc4.charge(i * 100 +50);
        }
        for(int i = 0; i < 10; i++){
            cc4.payment(i * 100 +50);
        }

        cc4.printInfo();
        cc4.process();
    }
}

