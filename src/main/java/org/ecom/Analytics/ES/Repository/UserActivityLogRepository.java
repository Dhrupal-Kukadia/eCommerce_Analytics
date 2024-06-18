package org.ecom.Analytics.ES.Repository;

import org.ecom.Analytics.Log.UserActivityLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityLogRepository extends ElasticsearchRepository<UserActivityLog, String> {
}
