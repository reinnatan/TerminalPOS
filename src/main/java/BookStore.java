import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "bookstore")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {"books", "storeName", "location"})
public class BookStore {

    //@XmlElementWrapper(name = "bookList")
    // XmlElement sets the name of the entities
    //@XmlElement(name = "books")
    private List<Book> bookList;
    private String storeName;
    private String location;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
