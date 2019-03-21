import java.util.*;

abstract class Card {

    private User user;
    private String bank;
    private String accountNumber;
    private Calendar expirationDate;
    private String cvcCode;
    private Stack<Transaction> transactionsStack;
    private double balance;

    Card(User user, String b, String an, Calendar exp, String cvc, double bal){
        this.user = user;
        this.bank = b;
        this.accountNumber = an;
        this.expirationDate = exp;
        this.cvcCode = cvc;
        this.balance = bal;
        this.transactionsStack = new Stack<>();
    }


    protected void addTransaction(Transaction t){
        transactionsStack.push(t);
    }


    protected User getUser() {
        return user;
    }

    protected String getBank() {
        return bank;
    }

    protected String getAccountNumber() {
        return accountNumber;
    }

    protected String getExpirationDate() {
        return expirationDate.toString();
    }

    protected String getCvcCode() {
        return cvcCode;
    }

    protected double getBalance() {
        return balance;
    }

    protected void setBalance(Double b){this.balance = b;}

    protected Stack<Transaction> getTransactionsStack() { return transactionsStack;}

    protected Transaction seeMostRecentTransaction(){
        if(transactionsStack.size() > 0)
            return transactionsStack.peek();
        else{
            System.out.println("There are no transactions!");
            return null;
        }
    }

    @Override
    public String toString(){
        System.out.println("User = " + this.user.printName());
        System.out.println("Bank = " + this.bank);
        System.out.println("Account Number = " + this.accountNumber);
        System.out.println("Expiration Date = " + this.expirationDate.getTime());
        System.out.println("CVC = " + this.cvcCode);
        System.out.println("Balance = " + this.balance);

        for(int i = 0; i < transactionsStack.size(); i++)
            System.out.println(transactionsStack.get(i));

        return null;
    }

    abstract protected boolean charge(double amount);
    abstract protected boolean processTransaction(Transaction t);
}

class CreditCard extends Card{

    private double limit;

    CreditCard(User user, String b, String an, Calendar exp, String cvc, double bal, double l){
        super(user, b, an, exp, cvc, bal);
        this.limit = l;
    }


    protected boolean payment(double amount){
        Transaction t = new Transaction(Transaction.TYPE_PAYMENT, amount);
        addTransaction(t);

        return true;
    }

    @Override
    public String toString(){
        System.out.println("Credit Card");
        super.toString();
        System.out.println("Limit = " + this.limit + "\n");

        return null;
    }

    @Override
    protected boolean charge(double amount){

        Transaction t = new Transaction(Transaction.TYPE_CHARGE, amount);
        addTransaction(t);

        return true;
    }

    @Override
    protected boolean processTransaction(Transaction t){
        Date date = new Date();
        date.setMonth(date.getMonth() - 6);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(t.getDate().getTime().before(date)){
            System.out.println("Transaction is too old");
            return false;
        }
        else if(t.getType() == Transaction.TYPE_PAYMENT){
            if(getBalance() - t.getAmount() >= 0){
                setBalance(getBalance() - t.getAmount());
                return true;
            }
            else{
                System.out.println("Your payment is too large");
                return false;
            }

        }
        else if(t.getType() == Transaction.TYPE_CHARGE){
            setBalance(getBalance() + t.getAmount());
            return true;

        }
        else{
            return false;
        }
    }
}

class DebitCard extends Card{


    DebitCard(User user, String b, String an, Calendar exp, String cvc, double bal){
        super(user, b, an, exp, cvc, bal);
    }


    @Override
    protected boolean charge(double amount) {
        return false;
    }

    @Override
    protected boolean processTransaction(Transaction t) {
        return false;
    }

    @Override
    public String toString(){
        System.out.println("Debit Card");
        super.toString();
        return null;
    }

}

class Transaction {

    public static String TYPE_CHARGE = "Charge";
    public static String TYPE_PAYMENT = "Payment";

    private String type;
    private double amount;
    private Calendar date;

    Transaction(String type, double amount){
        this.type = type;
        this.amount = amount;

        Date date = new Date();
        date.setMonth(date.getMonth());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.date = cal;
    }

    protected String getType() {
        return type;
    }

    protected double getAmount() {
        return amount;
    }

    protected Calendar getDate() {
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
    private ArrayList<Object> cards;

    public User(String fn, String ln, String email, String phoneNumber){
        this.firstName = fn;
        this.lastName = ln;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cards = new ArrayList<Object>();
    }

    protected String getFirstName(){
        return firstName;
    }

    protected String getLastName(){
        return lastName;
    }

    protected void addCard(Card cc){
        cards.add(cc);
    }

    protected void printCards(){
        for(Object obj: cards){
            obj.toString();
        }
    }

    protected String printName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}

class CreditCardSystem{

       protected void process(Card c){
        Iterator<Transaction> iter = c.getTransactionsStack().iterator();
        while(iter.hasNext()){
            Transaction t = iter.next();
            if(c.processTransaction(t)){
                System.out.println("Transaction Successfully Processed: " + t.toString());
                iter.remove();
                c.toString();
            }
            else {
                System.out.println("Transaction DID NOT Process: " + t.toString());
                c.toString();
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        CreditCardSystem test = new CreditCardSystem();

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

        // u1.printCards();

        cc1.charge(200);
        cc1.charge(350);
        cc1.charge(100);
        cc1.payment(50);
        cc1.payment(150);
        cc1.payment(175);
        cc1.charge(500);
        //cc1.toString();

        DebitCard db1 = new DebitCard(u1, "Chase", "1234 5678 9012 3456" , cal1, "111", 0 );
        u1.addCard(db1);
        u1.printCards();


        test.process(cc1);

//        CreditCard cc4 = new CreditCard(u2, "Chase", "1234 5678 9012 3456" , cal1, "111", 0, 15000 );
//        CreditCard cc5 = new CreditCard(u2, "Capital One", "6198 6849 3218 3841" , cal1, "222", 0, 2000 );
//        u2.addCard(cc4);
//        u2.addCard(cc5);
//        u2.printCards();
//
//        for(int i = 0; i < 5; i++){
//            cc4.charge(i * 100 +50);
//        }
//        for(int i = 0; i < 5; i++){
//            cc4.payment(i * 100 +50);
//        }
//
//        cc4.printInfo();
//        cc4.process();
    }
}

