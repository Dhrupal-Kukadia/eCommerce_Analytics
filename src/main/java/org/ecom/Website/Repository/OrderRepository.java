package org.ecom.Website.Repository;

import org.ecom.Website.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{\"bool\": {\"must\": [{\"range\": {\"timestamp\": {\"gte\": \"?0\", \"lte\": \"?1\"}}}]}}")
    Long countByTimestampBetween(Long sinceTime, Long untilTime);

    @Query("{\"bool\": {\"must\": [{\"range\": {\"timestamp\": {\"gte\": \"?0\", \"lte\": \"?1\"}}}]}}")
    Iterable<Order> findByTimestampBetween(Long sinceTime, Long untilTime);
}
