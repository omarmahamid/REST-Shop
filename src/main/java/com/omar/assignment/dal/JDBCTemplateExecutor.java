package com.omar.assignment.dal;

import com.omar.assignment.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Component
public class JDBCTemplateExecutor {

    protected Logger log = Logger.getLogger(JDBCTemplateExecutor.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JDBCTemplateExecutor(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public void insertIntoCatalog(long catalogId,
                                  String catalogName){
        try {
            jdbcTemplate.update(
                    QueryVCB.INSERT_INTO_CATALOG,
                    catalogId,
                    catalogName
            );
        }catch (DuplicateKeyException e){
            log.info("Duplicate Key, need to choose another key");
        }
    }

    public void updateCatalog(long catalogId,
                              String name){
        jdbcTemplate.update(
                QueryVCB.UPDATE_CATALOG,
                name,
                catalogId
        );
    }

    public void updateItemName(long itemId,
                               String newName){
        jdbcTemplate.update(
                QueryVCB.UPDATE_ITEM_NAME,
                newName,
                itemId
        );
    }

    public void updateItemPrice(long itemId,
                               long price){
        jdbcTemplate.update(
                QueryVCB.UPDATE_ITEM_PRICE,
                price,
                itemId
        );
    }

    public void removeItem(long itemId){
        jdbcTemplate.update(
                QueryVCB.REMOVE_ITEM_BY_ID,
                itemId
        );
    }

    public void removeCatalog(long catalogId){
        jdbcTemplate.update(
                QueryVCB.REMOVE_CATALOG_BY_ID,
                catalogId
        );
    }

    public void updateItemCatalog(long itemId,
                               long catalogId){
        jdbcTemplate.update(
                QueryVCB.UPDATE_ITEM_CATALOG,
                catalogId,
                itemId
        );
    }

    public void insertIntoItemInShop(long itemId,
                                     long ownerId,
                                     long sold){
        jdbcTemplate.update(
                QueryVCB.INSERT_INTO_ITEM_IN_SHOP,
                itemId,
                ownerId,
                sold
        );
    }

    public void insertIntoItemPurchased(long itemId,
                                        long purchaseId){
        jdbcTemplate.update(
                QueryVCB.INSERT_INTO_ITEM_PURCHASED,
                itemId,
                purchaseId
        );
    }


    public void insertIntoItemToShop(long itemId,
                                     long catalogId,
                                     long sellerId,
                                     String itemName,
                                     long price
                                     ){
        jdbcTemplate.update(
                QueryVCB.INSERT_INTO_ITEM_TO_SHOP,
                itemId,
                catalogId,
                sellerId,
                itemName,
                price
        );
    }


    public void insertIntoPurchases(long itemId,
                                    long customerId,
                                    String paymentMethod,
                                    double total,
                                    double discount){
        jdbcTemplate.update(QueryVCB.INSERT_INTO_PURCHASES,
                itemId,
                customerId,
                paymentMethod,
                total,
                discount
        );
    }


    public void insertIntoSellers(long sellerId,
                                  String sellerName,
                                  String sellerEmail){
        jdbcTemplate.update(
                QueryVCB.INSERT_INTO_SELLERS,
                sellerId,
                sellerName,
                sellerEmail
        );
    }

    public void insertIntoOwners(long ownerId,
                                  String ownerName,
                                  String ownerEmail){
        jdbcTemplate.update(
                QueryVCB.INSERT_INTO_OWNERS,
                ownerId,
                ownerName,
                ownerEmail
        );
    }

    public void insertIntoCustomers(long customerId,
                                  String customerName,
                                  String customerEmail){
        jdbcTemplate.update(
                QueryVCB.INSERT_INTO_CUSTOMERS,
                customerId,
                customerName,
                customerEmail
        );
    }

    public List<ItemToShop> getItemsByCatalogId(long catalogId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_ITEMS_BY_CATALOG_ID,
                new Object[]{catalogId},
                (rs, rowNum) ->
                        new ItemToShop(
                                rs.getLong("ITEM_ID"),
                                rs.getLong("CATALOG_ID"),
                                rs.getLong("SELLER_ID"),
                                rs.getString("ITEM_NAME"),
                                rs.getLong("PRICE")
                        )
        );
    }

    public List<Catalog> getCatalogById(long catalogId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_CATALOG_ID,
                new Object[]{catalogId},
                (rs,rowNum) ->
                        new Catalog(
                                rs.getLong("CATALOG_ID"),
                                rs.getString("CATALOG_NAME")
                        )
        );
    }

    public List<ItemToShop> getItemToShopById(long itemId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_ITEM_ID,
                new Object[]{itemId},
                (rs, rowNum) ->
                        new ItemToShop(
                                rs.getLong("ITEM_ID"),
                                rs.getLong("CATALOG_ID"),
                                rs.getLong("SELLER_ID"),
                                rs.getString("ITEM_NAME"),
                                rs.getLong("PRICE")
                        )
        );
    }

    public Integer getMaxIdForPurchase(){
         Optional<Integer> maxId = jdbcTemplate.
                query(
                        QueryVCB.SELECT_PURCHASE_ID,
                        (rs,rowNum) -> (int) rs.getLong("PURCHASE_ID"))
                .stream()
                .max(Comparator.naturalOrder());

        return maxId.orElse(null);
    }


    public List<ItemInShop> getItemsInShopById(long itemId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_ITEM_ID_IN_SHOP,
                new Object[]{itemId},
                (rs, rowNum) ->
                        new ItemInShop(
                                rs.getLong("ITEM_ID"),
                                rs.getLong("OWNER_ID"),
                                rs.getLong("SOLD")
                        )

        );
    }


    public List<Customers> getCustomerById(long customerId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_CUSTOMER_ID,
                new Object[]{customerId},
                (rs, rowNum) ->
                        new Customers(
                                rs.getLong("CUSTOMER_ID"),
                                rs.getString("CUSTOMER_NAME"),
                                rs.getString("CUSTOMER_EMAIL")
                        )
        );
    }

    public List<Owners> getOwnerById(long ownerId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_OWNER_ID,
                new Object[]{ownerId},
                (rs,rowNum) ->
                        new Owners(
                                rs.getLong("OWNER_ID"),
                                rs.getString("OWNER_NAME"),
                                rs.getString("OWNER_EMAIL")
                        )
        );
    }

    public List<Sellers> getSellerById(long sellerId){
        return jdbcTemplate.query(
                QueryVCB.SELECT_SELLER_ID,
                new Object[]{sellerId},
                (rs,rowNum) ->
                        new Sellers(
                                rs.getLong("SELLER_ID"),
                                rs.getString("SELLER_NAME"),
                                rs.getString("SELLER_EMAIL")
                        )
        );
    }


    public void setSold(long id){
        jdbcTemplate.update(
                QueryVCB.SET_SOLD_ITEM_IN_SHOP,
                id
        );
    }

}
