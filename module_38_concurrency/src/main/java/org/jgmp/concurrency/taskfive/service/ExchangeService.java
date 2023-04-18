package org.jgmp.concurrency.taskfive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgmp.concurrency.taskfive.data.ExchangeRatesData;
import org.jgmp.concurrency.taskfive.dataaccess.UserAccountDAO;
import org.jgmp.concurrency.taskfive.exception.BaseExchangeException;
import org.jgmp.concurrency.taskfive.exception.IncorrectOperationException;
import org.jgmp.concurrency.taskfive.exception.InsufficientFundsException;
import org.jgmp.concurrency.taskfive.exception.UserAccountNotFoundException;
import org.jgmp.concurrency.taskfive.model.Currency;
import org.jgmp.concurrency.taskfive.model.OperationInfo;
import org.jgmp.concurrency.taskfive.model.UserAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.jgmp.concurrency.taskfive.data.Data.lockMap;

public class ExchangeService {

    private static final Logger logger = LogManager.getLogger(ExchangeService.class);

    private static volatile ExchangeService instance;
    private final UserAccountDAO userAccountDAO = UserAccountDAO.getInstance();

    private ExchangeService() {
    }

    public static ExchangeService getInstance() {
        if (instance == null) {
            synchronized (ExchangeService.class) {
                if (instance == null) {
                    instance = new ExchangeService();
                }
            }
        }
        return instance;
    }

    public OperationInfo exchangeCurrency(String accountName, Currency from, Currency to, Double amount) throws BaseExchangeException {
        logger.debug("Currency exchange started. From: {}, to: {}, amount: {}, accountName: {}", from, to, amount, accountName);
        OperationInfo info = new OperationInfo();
        BigDecimal exchangeRate = BigDecimal.valueOf(ExchangeRatesData.rates.get(from).getExchangeRates().get(to));
        lockMap.get(accountName).lock();

        UserAccount account = userAccountDAO.readUserAccount(accountName);
        if (account == null) {
            lockMap.get(accountName).unlock();
            logger.error("User account not found. Account name: {}", accountName);
            throw new UserAccountNotFoundException();
        }

        info.setAccountFullName(account.getFullName());
        if (from.equals(to)) {
            lockMap.get(accountName).unlock();
            logger.error("Incorrect operation. From: {}, to: {}, amount: {}, accountName: {}", from, to, amount, accountName);
            throw new IncorrectOperationException(from, to, amount);
        }

        BigDecimal currentAmount = account.getAmount(from);
        BigDecimal targetCurrencyAmount = account.getAmount(to);
        BigDecimal amountToExchange = BigDecimal.valueOf(amount);

        if (currentAmount.compareTo(amountToExchange) < 0) {
            lockMap.get(accountName).unlock();
            logger.error("Insufficient funds. From: {}, to: {}, amount: {}, accountName: {}", from, to, amount, accountName);
            throw new InsufficientFundsException(from, to, amount);
        } else {
            BigDecimal newFrom = currentAmount.subtract(amountToExchange);
            BigDecimal newTo = targetCurrencyAmount.add(amountToExchange.multiply(exchangeRate));
            account.setNewAmount(from, newFrom);
            account.setNewAmount(to, newTo);
            userAccountDAO.writeUserAccount(account);
            info.setPreviousFrom(currentAmount.toString());
            info.setPreviousTo(targetCurrencyAmount.toString());
            info.setCurrentFrom(newFrom.toString());
            info.setCurrentTo(newTo.toString());
        }
        info.setFrom(from.getShortName());
        info.setTo(to.getShortName());
        info.setExchangeRate(exchangeRate.toString());
        info.setAmount(amount.toString());
        info.setDateTime(LocalDateTime.now());
        lockMap.get(accountName).unlock();
        logger.debug("Currency exchange successful.");
        return info;
    }
}
