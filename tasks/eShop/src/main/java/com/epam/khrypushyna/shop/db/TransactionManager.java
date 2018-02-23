package com.epam.khrypushyna.shop.db;

public interface TransactionManager {
	Object doTransaction(TransactionOperation operation);

}
