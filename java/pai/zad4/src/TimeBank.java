package zad4;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;


class TimeBank{
	private HashMap<Integer, Service> 			services 				= new HashMap<Integer, Service>();
	private HashMap<User, LinkedList<Integer>>	userServices			= new HashMap<>();
	private HashMap<User, LinkedList<Integer>>	userReservedServices	= new HashMap<>();
	private LinkedList<Integer> 				avalibeServices 		= new LinkedList<Integer>();
	
	void createService(String name, User owner, Date startDate, Date endDate) throws WrongDateAndTimeExcpetion{
		if(startDate.after(endDate)) throw new WrongDateAndTimeExcpetion();
		Service service = new Service(name, owner, startDate, endDate);
		services.put(service.getServiceNo(), service);
		synchronized(this){
			avalibeServices.add(service.getServiceNo());
			if(userServices.containsKey(owner))
				userServices.get(owner).add(service.getServiceNo());
			else{
				LinkedList<Integer> ownerServices = new LinkedList<>();
				ownerServices.add(service.getServiceNo());
				userServices.put(owner, ownerServices);
			}
		}
	}
	
	String getAvaibleServices() throws NoServicesException{
		int i = 0;
		String avaibleServicesString = "";
		synchronized(this){
			Iterator<Integer> iterator = avalibeServices.iterator();
			while(iterator.hasNext()){
				Service serviceToDisplay = services.get(iterator.next());
				avaibleServicesString += Integer.toString(++i) + ". " + serviceToDisplay.getName() + "\n";
			}
		}
		if(avaibleServicesString.equals("")) throw new NoServicesException ();
		return avaibleServicesString;
	}

	String getUserServices(User owner) throws NoServicesException{
		int i= 0;
		LinkedList toDisplay;
		Iterator userServiceI;
		String usersServicesString = "";

		synchronized(this){
			if(userServices.containsKey(owner)){
				toDisplay = userServices.get(owner);
				userServiceI = toDisplay.iterator();
				while(userServiceI.hasNext()){
					Service serviceToDisplay = services.get(userServiceI.next());
					usersServicesString += Integer.toString(++i) + ". " + serviceToDisplay.getName() + "\n";
				}
			}	
		}
		if(usersServicesString.equals("")) throw new NoServicesException();
		else return usersServicesString;
	}
	
	void reserveService(User customer, int choice)throws NoSuchServiceException{
		int serviceNo;
		Service serviceToReserve;

		synchronized(this){
			try{
				serviceNo = avalibeServices.remove(choice - 1);
				serviceToReserve = services.get(serviceNo);
				serviceToReserve.setReservationHolder(customer);
				
				if(userReservedServices.containsKey(customer))
					userReservedServices.get(customer).add(serviceToReserve.getServiceNo());
				else{
					LinkedList<Integer> reservedServices = new LinkedList<>();
					reservedServices.add(serviceToReserve.getServiceNo());
					userReservedServices.put(customer, reservedServices);
				}
			}catch(IndexOutOfBoundsException e){throw new NoSuchServiceException();}
		}
	} 

	String getUserReservedServices(User user) throws NoServicesException{
		int i= 0;
		LinkedList toDisplay;
		Iterator userReservedServiceI;
		String userReservedServicesString = "";
		
		synchronized(this){
			if(userReservedServices.containsKey(user)){
				toDisplay = userReservedServices.get(user);
				userReservedServiceI = toDisplay.iterator();
				while(userReservedServiceI.hasNext()){
					Service serviceToDisplay = services.get(userReservedServiceI.next());
					userReservedServicesString += Integer.toString(++i) + ". " + serviceToDisplay.getName() + "\n";
				}
			}	
		}
		if(userReservedServicesString.equals("")) throw new NoServicesException();
		else return userReservedServicesString;
	}

	void cancelService(User owner, int choice) throws NoSuchServiceException{
		Integer serviceNo;
		Service serviceToCancel;
		synchronized(this){
			if(userServices.containsKey(owner)){
				try{
					serviceNo = userServices.get(owner).remove(choice - 1);
					serviceToCancel = services.remove(serviceNo);
					if(userReservedServices.containsKey(serviceToCancel.getReservationHolder()))
					userReservedServices.get(serviceToCancel.getReservationHolder()).remove(serviceNo);
				}catch(IndexOutOfBoundsException e){throw new NoSuchServiceException();}
			}
		}
	}
}
