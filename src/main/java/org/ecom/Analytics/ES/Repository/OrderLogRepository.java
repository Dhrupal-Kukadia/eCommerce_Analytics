package org.ecom.Analytics.ES.Repository;

import org.ecom.Analytics.Log.OrderLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends ElasticsearchRepository<OrderLog, String> {
}
