/*
 * Bridge.java
 *
 * Created on January 31, 2004, 11:46 AM
 */

package metamodel;
/**
 * A Bridge describes the requirments placed by a client domain on a server domain
 *
 * @author  smr
 */
public class Bridge {
    
    /** ID of this Bridge */
    String id = null;
    
    /** the description of the Bridge */
    private String description = "";
    
    /** the client Domain of this Bridge */
    private Domain client = null;
    
    /** the server Domain of this Bridge */
    private Domain server = null;
    
    
    
    /** Creates a new instance of Bridge */
    public Bridge() {
    }
    
    /** Getter for property client.
     * @return Value of property client.
     *
     */
    public Domain getClient() {
        return client;
    }
    
    /** Setter for property client.
     * @param client New value of property client.
     *
     */
    public void setClient(Domain client) {
        this.client = client;
    }
    
    /** Getter for property description.
     * @return Value of property description.
     *
     */
    public java.lang.String getDescription() {
        return description;
    }
    
    /** Setter for property description.
     * @param description New value of property description.
     *
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    
    /** Getter for property id.
     * @return Value of property id.
     *
     */
    public java.lang.String getId() {
        return id;
    }
    
    /** Setter for property id.
     * @param id New value of property id.
     *
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }
    
    /** Getter for property server.
     * @return Value of property server.
     *
     */
    public Domain getServer() {
        return server;
    }
    
    /** Setter for property server.
     * @param server New value of property server.
     *
     */
    public void setServer(Domain server) {
        this.server = server;
    }
    
}
