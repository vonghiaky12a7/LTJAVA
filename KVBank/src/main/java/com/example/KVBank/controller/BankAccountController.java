package com.example.KVBank.controller;

import com.example.KVBank.dao.BankAccountDAO;
import com.example.KVBank.entities.SendMoney;
import com.example.KVBank.dao.BankTransactionException;
import com.example.KVBank.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @GetMapping("/list")
    public String listAccounts(Model model) {
        List<BankAccountInfo> accounts = bankAccountDAO.listBankAccountInfo();
        model.addAttribute("accounts", accounts);
        return "listAccount";
    }

    @GetMapping("/sendmoney")
    public String showSendMoney(Model model) {
        model.addAttribute("SendMoney", new SendMoney());
        return "SendMonypage";
    }

    // không có lỗi
    @PostMapping("/sendmoney")
    public String processSendMoney(@ModelAttribute("SendMoney") SendMoney SendMoney, Model model) {
        try {
            bankAccountDAO.sendMoney(SendMoney.getFromAccountId(), SendMoney.getToAccountId(), SendMoney.getAmount());
            return "redirect:/list";
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("SendMoney", SendMoney);
            return "SendMonypage";
        }
    }
    // có lỗi
    /*@PostMapping("/sendmoney")
    public String processSendMoney(@ModelAttribute("SendMoney") SendMoney SendMoney, Model model) {
        try {
            bankAccountDAO.sendMoney(SendMoney.getFromAccountId(), SendMoney.getToAccountId(), SendMoney.getAmount());
            return "redirect:/list";
        } catch (RuntimeException e) { // Bắt RuntimeException để xử lý lỗi giả lập
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("SendMoney", SendMoney);
            return "SendMonypage";
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("SendMoney", SendMoney);
            return "SendMonypage";
        }
    }*/
}
