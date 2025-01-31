public class TrainLine {

    private static final String EMPTY_LINE_MESSAGE = "The line is empty.";

    /** Points to first station in the train line */
    private Station head;

    /**
     * Points to the last station and facilitates O(1) performance when adding
     * a new station at the end of the line
     */
    private Station tail;

    /** Current number of stations in this object */
    private int numberOfStations;


    public static void main(String[] args) {
        
        // Builds an example trainline & then prints the results of the reverseListStations method
        TrainLine lincolnService = new TrainLine();
        lincolnService.addStation("Chicago");
        lincolnService.addStation("Summit");
        lincolnService.addStation("Joliet");
        lincolnService.addStation("Dwight");
        lincolnService.addStation("Pontiac");
        lincolnService.addStation("Bloomington");
        lincolnService.addStation("Lincoln");
        lincolnService.addStation("Springfield");
        lincolnService.addStation("Carlinville");
        lincolnService.addStation("Alton");
        lincolnService.addStation("Saint Louis");
        System.out.println(lincolnService.reverseListStations());
    }

    /*
     * This recursive method is used to concatenate the next station's in front of the current station if the head station is not the only station (determined by overloaded alternative method)
     */
    public String reverseListStations(Station station) {
        String result; // Stores the result of the method's operation
        if (!station.hasNext()) {
            result = station.getName(); // If the current station is the last in the trainline, returns this station and begins the recursive string concatenation
        } else {
            result = reverseListStations(station.getNext()) + "\n" + station.getName(); // If the current station is not the last in the trainline, calls itself and waits for the results to come back
        }
        return result;
    }

    /*
     * This method is used to determine if the head station is null and, if not, calls the other recursive overload method to get the value of next stations and concatenate to the head station
     */
    public String reverseListStations() {
        String result; // Initializes the variable to store our resulting string
        Station head = this.getHead(); // Initializes and declares a variable referring to the this Trainline's head Station
        if (head == null) {
            result = EMPTY_LINE_MESSAGE; // If the head Station is null, returns the default empty line message
        } else if (!head.hasNext()) {
            result = head.getName(); // If the head station is the only station, simply returns the name of the head station
        } else {
            result = reverseListStations(head.getNext()) + "\n" + head.getName(); // If there are additional stations after the head station, calls the overloaded method to concatenate the next station names
        }
        return result;
    }

    /** Default constructor - redundant but good to show intent */
    public TrainLine() {
        this.head = null;
        this.tail = null;
        this.numberOfStations = 0;
    } // default constructor

    /** Accessor for number of stations present in this trainline */
    public int getNumberOfStations() {
        return this.numberOfStations;
    } // method getNumberOfStations

    /**
     * Add a new station at the end of this trainline. The method creates
     * a new station object, first, with the given name. Then it checks to
     * if this line has a head station yet; if not, the new station becomes
     * the head station. If this line has a head station, the method begins
     * traversing this line from its head station, following the next points
     * untils it finds a station whose next is null. That station, by definition
     * is presently the last station in the line. The new station is added after
     * that last station.
     * 
     * @param name String with name of new station to add
     */
    public void addStation(String name) {
        // Create a new station object with the given name
        Station newStation = new Station(name);
        // Use addStion(Station) method
        this.addStation(newStation);
    } // method addStation

    /**
     * Add a new station at the end of this trainline. The method takes
     * a station object and it checks if this line has a head station yet;
     * if not, the new station becomes the head station. If this line has a
     * head station, the method places the new station after the last station
     * in the line and marks that new station the last station in the line.
     * 
     * @param station Station object to insert at teh end of the line
     */
    public void addStation(Station station) {
        // Check if this trainline has a head station yet or not
        if (this.head == null) {
            // There is no head station in this trainline. Make the
            // new station, just created, the head station and also
            // the tail station of the line and we are done.
            this.head = station;
            this.tail = station;
        } else {
            // The trainline has an existing head station. Therefore,
            // it also has a known last station (this.tail).
            this.tail.setNext(station); // add new station after tail station
            this.tail = station; // Designate newly added station as tail station
        }
        // Update station counter
        this.numberOfStations++;
    } // method addStation

    /**
     * Determines if the linked list contains a loop. A loop forms when
     * the last station (this.tail) points to another station in the
     * train line instead of pointing to null. An empty line (this.head==null)
     * is considered loop-free.
     * 
     * @return true if there is a loop, false otherwise
     */
    public boolean hasLoop() {
        boolean loopFound = false;
        // Perform the check only when the train line is not empty
        if (this.head != null) {
            // Create a fast and a slow cursor.
            Station fast = this.head;
            Station slow = this.head;
            while (!loopFound && fast.hasNext() && fast.getNext().hasNext()) {
                fast = fast.getNext().getNext();
                slow = slow.getNext();
                loopFound = (slow == fast);
            }
        }
        return loopFound;
    } // method hasLoop

    /**
     * Inserts a new station after an existing one.
     * 
     * @param existingStationName String with name of existing station that we
     *                            are adding a station after.
     * @param stationToAdd        String with name of new station to add.
     * @return true if insertion is successful, false if there is a problem.
     *         Potential problems inlude the presence of the station we are trying
     *         to add, the absence of the station we are trying to add after, and
     *         null/empty strings.
     */
    public boolean addAfter(String existingStationName, String stationToAdd) {
        boolean success = false;
        // Check if the station to add is already present in the TrainLine
        // object or if the supplied strings are null or empty.
        if (!this.contains(stationToAdd)
                && existingStationName != null && existingStationName.length() > 0
                && stationToAdd != null && stationToAdd.length() > 0) {
            // Traverse the TrainLine, looking for the existing station
            Station current = this.head;
            while (current != null) {
                // Check if the current station is the one we are looking for.
                // If the intended station is not found, we skill the if block,
                // the while-loop eventually ends, and we return the intial
                // value of success which is still false.
                if (current.getName().equals(existingStationName)) {
                    // Intended station found, time to get things going, first
                    // by creating the new station to insert.
                    Station newStation = new Station(stationToAdd);
                    // Make the new station point to where the existing station points
                    newStation.setNext(current.getNext());
                    // Make the existing station point to the new station
                    current.setNext(newStation);
                    // Update the return variable to indicate a successful insertion
                    success = true;
                }
            }
        }
        return success;
    } // method addAfter

    /**
     * Finds the position of a station with the specified name. If the station
     * is not present in the train line, the reported position is -1.
     * 
     * @param name String with name of station to look for in this train line.
     * @return int position of named station in train line or -1 if station
     *         is not present or the trainline is empty.
     */
    public int indexOf(String name) {
        // Return value
        int position = -1;
        // Make sure line is not empty first
        if (this.head != null) {
            // Counts the stations as we traverse the line.
            int counter = 0;
            // Begin traversing the line from start
            Station current = this.head;
            while (position < 0 && current != null) {
                // Update position if the current station matches the specified name
                position = (current.getName().equals(name)) ? counter : position;
                // Update counter
                counter++;
                // Advance station
                current = current.getNext();
            }
        }
        return position;
    } // method indexOf

    /**
     * Determines if a station with a specific name is present in this TrainLine.
     * Method contains is essentially a wrapper method for indexOf. If a station
     * is present in this trainline its indexOf will be > -1. Therefore, any
     * indexOf value > -1 indicates that the named station is contained in the
     * trainline.
     * 
     * @param stationName String with station name to search for
     * @return true if station found; false otherwise or if object has no stations.
     */
    public boolean contains(String stationName) {
        return this.indexOf(stationName) > -1;
    } // method contains

    /**
     * Accessor for this.head
     * This accessor is necessary so that we can tell if other similar objects
     * have a null head or not.
     * 
     * @return Station this.head
     */
    public Station getHead() {
        return this.head;
    } // method getHead

    /**
     * Accessor for this.tail
     * 
     * @return Station this.tail
     */
    public Station getTail() {
        return this.tail;
    } // method getHead

    /**
     * Appends a trainline to the current trainline object
     * 
     * @param other Trainline to append to present object
     */
    public void append(TrainLine other) {
        // First make sure that the trainline we wish to append is not null or empty
        if (other != null && other.getHead() != null) {
            // OK, we have something to append, now how to append it?
            if (this.head == null) {
                // If this trainline is empty, we use the other trainline's head and tail
                this.head = other.getHead();
            } else {
                // otherwise, we point this.tail to other.head ae
                this.tail.setNext(other.getHead());
            }
            // Either way it's the same tail
            this.tail = other.getTail();
        }
    } // method append

    /** Produces a list of stations in the order of traversal. Use StringBuilder to mutable objects */
    public String listStations() {
        StringBuilder stationList = new StringBuilder();
        if (this.head == null) {
            stationList.append(EMPTY_LINE_MESSAGE);
        } else {
            Station current = this.head;
            while (current != null) {
                stationList.append(String.format("%s\n", current.getName()));
                current = current.getNext();
            }
        }
        return stationList.toString();
    } // method listStations

    /**
     * Determines if two trainline objects intersect, i.e., they both have a station
     * with the same name. The method stops as soon as an intersection is found.
     * Worst case scenarion, when the two lines intersect at their last stations, on
     * do not intersect at all, the method runs an exhaustive traversal for both
     * lines. Nulls are treated as empty lines (no intersectin found)
     */
    public boolean intersects(TrainLine other) {
        // Check if two objects are the same
        boolean intersectionFound = this == other;
        // If they are not the same, check them thoroughly, but if they are, 
        // skip the traversals and return true: by definition an trainline
        // intersects itself
        if (!intersectionFound) {
            // Reset boolean to avoid null objects
            intersectionFound = this.head != null && other.getHead() != null;
            Station thisCurrent = this.head;
            while (!intersectionFound && thisCurrent != null) {
                Station otherCurrent = other.getHead();
                while (!intersectionFound && otherCurrent != null) {
                    intersectionFound = (thisCurrent.getName().equals(otherCurrent.getName()));
                    otherCurrent = otherCurrent.getNext();
                }
                thisCurrent = thisCurrent.getNext();
            }
        }
        return intersectionFound;
    } // method intersects

    /**
     * Compares two lines based on their number of train stations. 
     */
    public int compareTo(TrainLine other) {
        return this.numberOfStations - other.getNumberOfStations();
    } // method compareTo

    /*
     * 
     */

} // class TrainLine