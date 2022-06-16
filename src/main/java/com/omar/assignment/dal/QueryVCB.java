package com.omar.assignment.dal;

public class QueryVCB {

    public static final String SELECT_ITEMS_BY_CATALOG_ID = "SELECT item_to_shop.item_id,item_to_shop.price,item_to_shop.item_name,item_to_shop.catalog_id,item_to_shop.seller_id " +
            "FROM ITEM_TO_SHOP INNER JOIN ITEM_IN_SHOP ON " +
            "item_to_shop.item_id = item_in_shop.item_id and item_to_shop.catalog_id = ? and item_in_shop.sold = '0'";



    public static final String SELECT_CUSTOMER_ID = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = ?";

    public static final String SELECT_OWNER_ID = "SELECT * FROM OWNERS WHERE OWNER_ID = ?";

    public static final String SELECT_SELLER_ID = "SELECT * FROM SELLERS WHERE SELLER_ID = ?";

    public static final String SELECT_ITEM_ID = "SELECT * FROM ITEM_TO_SHOP WHERE ITEM_ID = ?";

    public static final String SELECT_MAX_ID_PURCHASE = "SELECT MAX(PURCHASE_ID) FROM PURCHASES";

    public static final String SELECT_CATALOG_ID = "SELECT * FROM CATALOG WHERE CATALOG_ID = ?";

    public static final String SELECT_PURCHASE_ID = "SELECT purchase_id FROM PURCHASES";

    public static final String INSERT_INTO_ITEM_PURCHASED = "insert into item_purchased (item_id, purchase_id) VALUES (?,?)";

    public static final String INSERT_INTO_ITEM_TO_SHOP = "insert into item_to_shop (item_id, catalog_id, seller_id, item_name, price) VALUES (?,?,?,?,?)";

    public static final String INSERT_INTO_PURCHASES = "insert into purchases (purchase_id, customer_id, payment_method, total, discount) VALUES (?,?,?,?,?)";

    public static final String INSERT_INTO_CATALOG = "insert into catalog (catalog_id, catalog_name) VALUES (?,?)";

    public static final String INSERT_INTO_ITEM_IN_SHOP = "insert into item_in_shop (item_id, owner_id, sold) VALUES (?,?,?)";

    public static final String INSERT_INTO_SELLERS = "insert into sellers (seller_id, seller_name, seller_email) VALUES (?,?,?)";

    public static final String INSERT_INTO_OWNERS = "insert into owners (owner_id, owner_name, owner_email) VALUES (?,?,?)";

    public static final String INSERT_INTO_CUSTOMERS = "insert into customers (customer_id, customer_name, customer_email) VALUES (?,?,?)";

    public static final String SELECT_ITEM_ID_IN_SHOP = "SELECT * FROM ITEM_IN_SHOP WHERE ITEM_ID = ? AND SOLD = 0";

    public static final String SET_SOLD_ITEM_IN_SHOP = "update item_in_shop SET item_in_shop.sold = '1' where item_id = ?";


    public static final String UPDATE_CATALOG = "update catalog set catalog.catalog_name = ? where catalog.catalog_id = ?";

    public static final String UPDATE_ITEM_NAME = "update item_to_shop SET item_to_shop.item_name = ? where item_to_shop.item_id = ?";

    public static final String UPDATE_ITEM_PRICE = "update item_to_shop SET item_to_shop.price = ? where item_to_shop.item_id = ?";

    public static final String UPDATE_ITEM_CATALOG = "update item_to_shop set item_to_shop.catalog_id = ? where item_to_shop.item_id = ?";

    public static final String REMOVE_ITEM_BY_ID = "delete from item_in_shop where item_id = ?";

    public static final String REMOVE_CATALOG_BY_ID = "delete from catalog where catalog_id = ?";
}
