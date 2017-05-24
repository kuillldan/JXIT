package vo;



/**
 * Subitem entity. @author MyEclipse Persistence Tools
 */

public class Subitem  implements java.io.Serializable {


    // Fields    

     private Integer sid;
     private Item item;
     private String title;


    // Constructors

    /** default constructor */
    public Subitem() {
    }

    
    /** full constructor */
    public Subitem(Item item, String title) {
        this.item = item;
        this.title = title;
    }

   
    // Property accessors

    public Integer getSid() {
        return this.sid;
    }
    
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Item getItem() {
        return this.item;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
   








}