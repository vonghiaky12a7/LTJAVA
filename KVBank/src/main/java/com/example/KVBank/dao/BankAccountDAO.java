package com.example.KVBank.dao;

import com.example.KVBank.entities.BankAccount;
import com.example.KVBank.model.BankAccountInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;


import java.util.List;

@Repository
public class BankAccountDAO {
    // MANDATORY: Bắt buộc phải có Transaction đã được tạo trước đó.
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(int id, int amount) throws BankTransactionException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }

        int newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException("The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }

    // Không bắt ngoại lệ BankTransactionException trong phương thức này.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(int fromAccountId, int toAccountId, int amount) throws BankTransactionException {
        addAmount(toAccountId, amount);
        // Giả lập lỗi xảy ra
/*        if (true) {
            throw new RuntimeException("Simulated error for rollback testing");
        }*/
        // khi không có lỗi
        addAmount(fromAccountId, -amount);
    }
    @PersistenceContext
    private EntityManager entityManager;

    public BankAccount findById(int id) {
        return entityManager.find(BankAccount.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName()
                + "(e.id, e.username, e.balance) "
                + "from " + BankAccount.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }
}
