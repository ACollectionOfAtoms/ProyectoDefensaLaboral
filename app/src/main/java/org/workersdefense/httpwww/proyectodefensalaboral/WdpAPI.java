//package org.workersdefense.httpwww.proyectodefensalaboral;
//
//import android.util.Log;
//
//import com.algolia.search.saas.APIClient;
//import com.algolia.search.saas.AlgoliaException;
//import com.algolia.search.saas.Index;
//import com.algolia.search.saas.Query;
//import com.algolia.search.saas.listeners.SearchListener;
//
//import org.json.JSONObject;
//
///**
// * Created by ahernandez on 3/25/16.
// */
//
//public class WdpAPI implements SearchListener {
//    private static final APIClient client = new APIClient("R7X1GJKUPR", "fe2deb45679649d8e57466fe088f6d46");
//    private static final Index index = client.initIndex("WDPEmployers");
//    public String queryResult;
//
//    public void search(String q) {
//        index.searchASync(new Query(q), this);
//    }
//
//    @Override
//    public void searchResult(Index index, Query query, JSONObject results) {
//        // Log.d("results", results.toString());
//        this.queryResult = results.toString();
//    }
//
//    @Override
//    public void searchError(Index index, Query query, AlgoliaException e) {
//        Log.d("error", e.getMessage());
//    }
//}
