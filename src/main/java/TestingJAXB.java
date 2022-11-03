import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestingJAXB {
    public static void main(String[] args)  {
        try {
            List<Book> listBook = new ArrayList<Book>();

            Book book = new Book();
            book.setAuthor("Reinhart");
            book.setDate(new Date());
            book.setName("Reinhart Simanjuntak");
            listBook.add(book);

            //JAXBContext context = JAXBContext.newInstance(Book.class);
            //Marshaller mar = context.createMarshaller();
            //mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //mar.marshal(book, new File("./book.xml"));

            //Book temp = (Book) context.createUnmarshaller().unmarshal(new FileReader("./book.xml"));
            //temp.setAuthor("Reinhart");
            //System.out.println(temp.getId()+" "+temp.getName()+" "+temp.getAuthor());

            BookStore bookStrore = new BookStore();
            bookStrore.setStoreName("Toko Reinhart");
            bookStrore.setLocation("Jalan Kintamani 11");
            bookStrore.setBookList(listBook);

            JAXBContext context = JAXBContext.newInstance(BookStore.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(bookStrore, new File("./bookstore.xml"));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
