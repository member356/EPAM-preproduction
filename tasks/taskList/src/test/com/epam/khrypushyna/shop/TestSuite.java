package com.epam.khrypushyna.shop;

import com.epam.khrypushyna.shop.creator.AddCouchTest;
import com.epam.khrypushyna.shop.creator.AddDeskTest;
import com.epam.khrypushyna.shop.creator.AddOfficeTableTest;
import com.epam.khrypushyna.shop.repository.CartRepositoryImplTest;
import com.epam.khrypushyna.shop.repository.CatalogRepositoryImplTest;
import com.epam.khrypushyna.shop.repository.OrderRepositoryImplTest;
import com.epam.khrypushyna.shop.service.CartServiceImplTest;
import com.epam.khrypushyna.shop.service.CatalogServiceImplTest;
import com.epam.khrypushyna.shop.service.OrderServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddCouchTest.class,
        AddOfficeTableTest.class,
        AddDeskTest.class,
        CartRepositoryImplTest.class,
        CatalogRepositoryImplTest.class,
        OrderRepositoryImplTest.class,
        CartServiceImplTest.class,
        CatalogServiceImplTest.class,
        OrderServiceImplTest.class
})
public class TestSuite {
}
