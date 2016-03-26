package org.workersdefense.httpwww.proyectodefensalaboral;
import com.algolia.search.saas.APIClient;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.APIMethod;
import com.algolia.search.saas.Query;

/**
 * Created by dhernandez on 3/25/16.
 */

public class WdpAPI extends APIClient{
    APIClient client = new APIClient("", "");
    Index index = client.initIndex("WDPEmployers");
}
