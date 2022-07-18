package zad4;

import java.util.Date;

class Service{
	private static  int 	uniqueKeeper = 0;

	private final 	String 	name;
	private final 	User 	owner;
	private 		User 	reservationHolder;
	private 		Date 	startDate, endDate;
	private final	int		serviceNo;

	Service(String theName, User theOwner,Date theStartDate, Date theEndDate){
		name 				= theName;
		owner 				= theOwner;
		startDate			= theStartDate;
		endDate				= theEndDate;
		serviceNo			= ++uniqueKeeper;
	}

	@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			
			if (!(obj instanceof Service)) {
				return false;
			}

			Service c = (Service) obj;
			return this.name.equals(c.serviceNo);
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the owner
		 */
		public User getOwner() {
			return owner;
		}

		/**
		 * @return the reservationHolder
		 */
		public User getReservationHolder() {
			return reservationHolder;
		}

		/**
		 * @return the endDate
		 */
		public Date getEndDate() {
			return endDate;
		}

		/**
		 * @return the startDate
		 */
		public Date getStartDate() {
			return startDate;
		}

		/**
		 * @return the serviceNo
		 */
		public int getServiceNo() {
			return serviceNo;
		}

		/**
		 * @param reservationHolder the reservationHolder to set
		 */
		public void setReservationHolder(User reservationHolder) {
			this.reservationHolder = reservationHolder;
		}
}