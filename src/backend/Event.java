package backend;

//23 Oct 2021
/**
 *
 *@author cen7
 *
 */
public class Event {

    private String eventName;
    private int eventCapacity;
    private String eventHost;
    /**
     * @param eventName
     * @param eventCapacity
     * @param eventHost
     */
    public Event(String eventName, int eventCapacity, String eventHost) {
        
        this.eventName = eventName;
        this.eventCapacity = eventCapacity;
        this.eventHost = eventHost;
    }
    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }
    /**
     * @return the eventCapacity
     */
    public int getEventCapacity() {
        return eventCapacity;
    }
    /**
     * @return the eventHost
     */
    public String getEventHost() {
        return eventHost;
    }
    
    
    
    
}
