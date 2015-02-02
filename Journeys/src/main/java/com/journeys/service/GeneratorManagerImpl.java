package com.journeys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.DayDAO;
import com.journeys.dao.JourneyDAO;
import com.journeys.dao.RoleDAO;
import com.journeys.dao.UserDAO;
import com.journeys.entity.Day;
import com.journeys.entity.Journey;
import com.journeys.entity.Role;
import com.journeys.entity.User;

@Service
public class GeneratorManagerImpl implements GeneratorManager {
	
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private JourneyDAO journeyDAO;
    
    @Autowired
    private DayDAO dayDAO;
    
    @Autowired
    private RoleDAO roleDAO;
    
	@Transactional
	public void generate() {

	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
	        // Create roles
	        
	        Role role = new Role();
	        role.setId(1);
	        role.setName("ROLE_ADMIN");
	        roleDAO.addRole(role);

            Set<Role> roles = new HashSet<Role>();
            roles.add(role);
            
	        //Create users
	        
	        User user = new User();
	        user.setId(1);
            user.setEmail("jeremie.ardiot@gmail.com");
            user.setFirstName("Jérémie");
            user.setLastName("Ardiot");
	        user.setPassword(BCrypt.hashpw("Bonjour01", BCrypt.gensalt()));
	        user.setEnabled(true);
	        user.setRoles(roles);
	        
	        userDAO.addUser(user);

            User user2 = new User();
            user2.setId(2);
            user2.setEmail("j.ardiot@gmail.com");
            user2.setFirstName("Jonathan");
            user2.setLastName("Ardiot");
            user2.setPassword(BCrypt.hashpw("Bonjour01", BCrypt.gensalt()));
            user2.setEnabled(true);
            user2.setRoles(roles);
            
            userDAO.addUser(user2);
            
	        // Create journeys
	        
	        Journey journey = new Journey();
	        journey.setId(1);
	        journey.setTitle("Villes de rêve");
            journey.setStartDate(sdf.parse("20/06/2015"));
            journey.setEndDate(sdf.parse("11/07/2015"));
            journey.setDisplayWeekEnd(true);
            journey.setPictureUrl("http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg");
            journey.setPassword(BCrypt.hashpw("canada", BCrypt.gensalt()));
            journey.setUser(user);
            journeyDAO.addJourney(journey);
            
            Journey journey2 = new Journey();
            journey2.setId(2);
            journey2.setTitle("Viva Las Vegas");
            journey2.setStartDate(sdf.parse("20/08/2015"));
            journey2.setEndDate(sdf.parse("22/08/2015"));
            journey2.setDisplayWeekEnd(true);
            journey2.setPictureUrl("http://www.las-vegas.travel/images/paris-hotel-las-vegas.jpg");
            journey2.setPassword("");
            journey2.setUser(user);
            journeyDAO.addJourney(journey2);
            
            // Create days
            
            Day day1 = new Day();
            day1.setId(1);
            day1.setTitle("Arrivée à Montréal");
            day1.setPictureUrl("http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg");
            day1.setDate(sdf.parse("20/06/2015"));
            day1.setContent("");
            day1.setJourney(journey);
            day1.setLatitude(45.5016889);
            day1.setLongitude(-73.56725599999999);
            dayDAO.addDay(day1);
            
            Day day2 = new Day();
            day2.setId(2);
            day2.setTitle("Voiture à Plattsburgh");
            day2.setPictureUrl("http://flyplattsburgh.com/data/images/gallery/Plattsburgh%20International%20Airport%20Terminal%201.jpg");
            day2.setDate(sdf.parse("21/06/2015"));
            day2.setContent("");
            day2.setJourney(journey);
            day2.setLatitude(44.6994873);
            day2.setLongitude(-73.4529124);
            dayDAO.addDay(day2);
            
            Day day3 = new Day();
            day3.setId(3);
            day3.setTitle("En route vers Québec");
            day3.setPictureUrl("http://pvtistes.net/wp-content/uploads/2012/11/quebec-ville1-800x535.jpg");
            day3.setDate(sdf.parse("22/06/2015"));
            day3.setContent("");
            day3.setJourney(journey);
            day3.setLatitude(46.8032826);
            day3.setLongitude(-71.242796);
            dayDAO.addDay(day3);
            
            Day day4 = new Day();
            day4.setId(4);
            day4.setTitle("En route vers Tadoussac");
            day4.setPictureUrl("http://ssdc-country.net/Bals_Sorties/Carnet%20de%20voyage/photos%20carnet%20de%20voyage/014%20Chute-montmorency.jpg");
            day4.setDate(sdf.parse("23/06/2015"));
            day4.setContent("");
            day4.setJourney(journey);
            day4.setLatitude(48.14597759999999);
            day4.setLongitude(-69.71283950000003);
            dayDAO.addDay(day4);
            
            Day day5 = new Day();
            day5.setId(5);
            day5.setTitle("Baleines et fjord");
            day5.setPictureUrl("http://www.bonjourquebec.com/fileadmin/Image/decouvrez/activites/routes_circuits/route_fjord/tq_000186_g.jpg");
            day5.setDate(sdf.parse("24/06/2015"));
            day5.setContent("");
            day5.setJourney(journey);
            day5.setLatitude(48.423604);
            day5.setLongitude(-71.23954630000003);
            dayDAO.addDay(day5);
            
            Day day6 = new Day();
            day6.setId(6);
            day6.setTitle("Retour à Québec");
            day6.setPictureUrl("http://pvtistes.net/wp-content/uploads/2012/11/quebec-ville1-800x535.jpg");
            day6.setDate(sdf.parse("25/06/2015"));
            day6.setContent("");
            day6.setJourney(journey);
            day6.setLatitude(46.8032826);
            day6.setLongitude(-71.242796);
            dayDAO.addDay(day6);
            
            Day day7 = new Day();
            day7.setId(7);
            day7.setTitle("Visite de Québec");
            day7.setPictureUrl("http://pvtistes.net/wp-content/uploads/2012/11/quebec-ville1-800x535.jpg");
            day7.setDate(sdf.parse("26/06/2015"));
            day7.setContent("");
            day7.setJourney(journey);
            day7.setLatitude(46.8032826);
            day7.setLongitude(-71.242796);
            dayDAO.addDay(day7);
            
            Day day8 = new Day();
            day8.setId(8);
            day8.setTitle("Retour à Montréal");
            day8.setPictureUrl("http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg");
            day8.setDate(sdf.parse("27/06/2015"));
            day8.setContent("");
            day8.setJourney(journey);
            day8.setLatitude(45.5016889);
            day8.setLongitude(-73.56725599999999);
            dayDAO.addDay(day8);
            
            Day day9 = new Day();
            day9.setId(9);
            day9.setTitle("Visite de Montréal");
            day9.setPictureUrl("http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg");
            day9.setDate(sdf.parse("28/06/2015"));
            day9.setContent("");
            day9.setJourney(journey);
            day9.setLatitude(45.5016889);
            day9.setLongitude(-73.56725599999999);
            dayDAO.addDay(day9);
            
            Day day10 = new Day();
            day10.setId(10);
            day10.setTitle("En route vers Ottawa");
            day10.setPictureUrl("http://media-cdn.tripadvisor.com/media/photo-s/01/0c/cb/ef/ottawa.jpg");
            day10.setDate(sdf.parse("29/06/2015"));
            day10.setContent("");
            day10.setJourney(journey);
            day10.setLatitude(45.4215296);
            day10.setLongitude(-75.69719309999999);
            dayDAO.addDay(day10);
            
            Day day11 = new Day();
            day11.setId(11);
            day11.setTitle("Mille îles");
            day11.setPictureUrl("http://mw2.google.com/mw-panoramio/photos/medium/11716810.jpg");
            day11.setDate(sdf.parse("30/06/2015"));
            day11.setContent("");
            day11.setJourney(journey);
            day11.setLatitude(44.2311717);
            day11.setLongitude(-76.48595439999997);
            dayDAO.addDay(day11);
            
            Day day12 = new Day();
            day12.setId(12);
            day12.setTitle("Toronto");
            day12.setPictureUrl("http://knightsinntoronto-com.factorepreview.ca/system/images/images/2/original/toronto-012222.jpg?1358279327");
            day12.setDate(sdf.parse("01/07/2015"));
            day12.setContent("");
            day12.setJourney(journey);
            day12.setLatitude(43.653226);
            day12.setLongitude(-79.38318429999998);
            dayDAO.addDay(day12);
            
            Day day13 = new Day();
            day13.setId(13);
            day13.setTitle("Chutes du Niagara");
            day13.setPictureUrl("http://moonliteniagarafalls.com/photos/falls1.jpg");
            day13.setDate(sdf.parse("02/07/2015"));
            day13.setContent("");
            day13.setJourney(journey);
            day13.setLatitude(43.08);
            day13.setLongitude(-79.07100000000003);
            dayDAO.addDay(day13);
            
            Day day14 = new Day();
            day14.setId(14);
            day14.setTitle("En route vers Washington");
            day14.setPictureUrl("http://washington.org/sites/default/files/styles/guide_to_left/public/July4th-credit-WETA_3.jpg?itok=qtgNQ4Tc");
            day14.setDate(sdf.parse("03/07/2015"));
            day14.setContent("");
            day14.setJourney(journey);
            day14.setLatitude(38.9071923);
            day14.setLongitude(-77.03687070000001);
            dayDAO.addDay(day14);
            
            Day day15 = new Day();
            day15.setId(15);
            day15.setTitle("Independance Day à Washington");
            day15.setPictureUrl("http://washington.org/sites/default/files/styles/guide_to_left/public/July4th-credit-WETA_3.jpg?itok=qtgNQ4Tc");
            day15.setDate(sdf.parse("04/07/2015"));
            day15.setContent("");
            day15.setJourney(journey);
            day15.setLatitude(38.9071923);
            day15.setLongitude(-77.03687070000001);
            dayDAO.addDay(day15);
            
            Day day16 = new Day();
            day16.setId(16);
            day16.setTitle("En route vers Philadelphie");
            day16.setPictureUrl("http://www.informationplanet.be/files/images/sejour_linguistique_philadelphie.jpg");
            day16.setDate(sdf.parse("05/07/2015"));
            day16.setContent("");
            day16.setJourney(journey);
            day16.setLatitude(39.9525839);
            day16.setLongitude(-75.16522150000003);
            dayDAO.addDay(day16);
            
            Day day17 = new Day();
            day17.setId(17);
            day17.setTitle("En route vers New York");
            day17.setPictureUrl("http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg");
            day17.setDate(sdf.parse("06/07/2015"));
            day17.setContent("");
            day17.setJourney(journey);
            day17.setLatitude(40.7127837);
            day17.setLongitude(-74.00594130000002);
            dayDAO.addDay(day17);
            
            Day day18 = new Day();
            day18.setId(18);
            day18.setTitle("New York");
            day18.setPictureUrl("http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg");
            day18.setDate(sdf.parse("07/07/2015"));
            day18.setContent("");
            day18.setJourney(journey);
            day18.setLatitude(40.7127837);
            day18.setLongitude(-74.00594130000002);
            dayDAO.addDay(day18);
            
            Day day19 = new Day();
            day19.setId(19);
            day19.setTitle("New York");
            day19.setPictureUrl("http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg");
            day19.setDate(sdf.parse("08/07/2015"));
            day19.setContent("");
            day19.setJourney(journey);
            day19.setLatitude(40.7127837);
            day19.setLongitude(-74.00594130000002);
            dayDAO.addDay(day19);
            
            Day day20 = new Day();
            day20.setId(20);
            day20.setTitle("New York");
            day20.setPictureUrl("http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg");
            day20.setDate(sdf.parse("09/07/2015"));
            day20.setContent("");
            day20.setJourney(journey);
            day20.setLatitude(40.7127837);
            day20.setLongitude(-74.00594130000002);
            dayDAO.addDay(day20);
            
            Day day21 = new Day();
            day21.setId(21);
            day21.setTitle("New York");
            day21.setPictureUrl("http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg");
            day21.setDate(sdf.parse("10/07/2015"));
            day21.setContent("");
            day21.setJourney(journey);
            day21.setLatitude(40.7127837);
            day21.setLongitude(-74.00594130000002);
            dayDAO.addDay(day21);
            
            Day day22 = new Day();
            day22.setId(22);
            day22.setTitle("New York");
            day22.setPictureUrl("http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg");
            day22.setDate(sdf.parse("11/07/2015"));
            day22.setContent("");
            day22.setJourney(journey);
            day22.setLatitude(40.7127837);
            day22.setLongitude(-74.00594130000002);
            dayDAO.addDay(day22);
            
            /* ============================ */
            
            Day day23 = new Day();
            day23.setId(23);
            day23.setTitle("Las Vegas");
            day23.setPictureUrl("http://jre2014.fr/wp-content/uploads/2014/09/lasVegasBig.jpg");
            day23.setDate(sdf.parse("20/08/2015"));
            day23.setContent("");
            day23.setJourney(journey2);
            day23.setLatitude(36.1699412);
            day23.setLongitude(-115.13982959999998);
            dayDAO.addDay(day23);
            
            Day day24 = new Day();
            day24.setId(24);
            day24.setTitle("Las Vegas");
            day24.setPictureUrl("http://jre2014.fr/wp-content/uploads/2014/09/lasVegasBig.jpg");
            day24.setDate(sdf.parse("21/08/2015"));
            day24.setContent("");
            day24.setJourney(journey2);
            day24.setLatitude(36.1699412);
            day24.setLongitude(-115.13982959999998);
            dayDAO.addDay(day24);
            
            Day day25 = new Day();
            day25.setId(25);
            day25.setTitle("Las Vegas");
            day25.setPictureUrl("http://jre2014.fr/wp-content/uploads/2014/09/lasVegasBig.jpg");
            day25.setDate(sdf.parse("22/08/2015"));
            day25.setContent("");
            day25.setJourney(journey2);
            day25.setLatitude(36.1699412);
            day25.setLongitude(-115.13982959999998);
            dayDAO.addDay(day25);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }

	}

}
