package com.otus.notification.error;

public class NotEnoughMoneyException extends StoreServiceException {
    public NotEnoughMoneyException() {
        super(ApplicationError.NOT_ENOUGH_MONEY);
    }
}
