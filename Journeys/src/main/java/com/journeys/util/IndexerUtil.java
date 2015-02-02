/*
 * Creation : 30 janv. 2015
 */
package com.journeys.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;

import com.journeys.entity.Day;
import com.journeys.entity.Journey;
import com.journeys.entity.User;

public class IndexerUtil {

    public final static int VIEW = 1;
    public final static int EDIT = 2;
    
    private static HttpSolrServer server = null;
    
    public static void initSolr() {
    	String url = "http://localhost:8080/Solr";
		server = new HttpSolrServer(url);
		server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
		server.setConnectionTimeout(5000); // 5 seconds to establish TCP
		server.setSoTimeout(1000); // socket read timeout
		server.setDefaultMaxConnectionsPerHost(100);
		server.setMaxTotalConnections(100);
		server.setFollowRedirects(false); // defaults to false
		
    }
    
    public static void reindex(User user) throws SolrServerException {

    	SolrInputDocument doc = getSolrDocumentByTypeAndId("user", user.getId() + 10000);

        doc.setField( "id", 10000 + user.getId());
        doc.setField( "firstName", user.getFirstName() );
        doc.setField( "lastName", user.getLastName() );
        doc.setField( "email", user.getEmail() );
        doc.setField( "content_type", "user");
        
        addDocToSolr(doc);
    }
    
    public static void reindex(Journey journey) throws SolrServerException {

    	SolrInputDocument doc = getSolrDocumentByTypeAndId("journey", journey.getId() + 20000);

        doc.setField( "id", 20000 + journey.getId());
        doc.setField( "title", journey.getTitle() );
        doc.setField( "startDate", journey.getStartDate());
        doc.setField( "endDate", journey.getEndDate());
        if (journey.getCategoryGeo() != null) doc.setField( "categoryGeoEn", journey.getCategoryGeo().getEnglishName() );
        if (journey.getCategoryGeo() != null) doc.setField( "categoryGeoFr", journey.getCategoryGeo().getFrenchName() );
        if (journey.getCategoryTrip() != null) doc.setField( "categoryTripEn", journey.getCategoryTrip().getEnglishName() );
        if (journey.getCategoryTrip() != null) doc.setField( "categoryTripFr", journey.getCategoryTrip().getFrenchName() );
        
        doc.setField( "content_type", "journey");
        
        addDocToSolr(doc);
    }

    public static void reindex(Day day) throws SolrServerException {

    	SolrInputDocument doc = getSolrDocumentByTypeAndId("day", day.getId() + 30000);

    	doc.setField( "id", 30000 + day.getId());
        doc.setField( "title", day.getTitle() );
        
        if (Validator.isNotNull(day.getContent())) {
        	String textContent = Jsoup.parse(day.getContent()).text();
            doc.setField( "content", textContent);
        }
        
        doc.setField( "content_type", "day");
        
        addDocToSolr(doc);
        
    }
    
    private static SolrInputDocument getSolrDocumentByTypeAndId(String type, Integer id) throws SolrServerException {
    	
    	if (server == null) {
    		initSolr();
    	}
    	
    	SolrQuery query = new SolrQuery();
        query.setQuery( "content_type:user AND id:" + id );

        QueryResponse queryResponse;
    	queryResponse = server.query( query );
    	Iterator<SolrDocument> iter = queryResponse.getResults().iterator();
    	
    	if (iter.hasNext()) {
    		return ClientUtils.toSolrInputDocument(iter.next());
    	}
    	
    	return new SolrInputDocument();
    }

    private static void addDocToSolr(SolrInputDocument doc) {
    	
        if(server == null) {
        	initSolr();
        }
        try {
			server.add(doc);
			server.commit();

	    } catch (SolrServerException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        

    }
    
    public static Set<Journey> searchJourneys(String search) {

        if(server == null) {
        	initSolr();
        }
        
        SolrQuery query = new SolrQuery();
        query.setQuery( "content_type:journey AND title:(" + search + ")");

        QueryResponse queryResponse;
        
        Set<Journey> journeys = new HashSet<Journey>();

		try {
			queryResponse = server.query( query );
			
	        Iterator<SolrDocument> iter = queryResponse.getResults().iterator();

	        while (iter.hasNext()) {
	            SolrDocument resultDoc = iter.next();

	            String id = (String) resultDoc.getFieldValue("id"); //id is the uniqueKey field
	            String title = (String) resultDoc.getFieldValue("title");
	            Date startDate = (Date) resultDoc.getFieldValue("startDate");
	            Date endDate = (Date) resultDoc.getFieldValue("endDate");
	            
	            Journey journey = new Journey();
	            journey.setId(Integer.parseInt(id));
	            journey.setTitle(title);
	            journey.setStartDate(startDate);
	            journey.setEndDate(endDate);
	            
	            journeys.add(journey);
	            
	            /*if (queryResponse.getHighlighting().get(id) != null) {
	              List<String> highlightSnippets = queryResponse.getHighlighting().get(id).get("content");
	            }*/
	            
	          }
	        
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
        return journeys;
    }
    
    public static Set<User> searchUsers(String search) {
    	
        if(server == null) {
        	initSolr();
        }
        
    	SolrQuery query = new SolrQuery();
        query.setQuery( "content_type:user AND (firstName:(" + search + ") OR lastName:(" + search + "))");
        //query.addSortField( "price", SolrQuery.ORDER.asc );
        QueryResponse queryResponse;
        
        Set<User> users = new HashSet<User>();

		try {
			queryResponse = server.query( query );
			
	        Iterator<SolrDocument> iter = queryResponse.getResults().iterator();

	        while (iter.hasNext()) {
	            SolrDocument resultDoc = iter.next();

	            String id = (String) resultDoc.getFieldValue("id"); //id is the uniqueKey field
	            String email = (String) resultDoc.getFieldValue("email");
	            String firstName = (String) resultDoc.getFieldValue("firstName");
	            String lastName = (String) resultDoc.getFieldValue("lastName");

	            User user = new User();
	            user.setId(Integer.parseInt(id));
	            user.setEmail(email);
	            user.setFirstName(firstName);
	            user.setLastName(lastName);
	            users.add(user);
	            
	            /*if (queryResponse.getHighlighting().get(id) != null) {
	              List<String> highlightSnippets = queryResponse.getHighlighting().get(id).get("content");
	            }*/
	            
	          }
	        
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
        return users;
    }
    
    public static void deleteIndex(Integer id) {
        if(server == null) {
        	initSolr();
        }
        try {
        	server.deleteById(id.toString());
            server.commit();

	    } catch (SolrServerException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public static void deleteAllIndex() {
        if(server == null) {
        	initSolr();
        }
        try {
            server.deleteByQuery("*:*");
            server.commit();

	    } catch (SolrServerException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

}
