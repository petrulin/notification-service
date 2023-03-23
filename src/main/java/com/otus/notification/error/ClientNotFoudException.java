package com.otus.notification.error;

public class ClientNotFoudException extends StoreServiceException {
    public ClientNotFoudException() {
        super(ApplicationError.CLIENT_NOT_FOUND);
    }
}
