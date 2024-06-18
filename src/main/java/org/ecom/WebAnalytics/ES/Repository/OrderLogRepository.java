package org.ecom.WebAnalytics.ES.Repository;

import org.ecom.WebAnalytics.Log.OrderLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends ElasticsearchRepository<OrderLog, String> {
}
