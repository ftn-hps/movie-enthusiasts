package ftnhps.movieenthusiasts.places;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.reservations.Reservation;
import ftnhps.movieenthusiasts.reservations.ReservationRepository;

@Transactional
@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Place findOne(Long id) {
		return placeRepository.findOne(id);
	}

	@Override
	public List<Place> findAll() {
		return placeRepository.findAll();
	}

	@Override
	public List<Place> findAll(PlaceType type) {
		return placeRepository.findByType(type);
	}

	@Override
	public Place add(Place input) {
		return placeRepository.save(input);
	}

	@Override
	public Place edit(Long id, Place input) {
		if(findOne(id) == null)
			return null;

		input.setId(id);
		return placeRepository.save(input);
	}

	@Override
	public void recalculateRating(Place place) {
		List<Reservation> reservations = reservationRepository.findByDateTime_Projection_Place(place);
		int sum = place.getRating();
		int number = 1;
		for(Reservation reservation:reservations) {
			if(reservation.getAmbientRating() > 0) {
				sum += reservation.getAmbientRating();
				number++;
			}		
		}
		place.setRating((int)sum/number);
	}

	@Override
	public ChartDTO getChartData(Place place) {
		HashMap<Integer, String> monthMap = new HashMap<Integer, String>();
		monthMap.put(0,"January");
		monthMap.put(1,"February");
		monthMap.put(2,"March");
		monthMap.put(3,"April");
		monthMap.put(4,"May");
		monthMap.put(5,"Jun");
		monthMap.put(6,"July");
		monthMap.put(7,"August");
		monthMap.put(8,"September");
		monthMap.put(9,"October");
		monthMap.put(10,"November");
		monthMap.put(11,"December");
		
		HashMap<Integer, Integer[]> dataMap = new HashMap<>();
		dataMap.put(0,new Integer[] {0, 0});
		dataMap.put(1,new Integer[] {0, 0});
		dataMap.put(2,new Integer[] {0, 0});
		dataMap.put(3,new Integer[] {0, 0});
		dataMap.put(4,new Integer[] {0, 0});
		dataMap.put(5,new Integer[] {0, 0});
		dataMap.put(6,new Integer[] {0, 0});
		dataMap.put(7,new Integer[] {0, 0});
		dataMap.put(8,new Integer[] {0, 0});
		dataMap.put(9,new Integer[] {0, 0});
		dataMap.put(10,new Integer[] {0, 0});
		dataMap.put(11,new Integer[] {0, 0});
		ChartDTO chartDTO= new ChartDTO();
		
				
		// izracunavam koliko je rezervacija bilo u tom mesece i koliko je to ukupno kesa
		List<Reservation> reservations = reservationRepository.findByDateTime_Projection_Place(place);
		for(Reservation r:reservations) {
			Date date = new Date(r.getDateTime().getTimeStamp() * 1000);
			int currentMonth = date.getMonth();
			Integer [] currentMonthData = dataMap.get(currentMonth);
			currentMonthData[0]+=1;
			currentMonthData[1]+=(int) r.getPriceWithDiscount();
			dataMap.put(currentMonth,currentMonthData);
		}
		
		Date currentDate = new Date();
		int month = currentDate.getMonth();
		
		for (int i =0; i<6; i++) {
			chartDTO.lables[5-i] = monthMap.get(month);
			chartDTO.data[0][5-i] = dataMap.get(month)[0];
			chartDTO.data[1][5-i] = dataMap.get(month)[1];
			month--;
			if(month < 0)
				month = 11;
		}
		
		return chartDTO;
	}

}
