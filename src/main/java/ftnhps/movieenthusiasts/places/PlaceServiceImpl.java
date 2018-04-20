package ftnhps.movieenthusiasts.places;

import java.util.Date;
import java.util.HashMap;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.reservations.Reservation;
import ftnhps.movieenthusiasts.reservations.ReservationRepository;

@Transactional(readOnly = true)
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Place add(Place input) {
		return placeRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Place edit(Long id, Place input) {
		if(findOne(id) == null)
			return null;

		input.setId(id);
		return placeRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
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
		placeRepository.save(place);
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

	@Override
	public ChartDTO getChartDataMonth(Place place) {
		ChartDTO chartDTO= new ChartDTO(1);
		
		//napravim mapu za vrednosti para/posete u mesece
		HashMap<Integer, Integer[]> dataMap = new HashMap<>();
		//prvo postavim labele
		for(int i =0; i < 31 ; i++)
		{
			//inicjalizujem mapu
			dataMap.put(i,new Integer[] {0, 0});
			//popunim labele
			StringBuilder sb = new StringBuilder();
			sb.append("");
			sb.append(i+1);
			String strI = sb.toString();
			chartDTO.lables[i]=strI;
		}
		
		//trenutni datum
		Date currentDate = new Date();
		int month = currentDate.getMonth();
		
		// izracunavam koliko je rezervacija bilo u tom mesece i koliko je to ukupno kesa
		List<Reservation> reservations = reservationRepository.findByDateTime_Projection_Place(place);
		for(Reservation r:reservations) {
			Date date = new Date(r.getDateTime().getTimeStamp() * 1000);
			int currentMonth = date.getMonth();
			if(currentMonth != month)
				continue;
			int currentDay = date.getDay();
			Integer [] currentDayData = dataMap.get(currentDay);
			currentDayData[0]+=1;
			currentDayData[1]+=(int) r.getPriceWithDiscount();
			dataMap.put(currentDay,currentDayData);
		}
		

		for (int i =0; i<31; i++) {
			chartDTO.data[0][30 - i] = dataMap.get(i)[0];
			chartDTO.data[1][30 - i] = dataMap.get(i)[1];
		}
		
		return chartDTO;
	}

}
