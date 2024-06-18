package org.ecom.Website.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "carts")
public class Cart {
    @Id
    String id;
    String userId;
    Map<String, Integer> productIds = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(Map<String, Integer> productIds) {
        this.productIds = productIds;
    }
}
