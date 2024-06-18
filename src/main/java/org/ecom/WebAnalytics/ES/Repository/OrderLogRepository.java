package org.ecom.WebAnalytics.ES.Repository;

import org.ecom.WebAnalytics.Log.OrderLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLogRepository extends ElasticsearchRepository<OrderLog, String> {
    List<OrderLog> findByuserId(String userId);
}
