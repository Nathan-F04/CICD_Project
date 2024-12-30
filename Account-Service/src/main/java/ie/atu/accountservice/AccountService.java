package ie.atu.accountservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final PersonClient personClient;

    private final StockClient stockClient;

    private final StockValueClient stockValueClient;

    public AccountService(AccountRepository accountRepository, PersonClient personClient, StockClient stockClient, StockValueClient stockValueClient) {
        this.accountRepository = accountRepository;
        this.personClient = personClient;
        this.stockClient = stockClient;
        this.stockValueClient = stockValueClient;
    }

    public ResponseEntity<?> viewDetailsService(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()) {
            Account currentAccount = account.get();
            Map<String, String> user = new LinkedHashMap<>();
            user.put("Name", currentAccount.getName());
            user.put("Balance", String.valueOf(currentAccount.getBankBal()));
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
        }
    }

    public ResponseEntity<?> addBal(String name, float bankBal) {
        Optional<Account> account = accountRepository.findByName(name);
        if(bankBal >= 0) {
            if(account.isPresent()) {
                Account accountCurrent = account.get();
                float bal = accountCurrent.getBankBal() + bankBal;
                accountCurrent.setBankBal(bal);
                accountRepository.save(accountCurrent);
                return ResponseEntity.ok(accountCurrent);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
            }
        }
        else {
            if(account.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot add a negative value");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
            }
        }
    }

    public ResponseEntity<?> removeBal(String name, float bankBal) {
        Optional<Account> account = accountRepository.findByName(name);
        if(bankBal <= checkBal(name) && bankBal >= 0) {
            if(account.isPresent()) {
                Account accountCurrent = account.get();
                float bal = accountCurrent.getBankBal() - bankBal;
                accountCurrent.setBankBal(bal);
                accountRepository.save(accountCurrent);
                return ResponseEntity.ok(accountCurrent);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
            }
        }
        else {
            if(account.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot withdraw more than you have in your account");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
            }
        }
    }

    public double checkBal(String name){
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()) {
            Account accountCurrent = account.get();
            return accountCurrent.getBankBal();
        }
        else {
            return 0;
        }
    }

    public void createAcc(String name) {
        Account account = new Account();
        account.setName(name);
        account.setBankBal(0);
        accountRepository.save(account);
    }

    public void swapService(String oldName, String newName){
        Optional<Account> changedUser = accountRepository.findByName(oldName);
        if(changedUser.isPresent()){
            Account account = changedUser.get();
            account.setName(newName);
            accountRepository.save(account);
        }
    }

    public ResponseEntity<String> deleteAcc(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        if(checkBal(name) == 0) {
            if(account.isPresent()){
                Account accountCurrent = account.get();
                accountRepository.delete(accountCurrent);
                personClient.RemoveAccount(accountCurrent.getName());
                stockClient.deleteStocks(name);
                return ResponseEntity.ok("Account deleted successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
            }
        }
        else {
            if(account.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You still have funds in your account, please withdraw them first and make sure to sell your stocks or they will be lost.");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
            }
        }
    }

    //buy and sell stock
    public ResponseEntity<?> stockBuy (String stock, int amount, String name){
        //function to check balance and store it
        double bal = checkBal(name);
        //find stock price
        double price = stockValueClient.portfolioFromStockVal(stock);
        double total = price * amount;
        //if total>bal return invalid transaction else write it to the db and remove money from the account
        if(total>bal){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You do not have the funds to purchase the entered stocks");
        }else {
            //add to the db and remove from bal
            addBal(name, (float) -(total));
            stockClient.buyNewStocks(name, amount, stock);
            return ResponseEntity.ok("Stocks bought successfully");
        }
    }

    public ResponseEntity<?> stockSell(String stock, int amount, String name){
        //find stock price
        double price = stockValueClient.portfolioFromStockVal(stock);
        //check if you have that amount of shares
        int sharesInAccount = stockClient.checkShares(name, stock);
        System.out.println(sharesInAccount);
        if(sharesInAccount<amount){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You do not have the shares to sell");
        }else{
            double total = price * amount;
            //add to balance
            addBal(name, (float) total);
            //remove from db
            stockClient.sellStocks(name, amount, stock);
            return ResponseEntity.ok("Stock sold successfully");
        }
    }

    public ResponseEntity<?> returnPortfolioValueService(String name, String password) {
        Map<String, Object> response = personClient.portfolioValue(name, password);
        HttpStatusCode statusCode = HttpStatus.valueOf((Integer) response.get("Code"));
        if(!response.containsKey("Balance")){
            return ResponseEntity.status(statusCode).body(response.get("Message"));
        }else{
            return ResponseEntity.status(statusCode).body("Balance: " + response.get("Balance"));
        }
    }
}
