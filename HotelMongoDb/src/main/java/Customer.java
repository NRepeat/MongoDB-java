import java.io.Serializable;


   public class Customer extends Check implements Serializable {

        private static final long serialVersionUID = 1L;

        private String customerName = null;
        private Integer customerId = 0;
        private String[] reservation_room = null;

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public String[] getReservation_room() {
            return reservation_room;
        }

        public void setReservation_room(String[] reservation_room) {
            this.reservation_room = reservation_room;
        }

        public Customer() {

        }

        public Customer(String customerName, Integer customerId, String[] reservation_room) {
            super();
            this.customerName = customerName;
            this.customerId = customerId;
            this.reservation_room = reservation_room;
        }


        @Override
        public String toString() {
            StringBuilder roomStringBuilder = new StringBuilder();
            for (String room : reservation_room) {
                roomStringBuilder.append(room+"\n");
            }
            return "customerName: " + this.customerName + "\ncustomerId: " + this.customerId+"\nroom:\n" + roomStringBuilder;
        }
    }

